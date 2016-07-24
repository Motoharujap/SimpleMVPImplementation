package ru.maslov.sandbox;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;

import ru.maslov.sandbox.dataLayer.GlobalDataManager;
import ru.maslov.sandbox.dataLayer.IDataManager;

/**
 * Created by Администратор on 19.06.2016.
 */
public abstract class BasePresenter<T extends IView>{
    protected WeakReference<T> mView;
    protected IDataManager mDataManager;
    public BasePresenter() {
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

    public void bindView(T view) {
        mView = new WeakReference<>(view);
    }

    public void unbindView() {
        mView.clear();
    }

    protected abstract Class getDataManagerClass();
}
