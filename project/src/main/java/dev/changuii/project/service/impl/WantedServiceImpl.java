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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


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
    public WantedResponseDTO createWanted(WantedDTO wantedDTO, MultipartFile image) throws IOException {
        UserEntity user = this.userRepository.findByEmail(wantedDTO.getUserEmail())
                .orElseThrow(() -> new CustomException(ErrorCode.EMAIL_NOT_FOUND));

        if(wantedRepository.existsByUserAndStatus(user, WantedStatus.PROGRESS))
            throw new CustomException(ErrorCode.WANTED_ALREADY_PROGRESS);

        String imageUrl = this.imageService.ImageUpload(image);

        WantedEntity wanted = wantedDTO
                .toEntity(user, imageUrl, WantedStatus.PROGRESS);

        return this.wantedRepository.save(wanted)
                .toResponseDTO();
    }

    @Override
    public WantedResponseDTO createWanted(WantedDTO wantedDTO) {
        UserEntity user = this.userRepository.findByEmail(wantedDTO.getUserEmail())
                .orElseThrow(() -> new CustomException(ErrorCode.EMAIL_NOT_FOUND));

        if(wantedRepository.existsByUserAndStatus(user, WantedStatus.PROGRESS))
            throw new CustomException(ErrorCode.WANTED_ALREADY_PROGRESS);

        // todo 우리 서비스 url로 변경해야함 (도메인나오면, basic은 기본 이미지이름)
        String imageUrl = "basic.png";

        WantedEntity wanted = wantedDTO
                .toEntity(user, imageUrl, WantedStatus.PROGRESS);

        return this.wantedRepository.save(wanted)
                .toResponseDTO();
    }

    @Override
    public WantedResponseDTO readWantedById(Long id) {
        return wantedRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.WANTED_NOT_FOUND))
                .toResponseDTO();
    }
}
