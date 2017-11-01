package com.tt.tttv.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.tt.tttv.model.Channel;
import com.tt.tttv.util.DBUtil;

public class ChannelDB {
	
	public List<Channel> getChannels(){
		List<Channel> channels = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			String sql = "select * from tv_channel";
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				int id = rs.getInt(rs.findColumn("channel_id"));
				String name = rs.getString(rs.findColumn("channel_name"));
				String address = rs.getString(rs.findColumn("channel_address"));
				Channel channel = new Channel(id, name, address);
				channels.add(channel);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.close(conn, ps, rs);
		}
		return channels;
	}
	
	public boolean addChannel(Channel channel){
		if(channel == null){
			return false;
		}
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			String sql = "insert into tv_channel(channel_name, channel_address) values(?, ?)";
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, channel.getChannelName());
			ps.setString(2, channel.getChannelAddress());
			int line = ps.executeUpdate();
			if(line > 0){
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.close(conn, ps, null);
		}
		return false;
	}
	
	public boolean deleteChannel(int id){
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			String sql = "delete from tv_channel where channel_id = ?";
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			int line = ps.executeUpdate();
			if(line > 0){
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.close(conn, ps, null);
		}
		return false;
	}

	public boolean updateChannel(Channel channel){
		if(channel == null){
			return false;
		}
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			String sql = "update tv_channel set channel_name = ?, channel_address = ? where channel_id = ?";
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, channel.getChannelName());
			ps.setString(2,  channel.getChannelAddress());
			ps.setInt(3, channel.getChannelId());
			int line = ps.executeUpdate();
			if(line > 0){
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.close(conn, ps, null);
		}
		return false;
	}
}
