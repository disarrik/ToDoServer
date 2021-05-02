package com.example.restserver.service;

import com.example.restserver.entity.GroupEntity;
import com.example.restserver.entity.GroupTaskEntity;
import com.example.restserver.exception.GroupNotFoundException;
import com.example.restserver.repository.GroupRepository;
import com.example.restserver.repository.GroupTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupTaskService {
    @Autowired
    GroupTaskRepository groupTaskRepository;
    @Autowired
    GroupRepository groupRepository;

    public boolean add(GroupTaskEntity newTask) throws GroupNotFoundException {
        if(groupRepository.findById(newTask.getGroup().getId()) == null) {
            throw new GroupNotFoundException("Group not found");
        }
        else {
            groupTaskRepository.save(newTask);
            return true;
        }
    }

    public GroupTaskEntity findByGroupAndName(GroupEntity group, String name) {
        return groupTaskRepository.findByGroupAndName(group, name);
    }
}
