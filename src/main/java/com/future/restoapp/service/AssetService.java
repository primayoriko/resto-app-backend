package com.future.restoapp.service;

public interface AssetService {

    String CONTEXT_MENU = "/menus";

    String BASE_DIRECTORY = "/data/assets";

    String BASE_IMAGE_DIRECTORY = BASE_DIRECTORY + "/images";

    String IMAGE_MENU_DIRECTORY = BASE_IMAGE_DIRECTORY + CONTEXT_MENU;

    byte[] getImageBytes(String filename) throws Exception;

    String getImageBase64(String filename) throws Exception;

    String addImage(String filename, String base64Content) throws Exception;

    boolean deleteImage(String filename) throws Exception;

}
