package com.example.restserver.model;

import com.example.restserver.entity.GroupEventEntity;

public class GroupEvent {
    private String name;
    private String date;
    private String description;

    private GroupEvent(String name, String date, String description) {
        this.name = name;
        this.date = date;
        this.description = description;
    }

    public static GroupEvent entityToModel(GroupEventEntity groupEventEntity) {
        return new GroupEvent(groupEventEntity.getName(), groupEventEntity.getDate(), groupEventEntity.getDescription());
    }
}
