package com.example.restserver.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class GroupTaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String dueDate;
//    @ManyToMany
//    //todo
//    private List<UserEntity> hasDone;


    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "group_id")
    private GroupEntity group;

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
}
