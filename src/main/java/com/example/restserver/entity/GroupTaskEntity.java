package com.example.restserver.entity;

import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class GroupTaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String dueDate;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "task_userHasDone", joinColumns = {@JoinColumn(name = "task_id")}, inverseJoinColumns = {@JoinColumn (name = "user_id")})
    private Set<UserEntity> hasDone;


    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "group_id")
    private GroupEntity group;


    public void completeTask(UserEntity member) {
        this.getHasDone().add(member);
        member.getDoneTasks().add(this);
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public GroupEntity getGroup() {
        return group;
    }

    public void setGroup(GroupEntity group) {
        this.group = group;
    }

    public String getDueDate() { return dueDate; }

    public void setDueDate(String dueDate) { this.dueDate = dueDate; }

    public Set<UserEntity> getHasDone() { return hasDone; }

    public void setHasDone(Set<UserEntity> hasDone) { this.hasDone = hasDone; }
}
