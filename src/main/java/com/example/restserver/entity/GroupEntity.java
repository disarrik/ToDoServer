package com.example.restserver.entity;

import com.example.restserver.model.User;

import javax.persistence.*;
import java.util.List;

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
    private List<UserEntity> members;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "group")
    private List<GroupTaskEntity> tasks;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "group")
    private List<GroupEventEntity> events;


    public void addMember(UserEntity newUser) {
        members.add(newUser);
        newUser.getGroups().add(this);
    }

    public void deleteMember(UserEntity member) {
        UserEntity memberToDelete = null;
        for (int i = 0; i < members.size(); i++) {
            if (member.getEmail().equals(members.get(i).getEmail())) {
                memberToDelete = members.get(i);
            }
        }
        this.members.remove(memberToDelete);
        GroupEntity groupToDelete = null;
        for (int i = 0; i < memberToDelete.getGroups().size(); i++) {
            if (memberToDelete.getGroups().get(i).getAdmin().getEmail().equals(this.getAdmin().getEmail())) {
                groupToDelete = memberToDelete.getGroups().get(i);
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

    public List<UserEntity> getMembers() {
        return members;
    }

    public void setMembers(List<UserEntity> members) {
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
