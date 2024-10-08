package dev.changuii.project.repository;


import dev.changuii.project.entity.CommentEntity;
import dev.changuii.project.entity.WantedEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    public List<CommentEntity> readAllByWanted(WantedEntity wanted);
}
