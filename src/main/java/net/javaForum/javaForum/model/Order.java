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

    @Column(columnDefinition = "TEXT")
    private String card_number;
    private Integer cvv;


    //TODO:date
//    Date date;

    @JoinColumn(name = "users_id")
    @ManyToOne
    private User user;

    private Long game_id;

}
