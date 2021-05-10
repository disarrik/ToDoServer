package com.example.restserver.service;

import com.example.restserver.entity.GroupEntity;
import com.example.restserver.entity.GroupTaskEntity;
import com.example.restserver.entity.UserEntity;
import com.example.restserver.exception.UserAlreadyExistException;
import com.example.restserver.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;

@Service
public class GroupService {
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    GroupTaskService groupTaskService;
    @Autowired
    UserService userService;

    public GroupEntity save(GroupEntity newGroup) {
        groupRepository.save(newGroup);
        return newGroup;
    }

    public boolean deleteById(Long id) {
        System.out.println("------------------------------------------------------");
        GroupEntity group = groupRepository.findById(id).orElse(new GroupEntity());
        for (UserEntity user : group.getMembers()) {
            user.getGroups().remove(group);
            userService.save(user);
        }
        for (GroupTaskEntity task : group.getTasks()) {
            groupTaskService.deleteById(task.getId());
        }
        groupRepository.deleteQuery(id);
        return true;
    }

    public boolean groupExist(GroupEntity group) {
        if (groupRepository.findByAdminAndName(group.getAdmin(), group.getName()) != null) return true;
        return false;
    }


    public GroupEntity findByAdminAndName(UserEntity admin, String name) {
        return groupRepository.findByAdminAndName(admin, name);
    }


}
