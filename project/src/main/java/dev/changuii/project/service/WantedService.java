package dev.changuii.project.service;

import dev.changuii.project.dto.WantedDTO;
import dev.changuii.project.dto.response.WantedResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface WantedService {

    public WantedResponseDTO createWanted(WantedDTO wantedDTO, MultipartFile image) throws IOException;
    public WantedResponseDTO createWanted(WantedDTO wantedDTO);

    public WantedResponseDTO readWantedById(Long id);
}
