package dev.changuii.project.service;

import dev.changuii.project.dto.ReportDTO;
import dev.changuii.project.dto.response.ReportResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ReportService {



    public ReportResponseDTO createReport(ReportDTO reportDTO,Long wantedId, MultipartFile image) throws IOException;
    public List<ReportResponseDTO> readAllReportByWantedId(Long wantedId);
    public ReportResponseDTO readReportById(Long id);
    public void deleteReportById(Long id);



}
