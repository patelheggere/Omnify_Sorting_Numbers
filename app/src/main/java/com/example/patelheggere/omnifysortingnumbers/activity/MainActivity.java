package com.example.patelheggere.omnifysortingnumbers.activity;

import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.patelheggere.omnifysortingnumbers.R;
import com.example.patelheggere.omnifysortingnumbers.fragment.HomeFragment;
import com.example.patelheggere.omnifysortingnumbers.fragment.SortingFragment;

public class MainActivity extends AppCompatActivity implements HomeFragment.OnFragmentInteractionListener, SortingFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startingFragment();
    }

    private void startingFragment()
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container, new HomeFragment());
        fragmentTransaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
