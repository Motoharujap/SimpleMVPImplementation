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
    protected NavigationView mNavigationView;
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

        mNavigationView = (NavigationView) findViewById(getNavigationViewResId());
        mNavigationView.setNavigationItemSelectedListener(this);

        showMainFragment();
    }

    //in order to make drawer toggle work you should call super.onBackPressed()
    @Override
    public void onBackPressed() {
        int size = mNavigationView.getMenu().size();
        for (int i = 0; i < size; i++) {
            mNavigationView.getMenu().getItem(i).setChecked(false);
        }
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    protected void startFragmentTransaction(Fragment fragmentToShow, boolean rememberTran, boolean replacePrevFrag,
                                            int fragmentContainerId){
        FragmentManager fragmentManager = getFragmentManager();
        Fragment currentlyActiveFragment = fragmentManager.findFragmentById(fragmentContainerId);
        if (currentlyActiveFragment != null && currentlyActiveFragment.getClass().equals(fragmentToShow.getClass())){
            return;
        }
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

    protected abstract int getLayoutResId();
    protected abstract int getToolbarResId();
    protected abstract int getDrawerResId();
    protected abstract int getNavigationViewResId();
    //Every activity should have it's main fragment which represents activity's main view
    protected abstract void showMainFragment();
    protected abstract int getMainFragmentResId();
}
