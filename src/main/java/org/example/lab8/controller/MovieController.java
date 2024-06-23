package org.example.lab8.controller;
import org.example.lab8.dao.MovieDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieDao movieDao;

    @GetMapping("/now_playing")
    public ResponseEntity<List<Map<String, Object>>> getNowPlayingMovies() {
        List<Map<String, Object>> movies = movieDao.listarPeliculasEnCartelera();
        return ResponseEntity.ok(movies);
    }

    @PostMapping("/add_favorite")
    public ResponseEntity<String> addFavoriteMovie(@RequestBody Map<String, Object> payload) {
        Long userId = ((Number) payload.get("userId")).longValue();
        Long movieId = ((Number) payload.get("movieId")).longValue();

        movieDao.agregarFavorito(userId, movieId);

        return ResponseEntity.status(201).body("Favorite added successfully");
    }


}

