package ru.maslov.sandbox;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if (manager.findFragmentById(R.id.fragmentContainer) == null) {
            TestFragmentOne testFragmentOne = new TestFragmentOne();
            transaction.add(R.id.fragmentContainer, testFragmentOne);
            transaction.commit();
        }
    }
}
