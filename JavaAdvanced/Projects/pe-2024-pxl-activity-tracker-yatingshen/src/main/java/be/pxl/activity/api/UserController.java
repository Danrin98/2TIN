package be.pxl.activity.api;

import be.pxl.activity.api.request.UserDetailRequest;
import be.pxl.activity.api.request.UserRequest;
import be.pxl.activity.domain.User.User;
import be.pxl.activity.repository.UserDetailRepository;
import be.pxl.activity.repository.UserRepository;
import be.pxl.activity.service.UserDetailService;
import be.pxl.activity.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserDetailService userDetailService;


    public UserController(UserService userService, UserDetailService userDetailService) {
        this.userService = userService;
        this.userDetailService = userDetailService;
    }

    @PostMapping
    public ResponseEntity<Long> createUser(@RequestBody UserRequest userRequest) {
        return new ResponseEntity<>(userService.createUser(userRequest), HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<?> updateUser (@Valid @RequestBody UserDetailRequest userDetailRequest, Principal principal) {
        try{
            return new ResponseEntity<>(userService.updateUserDetails(userDetailRequest, principal.getName()), HttpStatus.OK);
        }
        catch (Exception ex){
            throw new IllegalArgumentException("Ongeldige of ontbrekende gegevens.");
        }

    }
}
