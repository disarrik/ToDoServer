package com.example.restserver.repository;

import com.example.restserver.entity.GroupEntity;
import com.example.restserver.entity.UserEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface GroupRepository extends CrudRepository<GroupEntity, Long> {
    GroupEntity findByAdminAndName(UserEntity admin, String name);
    @Transactional
    @Modifying
    @Query("DELETE FROM GroupEntity t WHERE t.id = :pid")
    void deleteQuery(@Param("pid")Long id);

}
