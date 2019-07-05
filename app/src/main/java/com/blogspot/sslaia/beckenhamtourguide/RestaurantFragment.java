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

public class RestaurantFragment extends Fragment {

    // Global variables
    private RecyclerView mRecyclerView;
    private GuideAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<GuideData> mGuideList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recycle_list, container, false);

        String pierluigi = "Beautiful and Authentic Italian Food\n" +
                "\n" +
                "Address: 86-90 High St, Beckenham BR3 1ED\n" +
                "Phone: 020 8663 3387\n" +
                "\n" +
                "Since 1994, Pierluigi’s has been serving the finest Italian cuisine from the heart " +
                "of Beckenham. Born out of an Italian families love of authentic home cooking, " +
                "the restaurant has evolved to offer exquisite, freshly prepared dishes inspired " +
                "by traditional and modern Italy.";
        String agora = "Address: 66 High St, Beckenham BR3 1ED\n" +
                "Phone: 020 8650 0511\n" +
                "Mediterranean restauran in the heart of Beckenham\n" +
                "\n" +
                "Situated in the heart of Beckenham High Street, we bring good, Greek & Mediterranean " +
                "food to your table, with a relaxed twist on sophisticated dining.  " +
                "We offer a tranquil dining experience all year around and the perfect setting " +
                "for any occasion. Enjoy our all menus and premium cocktails at your leisure whether " +
                "you are a local resident, work close by or are simply visiting the area." +
                "Experience the best of Greek & Mediterranean food in a beautiful setting.";
        String bocca = "Modern Italian Restaurant & Cocktail Bar\n" +
                "\n" +
                "Address: 72-74 High St, Beckenham BR3 1ED\n" +
                "Phone: 020 8658 9990\n" +
                "\n" +
                "Set in the heart of Beckenham, Bocca Social offers an Italian inspired seasonal " +
                "menu and expertly crafted cocktails.\n" +
                "\n" +
                "Our starters ‘Cichetti’ are designed for sharing and our main courses are a combination " +
                "of traditional Italian cooking mixed in with a few favourites of ours. " +
                "Paired with our carefully sourced wines, signature cocktails and expansive spirit list " +
                "you will always find something to make your ‘Bocca’ water";
        String delinene = "Address: 52-54 High St, Beckenham BR3 1AY\n" +
                "Phone: 020 8289 7307\n" +
                "\n" +
                "The only child friendly Café & Bistro in Beckenham with dedicated kids play area. " +
                "It is the ultimate dream come true for parents and children. Uniquely decorated " +
                "with hand crafted pieces that add to the family friendly atmosphere of our café. " +
                "pen 8am to 8pm, now you don’t have to worry about breakfast, lunch and dinner. " +
                "Looking to have a party, we also offer private space for hire and " +
                "a beautiful garden in summer. Come in, take a seat and enjoy!\n";

        // Create an ArrayList of celebrities
        final ArrayList<GuideData> guideData = new ArrayList<>();
        guideData.add(new GuideData("Pierluigi", "Beckenham", pierluigi, "Restaurants and Cafes", R.drawable.restaurants));
        guideData.add(new GuideData("Agora", "Beckenham", agora, "Restaurants and Cafes", R.drawable.restaurant2));
        guideData.add(new GuideData("Bocca Social", "Beckenham", bocca, "Restaurants and Cafes", R.drawable.profile_image));
        guideData.add(new GuideData("Deli Nene", "Beckenham", delinene, "Restaurants and Cafes", R.drawable.cafe));

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
