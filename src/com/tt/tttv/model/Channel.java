package com.tt.tttv.model;

public class Channel {

	private int channelId;
	private String channelName;
	private String channelAddress;
	private Category category;
	
	public int getChannelId() {
		return channelId;
	}
	
	public Channel(){
	}
	
	public Channel(int channelId, String channelName, String channelAddress, Category category) {
		this.channelId = channelId;
		this.channelName = channelName;
		this.channelAddress = channelAddress;
		this.category = category;
	}

	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public String getChannelAddress() {
		return channelAddress;
	}
	public void setChannelAddress(String channelAddress) {
		this.channelAddress = channelAddress;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
}
