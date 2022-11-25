<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="java.util.List"%>
<%@page import="kr.or.kosa.dto.Board"%>
<%@page import="kr.or.kosa.dao.MemberDao" %>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>




</head>
<body>
<tbody>
	<%
		MemberDao memberdao = new MemberDao();
		List<Bookmarks> bookmarklist = memberdao.getList(userID, pageNumber);
		for(int i=0; i<list.size(); i++){	
	%>
	<tr>
		<td><%= list.get(i).getBbsID() %></td>
		<td><a href="view.jsp?boardID=<%=list.get(i).getBoardID()%>&bbsID=<%= list.get(i).getBbsID() %>"><%= list.get(i).getBbsTitle().replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">","&gt;").replaceAll("\n","<br>") %></a></td>
		<td><%= list.get(i).getUserID() %></td>
		<td><%= list.get(i).getBbsDate().substring(0,11) + list.get(i).getBbsDate().substring(11,13) + "시" + list.get(i).getBbsDate().substring(14,16) + "분" %></td>
	</tr>
	<%
		}
	%>
</tbody>
</body>
</html>