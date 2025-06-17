<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>｜TABI×TILE</title>
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
	
<div class ="login">
<p>※ID=id, PW=password でログインできます。
</div>
<form id="login_form" method="POST" action="/webapp/LoginServlet">
  <table>
    <tr>
      <td>
        <label>ユーザーID<br>
        <input type="text" name="id" >
        </label>
      </td>
    </tr>
    <tr>
      <td>
        <label>パスワード<br>
        <input type="password" name="pw" >
        </label>
      </td>
    </tr>
    <tr>
      <td colspan="2">
        <input type="submit" name="login" value="ログイン">
        <input type="reset" class="big-button" name="reset" value="リセット">
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