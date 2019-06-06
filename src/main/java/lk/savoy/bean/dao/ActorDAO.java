package lk.savoy.bean.dao;

import lk.savoy.bean.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorDAO extends JpaRepository<Actor, Integer> {

    // ActorTM getTopActorsByActorByIdDese() throws Exception;

}
