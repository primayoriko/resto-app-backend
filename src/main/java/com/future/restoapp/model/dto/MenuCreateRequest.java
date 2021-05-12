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
import javax.validation.constraints.PositiveOrZero;
import java.util.Optional;

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

    @NotNull(message = "price must be specified")
    @Positive(message = "price value must be positive")
    private Float price;

    @NotBlank(message = "description must be specified")
    private String description;

    @NotNull(message = "stock must be specified")
    @PositiveOrZero(message = "stock value must equals or more than zero")
    private Integer stock;

    private String image;

    private String fileExtension;

    public Menu toMenu(){
//        menu.setName(name);
//        menu.setCategory(category);
//        menu.setPrice(price);
//        menu.setDescription(description);
//        menu.setStock(stock);
		return Optional.of(this).map(e -> {
            Menu menu = Menu.builder().build();
			BeanUtils.copyProperties(e, menu);

			return menu;
		}).orElse(null);
    }

}
