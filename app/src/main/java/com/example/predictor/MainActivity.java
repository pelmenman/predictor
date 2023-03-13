package com.example.predictor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.predictor.api.YandexAPI;
import com.example.predictor.model.Answer;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String BASE_URL = "https://predictor.yandex.net";
    public static final String KEY = "pdct.1.1.20230313T095628Z.05de8879f20ef8d5.b850f23af0e22dfae3d5334319aeae489596151c";
    public static final String LANG = "en";
    public static final Integer LIMIT = 5;
    TextView textView;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Button btn = findViewById(R.id.button);
//        btn.setOnClickListener(view -> {
//            doRequest();
//        });
        textView = findViewById(R.id.result);
        editText = findViewById(R.id.editTextTextPersonName);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                doRequest();
            }
        });
    }

    public void doRequest() {

        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).
                build();

        YandexAPI api = retrofit.create(YandexAPI.class);
        api.getComplete(KEY, editText.getText().toString(), LANG, LIMIT).enqueue(new Callback<Answer>() {
            @Override
            public void onResponse(Call<Answer> call, Response<Answer> response) {
                if(response.code() == 200) {
                    List<String> result = response.body().getText();
                    StringBuilder stringBuilder = new StringBuilder();
                    for (String s : result) {
                        stringBuilder.append(s + "\n");
                    }

                    textView.setText(stringBuilder.toString());
                }
            }

            @Override
            public void onFailure(Call<Answer> call, Throwable t) {
                Log.d("RRR", t.toString());
            }
        });
    }


}