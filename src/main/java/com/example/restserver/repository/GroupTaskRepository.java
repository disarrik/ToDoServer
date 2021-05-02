package com.example.restserver.repository;

import com.example.restserver.entity.GroupTaskEntity;
import org.springframework.data.repository.CrudRepository;

public interface GroupTaskRepository extends CrudRepository<GroupTaskEntity, Long> {
}
