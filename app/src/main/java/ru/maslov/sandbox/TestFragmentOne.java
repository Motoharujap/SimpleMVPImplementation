package ru.maslov.sandbox;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import butterknife.Bind;

/**
 * Created by Администратор on 19.06.2016.
 */
public class TestFragmentOne extends BaseFragment<TFOnePresenter> implements ITFone{
    @Bind(R.id.text)protected TextView resultDisplay;
    @Bind(R.id.button)protected Button loadDataButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View mainView = super.onCreateView(inflater, container, savedInstanceState);
        loadDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.startLoader();
            }
        });
        return mainView;
    }

    @Override
    protected TFOnePresenter getPresenter() {
        return new TFOnePresenter();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.testfragmentone;
    }

    @Override
    public void onUpdateTextView(String value) {
        resultDisplay.setText(value);
    }
}
