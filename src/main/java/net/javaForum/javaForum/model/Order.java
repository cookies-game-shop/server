package net.javaForum.javaForum.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name="orders")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="order_id")
    private Long id;
    private String userFirstName;
    private String userLastName;
    private String email;
    private String nameOnCard;
    @Column(columnDefinition = "TEXT")
    private String card_number;
    private Integer cvv;
    private Date exp_date;
    private Long game_id;
}
