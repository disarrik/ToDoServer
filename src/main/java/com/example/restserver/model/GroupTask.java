package com.example.restserver.model;

import com.example.restserver.entity.GroupTaskEntity;

public class GroupTask {
    private String name;
    private String description;
    private String dueDate;

    private GroupTask(String name, String description, String dueDate) {
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
    }

    public static GroupTask entityToModel(GroupTaskEntity groupTaskEntity) {
        return new GroupTask(groupTaskEntity.getName(), groupTaskEntity.getDescription(), groupTaskEntity.getDueDate());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
}
