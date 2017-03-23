package com.bluelampcreative.basicweather.core;

import com.bluelampcreative.basicweather.utility.SmartNull;

import rx.subscriptions.CompositeSubscription;

public abstract class BasePresenter<T> {

    private final Class<T> viewType;
    private final CompositeSubscription subscriptions = new CompositeSubscription();
    private T view;

    public BasePresenter(Class<T> viewType) {
        this.viewType = viewType;
        view = SmartNull.create(viewType);
    }

    public final void registerView(T view) {
        if (view == null) {
            throw new IllegalArgumentException("View must not be null");
        }
        this.view = view;
        onViewRegistered();
    }

    protected void onViewRegistered() {

    }

    public final void unregisterView() {
        this.view = SmartNull.create(viewType);
        clearSubscriptions();
        onViewUnregistered();
    }

    public abstract void updateView();

    public T getView() {
        return view;
    }

    protected void onViewUnregistered() {
        subscriptions.clear();
    }

    protected void clearSubscriptions() {
    }
}
