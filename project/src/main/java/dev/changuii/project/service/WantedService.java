package dev.changuii.project.service;

import dev.changuii.project.dto.WantedDTO;
import org.springframework.web.multipart.MultipartFile;

public interface WantedService {

    public WantedDTO createWanted(WantedDTO wantedDTO, MultipartFile image);
    public WantedDTO createWanted(WantedDTO wantedDTO);

    public WantedDTO readWantedById(Long id);
}
