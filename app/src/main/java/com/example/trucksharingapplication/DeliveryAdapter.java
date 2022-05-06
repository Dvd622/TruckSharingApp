package com.example.trucksharingapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DeliveryAdapter extends RecyclerView.Adapter<DeliveryAdapter.DeliveryViewHolder> {
    private Context context;
    private List<Delivery> deliveryList;
    private OnDeliveryClickListener listener;

    public DeliveryAdapter(Context context, List<Delivery> deliveryList, OnDeliveryClickListener clickListener) {
        this.context = context;
        this.deliveryList = deliveryList;
        this.listener = clickListener;
    }

    @NonNull
    @Override
    public DeliveryAdapter.DeliveryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.delivery, parent, false);
        return new DeliveryViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull DeliveryAdapter.DeliveryViewHolder holder, int position) {
        holder.titleTextView.setText(deliveryList.get(position).getGoodType());
        String descriptionText = deliveryList.get(position).getSender() + " sending " + deliveryList.get(position).getGoodType();
        holder.descriptionTextView.setText(descriptionText);
    }

    @Override
    public int getItemCount() {
        return deliveryList.size();
    }

    public class DeliveryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView deliveryImageView;
        TextView titleTextView, descriptionTextView;
        ImageButton shareImageButton;
        public OnDeliveryClickListener onDeliveryClickListener;

        public DeliveryViewHolder(@NonNull View itemView, OnDeliveryClickListener onDeliveryClickListener) {
            super(itemView);
            deliveryImageView = itemView.findViewById(R.id.deliveryImageView);
            titleTextView = itemView.findViewById(R.id.deliveryTitleTextView);
            descriptionTextView = itemView.findViewById(R.id.deliveryDescriptionTextView);
            shareImageButton = itemView.findViewById(R.id.deliveryShareImageButton);

            this.onDeliveryClickListener = onDeliveryClickListener;
            itemView.setOnClickListener(this);

            shareImageButton.setOnClickListener(view -> {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, titleTextView.getText().toString() + "\n" + descriptionTextView.getText().toString());
                sendIntent.setType("text/plain");
                Intent shareIntent = Intent.createChooser(sendIntent, null);
                context.startActivity(shareIntent);
            });
        }

        @Override
        public void onClick(View view) {
            onDeliveryClickListener.onDeliveryClick(getAdapterPosition());
        }
    }

    public interface OnDeliveryClickListener {
        void onDeliveryClick (int position);
    }

}
