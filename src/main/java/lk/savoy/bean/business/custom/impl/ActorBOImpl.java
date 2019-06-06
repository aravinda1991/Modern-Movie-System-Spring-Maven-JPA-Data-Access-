package lk.savoy.bean.business.custom.impl;

import lk.savoy.bean.business.custom.ActorBO;
import lk.savoy.bean.dao.ActorDAO;
import lk.savoy.bean.dto.ActorDTO;
import lk.savoy.bean.entity.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Transactional
public class ActorBOImpl implements ActorBO {

    @Autowired
    private ActorDAO actorDAO;

    @Override
    public List<ActorDTO> getAllActors() throws Exception {
        List<ActorDTO> actors = actorDAO.findAll().stream().map(actor -> new ActorDTO(actor.getId(), actor.getName(), actor.getAge())).collect(Collectors.toList());
        return actors;
    }

    @Override
    public void saveActor(ActorDTO dto) throws Exception {
        actorDAO.save(new Actor(dto.getId(), dto.getName(), dto.getAge()));
    }

    @Override
    public void updateActor(ActorDTO dto) throws Exception {
        actorDAO.save(new Actor(dto.getId(), dto.getName(), dto.getAge()));
    }

    /*Not sure this id*/
    @Override
    public void removeActor(Integer id) throws Exception {
        actorDAO.deleteById(id);
    }

    /*Not sure this id*/
    @Override
    public ActorDTO getActorById(Integer id) throws Exception {
        Actor actor = actorDAO.getOne(id);
        ActorDTO actorDTO = new ActorDTO(actor.getId(), actor.getName(), actor.getAge());
        return actorDTO;
    }
}
