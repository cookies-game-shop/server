package net.javaForum.javaForum;

import net.javaForum.javaForum.model.Category;
import net.javaForum.javaForum.model.User;
import net.javaForum.javaForum.repository.CategoryRepo;
import net.javaForum.javaForum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.ArrayList;
@EnableSwagger2
@SpringBootApplication
public class JavaForumApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(JavaForumApplication.class, args);
    }

    @Autowired
    UserService userService;
    @Autowired
    CategoryRepo categoryRepo;

    @Override
    public void run(String... args) {
        userService.createUser(new User(null, "admin", "123456", "", "", new ArrayList<>()));
        Category category1 = new Category(null, "Arcade");
        Category category2 = new Category(null, "3D");
        categoryRepo.save(category1);
        categoryRepo.save(category2);
    }

}
