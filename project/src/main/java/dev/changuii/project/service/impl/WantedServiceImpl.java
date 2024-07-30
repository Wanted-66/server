package dev.changuii.project.service.impl;

import dev.changuii.project.dto.WantedDTO;
import dev.changuii.project.repository.WantedRepository;
import dev.changuii.project.service.WantedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class WantedServiceImpl implements WantedService {


    private final WantedRepository wantedRepository;

    public WantedServiceImpl(
            @Autowired WantedRepository wantedRepository
    ){
        this.wantedRepository = wantedRepository;
    }

    @Override
    public WantedDTO createWanted(WantedDTO wantedDTO, MultipartFile image) {
        return null;
    }

    @Override
    public WantedDTO createWanted(WantedDTO wantedDTO) {
        return null;
    }

    @Override
    public WantedDTO readWantedById(Long id) {
        return null;
    }
}
