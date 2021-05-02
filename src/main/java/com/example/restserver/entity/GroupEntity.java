package com.example.restserver.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class GroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    private String name;
    private Long admin_id;

    @ManyToMany
    private List<UserEntity> members;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "group")
    private List<GroupTaskEntity> tasks;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "group")
    private List<GroupEventEntity> events;

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

    public Long getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(Long admin_id) {
        this.admin_id = admin_id;
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
}
