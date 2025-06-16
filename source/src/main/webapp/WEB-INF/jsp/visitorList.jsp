<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>	一覧｜TABI×TILE</title>
<link >
</head>
<body>

<!-- ヘッダー（ここから） -->
	<header>
		<h1>
		 <a href="/A1/HomeServlet">TABI×TILE</a>
		</h1>
	
	<nav>
		<ul>
			<li><a href="/A1/HomeServlet">ホーム</a>
			<li><a href="/A1/VisitorRegistServlet">登録</a>
			<li><a href="/A1/VisitorSearchServlet">検索</a>
			<li><a href="/A1/VisitorListServlet">一覧</a>
		</ul>
	</nav>
	
	<!-- ボタン設置 -->
	<div class="">
	<a href="/A1/GachaServlet">ガチャ</a>
		<a href="/A1/QaServlet">QA</a>
		<a href="/A1/SettingServlet">設定</a>
		<a href="/A1/LogoutServlet">ログアウト</a>
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
			<div id="panel1" class="tab_panel">
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
			
		<!-- ▼ 候補地 一覧 -->>
			<div id="panel2" class="tab_panel">
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