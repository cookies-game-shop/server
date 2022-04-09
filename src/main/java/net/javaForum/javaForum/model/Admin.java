package net.javaForum.javaForum.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor

@Getter
@Setter
@Table(name = "admins")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "admin_id")
    private Long id;


    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

//    @ManyToMany(fetch = FetchType.EAGER)
//    private Collection<Role> roles = new ArrayList<>();

}
