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

        String screen_green = getString(R.string.screen_green);
        String the_huskies = getString(R.string.the_huskies);
        String music_in_garden = getString(R.string.music_in_garden);
        String music_churchill = getString(R.string.music_churchill);
        String bec_rec_rock = getString(R.string.bec_rec_rock);
        String magic_musical = getString(R.string.magic_musical);

        // Create an ArrayList of celebrities
        final ArrayList<GuideData> guideData = new ArrayList<>();

        guideData.add(new GuideData("Screen on the Green", "Beckenham", screen_green, "Events", R.drawable.screen_green));
        guideData.add(new GuideData("Bec Rec Rocks!", "Beckenham", bec_rec_rock, "Events", R.drawable.bec_rocks));
        guideData.add(new GuideData("Magic of the Musicals", "Bromley", magic_musical, "Events", R.drawable.magic_musical));
        guideData.add(new GuideData("Celebrate Summer at the Churchill Theatre", "Bromley", music_churchill, "Events", R.drawable.beckenham));
        guideData.add(new GuideData("Music in the Garden", "Biggin Hill", music_in_garden, "Events", R.drawable.beckenham));
        guideData.add(new GuideData("The Huskies", "Orpington", the_huskies, "Events", R.drawable.beckenham));

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
                bundle.putString("name", mName);
                bundle.putString("place", mPlace);
                bundle.putString("description", mDescription);
                bundle.putInt("image", mImage);
                detailsFragment.setArguments(bundle);
                transaction.replace(R.id.fragment_container, detailsFragment);
                transaction.addToBackStack(null).commit();
            }
        });

        return rootView;
    }
}
