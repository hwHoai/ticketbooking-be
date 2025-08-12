package com.example.mvc_spring_postgres.repository;

import com.example.mvc_spring_postgres.entity.user.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    UserEntity findByUserName(String userName);
    UserEntity save(UserEntity userEntity);
}
