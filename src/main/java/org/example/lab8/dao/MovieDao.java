package org.example.lab8.dao;

import org.example.lab8.entity.Movie;
import org.example.lab8.entity.User;
import org.example.lab8.repository.MovieRepository;
import org.example.lab8.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
public class MovieDao {

    private static final String KEY = "76e2d1a3a7679081065d461184f15dd4";

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private UsersRepository userRepository;

    public List<Map<String, Object>> listarPeliculasEnCartelera() {
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format("https://api.themoviedb.org/3/movie/now_playing?api_key=%s", KEY);
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);
        return (List<Map<String, Object>>) response.get("results");
    }

    public void agregarFavorito(Long userId, Long movieId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        RestTemplate restTemplate = new RestTemplate();
        String url = String.format("https://api.themoviedb.org/3/movie/%d?api_key=%s", movieId, KEY);
        Map<String, Object> movieData = restTemplate.getForObject(url, Map.class);

        if (movieData != null) {
            Movie movie = new Movie();
            movie.setId(movieId);
            movie.setTitle((String) movieData.get("title"));
            movie.setOverview((String) movieData.get("overview"));
            movie.setPopularity(((Number) movieData.get("popularity")).floatValue());
            movie.setReleaseDate(Date.from(Instant.parse((String) movieData.get("release_date"))));
            movie.setUser(user);

            movieRepository.save(movie);
        }
    }

}
