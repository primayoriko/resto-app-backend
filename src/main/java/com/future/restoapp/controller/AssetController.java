package com.future.restoapp.controller;

import com.future.restoapp.controller.path.AssetControllerPath;
import com.future.restoapp.service.AssetService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Tag(name = "Asset")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AssetController extends BaseController {

    @Autowired
    private AssetService assetService;

    @RequestMapping(value = AssetControllerPath.IMAGE_MENU_URL, method = RequestMethod.GET,
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> getImageMenu(@PathVariable String filename) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        String path = AssetControllerPath.IMAGE_MENU_DIRECTORY + "/" + filename;
        byte[] media = assetService.getImage(path);

        // Debug
        System.out.println(path);
        System.out.println(filename);
        System.out.println(Arrays.toString(media));

        headers.setCacheControl(CacheControl.noCache().getHeaderValue());
        headers.setContentType(MediaType.valueOf(MediaType.APPLICATION_OCTET_STREAM_VALUE));

        return new ResponseEntity<>(media, headers, HttpStatus.OK);
    }

}
