<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- [ 読込 ] jstl を扱えるように --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>	一覧｜TABI×TILE</title>
<link rel="stylesheet" href="<c:url value='assets/css/common.css'/>" >
<link rel="stylesheet" href="<c:url value='assets/css/custom.css'/>">
<link rel="styleesheet" href="<c:url value='assets/css/list.css'/>" >
</head>
<body>

<!-- ヘッダー（ここから） -->
	<header>
		<h1>
		 <a href="<c:url value='/HomeServlet'/>">TABI×TILE</a>
		</h1>
	
	<nav>
		<ul>
			<li><a href="<c:url value='/HomeServlet'/>">ホーム</a>
			<li><a href="<c:url value='/RegistServlet'/>">登録</a>
			<li><a href="<c:url value='/VisitorServlet'/>">検索</a>
			<li><a href="<c:url value='/VisitorListServlet'/>">一覧</a>
		</ul>
	</nav>
	
	<!-- ボタン設置 -->
	<div class="">
		<a href="<c:url value='/GachaServlet'/>">ガチャ</a>
		<a href="<c:url value='/QaServlet'/>">QA</a>
		<a href="<c:url value='/SettingServlet'/>">設定</a>
		<a href="<c:url value='/LogoutServlet'/>" onclick="showConfirmDialog(); return false;">ログアウト</a>
	</div>
	
	</header>
<!-- ヘッダー(ここまで) -->

<!-- メイン（ここから） -->
	<main>
	
		<div class="tab_wrap">
			<input id="tab1" type="radio" name="tab_btn" checked>
			<input id="tab2" type="radio" name="tab_btn">

			<div class="tab_area">
				<label class="tab1_label" for="tab1">訪問地一覧</label>
				<label class="tab2_label" for="tab2">候補地一覧</label>
			</div>
		
		<div class="panel_area">
			<!-- ▼ 訪問地 一覧 -->
				<div class="tab_panel" id="panel1">
					<h2>訪問地一覧</h2>
					<div class="">
						<c:forEach var="visitor" items="${visitorList}">
							<div class= "card">
								<h3>${visitor.start_date} の思い出</h3>
								<p>${visitor.prefecture_id}</p>
								<p>${visitor.place}</p>
							</div>	
						</c:forEach>
					</div>
				</div>
			
			<!-- ▼ 候補地 一覧 -->
				<div class="tab_panel" id="panel2">
					<h2>候補地一覧</h2>
					<div class="">
						<c:forEach var="pickup" items="${pickupList}">
							<div class= "card">
								<p>${pickup.prefecture_id} </p>
								<p>${pickup.place}</p>
								<p>${pickup.remarks}</p>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
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
    <img src="<c:url value='/assets/images/char/Door.png'/>" alt="ログアウト確認">
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