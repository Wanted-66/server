package dev.changuii.project.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor @AllArgsConstructor
public class ResponseDTO<T> {

    private String status;
    private String message;
    private T data;

}
