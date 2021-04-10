package com.example.restserver.entity;

import javax.persistence.*;

@Entity
public class GroupTaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private boolean isDone;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private GroupEntity group;
}
