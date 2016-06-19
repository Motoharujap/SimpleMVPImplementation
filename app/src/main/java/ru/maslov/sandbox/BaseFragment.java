package ru.maslov.sandbox;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by Администратор on 19.06.2016.
 */
public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements IView {
    private static final String TAG = BaseFragment.class.getSimpleName();
    protected T mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, this.getClass().getSimpleName() + " onCreateView()");
        setRetainInstance(true);
        View mainView = inflater.inflate(getLayoutResId(), container, false);
        ButterKnife.bind(this, mainView);
        if (mPresenter == null) {
            mPresenter = getPresenter();
        }
        return mainView;
    }

    @Override
    public void onDestroyView() {
        Log.d(TAG, this.getClass().getSimpleName() + " onDestroyView()");
        mPresenter.unbindView();
        super.onDestroyView();
    }

    @Override
    public void onPause() {
        Log.d(TAG, this.getClass().getSimpleName() + " onPause()");
        super.onPause();
    }

    @Override
    public void onResume() {
        mPresenter.bindView(this);
        Log.d(TAG, this.getClass().getSimpleName() + " onResume()");
        super.onResume();
    }

    protected abstract T getPresenter();

    protected abstract int getLayoutResId();
}
