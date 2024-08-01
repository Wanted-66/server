package dev.changuii.project.service;

import java.util.List;

public interface IdempotentService {



    public void isValidIdempotent(List<String> keyElement);
}
