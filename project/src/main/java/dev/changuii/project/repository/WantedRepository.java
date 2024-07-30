package dev.changuii.project.repository;

import dev.changuii.project.entity.WantedEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WantedRepository extends JpaRepository<WantedEntity, Long> {
}
