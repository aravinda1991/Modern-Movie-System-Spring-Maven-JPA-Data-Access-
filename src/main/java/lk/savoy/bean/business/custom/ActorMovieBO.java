package lk.savoy.bean.business.custom;

import lk.savoy.bean.business.SuperBO;
import lk.savoy.bean.dto.ActorMovieDTO;

public interface ActorMovieBO extends SuperBO {

    public void saveActorMovie(ActorMovieDTO dto) throws Exception;

}
