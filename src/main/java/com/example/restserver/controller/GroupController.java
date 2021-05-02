package com.example.restserver.controller;

import com.example.restserver.entity.GroupEntity;
import com.example.restserver.entity.UserEntity;
import com.example.restserver.exception.UserNotFoundException;
import com.example.restserver.repository.GroupRepository;
import com.example.restserver.service.GroupService;
import com.example.restserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody AdminAndGroupHolder holder) {
        try {
            UserEntity admin = holder.getAdmin();
            System.out.println(1);
            admin = userService.findByEmailAndPassword(admin.getEmail(), admin.getPassword());//
            GroupEntity newGroup = holder.getNewGroup();
            if (userService.userExist(admin)) {
                newGroup.setAdmin(admin);
                if (!groupService.groupExist(newGroup)) {
                    groupService.create(newGroup);
                    return ResponseEntity.ok("Группа создана");
                }
                else {
                    return ResponseEntity.badRequest().body("Такая группа уже существует");
                }
            } else {
                return ResponseEntity.badRequest().body("Такого администратора не существет");
            }
        }
        catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body("Такая группа не может быть создана");
        }
    }

    @PostMapping("/addMember")
    public ResponseEntity addMember(@RequestBody AdminAndGroupAndUserHolder holder) {
        try{
            UserEntity admin = holder.getAdmin();
            GroupEntity group = holder.getGroup();
            UserEntity newMember = holder.getNewMember();
            admin = userService.findByEmailAndPassword(admin.getEmail(), admin.getPassword());
            group = groupService.findByAdminAndName(admin, group.getName());
            newMember = userService.findByEmail(newMember.getEmail());
            if (admin != null && group != null && newMember != null) {
                group.addMember(newMember);
                groupService.create(group);
                return ResponseEntity.ok("Пользователь добавлен");
            } else {
                return ResponseEntity.badRequest().body("Пользователь не может быть добавлен!");
            }
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Пользователь не может быть добавлен");
        }
    }
}

class AdminAndGroupHolder {
    public UserEntity getAdmin() {
        return admin;
    }

    public void setAdmin(UserEntity admin) {
        this.admin = admin;
    }

    private UserEntity admin;

    public GroupEntity getNewGroup() {
        return newGroup;
    }

    public void setNewGroup(GroupEntity newGroup) {
        this.newGroup = newGroup;
    }

    private GroupEntity newGroup;
}

class AdminAndGroupAndUserHolder {
    private UserEntity admin;

    public UserEntity getAdmin() {
        return admin;
    }

    public void setAdmin(UserEntity admin) {
        this.admin = admin;
    }

    public UserEntity getNewMember() {
        return newMember;
    }

    public void setNewMember(UserEntity user) {
        this.newMember = user;
    }

    public GroupEntity getGroup() {
        return group;
    }

    public void setGroup(GroupEntity group) {
        this.group = group;
    }

    private UserEntity newMember;
    private GroupEntity group;
}
