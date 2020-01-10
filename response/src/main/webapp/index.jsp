<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2020/1/6
  Time: 11:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
  <head>
    <title>response</title>
    <script src="js/jquery.min.js"></script>
    <script>
      //页面加载 绑定单击时间
      //异步响应
      $(function () {
        $("#btn1").click(function () {
          $.ajax({
            //编写json格式，设置属性和值
            url:"user/testJson2Bean",
            contentType:"application/json;charset=UTF-8",
            data:'{"username":"刘炎", "password":"5211", "money":1000}',
            dataType:"json",
            type:"post",
            success:function (data) {
              //data服务器响应的json数据，进行解析
              alert(data);
              alert(data.username);
              alert(data.password);
              alert(data.money);
            }
          });
          // alert("hello btn!")
        })
      })

    </script>
  </head>
  <body>
  <h2>跨服务器上传文件成功</h2>

<h1>Response</h1>
<a href="hello/string">testString</a><br/>
  <a href="user/testString">update</a><br/>

<h3>testVoid</h3>
<a href="user/testVoid">testVoid</a>

<h3>testModelAndView</h3>
<a href="user/testModelAndView">testModelAndVIew</a>

<h3>testForwardOrRedirect</h3>
<a href="user/testForwardOrRedirect">testForwardOrRedirect</a>

  <h3>testException</h3>
  <a href="user/textException">testException</a>

<h3>ajax</h3>
<button id="btn1">ajax请求</button>
  <h3>修改用户</h3>


<form action="user/testString" method="post">
  用户名称：<input type="text" name="username" value="${user.username}"><br/>
  用户密码：<input type="text" name="password" value="${user.password}"><br/>
  用户金额：<input type="text" name="monty" value="${user.money}"><br/>
  <input type="submit" value="提交"></form>

  </body>
</html>
