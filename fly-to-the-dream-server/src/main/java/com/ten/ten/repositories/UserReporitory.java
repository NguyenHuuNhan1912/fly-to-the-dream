package com.ten.ten.repositories;

import com.ten.ten.dtos.SearchUserResponse;
import com.ten.ten.entities.ProductEntity;
import com.ten.ten.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserReporitory extends JpaRepository<UserEntity, Integer> {

    @Query(value = "SELECT * FROM users", nativeQuery = true)
    List<UserEntity> getAllUsers();

    UserEntity findByUsername(String username);

    UserEntity findById(String userId);

    @Query(value = "SELECT * FROM users WHERE id = :id", nativeQuery = true)
    UserEntity searchUser(@Param("id") String id);
}
