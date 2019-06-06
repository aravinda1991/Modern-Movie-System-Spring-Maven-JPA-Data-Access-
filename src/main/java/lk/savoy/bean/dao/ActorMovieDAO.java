package lk.savoy.bean.dao;

import lk.savoy.bean.entity.ActorMovie;
import lk.savoy.bean.entity.ActorMoviePK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorMovieDAO extends JpaRepository<ActorMovie, ActorMoviePK> {
}
