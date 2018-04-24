<%@page import="com.tt.tttv.model.Channel"%>
<%@page import="com.tt.tttv.db.ChannelDB"%>
<%@ page language="java" import="java.util.*,com.tt.tttv.*" pageEncoding="GB18030"%>
<%
	ChannelDB channelDB = new ChannelDB();
	List<Channel> channels = channelDB.getChannels();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>TTTv</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="TTTv">
	
	<script type="text/javascript" src="ckplayer/ckplayer.js" charset="UTF-8"></script>
	<style type="text/css">
		body {
			margin: 0;
			padding: 0px;
			font-family: "Microsoft YaHei", YaHei, "微软雅黑", SimHei, "黑体";
			font-size: 14px
		}
	</style>
  </head>
  
  <body>
  	<center>
  		<table>
  			<tr>
  				<td>
  					<ul>
  						<%
  							int len = channels.size();
  							for(int i = 0; i < len; i++){
  								%>
	  								<li>
	  									<a href="<%=channels.get(i).getChannelAddress() %>" onclick="change(this);return false;"><%=channels.get(i).getChannelName() %></a>
	  								</li>
  								<%
  							}
  						%>
  					</ul>
  				</td>
  				<td>
					<div id="video" style="width: 600px; height: 400px;"></div>
					<script type="text/javascript">
						var videoUrl = '<%=channels.get(0).getChannelAddress() %>';
						var videoObject = {
							container:'#video',
							variable:'player',
							autoplay:true,
							video:videoUrl
						};
						var player=new ckplayer(videoObject);
						player.changeControlBarShow(false);
					</script>
					<script type="text/javascript">
						function change(obj){
							player.videoClear();
							changeVideo(obj.href);
						}
					
						function changeVideo(videoUrl) {
							if(player == null) {
								return;
							}
	
							var newVideoObject = {
								container: '#video', //容器的ID
								variable: 'player',
								autoplay: true, //是否自动播放
								loaded: 'loadedHandler', //当播放器加载后执行的函数
								video: videoUrl
							}
							//判断是需要重新加载播放器还是直接换新地址
							if(player.playerType == 'html5video') {
								if(player.getFileExt(videoUrl) == '.flv' || player.getFileExt(videoUrl) == '.m3u8' || player.getFileExt(videoUrl) == '.f4v' || videoUrl.substr(0, 4) == 'rtmp') {
									player.removeChild();
	
									player = null;
									player = new ckplayer();
									player.embed(newVideoObject);
								} else {
									player.newVideo(newVideoObject);
								}
							} else {
								if(player.getFileExt(videoUrl) == '.mp4' || player.getFileExt(videoUrl) == '.webm' || player.getFileExt(videoUrl) == '.ogg') {
									player = null;
									player = new ckplayer();
									player.embed(newVideoObject);
								} else {
									player.newVideo(newVideoObject);
								}
							}
						}
					</script>
  				</td>
  			</tr>
  		</table>
  	</center>
    
  </body>
</html>
