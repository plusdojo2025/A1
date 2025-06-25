<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- [ 読込 ] jstl を扱えるように --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>訪問地検索結果｜TABI×TILE</title>
<!-- 全体共通css -->
<link rel="stylesheet" href="<c:url value='assets/css/common.css'/>">
<link rel="stylesheet" href="<c:url value='assets/css/custom.css'/>">

<!-- 結果表示 -->
<link rel="stylesheet" href="<c:url value='assets/css/tab.css'/>">
</head>
<body>
<!-- ヘッダー（ここから） -->
	<header>
		<div class="header">
		<div class="parent">
		 <a href="<c:url value='/HomeServlet'/>"><img src="<c:url value='/assets/imgs/TABITILE_logo.png' />" width="300"></a>
		
<!-- ボタン設置 -->
	<div class="menubutton">
		<a href="<c:url value='/GachaServlet'/>"><img src="<c:url value='/assets/imgs/icons/gacha.png' />" width="100" ></a>
		<a href="<c:url value='/QaServlet'/>"><img src="<c:url value='/assets/imgs/icons/qa.png' />" width="100" ></a>
		<a href="<c:url value='/SettingServlet'/>"><img src="<c:url value='/assets/imgs/icons/setting.png' />" width="100" ></a>
		<a href="<c:url value='/LogoutServlet'/>" onclick="showConfirmDialog(); return false;"><img src="<c:url value='/assets/imgs/icons/logout.png' />" width="100" ></a>
	</div>
	</div>
<!-- ニックネーム表示 -->
	<c:if test ="${not empty sessionScope.user_id }">
	<span class="nickname">${sessionScope.user_id.nickname}&nbsp;さん</span>
	</c:if>
	</div>

<!-- メニューバー表示 -->
	<nav>
		<ul>
			<li><a href="<c:url value='/HomeServlet'/>">ホーム</a>
			<li><a href="<c:url value='/VisitorRegistServlet'/>">登録</a>
			<li><a href="<c:url value='/VisitorSearchServlet'/>">検索</a>
			<li><a href="<c:url value='/ListServlet'/>">一覧</a>
		</ul>
	</nav>
	
	</header>
<!-- ヘッダー(ここまで) -->
<!-- メイン（ここから） -->
	<main>
	<!-- 訪問地検索結果一覧 -->
<h2>訪問地検索結果一覧</h2>
<hr>
	<c:forEach var="e" items="${visitorList}" >
	<form method="POST" action="<c:url value='/VisitorSearchServlet'/>">
	<input type="hidden" name="user_id" value="${e.user_id}">
	訪問開始日<input type="text" name="start_date" value="${e.start_date}"><br>
	訪問終了日<input type="text" name="end_date" value="${e.end_date}"><br>
    都道府県<input type="text" name="prefecture_name" value="${e.prefecture_name}"><br>
	場所<input type="text" name="visitor_place" value="${e.visitor_place}"><br>
	タイトル<input type="text" name="title" value="${e.title}"><br>
	同行者<input type="text" name="componion" value="${e.componion}"><br>
	感情<input type="text" name="emotion_name" value="${e.emoji}"><br> 
	感想<input type="text" name="thought" value="${e.thought}"><br>
	
	</form>
	<hr>
</c:forEach>
<c:if test="${empty visitorList}">
<p>指定された条件に一致するデータはありません。</p>
</c:if>
	
	
		
	</main>
<!-- メイン(ここまで) -->

<!-- フッター(ここから) -->
	<div class="footer">
    	<p class="copyright">TABI×TILE　&copy; 2025 Always First.</p>
    	<p>旅のひとコマが、未来を彩るタイルになる</p>
	</div>
<!-- フッター（ここまで） -->

<!-- ダイアログHTML(ここから) -->
<div id="overlay" class="overlay" style="display:none;"></div>
<div id="confirmDialog" class="custom-dialog" style="display:none;">
    <p>ログアウトしてもよろしいですか？</p>
    <img src="<c:url value='/assets/imgs/char/Door.png'/>" alt="ログアウト確認">
    <div class="dialog-buttons">
        <button class="btn-no" onclick="handleConfirm(false)">キャンセル</button>
        <button class="btn-yes" onclick="handleConfirm(true)">ログアウト</button>
    </div>
</div>
<!-- ダイアログHTML(ここまで) -->

<!-- ダイアログJS(ここから) -->
<script>
function showConfirmDialog() {
    document.getElementById('overlay').style.display = 'block';
    document.getElementById('confirmDialog').style.display = 'block';
}

function handleConfirm(isConfirmed) {
    document.getElementById('overlay').style.display = 'none';
    document.getElementById('confirmDialog').style.display = 'none';
    if (isConfirmed) {
        window.location.href = '<c:url value="/LogoutServlet" />';
    }
}
</script>
<!-- ダイアログJS(ここまで) -->
</body>
</html>