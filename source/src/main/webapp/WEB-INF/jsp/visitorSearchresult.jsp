<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- [ 読込 ] jstl を扱えるように --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>訪問地検索結果｜TABI×TILE</title>
<!-- 全体共通css -->
<link rel="stylesheet" href="<c:url value='assets/css/common.css'/>">
<link rel="stylesheet" href="<c:url value='assets/css/custom.css'/>">
</head>
<body>
<!-- ヘッダー（ここから） -->
	<header>
		<h1>
		 <a href="<c:url value='/HomeServlet'/>"><img src="<c:url value='/assets/imgs/TABITILE_logo.png' />" width="250"></a>
		</h1>
		
<!-- ボタン設置 -->
	<div class="">
		<a href="<c:url value='/GachaServlet'/>"><img src="<c:url value='/assets/imgs/icons/gacha.png' />" width="50" ></a>
		<a href="<c:url value='/QaServlet'/>"><img src="<c:url value='/assets/imgs/icons/qa.png' />" width="50" ></a>
		<a href="<c:url value='/SettingServlet'/>"><img src="<c:url value='/assets/imgs/icons/setting.png' />" width="50" ></a>
		<a href="<c:url value='/LogoutServlet'/>" onclick="showConfirmDialog(); return false;"><img src="<c:url value='/assets/imgs/icons/logout.png' />" width="50" ></a>
	</div>
	
<!-- ニックネーム表示 -->
	<c:if test ="${not empty sessionScope.user_id }">
	<span class="nickname">${sessionScope.user_id.nickname}&nbsp;さん</span>
	</c:if>

<!-- メニューバー表示 -->
	<nav>
		<ul>
			<li><a href="<c:url value='/HomeServlet'/>">ホーム</a>
			<li><a href="<c:url value='/VisitorRegistServlet'/>">登録</a>
			<li><a href="<c:url value='/VisitorSearchServlet'/>">検索</a>
			<li><a href="<c:url value='/VisitorListServlet'/>">一覧</a>
		</ul>
	</nav>
	
	</header>
<!-- ヘッダー(ここまで) -->
<!-- メイン（ここから） -->
	<main>
	<!-- 訪問地検索結果一覧 -->
<h2>訪問地検索結果一覧</h2>
	
		  <c:if test="${empty visitorList}">
    <p>該当する訪問記録が見つかりませんでした。</p>
  </c:if>

  <c:if test="${not empty visitorList}">
    <table class="result-table">
      <thead>
        <tr>
          <th>訪問開始日</th>
          <th>訪問終了日</th>
          <th>都道府県</th>
          <th>場所</th>
          <th>タイトル</th>
          <th>同行者</th>
          <th>感情</th>
          <th>感想</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="record" items="${visitorList}">
          <tr>
            <td><c:out value="${record.start_date}" /></td>
            <td><c:out value="${record.end_date}" /></td>
            <td><c:out value="${record.prefecture_name}" /></td>
            <td><c:out value="${record.place}" /></td>
            <td><c:out value="${record.title}" /></td>
            <td><c:out value="${record.componion}" /></td>
            <td><c:out value="${record.emotion_name}" /></td>
            <td><c:out value="${record.thought}" /></td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
  </c:if>
		
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
</body>
</html>