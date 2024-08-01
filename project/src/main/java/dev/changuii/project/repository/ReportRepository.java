package dev.changuii.project.repository;

import dev.changuii.project.entity.ReportEntity;
import dev.changuii.project.entity.WantedEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<ReportEntity, Long> {

    public List<ReportEntity> readAllByWanted(WantedEntity wanted);
}
