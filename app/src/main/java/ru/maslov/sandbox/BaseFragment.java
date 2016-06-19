package ru.maslov.sandbox;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by Администратор on 19.06.2016.
 */
public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements IView {
    protected T mPresenter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setRetainInstance(true);
        View mainView = inflater.inflate(getLayoutResId(), container, false);
        ButterKnife.bind(this, mainView);
        if (mPresenter == null) {
            mPresenter = getPresenter();
            mPresenter.bindView(this);
        }
        return mainView;
    }

    @Override
    public void onDestroy() {
        mPresenter.unbindView();
        super.onDestroy();
    }

    protected abstract T getPresenter();

    protected abstract int getLayoutResId();
}
