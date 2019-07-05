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

public class HistoryFragment extends Fragment {

    // Global variables
    private RecyclerView mRecyclerView;
    private GuideAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<GuideData> mGuideList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recycle_list, container, false);

        String bowieBandstand = "The bandstand in Croydon Road Recreation Ground, Beckenham, " +
                "is Edwardian, built by McCallum & Hope Ltd of Glasgow.\n" +
                "\n" +
                "It is Locally Listed and has been the venue for many musical performances, including " +
                "one of Beckenham’s most famous residents, David Bowie who played on the bandstand " +
                "at the Beckenham Free Festival at the park in 1969 which was organised by himself and " +
                "The Beckenham Arts Laboratory. Recent research has identified that this is the last " +
                "marked McCallum & Hope bandstand in the country, and is therefore of high architectural value.\n" +
                "\n" +
                "The bandstand is much-loved by local residents and is a showcase for Edwardian " +
                "craftsmanship and design (Source: beckenhamheritagegroup.co.uk)";

        String croftonVilla = "Crofton Roman Villa is the only villa open to the public in Greater London. " +
                "It was inhabited from about AD 140 to 400 and was the centre of a large farming estate. " +
                "Today you can see the remains of 10 rooms protected inside a public viewing building. " +
                "Remains include tiled (tessellated) floors and the under-floor heating system (hypocaust).\n" +
                "\n" +
                "The Villa is managed on behalf of the London Borough of Bromley by " +
                "Kent Archaeological Rescue Unit (Source: bromley.gov.uk)";

        String bigginHill = "The museum showcases the remarkable stories of men and women caught up " +
                "in the Battle of Britain through the lens of four themes – Early Years, Station Life, " +
                "Community Life and Remembering. A wealth of objects including letters, photographs, " +
                "paintings, uniforms and flying jackets, a Browning machine gun, ‘scramble’ " +
                "and ‘victory’ bells, brings these personal stories to life.\n" +
                "\n" +
                "The museum is independently governed by the Biggin Hill Memorial Museum Trust, " +
                "and managed by a team of staff and volunteers (Source: bromley.gov.uk)";

        String crystalPalace = "The Crystal Palace was a cast-iron and plate-glass structure originally " +
                "built in Hyde Park, London, to house the Great Exhibition of 1851. The exhibition " +
                "took place from 1 May until 15 October 1851, and more than 14,000 exhibitors from " +
                "around the world gathered in its 990,000 square feet (92,000 m2) exhibition space " +
                "to display examples of technology developed in the Industrial Revolution. " +
                "Designed by Joseph Paxton, the Great Exhibition building was 1,851 feet (564 m) long, " +
                "with an interior height of 128 feet (39 m). It was three times the size of St Paul's Cathedral.\n" +
                "\n" +
                "The introduction of the sheet glass method into Britain by Chance Brothers in 1832 " +
                "made possible the production of large sheets of cheap but strong glass, and its use " +
                "in the Crystal Palace created a structure with the greatest area of glass ever seen " +
                "in a building. It astonished visitors with its clear walls and ceilings that " +
                "did not require interior lights.\n" +
                "\n" +
                "It has been suggested that the name of the building resulted from a piece penned by " +
                "the playwright Douglas Jerrold, who in July 1850 wrote in the satirical magazine Punch " +
                "about the forthcoming Great Exhibition, referring to a \"palace of very crystal\".\n" +
                "\n" +
                "After the exhibition, the Palace was relocated to an area of South London known as " +
                "Penge Common. It was rebuilt at the top of Penge Peak next to Sydenham Hill, " +
                "an affluent suburb of large villas. It stood there from June 1854 until its destruction " +
                "by fire in November 1936. The nearby residential area was renamed Crystal Palace " +
                "after the landmark. This included the Crystal Palace Park that surrounds the site, " +
                "home of the Crystal Palace National Sports Centre, which had previously been " +
                "a football stadium that hosted the FA Cup Final between 1895 and 1914. " +
                "Crystal Palace F.C. were founded at the site in 1905 and played at the Cup Final " +
                "venue in their early years. The park still contains Benjamin Waterhouse Hawkins's " +
                "Crystal Palace Dinosaurs which date back to 1854. (Source: Wikipedia)";

        String stGeorges = "Beckenham as a settlement dates from 862. There is rumour of an " +
                "early wooden church but it is generally accepted that there was a church in Beckenham " +
                "from before 1070. The earliest record is in the Textus Roffensis (1115 – 1125), " +
                "which is held at Rochester Cathedral. There is no information about this church.\n" +
                "\n" +
                "Building of the medieval church began in 1340. Initially the church comprised of " +
                "a chancel and nave with a square tower. There are registers of baptisms, marriages, " +
                "and burials from 1538, many of which are held at the county records office. " +
                "(Source: St. George's Parish)";

        // Create an ArrayList of celebrities
        final ArrayList<GuideData> guideData = new ArrayList<>();
        guideData.add(new GuideData("David Bowie Bandstand", "Beckenham", bowieBandstand, "Historical sites", R.drawable.bowie_bandstand));
        guideData.add(new GuideData("Crystal Palace", "Crystal Palace", crystalPalace, "Historical sites", R.drawable.crystal_palace));
        guideData.add(new GuideData("St George's Church", "Beckenham", stGeorges, "Historical sites", R.drawable.st_georges));
        guideData.add(new GuideData("Crofton Roman Villa", "Orpington", croftonVilla, "Historical sites", R.drawable.beckenham_coa));
        guideData.add(new GuideData("Biggin Hill Memorial Museum", "Biggin Hill", bigginHill, "Historical sites", R.drawable.bowie_04));

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
