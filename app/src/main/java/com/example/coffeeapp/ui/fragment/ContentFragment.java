package com.example.coffeeapp.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.coffeeapp.R;
import com.example.coffeeapp.adapter.ContentAdapter;
import com.example.coffeeapp.databinding.FragmentContentBinding;

public class ContentFragment extends Fragment {

    FragmentContentBinding binding;

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
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        binding.contentRv.setLayoutManager(gridLayoutManager);
        ContentAdapter contentAdapter = new ContentAdapter(getActivity());
        binding.contentRv.setAdapter(contentAdapter);
        return binding.getRoot();
    }
}
