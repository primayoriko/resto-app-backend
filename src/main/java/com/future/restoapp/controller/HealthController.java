package com.future.restoapp.controller;

import com.future.restoapp.controller.path.HealthControllerPath;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "HealthCheck")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HealthController extends BaseController {

    @RequestMapping(value = HealthControllerPath.BASE, method = RequestMethod.GET)
    public ResponseEntity check() {
        return ResponseEntity.ok("HEALTHY");
    }

}