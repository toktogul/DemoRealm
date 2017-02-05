package sport.mp3.kg.realmdemo.presenter;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import sport.mp3.kg.realmdemo.models.Car;
import sport.mp3.kg.realmdemo.view.MainView;

public class CarPresenter implements Presenter<Car> {


    private MainView activity;
    boolean isTurn = false;
    private Realm realmInstance;

    public CarPresenter(MainView activity) {
        this.activity = activity;
        realmInstance = Realm.getDefaultInstance();
    }

    @Override
    public void onStart(String... id) {
        start();
    }

    private void start(){

        if(activity != null){
            Log.d("happy", "start: ");
            Log.d("happy", "start: ");
            Log.d("happy", "start: ");
            Log.d("happy", "start: ");
            Log.d("happy", "start: ");

            Log.d("happy", "start: ");

        }
    }

    @Override
    public void add(String... car){

        Number key = realmInstance.where(Car.class).max("id");
        long nextId = 0;
        if(key != null) {
            nextId = key.longValue() + 1;
        }

        final Car newCar = new Car(nextId, car[0]);


        realmInstance.executeTransactionAsync(new Realm.Transaction() {
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
        RealmQuery<Car> query = realmInstance.where(Car.class);
        RealmResults<Car> result = query.findAll();
        Log.d("happy", "start: ");
        List<Car> carList = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            carList.add(result.get(i));
        }

        return carList;
    }

    @Override
    public void onDestroy() {
        activity = null;
        realmInstance.close();
    }

    @Override
    public void remove(Car car, int position) {
        RealmResults<Car> row = realmInstance.where(Car.class).equalTo("id", car.getId()).findAll();
        realmInstance.beginTransaction();
        boolean result = row.deleteAllFromRealm();
        realmInstance.commitTransaction();
        if(result && activity != null){
            activity.itemRemoved(position);
        }
    }

    @Override
    public void update(Car s, int position) {
        Car car = realmInstance.where(Car.class).equalTo("id", s.getId()).findFirst();
        realmInstance.beginTransaction();
        car.setName(s.getName());
        realmInstance.commitTransaction();
        if(activity != null){
            activity.updated(s, position);
        }
    }
}
