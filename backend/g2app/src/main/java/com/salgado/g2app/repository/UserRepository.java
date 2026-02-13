package com.salgado.g2app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salgado.g2app.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUserEmail(String userEmail);
}
 