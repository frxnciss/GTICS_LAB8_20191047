package org.example.lab8.repository;

import org.example.lab8.entity.Movie;
import org.example.lab8.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {
}
