package com.ten.ten.repositories;


import com.ten.ten.entities.RefreshTokenEntity;
import com.ten.ten.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository  extends JpaRepository<RefreshTokenEntity, String> {

     RefreshTokenEntity findByToken(String token);

     @Modifying
     int deleteByUser(UserEntity user);
}
