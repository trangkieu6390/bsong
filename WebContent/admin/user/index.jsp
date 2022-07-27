<%@page import="model.dao.UserDao"%>
<%@page import="model.bean.User"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>Quản lý người dùng</h2>
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
									out.print("<p style = 'color: red;'>Xóa người dùng thành công</p>");
								}else if(msg == 2){
									out.print("<p style = 'color: red;'>Sửa người dùng thành công</p>");
								}else if(msg == 3){
									out.print("<p style = 'color: red;'>Thêm người dùng thành công</p>");
								}
							}
						%>
                <!-- Advanced Tables -->
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="table-responsive">
                            <div class="row">
                                <div class="col-sm-6">
                                <%
                                	String search = (String) request.getAttribute("search");
                                %>
                                    <a href="<%=request.getContextPath()%>/admin/user/add" class="btn btn-success btn-md">Thêm</a>
                                </div>
                                <div class="col-sm-6" style="text-align: right;">
                                    <form method="post" action="<%=request.getContextPath()%>/admin/user/search">
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
                                        <th>Username</th>
                                        <th>Password</th>
                                        <th>Fullname</th>
                                        <th width="160px">Chức năng</th>
                                    </tr>
                                </thead>
                                <tbody>
                              	<%
                                	@SuppressWarnings("unchecked")
                                	ArrayList<User>listCat = (ArrayList<User>) request.getAttribute("getItems");
                                	if(listCat != null && listCat.size() >0){
                                		for(User items : listCat){
                                %>
                                    <tr>
                                        <td><%=items.getId() %></td>
                                        <td class="center"><%=items.getUsername() %></td>
                                        <td class="center"><%=items.getPassword() %></td>
                                        <td class="center"><%=items.getFullname() %></td>
                                        
                                        <td class="center">
                                            <a href="<%=request.getContextPath()%>/admin/user/edit?id=<%=items.getId()%>" title="" class="btn btn-primary"><i class="fa fa-edit "></i> Sửa</a>
                                            <a href="<%=request.getContextPath()%>/admin/user/del?id=<%=items.getId()%>" title="" class="btn btn-danger" onclick="return confirm('Are you sure?');"><i class="fa fa-pencil"></i> Xóa</a>
                                        </td>
                                    </tr>
									<%}} %>
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
	                                User user = (User) request.getAttribute("countUser");
                                %>
                                   <%if(index_page == i){%>
                              		 <div class="dataTables_info" id="dataTables-example_info" style="margin-top:27px">Hiển thị từ <%=(index_page-1)*3+1%> đến <%=user.getCount()%> của <%=user.getCount() %> Người dùng</div>
                              <%}else{ if(index_page < i){ %>
                              <%if(index_page == 1){ %>
                                    <div class="dataTables_info" id="dataTables-example_info" style="margin-top:27px">Hiển thị từ <%=index_page%> đến <%=index_page*3%> của <%=user.getCount() %> Người dùng</div>
                              <%}else{ %>
                              	    <div class="dataTables_info" id="dataTables-example_info" style="margin-top:27px">Hiển thị từ <%=(index_page-1)*3+1%> đến <%=(index_page-1)*3+3 %> của <%=user.getCount() %> Người dùng</div>
                              	
                              <%}} }%>
                                </div>
                                <div class="col-sm-6" style="text-align: right;">
                                    <div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
                                    <%
	                                       
											if(number_page != null){
											
											%>
                                        <ul class="pagination">
                                                <%if(index_page <= 1){ %>
											 <li class="paginate_button previous disabled" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_previous"><a href="<%=request.getContextPath()%>/admin/user/search?index_page=<%=1%>&search=<%=search%>">Trang trước</a></li>
											<%}else{ %>
											 <li class="" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_previous"><a href="<%=request.getContextPath()%>/admin/user/search?index_page=<%=index_page - 1%>&search=<%=search%>">Trang trước</a></li>
											<%} %>
											<%
											for(int i1 = 1; i1 <= i; i1++) {
												if(index_page == i1){
											%>
                                            <li class="paginate_button active" aria-controls="dataTables-example" tabindex="0" ><a href="<%=request.getContextPath()%>/admin/user/search?index_page=<%=i1%>&search=<%=search%>"><%=i1%></a></li>
											<%}else{%>
								             <li aria-controls="dataTables-example" tabindex="0" ><a href="<%=request.getContextPath()%>/admin/user/search?index_page=<%=i1%>&search=<%=search%>"><%=i1%></a></li>
												
											<%}}%>
											
											 <%if(index_page >= 1 && index_page < i){ %>
											  <li class="paginate_button next" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_next"><a href="<%=request.getContextPath()%>/admin/user/search?index_page=<%=index_page + 1%>&search=<%=search%>">Trang tiếp</a></li>
											<%}else{ %>
											  <li class="paginate_button next disabled" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_next"><a href="<%=request.getContextPath()%>/admin/user/search?index_page=<%=i%>&search=<%=search%>">Trang tiếp</a></li>
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
    document.getElementById("user").classList.add('active-menu');
</script>
<!-- /. PAGE INNER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>