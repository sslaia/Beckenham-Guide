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

        String screenGreen = "Date: 05 July 2019 - 14 July 2019\n" +
                "Location: Beckenham Green\n" +
                "\n" +
                "Beckenham Together host this years Screen on the Green...\n" +
                "\n" +
                "Beckenham Green open space Screen on the Green is set to entertain and engage the Beckenham community for two weeks.\n" +
                "\n" +
                "During the day, you will be able to soak up the sunshine while live games from the Wimbledon Championships are projected on a huge screen.\n" +
                "\n" +
                "Every Friday and Saturday evening selected movies will be screened - turning these summer evenings into memorable nights as friends and families gather under the stars.\n" +
                "\n" +
                "Screen on the Green will also feature food and drink stalls including a PIMM’S bar. Free entry for all!\n" +
                "\n" +
                "For more information: https://www.facebook.com/events/328772838034519/\n" +
                "\n" +
                "Event organiser: Beckenham Together\n" +
                "\n" +
                "Web: https://beckenhamtogether.co.uk/";

        String theHuskies = "Date: 5 July 2019\n" +
                "Location: Orpington Liberal Club, 7 Station Rd, Orpington BR6 0RZ\n" +
                "Time: 20:00 - 23:00\n" +
                "Cost: £3\n" +
                "Live music with open mic slots\n" +
                "\n" +
                "An evening of music with popular local Bluegrass and Americana virtuoso guitarist John Edwards; and impresario and bass-player David Silk.\n" +
                "\n" +
                "Guest performers very welcome. (A donation to St Christopher’s Hospice will be made from the proceeds.)\n" +
                "\n" +
                "For  more information contact Dave Silk on 01689 875683\n";

        String musicInGarden = "Date: 07 July 2019\n" +
                "Location: Garden View, 6 Edward Road, Biggin Hill, TN16 3HL\n" +
                "Time: 14:00 - 17:00\n" +
                "Cost: £5\n" +
                "Enjoy an afternoon of music in this beautiful award winning garden in Biggin Hill, in aid of the NSPCC...\n" +
                "\n" +
                "People at a garden party with music\n" +
                "Entertainment from the Bromley Philharmonic Choir, Bromley Male Voices, Frank McConell, Vocal Eclipse and crooner Pete Sinclair.\n" +
                "\n" +
                "There will also be a small art exhibition, cards and gifts, pot luck and light refreshments available.\n" +
                "\n" +
                "Event organiser: Freda Davis\n" +
                "Telephone: 07958 534074\n" +
                "Web: www.fredasgarden.co.uk";

        String churchill = "Date: 11 July 2019\n" +
                "Location: Garden View, 6 Edward Road, Biggin Hill, TN16 3HL\n" +
                "Time: 14:00 - 17:00\n" +
                "Cost: £5\n" +
                "Enjoy an afternoon of music in this beautiful award winning garden in Biggin Hill, in aid of the NSPCC...\n" +
                "\n" +
                "People at a garden party with music Entertainment from the Bromley Philharmonic Choir, Bromley Male Voices, Frank McConell, Vocal Eclipse and crooner Pete Sinclair.\n" +
                "\n" +
                "There will also be a small art exhibition, cards and gifts, pot luck and light refreshments available.\n" +
                "\n" +
                "Event organiser: Freda Davis\n" +
                "Telephone: 07958 534074\n" +
                "Web: www.fredasgarden.co.uk";

        String beckReckRock = "Date: 13 July 2019\n" +
                "Location: Croydon Road Recreation Ground, Beckenham, Kent BR3 3NR\n" +
                "Time: 13:00 - 20:00\n" +
                "Cost: Free\n" +
                "Fully inclusive family fun day.\n" +
                "\n" +
                "Entertainment, attractions, live music, food, drinks and fun. \n" +
                "\n" +
                "Early entry for people with disabilities (12 noon), toilets with hoists, adult changing benches, showers and attendants, quite and sensory zones, sunflower lanyards for \"hidden disabilities\", online Herbert Protocol and social stories, parking which can be pre-booked, good signage, buddies to provide assistance and medical support on site.\n" +
                "\n" +
                "We want everyone to be able to enjoy our events so if there is anything preventing you from joining us, please contact us and we will see if we can help.\n" +
                "\n" +
                "Website: www.becrecrocks.com\n" +
                "Telephone: 01322 291151";

        String magicMusical = "Date: 20 July 2019\n" +
                "Location: Church House Gardens, Bromley BR1 1HA\n" +
                "Time: 19:30\n" +
                "Cost: From £25\n" +
                "From West End to Broadway, all your favourite hits in one sensational night featuring orchestra, choir and singers from the West End stage...\n" +
                "\n" +
                "Enjoy the splendor of the outdoors in this award-winning setting by the lake with a night showcasing the Magic of the Musicals.\n" +
                "\n" +
                "Accessible seating availiable.\n" +
                "\n" +
                "For more information and to book tickets visit www.torchentertainment.co.uk\n" +
                "\n" +
                "Event organiser: Torch Entertainment\n";

        // Create an ArrayList of celebrities
        final ArrayList<GuideData> guideData = new ArrayList<>();

        guideData.add(new GuideData("Screen on the Green", "Beckenham", screenGreen, "Events", R.drawable.screen_green));
        guideData.add(new GuideData("Bec Rec Rocks!", "Beckenham", beckReckRock, "Events", R.drawable.bec_rocks));
        guideData.add(new GuideData("Magic of the Musicals", "Bromley", magicMusical, "Events", R.drawable.magic_musical));
        guideData.add(new GuideData("Celebrate Summer at the Churchill Theatre", "Bromley", churchill, "Events", R.drawable.beckenham));
        guideData.add(new GuideData("Music in the Garden", "Biggin Hill", musicInGarden, "Events", R.drawable.beckenham));
        guideData.add(new GuideData("The Huskies", "Orpington", theHuskies, "Events", R.drawable.beckenham));

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
