package com.example.restserver.entity;

import com.example.restserver.model.User;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Entity
public class GroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "admin_id")
    private UserEntity admin;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinTable(name = "group_user", joinColumns = {@JoinColumn(name = "group_id")}, inverseJoinColumns = {@JoinColumn (name = "user_id")})
    private Set<UserEntity> members = new HashSet<>();

    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "group")
    private List<GroupTaskEntity> tasks;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "group")
    private List<GroupEventEntity> events;


    public void addMember(UserEntity newUser) {
        members.add(newUser);
        newUser.getGroups().add(this);
    }

    public void deleteMember(UserEntity member) {
        UserEntity memberToDelete = null;
        for (UserEntity user: members) {
            if (member.getEmail().equals(user.getEmail())) {
                memberToDelete = user;
            }
        }
        if (memberToDelete == null) return;
        this.members.remove(memberToDelete);
        GroupEntity groupToDelete = null;
        for (GroupEntity group : memberToDelete.getGroups()) {
            if (group.getAdmin().getEmail().equals(this.getAdmin().getEmail())) {
                groupToDelete = group;
            }
        }
        member.getGroups().remove(groupToDelete);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<UserEntity> getMembers() {
        return members;
    }

    public void setMembers(Set<UserEntity> members) {
        this.members = members;
    }

    public List<GroupTaskEntity> getTasks() {
        return tasks;
    }

    public void setTasks(List<GroupTaskEntity> tasks) {
        this.tasks = tasks;
    }

    public List<GroupEventEntity> getEvents() {
        return events;
    }

    public void setEvents(List<GroupEventEntity> events) {
        this.events = events;
    }

    public UserEntity getAdmin() { return admin; }

    public void setAdmin(UserEntity admin) { this.admin = admin; }
}
