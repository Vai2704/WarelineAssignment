package com.example.warelineassignment.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.warelineassignment.R;
import com.example.warelineassignment.models.ImageModel;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageViewHolder> {

    private Context context;
    private List<ImageModel> ImageList;

    public ImageAdapter(Context context, List<ImageModel> imageList) {
        this.context = context;
        ImageList = imageList;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.image_item_layout, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {

        ImageModel model = ImageList.get(position);

        Glide.with(context)
                .load(model.getMediumUrl())
                .into(holder.itemImage);


    }

    @Override
    public int getItemCount() {
        return ImageList.size();
    }
}

class ImageViewHolder extends RecyclerView.ViewHolder{

    ImageView itemImage;


    public ImageViewHolder(@NonNull View itemView) {
        super(itemView);

        itemImage = itemView.findViewById(R.id.imageViewItem);
    }
}
