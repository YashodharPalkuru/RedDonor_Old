<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<html>
<head>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
<title>Sign in with Facebook example</title>
</head>
<body>
<tag:notloggedin>
  <a href="/Red_Donor/login.htm">Sign in with Facebook</a>
</tag:notloggedin>
<tag:loggedin>
<script>
alert("hai");
</script>
</tag:loggedin>
</body>
</html>