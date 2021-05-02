package com.example.restserver.repository;

import com.example.restserver.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findByFullname(String fullname);
    UserEntity findByEmailAndPassword(String email, String password);
    UserEntity findByEmail(String email);
}
