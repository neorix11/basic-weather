package com.bluelampcreative.basicweather.feature;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import com.bluelampcreative.basicweather.R;
import com.bluelampcreative.basicweather.adapters.WeatherListAdapter;
import com.bluelampcreative.basicweather.core.BaseActivity;
import com.bluelampcreative.basicweather.models.ForecastDay;
import com.bluelampcreative.basicweather.views.LocationEntry;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.functions.Action1;

public class MainActivity extends BaseActivity implements MainContract.View {


    @BindView(R.id.btn_get_weather)
    Button weatherButton;

    @BindView(R.id.location_entry)
    LocationEntry locationEntry;

    @BindView(R.id.recycler_weather)
    RecyclerView recyclerWeather;

    List<ForecastDay> forcasts = new ArrayList<>();
    WeatherListAdapter adapter = new WeatherListAdapter();


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

        presenter.registerView(this);

        recyclerWeather.setLayoutManager(new LinearLayoutManager(this));
        recyclerWeather.setAdapter(adapter);

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
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

    }

    @Override
    public void updateWeatherList(List<ForecastDay> weatherList) {
        adapter.setItems(weatherList);
    }
}
