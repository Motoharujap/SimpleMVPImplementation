package ru.maslov.sandbox.dataLayer;

/**
 * Created by Администратор on 24.07.2016.
 */
public class DatablockDoesNotExistException extends Exception {
    public DatablockDoesNotExistException(String detailMessage) {
        super(detailMessage);
    }
}
