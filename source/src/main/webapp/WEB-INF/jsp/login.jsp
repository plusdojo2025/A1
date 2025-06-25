<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- [ 読込 ] jstl を扱えるように --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>ログイン｜TABI×TILE</title>
<link rel="stylesheet" href="<c:url value='assets/css/common.css'/>">
<link rel="stylesheet" href="<c:url value='assets/css/custom.css'/>">
<link href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" rel="stylesheet">

	<style> 
	/* 全体の中央寄せ */
	 .login-container {
	 display: flex;
	 flex-direction: column;
	 align-items: center; 
	 justify-content: center;
	 }
	
	/* フォームを中央に寄せる */
	.login-container form {
	text-align:center;
	position: relative;
	}
	
	/* ラベルの文字を大きく */
	.login-container label {
	font-size: 30px;
	font-weight: bold;
	display: block;
	margin-bottom: 5px;
	}
	
	/* 入力欄（ユーザーID・パスワード） */
	.user_id-input,
	.password-input {
	padding: 20px;
	width: 320px;
	font-size: 25px;
	border: 1px solid #ccc;
	border-radius: 6px;
	margin-bottom: 20px;
	}
	
	/* パスワード入力と目アイコンを揃えるために親を相対位置に */
	.password-wrapper {
	position: relative;
	display: inline-block;
	}
	
	/* 目のアイコンを入力欄の中に配置 */
	#buttonEye {
  	position: absolute;
  	right: 10px;
  	top: 50%;
  	transform: translateY(-50%);
  	cursor: pointer;
  	font-size: 30px;
  	color: #666;
	}
	
	/* エラーメッセージ中央寄せ */
	.error-message {
	text-align: center;
	margin-bottom: 20px;
	font-size: 20px;
	}
	
	/* ボタンの配置とデザイン */
	.push-button {
  	display: flex;
  	justify-content: center;
 	gap: 100px;
  	margin-top: 20px;
  	margin-bottom: 40px;
	}
	
	.btn-no,
	.btn-yes {
  	width: 120px;
  	height: 50px;
 	display: flex;
  	align-items: center;
  	justify-content: center;
  	font-size: 25px;
  	border: none;
  	border-radius: 5px;
  	cursor: pointer;
  	white-space: nowrap;
	}

	.btn-no {
  	background-color: #e79bb7;
  	color: white;
	}

	.btn-no:hover {
  	background-color: #d9769b;
	}

	.btn-no:active {
  	background-color: #cc4f7f;
	}

	.btn-yes {
  	background-color: #8da9e0;
  	color: white;
	}

	.btn-yes:hover {
  	background-color: #5f87d2;
	}

	.btn-yes:active {
  	background-color: #406ac2;
	}
	
	/* [新規登録はこちら] ボタン */
	.button {
	background: #fff;
  	padding: 8px 16px;
  	text-decoration: none;
  	border-radius: 4px;
  	display: inline-block;
  	border: 1px solid #ccc;
  	font-size: 25px;
  	margin-bottom: 40px; 
	}

	</style>

</head>
<body>

<!-- ヘッダー（ここから） -->
	<header>
		<div class="header">
			 <a href="<c:url value='/HomeServlet'/>"><img src="<c:url value='/assets/imgs/TABITILE_logo.png' />" width="300"></a>
		</div>
	</header>

<!-- ヘッダー(ここまで) -->

<!-- メイン（ここから） -->


<div class ="login-container">
	<c:if test="${not empty errorMessage}">
		<p class="error-message">${errorMessage}</p>
	</c:if>

<form id="login_form" method="POST" action="<c:url value='/LoginServlet'/>">
  <table>
    <tr>
      <td>
        <label>ユーザーID</label>
        <input type="text" name="user_id" class="user_id-input">
      </td>
    </tr>
    
    <!-- 間を開ける -->
    <tr><td style="height: 30px;"></td></tr>
    
    <tr>
     <td>
      <label>パスワード</label>
       <div class="password-wrapper">
        <input type="password" id="password" name="password" class="password-input">
        <span id ="buttonEye" class="fa fa-eye-slash" onclick="pushHideButton()"></span>
       </div>
      </td>
    </tr>
    
    <!-- ボタン -->
    <tr>
     <td colspan="2">
      <div class="push-button">
        <input type="reset"  name="reset" value="リセット" class="btn-no">
        <input type="submit" name="login" value="ログイン" class="btn-yes">
      </div>
     <div id="output"></div>
    </td>
   </tr>
    
    <tr>
    	<td>
 		<a href="<c:url value='/SignUpServlet' />" class="button">新規登録はこちら</a>
    	</td>
    </tr>
  </table>
</form>
	
</div>
<!-- メイン(ここまで) -->
	<script>
	/*パスワードを隠すための処理*/
	function pushHideButton() {
		var txtPass =document.getElementById("password");
		var btnEye = document.getElementById("buttonEye");
		if(txtPass.type === "text") {
			txtPass.type = "password";
			btnEye.className = "fa fa-eye-slash"
		}else{
			txtPass.type = "text";
			btnEye.className ="fa fa-eye";
			
		}
		
	}
	
	'use strict';
	document.getElementById('login_form').onsubmit = function(event) {
	  const I = document.getElementsByName('user_id')[0].value.trim();
	  const P = document.getElementsByName('password')[0].value.trim();
	  const output = document.getElementById('output');
	  if (I === '' && P === '') {
	    output.textContent = 'IDとPWを入力してください！'	;
	    event.preventDefault();
	  }else if (I ==='') {
		 output.textContent = 'IDを入力してください！';
		 event.preventDefault();
	  }else if (P ==='') {
		 output.textContent = 'PWを入力してください！';
		 event.preventDefault();  
	  }else {
		  output.textContent = '';
	  }
	  
	  
	};
	</script>
<!-- フッター(ここから) -->
	<div class="footer">
    	<p class="copyright">TABI×TILE　&copy; 2025 Always First.</p>
    	<p>旅のひとコマが、未来を彩るタイルになる</p>
	</div>
<!-- フッター（ここまで） -->

</body>
</html>