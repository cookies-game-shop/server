package net.javaForum.javaForum.controller;


import lombok.Data;

import lombok.extern.slf4j.Slf4j;

import net.javaForum.javaForum.model.Game;
import net.javaForum.javaForum.model.User;
import net.javaForum.javaForum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@RestController

@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    UserService userService;


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
    public ResponseEntity<?> update(String username, @RequestBody User user){
   //     String username = getUsernameByToken.getUsernameByToken(request, response);
        User requestUser = userService.update(username, user);
        if (requestUser != null) {
            return new ResponseEntity(user, HttpStatus.OK);
        }
        return ResponseEntity.badRequest().body("User does not exists");
    }

    @PostMapping("/add-to-card")
    public ResponseEntity<?> addToCard(@RequestParam Long game_id, String username) {
        boolean saving = userService.addToCard(username,game_id);
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
    public ResponseEntity<?> getProfile(String username) throws IOException {
      //  String username = getUsernameByToken.getUsernameByToken(request, response);
        User user = userService.getUser(username);
        if (user != null) {
            return new ResponseEntity(user, HttpStatus.OK);
        }
        return ResponseEntity.badRequest().body("User does not exists");
    }

    //DELETE
    @DeleteMapping("/delete-user")
    public boolean delete(String username) throws IOException {
       // String username = getUsernameByToken.getUsernameByToken(request, response);
        return userService.delete(username);
    }


    @GetMapping("/get-card")
    public ResponseEntity<Game> getListGameCard(String username) throws Exception {
       // String username = getUsernameByToken.getUsernameByToken(request, response);

        return new ResponseEntity(userService.getGamesCardByUsername(username), HttpStatus.OK);
    }

    //DELETE FROM CARD
    @DeleteMapping("/delete-card")
    public boolean deleteGameFromCard(Long game_id,String username) {
        // String username = getUsernameByToken.getUsernameByToken(request, response);
        return userService.deleteGameFromCard(game_id,username);
    }

}

