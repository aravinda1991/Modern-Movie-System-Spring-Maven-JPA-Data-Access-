package lk.savoy.bean.business.custom.impl;

import lk.savoy.bean.business.custom.ActorMovieBO;
import lk.savoy.bean.dao.ActorMovieDAO;
import lk.savoy.bean.dto.ActorMovieDTO;
import lk.savoy.bean.entity.ActorMovie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class ActorMovieBOImpl implements ActorMovieBO {

    @Autowired
    private ActorMovieDAO actorMovieDAO;

    @Override
    public void saveActorMovie(ActorMovieDTO dto) throws Exception {

        actorMovieDAO.save(new ActorMovie(dto.getActorId(), dto.getMovieId(), dto.getRole()));

    }
}
