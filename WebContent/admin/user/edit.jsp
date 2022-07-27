
<%@page import="model.bean.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>Sửa Người dùng</h2>
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
                                <form method="post" action="<%=request.getContextPath()%>/admin/user/edit-data">
                                    <div class="form-group">
                                    <%
                                    	User getItemById =(User) request.getAttribute("getUserById");
                                    %>
                                        <input type="hidden" value="<%=getItemById.getId() %>" id="id" name="id" class="form-control" readonly="readonly"/>
                                        <label for="name">Tên user</label>
                                        <input type="text" value="<%=getItemById.getUsername() %>" id="name" name="name" class="form-control" />
                                        
                                        <label for="name">Tên user</label>
                                        <input type="text" value="<%=getItemById.getPassword() %>" id="password" name="password" class="form-control" />
                                        
                                        <label for="name">Tên user</label>
                                        <input type="text" value="<%=getItemById.getFullname() %>" id="fullname" name="fullname" class="form-control" />
                                    </div>
                                    <input type="submit" value="Edit" class="btn btn-success btn-md" />
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