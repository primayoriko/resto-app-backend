package com.future.restoapp.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;


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

}
