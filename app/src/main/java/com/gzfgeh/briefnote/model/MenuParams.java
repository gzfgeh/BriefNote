package com.gzfgeh.briefnote.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.gzfgeh.briefnote.adapter.MenuAdapter;

import java.util.List;

public class MenuParams implements Parcelable {
	private int actionBarSize = 0;
	private List<MenuObject> menuObjects;
	
	private int animationDelay = 0;
	private int animationDuration = MenuAdapter.ANIMATION_DURATION_MILLIS;
	private boolean isFitsSystemWindow = false;
    private boolean isClipToPadding = true;
    private boolean isClosableOutside = false;
    
	public int getActionBarSize() {
		return actionBarSize;
	}

	public void setActionBarSize(int actionBarSize) {
		this.actionBarSize = actionBarSize;
	}

	public List<MenuObject> getMenuObjects() {
		return menuObjects;
	}

	public void setMenuObjects(List<MenuObject> menuObjects) {
		this.menuObjects = menuObjects;
	}

	public int getAnimationDelay() {
		return animationDelay;
	}

	public void setAnimationDelay(int animationDelay) {
		this.animationDelay = animationDelay;
	}

	public int getAnimationDuration() {
		return animationDuration;
	}

	public void setAnimationDuration(int animationDuration) {
		this.animationDuration = animationDuration;
	}

	public boolean isFitsSystemWindow() {
		return isFitsSystemWindow;
	}

	public void setFitsSystemWindow(boolean isFitsSystemWindow) {
		this.isFitsSystemWindow = isFitsSystemWindow;
	}

	public boolean isClipToPadding() {
		return isClipToPadding;
	}

	public void setClipToPadding(boolean isClipToPadding) {
		this.isClipToPadding = isClipToPadding;
	}

	public boolean isClosableOutside() {
		return isClosableOutside;
	}

	public void setClosableOutside(boolean isClosableOutside) {
		this.isClosableOutside = isClosableOutside;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(actionBarSize);
		dest.writeTypedList(menuObjects);
		dest.writeInt(animationDelay);
		dest.writeInt(animationDuration);
		dest.writeByte(isFitsSystemWindow ? (byte)1 : (byte)0 );
		dest.writeByte(isClipToPadding ? (byte)1 : (byte)0);
		dest.writeByte(isClosableOutside ? (byte)1 : (byte)0);
	}
	
	public MenuParams(){
		
	}

	private MenuParams(Parcel in){
		actionBarSize = in.readInt();
		in.readTypedList(menuObjects, MenuObject.CREATOR);
		animationDelay = in.readInt();
		animationDuration = in.readInt();
		isFitsSystemWindow = in.readByte() != 0;
		isClipToPadding = in.readByte() != 0;
		isClosableOutside = in.readByte() != 0;
	}
	
	public static final Creator<MenuParams> CREATOR = new Creator<MenuParams>() {

		@Override
		public MenuParams createFromParcel(Parcel source) {
			return new MenuParams(source);
		}

		@Override
		public MenuParams[] newArray(int size) {
			return new MenuParams[size];
		}
	};
}
