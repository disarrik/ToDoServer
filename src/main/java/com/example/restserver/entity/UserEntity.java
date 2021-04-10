package com.example.restserver.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullanme;
    private String password;
    private String email;

    @ManyToMany
    private List<GroupEntity> groups;
}
