package com.future.restoapp.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.future.restoapp.model.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Optional;


@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardResponse implements Serializable {

    private Long id;

    private Integer x;

    private Integer y;

    private Integer length;

    private Integer width;

    private Integer capacity;

    public static BoardResponse build(Board b){
        return Optional.ofNullable(b).map(entity -> {
            BoardResponse boardResponse = BoardResponse.builder().build();
            BeanUtils.copyProperties(entity, boardResponse, "reservations");
            return boardResponse;
        }).orElse(null);
    }

}
