package net.javaForum.javaForum.controller;

import lombok.extern.slf4j.Slf4j;
import net.javaForum.javaForum.model.Game;
import net.javaForum.javaForum.model.User;
import net.javaForum.javaForum.repository.GameRepo;
import net.javaForum.javaForum.repository.UserRepo;
import net.javaForum.javaForum.service.GameService;
import net.javaForum.javaForum.service.UserService;
import net.javaForum.javaForum.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/game")
public class GameController extends Util {

    @Autowired
    GameService gameService;
    @Autowired
    UserService userService;
    @Autowired
    GameRepo gameRepo;
    @Autowired
    UserRepo userRepo;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/save-game")
    public ResponseEntity<?> saveGame(
            // @RequestParam MultipartFile file,
            @RequestParam String name,
            @RequestParam String par,
            @RequestParam Double price,
            HttpServletResponse response,
            HttpServletRequest request) throws IOException {
        String adminCreds = getUsernameByToken(request, response);
        User admin = userRepo.getByUsername(adminCreds);
        if (admin.getUsername().equals("admin")) {
            Game game = new Game(null, name, par, price, null, null);
            //  game.setPreviewImage(file.getBytes());
            gameRepo.save(game);
            return ResponseEntity.status(HttpStatus.OK).body("SAVED");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You are not allowed!");
    }

    @GetMapping("/get-game")
    public ResponseEntity<?> getGame(@RequestParam Long id) {
        Game gameReq = gameService.getGame(id);
        if (gameReq != null) {
            return new ResponseEntity<>(gameReq, HttpStatus.OK);
        }
        return ResponseEntity.badRequest().body("Bad request");
    }

    @DeleteMapping("/delete-game")
    public boolean deleteQue(@RequestParam Long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String adminCreds = getUsernameByToken(request, response);
        User admin = userRepo.getByUsername(adminCreds);
        if (admin != null) {
            return gameService.deleteGameFromDB(id);
        }
        return false;
    }

    @GetMapping("/get-list-game")
    public ResponseEntity<?> getListQue() {
        return new ResponseEntity<>(gameService.getListGame(), HttpStatus.OK);
    }
}
