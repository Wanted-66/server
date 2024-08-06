package dev.changuii.project.service.impl;

import dev.changuii.project.dto.response.ResponseDTO;
import dev.changuii.project.entity.UserEntity;
import dev.changuii.project.enums.ErrorCode;
import dev.changuii.project.enums.UserDesignation;
import dev.changuii.project.exception.CustomException;
import dev.changuii.project.repository.UserRepository;
import dev.changuii.project.service.ImageService;
import dev.changuii.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ImageService imageService;

    public UserServiceImpl(
            @Autowired ImageService imageService,
            @Autowired UserRepository userRepository
    ){
        this.userRepository=userRepository;
        this.imageService=imageService;
    }

    @Override
    @Transactional
    public ResponseDTO<String> changeUserProfileImage(String email, MultipartFile image) throws IOException {
        UserEntity user = this.userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(ErrorCode.EMAIL_NOT_FOUND));

        if(user.getProfileImage().contains("/basic.png"))
            throw new CustomException(ErrorCode.NON_DELETE_IMAGE_EXCEPTION);
        this.imageService.deleteImage(this.imageService.extractionKey(user.getProfileImage()));

        String url = this.imageService.ImageUpload(image);

        return ResponseDTO.<String>builder()
                .status(HttpStatus.OK.toString())
                .message("change image success")
                .data(user.setUserImage(url).getProfileImage())
                .build();
    }

    @Override
    public ResponseDTO<List<UserDesignation>> readUserDesignation(String email) {
        UserEntity user = this.userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(ErrorCode.EMAIL_NOT_FOUND));

        return ResponseDTO.<List<UserDesignation>>builder()
                .status(HttpStatus.OK.toString())
                .message("designation")
                .data(user.getUserDesignationList())
                .build();
    }

    @Override
    @Transactional
    public ResponseDTO<UserDesignation> changeDesignation(String email, UserDesignation designation) {
        UserEntity user = this.userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(ErrorCode.EMAIL_NOT_FOUND));

        if(!this.userRepository.existsByUserDesignationListContainsAndEmail(designation, email))
            throw new CustomException(ErrorCode.DESIGNATION_NOT_OWN);

        return ResponseDTO.<UserDesignation>builder()
                .status(HttpStatus.OK.toString())
                .message("designation change success")
                .data(user.wearDesignation(designation))
                .build();
    }

    @Override
    @Transactional
    public ResponseDTO<UserDesignation> addDesignation(String email, UserDesignation designation) {
        UserEntity user = this.userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(ErrorCode.EMAIL_NOT_FOUND));

        if(this.userRepository.existsByUserDesignationListContainsAndEmail(designation, email))
            throw new CustomException(ErrorCode.DESIGNATION_DUPLICATION);


        return ResponseDTO.<UserDesignation>builder()
                .status(HttpStatus.OK.toString())
                .message("designation change success")
                .data(user.addDesignation(designation))
                .build();
    }
}
