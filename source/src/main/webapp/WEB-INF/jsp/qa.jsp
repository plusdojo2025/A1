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
</head>
<body>
	<header>
		<h1>
		 	<a href="${contextPath}//HomeServlet">TABI×TILE</a>
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
	<main>
		<p>ログイン・ログアウトについて</p>
			<details>
				<summary>Q.ログイン時にパスワードを忘れてしまった。</summary>
					<div>
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
						A.以下のチェック項目を参考に、入力内容に誤りがないかご確認ください。<br>
					<ul>
						<li>・半角英数字で入力されているかどうか</li>
						<li>・前後にスペースが入っていないか</li>
					</ul>
			</details>
			<details>
				<summary>Q.ログアウトするには</summary>
					<div>
						A.右上の「画像」を選択するとログアウトできます。
					</div>
			</details>
		<p>訪問地、候補地の登録について</p>
			<details>
				<summary>Q.「訪問地」に関する登録をするには</summary>
					<div>
						A.登録画面から「訪れた場所に関する登録」を選択し、必要な項目を入力してから登録ボタンを押してください。
					</div>
			</details>
			<details>
				<summary>Q.「候補地」に関する登録をするには</summary>
					<div>
						A.登録画面から「訪れた場所に関する登録」を選択し、必要な項目を入力してから登録ボタンを押してください。
					</div>
			</details>
				<details>
				<summary>Q.「訪問地」に関する登録の際、画像の登録枚数に上限はありますか？</summary>
					<div>
						A.画像は5枚まで登録が可能となっております。
					</div>
			</details>
			<details>
				<summary>Q.「候補地」に登録した場所に訪れた場合はどうしたらいいですか？</summary>
					<div>
						A.当該候補地の詳細画面にある「移動」をクリックし、必要な項目を入力の上ご登録ください。
					</div>
			</details>
		<p>称号について</p>
			<details>
				<summary>Q.獲得した称号はどこで確認ができますか？</summary>
					<div>
						A.獲得した称号はホーム画面から閲覧が可能です。
					</div>
			</details>
			<details>
				<summary>Q.獲得した称号が削除されていました。</summary>
					<div>
						A.称号は「訪問地」リストに登録された情報を基に付与しているため、「訪問地」リストに登録した情報を削除すると獲得した称号を失う可能性があります。ご了承ください。
					</div>
			</details>
			<p>ガチャについて</p>
				<details>
				<summary>Q.ガチャは何回まで引けますか？</summary>
					<div>
						A.ガチャは1日1回までとなっております。
					</div>
			</details>
	</main>

	<footer class="footer">
		<p class="copyright">TABI×TILE　&copy; 2025 Always First.</p>
		<p>旅のひとコマが、未来を彩るタイルになる</p>
	</footer>
</body>
</html>