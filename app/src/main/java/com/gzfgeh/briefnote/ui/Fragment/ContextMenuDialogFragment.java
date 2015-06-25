package com.gzfgeh.briefnote.ui.Fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.gzfgeh.briefnote.R;
import com.gzfgeh.briefnote.adapter.MenuAdapter;
import com.gzfgeh.briefnote.adapter.MenuAdapter.OnItemClickListener;
import com.gzfgeh.briefnote.adapter.MenuAdapter.OnItemLongClickListener;
import com.gzfgeh.briefnote.listener.OnMenuItemClickListener;
import com.gzfgeh.briefnote.listener.OnMenuItemLongClickListener;
import com.gzfgeh.briefnote.model.MenuObject;
import com.gzfgeh.briefnote.model.MenuParams;

import java.util.List;

public class ContextMenuDialogFragment extends DialogFragment implements OnItemClickListener, OnItemLongClickListener {
	public static final String TAG = ContextMenuDialogFragment.class.getSimpleName();
	private static final String BUNDLE_MENU_PARAMS = "BUNDLE_MENU_PARAMS";
	private MenuParams menuParams;
	private LinearLayout wrapperButtons;
    private LinearLayout wrapperText;
    private MenuAdapter dropDownMenuAdapter;
    private OnMenuItemClickListener itemClickListener;
    private OnMenuItemLongClickListener itemLongClickListener;
	
	public static ContextMenuDialogFragment newInstance(MenuParams menuParams){
		ContextMenuDialogFragment dialogFragment = new ContextMenuDialogFragment();
		Bundle bundle = new Bundle();
		bundle.putParcelable(BUNDLE_MENU_PARAMS, menuParams);
		dialogFragment.setArguments(bundle);
		return dialogFragment;
	}
	
	public static ContextMenuDialogFragment newInstance(int actionBarSize, List<MenuObject> menuObjects){
		MenuParams menuParams = new MenuParams();
		menuParams.setActionBarSize(actionBarSize);
		menuParams.setMenuObjects(menuObjects);
		return newInstance(menuParams);
	}
	
	public static ContextMenuDialogFragment newInstance(int actionBarSize, List<MenuObject> menuObjects, int animationDelay){
		MenuParams menuParams = new MenuParams();
		menuParams.setActionBarSize(actionBarSize);
		menuParams.setMenuObjects(menuObjects);
		menuParams.setAnimationDelay(animationDelay);
		return newInstance(menuParams);
	}
	
	public static ContextMenuDialogFragment newInstance(int actionBarSize, List<MenuObject> menuObjects, int animationDelay, int animationDuration){
		MenuParams menuParams = new MenuParams();
		menuParams.setActionBarSize(actionBarSize);
		menuParams.setMenuObjects(menuObjects);
		menuParams.setAnimationDelay(animationDelay);
		menuParams.setAnimationDuration(animationDuration);
		return newInstance(menuParams);
	}
	
	public static ContextMenuDialogFragment newInstance(int actionBarSize, List<MenuObject> menuObjects, 
				int animationDelay, int animationDuration, boolean fitsSystemWindow, boolean clipToPadding){
		MenuParams menuParams = new MenuParams();
		menuParams.setActionBarSize(actionBarSize);
		menuParams.setMenuObjects(menuObjects);
		menuParams.setAnimationDelay(animationDelay);
		menuParams.setAnimationDuration(animationDuration);
		return newInstance(menuParams);
	}

	
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		itemClickListener = (OnMenuItemClickListener)activity;
		itemLongClickListener = (OnMenuItemLongClickListener)activity;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setStyle(STYLE_NO_FRAME, R.style.MenuFragmentStyle);
		if (getArguments() != null)
			menuParams = getArguments().getParcelable(BUNDLE_MENU_PARAMS);
	}
	

	@SuppressLint("NewApi") 
	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.menu_fragment, container, false);
		rootView.setFitsSystemWindows(menuParams.isFitsSystemWindow());
		((ViewGroup)rootView).setClipToPadding(menuParams.isClipToPadding());
		
		wrapperButtons = (LinearLayout) rootView.findViewById(R.id.wrapper_buttons);
		wrapperText = (LinearLayout) rootView.findViewById(R.id.wrapper_text);
		
		getDialog().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
		dropDownMenuAdapter = new MenuAdapter(getActivity(), wrapperButtons, wrapperText, menuParams.getMenuObjects(),
								menuParams.getActionBarSize());
		dropDownMenuAdapter.setOnItemClickListener(this);
		dropDownMenuAdapter.setOnItemLongClickListener(this);
		dropDownMenuAdapter.setAnimationDuration(menuParams.getAnimationDuration());
		
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				dropDownMenuAdapter.menuToggle();
			}
		}, menuParams.getAnimationDelay());
		
		if (menuParams.isClosableOutside()){
			rootView.findViewById(R.id.root).setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					getActivity().onBackPressed();
				}
			});
		}
		return rootView;
	}
	
	private void close(){
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				dismiss();
			}
		}, menuParams.getAnimationDelay());
	}

	@Override
	public void onClick(View v) {
		if (itemClickListener != null)
			itemClickListener.onMenuItemClick(v, wrapperButtons.indexOfChild(v));
		close();
	}

	@Override
	public void onLongClick(View v) {
		if (itemLongClickListener != null)
			itemLongClickListener.onMenuItemLongClick(v, wrapperButtons.indexOfChild(v));
		
	}
	
	
}
