package com.future.restoapp.model.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.future.restoapp.model.entity.Menu;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuUpdateRequest {

    @NotBlank(message = "id must be specified")
    @Positive(message = "price must be positive value")
    private Long id;

    @Positive(message = "price must be positive value")
    private Float price;

    private Boolean isSold;

    public Menu inject(Menu target){
        target.setId(id);

        if(price != null) target.setPrice(price);
        if(isSold != null) target.setIsSold(isSold);

        return target;
    }

}
