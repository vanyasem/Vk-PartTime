package ru.semkin.ivan.parttime.api.request;

/**
 * Created by Ivan Semkin on 5/17/18
 */
public interface GenericCallback<E> {
    void onFinished(E item);
}
