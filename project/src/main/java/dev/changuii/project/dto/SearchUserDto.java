package dev.changuii.project.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.domain.Pageable;

@Getter
@ToString
@NoArgsConstructor @AllArgsConstructor
public class SearchUserDto {

    String nickname;
    Pageable pageable;
}
