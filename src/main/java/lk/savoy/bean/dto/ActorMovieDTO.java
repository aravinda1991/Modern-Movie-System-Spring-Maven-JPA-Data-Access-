package lk.savoy.bean.dto;


public class ActorMovieDTO {

    private int actorId;
    private int movieId;
    private String role;


    public ActorMovieDTO() {
    }

    public ActorMovieDTO(int actorId, int movieId, String role) {
        this.actorId = actorId;
        this.movieId = movieId;
        this.role = role;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "ActorMovieDTO{" +
                "actorId=" + actorId +
                ", movieId=" + movieId +
                ", role='" + role + '\'' +
                '}';
    }
}
