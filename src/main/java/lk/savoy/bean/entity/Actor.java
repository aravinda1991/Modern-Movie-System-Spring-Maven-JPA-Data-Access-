package lk.savoy.bean.entity;


import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Actor extends SuperEntity {

    @Id
    private int id;
    private String name;
    private int age;

    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.PERSIST})
    @OneToMany(mappedBy = "actor")
    private List<ActorMovie> actorMovies = new ArrayList<>();


    public Actor() {
    }

    public Actor(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "ActorTM{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public List<ActorMovie> getActorMovies() {
        return actorMovies;
    }
}

