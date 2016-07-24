package ru.maslov.sandbox;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by Администратор on 24.07.2016.
 */
public abstract class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    protected DrawerLayout mDrawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        Toolbar toolbar = (Toolbar) findViewById(getToolbarResId());
        setSupportActionBar(toolbar);


        mDrawerLayout = (DrawerLayout) findViewById(getDrawerResId());
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(getNavigationViewResId());
        navigationView.setNavigationItemSelectedListener(this);

        showMainFragmentIfNull();
    }

    //in order to make drawer toggle work you should call super.onBackPressed()
    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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

    //instantiate and show the main fragment only if it is null and not shown already
    protected void showMainFragmentIfNull(){
        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager.findFragmentById(getMainFragmentResId()) == null) {
            showMainFragment();
        }
    }

    protected abstract int getLayoutResId();
    protected abstract int getToolbarResId();
    protected abstract int getDrawerResId();
    protected abstract int getNavigationViewResId();
    //Every activity should have it's main fragment which represents activity's main view
    protected abstract void showMainFragment();
    protected abstract int getMainFragmentResId();
}
