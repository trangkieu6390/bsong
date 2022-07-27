<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/public/inc/header.jsp" %>
<div class="content_resize">
  <div class="mainbar">
    <div class="article">
		<h1>Nhạc trẻ</h1>
    </div>
    <div class="article">
 <%
 	ArrayList<Song> listSongs = (ArrayList<Song>) request.getAttribute("listSong");
 	if(listSongs != null && listSongs.size() > 0){
 		for(Song song : listSongs){
 %>
      <h2><a href="" title="Đổi thay"><%=song.getName() %></a></h2>
      <p class="infopost">Ngày đăng: <%=song.getDate_create() %> Lượt xem: <%=song.getCounter() %> <a href="#" class="com"><span><%=song.getId() %></span></a></p>
      <div class="clr"></div>
      <div class="img"><img src="<%=request.getContextPath()%>/templates/public/images/<%=song.getPicture() %>" width="177" height="213" alt="Đổi thay" class="fl" /></div>
      <div class="post_content">
        <p><%=song.getPreview_text() %></p>
        <p class="spec"><a href="" class="rm">Chi tiết &raquo;</a></p>
      </div>
      <div class="clr"></div>
         <%}}else{ %>
	 <div>
	    <p>Không có danh mục nào</p>
	</div>
											
	<%} %>
    </div>
    <%
    Object cat_id1 = request.getAttribute("cat_id");
    int cat_id= Integer.parseInt(cat_id1.toString());
   Object index_page1 = request.getAttribute("index_page");
   Object number_page = request.getAttribute("number_page");
   int i = Integer.parseInt(number_page.toString());
   int index_page= Integer.parseInt(index_page1.toString());
   
   %>
   <%if(i == 0){%>
    <p class="pages"><small>Trang <%=index_page %> của <%=1 %> </small>
  <%}else{ %>
  	<p class="pages"><small>Trang <%=index_page %> của <%=i %> </small>
  <%} %>
  <%if(i == 0){ %>
   <span><%=1%></span>
  <%} %>
     <%
		for(int i1 = 1; i1 <= i; i1++) {
		if(index_page == i1){
	 %>
	  <span><%=i1%></span>
	 <%}else{%>
    <a href="<%=request.getContextPath()%>/public/danh-muc?cat_id=<%=cat_id%>&index_page=<%=i1%>"><%=i1 %></a>
    <%}} %>
    <% if(i >= 2){ %>
    <a href="<%=request.getContextPath()%>/public/danh-muc?cat_id=<%=cat_id%>&index_page=<%=i%>">&raquo;</a></p>
	 <%} %>
  </div>
  <div class="sidebar">
      <%@ include file="/templates/public/inc/leftbar.jsp" %>
  </div>
  <div class="clr"></div>
</div>
<%@ include file="/templates/public/inc/footer.jsp" %>