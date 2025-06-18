<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- [ 読込 ] jstl を扱えるように --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>｜TABI×TILE</title>
<link rel="stylesheet" href="<c:url value='assets/css/common.css'/>">
<link rel="stylesheet" href="<c:url value='assets/css/custom.css'/>">
    <style>
     body {
      font-family: sans-serif;
      margin: 0;
      padding: 0;
    }

    .tab_wrap {
      max-width: 600px;
      margin: 0 auto;
      padding: 10px;
    }

    .tab_area {
       display: flex;
      flex-direction: row;
      margin-bottom: 0;
      border-bottom: 2px solid #ccc;
    }

    .tab_btn {
      flex: 1;
      padding: 14px;
      font-size: 16px;
      background: #f0f0f0;
      border: none;
      border-right: 1px solid #ccc;
      cursor: pointer;
      text-align: center;
    }

    .tab_btn:last-child {
      border-right: none;
    }

    .tab_btn.active {
      background-color: white;
      font-weight: bold;
      border-bottom: 2px solid #007BFF;
      color: #007BFF;
    }

    .tab_panel {
      display: none;
      padding: 20px;
      background-color: #fff;
      border: 1px solid #ccc;
      border-top: none;
    }

    .tab_panel.active {
      display: block;
    }


  </style>
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
    <!--タブメニュー-->
  	<div class="tab_area">
    <button class="tab_btn active" data-tab="tab1">訪問地検索</button>
    <button class="tab_btn" data-tab="tab2">候補地検索</button> 
  	</div>  
		<!-- 訪問地登録 -->
		<div id="tab1" class="tab_panel active">
			<h2>訪問地検索</h2>
		<form method="POST" action="/webapp/VisitorSearchServlet">
		初日<input type="date" name="start_date"><br>
		終日<input type="date" name="end_date"><br>
		タイトル<input type="text" name="title"><br>
		都道府県<select name="prefecture_id">
					<option value="" disabled selected>選択してください</option>
					<c:forEach var="prefecture" items="${prefectureList}">
						<option value="${prefecture.prefecture_id}">${prefecture.prefecture_name}</option>
					</c:forEach>
			   </select><br>
		場所<input type="text" name="place"><br>
		同行者<input type="text" name="componion"><br>
		感情<select name="emotion_id">
				<option value="" disabled selected>選択してください</option>
				<c:forEach var="emotion" items="${emotionList}">
						<option value="${emotion.id}">${emotion.emoji}</option>
					</c:forEach>
			</select><br>
		感想<input type="text" name="thought"><br>
		写真<input type="file" name="photo"><br>
		<input type="reset" name=ResetButton value="リセット">
		<input type="submit" name=RegistButton value="検索"><br>
		</form>
		</div>
		
		<!-- 候補地登録 -->
		<div id="tab2" class="tab_panel">
			<h2>候補地検索</h2>
		<form method="POST" action="/webapp/PickupSearchServlet">
		都道府県<select name="prefecture_id">
					<option value="" disabled selected>選択してください</option>
					<c:forEach var="prefecture" items="${prefectureList}">
						<option value="${prefecture.prefecture_id}">${prefecture.prefecture_name}</option>
					</c:forEach>
				</select><br>
		場所<input type="text" name="place"><br>
		備考欄<input type="text" name="remarks"><br>
		<input type="reset" name=ResetButton value="リセット">
		<input type="submit" name=RegistButton value="検索"><br>
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