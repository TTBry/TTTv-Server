package com.tt.tttv.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tt.tttv.db.ChannelDB;
import com.tt.tttv.model.Channel;

public class ChannelServlet extends HttpServlet {
	
	private ChannelDB channelDB;

	public ChannelServlet() {
		super();
		channelDB = new ChannelDB();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String method = request.getParameter("method");
		response.setHeader("Content-type", "text/html;charset=UTF-8"); 
		if("add".equalsIgnoreCase(method)){
			add(request, response);
		}else if("get".equalsIgnoreCase(method)){
			get(request, response);
		}else if("update".equalsIgnoreCase(method)){
			update(request, response);
		}else if("delete".equalsIgnoreCase(method)){
			delete(request, response);
		}else{
			JSONObject obj = new JSONObject();
			obj.put("isSuccess", false);
			obj.put("result", "该方法不存在");
			response.getWriter().print(obj.toString());
		}
	}
	
	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("channelName");
		String address = request.getParameter("channelAddress");
		JSONObject obj = new JSONObject();
		
		if(name == null || "".equals(name)){
			obj.put("isSuccess", false);
			obj.put("result", "参数name缺失");
		}else if(address == null || "".equals(address)){
			obj.put("isSuccess", false);
			obj.put("result", "参数address缺失");
		}else{
			Channel channel = new Channel(0, name, address);
			boolean isSuccess = channelDB.addChannel(channel);
			obj.put("isSuccess", isSuccess);
			if(!isSuccess){
				obj.put("result", "插入数据库失败");
			}else{
				obj.put("result", "添加成功");
			}
		}
		
		response.getWriter().print(obj.toString());
	}
	
	private void get(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Channel> channels = channelDB.getChannels();
		JSONObject obj = new JSONObject();
		if(channels == null){
			obj.put("isSuccess", false);
			obj.put("result", "获取数据失败");
		}else{
			obj.put("isSuccess", true);
			obj.put("result", "success");
			obj.put("data", JSONArray.parseArray(JSON.toJSONString(channels)));
		}

		response.getWriter().print(obj.toString());
	}
	
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("channelId");
		String name = request.getParameter("channelName");
		String address = request.getParameter("channelAddress");
		JSONObject obj = new JSONObject();
		if(idStr == null || "".equals(idStr)){
			obj.put("isSuccess", false);
			obj.put("result", "参数id缺失");
		}else if(name == null || "".equals(name)){
			obj.put("isSuccess", false);
			obj.put("result", "参数name缺失");
		}else if(address == null || "".equals(address)){
			obj.put("isSuccess", false);
			obj.put("result", "参数address缺失");
		}else{
			int id = Integer.valueOf(idStr);
			Channel channel = new Channel(id, name, address);
			boolean isSuccess = channelDB.updateChannel(channel);
			obj.put("isSuccess", isSuccess);
			if(!isSuccess){
				obj.put("result", "修改失败");
			}else{
				obj.put("result", "修改成功");
			}
		}

		response.getWriter().print(obj.toString());
	}
	
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("channelId");
		JSONObject obj = new JSONObject();
		if(idStr == null || "".equals(idStr)){
			obj.put("isSuccess", false);
			obj.put("result", "参数id缺失");
		}else{
			int id = Integer.valueOf(idStr);
			boolean isSuccess = channelDB.deleteChannel(id);
			obj.put("isSuccess", isSuccess);
			if(!isSuccess){
				obj.put("result", "删除失败");
			}else{
				obj.put("result", "删除成功");
			}
		}

		response.getWriter().print(obj.toString());
	}

}
