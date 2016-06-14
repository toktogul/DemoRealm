package sport.mp3.kg.realmdemo.models;

import io.realm.RealmObject;

/**
 * Created by chen on 14.06.16.
 */
public class City extends RealmObject implements BaseModel {


    private String name;
    private String cityType;



    public City() {

    }

    public City(String name, String cityType) {
        this.name = name;
        this.cityType = cityType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCityType() {
        return cityType;
    }

    public void setCityType(String cityType) {
        this.cityType = cityType;
    }

    @Override
    public String getName() {
        return null;
    }
}


//        Route{id, cityName},
//        Days{routeId, day},
//        Times{routeId, time}