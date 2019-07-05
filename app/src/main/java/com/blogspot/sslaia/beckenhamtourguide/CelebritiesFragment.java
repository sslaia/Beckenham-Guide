package com.blogspot.sslaia.beckenhamtourguide;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

public class CelebritiesFragment extends Fragment {

    // Global variables
    private RecyclerView mRecyclerView;
    private GuideAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<GuideData> mGuideList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recycle_list, container, false);

        String darwin = "Obviously the most famous person in this area." +
                "He was born in 1809 in West Midlands, but spent most of his life at Down House, " +
                "20 min auto journey from Beckenham. He is most famous for being a biologist and " +
                "scientist, developing the theory of natural selection, which he explored in " +
                "his groundbreaking book ‘On the Origin of Species’. It was here at Down House " +
                "that he wrote that famous book and built his greenhouse " +
                "where he conducted scientific experiments." +
                "\n" +
                "Down House is a beautiful place to visit, with lovely gardens and lots of rooms " +
                "to explore – you can even stand in the very same study " +
                "where Darwin wrote \'On the Origin of Species\'.";

        String bowie = "He was in Brixton but lived in Plaistow Grove, Bromley, for most of his childhood. " +
                "About 11 min auto journey from Beckenham. He went to Bromley Technical High School " +
                "in 1958, which we all now know as Ravens Wood. " +
                "Bowie’s musical career was centred around Bromley and Beckenham, " +
                "and he played gigs at various places including Chislehurst Caves, " +
                "the Three Tuns pub in Beckenham (now Zizzi) and the Bromley Court Hotel." +
                "\n" +
                "BTW there is an appeal going on to save a piece of Bowie history, i.e. the Bowie Bandstand Appeal. " +
                "It aims to restore the bandstand where Bowie played at Croydon Road recreation ground.";

        String blyton = "Children all over the world loved stories written by Enid Blyton. " +
                "She lived in Shortlands between 1926 and 1929, and there is now a blue plaque " +
                "located at 83 Shortlands Road in memory of her. That's only 5 minutes journey " +
                "from Beckenham High Street. Among her world bestselling books: The Secret Seven, " +
                "The Famous Five and The Faraway Tree.";

        String wells = "If you have read the science fiction book The Time Machine and The War of the Worlds, " +
                "which likely you have, then H.G. Wells is familiar author name to you. " +
                "He was born in the town nearby Bromley (10 minutes journey from Beckenham) " +
                "on the 21st September 1866. He was nominated for the Nobel Prize in Literature four times.\n" +
                "\n" +
                "In front of the now Primark you can see a blue plaque to commemorate his birthplace.";

        // Create an ArrayList of celebrities
        final ArrayList<GuideData> guideData = new ArrayList<>();
        guideData.add(new GuideData("Charles Darwin", "Down House", darwin, "Famous residents", R.drawable.charles_darwin));
        guideData.add(new GuideData("David Bowie", "Bromley", bowie, "Famous residents", R.drawable.david_bowie));
        guideData.add(new GuideData("Enid Blyton", "Shortlands", blyton, "Famous residents", R.drawable.enid_blyton));
        guideData.add(new GuideData("H. G. Wells", "Bromley", wells, "Famous residents", R.drawable.h_g_wells));

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
