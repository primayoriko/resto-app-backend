package com.future.restoapp.service;

import javax.validation.constraints.NotBlank;

public interface AssetService {

    String CONTEXT_MENU = "/menus";

    String BASE_DIRECTORY = "/data/assets";

    String BASE_IMAGE_DIRECTORY = BASE_DIRECTORY + "/images";

    String IMAGE_MENU_DIRECTORY = BASE_IMAGE_DIRECTORY + CONTEXT_MENU;

    byte[] getImageBytes(@NotBlank String filename) throws Exception;

    String getImageBase64(@NotBlank String filename) throws Exception;

    String addImage(@NotBlank String filename, @NotBlank String base64Content) throws Exception;

    boolean deleteImage(@NotBlank String filename) throws Exception;

}
