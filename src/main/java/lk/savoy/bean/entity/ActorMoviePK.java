package lk.savoy.bean.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ActorMoviePK implements Serializable {

    @Column(name = "actor_id")
    private int actorId;
    @Column(name = "movie_id")
    private int movieId;

    public ActorMoviePK() {
    }

    public ActorMoviePK(int actorId, int movieId) {
        this.actorId = actorId;
        this.movieId = movieId;
    }

    public int getActorId() {
        return actorId;
    }

    public void setActorId(int actorId) {
        this.actorId = actorId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    @Override
    public String toString() {
        return "ActorMoviePK{" +
                "actorId=" + actorId +
                ", movieId=" + movieId +
                '}';
    }
}
