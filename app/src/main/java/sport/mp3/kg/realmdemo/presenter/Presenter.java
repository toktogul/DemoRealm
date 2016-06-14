package sport.mp3.kg.realmdemo.presenter;

import java.util.List;

/**
 * Created by chen on 14.06.16.
 */
public interface Presenter<T> {


    void onStart(String... id);
    List<T> refresh();
    void update(T t, int position);
    void remove(T t , int position);
    void add(String... t);
    void onDestroy();
}
