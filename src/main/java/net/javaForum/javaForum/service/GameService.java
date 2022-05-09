package net.javaForum.javaForum.service;

import net.javaForum.javaForum.model.Game;
import net.javaForum.javaForum.repository.GameRepo;
import net.javaForum.javaForum.repository.UserRepo;
import net.javaForum.javaForum.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService  {

    @Autowired
    GameRepo gameRepo;
    @Autowired
    UserRepo userRepo;


    public GameService(GameRepo gameRepo, UserRepo userRepo) {
        this.gameRepo = gameRepo;
        this.userRepo = userRepo;
    }

    public boolean saveGameToDB(Game game, Long category_id) {
       if(game!=null){
            game.setCategory_id(category_id);
            gameRepo.save(game);
            return true;
        }
        return false;
    }

    public List<Game> getListGame() {
        return gameRepo.findAll();
    }

    public Game getGame(Long id) {
        if (gameRepo.existsById(id)) {
            return gameRepo.getById(id);
        }
        return null;
    }

    public boolean deleteGameFromDB(Long id) {
        if (gameRepo.existsById(id)) {
                gameRepo.deleteById(id);
                return true;
        }
        return false;
    }
}
