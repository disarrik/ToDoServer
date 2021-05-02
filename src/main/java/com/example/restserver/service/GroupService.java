package com.example.restserver.service;

import com.example.restserver.entity.GroupEntity;
import com.example.restserver.entity.GroupTaskEntity;
import com.example.restserver.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupService {
    @Autowired
    GroupRepository groupRepository;

    public GroupEntity create(GroupEntity newGroup) {
        groupRepository.save(newGroup);
        return newGroup;
    }

    public boolean deleteById(Long id) {
        groupRepository.deleteById(id);
        return true;
    }
}
