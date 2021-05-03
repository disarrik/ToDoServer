package com.example.restserver.model;
import com.example.restserver.entity.GroupEntity;
import com.example.restserver.entity.GroupEventEntity;
import com.example.restserver.entity.GroupTaskEntity;
import com.example.restserver.entity.UserEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class Group implements Serializable {

    private String name;
    private User admin;
    private List<User> members = new ArrayList<>();
    private List<GroupTask> tasks = new ArrayList<>();
    private List<GroupEvent> events = new ArrayList<>();

    private Group(String name, UserEntity admin, Set<UserEntity> members, List<GroupTaskEntity> tasks, List<GroupEventEntity> events) {
        this.name = name;
        this.admin = User.entityToModel(admin);
        for (UserEntity user : members) {
            this.members.add(User.entityToModel(user));
        }
        for (GroupTaskEntity task : tasks) {
            this.tasks.add(GroupTask.entityToModel(task));
        }
        for (GroupEventEntity event : events) {
            this.events.add(GroupEvent.entityToModel(event));
        }
    }

    public static Group entityToModel(GroupEntity groupEntity) {
        return new Group(groupEntity.getName(), groupEntity.getAdmin(), groupEntity.getMembers(), groupEntity.getTasks(), groupEntity.getEvents());
    }

    public static List<Group> entityToModel(Set<GroupEntity> groupEntities) {
        List<Group> groups = new ArrayList<>();
        for (GroupEntity group: groupEntities) {
            groups.add(entityToModel(group));
        }
        return groups;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    public List<GroupTask> getTasks() {
        return tasks;
    }

    public void setTasks(List<GroupTask> tasks) {
        this.tasks = tasks;
    }

    public List<GroupEvent> getEvents() { return events; }

    public void setEvents(List<GroupEvent> events) { this.events = events; }
}
