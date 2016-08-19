package ru.maslov.sandbox;

import android.util.Log;

import org.greenrobot.eventbus.Subscribe;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;

import ru.maslov.sandbox.dataLayer.DatablockDoesNotExistException;
import ru.maslov.sandbox.dataLayer.GlobalDataManager;
import ru.maslov.sandbox.dataLayer.IDataManager;
import ru.maslov.sandbox.eventBus.LeaveStateEvent;

/**
 * Created by Администратор on 19.06.2016.
 */
public abstract class BasePresenter<T extends IView>{
    private static final String TAG = BasePresenter.class.getSimpleName();
    protected WeakReference<T> mView;
    protected IDataManager mDataManager;
    public BasePresenter() {
        initializeDataManager();
    }

    public void bindView(T view) {
        mView = new WeakReference<>(view);
    }

    public void unbindView() {
        mView.clear();
    }

    protected abstract Class getDataManagerClass();

    @Subscribe
    public void onLeaveStateEvent(LeaveStateEvent event){
        onLeaveState();
    }

    protected void onLeaveState(){
        try {
            GlobalDataManager.getInstance().destroyDataBlock(getDataManagerClass());
        } catch (DatablockDoesNotExistException e) {
            String className = getDataManagerClass() == null ? BasePresenter.class.getSimpleName() : getDataManagerClass().getSimpleName();
            Log.e(TAG, "Datablock with name " + className + " does not exist in global manager!");
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
