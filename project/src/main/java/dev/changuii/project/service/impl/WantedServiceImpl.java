package dev.changuii.project.service.impl;

import dev.changuii.project.dto.WantedDTO;
import dev.changuii.project.dto.response.WantedResponseDTO;
import dev.changuii.project.entity.UserEntity;
import dev.changuii.project.entity.WantedEntity;
import dev.changuii.project.enums.ErrorCode;
import dev.changuii.project.enums.WantedStatus;
import dev.changuii.project.exception.CustomException;
import dev.changuii.project.repository.UserRepository;
import dev.changuii.project.repository.WantedRepository;
import dev.changuii.project.service.ImageService;
import dev.changuii.project.service.WantedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Service
public class WantedServiceImpl implements WantedService {


    private final WantedRepository wantedRepository;
    private final ImageService imageService;
    private final UserRepository userRepository;

    public WantedServiceImpl(
            @Autowired WantedRepository wantedRepository,
            @Autowired ImageService imageService,
            @Autowired UserRepository userRepository
    ){
        this.imageService = imageService;
        this.wantedRepository = wantedRepository;
        this.userRepository = userRepository;
    }

    @Override
    public WantedResponseDTO createWanted(WantedDTO wantedDTO, MultipartFile mainImage, MultipartFile signature) throws IOException {
        UserEntity user = this.userRepository.findByEmail(wantedDTO.getUserEmail())
                .orElseThrow(() -> new CustomException(ErrorCode.EMAIL_NOT_FOUND));

        if(wantedRepository.existsByUserAndStatus(user, WantedStatus.PROGRESS))
            throw new CustomException(ErrorCode.WANTED_ALREADY_PROGRESS);

        String mainImageUrl = this.imageService.ImageUpload(mainImage);
        String signautreUrl = this.imageService.ImageUpload(signature);

        WantedEntity wanted = wantedDTO
                .toEntity(user, mainImageUrl,  signautreUrl, WantedStatus.PROGRESS);

        return this.wantedRepository.save(wanted)
                .toResponseDTO();
    }

    @Override
    public WantedResponseDTO createWanted(WantedDTO wantedDTO, MultipartFile signature) throws IOException {
        UserEntity user = this.userRepository.findByEmail(wantedDTO.getUserEmail())
                .orElseThrow(() -> new CustomException(ErrorCode.EMAIL_NOT_FOUND));

        if(wantedRepository.existsByUserAndStatus(user, WantedStatus.PROGRESS))
            throw new CustomException(ErrorCode.WANTED_ALREADY_PROGRESS);

        String mainImageUrl = this.imageService.getBasicImage();
        String signatureUrl = this.imageService.ImageUpload(signature);

        WantedEntity wanted = wantedDTO
                .toEntity(user, mainImageUrl, signatureUrl, WantedStatus.PROGRESS);

        return this.wantedRepository.save(wanted)
                .toResponseDTO();
    }

    @Override
    public WantedResponseDTO readWantedById(Long id) {
        return wantedRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.WANTED_NOT_FOUND))
                .toResponseDTO();
    }

    @Override
    public Boolean isProgressByEmail(String email) {
        UserEntity user = this.userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(ErrorCode.EMAIL_NOT_FOUND));

        return this.wantedRepository
                .existsByUserAndStatus(user, WantedStatus.PROGRESS);
    }

    @Override
    public List<WantedResponseDTO> readAllWantedByEmail(String email) {
        UserEntity user = this.userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(ErrorCode.EMAIL_NOT_FOUND));

        List<WantedEntity> wantedEntityList = this.wantedRepository.readAllByUser(user);

        return WantedEntity.toResponseDTOList(wantedEntityList);
    }

    @Override
    @Transactional
    public WantedResponseDTO modifyWantedStatus(Long id, String status) {
        return wantedRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.WANTED_NOT_FOUND))
                .modifyStatus(status)
                .toResponseDTO();
    }


}
