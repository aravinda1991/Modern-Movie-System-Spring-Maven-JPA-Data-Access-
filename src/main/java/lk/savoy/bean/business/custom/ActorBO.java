package lk.savoy.bean.business.custom;

import lk.savoy.bean.business.SuperBO;
import lk.savoy.bean.dto.ActorDTO;

import java.util.List;

public interface ActorBO extends SuperBO {

    public List<ActorDTO> getAllActors() throws Exception;

    public void saveActor(ActorDTO dto) throws Exception;

    public void updateActor(ActorDTO dto) throws Exception;

    public void removeActor(Integer id) throws Exception;

    public ActorDTO getActorById(Integer id) throws Exception;

}
