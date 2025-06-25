<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- [ 読込 ] jstl を扱えるように --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- [ 短縮 ] 画像フォルダパス --%>
<c:set var="imgsPath" value="/assets/imgs" />

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>登録｜TABI×TILE</title>
<link rel="stylesheet" href="<c:url value='assets/css/common.css'/>">
<link rel="stylesheet" href="<c:url value='assets/css/custom.css'/>">
<!-- 登録画面用css -->
<link rel="stylesheet" href="<c:url value='assets/css/tab.css'/>" >
<link rel="stylesheet" href="<c:url value='assets/css/regist.css'/>">
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
			<li><a href="<c:url value='/HomeServlet'/>">ホーム</a></li>
			<li><a href="<c:url value='/VisitorRegistServlet'/>">登録</a></li>
			<li><a href="<c:url value='/VisitorSearchServlet'/>">検索</a></li>
			<li><a href="<c:url value='/ListServlet'/>">一覧</a></li>
		</ul>
	</nav>
	
	</header>
<!-- ヘッダー(ここまで) -->

<!-- メイン（ここから） -->
	<main>
	<div class="tab_wrap">
    <!--タブメニュー-->
  	<div class="tab_area">
    <button class="tab_btn active" data-tab="tab1">訪問地登録</button>
    <button class="tab_btn" data-tab="tab2">候補地登録</button> 
  	</div>  
		<!-- 訪問地登録 -->
		<div id="tab1" class="tab_panel active">
			<h2>訪問地登録</h2>
		<form method="POST" action="<c:url value='/VisitorRegistServlet'/>" enctype="multipart/form-data"  id="visitorForm" >
		<div class="center-form">
		タイトル<input type="text" name="title"><br>
		  <div class="inline-group">
		※初日<input type="date" name="start_date" required>
				&nbsp;&nbsp;&nbsp;
		※終日<input type="date" name="end_date" required></div><br>
		  <div class="inline-group">
		※都道府県<select name="prefecture_id" required>
					<option value="" disabled selected>選択してください</option>
					<c:forEach var="prefecture" items="${prefectureList}">
						<option value="${prefecture.prefecture_id}"
							<c:if test="${prefecture_id == prefecture.prefecture_id}"
	                    			>selected</c:if>
							>${prefecture.prefecture_name}</option>
					</c:forEach>
			   </select>
			   	&nbsp;&nbsp;&nbsp;
		場所<input type="text" name="place" value="${place}" /></div><br>
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
		<textarea name="thought" rows="5" cols="40" class="textarea-large"
			>${thought}</textarea><br>
	
		写真<input type="file" name="photo"><br><br>
				  <div class="inline-group">
		<input type="reset" name=ResetButton value="リセット" class="btn-reset">
		&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;
		<input type="button" name=RegistButton value="登録" class="btn-submit" onclick="validateAndShowDialog('visitor')" /></div><br>
		※欄は必須項目です
                <span id="error_message"></span>
                </div>
		</form>
		</div>
		
		<!-- 候補地登録 -->
		<div id="tab2" class="tab_panel">
			<h2>候補地登録</h2>
		<form method="POST" action="<c:url value='/PickupRegistServlet'/>"  id="pickupForm" >
		<div class="center-form">
		  <div class="inline-group">
		※都道府県<select name="prefecture_id" required>
					<option value="" disabled selected>選択してください</option>
					<c:forEach var="prefecture" items="${prefectureList}">
						<option value="${prefecture.prefecture_id}">${prefecture.prefecture_name}</option>
					</c:forEach>
				</select>
		&nbsp;&nbsp;&nbsp;
		場所<input type="text" name="place" ></div><br>
		備考欄<br>
				<textarea name="thought" rows="5" cols="40" class="textarea-large"></textarea><br><br>
				  <div class="inline-group">
		<input type="reset" name=ResetButton value="リセット" class="btn-reset">
		&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;
		<input type="button" name=RegistButton value="登録" class="btn-submit" onclick="validateAndShowDialog('pickup')" /></div><br>
		※欄は必須項目です
                <span id="error_message"></span>
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
<!-- [ ダイアログ / HTML ] 登録 (ここから) -->
<div id="Regist-confirmDialog" class="custom-dialog" style="display:none;">
    <p>登録しますが、よろしいですか？</p>
    <img src="<c:url value='${imgsPath}/char/Question.png'/>" alt="登録確認">
    <div class="dialog-buttons">
        <button class="btn-yes" onclick="handleRegistConfirm(true)">はい</button>
        <button class="btn-no" onclick="handleRegistConfirm(false)">いいえ</button>
    </div>
</div>
<!-- [ ダイアログ / HTML ] 登録 (ここまで) -->

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
let currentForm = null;

function validateAndShowDialog(formType) {
  const formId = formType === 'visitor' ? 'visitorForm' : 'pickupForm';
  const form = document.getElementById(formId);

  // HTML5のバリデーションを使って、必須項目が入力されているか確認
  if (form.checkValidity()) {
    currentForm = formId;
    document.getElementById('overlay').style.display = 'block';
    document.getElementById('Regist-confirmDialog').style.display = 'block';
  } else {
    form.reportValidity(); // 不備がある箇所をブラウザが教えてくれる
  }
}

function handleRegistConfirm(isConfirmed) {
  document.getElementById('overlay').style.display = 'none';
  document.getElementById('Regist-confirmDialog').style.display = 'none';

  if (isConfirmed && currentForm) {
    document.getElementById(currentForm).submit();
  }
}
</script>

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