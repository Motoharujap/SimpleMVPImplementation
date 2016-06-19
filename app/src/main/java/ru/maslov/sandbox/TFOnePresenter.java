package ru.maslov.sandbox;

import android.os.AsyncTask;

/**
 * Created by Администратор on 19.06.2016.
 */
public class TFOnePresenter extends BasePresenter<ITFone> {

    public void startLoader(){
        Loader loader = new Loader();
        loader.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    class Loader extends AsyncTask<Void, String, Void>{

        @Override
        protected Void doInBackground(Void... params) {
            try {
            synchronized (this) {
                for (int i = 0; i < 1000; i++) {
                    Thread.sleep(500);
                    publishProgress("Value update: " + String.valueOf(i));
                }
            }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            if (mView.get() != null) {
                mView.get().onUpdateTextView(values[0]);
                super.onProgressUpdate(values);
            }
        }
    }
}
