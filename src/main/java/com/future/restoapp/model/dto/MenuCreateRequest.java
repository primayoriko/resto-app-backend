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
public class MenuCreateRequest {

    @NotBlank(message = "category must be specified")
    private String category;

    @NotBlank(message = "name must be specified")
    private String name;

    @NotBlank(message = "price must be specified")
    @Positive(message = "price must be positive value")
    private Float price;

    @NotBlank(message = "description must be specified")
    private String description;

    @NotBlank(message = "stock must be specified")
    @PositiveOrZero(message = "stock can't be negative value")
    private Integer stock;

    public Menu toMenu(){
        Menu menu = new Menu();

        menu.setName(name);
        menu.setCategory(category);
        menu.setPrice(price);
        menu.setDescription(description);
        menu.setStock(stock);

        return menu;
    }

}
