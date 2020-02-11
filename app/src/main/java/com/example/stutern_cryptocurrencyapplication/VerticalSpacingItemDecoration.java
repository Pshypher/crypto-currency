package com.example.stutern_cryptocurrencyapplication;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class VerticalSpacingItemDecoration extends RecyclerView.ItemDecoration {

    private int mVerticalSpaceHeight;

    public VerticalSpacingItemDecoration(int height) {
        mVerticalSpaceHeight = height;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view,
                               @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.bottom = mVerticalSpaceHeight;
    }
}
