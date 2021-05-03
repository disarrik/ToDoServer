package com.example.restserver.controller;

import com.example.restserver.entity.GroupEntity;
import com.example.restserver.entity.GroupEventEntity;
import com.example.restserver.entity.GroupTaskEntity;
import com.example.restserver.entity.UserEntity;
import com.example.restserver.exception.GroupNotFoundException;
import com.example.restserver.exception.UserNotFoundException;
import com.example.restserver.model.Group;
import com.example.restserver.service.GroupEventService;
import com.example.restserver.service.GroupService;
import com.example.restserver.service.GroupTaskService;
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

    @Autowired
    private GroupTaskService groupTaskService;

    @Autowired
    private GroupEventService groupEventService;

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

    @DeleteMapping("/kickMember")
    public ResponseEntity deleteMember(@RequestBody AdminAndGroupAndUserHolder holder) {
        try{
            UserEntity admin = holder.getAdmin();
            GroupEntity group = holder.getGroup();
            UserEntity newMember = holder.getNewMember();
            admin = userService.findByEmailAndPassword(admin.getEmail(), admin.getPassword());
            group = groupService.findByAdminAndName(admin, group.getName());
            newMember = userService.findByEmail(newMember.getEmail());
            if (admin != null && group != null && newMember != null) {
                group.deleteMember(newMember);
                groupService.deleteById(group.getId());
                groupService.create(group);
                return ResponseEntity.ok("Пользователь удален");
            } else {
                return ResponseEntity.badRequest().body("Пользователь не может быть удален!");
            }
        }
        catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body("Пользователь не может быть удален");
        }
    }

    @PostMapping("/addTask")
    public ResponseEntity addTask(@RequestBody AdminAndGroupAndTaskHolder holder) {
        try {
            UserEntity admin = holder.getAdmin();
            GroupEntity group = holder.getGroup();
            GroupTaskEntity newTask = holder.getTask();
            admin = userService.findByEmailAndPassword(admin.getEmail(), admin.getPassword());
            group = groupService.findByAdminAndName(admin, group.getName());
            if (admin != null && group != null && groupTaskService.findByGroupAndName(group, newTask.getName()) == null) {
                newTask.setGroup(group);
                groupTaskService.add(newTask);
                return ResponseEntity.ok("Задание создано");
            } else {
                return ResponseEntity.badRequest().body("Задание не может быть создано!");
            }
        }
        catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body("Администратор не найден");
        }
        catch (GroupNotFoundException e) {
            return ResponseEntity.badRequest().body("Группа не найдена");
        }
    }

    @PostMapping("/done")
    public ResponseEntity done(@RequestBody TaskAndUserHolder holder) {
        try {
            GroupTaskEntity task = holder.getTask();
            UserEntity user = holder.getUser();
            user = userService.findByEmailAndPassword(user.getEmail(), user.getPassword());
            UserEntity admin = task.getGroup().getAdmin();
            admin = userService.findByEmail(admin.getEmail());
            GroupEntity groupOfTask = task.getGroup();
            groupOfTask = groupService.findByAdminAndName(admin, groupOfTask.getName());
            task = groupTaskService.findByGroupAndName(groupOfTask, task.getName());
            task.completeTask(user);
            groupTaskService.add(task);
            return ResponseEntity.ok("Выполнено");
        }
        catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body("Пользователя не существует");
        }
        catch (GroupNotFoundException e) {
            return ResponseEntity.badRequest().body("Группы не существует");
        }
    }

    @GetMapping("/view")
    public ResponseEntity view(@RequestBody UserEntity user) {
        try {
            user = userService.findByEmailAndPassword(user.getEmail(), user.getPassword());
            return ResponseEntity.ok(Group.entityToModel(user.getGroups()));
        }
        catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body("Пользователь не найден");
        }
    }

    @PostMapping("/addEvent")
    public ResponseEntity addEvent(@RequestBody AdminAndGroupAndEventHolder holder) {
        try{
            UserEntity admin = holder.getAdmin();
            GroupEntity group = holder.getGroup();
            admin = userService.findByEmailAndPassword(admin.getEmail(), admin.getPassword());
            group = groupService.findByAdminAndName(admin, group.getName());
            //todo group can be null
            GroupEventEntity event = holder.getEvent();
            if (group != null && admin != null && !groupEventService.existByGroupAndName(group, event.getName())) {
                event.setGroup(group);
                groupEventService.add(event);
                return ResponseEntity.ok("Событие добавлено");
            }
            return ResponseEntity.badRequest().body("Событие с таким именем уже существует");
        }
        catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body("Пользователь не найден");
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

class AdminAndGroupAndTaskHolder {
    public UserEntity getAdmin() {
        return admin;
    }

    public void setAdmin(UserEntity admin) {
        this.admin = admin;
    }

    public GroupEntity getGroup() {
        return group;
    }

    public void setGroup(GroupEntity group) {
        this.group = group;
    }

    public GroupTaskEntity getTask() {
        return task;
    }

    public void setTask(GroupTaskEntity task) {
        this.task = task;
    }

    private UserEntity admin;
    private GroupEntity group;
    private GroupTaskEntity task;


}

class TaskAndUserHolder {
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public GroupTaskEntity getTask() {
        return task;
    }

    public void setTask(GroupTaskEntity task) {
        this.task = task;
    }

    private UserEntity user;
    private GroupTaskEntity task;
}

class AdminAndGroupAndEventHolder {
    private UserEntity admin;
    private GroupEntity group;
    private GroupEventEntity event;

    public UserEntity getAdmin() {
        return admin;
    }

    public void setAdmin(UserEntity admin) {
        this.admin = admin;
    }

    public GroupEventEntity getEvent() {
        return event;
    }

    public void setEvent(GroupEventEntity event) {
        this.event = event;
    }

    public GroupEntity getGroup() {
        return group;
    }

    public void setGroup(GroupEntity group) {
        this.group = group;
    }
}
