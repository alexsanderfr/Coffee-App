package com.example.coffeeapp.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.coffeeapp.R;

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ViewHolder> {
    private Context mContext;

    public ContentAdapter(Context context) {
        mContext = context;
    }

    @Override
    public ContentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        int layoutIdForListItem = R.layout.content_list_item;
        View view = inflater.inflate(layoutIdForListItem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContentAdapter.ViewHolder holder, int position) {
        String itemName = mContext.getResources().getStringArray(R.array.coffe_items_array)[position];
        TypedArray drawables = mContext.getResources().obtainTypedArray(R.array.coffe_drawables);
        int itemDrawableId = drawables.getResourceId(position, -1);
        holder.itemTextView.setText(itemName);
        holder.itemImageView.setContentDescription(itemName);
        holder.itemImageView.setImageResource(itemDrawableId);
        drawables.recycle();
    }

    @Override
    public int getItemCount() {
        if (mContext != null) {
            return mContext.getResources().getStringArray(R.array.coffe_items_array).length;
        } else {
            return 0;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView itemTextView;
        final ImageView itemImageView;

        ViewHolder(View itemView) {
            super(itemView);
            itemTextView = itemView.findViewById(R.id.item_tv);
            itemImageView = itemView.findViewById(R.id.item_iv);
        }
    }
}
