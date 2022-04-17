package net.javaForum.javaForum.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javaForum.javaForum.model.Game;
import net.javaForum.javaForum.model.Role;
import net.javaForum.javaForum.model.User;
import net.javaForum.javaForum.repository.GameRepo;
import net.javaForum.javaForum.repository.RoleRepo;
import net.javaForum.javaForum.repository.UserRepo;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService implements UserDetailsService {


    private final UserRepo userRepository;
    private final GameRepo gameRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            log.error("User not found");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User found in the database: {}", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.get().getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.get().getUsername(), user.get().getPassword(), authorities);
    }


    //CREATE USER
    public boolean createUser(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return false;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        addRoleToUser(user.getUsername(), "ROLE_USER");
        return true;
    }

    //UPDATE USER CREDENTIALS
    public User update(String username, User updated) {
        if (userRepository.existsByUsername(username)) {
            User user = userRepository.getByUsername(username);
            userRepository.deleteByUsername(user.getUsername());
            updated.setPassword(passwordEncoder.encode(updated.getPassword()));
            userRepository.save(updated);
            return userRepository.getByUsername(updated.getUsername());
        }
        return null;
    }

    //GET USER
    public User getUser(String username) {
        if (userRepository.findByUsername(username).isPresent()) {
            return userRepository.findByUsername(username).get();
        }
        return null;
    }

    //DELETE USER
    public boolean delete(String username) {
        if (userRepository.findByUsername(username).isPresent()) {
            userRepository.deleteByUsername(username);
            return true;
        }
        return false;
    }

    //LIST OF GAMES IN CARD
    public Collection<Game> getGamesCardByUsername(String username) throws Exception {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return user.get().getGamesCard();
        }
        throw new Exception();
    }


    // ADD TO CARD GAME
    public boolean addToCard(String username, Long game_id) {
        if (userRepository.existsByUsername(username)) {
            if (gameRepo.existsById(game_id)) {
                if (!userRepository.getByUsername(username).getGamesCard().contains(gameRepo.getById(game_id))) {
                    userRepository.getByUsername(username).getGamesCard().add(gameRepo.getById(game_id));
                    return true;
                }
            }
        }
        return false;
    }

    public boolean deleteGameFromCard(Long id, String username) {
        if (userRepository.existsById(id)) {
            if (userRepository.getByUsername(username).getGamesCard().contains(gameRepo.getById(id))) {
                userRepository.getByUsername(username).getGamesCard().remove(gameRepo.getById(id));
                return true;
            }
        }
        return false;
    }

    public Role saveRole(Role role) {
        return roleRepo.save(role);
    }

    public void addRoleToUser(String username, String roleName) {
        User user = userRepository.getByUsername(username);
        Role role = roleRepo.findByName(roleName);
        user.getRoles().add(role);
    }
}
