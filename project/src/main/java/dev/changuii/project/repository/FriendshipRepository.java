package dev.changuii.project.repository;

import dev.changuii.project.entity.FriendshipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendshipRepository extends JpaRepository<FriendshipEntity, Long> , CustomSampleRepository{

}
