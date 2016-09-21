<%@ page language="java" pageEncoding="utf-8" contentType="text/html;charset=utf-8"%>
<html>
<head>
<title>In bao cao</title>
</head>
<body style="zoom:130%;margin:0px;padding:0px;text-align:center;" >
<jsp:include page='<%=request.getParameter("filePrint")%>' />
<script type="text/javascript">
function init(){}
window.print();
</script>
</body>
</html>



