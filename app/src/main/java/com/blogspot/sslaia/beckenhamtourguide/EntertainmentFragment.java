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

        String odeon = "Address: High St, Beckenham BR3 1DY\n" +
                "Phone: 0333 014 4501\n" +
                "Odeon, sometimes stylised as ODEON, is a cinema brand name operating in the United Kingdom, " +
                "Ireland and Norway, which along with UCI Cinemas and Nordic Cinema Group is part of " +
                "the Odeon Cinemas Group subsidiary of AMC Theatres. It uses the famous name of " +
                "the Odeon cinema circuit first introduced in Britain in 1930.\n" +
                "\n" +
                "The first Odeon cinema was opened by Oscar Deutsch in 1928, in Brierley Hill, " +
                "Staffordshire (now West Midlands), although initially called \"Picture House\". " +
                "The first cinema to use the Odeon brand name was Deutsch's cinema at Perry Barr, " +
                "Birmingham in 1930. Ten years later Odeon was part of the Rank Organisation " +
                "who continued their ownership of the circuit for a further sixty years. " +
                "Through a number of sales and acquisitions in the early 2000s the company was " +
                "purchased by Terra Firma, which merged Odeon and UCI Cinemas to form " +
                "Odeon UCI Cinemas Group. Most UCI cinemas then took the Odeon brand name in 2006. " +
                "Terra Firma/UCI sold the company to AMC Theatres in November 2016. Ironically, " +
                "UCI was originally formed through the merger of AMC UK and " +
                "Cinema International Corporation in 1989.\n" +
                "\n" +
                "As of 2016, Odeon is the largest cinema chain in the United Kingdom by market share " +
                "(although the Irish cinemas were also included within this figure).";
        String theSpa = "Address: 4 Bromley Road, Beckenham, BR3 5JE\n" +
                "\n" +
                "We are a social enterprise on a mission to improve wellbeing. " +
                "We help everyone get more out of life by moving more, eating better, feeling positive " +
                "and making new friends. We understand what it takes to make a positive impact on everyday " +
                "wellbeing and we are here to make it simple, easy and more fun for everyone in our " +
                "neighbourhoods to live longer, healthier and happier lives.\n" +
                "\n" +
                "From children to grandparents and everyone in between, we help people get to where " +
                "they want to be, whatever their starting point. With sports, leisure, golf and health " +
                "offerings, our range of wellbeing services ensures we have something for everyone.";
        String gambado = "Address: Natwest Sports Ground, Copers Cope Rd, Beckenham BR3 1NZ\n" +
                "Phone: 0330 555 1415\n" +
                "Gambado offers children the chance to play, learn, explore and party in some of " +
                "the most exciting indoor play venues in the world.\n" +
                "\n" +
                "Our venues have been superbly designed to cater for all ages and are packed full " +
                "of variety and innovation to ensure that no two trips are the same!\n" +
                "\n" +
                "Bring the children to Gambado and let them play in a healthy, fun and above all " +
                "safe environment whilst learning some of the most valuable development skills at the same time!";
        String pictureHouse = "Address: 242 High St, Bromley BR1 1PQ\n" +
                "0871 902 5747\n" +
                "\n" +
                "Picturehouse Cinemas is a network of cinemas in the United Kingdom, operated by " +
                "Picturehouse Cinemas Ltd and owned by Cineworld. The company runs its own film " +
                "distribution arm, Picturehouse Entertainment, which has released acclaimed films " +
                "such as David Lowery's A Ghost Story, Sally Potter's The Party and " +
                "Francis Lee's God's Own Country, Custody, Capernaum and The Wife. A previous iteration " +
                "of this distribution arm, which focused largely on alternative content, was sold in 2017 " +
                "to Howard Panter and Rosemary Squire and rebranded as Trafalgar Releasing.\n" +
                "\n" +
                "The first cinema in the chain, Phoenix Picturehouse, opened in Oxford in 1989, " +
                "but many of the others operated independently before then: the Duke of York's " +
                "Picture House in Brighton, for example, opened in 1910 and is Britain's longest continually operating cinema.";
        String theGlades = "Address: High St, Bromley BR1 1DN\n" +
                "\n" +
                "The Glades is a posh shopping centre in Bromley, a 15 minutes drive from Beckenham. " +
                "It has a total of 135 stores trading from a combined floorspace of 464,000 sq ft. " +
                "Opened as The Glades on Tuesday 22 October 1991, the centre is currently jointly " +
                "owned by Alaska Permanent Fund and the London Borough of Bromley.";

        // Create an ArrayList of famous entertainment entries
        final ArrayList<GuideData> guideData = new ArrayList<>();
        guideData.add(new GuideData("Odeon", "Beckenham", odeon, "Entertainment", R.drawable.screen_green));
        guideData.add(new GuideData("The Spa", "Beckenham", theSpa, "Entertainment", R.drawable.restaurants));
        guideData.add(new GuideData("Gambado", "Beckenham", gambado, "Entertainment", R.drawable.little_explorers));
        guideData.add(new GuideData("Picture House", "Bromley", pictureHouse, "Entertainment", R.drawable.entertainment));
        guideData.add(new GuideData("The Glades Shopping Centre", "Bromley", theGlades, "Entertainment", R.drawable.strange_bromley));

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
