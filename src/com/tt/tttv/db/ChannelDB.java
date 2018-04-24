package com.tt.tttv.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.tt.tttv.model.Category;
import com.tt.tttv.model.Channel;
import com.tt.tttv.util.DBUtil;

public class ChannelDB {
	
	public List<Channel> getChannels(){
		List<Channel> channels = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			StringBuilder sql = new StringBuilder("select channel_id, channel_name, channel_address, tv_channel.category_id, tv_category.category_name");
			sql.append(" from tv_channel, tv_category");
			sql.append(" where tv_channel.category_id = tv_category.category_id");
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			while(rs.next()){
				int id = rs.getInt(rs.findColumn("channel_id"));
				String name = rs.getString(rs.findColumn("channel_name"));
				String address = rs.getString(rs.findColumn("channel_address"));
				int categoryId = rs.getInt(rs.findColumn("category_id"));
				String categoryName = rs.getString(rs.findColumn("category_name"));
				Category category = new Category(categoryId, categoryName);
				Channel channel = new Channel(id, name, address, category);
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
			String sql = "insert into tv_channel(channel_name, channel_address, category_id) values(?, ?, ?)";
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, channel.getChannelName());
			ps.setString(2, channel.getChannelAddress());
			ps.setInt(3, channel.getCategory().getCategoryId());
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
			String sql = "update tv_channel set channel_name = ?, channel_address = ?, category_id = ? where channel_id = ?";
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, channel.getChannelName());
			ps.setString(2,  channel.getChannelAddress());
			ps.setInt(3, channel.getChannelId());
			ps.setInt(4, channel.getCategory().getCategoryId());
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
