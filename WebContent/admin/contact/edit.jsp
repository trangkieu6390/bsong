<%@page import="model.bean.Contact"%>
<%@page import="model.bean.Category"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>Sửa liên hệ</h2>
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
                                <form method="post" action="<%=request.getContextPath()%>/admin/contact/edit-data">
                                    <div class="form-group">
                                    <%
                                    	Contact getItemById =(Contact) request.getAttribute("getContactById");
                                    %>
                                    	
                                        <input type="hidden" value="<%=getItemById.getId()%>" id="id" name="id" class="form-control" readonly="readonly"/>
                                        <label for="name">name</label>
                                        <input type="text" value="<%=getItemById.getName()%>" id="name" name="name" class="form-control" />
                                        <label for="name">Email</label>
                                        <input type="text" value="<%=getItemById.getEmail()%>" id="email" name="email" class="form-control" />
                                        
                                        <label for="name">Website</label>
                                        <input type="text" value="<%=getItemById.getWebsite()%>" id="website" name="website" class="form-control" />
                                        
                                        <label for="name">Message</label>
                                        <input type="text" value="<%=getItemById.getMessage()%>" id="message" name="message" class="form-control" />
                                        
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