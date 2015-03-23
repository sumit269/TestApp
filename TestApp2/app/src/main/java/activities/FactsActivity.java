package activities;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import com.testapplication.testapp.R;

import fragments.FactsFragment;

public class FactsActivity extends BaseActivity {

    private final String FRAGMENT_TAG = "MainFragment";
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            fragment = new FactsFragment();
        } else {
            fragment = getFragmentManager().findFragmentByTag(FRAGMENT_TAG);
        }

        getFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment, FRAGMENT_TAG)
                .setTransition(FragmentTransaction.TRANSIT_NONE)
                .commit();
    }

}
