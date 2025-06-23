<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- [ 読込 ] jstl を扱えるように --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- [ 短縮 ] コンテキストパス --%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%-- [ 短縮 ] アセッツパス --%>
<c:set var="assetsPath" value="${contextPath}/assets" />

<!doctype html>
<html>
<head>
	<meta charset="UTF-8">
	<title>TABI×TILE</title>
	<!-- 全体共通css -->
	<link rel="stylesheet" href="<c:url value='assets/css/common.css'/>">
	<link rel="stylesheet" href="<c:url value='assets/css/custom.css'/>">
	
	<!-- Q&A画面用css -->
	<link rel="stylesheet" href="<c:url value='assets/css/qa.css'/>">
	
</head>
<body>
<!-- ヘッダー（ここから） -->
	<header>
		<div class="parent">
			<h1>
				<a href="<c:url value='/HomeServlet'/>"><img src="<c:url value='/assets/imgs/TABITILE_logo.png' />" width="250"></a>
			</h1>
			
<!-- ボタン設置 -->
			<div class="menubutton">
				<a href="<c:url value='/GachaServlet'/>"><img src="<c:url value='/assets/imgs/icons/gacha.png' />" width="50" ></a>
				<a href="<c:url value='/QaServlet'/>"><img src="<c:url value='/assets/imgs/icons/qa.png' />" width="50" ></a>
				<a href="<c:url value='/SettingServlet'/>"><img src="<c:url value='/assets/imgs/icons/setting.png' />" width="50" ></a>
				<a href="<c:url value='/LogoutServlet'/>" onclick="showConfirmDialog(); return false;"><img src="<c:url value='/assets/imgs/icons/logout.png' />" width="50" ></a>
			</div>
			
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
		</div>
<!-- ニックネーム表示 -->
		<c:if test ="${not empty sessionScope.user_id }">
		<span class="nickname">${sessionScope.user_id.nickname}&nbsp;さん</span>
		</c:if>
	
<!-- メニューバー表示 -->
		<nav>
			<ul>
				<li class="home"><a href="<c:url value='/HomeServlet'/>">ホーム</a>
				<li class="regist"><a href="<c:url value='/VisitorRegistServlet'/>">登録</a>
				<li class="search"><a href="<c:url value='/VisitorSearchServlet'/>">検索</a>
				<li class="list"><a href="<c:url value='/VisitorListServlet'/>">一覧</a>
			</ul>
		</nav>
	</header>	
<!-- ヘッダー(ここまで) -->

<!-- メイン（ここから） -->
	<main>
		<p>ログイン・ログアウトについて</p>
			<details>
				<summary>Q.ログイン時にパスワードを忘れてしまった。</summary>
					<div class=text>
						A.設定画面からパスワードの再設定ができます。
					</div>
			</details>
			<details>
				<summary>Q.IDの変更をしたい</summary>
					<div>
						A.ユーザーIDは一度登録すると変更ができないものとなっております。ご了承ください。
					</div>
			</details>
			<details>
				<summary>Q.ログイン時に「入力内容が間違っています。」と表示されます。</summary>
					<div class=text>
						A.以下のチェック項目を参考に、入力内容に誤りがないかご確認ください。<br>
					<ul class=qa>
						<li>・半角英数字で入力されているかどうか</li>
						<li>・前後にスペースが入っていないか</li>
					</ul>
					</div>
			</details>
			<details>
				<summary>Q.ログアウトするには</summary>
					<div class=text>
						A.右上の「画像」を選択するとログアウトできます。
					</div>
			</details>
		<p>訪問地、候補地の登録について</p>
			<details>
				<summary>Q.「訪問地」に関する登録をするには</summary>
					<div class=text>
						A.登録画面から「訪れた場所に関する登録」を選択し、必要な項目を入力してから登録ボタンを押してください。
					</div>
			</details>
			<details>
				<summary>Q.「候補地」に関する登録をするには</summary>
					<div class=text>
						A.登録画面から「訪れた場所に関する登録」を選択し、必要な項目を入力してから登録ボタンを押してください。
					</div>
			</details>
				<details>
				<summary>Q.「訪問地」に関する登録の際、画像の登録枚数に上限はありますか？</summary>
					<div class=text>
						A.画像は5枚まで登録が可能となっております。
					</div>
			</details>
			<details>
				<summary>Q.「候補地」に登録した場所に訪れた場合はどうしたらいいですか？</summary>
					<div class=text>
						A.当該候補地の詳細画面にある「移動」をクリックし、必要な項目を入力の上ご登録ください。
					</div>
			</details>
		<p>称号について</p>
			<details>
				<summary>Q.獲得した称号はどこで確認ができますか？</summary>
					<div class=text>
						A.獲得した称号はホーム画面から閲覧が可能です。
					</div>
			</details>
			<details>
				<summary>Q.獲得した称号が削除されていました。</summary>
					<div class=text>
						A.称号は「訪問地」リストに登録された情報を基に付与しているため<br>
						「訪問地」リストに登録した情報を削除すると獲得した称号を失う可能性があります。ご了承ください。
					</div>
			</details>
			<p>ガチャについて</p>
				<details>
				<summary>Q.ガチャは何回まで引けますか？</summary>
					<div class=text>
						A.ガチャは1日1回までとなっております。
					</div>
			</details>
	</main>

	<footer class="footer">
		<p class="copyright">TABI×TILE　&copy; 2025 Always First.</p>
		<p>旅のひとコマが、未来を彩るタイルになる</p>
	</footer>
	
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