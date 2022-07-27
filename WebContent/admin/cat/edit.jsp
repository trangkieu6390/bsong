﻿<%@page import="model.bean.Category"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>Sửa danh mục</h2>
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
                                <form method="post" >
                                    <div class="form-group">
                                    <%
                                    	Category getItemById =(Category) request.getAttribute("getCatById");
                                   
                                    	String name = request.getParameter("name");
                                    	String err = request.getParameter("err");
                						
        								if("1".equals(err)){
        									out.print("<p style = 'color: red;'> Có lỗi xảy ra</p>");
        								
        								}
        								if(getItemById != null){
											name = getItemById.getName();
										}
        						%>
										
										
                                        <label for="name">Tên danh mục</label>
                                        <input type="text" value="<%if(name != null){out.print(name);} %>" id="name" name="name" class="form-control" />
                                    </div>
                                    <button type="submit" name="submit" class="btn btn-success btn-md">Sửa</button>
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
    document.getElementById("category").classList.add('active-menu');
</script>
<!-- /. PAGE WRAPPER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>