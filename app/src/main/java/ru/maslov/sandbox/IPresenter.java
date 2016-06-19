package ru.maslov.sandbox;

/**
 * Created by Администратор on 19.06.2016.
 */
public interface IPresenter {
    void bindView(IView view);
    void unbindView();
}
