package com.blogspot.sslaia.beckenhamtourguide;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class EntertainmentFragment extends Fragment {

    // Global variables
    private RecyclerView mRecyclerView;
    private GuideAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<GuideData> mGuideList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recycle_list, container, false);

        // Create an ArrayList of famous entertainment entries
        final ArrayList<GuideData> guideData = new ArrayList<>();
        guideData.add(new GuideData(getString(R.string.odeon_name), getString(R.string.place_beckenham), getString(R.string.odeon_description), getString(R.string.category_entertainment), R.drawable.screen_green));
        guideData.add(new GuideData(getString(R.string.the_spa_name), getString(R.string.place_beckenham), getString(R.string.the_spa_description), getString(R.string.category_entertainment), R.drawable.restaurants));
        guideData.add(new GuideData(getString(R.string.gambado_name), getString(R.string.place_beckenham), getString(R.string.gambado_description), getString(R.string.category_entertainment), R.drawable.little_explorers));
        guideData.add(new GuideData(getString(R.string.picture_house_name), getString(R.string.place_bromley), getString(R.string.picture_house_description), getString(R.string.category_entertainment), R.drawable.entertainment));
        guideData.add(new GuideData(getString(R.string.the_glades_name), getString(R.string.place_bromley), getString(R.string.the_glades_description), getString(R.string.entertainment), R.drawable.strange_bromley));

        // Now push the list to the recyclerview
        mRecyclerView = rootView.findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mAdapter = new GuideAdapter(guideData);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        // Set onItemClickListener for when users click one of the item to get more details
        mAdapter.setOnItemClickListener(new GuideAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // Open the details page of the clicked item
                String mName = guideData.get(position).getName();
                String mDescription = guideData.get(position).getDescription();
                String mPlace = guideData.get(position).getPlace();
                int mImage = guideData.get(position).getImageResource();

                // Send the request to show the detail page fragment (detailsFragment)
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                DetailsFragment detailsFragment = new DetailsFragment();

                Bundle bundle = new Bundle();
                bundle.putString(getString(R.string.name), mName);
                bundle.putString(getString(R.string.place), mPlace);
                bundle.putString(getString(R.string.description), mDescription);
                bundle.putInt(getString(R.string.image), mImage);
                detailsFragment.setArguments(bundle);
                transaction.replace(R.id.fragment_container, detailsFragment);
                transaction.addToBackStack(null).commit();
            }
        });

        return rootView;
    }
}
