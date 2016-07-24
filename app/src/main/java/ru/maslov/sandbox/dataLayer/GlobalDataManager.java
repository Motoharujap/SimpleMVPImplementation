package ru.maslov.sandbox.dataLayer;

import android.support.annotation.Nullable;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Администратор on 24.07.2016.
 */
public class GlobalDataManager {
    private static final String TAG = GlobalDataManager.class.getSimpleName();
    private static GlobalDataManager instance;
    private HashMap<Class, IDataManager> map = new HashMap<>();
    private HashMap<Class, DataManagerBlock> dataManagerBlocks = new HashMap<>();

    private GlobalDataManager(){
    }

    public static GlobalDataManager getInstance(){
        if (instance == null){
            instance = new GlobalDataManager();
        }
        return instance;
    }

    public void createDataBlock(IDataManager manager){
        DataManagerBlock block = new DataManagerBlock(manager);
        dataManagerBlocks.put(manager.getClass(), block);
    }

    @Nullable
    public IDataManager getDataManager(Class clazz) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        for (Map.Entry<Class, DataManagerBlock> block : dataManagerBlocks.entrySet()){
            IDataManager dataManager = block.getValue().getDataManager(clazz);
            if (dataManager != null){
                return dataManager;
            }
        }
        IDataManager newDataManager = (IDataManager) clazz.getConstructor(null).newInstance(null);
        if (newDataManager != null){
            createDataBlock(newDataManager);
        }
        return newDataManager;
    }
    /**
     *
     * @param manager a manager to add to an existing datablock
     * @param datablockClass an existing datablock class
     */
    public void addToExistingDatablock(IDataManager manager, Class datablockClass) throws DatablockDoesNotExistException {
        DataManagerBlock block = dataManagerBlocks.get(datablockClass);
        checkBlockNotNull(block, datablockClass);
        block.addToDatablock(manager);
    }

    /**
     * Destroys the datablock to free memory
     * @param clazz key to find the datablock to destroy
     */
    public void destroyDataBlock(Class clazz) throws DatablockDoesNotExistException {
        DataManagerBlock block = dataManagerBlocks.remove(clazz);
        checkBlockNotNull(block, clazz);
    }

    private void checkBlockNotNull(DataManagerBlock block, Class clazz) throws DatablockDoesNotExistException {
        if (block == null){
            throw new DatablockDoesNotExistException("Requested datablock with name " + clazz.getSimpleName() + " was not found." +
                    "It was either not added or is destroyed.");
        }
    }

    private class DataManagerBlock {
        private HashMap<Class, IDataManager> managersInBlock = new HashMap<>();

        public DataManagerBlock(IDataManager manager){
            addToDatablock(manager);
        }

        protected void addToDatablock(IDataManager manager){
            managersInBlock.put(manager.getClass(), manager);
        }

        protected IDataManager getDataManager(Class clazz){
            return managersInBlock.get(clazz);
        }
    }
}
