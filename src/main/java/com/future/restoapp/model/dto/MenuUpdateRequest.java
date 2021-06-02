package com.future.restoapp.model.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.future.restoapp.model.entity.Menu;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Optional;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuUpdateRequest {

    @NotNull(message = "id must be specified")
    @Positive(message = "id must be positive value")
    private Long id;

    @Positive(message = "price must be positive value")
    private Float price;

    private Boolean isSold;

    public Menu toMenu(){
        return Optional.of(this).map(dto -> {
            Menu menu = Menu.builder().build();
            BeanUtils.copyProperties(dto, menu);
            return menu;
        }).orElse(null);
    }

}
