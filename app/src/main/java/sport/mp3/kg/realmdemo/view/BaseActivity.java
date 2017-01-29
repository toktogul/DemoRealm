package sport.mp3.kg.realmdemo.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import sport.mp3.kg.realmdemo.R;
import sport.mp3.kg.realmdemo.presenter.Presenter;

/**
 * Created by chen on 14.06.16.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected Presenter presenter;
    protected RecyclerView recyclerView;
    protected CarAdapter carAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    protected void init() {

        recyclerView = (RecyclerView) findViewById(R.id.list);
        assert recyclerView != null;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }


}