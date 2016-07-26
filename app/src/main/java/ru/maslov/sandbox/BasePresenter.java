package ru.maslov.sandbox;

import android.util.Log;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;

import ru.maslov.sandbox.dataLayer.DatablockDoesNotExistException;
import ru.maslov.sandbox.dataLayer.GlobalDataManager;
import ru.maslov.sandbox.dataLayer.IDataManager;

/**
 * Created by Администратор on 19.06.2016.
 */
public abstract class BasePresenter<T extends IView>{
    private static final String TAG = BasePresenter.class.getSimpleName();
    protected WeakReference<T> mView;
    protected IDataManager mDataManager;
    public BasePresenter() {

    }

    public void bindView(T view) {
        mView = new WeakReference<>(view);
        initializeDataManager();
    }

    public void unbindView() {
        mView.clear();
        onLeaveState();
    }

    protected abstract Class getDataManagerClass();

    protected void onLeaveState(){
        try {
            GlobalDataManager.getInstance().destroyDataBlock(getDataManagerClass());
        } catch (DatablockDoesNotExistException e) {
            Log.e(TAG, "Datablock with name " + getDataManagerClass().getSimpleName() + " does not exist in global manager!");
            e.printStackTrace();
        }
    }

    private void initializeDataManager(){
        try {
            Class dataManagerClass = getDataManagerClass();
            if (dataManagerClass != null) {
                mDataManager = GlobalDataManager.getInstance().getDataManager(dataManagerClass);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
