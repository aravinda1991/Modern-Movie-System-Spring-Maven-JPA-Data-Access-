package lk.savoy.bean.business.custom;

import lk.savoy.bean.business.SuperBO;
import lk.savoy.bean.dto.MovieDTO;

import java.util.List;

public interface MovieBO extends SuperBO {

    public List<MovieDTO> getAllMovies() throws Exception;

    public void saveMovie(MovieDTO dto) throws Exception;

    public void updateMovie(MovieDTO dto) throws Exception;

    public void removeMovie(Integer id) throws Exception;

    public MovieDTO getMovieById(Integer id) throws Exception;


}
