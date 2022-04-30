package net.javaForum.javaForum.model;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
@Entity
@Table(name="games")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Game {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name="game_id")
    private Long id;
    private String name;
    @Column(columnDefinition = "TEXT")
    private String par;
    @NotNull
    private Integer price;
    @NotNull
    private Long category_id;
    //TODO: image

}
