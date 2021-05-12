package com.future.restoapp.model.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.future.restoapp.model.entity.Menu;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuUpdateRequest {

    @NotBlank(message = "id must be specified")
    private String id;

    private String category;

    private String name;

    @Positive(message = "price must be positive value")
    private Float price;

    private String description;

    @PositiveOrZero(message = "stock can't be negative value")
    private Integer stock;

    private String image;

    private String fileExtension;

    public Menu inject(Menu target){
        target.setId(id);

        if(target.getName() != null) target.setName(name);
        if(target.getCategory() != null) target.setCategory(category);
        if(target.getPrice() != null) target.setPrice(price);
        if(target.getDescription() != null) target.setDescription(description);
        if(target.getStock() != null) target.setStock(stock);

        return target;
    }

}
