<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="delete.do" method="post">
		<input type="text" name="sno">
		<input type="submit" value="删除">
	</form>
	<form action="add.do" method="post">
		学号：<input type="text" name="sno"><br>
		姓名：<input type="text" name="sname"><br>
		年龄：<input type="text" name="sage">
		<input type="submit" value="添加">
	</form>
	<form action="find.do" method="post">
		<input type="submit" value="全部查询">
		<table cellspacing="0" cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
							<tr
								style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

								<td align="center" width="18%">序号</td>
								<td align="center" width="17%">学号</td>
								<td align="center" width="17%">姓名</td>
								<td align="center" width="17%">年龄</td>
								<td align="center" width="17%">编辑</td>
							</tr>
							<c:forEach items="${list}" var="p" varStatus="status">
							<tr onmouseover="this.style.backgroundColor = 'white'"
								onmouseout="this.style.backgroundColor = '#F5FAFE';">
								<td style="CURSOR: hand; HEIGHT: 22px" align="center"
									width="18%">${status.count}</td>
								<td style="CURSOR: hand; HEIGHT: 22px" align="center"
									width="17%">${p.sno}</td>
								<td style="CURSOR: hand; HEIGHT: 22px" align="center"
									width="17%">${p.sname}</td>
								<td style="CURSOR: hand; HEIGHT: 22px" align="center"
									width="17%">${p.sage}</td>
								<td align="center" style="HEIGHT: 22px"><a
									href="/DoubleSpringMybatis/findOne.do?sno=${p.sno}">
										<img
										src="${pageContext.request.contextPath}/i_edit.gif"
										border="0" style="CURSOR: hand">
								</a></td>
							</tr>
							</c:forEach>
							
		</table>
	</form>
	
</body>
</html>