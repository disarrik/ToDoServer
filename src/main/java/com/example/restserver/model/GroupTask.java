package com.example.restserver.model;

import com.example.restserver.entity.GroupTaskEntity;

import java.util.Date;

public class GroupTask {
    private String name;
    private String description;
    private Date dueDate;
    private boolean isDone;

    private GroupTask(String name, String description, Date dueDate, boolean isDone) {
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

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
