package com.future.restoapp.controller;

import com.future.restoapp.controller.path.MenuControllerPath;
import com.future.restoapp.model.dto.MenuCreateRequest;
import com.future.restoapp.model.dto.MenuUpdateRequest;
import com.future.restoapp.model.dto.SuccessResponse;
import com.future.restoapp.model.entity.Menu;
import com.future.restoapp.model.entity.Menu.MenuCategory;
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
            String filename = addImage(menuReq.getName(), menuReq.getFileExtension(), menuReq.getImage(), false);
            menu.setImageFilename(filename);
        } else {
            menu.setImageFilename("default-menu.png");
        }

        menuService.create(menu);

        return ResponseEntity.created(new URI("/api/v1/client/menus/1")).build();
    }

    @RequestMapping(value = MenuControllerPath.UPDATE, method = RequestMethod.PATCH)
    public ResponseEntity update(@Valid @RequestBody MenuUpdateRequest menuReq) throws Exception {
        Menu menu = new Menu();

        menuReq.inject(menu);

        Menu newMenu = menuService.updateById(menu.getId(), menu);
        SuccessResponse responseBody = new SuccessResponse(newMenu);

        return ResponseEntity.ok(responseBody);
    }

    // TODO: Must be delete for future version
    @RequestMapping(value = MenuControllerPath.DELETE, method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable Long id) throws Exception {
        Menu menu = menuService.deleteById(id);
        String path = menu.getImageFilename();

        if(path != null){
            String[] splittedPath = path.split("/");
            String filename = splittedPath[splittedPath.length - 1];

            assetService.deleteImage(filename);
        }
        
        return ResponseEntity.status(HttpStatus.OK).body(menu);
    }

    @RequestMapping(
            value = {
                    MenuControllerPath.FETCH_ONE
            },
            method = RequestMethod.GET
    )
    public ResponseEntity fetchOne(@PathVariable Long id) throws Exception {
        Menu menu = menuService.findOneById(id);

        if(menu == null) throw new NoSuchElementException("Menu with specified ID not found");

        SuccessResponse responseBody = new SuccessResponse(menu);

        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
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

    private String addImage(String rawMenuName, String fileExtension,
                            String base64Content, boolean deleteExisting) throws Exception {
        String filename = rawMenuName.replace(" ", "_") + "." + fileExtension;

        if(deleteExisting){
            assetService.deleteImage(filename);
        }

        assetService.addImage(filename, base64Content);

        return filename;
    }

}
