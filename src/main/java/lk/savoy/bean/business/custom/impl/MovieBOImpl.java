package lk.savoy.bean.business.custom.impl;

import lk.savoy.bean.business.custom.MovieBO;
import lk.savoy.bean.dao.MovieDAO;
import lk.savoy.bean.dto.MovieDTO;
import lk.savoy.bean.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Transactional
public class MovieBOImpl implements MovieBO {

    @Autowired
    private MovieDAO movieDAO;

    @Override
    public List<MovieDTO> getAllMovies() throws Exception {
        List<MovieDTO> movies = movieDAO.findAll().stream().map(movie -> new MovieDTO(movie.getId(), movie.getName())).collect(Collectors.toList());
        return movies;
    }

    @Override
    public void saveMovie(MovieDTO dto) throws Exception {
        movieDAO.save(new Movie(dto.getId(), dto.getName()));
    }

    @Override
    public void updateMovie(MovieDTO dto) throws Exception {
        movieDAO.save(new Movie(dto.getId(), dto.getName()));
    }

    /*Not sure here id*/
    @Override
    public void removeMovie(Integer id) throws Exception {
        movieDAO.deleteById(id);
    }

    /*Not sure here id*/
    @Override
    public MovieDTO getMovieById(Integer id) throws Exception {
        Movie movie = movieDAO.getOne(id);
        MovieDTO movieDTO = new MovieDTO(movie.getId(), movie.getName());
        return movieDTO;
    }
}
