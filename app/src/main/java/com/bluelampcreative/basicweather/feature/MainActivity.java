package com.bluelampcreative.basicweather.feature;

import android.os.Bundle;
import android.widget.Button;

import com.bluelampcreative.basicweather.R;
import com.bluelampcreative.basicweather.core.BaseActivity;
import com.bluelampcreative.basicweather.views.LocationEntry;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.functions.Action1;

public class MainActivity extends BaseActivity {


    @BindView(R.id.btn_get_weather)
    Button weatherButton;

    @BindView(R.id.location_entry)
    LocationEntry locationEntry;


    @Inject
    MainContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        //DAGGER Injection
        getApplicationComponent().inject(this);

        subscribeToDataEntry();
    }

    private void subscribeToDataEntry() {
        locationEntry.subscribeToTextEntry()
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean valid) {
                        weatherButton.setEnabled(valid);
                    }
                });
    }

    @OnClick(R.id.btn_get_weather)
    public void onGetWeatherClick() {
        presenter.getWeatherClick(
                locationEntry.getCity(),
                locationEntry.getState()
        );
    }

}
