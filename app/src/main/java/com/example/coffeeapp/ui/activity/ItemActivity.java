package com.example.coffeeapp.ui.activity;

import android.content.Intent;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.example.coffeeapp.R;
import com.example.coffeeapp.databinding.ActivityItemBinding;

public class ItemActivity extends AppCompatActivity {

    ActivityItemBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_item);

        int position = getIntent().getIntExtra("position", 0);
        String itemName = getResources().getStringArray(R.array.coffee_items_array)[position];
        TypedArray drawables = getResources().obtainTypedArray(R.array.coffee_drawables);
        int itemDrawableId = drawables.getResourceId(position, -1);
        String itemDescription = getResources().getStringArray(R.array.coffee_descriptions)[position];
        binding.photo.setImageResource(itemDrawableId);
        binding.articleBody.setText(itemDescription);
        setSupportActionBar(binding.toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(itemName);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
        drawables.recycle();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClickShare(View view) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        int position = getIntent().getIntExtra("position", 0);
        String itemName = getResources().getStringArray(R.array.coffee_items_array)[position];
        sendIntent.putExtra(Intent.EXTRA_TEXT, itemName);
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.share)));
    }
}
