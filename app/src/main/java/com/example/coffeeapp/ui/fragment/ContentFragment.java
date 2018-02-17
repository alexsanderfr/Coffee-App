package com.example.coffeeapp.ui.fragment;

import android.content.Intent;
import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.coffeeapp.R;
import com.example.coffeeapp.adapter.ContentAdapter;
import com.example.coffeeapp.databinding.FragmentContentBinding;
import com.example.coffeeapp.ui.activity.ItemActivity;

public class ContentFragment extends Fragment implements ContentAdapter.ContentAdapterOnClickHandler {

    FragmentContentBinding binding;
    GridLayoutManager mLayoutManager;
    boolean isTablet;

    public ContentFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_content, container, false);
        binding.contentRv.setHasFixedSize(true);
        isTablet = getResources().getBoolean(R.bool.isTablet);
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE && !isTablet) {
            mLayoutManager = new GridLayoutManager(getActivity(), 3);
        } else {
            mLayoutManager = new GridLayoutManager(getActivity(), 2);
        }
        binding.contentRv.setLayoutManager(mLayoutManager);
        ContentAdapter contentAdapter = new ContentAdapter(getActivity(), this);
        binding.contentRv.setAdapter(contentAdapter);
        return binding.getRoot();
    }

    @Override
    public void onClick(int position) {
        Intent intent = new Intent(getActivity(), ItemActivity.class);
        intent.putExtra("position", position);
        if (isTablet) {
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            Fragment fragment = new ItemFragment();
            Bundle args = new Bundle();
            args.putInt("position", position);
            fragment.setArguments(args);
            fragmentManager.beginTransaction().replace(R.id.item_frame, fragment).commit();
        } else {
            getActivity().startActivity(intent);
        }
    }
}
