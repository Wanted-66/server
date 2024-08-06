package dev.changuii.project.service;

import dev.changuii.project.dto.response.ResponseDTO;
import dev.changuii.project.enums.UserDesignation;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserService {
    public ResponseDTO<String> changeUserProfileImage(String email, MultipartFile image) throws IOException;
    public ResponseDTO<List<UserDesignation>> readUserDesignation(String email);
    public ResponseDTO<UserDesignation> changeDesignation(String email, UserDesignation designation);
    public ResponseDTO<UserDesignation> addDesignation(String email, UserDesignation designation);

}
