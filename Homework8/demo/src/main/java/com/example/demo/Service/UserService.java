package com.example.demo.Service;

import com.example.demo.dto.UserDTO;
import com.example.demo.Entity.BookEntity;
import com.example.demo.Permission;
import com.example.demo.Entity.UserEntity;
import com.example.demo.Repository.BookRepository;
import com.example.demo.Repository.PermissionRepository;
import com.example.demo.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final PermissionRepository permissionRepository;
    public UserEntity save(final UserEntity user) {return userRepository.save(user);}

    public UserEntity registerUser(UserDTO user )
    {
        return save(UserEntity.builder()
                .login(user.getLogin())
                .password(user.getPassword())
                .permissions(Collections.singletonList(permissionRepository.findByPermission(Permission.USER))).build());
    }
    public Optional<UserEntity> getUserByLogin(String log)
    {
        return userRepository.findByLogin(log);
    }
    public boolean userExists(final String login)
    {
        return userRepository.existsByLogin(login);
    }
    public void addFavorites(final BookEntity book, final String login) {
        Optional<UserEntity> user = getUserByLogin(login);
        if (user.isPresent()) {
            UserEntity us = user.get();
            List<BookEntity> favorites = findFavorites(login);
            favorites.add(book);
            us.setFavorites(favorites);
            save(us);
        }
    }
    public void deleteFavorites(final int id, final String login) {
        Optional<UserEntity> user = getUserByLogin(login);
        if (user.isPresent()) {
            UserEntity us = user.get();
            List<BookEntity> books = findFavorites(login);
            System.out.println(books.size());
            for (Iterator<BookEntity> it = books.iterator(); it.hasNext();) {
                BookEntity s = it.next();

                if (s.getId()==id){
                    it.remove();
                }
            }
            us.setFavorites(books);
            save(us);
        }
    }

    public List<BookEntity> findFavorites(String login) {
        return bookRepository.findFavoritesForUser(login);
    }
    public boolean isFavorite(int id, String login)
    {
        List<BookEntity> favorites = findFavorites(login);
        for(BookEntity b : favorites)
        {
            if (b.getId()==id)
                return true;
        }
        return false;
    }

}