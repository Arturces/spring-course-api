package com.arturces.springcourseapi.resource;


import com.arturces.springcourseapi.domain.Request;
import com.arturces.springcourseapi.domain.User;
import com.arturces.springcourseapi.dto.UserLoginDto;
import com.arturces.springcourseapi.dto.UserSaveDto;
import com.arturces.springcourseapi.dto.UserUpdateDto;
import com.arturces.springcourseapi.dto.UserUpdateRoleDto;
import com.arturces.springcourseapi.model.PageModel;
import com.arturces.springcourseapi.model.PageRequestModel;
import com.arturces.springcourseapi.security.JwtManager;
import com.arturces.springcourseapi.service.RequestService;
import com.arturces.springcourseapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService userService;

    @Autowired
    private RequestService requestService;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtManager jwtManager;

    @PostMapping
    public ResponseEntity<User> save(@RequestBody @Valid UserSaveDto userDto) {
        User userToSave = userDto.transformToUser();

        User createdUser = userService.save(userToSave);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable(name = "id") Long id, @RequestBody @Valid UserUpdateDto userDto) {
        User userToUpdate = userDto.transformToUser();

        userToUpdate.setId(id);
        User updateUser = userService.update(userToUpdate);
        return ResponseEntity.ok(updateUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable("id") Long id) {
        User user = userService.getById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<PageModel<User>> listAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {

        PageRequestModel pr = new PageRequestModel(page,size);
        PageModel<User> pm = userService.listAllOnLazyMode(pr);

        return ResponseEntity.ok(pm);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid UserLoginDto user) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword());
        Authentication auth = authManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(auth);

        org.springframework.security.core.userdetails.User userSpring = (org.springframework.security.core.userdetails.User) auth.getPrincipal();

        String email = userSpring.getUsername();
        List<String> roles = userSpring.getAuthorities()
                .stream()
                .map(authority -> authority.getAuthority())
                .collect(Collectors.toList());

        String jwt = jwtManager.createToken(email,roles);

        return ResponseEntity.ok(jwt);
    }

    @GetMapping("/{id}/requests")
    public ResponseEntity<PageModel<Request>> listAllRequestById(
            @PathVariable(name = "id") Long id,
            @RequestParam(value = "size", defaultValue = "10")int size,
            @RequestParam(value = "page",defaultValue = "0") int page) {
       PageRequestModel pr = new PageRequestModel(page, size);


        PageModel<Request> pm = requestService.listAllByOwnerIdOnLazyModel(id,pr);
        return ResponseEntity.ok(pm);
    }

    @PatchMapping("/role/{id}")
    public ResponseEntity<?> updateRole(@PathVariable(name = "id") Long id, @RequestBody @Valid UserUpdateRoleDto userDto) {
        User user = new User();
        user.setId(id);
        user.setRole(userDto.getRole());

        userService.updateRole(user);

        return ResponseEntity.ok().build();
    }



}
