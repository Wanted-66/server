package dev.changuii.project.service;

import dev.changuii.project.dto.WantedDTO;
import dev.changuii.project.dto.response.WantedResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface WantedService {

    public WantedResponseDTO createWanted(WantedDTO wantedDTO, MultipartFile mainImage, MultipartFile signature ) throws IOException;
    public WantedResponseDTO createWanted(WantedDTO wantedDTO, MultipartFile signature) throws IOException;

    public WantedResponseDTO readWantedById(Long id);
    public List<WantedResponseDTO> readAllWantedByEmail(String email);

    public WantedResponseDTO modifyWantedStatus(Long id, String status);
}
