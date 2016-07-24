package ru.maslov.sandbox.dataLayer;

/**
 * Created by Администратор on 24.07.2016.
 */
public interface IDataManagerBlock {
    void destroyDataManagers();
    void addDataManager(IDataManager manager);
}
