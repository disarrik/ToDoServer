package com.example.restserver.repository;

import com.example.restserver.entity.GroupEntity;
import com.example.restserver.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface GroupRepository extends CrudRepository<GroupEntity, Long> {
    GroupEntity findByAdminAndName(UserEntity admin, String name);
    void deleteByNameAndAdmin(String name, UserEntity admin);
}
