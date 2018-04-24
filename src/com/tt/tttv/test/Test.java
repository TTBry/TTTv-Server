package com.tt.tttv.test;

import java.sql.Connection;
import java.util.List;

import com.tt.tttv.db.ChannelDB;
import com.tt.tttv.model.Category;
import com.tt.tttv.model.Channel;
import com.tt.tttv.util.DBUtil;

public class Test {
	
	@org.junit.Test
	public void testGetConn(){
		Connection conn = null;
		try{
			conn = DBUtil.getConnection();
			System.out.println(conn);
		}catch(Exception e){
			e.printStackTrace();
			DBUtil.close(conn, null, null);
		}
	}
	
	@org.junit.Test
	public void testGetChannels(){
		ChannelDB channelDB = new ChannelDB();
		List<Channel> channels = channelDB.getChannels();
		for(Channel channel : channels){
			System.out.println(channel.getChannelId() + ", " + channel.getChannelName() + ", " + channel.getChannelAddress() + ", " + channel.getCategory().getCategoryName());
		}
	}
	
	@org.junit.Test
	public void testAddChannel(){
		ChannelDB channelDB = new ChannelDB();
		Channel channel = new Channel(0, "深圳卫视", "http://183.252.176.65//PLTV/88888888/224/3221225938/index.m3u8", new Category(2, null));
		boolean result = channelDB.addChannel(channel);
		System.out.println(result);
	}

	@org.junit.Test
	public void testUpdateChannel(){
		ChannelDB channelDB = new ChannelDB();
		Channel channel = new Channel(2, "深圳卫视", "http://183.252.176.65//PLTV/88888888/224/3221225938/index.m3u8", new Category(2, null));
		boolean result = channelDB.updateChannel(channel);
		System.out.println(result);
	}
	
	@org.junit.Test
	public void testDeleteChannel(){
		ChannelDB channelDB = new ChannelDB();
		boolean result = channelDB.deleteChannel(5);
		System.out.println(result);
	}
}
