<%@page import="kr.or.kosa.dto.Reply"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.kosa.dto.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

	<c:choose>
		<c:when test="${requestScope.bookMarking == 0}">
			<a href='#' onclick="alert('북마크 해제!');"><img src='./image/checked_bookmark.png' width="17px" heigth="20px" id='bookmarking_img'>
			
		</c:when>
		<c:otherwise>
			<a href='#' onclick="alert('북마크 성공!');"><img src='./image/empty_bookmark.png' width="17px" heigth="20px" id='bookmarking_img'></a>
			
		</c:otherwise>
	</c:choose>
	
