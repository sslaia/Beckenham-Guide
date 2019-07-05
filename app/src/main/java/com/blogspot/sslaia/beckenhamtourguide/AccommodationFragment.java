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

public class AccommodationFragment extends Fragment {

    // Global variables
    private RecyclerView mRecyclerView;
    private GuideAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<GuideData> mGuideList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recycle_list, container, false);

        String beckenhamPark = "Address: 10 Copers Cope Rd, Beckenham BR3 1NB\n" +
                "Phone: 020 8663 9199\n" +
                "\n" +
                "Beckenham Park Hotel is set in the heart of Beckenham Town Centre on the glories " +
                "tree lined historical copers cope road. The building has retained many of " +
                "its characters since when it was originally built in late 18th century. " +
                "Jones family has established this unique privately-owned hotel some 50 years ago. " +
                "The hotel almost last one year has spent upgrading and reinstating its facilities " +
                "to provide warm and welcoming family atmosphere for clients to relax and enjoy their stay.\n" +
                "\n" +
                "The current owners have spent a lot of money and a lot of hard work to provide " +
                "you exquisite relaxing atmosphere and keeping this period buildings original features.";

        String bromleyCourt = "Address: Bromley Hill, Bromley BR1 4JD\n" +
                "Phone: 020 8461 8600\n" +
                "\n" +
                "Ideally located, with a bird’s eye view of London and the Kent countryside, " +
                "The Bromley Court is the largest and most established hotel in the area.\n" +
                "\n" +
                "We are proud of our rich 200 year history and years of experience offering " +
                "comfortable surroundings, superb food and personal service to all our guests " +
                "whether they are holiday makers, business travellers or are visiting us for a special occasion.\n" +
                "\n" +
                "A warm welcome awaits you in elegant surroundings, including two acres " +
                "of beautifully landscaped gardens.\n" +
                "Begin your stay with a relaxing drink in our Garden Bar and then enjoy our " +
                "hospitality with a light snack or dinner in our orangery-style Garden Restaurant.\n";

        String innkeepers = "Address: Toby Carvery, Eden Park, 422 Upper Elmers End Rd, Beckenham BR3 3HQ\n" +
                "Phone: 020 8650 2233\n" +
                "\n" +
                "Comfort, character and the warmest of welcomes.\n" +
                "Innkeeper’s Lodge offer great hotels for those looking for a truly individual stay. " +
                "We have outstanding locations, characterful rooms and contemporary finishes.\n" +
                "\n" +
                "All of our sites are paired with a popular pub or award-winning restaurant, " +
                "so you can dine, relax, and enjoy the finest hospitality. Choose from convenient " +
                "Birmingham locations, to cosy country pubs in the Peak District. " +
                "But wherever you’re travelling to, you’ll find many of our hotels are situated in outstanding locations.\n" +
                "\n" +
                "All creature comforts and modern facilities such as comfortable Hypnos beds, " +
                "Freeview TVs, free Wi-Fi and parking, an en suite bathroom with " +
                "powerful shower and a continental breakfast are included in your price.";

        // Create an ArrayList of celebrities
        final ArrayList<GuideData> guideData = new ArrayList<>();
        guideData.add(new GuideData("Beckenham Park Hotel", "Beckenham", beckenhamPark, "Accommodation", R.drawable.accommodation));
        guideData.add(new GuideData("Bromley Court Hotel", "Beckenham", bromleyCourt, "Accommodation", R.drawable.hotel1));
        guideData.add(new GuideData("Innkeeper's Lodge", "Beckenham", innkeepers, "Accommodation", R.drawable.hotel2));

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
