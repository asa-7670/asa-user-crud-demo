package com.asa.user.demo.repository;

import com.asa.user.demo.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    /**
     * findByEmail
     * @param email String
     * @return Optional<UserEntity>
     */
    Optional<UserEntity> findByEmail(String email);

}
