package com.blogspot.sslaia.beckenhamtourguide;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class GuideAdapter extends RecyclerView.Adapter<GuideAdapter.GuideViewHolder> {
    // Global variable
    private ArrayList<GuideData> mGuideList;
    private OnItemClickListener mListener;

    // Create an interface for registering the clicked item
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public static class GuideViewHolder extends RecyclerView.ViewHolder {
        // Variables for the views in list_item.xml
        // mImageView for the image, mNameView for the name and mPlaceVidw for the place
        public ImageView mImageView;
        public TextView mNameView;
        public TextView mPlaceView;

        public GuideViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            // We assign the value to the previous variables for list_item.xml
            mImageView = itemView.findViewById(R.id.image);
            mNameView = itemView.findViewById(R.id.name);
            mPlaceView = itemView.findViewById(R.id.place);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    // Getting the data out of ArrayList into the GuideAdapter
    // by pasting them into constructor of GuideAdapter
    public GuideAdapter(ArrayList<GuideData> guideList) {
        mGuideList = guideList;
    }

    @NonNull
    @Override
    public GuideViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflate the list_item layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        GuideViewHolder guideViewHolder = new GuideViewHolder(view, mListener);
        return guideViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GuideViewHolder guideViewHolder, int position) {
        // Now the data from ArrayList are pasted into ViewHolder
        // First get the position of the view and retrieve the corresponding item from the array
        // through getter method (image, name, place)
        GuideData currentItem = mGuideList.get(position);
        guideViewHolder.mImageView.setImageResource(currentItem.getImageResource());
        guideViewHolder.mNameView.setText(currentItem.getName());
        guideViewHolder.mPlaceView.setText(currentItem.getPlace());
    }

    @Override
    public int getItemCount() {
        return mGuideList.size();
    }
}
