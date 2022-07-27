<%@page import="java.util.ArrayList"%>
<%@page import="model.bean.Song"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>Quản lý bài hát</h2>
            </div>
        </div>
        <!-- /. ROW  -->
        <hr />
        <div class="row">
            <div class="col-md-12">
              <%
							if(request.getParameter("msg")!=null){
								int msg = Integer.parseInt(request.getParameter("msg"));
								if(msg == 1){
									out.print("<p style = 'color: red;'>Xóa bài hát thành công</p>");
								}else if(msg == 3){
									out.print("<p style = 'color: red;'>Thêm bài hát thành công</p>");
								}else if(msg == 2){
									out.print("<p style = 'color: red;'>Sua bài hát thành công</p>");
								}
							}
						%>
                <!-- Advanced Tables -->
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="table-responsive">
                            <div class="row">
                                <div class="col-sm-6">
                                    <a href="<%=request.getContextPath()%>/admin/song/add" class="btn btn-success btn-md">Thêm</a>
                                </div>
                                <div class="col-sm-6" style="text-align: right;">
                                 <%
                                	String search = (String) request.getAttribute("search");
                                %>
                                    <form method="post" action="<%=request.getContextPath()%>/admin/song/search">
                                        <input type="submit" value="Tìm kiếm" class="btn btn-warning btn-sm" style="float:right" />
                                        <input name="search" type="text" value="<%if(search!=null){out.print(search);}%>" class="form-control input-sm" style="float:right; width: 300px;" />
                                        <div style="clear:both"></div>
                                    </form><br />
                                </div>
                            </div>

                            <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Tên bài hát</th>
                                        <th>Danh mục</th>
                                        <th>Lượt đọc</th>
                                        <th>Hình ảnh</th>
                                        <th width="160px">Chức năng</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <%
                                	@SuppressWarnings("unchecked")
                                	ArrayList<Song>listSong = (ArrayList<Song>) request.getAttribute("listSong");
                                	if(listSong != null && listSong.size() >0){
                                		for(Song items : listSong){
                                %>
                                    <tr>
                                        <td><%=items.getId() %></td>
                                        <td class="center"><%=items.getName() %></td>
                                        <td class="center"><%=items.getCats() %></td>
                                        <td class="center"><%=items.getCounter() %></td>
                                        <td class="center">
											<img width="200px" height="200px" src="<%=request.getContextPath()%>/templates/admin/assets/img/<%=items.getPicture() %>" alt="Đổi thay"/>
                                        </td>
                                        <td class="center">
                                            <a href="<%=request.getContextPath()%>/admin/song/edit?id=<%=items.getId() %>" title="" class="btn btn-primary"><i class="fa fa-edit "></i> Sửa</a>
                                            <a href="<%=request.getContextPath()%>/admin/song/del?id=<%=items.getId() %>" title="" class="btn btn-danger" onclick="return confirm('Are you sure?');"><i class="fa fa-pencil"></i> Xóa</a>
                                        </td>
                                    </tr>
									<%}}else{ %>
										<tr>
											<td colspan="5" align="center">Không có danh mục nào</td>
										</tr>
									<%} %>
									
                                </tbody>
                            </table>
                            <div class="row">
                                <div class="col-sm-6">
                                 <%
                                if(search == null){ search = "";}
                                Object number_page = request.getAttribute("number_page");
                                int i = Integer.parseInt(number_page.toString());
                                Object index_page1 = request.getAttribute("index_page");
                                int index_page= Integer.parseInt(index_page1.toString());
                                
                                Song song1 = (Song) request.getAttribute("countSong");
                        	
                                
                                %>
                                     <%if(index_page == i){%>
                              		 <div class="dataTables_info" id="dataTables-example_info" style="margin-top:27px">Hiển thị từ <%=(index_page-1)*3+1%> đến <%=song1.getSong()%> của <%=song1.getSong() %> bài hát</div>
                              <%}else{ if(index_page < i){ %>
                              <%if(index_page == 1){ %>
                                    <div class="dataTables_info" id="dataTables-example_info" style="margin-top:27px">Hiển thị từ <%=index_page%> đến <%=index_page*3%> của <%=song1.getSong()%> bài hát</div>
                              <%}else{ %>
                              	    <div class="dataTables_info" id="dataTables-example_info" style="margin-top:27px">Hiển thị từ <%=(index_page-1)*3+1%> đến <%=(index_page-1)*3+3 %> của <%=song1.getSong() %> bài hát</div>
                              	
                              <%}} }%>
                                </div>
                                <div class="col-sm-6" style="text-align: right;">
                                    <div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
                                        <%
	                                       
											if(number_page != null){
											
											%>
                                        <ul class="pagination">
                                            <%if(index_page <= 1){ %>
											 <li class="paginate_button previous disabled" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_previous"><a href="<%=request.getContextPath()%>/admin/song/search?index_page=<%=1%>&search=<%=search%>">Trang trước</a></li>
											<%}else{ %>
											 <li class="" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_previous"><a href="<%=request.getContextPath()%>/admin/song/search?index_page=<%=index_page - 1%>&search=<%=search%>">Trang trước</a></li>
											<%} %>
                                            <%
											for(int i1 = 1; i1 <= i; i1++) {
												if(index_page == i1){
											%>
                                            <li class="paginate_button active" aria-controls="dataTables-example" tabindex="0"><a href="<%=request.getContextPath()%>/admin/song/search?index_page=<%=i1%>&search=<%=search%>"><%=i1%></a></li>
											<%}else{%>
								             <li aria-controls="dataTables-example" tabindex="0" ><a href="<%=request.getContextPath()%>/admin/song/search?index_page=<%=i1%>&search=<%=search%>"><%=i1%></a></li>
												
											<%}}%>
                                            <%if(index_page >= 1 && index_page < i){ %>
											  <li class="paginate_button next" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_next"><a href="<%=request.getContextPath()%>/admin/song/search?index_page=<%=index_page + 1%>&search=<%=search%>">Trang tiếp</a></li>
											<%}else{ %>
											  <li class="paginate_button next disabled" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_next"><a href="<%=request.getContextPath()%>/admin/song/search?index_page=<%=i%>&search=<%=search%>">Trang tiếp</a></li>
											<%} %>
                                        </ul>
                                        <%} %>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
                <!--End Advanced Tables -->
            </div>
        </div>
    </div>
</div>
<script>
    document.getElementById("song").classList.add('active-menu');
</script>
<!-- /. PAGE INNER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>