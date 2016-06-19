package ru.maslov.sandbox;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.fragmentOne)protected Button fragment1;
    @Bind(R.id.fragmentTwo)protected Button fragment2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        fragment1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                TestFragmentOne testFragmentOne = new TestFragmentOne();
                transaction.add(R.id.fragmentContainer, testFragmentOne);
                transaction.commit();
            }
        });
        fragment2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                TestFragmentTwo testFragmentOne = new TestFragmentTwo();
                transaction.add(R.id.fragmentContainer, testFragmentOne);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

    }
}
