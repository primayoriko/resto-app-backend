package com.future.restoapp.controller.path;

import com.future.restoapp.constant.UrlBasePath;

public interface AssetControllerPath {

    /* Contexts List */
    String CONTEXT_MENU = "/menus";

    /* URL Path List */
    String BASE_URL = UrlBasePath.CURRENT_PUBLIC;

    String BASE_IMAGE_URL = BASE_URL + "/images";

    String IMAGE_MENU_URL = BASE_IMAGE_URL + CONTEXT_MENU + "/{filename}";

    /* System Directory Path List */
    String BASE_DIRECTORY = "/assets";

    String BASE_IMAGE_DIRECTORY = BASE_DIRECTORY + "/images";

    String IMAGE_MENU_DIRECTORY = BASE_IMAGE_DIRECTORY + CONTEXT_MENU;

}
