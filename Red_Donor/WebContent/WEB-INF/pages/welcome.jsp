<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<html>
<body>
<h1>Spring MVC Multi Actions Annotation Example</h1>
 
<tag:notloggedin>
 not logged in
</tag:notloggedin>
<tag:loggedin>
  <h1>Welcome ${facebook.name} (${facebook.id})</h1>
  <form action="./post" method="post">
    <textarea cols="80" rows="2" name="message"></textarea>
    <input type="submit" name="post" value="statuses" />
  </form>
<a href="./logout">logout</a>
</tag:loggedin>
</body>
</html>