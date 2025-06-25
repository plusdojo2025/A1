<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- [ 読込 ] jstl を扱えるように --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- [ 短縮 ] 画像フォルダパス --%>
<c:set var="imgsPath" value="/assets/imgs" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>検索｜TABI×TILE</title>
<link rel="stylesheet" href="<c:url value='assets/css/common.css'/>">
<link rel="stylesheet" href="<c:url value='assets/css/custom.css'/>">
<!-- 検索画面用css -->
<link rel="stylesheet" href="<c:url value='assets/css/tab.css'/>" >
<link rel="stylesheet" href="<c:url value='assets/css/search.css'/>">

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
	<main class="main">
	<div class="tab_wrap">
    <!--タブメニュー-->
  	<div class="tab_area">
    <button class="tab_btn active" data-tab="tab1">訪問地検索</button>
    <button class="tab_btn" data-tab="tab2">候補地検索</button> 
  	</div>  
		<!-- 訪問地登録 -->
		<div id="tab1" class="tab_panel active">
			<h2>訪問地検索</h2>
		<form method="POST" action="<c:url value='/VisitorSearchServlet'/>">
		<div class="center-form">
		タイトル<input type="text" name="title"><br>
		<div class="inline-group">
		初日<input type="date" name="start_date">
		&nbsp;&nbsp;&nbsp;
		終日<input type="date" name="end_date"></div><br>
		<div class="inline-group">
		都道府県<select name="prefecture_id">
					<option value="" disabled selected>選択してください</option>
					<c:forEach var="prefecture" items="${prefectureList}">
						<option value="${prefecture.prefecture_id}">${prefecture.prefecture_name}</option>
					</c:forEach>
			   </select>
		&nbsp;&nbsp;&nbsp;
		場所<input type="text" name="visitor_place"></div><br>
		<div class="inline-group">
		同行者<input type="text" name="componion">
		&nbsp;&nbsp;&nbsp;
		感情<select name="emotion_id">
				<option value="" disabled selected>選択してください</option>
				<c:forEach var="emotion" items="${emoList}">
						<option value="${emotion.emotion_id}">${emotion.emoji}</option>
					</c:forEach>
			</select></div><br>
		感想<br>
		<textarea name="thought" rows="5" cols="40" class="textarea-large"></textarea><br>
		<div class="inline-group">
		<input type="reset" name=ResetButton value="リセット" class="btn-reset">
		&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;
		<input type="submit" name=RegistButton value="検索" class="btn-submit"></div><br>
		</div>
		</form>
		</div>
		
		<!-- 候補地登録 -->
		<div id="tab2" class="tab_panel">
			<h2>候補地検索</h2>
		<form method="POST" action="<c:url value='PickupSearchServlet'/>">
		<div class="center-form">
		<div class="inline-group">
		都道府県<select name="prefecture_id">
					<option value="" disabled selected>選択してください</option>
					<c:forEach var="prefecture" items="${prefectureList}">
						<option value="${prefecture.prefecture_id}">${prefecture.prefecture_name}</option>
					</c:forEach>
				</select>
		&nbsp;&nbsp;&nbsp;
		場所<input type="text" name="pickup_place"></div><br>
		備考欄<br>
		<textarea name="remarks" rows="5" cols="40" class="textarea-large"></textarea><br>
		<div class="inline-group">		
		<input type="reset" name=ResetButton value="リセット" class="btn-reset">
		&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;
		<input type="submit" name=RegistButton value="検索" class="btn-submit"></div><br>
		</div>
		</form>
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
    <img src="<c:url value='${imgsPath}/char/Door.png'/>" alt="ログアウト確認">
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

<script>
  const tabButtons = document.querySelectorAll('.tab_btn');
  const tabContents = document.querySelectorAll('.tab_panel');

  tabButtons.forEach(button => {
    button.addEventListener('click', () => {
      // タブのアクティブ切り替え
      tabButtons.forEach(btn => btn.classList.remove('active'));
      button.classList.add('active');

      // コンテンツのアクティブ切り替え
      const target = button.getAttribute('data-tab');
      tabContents.forEach(content => {
        content.classList.remove('active');
        if (content.id === target) {
          content.classList.add('active');
        }
      });
    });
  });
</script>
</body>
</html>