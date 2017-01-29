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
    private char[] alphabetS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    public static final String TAG = BaseActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        CesarCipher test = new CesarCipher();
//        test.setAlphabet(alphabetS);
//        test.setShift(3);
//        Log.d(TAG, "onCreate: " + test.encode("The quick brown fox jumps over the lazy dog"));
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


class CesarCipher {


    private char[] alphabet;
    private int shift;

    public void setAlphabet(char[] alphabet) {
        this.alphabet = alphabet;
    }

    public void setShift(int shift) {
        this.shift = shift;
    }

    public String encode(String plainText) {
        // реализация
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < plainText.length(); i++) {
            boolean isFind = false;

            for (int k = 0; k < alphabet.length; k++) {
                if (plainText.charAt(i) == alphabet[k]) {
                    isFind = true;
                    int lastKey = alphabet.length;
                    int currentKey = k + shift;

                    if (currentKey < lastKey)
                        stringBuilder.append(alphabet[currentKey]);
                    else
                        stringBuilder.append(alphabet[currentKey - lastKey]);

                    break;
                }
            }

            if (!isFind) {
                stringBuilder.append(plainText.charAt(i));
            }
        }


        return stringBuilder.toString();
    }
}