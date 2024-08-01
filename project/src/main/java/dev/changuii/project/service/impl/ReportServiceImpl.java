package dev.changuii.project.service.impl;

import dev.changuii.project.dto.ReportDTO;
import dev.changuii.project.dto.response.ReportResponseDTO;
import dev.changuii.project.entity.ReportEntity;
import dev.changuii.project.entity.UserEntity;
import dev.changuii.project.entity.WantedEntity;
import dev.changuii.project.enums.ErrorCode;
import dev.changuii.project.enums.ReportStatus;
import dev.changuii.project.exception.CustomException;
import dev.changuii.project.repository.ReportRepository;
import dev.changuii.project.repository.UserRepository;
import dev.changuii.project.repository.WantedRepository;
import dev.changuii.project.service.ImageService;
import dev.changuii.project.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Service
public class ReportServiceImpl implements ReportService {


    private final ReportRepository reportRepository;
    private final ImageService imageService;
    private final UserRepository userRepository;
    private final WantedRepository wantedRepository;

    public ReportServiceImpl(
            @Autowired WantedRepository wantedRepository,
            @Autowired UserRepository userRepository,
            @Autowired ImageService imageService,
            @Autowired ReportRepository reportRepository
    ){
        this.wantedRepository = wantedRepository;
        this.userRepository=userRepository;
        this.imageService=imageService;
        this.reportRepository=reportRepository;
    }


    @Override
    public ReportResponseDTO createReport(ReportDTO reportDTO,Long wantedId ,MultipartFile image) throws IOException {
        UserEntity user = this.userRepository.findByEmail(reportDTO.getUserEmail())
                .orElseThrow(() -> new CustomException(ErrorCode.EMAIL_NOT_FOUND));

        WantedEntity wanted = this.wantedRepository.findById(wantedId)
                .orElseThrow(() -> new CustomException(ErrorCode.WANTED_NOT_FOUND));

        String imageUrl = this.imageService.ImageUpload(image);

        return this.reportRepository
                .save(reportDTO.toEntity(imageUrl, wanted, user))
                .toResponseDTO();
    }

    @Override
    public List<ReportResponseDTO> readAllReportByWantedId(Long wantedId) {
        WantedEntity wanted = this.wantedRepository.findById(wantedId)
                .orElseThrow(() -> new CustomException(ErrorCode.WANTED_NOT_FOUND));

        return ReportEntity
                .toResponseDTOList(this.reportRepository.readAllByWanted(wanted));
    }

    @Override
    public ReportResponseDTO readReportById(Long id) {
        return this.reportRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.REPORT_NOT_FOUND))
                .toResponseDTO();
    }

    @Override
    public void deleteReportById(Long id) {
        try {
            this.reportRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new CustomException(ErrorCode.REPORT_NOT_FOUND);
        }
    }
}
