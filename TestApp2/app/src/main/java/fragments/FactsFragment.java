package fragments;


import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;
import com.testapplication.testapp.R;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import activities.BaseActivity;
import adapters.FactsAdapter;
import model.FactItem;
import model.Facts;
import utils.AppUtils;

/**
 * Created by SumitBhatia on 22/03/15.
 */
public class FactsFragment extends Fragment {

    private ListView factsList;
    private FactsAdapter adapter;
    private List<FactItem> factCollection = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;
    private HttpURLConnection urlConnection = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        factsList = (ListView) view.findViewById(R.id.facts_list);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchFeed();
            }
        });
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        fetchFeed();
        return view;
    }

    private void fetchFeed(){
        if (AppUtils.isNetworkAvailable(getActivity())) {
            new FetchFactsTask().execute();
        } else {
            ((BaseActivity) getActivity()).showAlertDialog(getActivity(), getActivity().getResources().getString(R.string.error),
                    getActivity().getResources().getString(R.string.network_not_available), true);
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        adapter = new FactsAdapter(getActivity(), factCollection, R.layout.list_item);
        factsList.setAdapter(adapter);
    }

    private class FetchFactsTask extends AsyncTask<String, Void, Facts> {
        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ((BaseActivity) getActivity()).showProgressDialog(getActivity(), getActivity().getResources().getString(R.string.note),
                    getActivity().getResources().getString(R.string.please_wait), false, false);
            swipeRefreshLayout.setRefreshing(false);// we can set this to true if we want the default refresh indicator instead of the progressDialog
        }

        @Override
        protected Facts doInBackground(String... params) {
            return fetchFacts();
        }

        @Override
        protected void onPostExecute(Facts factsResponse) {
            if (factsResponse != null) {
                ((BaseActivity) getActivity()).setActionBarTitle(getActivity(), factsResponse.title);
            }
            if (factsResponse != null) {
                factCollection.clear();
                factCollection.addAll(factsResponse.factItemList);
                adapter.notifyDataSetChanged();
            }
            ((BaseActivity) getActivity()).hideProgressDialog();

        }
    }

    private Facts fetchFacts() {
        try {
            InputStream source = fetchStream(AppUtils.URL.FACTS_URL.getURL());
            Gson gson = new Gson();
            Reader reader = new InputStreamReader(source);
            Facts facts = gson.fromJson(reader, Facts.class);
            return facts;
        } finally {
            urlConnection.disconnect();
        }
    }

    private InputStream fetchStream(String url) {
        try {
            urlConnection = (HttpURLConnection) (new URL(url)).openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            return in;
        } catch (IOException ex) {
            return null;
        }
    }
}
