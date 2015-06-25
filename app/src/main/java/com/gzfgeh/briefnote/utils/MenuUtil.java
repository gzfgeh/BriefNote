package com.gzfgeh.briefnote.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gzfgeh.briefnote.R;
import com.gzfgeh.briefnote.model.MenuObject;

public class MenuUtil {
	
	private MenuUtil(){
		
	}
	
	public static int getDefaultActionBarSize(Context context) {
        final TypedArray styledAttributes = context.getTheme().obtainStyledAttributes(
                new int[]{android.R.attr.actionBarSize});
        int actionBarSize = (int) styledAttributes.getDimension(0, 0);
        styledAttributes.recycle();
        return actionBarSize;
    }

    public static TextView getItemTextView(Context context, MenuObject menuItem, int menuItemSize) {
        TextView itemTextView = new TextView(context);
        RelativeLayout.LayoutParams textLayoutParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, menuItemSize);
        itemTextView.setLayoutParams(textLayoutParams);
//        itemTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, context.getResources().getDimension(R.dimen.menu_text_size));
        itemTextView.setText(menuItem.getTitle());
        itemTextView.setPadding(0, 0, (int)context.getResources().getDimension(R.dimen.text_right_padding), 0);
        itemTextView.setGravity(Gravity.CENTER_VERTICAL);
        int textColor = menuItem.getTextColor() == 0 ?
                context.getResources().getColor(android.R.color.white) :
                menuItem.getTextColor();
        itemTextView.setTextColor(textColor);
        itemTextView.setTextAppearance(context, menuItem.getMenuTextAppearenseStyle() > 0
                ? menuItem.getMenuTextAppearenseStyle()
                : R.style.TextView_DefaultStyle);
        return itemTextView;
    }

    public static ImageView getItemImageButton(Context context, MenuObject item) {
        ImageView imageView = new ImageButton(context);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        imageView.setLayoutParams(lp);
        imageView.setPadding((int) context.getResources().getDimension(R.dimen.menu_item_padding),
                (int) context.getResources().getDimension(R.dimen.menu_item_padding),
                (int) context.getResources().getDimension(R.dimen.menu_item_padding),
                (int) context.getResources().getDimension(R.dimen.menu_item_padding));
        imageView.setClickable(false);
        imageView.setFocusable(false);
        imageView.setBackgroundColor(Color.TRANSPARENT);

        if (item.getSrcColor() != 0) {
            Drawable color = new ColorDrawable(item.getSrcColor());
            imageView.setImageDrawable(color);
        } else if (item.getSrcResID() != 0) {
            imageView.setImageResource(item.getSrcResID());
        } else if (item.getSrcBitmap() != null) {
            imageView.setImageBitmap(item.getSrcBitmap());
        } else if (item.getSrcDrawable() != null) {
            imageView.setImageDrawable(item.getSrcDrawable());
        }
        imageView.setScaleType(item.getScaleType());

        return imageView;
    }

    public static View getDivider(Context context, MenuObject menuItem) {
        View dividerView = new View(context);
        RelativeLayout.LayoutParams viewLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) context.getResources().getDimension(R.dimen.divider_height));
        viewLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        dividerView.setLayoutParams(viewLayoutParams);
        dividerView.setClickable(true);
        int dividerColor = menuItem.getDividerColor() == Integer.MAX_VALUE ?
                context.getResources().getColor(R.color.divider_color) :
                menuItem.getDividerColor();
        dividerView.setBackgroundColor(dividerColor);
        return dividerView;
    }

    @SuppressLint("NewApi") 
    public static RelativeLayout getImageWrapper(Context context, MenuObject menuItem, int menuItemSize,
                                                 View.OnClickListener onCLick, View.OnLongClickListener onLongClick,
                                                 boolean showDivider) {
        RelativeLayout imageWrapper = new RelativeLayout(context);
        LinearLayout.LayoutParams imageWrapperLayoutParams = new LinearLayout.LayoutParams(menuItemSize, menuItemSize);
        imageWrapper.setLayoutParams(imageWrapperLayoutParams);
        imageWrapper.setOnClickListener(onCLick);
        imageWrapper.setOnLongClickListener(onLongClick);
        imageWrapper.addView(MenuUtil.getItemImageButton(context, menuItem));
        if (showDivider) {
            imageWrapper.addView(getDivider(context, menuItem));
        }

        if (menuItem.getBgColor() != 0) {
            imageWrapper.setBackgroundColor(menuItem.getBgColor());
        } else if (menuItem.getBgDrawable() != null) {
            imageWrapper.setBackground(menuItem.getBgDrawable());
        } else if (menuItem.getBgResID() != 0) {
            imageWrapper.setBackgroundResource(menuItem.getBgResID());
        } else {
            imageWrapper.setBackgroundColor(context.getResources().getColor(R.color.menu_item_background));
        }
        return imageWrapper;
    }
}
