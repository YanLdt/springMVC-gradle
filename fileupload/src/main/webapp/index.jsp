<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2020/1/6
  Time: 15:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
<h1>FileUpLoad</h1>

  <form action="upload/fileupload1" method="post" enctype="multipart/form-data">
    选择文件：<input type="file" name="upload"/><br/>
    <input type="submit" value="上传"/>
  </form>

<form action="upload/fileupload2" method="post" enctype="multipart/form-data">
  选择文件：<input type="file" name="upload"/><br/>
  <input type="submit" value="上传"/>
</form>

<form action="upload/fileupload3" method="post" enctype="multipart/form-data">
  选择文件：<input type="file" name="upload"/><br/>
  <input type="submit" value="上传"/>
</form>
  </body>
</html>
