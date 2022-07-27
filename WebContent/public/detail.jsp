<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/public/inc/header.jsp" %>
<div class="content_resize">
  <div class="mainbar">
    <div class="article">
    <%
      	Song song2 = (Song)request.getAttribute("getSongtById");
      %>
      <h1><%=song2.getName()%></h1>
       
      <div class="clr"></div>
      <p>Ngày đăng:<%=song2.getDate_create() %>  Lượt xem: <%=song2.getCounter() %></p>
      <div class="vnecontent"><%=song2.getDetail_text() %></div>
    </div>
    <div class="article">
      <h2>Bài viết liên quan</h2>
      <%
     	@SuppressWarnings("unchecked")
        ArrayList<Song>listSongLq = (ArrayList<Song>) request.getAttribute("listSongLq");
        if(listSongLq != null && listSongLq.size() >0){
        for(Song items : listSongLq){
      %>
      <div class="clr"></div>
      <div class="comment"> <a href="<%=request.getContextPath()%>/public/chi-tiet?id=<%=items.getId() %>&cat_id=<%=items.getCat_id() %>"><img src="<%=request.getContextPath()%>/templates/public/images/<%=items.getPicture() %>" width="40" height="40" alt="" class="userpic" /></a>
        <h2><a href="<%=request.getContextPath()%>/public/chi-tiet?id=<%=items.getId() %>&cat_id=<%=items.getCat_id() %>"><%=items.getName() %></a></h2>
        <p><%=items.getPreview_text() %></p>
      </div>
      <%}}else{ %>
	 <div>
	    <p>Không có mục nào</p>
	</div>
											
	<%} %>
    </div>
  </div>
  <div class="sidebar">
  <%@ include file="/templates/public/inc/leftbar.jsp" %>
  </div>
  <div class="clr"></div>
</div>
<%@ include file="/templates/public/inc/footer.jsp" %>


