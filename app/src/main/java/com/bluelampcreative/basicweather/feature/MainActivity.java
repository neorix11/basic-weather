package com.bluelampcreative.basicweather.feature;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.bluelampcreative.basicweather.R;
import com.bluelampcreative.basicweather.core.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class MainActivity extends BaseActivity {

    @BindView(R.id.edit_zip_code)
    EditText zipCodeEntry;

    @BindView(R.id.btn_get_weather)
    Button getWeatherButton;


    @Inject
    MainContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        //DAGGER Injection
        getApplicationComponent().inject(this);
    }

    @OnClick(R.id.btn_get_weather)
    public void onGetWeatherClick() {
        presenter.getWeatherClick(zipCodeEntry.getText().toString());
    }

    @OnTextChanged(R.id.edit_zip_code)
    public void onZipTextChanged(CharSequence zip) {
        getWeatherButton.setEnabled(zip.length() > 4);
    }
}
