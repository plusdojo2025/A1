<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <%-- [ 読込 ] jstl を扱えるように --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
<title>｜TABI×TILE</title>
<link >
</head>
<body>

<!-- ヘッダー（ここから） -->
	
<!-- ヘッダー(ここまで) -->

<!-- メイン（ここから） -->
	
<div class ="login">
<p>※ID=id, PW=password でログインできます。</p>
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
        <input type="password" name="password" >
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

<!-- フッター(ここから) -->
	<div class="footer">
    	<p class="copyright">TABI×TILE　&copy; 2025 Always First.</p>
    	<p>旅のひとコマが、未来を彩るタイルになる</p>
	</div>
<!-- フッター（ここまで） -->

</body>
</html>