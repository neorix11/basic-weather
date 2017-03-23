package com.bluelampcreative.basicweather.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.bluelampcreative.basicweather.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;
import rx.Observable;
import rx.functions.Func2;
import rx.subjects.PublishSubject;


public class LocationEntry extends LinearLayout {

    @BindView(R.id.edit_city)
    EditText cityTextEntry;

    @BindView(R.id.edit_state)
    EditText stateTextEntry;

    PublishSubject<CharSequence> citySubject = PublishSubject.create();
    PublishSubject<CharSequence> stateSubject = PublishSubject.create();

    public LocationEntry(Context context) {
        super(context);
        initialize();
    }

    public LocationEntry(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    private void initialize() {
        inflate(getContext(), R.layout.view_location_entry, this);
        ButterKnife.bind(this);
    }

    public String getCity() {
        return cityTextEntry.getText().toString();
    }

    public String getState() {
        return stateTextEntry.getText().toString();
    }

    @OnTextChanged(R.id.edit_city)
    public void onCitytextChanged(CharSequence city) {
        citySubject.onNext(city);
    }

    @OnTextChanged(R.id.edit_state)
    public void onStaeTextChanged(CharSequence state) {
        stateSubject.onNext(state);
    }

    public Observable<Boolean> subscribeToTextEntry() {
        return Observable.combineLatest(
                citySubject,
                stateSubject,
                new Func2<CharSequence, CharSequence, Boolean>() {
                    @Override
                    public Boolean call(CharSequence city, CharSequence state) {
                        return city.length() > 2 && state.length() == 2;
                    }
                }
        );
    }
}
