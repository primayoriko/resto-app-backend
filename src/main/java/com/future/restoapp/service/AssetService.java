package com.future.restoapp.service;

import javax.validation.constraints.NotBlank;

public interface AssetService {

    String CONTEXT_MENU = "/menus";

    String BASE_DIRECTORY = "/data/assets";

    String BASE_IMAGE_DIRECTORY = BASE_DIRECTORY + "/images";

    String IMAGE_MENU_DIRECTORY = BASE_IMAGE_DIRECTORY + CONTEXT_MENU;

    String saveImage(@NotBlank String filename, @NotBlank String mimeType, @NotBlank String base64Content);

}
