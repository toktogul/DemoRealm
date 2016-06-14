package sport.mp3.kg.realmdemo.presenter;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import sport.mp3.kg.realmdemo.models.Model;
import sport.mp3.kg.realmdemo.view.MainView;

/**
 * Created by chen on 14.06.16.
 */
public class ModelPresenter implements Presenter<Model> {

    MainView view;
    Realm realm;


    public ModelPresenter(MainView view) {
        this.view = view;
        realm = Realm.getDefaultInstance();
    }

    @Override
    public void onStart(String... id) {
        start(id[0]);
    }

    private void start(String id) {
        RealmResults<Model> realmResults = realm.where(Model.class).equalTo("parentId", id).findAll();

        List<Model> list = new ArrayList<>();
        for (Model model :realmResults) {
            list.add(model);
        }
        if(view != null){
            view.onShow(list);
        }
    }

    @Override
    public List<Model> refresh() {
        return null;
    }

    @Override
    public void update(Model model, int position) {

    }

    @Override
    public void remove(Model model, int position) {

    }

    @Override
    public void add(String... t) {
        Number key = realm.where(Model.class).max("id");
        long nextId = 0;

        if(key != null){
            nextId = key.longValue() + 1;
        }

        final Model model = new Model(nextId, t[0], t[1]);

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(model);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                if(view != null){
                    view.putItem(model);
                }
            }
        });


    }

    @Override
    public void onDestroy() {
        realm.close();
        view = null;
    }
}
