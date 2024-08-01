package dev.changuii.project.service.impl;

import dev.changuii.project.enums.ErrorCode;
import dev.changuii.project.exception.CustomException;
import dev.changuii.project.service.IdempotentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


@Service
public class IdempotentServiceImpl implements IdempotentService {


    private final String VALUE = "IDEMPOTENT";
    private final StringRedisTemplate redisTemplate;
    private final Integer TIME_LIMIT = 5;



    public IdempotentServiceImpl(
            @Autowired StringRedisTemplate redisTemplate
    ){
        this.redisTemplate=redisTemplate;
    }

    @Override
    public void isValidIdempotent(List<String> keyElement) {

        String idempotentKey = this.compactKey(keyElement);

        Boolean isSUCCESS = this.redisTemplate
                .opsForValue()
                .setIfAbsent(idempotentKey, VALUE, TIME_LIMIT, TimeUnit.SECONDS);

        if(!isSUCCESS)
            throw new CustomException(ErrorCode.DUPLICATION_REQUEST);
    }


    private String compactKey(List<String> keyElement){
        return keyElement.stream()
                .collect(Collectors.joining());
    }

}
