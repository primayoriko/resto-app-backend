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
public class BoardCreateRequest implements Serializable {

    @NotNull(message = "x must be specified")
    private Integer x;

    @NotNull(message = "y must be specified")
    private Integer y;

    @NotNull(message = "length must be specified")
    @Positive(message = "length value must be positive")
    private Integer length;

    @NotNull(message = "width must be specified")
    @Positive(message = "width value must be positive")
    private Integer width;

    @NotNull(message = "capacity must be specified")
    @Positive(message = "capacity value must be positive")
    private Integer capacity;

    public Board toBoard(){
        return Optional.of(this).map(dto -> {
            Board board = new Board();
            BeanUtils.copyProperties(dto, board);
            return board;
        }).orElse(null);
    }

}
