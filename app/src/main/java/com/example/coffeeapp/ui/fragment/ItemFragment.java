package com.example.coffeeapp.ui.fragment;

import android.content.Intent;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.coffeeapp.R;
import com.example.coffeeapp.databinding.FragmentItemBinding;

public class ItemFragment extends Fragment {

    FragmentItemBinding binding;
    private int position;
    public ItemFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            position = getArguments().getInt("position");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_item, container, false);
        String itemName = getResources().getStringArray(R.array.coffee_items_array)[position];
        TypedArray drawables = getResources().obtainTypedArray(R.array.coffee_drawables);
        int itemDrawableId = drawables.getResourceId(position, -1);
        String itemDescription = getResources().getStringArray(R.array.coffee_descriptions)[position];
        binding.fragPhotoIv.setImageResource(itemDrawableId);
        binding.fragBodyTv.setText(itemDescription);
        binding.fragTitleTv.setText(itemName);
        binding.fragFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                String itemName = binding.fragTitleTv.getText().toString();
                sendIntent.putExtra(Intent.EXTRA_TEXT, itemName);
                sendIntent.setType("text/plain");
                getActivity().startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.share)));
            }
        });
        drawables.recycle();
        return binding.getRoot();
    }
}
