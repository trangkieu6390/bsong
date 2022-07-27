﻿<%@page import="model.bean.Song"%>
<%@page import="model.bean.Category"%>
<%@page import="model.dao.CategoryDAO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>Thêm bài hát</h2>
            </div>
        </div>
        <!-- /. ROW  -->
        <hr />
        <div class="row">
            <div class="col-md-12">
                <!-- Form Elements -->
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-md-12">
                                <form method="post" action="<%=request.getContextPath()%>/admin/song/edit-data" enctype="multipart/form-data">
                                    <div class="form-group">
                                    <%
                                    	Song song = (Song)request.getAttribute("getSongtById");
                                    %>
                                     <input type="hidden" value="<%=song.getId()%>" id="id" name="id" class="form-control" readonly="readonly"/>
                                        <label for="name">Tên bài hát</label>
                                         <input type="text" value="<%=song.getName() %>" id="name" name="name" class="form-control" />
                                    </div>
                                    <div class="form-group">
                                    
                                        <label for="category">Danh mục bài hát</label>
                                        <select id="category" name="category" class="form-control">
                                        <option value="<%=song.getCats()%>"><%=song.getCats()%></option>
											 <%
												CategoryDAO cats = new CategoryDAO();
												ArrayList<Category> item = cats.getItems();
                                        		
												if(item != null){
												for(Category cat : item){
								
											%>
											
											<option value="<%=cat.getId()%>"><%=cat.getName()%></option>
											
										<%}} %>
											
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="picture">Hình ảnh</label>
                                        <input type="file" name="picture"/>
                                        
                                    </div>
                                    <div class="form-group">
                                        <label for="preview">Mô tả</label>
                                        <textarea id="preview" class="form-control" rows="3" name="preview"><%=song.getPreview_text() %></textarea>
                                    </div>
                                    <div class="form-group">
                                        <label for="detail">Chi tiết</label>
                                        <textarea id="detail" class="form-control" id="detail" rows="5" name="detail"><%=song.getDetail_text() %> </textarea>
                                    </div>
                                    <input type="submit" value="Thêm" class="btn btn-success btn-md" />
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- End Form Elements -->
            </div>
        </div>
        <!-- /. ROW  -->
    </div>
    <!-- /. PAGE INNER  -->
</div>
<script>
    document.getElementById("song").classList.add('active-menu');
</script>
<!-- /. PAGE WRAPPER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>