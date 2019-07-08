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

public class EventsFragment extends Fragment {

    // Global variables
    private RecyclerView mRecyclerView;
    private GuideAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<GuideData> mGuideList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recycle_list, container, false);

        // Create an ArrayList of celebrities
        final ArrayList<GuideData> guideData = new ArrayList<>();

        guideData.add(new GuideData(getString(R.string.screen_green_name), getString(R.string.place_beckenham), getString(R.string.screen_green_description), getString(R.string.category_events), R.drawable.screen_green));
        guideData.add(new GuideData(getString(R.string.bec_rec_rock_name),getString(R.string.place_beckenham), getString(R.string.bec_rec_rock_description), getString(R.string.category_events), R.drawable.bec_rocks));
        guideData.add(new GuideData(getString(R.string.magic_musical_name), getString(R.string.place_bromley), getString(R.string.magic_musical_description), getString(R.string.category_events), R.drawable.magic_musical));
        guideData.add(new GuideData(getString(R.string.music_churchill_name), getString(R.string.place_bromley), getString(R.string.music_churchill_description), getString(R.string.category_events), R.drawable.beckenham));
        guideData.add(new GuideData(getString(R.string.music_in_garden_name), getString(R.string.place_bigginhill), getString(R.string.music_in_garden_description), getString(R.string.category_events), R.drawable.beckenham));
        guideData.add(new GuideData(getString(R.string.the_huskies_name), getString(R.string.place_orpington), getString(R.string.the_huskies_description), getString(R.string.category_events), R.drawable.beckenham));

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
                // Open the details page of the clicked item (name, place, description, image)
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
