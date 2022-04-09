package net.javaForum.javaForum.repository;



import net.javaForum.javaForum.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepo extends JpaRepository<Game,Long> {

}
