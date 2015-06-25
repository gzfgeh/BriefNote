package com.gzfgeh.briefnote.model;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

public class MenuObject implements Parcelable {
	private String title;
	
	private Drawable bgDrawable;
	private int bgColor;
	private int bgResID;
	
	private Drawable srcDrawable;
	private int srcColor;
	private int srcResID;
	private Bitmap srcBitmap;
	
	private ImageView.ScaleType scaleType = ImageView.ScaleType.CENTER_INSIDE;
	private int textColor;
	private int dividerColor = Integer.MAX_VALUE;
	private int menuTextAppearanceStyle;
	
	public MenuObject(){
		this("");
	}
	
	public MenuObject(String title){
		this.title = title;
	}
	
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Drawable getBgDrawable() {
		return bgDrawable;
	}

	public void setBgDrawable(Drawable bgDrawable) {
		this.bgDrawable = bgDrawable;
		bgColor = 0;
		bgResID = 0;
	}

	public int getBgColor() {
		return bgColor;
	}

	public void setBgColor(int bgColor) {
		this.bgColor = bgColor;
		bgDrawable = null;
		bgResID = 0;
	}

	public int getBgResID() {
		return bgResID;
	}

	public void setBgResID(int bgResID) {
		this.bgResID = bgResID;
		bgColor = 0;
		bgDrawable = null;
	}

	public Drawable getSrcDrawable() {
		return srcDrawable;
	}

	public void setSrcDrawable(Drawable srcDrawable) {
		this.srcDrawable = srcDrawable;
		srcBitmap = null;
		srcColor = 0;
		srcResID = 0;
	}

	public int getSrcColor() {
		return srcColor;
	}

	public void setSrcColor(int srcColor) {
		this.srcColor = srcColor;
		srcBitmap = null;
		srcDrawable = null;
		srcResID = 0;
	}

	public int getSrcResID() {
		return srcResID;
	}

	public void setSrcResID(int srcResID) {
		this.srcResID = srcResID;
		srcBitmap = null;
		srcDrawable = null;
		srcColor = 0;
	}

	public Bitmap getSrcBitmap() {
		return srcBitmap;
	}

	public void setSrcBitmap(Bitmap srcBitmap) {
		this.srcBitmap = srcBitmap;
		srcColor = 0;
		srcDrawable = null;
		srcResID = 0;
	}

	public ImageView.ScaleType getScaleType() {
		return scaleType;
	}

	public void setScaleType(ImageView.ScaleType scaleType) {
		this.scaleType = scaleType;
	}

	public int getTextColor() {
		return textColor;
	}

	public void setTextColor(int textColor) {
		this.textColor = textColor;
	}

	public int getDividerColor() {
		return dividerColor;
	}

	public void setDividerColor(int dividerColor) {
		this.dividerColor = dividerColor;
	}

	public int getMenuTextAppearenseStyle() {
		return menuTextAppearanceStyle;
	}

	public void setMenuTextAppearenseStyle(int menuTextAppearenseStyle) {
		this.menuTextAppearanceStyle = menuTextAppearenseStyle;
	}

	public static Creator<MenuObject> getCreator(){
		return CREATOR;
	}
	
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(title);
		dest.writeParcelable(bgDrawable == null ? null : ((BitmapDrawable)bgDrawable).getBitmap(), flags);
		dest.writeInt(bgColor);
		dest.writeInt(bgResID);
		dest.writeParcelable(srcDrawable == null ? null : ((BitmapDrawable)srcDrawable).getBitmap(), flags);
		dest.writeInt(srcColor);
		dest.writeInt(srcResID);
		dest.writeParcelable(srcBitmap, 0);
		dest.writeInt(dividerColor);
		dest.writeInt(textColor);
		dest.writeInt(menuTextAppearanceStyle);
		dest.writeInt(scaleType == null ? -1 : scaleType.ordinal());
	}

	public static final Creator<MenuObject> CREATOR = new Creator<MenuObject>() {

		@Override
		public MenuObject createFromParcel(Parcel source) {
			return new MenuObject(source);
		}

		@Override
		public MenuObject[] newArray(int size) {
			return new MenuObject[size];
		}
		
	};
	
	@SuppressWarnings("deprecation")
	private MenuObject(Parcel in){
		title = in.readString();
		Bitmap bitmapBgDrawable = in.readParcelable(Bitmap.class.getClassLoader());
		if (bitmapBgDrawable != null)
			bgDrawable = new BitmapDrawable(bitmapBgDrawable);
		
		bgColor = in.readInt();
		bgResID = in.readInt();
		
		Bitmap bitmapDrawable = in.readParcelable(Bitmap.class.getClassLoader());
		if (bitmapDrawable != null)
			srcDrawable = new BitmapDrawable(bitmapDrawable);
		
		srcColor = in.readInt();
		srcResID = in.readInt();
		srcBitmap = in.readParcelable(Bitmap.class.getClassLoader());
		int tmpScaleType = in.readInt();
		scaleType = tmpScaleType == -1 ? null : ImageView.ScaleType.values()[tmpScaleType];
		textColor = in.readInt();
		dividerColor = in.readInt();
		menuTextAppearanceStyle = in.readInt();
	}
}
