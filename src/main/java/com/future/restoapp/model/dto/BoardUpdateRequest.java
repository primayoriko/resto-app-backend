package com.future.restoapp.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;
import java.io.Serializable;


@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardUpdateRequest implements Serializable {

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

}
