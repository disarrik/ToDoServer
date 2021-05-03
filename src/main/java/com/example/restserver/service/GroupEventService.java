package com.example.restserver.service;

import com.example.restserver.entity.GroupEntity;
import com.example.restserver.entity.GroupEventEntity;
import com.example.restserver.repository.GroupEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupEventService {
    @Autowired
    GroupEventRepository groupEventRepository;

    public boolean existByGroupAndName(GroupEntity group, String name) {
        if (groupEventRepository.findByGroupAndName(group, name) == null) {
            return false;
        }
        return true;
    }

    public void add(GroupEventEntity newGroup) {
        groupEventRepository.save(newGroup);
    }
}
