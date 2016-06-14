package sport.mp3.kg.realmdemo.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.List;

import sport.mp3.kg.realmdemo.R;
import sport.mp3.kg.realmdemo.models.Car;
import sport.mp3.kg.realmdemo.presenter.CarPresenter;

public class MainActivity extends BaseActivity implements MainView<Car>,
        CarAdapter.OnCarAdapterListener<Car>{

    private static final String TAG = MainActivity.class.getName();





    @Override
    protected void onStart() {
        super.onStart();

        presenter.onStart();
    }

    @Override
    protected void init() {
        super.init();
        carAdapter = new CarAdapter(this);
        recyclerView.setAdapter(carAdapter);
        presenter = new CarPresenter(this);
    }

    public void onClick(View view) {
        EditText text = (EditText) findViewById(R.id.car_name);
        InputMethodManager manager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
        presenter.add(text.getText().toString());
        text.setText(null);
    }

    @Override
    public void onShow(List<Car> list) {
        carAdapter.setData(list);
    }

    @Override
    public void putItem(Car car) {
        carAdapter.putItem(car);
    }

    @Override
    public void itemRemoved(int position) {
        carAdapter.removeItem(position);
    }

    @Override
    public void updated(Car s, int position) {
        carAdapter.updateItem(s, position);
    }

    @Override
    public void onError(String error) {

    }

    @Override
    public void onItemDelete(Car car, int position) {
        Log.d(TAG, "onItemDelete: ");
        presenter.remove(car, position);
    }

    @Override
    public void onClickItem(Car car) {
        Intent intent  = new Intent(this, ModelActivity.class);
        intent.putExtra("car_id", String.valueOf(car.getId()));
        startActivity(intent);
    }

    @Override
    public void onItemEdit(final Car car, final int position) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.edit_dailog, null);
        final EditText editText = (EditText) view.findViewById(R.id.edit_value_dialog);

        editText.setText(car.getName());
        editText.setSelection(car.getName().length());
        dialog.setTitle("Edit");
        dialog.setView(view);


        dialog.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Car editCar = new Car(car.getId(), editText.getText().toString());
                presenter.update(editCar, position);
            }
        });

        dialog.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        dialog.create().show();
    }
}
