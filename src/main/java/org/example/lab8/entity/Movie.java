package org.example.lab8.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String overview;
    private float popularity;
    private Date releaseDate;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}