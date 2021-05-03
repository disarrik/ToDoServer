package com.example.restserver.model;

import com.example.restserver.entity.GroupTaskEntity;

public class GroupTask {
    private String name;
    private String description;
    private String dueDate;
    private boolean isDone;

    private GroupTask(String name, String description, String dueDate, boolean isDone) {
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
        this.isDone = isDone;
    }

    public static GroupTask entityToModel(GroupTaskEntity groupTaskEntity, boolean isDone) {
        return new GroupTask(groupTaskEntity.getName(), groupTaskEntity.getDescription(), groupTaskEntity.getDueDate(), isDone);
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

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
