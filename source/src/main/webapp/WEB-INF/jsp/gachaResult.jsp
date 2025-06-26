<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>ガチャ結果｜TABI×TILE</title>
<!-- 全体共通css -->
<link rel="stylesheet" href="<c:url value='assets/css/common.css'/>">
<link rel="stylesheet" href="<c:url value='assets/css/custom.css'/>">
<!-- ガチャ画面用css -->
<link rel="stylesheet" href="<c:url value='assets/css/gacha.css'/>">

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
			<li class="home"><a href="<c:url value='/HomeServlet'/>">ホーム</a>
			<li class="regist"><a href="<c:url value='/VisitorRegistServlet'/>">登録</a>
			<li class="search"><a href="<c:url value='/VisitorSearchServlet'/>">検索</a>
			<li class="list"><a href="<c:url value='/ListServlet'/>">一覧</a>
		</ul>
	</nav>
	
	</header>
<!-- ヘッダー(ここまで) -->

<!-- メイン（ここから） -->
	<main>
		<img src="<c:url value='/assets/imgs/icons/kapsel_open.png' />" alt="カプセル画像" width="120">

    <div class="balloon">
        <c:choose>
            <c:when test="${not empty sessionScope.selectedPickup}">
                <p>${sessionScope.selectedPickup.pickup_place}</p>
                <p><strong>地域：${sessionScope.selectedPickup.prefecture_name}</strong> に決定！！</p>
                <p>メモ：${sessionScope.selectedPickup.remarks}</p>

                <p><a class="goto-link" href="<c:url value='/PickupServlet?pk=${sessionScope.selectedPickup.pickup_id}' />">
					▶ 行きたいリストへ
				</a></p>
            </c:when>
            <c:otherwise>
                <%-- <p>行きたい場所が登録されていません。</p>
                <a href="<c:url value='/PickupRegistServlet' />">場所を登録する</a> --%>
                <div class="error-panel">
				    <img src="<c:url value='/assets/imgs/char/Ojigi.png' />" alt="エラー" class="error-icon" />
				
				    <p class="error-message">本日はもう引いています。</p>
				
				    <div class="error-buttons">
				        <a href="<c:url value='/GachaResultServlet'/>" class="link">結果を確認する</a>
				    </div>
				</div>
            </c:otherwise>
        </c:choose>
    </div>
    <div class="returned-buttons">
			<a href="<c:url value='/HomeServlet'/>">ホームに戻る</a>
	</div>
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