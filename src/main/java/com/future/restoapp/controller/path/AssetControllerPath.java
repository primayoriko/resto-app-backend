package com.future.restoapp.controller.path;

import com.future.restoapp.constant.UrlBasePath;

public interface AssetControllerPath {

    /* Contexts List */
    String CONTEXT_MENU = "/menus";

    /* URL Path List */
    String BASE = UrlBasePath.CURRENT_PUBLIC;

    String BASE_IMAGE = BASE + "/images";

    String IMAGE_MENU = BASE_IMAGE + CONTEXT_MENU + "/{filename}";
    
}
