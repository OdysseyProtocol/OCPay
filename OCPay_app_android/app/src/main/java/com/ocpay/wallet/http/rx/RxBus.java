package com.ocpay.wallet.http.rx;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class RxBus {


    private static volatile RxBus instance;
    private final Subject<Object> mBus;

    private RxBus() {
        mBus = PublishSubject.create().toSerialized();
    }


    public static RxBus getInstance() {
        if (instance == null) {
            synchronized (RxBus.class) {
                if (instance == null) {
                    instance = new RxBus();
                }
            }
        }
        return instance;
    }


    public void post(Object o) {
        mBus.onNext(o);
    }

    public void post(int code, Object obj) {
        mBus.onNext(new RxBusBaseMessage(code, obj));
    }


    public boolean hasObservers() {
        return mBus.hasObservers();
    }

    public <T> Observable<T> toObservable(final int code, final Class<T> eventType) {
        return mBus.ofType(RxBusBaseMessage.class)
                .filter(new Predicate<RxBusBaseMessage>() {
                    @Override
                    public boolean test(RxBusBaseMessage rxBusBaseMessage) throws Exception {
                        return rxBusBaseMessage.getCode() == code;
                    }
                }).map(new Function<RxBusBaseMessage, Object>() {
                    @Override
                    public Object apply(RxBusBaseMessage rxBusBaseMessage) throws Exception {
                        return rxBusBaseMessage.getObject();
                    }
                }).cast(eventType);
    }


    public <T> Observable<T> toObservable(Class<T> eventType) {
        return mBus.ofType(eventType);
    }
}