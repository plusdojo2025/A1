<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- [ 読込 ] jstl を扱えるように --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- [ 短縮 ] CSSフォルダパス --%>
<c:set var="cssPath" value="/assets/css" />
<%-- [ 短縮 ] 画像フォルダパス --%>
<c:set var="imgsPath" value="/assets/imgs" />
<%-- [ 短縮 ] ユーザーフォルダパス --%>
<c:set var="uploadPath" value="${imgsPath}/upload" />
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
    <button class="tab_btn active" data-tab="tab1">訪問地</button>
    <button class="tab_btn" data-tab="tab2">候補地</button> 
  	</div>  
	
	<!-- 訪問地登録 -->
	<div id="tab1" class="tab_panel active">
		<h2>訪問地登録</h2>
			<form method="POST" action="<c:url value='/VisitorRegistServlet'/>" enctype="multipart/form-data"  id="visitorForm" >
				<div class="center-form">
					<div class="form-group">
						<label for="title">タイトル</label>
						<input type="text" id="title" name="title">
					</div>
					
		  <div class="inline-group">
		  	<div class="form-group">	
				<label for="start_date">※初日</label>
				<input type="date" name="start_date" required>
			</div>
			<div class="form-group">
				<label for="end_date">※終日</label>
				<input type="date" name="end_date" required>
			</div>
		</div>
		
			
		<div class="inline-group">
			<div class="form-group">
				<label for="prefecture_id">※都道府県</label>
				<select name="prefecture_id" required>
					<option value="" disabled selected>選択してください</option>
						<c:forEach var="prefecture" items="${prefectureList}">
							<option value="${prefecture.prefecture_id}"
								<c:if test="${prefecture_id == prefecture.prefecture_id}">
	                    				selected</c:if>
								>${prefecture.prefecture_name}</option>
						</c:forEach>
			   	</select>
			</div>	
			
			<div class="form-group">
				<label for="place">場所</label>  				
					<input type="text" name="visitor_place" value="${place}" />
			</div><br>
		</div>
		
		
		<div class="inline-group">
			<div class="form-group">
				<label for="companion">同行者</label>
				<input type="text" name="companion">
			</div>
			<div class="form-group">
				<label for="emotion_id">感情</label>
					<select name="emotion_id">
						<option value="" disabled selected>選択してください</option>
						<c:forEach var="emotion" items="${emoList}">
							<option value="${emotion.emotion_id}">${emotion.emoji}</option>
						</c:forEach>
					</select>
			</div>
		</div>	
		
		<div class="form-group">
			<label for="thought">感想</label>
			<textarea name="thought" rows="10" class="textarea-large">${thought}</textarea><br>
		</div>
		
		<div class="form-group">
			<label for="photo1">写真1</label>	
			<input type="file" name="photo1">
		</div>
		
		<div class="form-group">
			<label for="photo2">写真2</label>
			<input type="file" name="photo2">
		</div>
		
		<div class="form-group">
			<label for="photo3">写真3</label>
			<input type="file" name="photo3">
		</div>
		
		<div class="form-group">
			<label for="photo4">写真4</label>
			<input type="file" name="photo4">
		</div>
		
		<div class="form-group">
			<label for="photo5">写真5</label>
			<input type="file" name="photo5">
		</div>
	</div>
            <div class="regist_buttons">
			<input type="reset" value="リセット" class="btn-reset">
			<input type="button" value="登録" class="btn-submit" onclick="validateAndShowDialog('visitor')" />
		</div>
			※欄は必須項目です
               <span id="error_message"></span>
            
        
		</form>
		</div>
		
		<!-- 候補地登録 -->
		<div id="tab2" class="tab_panel">
			<h2>候補地登録</h2>
				<form method="POST" action="<c:url value='/PickupRegistServlet'/>"  id="pickupForm" >
					<div class="center-form">
		  				<div class="inline-group">
		  					<div class="form-group">
		  						<label for="prefecture_id">※都道府県</label>
								<select name="prefecture_id" required>
									<option value="" disabled selected>選択してください</option>
										<c:forEach var="prefecture" items="${prefectureList}">
											<option value="${prefecture.prefecture_id}">${prefecture.prefecture_name}</option>
										</c:forEach>
								</select>
							</div>
								
							<div class="form-group">
								<label for="place">場所</label>
								<input type="text" name="pickup_place" >
							</div>
						</div>
		
		<div class="form-group">
			<label for="remarks">備考欄</label>
			<textarea name="remarks" rows="10" class="textarea-large"></textarea>
		</div>
		</div>
		
		<div class="regist_buttons">
			<input type="reset" value="リセット" class="btn-reset">
			<input type="button" value="登録" class="btn-submit" onclick="validateAndShowDialog('pickup')" />
		</div>
			※欄は必須項目です
               <span id="error_message"></span>
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
    	<button class="btn-no" onclick="handleRegistConfirm(false)">いいえ</button>
        <button class="btn-yes" onclick="handleRegistConfirm(true)">はい</button>
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