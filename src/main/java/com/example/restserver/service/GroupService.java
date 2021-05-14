package com.example.restserver.service;

import com.example.restserver.entity.GroupEntity;
import com.example.restserver.entity.GroupTaskEntity;
import com.example.restserver.entity.UserEntity;
import com.example.restserver.exception.GroupNotFoundException;
import com.example.restserver.model.User;
import com.example.restserver.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupService {
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    UserService userService;
    @Autowired
    GroupTaskService groupTaskService;

    public GroupEntity save(GroupEntity newGroup) {
        groupRepository.save(newGroup);
        return newGroup;
    }

    public boolean deleteById(Long id) {
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

    public void deleteMember(UserEntity member, GroupEntity group){
        UserEntity memberToDelete = null;
        for (UserEntity user: group.getMembers()) {
            for (GroupTaskEntity task : group.getTasks()) {
                for (UserEntity userHasDone : task.getHasDone()) {
                    if (userHasDone.getId() == member.getId()) {
                        System.out.println("-----------------------");
                        task.getHasDone().remove(userHasDone);
                        userHasDone.getDoneTasks().remove(task);
                        try {
                            groupTaskService.save(task);
                        } catch (GroupNotFoundException e) {
                            e.printStackTrace();
                        }
                        userService.save(userHasDone);
                        break;
                    }
                }
            }
            if (member.getEmail().equals(user.getEmail())) {
                memberToDelete = user;
            }
        }
        if (memberToDelete == null) return;
        group.getMembers().remove(memberToDelete);
        GroupEntity groupToDelete = null;
        for (GroupEntity groupOfMemberToDelete : memberToDelete.getGroups()) {
            if (group.getAdmin().getEmail().equals(group.getAdmin().getEmail())) {
                groupToDelete = group;
            }
        }
        member.getGroups().remove(groupToDelete);
        this.deleteById(group.getId());
        this.save(group);
    }
}
