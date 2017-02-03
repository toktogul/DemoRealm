package sport.mp3.kg.realmdemo.view;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.List;

import sport.mp3.kg.realmdemo.R;
import sport.mp3.kg.realmdemo.models.Model;
import sport.mp3.kg.realmdemo.presenter.ModelPresenter;

public class ModelActivity extends BaseActivity implements MainView<Model> ,
                                                CarAdapter.OnCarAdapterListener<Model>{

    private String parentId;

    @Override
    protected void onStart() {
        super.onStart();
        if(getIntent() != null && getIntent().hasExtra("car_id")){
            parentId = getIntent().getStringExtra("car_id");
            presenter.onStart(parentId);
        }
    }

    @Override
    protected void init() {
        super.init();
        carAdapter = new CarAdapter(this);
        recyclerView.setAdapter(carAdapter);
        presenter = new ModelPresenter(this);
    }

    public void onClick(View view) {
        EditText text = (EditText) findViewById(R.id.car_name);
        InputMethodManager manager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
        presenter.add(parentId, text.getText().toString());
        text.setText(null);
    }

    @Override
    public void onShow(List<Model> list) {
        carAdapter.setData(list);
    }

    @Override
    public void onError(String error) {

    }

    @Override
    public void putItem(Model model) {

        carAdapter.putItem(model);
    }

    @Override
    public void itemRemoved(int car) {

    }

    @Override
    public void updated(Model s, int position) {

    }



    @Override
    public void onItemDelete(Model car, int position) {

    }

    @Override
    public void onItemEdit(Model car, int position) {

    }

    @Override
    public void onClickItem(Model car) {

    }
}
