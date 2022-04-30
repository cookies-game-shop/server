package net.javaForum.javaForum;

import net.javaForum.javaForum.model.Category;
import net.javaForum.javaForum.model.Role;
import net.javaForum.javaForum.model.User;
import net.javaForum.javaForum.repository.CategoryRepo;
import net.javaForum.javaForum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@EnableSwagger2
@SpringBootApplication
public class JavaForumApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(JavaForumApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    UserService userService;
    @Autowired
    CategoryRepo categoryRepo;

    @Override
    public void run(String... args) {
        userService.saveRole(new Role(null, "ROLE_USER"));
        userService.saveRole(new Role(null, "ROLE_ADMIN"));
        userService.createUser(new User(null, "admin", "123456",
                new ArrayList<>(), new ArrayList<>()));

        userService.addRoleToUser("admin", "ROLE_ADMIN");

        Category category1 = new Category(null, "Arcade");
        Category category2 = new Category(null, "3D");
        categoryRepo.save(category1);
        categoryRepo.save(category2);

    }

}
