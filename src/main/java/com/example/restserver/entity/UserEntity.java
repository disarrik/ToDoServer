package com.example.restserver.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullname;

    private String password;
    private String email;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "members")
    private Set<GroupEntity> groups;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE, mappedBy = "hasDone")
    private Set<GroupTaskEntity> doneTasks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullanme) {
        this.fullname = fullanme;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<GroupEntity> getGroups() {
        return groups;
    }

    public void setGroups(Set<GroupEntity> groups) {
        this.groups = groups;
    }

    public Set<GroupTaskEntity> getDoneTasks() {
        return doneTasks;
    }

    public void setDoneTasks(Set<GroupTaskEntity> doneTasks) {
        this.doneTasks = doneTasks;
    }
}
