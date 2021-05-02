package com.example.restserver.repository;

import com.example.restserver.entity.GroupEntity;
import org.springframework.data.repository.CrudRepository;

public interface GroupRepository extends CrudRepository<GroupEntity, Long> {
}
