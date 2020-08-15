<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>${pageContext.request.getAttribute("userA").username}</h1>
    <h1>${pageContext.request.getAttribute("msg")}</h1>
</body>
</html>
