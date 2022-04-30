package net.javaForum.javaForum.service;

import net.javaForum.javaForum.model.Game;
import net.javaForum.javaForum.repository.GameRepo;
import net.javaForum.javaForum.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {
    private static final String ADMIN = "admin";
    @Autowired
    GameRepo gameRepo;
    @Autowired
    UserRepo userRepo;


    public GameService(GameRepo gameRepo, UserRepo userRepo) {
        this.gameRepo = gameRepo;
        this.userRepo = userRepo;
    }

    //TODO:проверка на админа
    public boolean saveGameToDB(String username, Game game, Long category_id) {
        if (username.equals(ADMIN)) {
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

    public boolean deleteGameFromDB(Long id, String username) {
        if (gameRepo.existsById(id)) {
            if (username.equals(ADMIN)) {
                gameRepo.deleteById(id);
                return true;
            }
        }
        return false;
    }
}
