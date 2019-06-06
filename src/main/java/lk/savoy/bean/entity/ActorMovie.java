package lk.savoy.bean.entity;

import javax.persistence.*;

@Entity
@Table(name = "actor_movie")
public class ActorMovie extends SuperEntity {

    @EmbeddedId
    private ActorMoviePK actorMoviePK;
    private String role;

    @ManyToOne
    @JoinColumn(name = "actor_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Actor actor;

    @ManyToOne
    @JoinColumn(name = "movie_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Movie movie;

    public ActorMovie() {
    }

    public ActorMovie(ActorMoviePK actorMoviePK, String role) {
        this.actorMoviePK = actorMoviePK;
        this.role = role;
    }

    public ActorMovie(int actorId, int movieId, String role) {
        this.actorMoviePK = new ActorMoviePK(actorId, movieId);
        this.role = role;
    }

    public ActorMoviePK getActorMoviePK() {
        return actorMoviePK;
    }

    public void setActorMoviePK(ActorMoviePK actorMoviePK) {
        this.actorMoviePK = actorMoviePK;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    @Override
    public String toString() {
        return "ActorMovie{" +
                "actorMoviePK=" + actorMoviePK +
                ", role='" + role + '\'' +
                '}';
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

}
