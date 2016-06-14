package sport.mp3.kg.realmdemo.view;

import java.util.List;

/**
 * Created by chen on 13.06.16.
 */
public interface MainView<T>{

    void onShow(List<T> list);
    void onError(String error);
    void putItem(T t);

    void itemRemoved(int car);

    void updated(T s, int position);
}
