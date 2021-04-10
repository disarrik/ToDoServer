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
}
