package com.gzfgeh.briefnote.ui.Activity.HandleComponent;

import android.content.Context;
import android.util.TypedValue;

import com.gzfgeh.briefnote.R;
import com.gzfgeh.briefnote.model.MenuObject;
import com.gzfgeh.briefnote.model.MenuParams;
import com.gzfgeh.briefnote.ui.Fragment.ContextMenuDialogFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guzhenf on 6/27/2015.
 */
public class HandleMenu {

    public static ContextMenuDialogFragment menuButtonClick(Context context){
        MenuParams menuParams = new MenuParams();
        menuParams.setActionBarSize((int)context.getResources().getDimension(R.dimen.tool_bar_height));
        menuParams.setMenuObjects(getMenuObjects(context));
        menuParams.setClosableOutside(false);
        return ContextMenuDialogFragment.newInstance(menuParams);
    }

    private static List<MenuObject> getMenuObjects(Context context){
        List<MenuObject> menuObjects = new ArrayList<MenuObject>();

        MenuObject close = new MenuObject();
        close.setSrcResID(R.drawable.icn_close);
        close.setBgColor(getColorPrimary(context));
        menuObjects.add(close);

        MenuObject send = new MenuObject("Send Message");
        send.setSrcResID(R.drawable.icn_1);
        send.setBgColor(getColorPrimary(context));
        menuObjects.add(send);

        MenuObject like = new MenuObject("Like Profile");
        like.setSrcResID(R.drawable.icn_2);
        like.setBgColor(getColorPrimary(context));
        menuObjects.add(like);

        MenuObject add = new MenuObject("Add to friends");
        add.setSrcResID(R.drawable.icn_3);
        add.setBgColor(getColorPrimary(context));
        menuObjects.add(add);

        MenuObject addFva = new MenuObject("Add to favorities");
        addFva.setSrcResID(R.drawable.icn_4);
        addFva.setBgColor(getColorPrimary(context));
        menuObjects.add(addFva);

        MenuObject block = new MenuObject("Block user");
        block.setSrcResID(R.drawable.icn_5);
        block.setBgColor(getColorPrimary(context));
        menuObjects.add(block);

        return menuObjects;
    }

    public static int getColorPrimary(Context context){
        TypedValue typedValue = new  TypedValue();
        context.getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
        return typedValue.data;
    }
}
