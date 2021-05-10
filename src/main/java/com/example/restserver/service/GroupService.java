package com.example.restserver.service;

import com.example.restserver.entity.GroupEntity;
import com.example.restserver.entity.GroupTaskEntity;
import com.example.restserver.entity.UserEntity;
import com.example.restserver.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupService {
    @Autowired
    GroupRepository groupRepository;

    public GroupEntity save(GroupEntity newGroup) {
        groupRepository.save(newGroup);
        return newGroup;
    }

    public boolean deleteById(Long id) {
        groupRepository.deleteById(id);
        return true;
    }

    public boolean groupExist(GroupEntity group) {
        if (groupRepository.findByAdminAndName(group.getAdmin(), group.getName()) != null) return true;
        return false;
    }


    public GroupEntity findByAdminAndName(UserEntity admin, String name) {
        return groupRepository.findByAdminAndName(admin, name);
    }

    public void deleteByNameAndAdmin(String name, UserEntity admin) {
        groupRepository.deleteByNameAndAdmin(name, admin);
    }
}
