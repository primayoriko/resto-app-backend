package com.future.restoapp.controller;

import com.future.restoapp.controller.path.AssetControllerPath;
import com.future.restoapp.service.AssetService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Asset")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AssetController extends BaseController {

    @Autowired
    private AssetService assetService;

    @RequestMapping(value = AssetControllerPath.IMAGE_MENU_URL, method = RequestMethod.GET)
    public void getImageMenu(@PathVariable String filename) throws Exception {

    }


}
