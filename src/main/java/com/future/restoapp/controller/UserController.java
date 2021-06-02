package com.future.restoapp.controller;

import com.future.restoapp.controller.path.ReservationControllerPath;
import com.future.restoapp.controller.path.UserControllerPath;
import com.future.restoapp.model.dto.RegisterRequest;
import com.future.restoapp.model.dto.SuccessResponse;
import com.future.restoapp.model.dto.UserResponse;
import com.future.restoapp.model.entity.User;
import com.future.restoapp.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.security.Principal;
import java.util.Collection;
import java.util.NoSuchElementException;

@Tag(name = "User")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = UserControllerPath.REGISTER, method = RequestMethod.POST)
    public ResponseEntity registerClient(@Valid @RequestBody RegisterRequest request) throws Exception{
        User user = request.toUser();

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user = userService.create(user);
        String uri = String.format("%s/%d", UserControllerPath.BASE_ADMIN, user.getId());

        return ResponseEntity.created(new URI(uri)).build();
    }

    // Just for development purpose
    @RequestMapping(value = UserControllerPath.REGISTER_ADMIN, method = RequestMethod.POST)
    public ResponseEntity registerAdmin(@Valid @RequestBody RegisterRequest request) throws Exception {
        User user = request.toUser();

        user.setIsAdmin(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user = userService.create(user);
        String uri = String.format("%s/%d", UserControllerPath.BASE_ADMIN, user.getId());

        return ResponseEntity.created(new URI(uri)).build();
    }

    @RequestMapping(value = UserControllerPath.FETCH_ONE, method = RequestMethod.GET)
    public ResponseEntity fetchOne(@PathVariable String username) throws Exception {
        User user = this.userService.findByUsername(username);

        if(user == null) throw new NoSuchElementException("User with specified ID not found");

        SuccessResponse responseBody = new SuccessResponse(UserResponse.build(user));

        return ResponseEntity.ok(responseBody);
    }

    @RequestMapping(value = UserControllerPath.FETCH_ME, method = RequestMethod.GET)
    public ResponseEntity fetchMe(Principal principal) throws Exception {
        User user = getUser(principal);
        SuccessResponse responseBody = new SuccessResponse(UserResponse.build(user));
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    @RequestMapping(value = UserControllerPath.FETCH, method = RequestMethod.GET)
    public ResponseEntity fetchByQuery(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String hpNumber,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "20") Integer pageSize
    ) throws Exception {
        Pageable pageable = PageRequest.of(
                page - 1, pageSize,
                Sort.by("username").ascending()
            );
        Page<User> users = userService.findByQuery(username, email, hpNumber, pageable);
        Page<UserResponse> responseBody = users.map(UserResponse::build);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    @RequestMapping(value = UserControllerPath.DELETE, method = RequestMethod.DELETE)
    public ResponseEntity deleteSelf(Principal principal) throws Exception {
        User user = getUser(principal);
        SuccessResponse responseBody = new SuccessResponse(UserResponse.build(user));
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    @RequestMapping(value = UserControllerPath.UPDATE, method = RequestMethod.PATCH)
    public ResponseEntity update(@Valid @RequestBody RegisterRequest request) throws Exception {

        User user = request.toUser();

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        this.userService.create(user);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
