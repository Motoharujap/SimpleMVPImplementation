package ru.maslov.sandbox;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import ru.maslov.sandbox.dataLayer.GlobalDataManager;
import ru.maslov.sandbox.eventBus.LeaveStateEvent;

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

    protected void startFragmentTransaction(Fragment fragmentToShow, boolean rememberTran, boolean replacePrevFrag,
                                            int fragmentContainerId){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (replacePrevFrag) {
            transaction.replace(fragmentContainerId, fragmentToShow);
        } else {
            transaction.add(fragmentContainerId, fragmentToShow);
        }
        if (rememberTran){
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    protected void gotoActivity(Activity current, Class activityToGo){
        EventBus.getDefault().post(new LeaveStateEvent());
        Intent startActivityIntent = new Intent(current, activityToGo);
        startActivity(startActivityIntent);
    }

    protected abstract T getPresenter();

    protected abstract int getLayoutResId();
}
