package com.example.restserver.model;
import com.example.restserver.entity.GroupEntity;
import com.example.restserver.entity.UserEntity;
import com.example.restserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class Group {
    @Autowired
    UserRepository userRepository;

    private String name;
    private User admin;
    private List<User> members;

    private Group(String name, Long admin_id, List<UserEntity> members) {
        this.name = name;
        this.admin = User.entityToModel(userRepository.findById(admin_id).orElse(new UserEntity()));
        for (int i = 0; i < members.size(); i++) {
            this.members.add(User.entityToModel(members.get(i)));
        }
    }

    public static Group entityToModel(GroupEntity groupEntity) {
        return new Group(groupEntity.getName(), groupEntity.getAdmin_id(), groupEntity.getMembers());
    }
}
