package net.javaForum.javaForum.controller;

import lombok.extern.slf4j.Slf4j;
import net.javaForum.javaForum.model.Game;
import net.javaForum.javaForum.service.GameService;
import net.javaForum.javaForum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/game")
public class GameController {


    @Autowired
    GameService gameService;
    @Autowired
    UserService userService;


    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/save-game")
    public ResponseEntity<?> saveGame(@RequestBody Game game,
                                      String username,
                                      @RequestParam Long category_id) {

        //  String username = getUsernameByToken.getUsernameByToken(request,response);
        if (gameService.saveGameToDB(username, game, category_id)) {
            return new ResponseEntity<>(gameService.getGame(game.getId()), HttpStatus.OK);
        }
        return ResponseEntity.badRequest().body("Bad request");
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
    public boolean deleteQue(@RequestParam Long id, String username) {
        //  String username = getUsernameByToken.getUsernameByToken(request, response);
        return gameService.deleteGameFromDB(id, username);
    }

    @GetMapping("/get-list-game")
    public ResponseEntity<?> getListQue() {
        return new ResponseEntity<>(gameService.getListGame(), HttpStatus.OK);
    }
}
