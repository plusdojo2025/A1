<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- [ 読込 ] jstl を扱えるように --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- [ 短縮 ] コンテキストパス --%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%-- [ 短縮 ] アセッツパス --%>
<c:set var="assetsPath" value="${contextPath}/assets" />
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>｜TABI×TILE</title>
<link >
</head>
<body>

<!-- ヘッダー（ここから） -->
	<header>
		<h1>
		 <a href="${contextPath}/HomeServlet">TABI×TILE</a>
		</h1>
	
	<nav>
		<ul>
			<li><a href="${contextPath}/HomeServlet">ホーム</a>
			<li><a href="${contextPath}/VisitorRegistServlet">登録</a>
			<li><a href="${contextPath}/VisitorSearchServlet">検索</a>
			<li><a href="${contextPath}/VisitorListServlet">一覧</a>
		</ul>
	</nav>
	
	<!-- ボタン設置 -->
	<div class="">
	<a href="${contextPath}/GachaServlet">ガチャ</a>
		<a href="${contextPath}/QaServlet">QA</a>
		<a href="${contextPath}/SettingServlet">設定</a>
		<a href="${contextPath}/LogoutServlet">ログアウト</a>
	</div>
	
	</header>
<!-- ヘッダー(ここまで) -->

<!-- メイン（ここから） -->
	<main>

	</main>
<!-- メイン(ここまで) -->

<!-- フッター(ここから) -->
	<div class="footer">
    	<p class="copyright">TABI×TILE　&copy; 2025 Always First.</p>
    	<p>旅のひとコマが、未来を彩るタイルになる</p>
	</div>
<!-- フッター（ここまで） -->

</body>
</html>