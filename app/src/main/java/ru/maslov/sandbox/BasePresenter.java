package ru.maslov.sandbox;

import java.lang.ref.WeakReference;

/**
 * Created by Администратор on 19.06.2016.
 */
public abstract class BasePresenter<T extends IView>{
    protected WeakReference<T> mView;

    public void bindView(T view) {
        mView = new WeakReference<>(view);
    }

    public void unbindView() {
        mView.clear();
    }
}
