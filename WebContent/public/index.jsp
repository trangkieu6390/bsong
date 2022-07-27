<%@page import="java.util.ArrayList"%>
<%@page import="model.bean.Song"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/public/inc/header.jsp" %>
<div class="content_resize">
  <div class="mainbar">
    <div class="article">
   
    <%
     	@SuppressWarnings("unchecked")
        ArrayList<Song>listSong = (ArrayList<Song>) request.getAttribute("listSong");
        if(listSong != null && listSong.size() >0){
        for(Song items : listSong){
      %>
      <h2><a href="<%=request.getContextPath()%>/public/chi-tiet?id=<%=items.getId() %>&cat_id=<%=items.getCat_id() %>" title="<%=items.getName() %>"><%=items.getName() %></a></h2>
      <p class="infopost">Ngày đăng: <%=items.getDate_create() %> Lượt xem: <%=items.getCounter() %> <a href="#" class="com"><span><%=items.getId() %></span></a></p>
      <div class="clr"></div>
      <div class="img"><img src="<%=request.getContextPath()%>/templates/public/images/<%=items.getPicture() %>" width="177" height="213" alt="Đổi thay" class="fl" /></div>
      <div class="post_content">
        <p><%=items.getPreview_text() %></p>
        <p class="spec"><a href="<%=request.getContextPath()%>/public/chi-tiet?id=<%=items.getId() %>&cat_id=<%=items.getCat_id() %>" class="rm">Chi tiết &raquo;</a></p>
      </div>
     
      <div class="clr"></div>
       <%}}else{ %>
	 <div>
	    <p>Không có danh mục nào</p>
	</div>
											
	<%} %>
    </div>
    
  
   <%
   String editbox_search = (String) request.getAttribute("editbox_search");
   if(editbox_search == null){ editbox_search = "";}
   Object index_page1 = request.getAttribute("index_page");
   Object number_page = request.getAttribute("number_page");
   int i = Integer.parseInt(number_page.toString());
   int index_page= Integer.parseInt(index_page1.toString());
   %>
    <p class="pages"><small>Trang <%=index_page %> của <%=i %> </small>
   
     <%
		for(int i1 = 1; i1 <= i; i1++) {
		if(index_page == i1){
	 %>
	  <span><%=i1%></span>
	 <%}else{%>
    <a href="<%=request.getContextPath()%>/public/search?index_page=<%=i1%>&editbox_search=<%=editbox_search%>"><%=i1 %></a>
    <%}} %>
    <a href="<%=request.getContextPath()%>/public/search?index_page=<%=i%>&editbox_search=<%=editbox_search%>">&raquo;</a></p>
  </div>
  <div class="sidebar">
      <%@ include file="/templates/public/inc/leftbar.jsp" %>
  </div>
  <div class="clr"></div>
</div>
<%@ include file="/templates/public/inc/footer.jsp" %>
