package com.future.restoapp.controller;

import com.future.restoapp.controller.path.MenuControllerPath;
import com.future.restoapp.model.dto.MenuCreateRequest;
import com.future.restoapp.model.dto.MenuUpdateRequest;
import com.future.restoapp.model.entity.Menu;
import com.future.restoapp.service.AssetService;
import com.future.restoapp.service.MenuService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
        }

        menuService.create(menu);

        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @RequestMapping(value = MenuControllerPath.UPDATE, method = RequestMethod.PATCH)
    public ResponseEntity update(@Valid @RequestBody MenuUpdateRequest menuReq) throws Exception {
        Menu menu = Menu.builder().build();

        BeanUtils.copyProperties(menuReq, menu);

        if(menuReq.getImage() != null){
            String filename = addImage(menuReq.getName(), menuReq.getFileExtension(), menuReq.getImage(), true);
            menu.setImageFilename(filename);
        }

        menuService.updateById(menu.getId(), menu);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @RequestMapping(value = MenuControllerPath.DELETE, method = RequestMethod.DELETE)
    public ResponseEntity delete(@Valid @PathVariable String id) throws Exception {
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
                    MenuControllerPath.FETCH_ONE_ADMIN,
                    MenuControllerPath.FETCH_ONE_CLIENT
            },
            method = RequestMethod.GET
    )
    public ResponseEntity fetchOne(@Valid @PathVariable String id) throws Exception {
        Menu menu = menuService.findOneById(id);

        if(menu == null) throw new NoSuchElementException("Menu with specified ID not found");

        return ResponseEntity.status(HttpStatus.OK).body(menu);
    }

    @RequestMapping(
            value = {
                    MenuControllerPath.FETCH_ADMIN,
                    MenuControllerPath.FETCH_CLIENT
            },
            method = RequestMethod.GET
    )
    public ResponseEntity fetch(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "20") Integer pageSize,
            @RequestParam(defaultValue = "#$#") String name,
            @RequestParam(defaultValue = "#$#") String category
    ) throws Exception {
        if(name.equals("#$#")) name = null;
        if(category.equals("#$#")) category = null;

        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Menu> result = menuService.findAllByNameAndCategory(name, category, pageable);

        if(result == null) throw new NoSuchElementException("Menus with specified criterion not found");

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
