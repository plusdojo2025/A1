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
	

</head>
<body>

<!-- ヘッダー（ここから） -->
	<header>
		<h1>
		 <a href="<c:url value='/HomeServlet'/>"><img src="<c:url value='/assets/imgs/TABITILE_logo.png' />" width="150"></a>
		</h1>
	</header>

<!-- ヘッダー(ここまで) -->

<!-- メイン（ここから） -->

<div class ="login">

<c:if test="${not empty errorMessage}">
<p>${errorMessage}</p>
</c:if>
</div>
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
        <input type="password" id="password" name="password" >
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
  </table>
</form>
	
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
	</script>
<!-- フッター(ここから) -->
	<div class="footer">
    	<p class="copyright">TABI×TILE　&copy; 2025 Always First.</p>
    	<p>旅のひとコマが、未来を彩るタイルになる</p>
	</div>
<!-- フッター（ここまで） -->

</body>
</html>