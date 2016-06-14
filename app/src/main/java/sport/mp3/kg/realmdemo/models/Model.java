package sport.mp3.kg.realmdemo.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by chen on 14.06.16.
 */
public class Model extends RealmObject implements BaseModel{

    @PrimaryKey()
    private long id;
    private String  parentId;
    private String name;

    public Model(long id, String parentId, String modelName) {
        this.id = id;
        this.parentId = parentId;
        this.name = modelName;
    }

    public Model() {
    }

    @Override
    public String toString() {
        return "Model{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", modelName='" + name + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String modelName) {
        this.name = modelName;
    }
}

