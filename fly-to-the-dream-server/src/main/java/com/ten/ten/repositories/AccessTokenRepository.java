package com.ten.ten.repositories;


import com.ten.ten.entities.AccessToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccessTokenRepository extends JpaRepository<AccessToken, String> {
    Optional<AccessToken> findByToken(String token);

    Boolean existsByToken(String token);

    void deleteByUserId(String id);
    
    @Modifying
    @Query(value = "DELETE FROM access_token a WHERE a.token=:token",nativeQuery = true)
    void deleteToken(@Param("token") String token);

    @Modifying
    @Query(value = "UPDATE access_token SET token= :token WHERE id = :id", nativeQuery = true)
    void updateAccessToken(@Param("id") String id, @Param("token") String token);

}
