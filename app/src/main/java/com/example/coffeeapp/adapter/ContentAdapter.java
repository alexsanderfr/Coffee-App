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
    private final ContentAdapterOnClickHandler mClickHandler;

    public interface ContentAdapterOnClickHandler{
        void onClick(int position);
    }

    public ContentAdapter(Context context, ContentAdapterOnClickHandler onClickHandler) {
        mContext = context;
        mClickHandler = onClickHandler;
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
        String itemName = mContext.getResources().getStringArray(R.array.coffee_items_array)[position];
        TypedArray drawables = mContext.getResources().obtainTypedArray(R.array.coffee_drawables);
        int itemDrawableId = drawables.getResourceId(position, -1);
        holder.itemTextView.setText(itemName);
        holder.itemImageView.setContentDescription(itemName);
        holder.itemImageView.setImageResource(itemDrawableId);
        drawables.recycle();
    }

    @Override
    public int getItemCount() {
        if (mContext != null) {
            return mContext.getResources().getStringArray(R.array.coffee_items_array).length;
        } else {
            return 0;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        final TextView itemTextView;
        final ImageView itemImageView;

        ViewHolder(View itemView) {
            super(itemView);
            itemTextView = itemView.findViewById(R.id.item_tv);
            itemImageView = itemView.findViewById(R.id.item_iv);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            mClickHandler.onClick(adapterPosition);
        }
    }
}
