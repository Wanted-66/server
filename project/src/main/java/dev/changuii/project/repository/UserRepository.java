package dev.changuii.project.repository;

import dev.changuii.project.entity.UserEntity;
import dev.changuii.project.enums.UserDesignation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);
    Optional<Long> findUserIdByEmail(String email);
//    Optional<UserEntity> findById(Long id)
    public Boolean existsByUserDesignationListContainsAndEmail(UserDesignation designation, String email);
}
