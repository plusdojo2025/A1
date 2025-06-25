<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>Now Loading...｜TABI×TILE</title>
<!-- 全体共通css -->
<link rel="stylesheet" href="<c:url value='assets/css/common.css'/>">
<!-- ガチャ画面用css -->
<link rel="stylesheet" href="<c:url value='assets/css/gacha.css'/>">
</head>
<body>

<!-- ローディング画面 -->
<div class="loading-wrapper">
	<div class="loading-icon" style="margin: 40px auto;"></div>
	<div class="loading-text">Now Loading・・・</div>
</div>

<!-- 自動遷移（3秒後） -->
<script>
	setTimeout(function() {
		window.location.href = "<c:url value='/CapsuleServlet' />";
	}, 3000);
</script>

</body>
</html>
