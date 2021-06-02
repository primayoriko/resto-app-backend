package com.future.restoapp.model.dto.menu;


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
import java.io.Serializable;
import java.util.Optional;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuCreateRequest implements Serializable {

    @NotBlank(message = "name must be specified")
    private String name;

    @NotNull(message = "category must be specified")
    private MenuCategory category;

    @NotNull(message = "price must be specified")
    @Positive(message = "price value must be positive")
    private Float price;

    @NotBlank(message = "description must be specified")
    private String description;

    private String image;

    private String fileExtension;

    public Menu toMenu(){
		return Optional.of(this).map(dto -> {
            Menu menu = new Menu();
			BeanUtils.copyProperties(dto, menu);
			return menu;
		}).orElse(null);
    }

}
