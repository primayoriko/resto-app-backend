package com.future.restoapp.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.future.restoapp.model.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.Optional;


@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardUpdateRequest implements Serializable {

    @NotNull(message = "id must be specified")
    @Positive(message = "id value must be positive")
    private Long id;

    private Integer x;

    private Integer y;

    @Positive(message = "length value must be positive")
    private Integer length;

    @Positive(message = "width value must be positive")
    private Integer width;

    @Positive(message = "capacity value must be positive")
    private Integer capacity;

    public Board toBoard(){
        return Optional.of(this).map(dto -> {
            Board board = Board.builder().build();
            BeanUtils.copyProperties(dto, board);
            return board;
        }).orElse(null);
    }

}
