package com.future.restoapp.controller;

import com.future.restoapp.controller.path.AssetControllerPath;
import com.future.restoapp.service.AssetService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

@Tag(name = "Asset")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AssetController extends BaseController {

    @Autowired
    private AssetService assetService;

    @RequestMapping(value = AssetControllerPath.IMAGE_MENU, method = RequestMethod.GET,
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<String> getImageMenu(@PathVariable String filename) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        byte[] media = assetService.getImageBytes(filename);
        String base64String = Base64.getEncoder().encodeToString(media);
        // Debug
//        System.out.println(Arrays.toString(media));
        headers.setCacheControl(CacheControl.noCache().getHeaderValue());
        headers.setContentType(MediaType.valueOf(MediaType.APPLICATION_OCTET_STREAM_VALUE));

        return new ResponseEntity<>(base64String, headers, HttpStatus.OK);
    }

}
