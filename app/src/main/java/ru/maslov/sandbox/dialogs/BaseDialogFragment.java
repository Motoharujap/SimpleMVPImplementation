package ru.maslov.sandbox.dialogs;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import ru.maslov.sandbox.BasePresenter;
import ru.maslov.sandbox.IView;

/**
 * Created by Администратор on 28.07.2016.
 */
public abstract class BaseDialogFragment<T extends BasePresenter> extends DialogFragment implements IView {
    protected T mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mPresenter = getPresenter();
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onResume() {
        mPresenter.bindView(this);
        super.onResume();
    }
    @Override
    public void onPause() {
        mPresenter.unbindView();
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    protected abstract T getPresenter();
    protected abstract int getLayoutResId();
}
