<%@page import="model.dao.SongDAO"%>
<%@page import="model.bean.Song"%>
<%@page import="model.bean.Category"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.dao.CategoryDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<div class="searchform">
<%
    String search = (String) request.getAttribute("editbox_search");
 %>
  <form id="formsearch" name="formsearch" method="post" action="<%=request.getContextPath()%>/public/search">
    <span>
    <input name="editbox_search" class="editbox_search" id="editbox_search" maxlength="80" value="<%if(search!=null){out.print(search);}%>" type="text" />
    </span>
    <input name="button_search" src="<%=request.getContextPath()%>/templates/public/images/search.jpg" class="button_search" type="image" />
  </form>
</div>
<div class="clr"></div>
<div class="gadget">
  <h2 class="star">Danh mục bài hát</h2>
  <div class="clr"></div>
  <ul class="sb_menu">
   <%
		CategoryDAO cats = new CategoryDAO();
		ArrayList<Category> item = cats.getItems();
	    if(item != null){
	    	for(Category cat : item){
   %>
    <li><a href="<%=request.getContextPath()%>/public/danh-muc?cat_id=<%=cat.getId()%>&index_page=<%=1%>"><%=cat.getName() %></a></li>
    <%} }%>
  </ul>
</div>

<div class="gadget">
  <h2 class="star"><span>Bài hát mới</span></h2>
  <div class="clr"></div>
  <ul class="ex_menu">
   <%
		SongDAO song = new SongDAO();
		ArrayList<Song> item1 = song.getItemsNew();
	    if(item != null){
	    	for(Song song1 : item1){
   %>
    <li><a href="<%=request.getContextPath()%>/public/chi-tiet?id=<%=song1.getId() %>&cat_id=<%=song1.getCat_id() %>"><%=song1.getName() %></a><br /><%=song1.getPreview_text() %></li>
   <%}} %>
  </ul>
</div>