package com.surfline.surflinegh.utils;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Jyoti Sheoran Bhambhu on 28/4/16.
 */
public class VerticalSpaceDecoration extends RecyclerView.ItemDecoration {
//    public class DividerItemDecoration extends RecyclerView.ItemDecoration {
//
//        private final int[] ATTRS = new int[]{android.R.attr.listDivider};
//
//        private Drawable mDivider;
//
//        /**
//         * Default divider will be used
//         */
//        public DividerItemDecoration(Context context) {
//            final TypedArray styledAttributes = context.obtainStyledAttributes(ATTRS);
//            mDivider = styledAttributes.getDrawable(0);
//            styledAttributes.recycle();
//        }
//
//        /**
//         * Custom divider will be used
//         */
//        public DividerItemDecoration(Context context, int resId) {
//            mDivider = ContextCompat.getDrawable(context, resId);
//        }
//
//        @Override
//        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
//            int left = parent.getPaddingLeft();
//            int right = parent.getWidth() - parent.getPaddingRight();
//
//            int childCount = parent.getChildCount();
//            for (int i = 0; i < childCount; i++) {
//                View child = parent.getChildAt(i);
//
//                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
//
//                int top = child.getBottom() + params.bottomMargin;
//                int bottom = top + mDivider.getIntrinsicHeight();
//
//                mDivider.setBounds(left, top, right, bottom);
//                mDivider.draw(c);
//            }
//        }
//    }


    private final int mVerticalSpaceHeight;

    public VerticalSpaceDecoration(int mVerticalSpaceHeight) {
        this.mVerticalSpaceHeight = mVerticalSpaceHeight;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        outRect.bottom = mVerticalSpaceHeight;
    }

}
