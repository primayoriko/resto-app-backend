package com.future.restoapp.dto.menu;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.future.restoapp.domain.Menu;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Optional;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuResponse implements Serializable {

    private String name;

    private Menu.MenuCategory category;

    private String description;

    private String imageFilename;

    private Float price;

    private Boolean isSold;

    public static MenuResponse build(Menu menu){
        return Optional.ofNullable(menu).map(entity -> {
            MenuResponse menuResponse = new MenuResponse();
            BeanUtils.copyProperties(entity, menuResponse);
            return menuResponse;
        }).orElse(null);
    }

}
