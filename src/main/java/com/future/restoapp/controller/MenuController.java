package com.future.restoapp.controller;

import com.future.restoapp.controller.path.MenuControllerPath;
import com.future.restoapp.dto.menu.MenuCreateRequest;
import com.future.restoapp.dto.menu.MenuUpdateRequest;
import com.future.restoapp.dto.core.SuccessResponse;
import com.future.restoapp.domain.Menu;
import com.future.restoapp.domain.Menu.MenuCategory;
import com.future.restoapp.service.AssetService;
import com.future.restoapp.service.MenuService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.NoSuchElementException;

@Tag(name = "Menu")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MenuController extends BaseController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private AssetService assetService;

    @RequestMapping(value = MenuControllerPath.CREATE, method = RequestMethod.POST)
    public ResponseEntity create(@Valid @RequestBody MenuCreateRequest menuReq) throws Exception {
        Menu menu = menuReq.toMenu();

        if(menuReq.getImage() != null){
            String url = addImage(menuReq.getName(), menuReq.getFileExtension(),
                    menuReq.getMimeType(), menuReq.getImage());
            menu.setImageUrl(url);
        } else {
            menu.setImageUrl("-");
        }

        menu = menuService.create(menu);
        String uri = String.format("%s/%d", MenuControllerPath.BASE_PUBLIC, menu.getId());

        return ResponseEntity.created(new URI(uri)).build();
    }

    @RequestMapping(
            value = {
                    MenuControllerPath.FETCH_ONE
            },
            method = RequestMethod.GET
    )
    public ResponseEntity fetchOne(@PathVariable Long id) throws Exception {
        Menu menu = menuService.findById(id);

        if(menu == null) throw new NoSuchElementException("Menu with specified ID not found");

        SuccessResponse responseBody = new SuccessResponse(menu);

        return ResponseEntity.ok(responseBody);
    }

    @RequestMapping(
            value = {
                    MenuControllerPath.FETCH,
            },
            method = RequestMethod.GET
    )
    public ResponseEntity fetchByQuery(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "20") Integer pageSize,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) MenuCategory category,
            @RequestParam(required = false) Boolean isSold
    ) throws Exception {
        Pageable pageable = PageRequest.of(
                page - 1, pageSize,
                Sort.by("isSold").descending()
                        .and(Sort.by("category").ascending())
                        .and(Sort.by("name").ascending())
        );
        Page<Menu> result = menuService.findByQuery(name, category, isSold, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @RequestMapping(value = MenuControllerPath.UPDATE, method = RequestMethod.PATCH)
    public ResponseEntity update(@Valid @RequestBody MenuUpdateRequest menuReq) throws Exception {
        Menu menu = menuService.update(menuReq.toMenu());
        return ResponseEntity.ok(new SuccessResponse(menu));
    }

    private String addImage(String rawMenuName, String fileExtension, String mimeType,
                            String base64Content) throws Exception {
        String filename = rawMenuName.replace(" ", "_") + "." + fileExtension;
        return assetService.saveImage(filename, mimeType, base64Content);
    }

}
