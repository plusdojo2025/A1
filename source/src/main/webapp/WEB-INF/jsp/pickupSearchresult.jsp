<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- [ 読込 ] jstl を扱えるように --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>候補地検索結果｜TABI×TILE</title>
<!-- 全体共通css -->
<link rel="stylesheet" href="<c:url value='assets/css/common.css'/>">
<link rel="stylesheet" href="<c:url value='assets/css/custom.css'/>">

<!-- 結果表示 -->
<link rel="stylesheet" href="<c:url value='assets/css/result.css'/>">
</head>
<body>
<!-- ヘッダー（ここから） -->
	<header>
		<div class="header">
		<div class="parent">
		 	<a href="<c:url value='/HomeServlet'/>"><img src="<c:url value='/assets/imgs/TABITILE_logo.png' />" width="300"></a>
		
<!-- ボタン設置 -->
	<div class="">
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
			<li><a href="<c:url value='/HomeServlet'/>">ホーム</a>
			<li><a href="<c:url value='/VisitorRegistServlet'/>">登録</a>
			<li><a href="<c:url value='/VisitorSearchServlet'/>">検索</a>
			<li><a href="<c:url value='/ListServlet'/>">一覧</a>
		</ul>
	</nav>
	
	</header>
<!-- ヘッダー(ここまで) -->

<!-- メイン（ここから） -->
	<main>
	<!-- 候補地検索結果一覧 -->
	<div class="center">
	
	<h2>候補地 検索結果</h2>
			<c:if test="${empty pickupList}">
			<p>指定された条件に一致するデータはありません。</p>
			<img src="<c:url value='/assets/imgs/char/Ojigi.png' />" width="100" alt="">
    	</c:if>

					<c:if test="${not empty pickupList}">
					<div class="card_list">
						<c:forEach var="pickup" items="${pickupList}">
							<a href="<c:url value='/PickupServlet?pk=${pickup.pickup_id}'/>">
								<div class="card link-card" >
									<h3 class="card_title">${pickup.prefecture_name} </h3>
									<div class="card_text_area">
										<p class="card_text">${pickup.pickup_place}</p>
										<p class="card_text">${pickup.remarks}</p>
									</div>
								</div>
							</a>
						</c:forEach>
						</div>
					</c:if>
					
					<!-- ▼ 候補地 ページネーション -->
					<c:if test="${totalPagesPickup > 1}">
						<div class="pagination">
							<c:forEach begin="1" end="${totalPagesPickup}" var="i">
						<form method="post" action="<c:url value='/PickupSearchServlet'/>" style="display:inline;">
      					<input type="hidden" name="page" value="${i}">
      					<input type="hidden" name="prefecture_name" value="${param.prefecture_name}">
      					<input type="hidden" name="pickup_place" value="${param.pickup_place}">
      					<input type="hidden" name="remarks" value="${param.remarks}">
      					<button type="submit" class="${i == currentPage ? 'active' : ''}">${i}</button>
    				</form> 
							
			   				</c:forEach>
						</div>
					</c:if>
	
      <!--   <td>${pickup.pickup_id}</td>
        <td>${pickup.pickup_place}</td>
        <td>${pickup.prefecture_name}</td>  
        <td>${pickup.remarks}</td> -->
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
</body>
</html>