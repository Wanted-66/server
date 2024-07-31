package dev.changuii.project.repository;

import dev.changuii.project.entity.UserEntity;
import dev.changuii.project.entity.WantedEntity;
import dev.changuii.project.enums.WantedStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WantedRepository extends JpaRepository<WantedEntity, Long> {

    public Boolean existsByUserAndStatus(UserEntity user, WantedStatus status);
    public List<WantedEntity> readAllByUser(UserEntity user);

}
