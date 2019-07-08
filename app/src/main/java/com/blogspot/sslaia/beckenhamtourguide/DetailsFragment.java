package com.blogspot.sslaia.beckenhamtourguide;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsFragment extends Fragment {

    private TextView mName, mPlace, mDescription;
    private ImageView mImage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Declare the Views to be changed in fragment_details.xml that displays the item details
        View rootView = inflater.inflate(R.layout.fragment_details, container, false);
        mName = rootView.findViewById(R.id.details_name);
        mPlace = rootView.findViewById(R.id.details_place);
        mDescription = rootView.findViewById(R.id.details_description);
        mImage = rootView.findViewById(R.id.details_image);

        // Extract data from the sending fragment and set the corresponding Views above
        Bundle bundle = getArguments();
        if (bundle != null) {
            mName.setText(String.valueOf(bundle.getString("name")));
            mPlace.setText(String.valueOf(bundle.getString("place")));
            mDescription.setText(String.valueOf(bundle.getString("description")));
            int bImage = bundle.getInt("image");
//          Alternative way to get the same result using Integer class (it needs to be converted to String)
//          int bImage = Integer.parseInt(bundle.get("image").toString());
            mImage.setImageResource(bImage);
        }

        return rootView;
    }
}
