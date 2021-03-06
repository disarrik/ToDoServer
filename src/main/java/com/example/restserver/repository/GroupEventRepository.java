package com.example.restserver.repository;

import com.example.restserver.entity.GroupEntity;
import com.example.restserver.entity.GroupEventEntity;
import org.springframework.data.repository.CrudRepository;

public interface GroupEventRepository extends CrudRepository<GroupEventEntity, Long> {
    GroupEventEntity findByGroupAndName(GroupEntity group, String name);
}
