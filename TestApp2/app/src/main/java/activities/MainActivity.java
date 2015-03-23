package activities;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;

import com.testapplication.testapp.R;

import fragments.MainFragment;


public class MainActivity extends ActionBarActivity {

    private final String FRAGMENT_TAG = "MainFragment";
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            fragment = new MainFragment();
        } else {
            fragment = getFragmentManager().findFragmentByTag(FRAGMENT_TAG);
        }

        getFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment, FRAGMENT_TAG)
                .setTransition(FragmentTransaction.TRANSIT_NONE)
                .commit();
    }









}
