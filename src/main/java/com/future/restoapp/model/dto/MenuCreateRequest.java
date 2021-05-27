package com.future.restoapp.model.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.future.restoapp.model.entity.Menu;
import com.future.restoapp.model.entity.Menu.MenuCategory;
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

    private String image;

    private String fileExtension;

    public Menu toMenu(){
		return Optional.of(this).map(e -> {
            Menu menu = new Menu();
			BeanUtils.copyProperties(e, menu, "category");
			menu.setCategory(MenuCategory.of(category));
			return menu;
		}).orElse(null);
    }

}
