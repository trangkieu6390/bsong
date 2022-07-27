<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>Thêm liên hệ</h2>
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
                                <form action="<%=request.getContextPath()%>/admin/contact/add" role="form" method="post" id="form">
                                    <div class="form-group">
                                        <label for="name">Tên liên hệ</label>
                                        <input type="text" id="name" value="" name="name" class="form-control" />
                                        <label for="email">Email</label>
                                        <input type="text" id="email" value="" name="email" class="form-control" />
                                        <label for="website">Website</label>
                                        <input type="text" id="website" value="" name="website" class="form-control" />
                                        <label for="message">Message</label>
                                        <input type="text" id="message" value="" name="message" class="form-control" />
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
    document.getElementById("category").classList.add('active-menu');
</script>
<!-- /. PAGE WRAPPER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>