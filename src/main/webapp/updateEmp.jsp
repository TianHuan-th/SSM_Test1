<%--
  Created by IntelliJ IDEA.
  User: 旗木卡卡西
  Date: 2020/7/22
  Time: 15:43
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<html>
<head>
    <title>修改员工</title>
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
</head>
<body>
<form action="/emp.do" method="post" enctype="multipart/form-data">
    <input type="hidden" name="_method" value="PUT" >
    <input type="hidden" name="empid" value="${emp.empid}">
    姓名:<input type="text" name="name" value="${emp.name}"  /><br>
    性别:<input type="radio" name="sex" value="男" ${emp.sex eq '男' ? 'checked':''} />男
    <input type="radio" name="sex" value="女" ${emp.sex eq '女'?'checked':''} />女<br>
    身份证附件:<input type="file" name="idfile" /><br>
    选择一个部门:<select id="ses" name="did">
    <c:forEach items="${deptAll}" var="dept">
        <option value="${dept.deptId}" ${dept.deptId == emp.did?'selected':''} >${dept.dname}</option>
    </c:forEach>
</select><br>
    选择职位(可多选):
    <c:set var="arr" value="${fn:split(emp.pid,',' )}"></c:set>
    <c:forEach items="${posAll}" var="pos">
    <input type="checkbox" name="pid" value="${pos.posid}"
            <c:forEach items="${arr}" var="str">
                ${str == pos.posid ? 'checked' : ''}
            </c:forEach>
    />${pos.posname}
</c:forEach>
    <br><input type="submit" value="修改" />
</form>
</body>
</html>
