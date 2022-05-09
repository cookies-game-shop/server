package net.javaForum.javaForum.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import net.javaForum.javaForum.model.Game;
import net.javaForum.javaForum.model.Role;
import net.javaForum.javaForum.model.User;
import net.javaForum.javaForum.repository.UserRepo;
import net.javaForum.javaForum.service.UserService;
import net.javaForum.javaForum.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController

@RequestMapping("/user")
@Slf4j
public class UserController extends Util {

    @Autowired
    UserService userService;
    @Autowired
    UserRepo userRepo;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //CREATE
    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        boolean saving = userService.createUser(user);
        if (saving) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return ResponseEntity.badRequest().body("Bad request");
    }

    //UPDATE
    @PutMapping("/update")
    public ResponseEntity<?> update(HttpServletRequest request, HttpServletResponse response, @RequestBody User user) throws IOException {
        String username = getUsernameByToken(request, response);
        User requestUser = userService.update(username, user);
        if (requestUser != null) {
            return new ResponseEntity(user, HttpStatus.OK);
        }
        return ResponseEntity.badRequest().body("User does not exists");
    }

    @PostMapping("/add-to-card")
    public ResponseEntity<?> addToCard(@RequestParam Long game_id, String username) {
        boolean saving = userService.addToCard(username, game_id);
        if (saving) {
            return new ResponseEntity<>(saving, HttpStatus.OK);
        }
        return ResponseEntity.badRequest().body("Bad request");
    }

    //GET
    @GetMapping("/get-user/{username}")
    public ResponseEntity<?> read(@PathVariable String username) throws IOException {
        User user = userService.getUser(username);
        if (user != null) {
            return new ResponseEntity(user, HttpStatus.OK);
        }
        return ResponseEntity.badRequest().body("User does not exists");
    }


    @GetMapping("/get-profile")
    public ResponseEntity<?> getProfile(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = getUsernameByToken(request, response);
        User user = userService.getUser(username);
        if (user != null) {
            return new ResponseEntity(user, HttpStatus.OK);
        }
        return ResponseEntity.badRequest().body("User does not exists");
    }


    //DELETE
    @DeleteMapping("/delete-user")
    public boolean delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = getUsernameByToken(request, response);
        return userService.delete(username);
    }

    @GetMapping("/get-card")
    public ResponseEntity<Game> getListGameCard(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = getUsernameByToken(request, response);
        return new ResponseEntity(userService.getGamesCardByUsername(username), HttpStatus.OK);
    }

    //DELETE FROM CARD
    @DeleteMapping("/delete-card")
    public boolean deleteGameFromCard(Long game_id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = getUsernameByToken(request, response);
        return userService.deleteGameFromCard(game_id, username);
    }

    //SAVE ROLE
    @PostMapping("/role/save")
    public ResponseEntity<?> saveRole(@RequestBody Role role) {
        return new ResponseEntity(userService.saveRole(role), HttpStatus.OK);
    }

    //SAVE ROLE TO USER
    @PostMapping("/role/addtouser")
    public ResponseEntity<Role> addRoleToUser(@RequestBody RoleToUserForm form) {
        userService.addRoleToUser(form.getUsername(), form.getRoleName());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            response.setContentType(APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(), refreshToken(request, response, userService, authorizationHeader));
        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }

    @GetMapping("/get-admin-creds")
    public ResponseEntity<?> getAdminCreds(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = getUsernameByToken(request, response);
        User admin = userRepo.getByUsername(username);
        if (admin.getUsername().equals("admin")) {
            return ResponseEntity.status(HttpStatus.OK).body("ADMIN");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ORDINARY USER");
    }

}

@Data
class RoleToUserForm {
    private String username;
    private String roleName;
}

