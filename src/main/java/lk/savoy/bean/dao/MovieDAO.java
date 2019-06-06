package lk.savoy.bean.dao;

import lk.savoy.bean.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieDAO extends JpaRepository<Movie, Integer> {
}
