package dev.changuii.project.advisor;


import dev.changuii.project.dto.ErrorDTO;
import dev.changuii.project.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ControllerAdvisor {


    // 유효성 검사 예외
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> validationHandler(MethodArgumentNotValidException e){
        List<FieldError> errors = e.getBindingResult().getFieldErrors();

        StringBuilder sb = new StringBuilder();
        for(FieldError error : errors ){
            sb
                    .append(error.getDefaultMessage())
                    .append("\n")
                    .append("입력된 값 : ")
                    .append(error.getRejectedValue())
                    .append("\n");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sb.toString());
    }

    // 예외
    @ExceptionHandler({
            CustomException.class
    })
    public ResponseEntity<ErrorDTO> exceptionHandler(CustomException e){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorDTO.builder().code(400).description(e.getErrorMessage()).build());
    }
}
