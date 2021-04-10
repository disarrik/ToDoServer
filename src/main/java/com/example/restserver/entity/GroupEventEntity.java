package com.example.restserver.entity;

import javax.persistence.*;

@Entity
public class GroupEventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String date;
    private String description;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private GroupEntity group;
}
