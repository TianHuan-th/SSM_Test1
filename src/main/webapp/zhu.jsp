<%--
  Created by IntelliJ IDEA.
  User: 旗木卡卡西
  Date: 2020/7/22
  Time: 9:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<html>
<head>
    <title>主页</title>
    <script src="https://code.jquery.com/jquery-3.1.1.min.js" ></script>
    <script>
        $(function(){
            $.get("/emp.do",function(data){
                var str="<tr><th>员工姓名</th><th>员工性别</th><th>身份证附件</th><th>所属部门</th><th>职位</th><th>操作</th></tr>";
                if(null!=data){
                    for(var i=0;i<data.length;i++){
                        str+="<tr><td>"+data[i].name+"</td><td>"+data[i].sex+"</td><td><img src='/"+data[i].idcard+"' style='width: 50px;height: 50px;'/></td><td>"+data[i].dname+"</td><td>"
                            +data[i].posname+"</td><td><a href='/emp/"+data[i].empid+".do' >修改</a><input type='button' value='删除' onclick='dele("+data[i].empid+")' /> </td></tr>"
                    }
                    $("#tab").html(str);
                }
            });

            $("#sub").click(function(){
                $.get("/emp.do?name="+$("#a1").val()+"&did="+$("#a2").val()+"&pid="+$("#a3").val(),function(data){
                    var str="<tr><th>员工姓名</th><th>员工性别</th><th>身份证附件</th><th>所属部门</th><th>职位</th><th>操作</th></tr>";
                    if(null!=data){
                        for(var i=0;i<data.length;i++){
                            str+="<tr><td>"+data[i].name+"</td><td>"+data[i].sex+"</td><td><img src='/"+data[i].idcard+"' style='width: 50px;height: 50px;' ></td><td>"+data[i].dname+"</td><td>"
                                +data[i].posname+"</td><td><a href='/emp/"+data[i].empid+".do' >修改</a><input type='button' value='删除' onclick='dele("+data[i].empid+")' /> </td></tr>"
                        }
                        $("#tab").html(str);
                    }else{
                        $("#tab").html("<tr>没有数据展示</tr>");
                    }
                });

            });
        });
        
        var add=function () {
            location.href="add.jsp";
        }

        var dele=function(id){
            $.ajax({
                type:"POST",
                url:"emp/"+id+".do",
                data:{
                    "_method":"DELETE",
                },
                success:function (data) {
                    location.reload();
                },
                error:function(data){
                    alert("删除失败");
                }
            });
        }
    </script>
</head>
<body>
姓名：<input type="text" name="name" id="a1">
部门：<select name="did" id="a2">
    <option value="0">请选择一个部门</option>
    <option value="1001">经理部</option>
    <option value="1003">人事部</option>
    <option value="1002">营销部</option>
    <option value="1004">售后部</option>
</select>
职位：<select name="pid" id="a3">
    <option value="0">请选择一个职位</option>
    <option value="101">员工</option>
    <option value="102">组长</option>
    <option value="103">经理</option>
    <option value="104">总监</option>
    <option value="105">董事长</option>
</select>
    <input type="button" value="查询" id="sub" /><br>
<input type="button" value="添加" onclick="add()" />
<table border="2" id="tab">
</table>
</body>
</html>
