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

//    @NotBlank(message = "category must be specified")
    private String category;

//    @NotBlank(message = "name must be specified")
    private String name;

//    @NotBlank(message = "price must be specified")
    @Positive(message = "price must be positive value")
    private Float price;

//    @NotBlank(message = "description must be specified")
    private String description;

//    @NotBlank(message = "stock must be specified")
    @PositiveOrZero(message = "stock can't be negative value")
    private Integer stock;

    public Menu injectToMenu(Menu target){
        target.setId(id);

        if(target.getName() != null) target.setName(name);
        if(target.getCategory() != null) target.setCategory(category);
        if(target.getPrice() != null) target.setPrice(price);
        if(target.getDescription() != null) target.setDescription(description);
        if(target.getStock() != null) target.setStock(stock);

        return target;
    }

}
