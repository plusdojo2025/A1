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
	.login-container {
	display: flex;
	flex-direction: column;
    align-items: center; 
    justify-content: center; 
	}
	.login-container form{
	text-align:center;
	}
	.password-input {
	margin-left:20px;
	}
	.error-message{
	text-align:center;
	margin-left:50px;
	}
	
	.button {
	background: #fff;
  	padding: 8px 16px;
  	text-decoration: none;
  	border-radius: 4px;
	}
	</style>

</head>
<body>

<!-- ヘッダー（ここから） -->
	<header>
		<h1>
		 <a href="<c:url value='/HomeServlet'/>"><img src="<c:url value='/assets/imgs/TABITILE_logo.png' />" width="250"></a>
		</h1>
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
        <label>ユーザーID<br>
        <input type="text" name="user_id" >
        </label>
      </td>
    </tr>
    <tr>
      <td>
        <label>パスワード<br>
        <input type="password" id="password" name="password" class="password-input">
        <span id ="buttonEye" class="fa fa-eye-slash" onclick="pushHideButton()"></span>
        </label>
      </td>
    </tr>
    <tr>
      <td colspan="2">
        <input type="submit" name="login" value="ログイン">
        <input type="reset"  name="reset" value="リセット">
        <p id="output"></p>
      </td>
    </tr>
    <tr>
    	<td>
 		<a href="<c:url value='/SignUpServlet' />" class="button">新規登録</a>
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
	  }else if (I ==="") {
		 output.textContent = 'IDを入力してください！';
		 event.preventDefault();
	  }else if (P ==="") {
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