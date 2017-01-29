package sport.mp3.kg.realmdemo.presenter;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import sport.mp3.kg.realmdemo.models.Car;
import sport.mp3.kg.realmdemo.view.MainView;

/**
 * Created by chen on 13.06.16.
 */
public class CarPresenter implements Presenter<Car> {

    private MainView activity;
    private Realm realm;

    public CarPresenter(MainView activity) {
        this.activity = activity;
        realm = Realm.getDefaultInstance();
    }

    @Override
    public void onStart(String... id) {
        start();
    }

    private void start(){

        if(activity != null){
            activity.onShow(refresh());
        }
    }

    @Override
    public void add(String... car){

        Number key = realm.where(Car.class).max("id");
        long nextId = 0;
        if(key != null) {
            nextId = key.longValue() + 1;
        }

        final Car newCar = new Car(nextId, car[0]);


        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(newCar);
            }
        }, new Realm.Transaction.OnSuccess() {

            @Override
            public void onSuccess() {
                if (activity != null) {
                    activity.putItem(newCar);
                }
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                if (activity != null) {
                    activity.onError("Не смог добавить запись");
                }
            }
        });


    }



    @Override
    public List<Car> refresh(){
        RealmQuery<Car> query = realm.where(Car.class);
        RealmResults<Car> result = query.findAll();

        List<Car> carList = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            carList.add(result.get(i));
        }

        return carList;
    }

    @Override
    public void onDestroy() {
        activity = null;
        realm.close();
    }

    @Override
    public void remove(Car car, int position) {
        RealmResults<Car> row = realm.where(Car.class).equalTo("id", car.getId()).findAll();
        realm.beginTransaction();
        boolean result = row.deleteAllFromRealm();
        realm.commitTransaction();
        if(result && activity != null){
            activity.itemRemoved(position);
        }
    }

    @Override
    public void update(Car s, int position) {
        Car car = realm.where(Car.class).equalTo("id", s.getId()).findFirst();
        realm.beginTransaction();
        car.setName(s.getName());
        realm.commitTransaction();
        if(activity != null){
            activity.updated(s, position);
        }
    }
}
