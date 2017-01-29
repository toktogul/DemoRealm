package sport.mp3.kg.realmdemo.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by chen on 13.06.16.
 */
public class Car extends RealmObject implements BaseModel {

    @PrimaryKey()
    private long id;

    private String name;


    public Car() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Car(long id, String carName) {
        this.id = id;
        this.name = carName;
    }

    public Car(String carName) {
        this.name = carName;
    }

    public String getName() {
        return name;
    }

    public void setName(String carName) {
        this.name = carName;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", carName='" + name + '\'' +
                '}';
    }
}
