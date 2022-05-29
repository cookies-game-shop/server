package net.javaForum.javaForum;

import net.javaForum.javaForum.model.Category;
import net.javaForum.javaForum.model.Game;
import net.javaForum.javaForum.model.Role;
import net.javaForum.javaForum.model.User;
import net.javaForum.javaForum.repository.CategoryRepo;
import net.javaForum.javaForum.repository.GameRepo;
import net.javaForum.javaForum.service.GameService;
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
    @Autowired
    GameService gameService;
    @Autowired
    GameRepo gameRepo;

    @Override
    public void run(String... args) {
        userService.saveRole(new Role(null, "ROLE_USER"));
        userService.saveRole(new Role(null, "ROLE_ADMIN"));
        userService.createUser(new User(null, "admin", "123456","","", new ArrayList<>(), new ArrayList<>()));
        userService.addRoleToUser("admin", "ROLE_ADMIN");
        Category category1 = new Category(null, "Arcade");
        Category category2 = new Category(null, "3D");
        categoryRepo.save(category1);
        categoryRepo.save(category2);
        Game game = new Game(null,
                "GHOSTWIRE: TOKYO'S VISITORS & YOKAI",
                "With the help of a gruff, ghostly detective named KK, you will fight to get to the bottom of Tokyo’s supernatural disappearance and save the city from disaster.\n" +
                        "While skills like Ethereal Weaving are essential in Ghostwire: Tokyo, knowledge is also crucial. To aid your ghost hunting, we’ve included a quick brief on some of the city’s newfound populace. Some are dangerous and should be avoided, while others can become key allies if you play your cards right, so read on for more details from our own paranormal investigations.",
                52.79,
                null,
                null);
        gameRepo.save(game);

        Game game1 = new Game(null,
                "Fallout 76",
                "It is an installment in the Fallout series and a prequel to previous entries. Fallout 76 is Bethesda Game Studios's first multiplayer game; players explore the open world, which has been torn apart by nuclear war, with others.",
                12.99,
                null,
                null);
        gameRepo.save(game1);


        Game game2 = new Game(null,
                "DOOM® Eternal",
                "Genshin Impact — компьютерная игра в жанре action-adventure " +
                        "The Doom Slayer spends his time in hell surviving and murdering legions of demons which earns him the title of Doom Slayer. He killed so many demons that he's now revered as some sort of devil himself in their world, which leads to the demons imprisoning him in a tomb so that he can't wreak havoc anymore.",
                29.99,
                null,
                null);
        gameRepo.save(game2);

        Game game3 = new Game(null,
                "The Elder Scrolls V: Skyrim - Special Edition",
                "Winner of more than 200 Game of the Year Awards, Skyrim Special Edition brings the epic fantasy to life in stunning detail. The Special Edition includes the critically acclaimed game and add-ons with all-new features like remastered art and effects, volumetric god rays, dynamic depth of field, screen-space reflections, and more. Skyrim Special Edition also brings the power of mods to the PC, Xbox One and Playstation 4.",
                49.99,
                null,
                null);
        gameRepo.save(game3);

        Game game4 = new Game(null,
                "Wolfenstein II: The New Colossus",
                "The story follows war veteran William \"B.J.\" Blazkowicz and his efforts to fight against the Nazi regime in the United States. The game is played from a first-person perspective and most of its levels are navigated on foot. The story is arranged in chapters, which players complete in order to progress.",
                15.99,
                null,
                null);
        gameRepo.save(game4);


        Game game5 = new Game(null,
                "Prey: Bethesda.Net Edition",
                "In Prey, you awaken aboard Talos I, a space station orbiting the moon in the year 2032. You are the key subject of an experiment meant to alter humanity forever – but things have gone terribly wrong. The space station has been overrun by hostile aliens and you are now being hunted. As you dig into the dark secrets of Talos I and your own past, you must survive using the tools found on the station, your wits, weapons, and mind-bending abilities.",
                39.99,
                null,
                null);
        gameRepo.save(game5);

        Game game6 = new Game(null,
                "The Evil Within Digital Deluxe Bundle",
                "This bundle includes The Evil Within and all season pass content: The Assignment, The Consequence, and The Executioner packs. Developed by Shinji Mikami -- creator of the seminal Resident Evil series -- and the talented team at Tango Gameworks, The Evil Within embodies the meaning of pure survival horror.",
                29.99,
                null,
                null);
        gameRepo.save(game6);

        Game game7 = new Game(null,
                "Wolfenstein: Cyberpilot",
                "Wolfenstein®: Cyberpilot™ brings virtual reality to the revolution against the Nazis.",
                20.00,
                null,
                null);
        gameRepo.save(game7);

        Game game8 = new Game(null,
                "Dishonored - Void Walker's Arsenal (DLC)",
                "The gifts of the Outsider are bestowed unto those who walk the Void.\n" +
                        "With the Void Walker’s Arsenal add-on pack, gain immediate access to four content bundles previously available only through pre-ordering Dishonored. The Acrobatic Killer Pack, Arcane Assassin Pack, Backstreet Butcher Pack and Shadow Rat Pack offer unique character bonuses, additional bone charm slots, unhidden books and bonus coins that will aid you in your pursuit of revenge. Found in the Hound Pits Pub, use these items to enhance your playthrough of Corvo’s main campaign in the original",
                3.99,
                null,
                null);
        gameRepo.save(game8);

        Game game9 = new Game(null,
                "Dishonored 2",
                "Dishonored 2 is an action-adventure game with stealth elements played from a first-person perspective. After playing as Empress Emily Kaldwin during the prologue, players may decide to play either as Emily or as Corvo Attano, the protagonist from Dishonored, the previous game.",
                29.99,
                null,
                null);
        gameRepo.save(game9);


        Game game10 = new Game(null,
                "DOOM Eternal: The Ancient Gods - Part Two",
                "The Ancient Gods - Part Two, abbreviated in-game as Ancient Gods 2 is the second and final campaign extension for Doom Eternal, that was released on March 18th, 2021 . It is a sequel to The Ancient Gods - Part One, the first campaign extension.",
                19.99,
                null,
                null);
        gameRepo.save(game10);


      /*  Game game11 = new Game(null,
                "Prey®: Bethesda.net Edition",
                "The game takes place in an alternate timeline in which an accelerated Space Race resulted in humankind taking to orbital stations far earlier. The player controls Morgan Yu while exploring the space station Talos I, in orbit around Earth–Moon L2, where they were part of a scientific team researching the Typhon, a hostile alien force composed of many forms with both physical and psychic powers, such as shapeshifting into a clone of any inanimate object. As the Typhoon escape confinement, the player uses a variety of weapons and abilities derived from the Typhoon to avoid being killed while looking to escape the station. ",
                39.99,
                null,
                null);
        gameRepo.save(game11);*/

        Game game12 = new Game(null,
                "The Elder Scrolls Online Collection",
                "The Elder Scrolls Online Collection: Blackwood is the ultimate Elder Scrolls experience and includes the base game, new Blackwood Chapter, and all previous Chapters.",
                14.99,
                null,
                null);
        gameRepo.save(game12);

        Game game13 = new Game(null,
                "The Elder Scrolls V: Skyrim - Anniversary Upgrade",
                "Winner of more than 200 Game of the Year Awards, Skyrim Special Edition brings the epic fantasy to life in stunning detail. The Special Edition includes the critically acclaimed game and add-ons with all-new features like remastered art and effects, volumetric god rays, dynamic depth of field, screen-space.",
                49.99,
                null,
                null);
        gameRepo.save(game13);
        Game game14 = new Game(null,
                "DEATHLOOP Deluxe Edition",
                "DEATHLOOP is a story about two perfect killers stuck in a mysterious time loop. They are doomed to live the same day over and over again. For Colt, the only chance to break out of the loop is to destroy eight targets by the end of the day. Every turn of the loop is an opportunity to learn something about the world, try out new ways and methods, get new weapons and abilities to finally break the loop.",
                49.99,
                null,
                null);
        gameRepo.save(game14);
        Game game15 = new Game(null,
                "Call of Cthulhu: Dark Corners of the Earth",
                "Call of Cthulhu: Dark Corners of the Earth is a first-person horror game that combines intense action and adventure elements. You will draw upon your skills in exploration, investigation, and combat while faced with the seemingly impossible task of battling evil incarnate.",
                47.99,
                null,
                null);
        gameRepo.save(game15);
        Game game16 = new Game(null,
                "DOOM Eternal: The Ancient Gods - Part Two",
                "",
                29.99,
                null,
                null);
        gameRepo.save(game16);
        Game game17 = new Game(null,
                "Quake III Arena",
                "Welcome to the Arena where high-ranking warriors turn into invertebrate porridge. Abandoning every drop of common sense and every trace of doubt, you rush into the arena of heartbreaking landscapes and veiled abysses. Your new mantra: Fight or goodbye.",
                14.99,
                null,
                null);
        gameRepo.save(game17);
        Game game18 = new Game(null,
                "Quake II",
                "Shortly after landing on the alien surface, you will find out that hundreds of your people have been reduced to just a few. Now you have to break through heavily fortified military installations, lower the city's defenses and stop the enemy's war machine. Only then will the fate of humanity be known.",
                4.99,
                null,
                null);
        gameRepo.save(game18);

        Game game19 = new Game(null,
                "Fallout Tactics",
                "What the Fallout ® universe lacked so much — tactical platoon battles!\n" +
                        "Right now you're nothing but trash.In these dark times, the Brotherhood—your Brotherhood— is the only thing that protects the rekindled spark of civilization from the gloomy, radioactive wasteland.",
                9.99,
                null,
                null);
        gameRepo.save(game19);

        Game game20 = new Game(null,
                "HeXen: Deathkings of the Dark Citadel",
                "Where HeXen Ends, the True Nightmare Begins. Deathkings of the Dark Citadel is the only official expansion of the original HeXen game. This add-on pack features 20 new single player levels from the original HeXen team as well as a host of new multiplayer options.",
                4.99,
                null,
                null);
        gameRepo.save(game20);

    }

}
