<%--
  Created by IntelliJ IDEA.
  User: 旗木卡卡西
  Date: 2020/7/22
  Time: 11:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<html>
<head>
    <title>添加</title>
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script>
        $(function(){
            $.get("/dept.do",function(data){
                if(null!=data){
                   var str="";
                   for(var i=0;i<data.length;i++){
                       str+="<option value='"+data[i].deptId+"'>"+data[i].dname+"</option>"
                   }
                   $("#ses").html(str);
                }
            });

            $.get("/position.do",function(data){
                if(null!=data){
                    var str="";
                    for(var i=0;i<data.length;i++){
                        str+="<input type='checkbox' value='"+data[i].posid+"' name='pid' />"+data[i].posname
                    }
                    $("#pid").html(str);
                }
            });
        });
    </script>
</head>
<body>
<form action="/emp.do" method="post" enctype="multipart/form-data">
    姓名:<input type="text" name="name" /><br>
    性别:<input type="radio" name="sex" value="男">男
    <input type="radio" name="sex" value="女">女<br>
    身份证附件:<input type="file" name="idfile" /><br>
    选择一个部门:<select id="ses" name="did">
                </select><br>
    选择职位(可多选):<span id="pid"></span>
    <input type="submit" value="添加" />
</form>
</body>
</html>
