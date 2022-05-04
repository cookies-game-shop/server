package net.javaForum.javaForum.controller;

import lombok.extern.slf4j.Slf4j;
import net.javaForum.javaForum.model.Game;
import net.javaForum.javaForum.repository.GameRepo;
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


    public GameController(GameService gameService) {
        this.gameService = gameService;
    }
//
//    @PostMapping("/save-game")
//    public ResponseEntity<?> saveGame(HttpServletRequest request,
//                                      HttpServletResponse response,
//                                      @RequestBody Game game,
//                                      @RequestParam Long category_id) throws IOException {
//
//        String username = getUsernameByToken(request, response);
//        if (gameService.saveGameToDB(username, game, category_id)) {
//            return new ResponseEntity<>(gameService.getGame(game.getId()), HttpStatus.OK);
//        }
//        return ResponseEntity.badRequest().body("Bad request");
//    }
    @PostMapping("/save-game")
    public ResponseEntity<?> saveGame(@RequestParam MultipartFile file) throws IOException {

      //  String username = getUsernameByToken(request, response);
        Game game  = new Game(null,"Name","Para",8000,null,null);

        game.setPreviewImage(file.getBytes());
//        if (gameService.saveGameToDB(username, game, category_id)) {
//            return new ResponseEntity<>(gameService.getGame(game.getId()), HttpStatus.OK);
//        }
        return ResponseEntity.status(HttpStatus.OK).body( gameRepo.save(game));
    }


    @GetMapping("/get-game")
    public ResponseEntity<?> getGame(@RequestParam Long id) {
        Game gameReq = gameService.getGame(id);
        if (gameReq != null) {
            return new ResponseEntity<>(gameReq, HttpStatus.OK);
        }
        return ResponseEntity.badRequest().body("Bad request");
    }
    @GetMapping("/get-test")
    public ResponseEntity<?> getTest(@RequestParam Long id) {

        return ResponseEntity.status(HttpStatus.OK).body(gameRepo.getById(id));
    }


    @DeleteMapping("/delete-game")
    public boolean deleteQue(@RequestParam Long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = getUsernameByToken(request, response);
        return gameService.deleteGameFromDB(id, username);
    }

    @GetMapping("/get-list-game")
    public ResponseEntity<?> getListQue() {
        return new ResponseEntity<>(gameService.getListGame(), HttpStatus.OK);
    }
}
