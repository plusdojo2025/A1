<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- [ 読込 ] jstl を扱えるように --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- [ 短縮 ] コンテキストパス --%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%-- [ 短縮 ] アセッツパス --%>
<c:set var="assetsPath" value="${contextPath}/assets" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" rel="stylesheet">
   <style>
      #textPassword {
        border: none; /* デフォルトの枠線を消す */
      }
      #fieldPassword {
        border-width: thin;
        border-style: solid;
        width: 200px;
      }
    </style>
<title>TABI×TILE</title>

	<!-- 全体共通css -->
	<link rel="stylesheet" href="<c:url value='assets/css/common.css'/>">
	<link rel="stylesheet" href="<c:url value='assets/css/custom.css'/>">
	
	<!-- Q&A画面用css -->
	<link rel="stylesheet" href="<c:url value='assets/css/setting.css'/>">
	
</head>
<body>

<!-- ヘッダー（ここから） -->
	<header>
		<div class="header">
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
	</div>
	
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
	<main class="main">
        <form method="POST" action="<c:url value='/SettingServlet'/>" id="name_form">
        		<input type="hidden" id="user_id" name="user_id" value='${sessionScope.user_id.user_id}'>
		    <p>ニックネームの変更</p>
		    <label>
		        <input type="text" id="name" class="nickname" name="nickname" value='${sessionScope.user_id.nickname}'>
		    </label>
		
		    <p>都道府県の変更</p>
		    <label>
		        <select name="prefecture_id">
		                <option value='${sessionScope.user_id.prefecture_id}' selected>選択してください</option>
		                <option value="1">北海道</option>
		                <option value="2">青森県</option> 
		                <option value="3">岩手県</option>
		                <option value="4">宮城県</option>
		                <option value="5">秋田県</option>
		                <option value="6">山形県</option> 
		                <option value="7">福島県</option>
		                <option value="8">茨城県</option>
		                <option value="9">栃木県</option>
		                <option value="10">群馬県</option> 
		                <option value="11">埼玉県</option>
		                <option value="12">千葉県</option>
		                <option value="13">東京都</option>
		                <option value="14">神奈川県</option> 
		                <option value="15">新潟県</option>
		                <option value="16">富山県</option>
		                <option value="17">石川県</option>
		                <option value="18">福井県</option> 
		                <option value="19">山梨県</option>
		                <option value="20">長野県</option>
		                <option value="21">岐阜県</option>
		                <option value="22">静岡県</option> 
		                <option value="23">愛知県</option>
		                <option value="24">三重県</option>
		                <option value="25">滋賀県</option>
		                <option value="26">京都府</option> 
		                <option value="27">大阪府</option>
		                <option value="28">兵庫県</option>
		                <option value="29">奈良県</option>
		                <option value="30">和歌山県</option> 
		                <option value="31">鳥取県</option>
		                <option value="32">島根県</option> 
		                <option value="33">岡山県</option>
		                <option value="34">広島県</option>
		                <option value="35">山口県</option>
		                <option value="36">徳島県</option> 
		                <option value="37">香川県</option>
		                <option value="38">愛媛県</option>
		                <option value="39">高知県</option>
		                <option value="40">福岡県</option> 
		                <option value="41">佐賀県</option>
		                <option value="42">長崎県</option>
		                <option value="43">熊本県</option>
		                <option value="44">大分県</option> 
		                <option value="45">宮崎県</option>
		                <option value="46">鹿児島県</option>
		                <option value="47">沖縄県</option>     
		            </select>
		    </label>
		    
		<div class ="login-container">
			<c:if test="${not empty errorMessage}">
				<p class="error-message">${errorMessage}</p>
			</c:if></div>
			
		<p>パスワードの変更</p>
			<div class="pass">
			    <p class="pass2">現在のパスワード
			        <input type="password" id="password1" class="pass3" placeholder="現在のパスワードを入力" name="password1">
			        <span id="buttonEye1" class="fa fa-eye-slash" onclick="pushHideButton1()"></span><br>
			    </p>
				<p class="pass2">新しいパスワード
		          <input type="password" id="password2" class="pass3" placeholder="新しいパスワードを入力" name="password2">
		          <span id="buttonEye2" class="fa fa-eye-slash" onclick="pushHideButton2()"></span><br>
		          <span id="error_message3"></span>
		        </p>
		      </div>
		       	<p class=button><input type="submit" id="register" class="push-button" name="submit" value="変更"></p>
        </form>
	</main>
<!-- メイン(ここまで) -->

<!-- フッター(ここから) -->
	<div class="footer">
    	<p class="copyright">TABI×TILE　&copy; 2025 Always First.</p>
    	<p>旅のひとコマが、未来を彩るタイルになる</p>
	</div>
<!-- フッター（ここまで） -->

    <script>
    function pushHideButton1() {
    var txtPass1 = document.getElementById("password1");
    var btnEye1 = document.getElementById("buttonEye1");

    if (txtPass1.type === "text") {
        txtPass1.type = "password";
        btnEye1.className = "fa fa-eye-slash";
    } else {
        txtPass1.type = "text";
        btnEye1.className = "fa fa-eye";
        }
    }
    
    function pushHideButton2() {
    var txtPass2 = document.getElementById("password2");
    var btnEye2 = document.getElementById("buttonEye2");
    
    if (txtPass2.type === "text") {
        txtPass2.type = "password";
        btnEye2.className = "fa fa-eye-slash";
    } else {
        txtPass2.type = "text";
        btnEye2.className = "fa fa-eye";
    	}
    }
    
    /*HTML要素をオブジェクトとして取得する*/
    let passwordformObj = document.getElementById('password_form');
    let errorMessageObj3 = document.getElementById('error_message3');

    /* [実行]ボタンをクリックしたときの処理 */
    passwordformObj.onsubmit = function() {
    /* パスワードに対するエラー処理 */
    if (passwordformObj.password1.value === passwordformObj.password2.value){
        	errorMessageObj3.textContent = '※現在のパスワードと同じものが入力されています';
        return false;            
        }
    }
	
    <!-- ダイアログJS(ここから) -->
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
    <!-- ダイアログJS(ここまで) -->
    </script>
</body>
</html>