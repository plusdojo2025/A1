<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- [ 読込 ] jstl を扱えるように --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*, dto.VisitorDTO, dto.PickupDTO" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>	一覧｜TABI×TILE</title>
<!-- 全画面共通 -->
<link rel="stylesheet" href="<c:url value='assets/css/common.css'/>" >
<link rel="stylesheet" href="<c:url value='assets/css/custom.css'/>">

<!-- 一覧画面 -->
<link rel="stylesheet" href="<c:url value='assets/css/tab.css'/>" >
<link rel="stylesheet" href="<c:url value='assets/css/list.css'/>" >

</head>
<body>

<!-- ヘッダー（ここから） -->
	<header>
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
	</div>
<!-- ニックネーム表示 -->
	<c:if test ="${not empty sessionScope.user_id }">
	<span class="nickname">${sessionScope.user_id.nickname}&nbsp;さん</span>
	</c:if>

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
	<main>
		<div class="tab_wrap">
    		<!--タブメニュー-->
  			<div class="tab_area">
   				 <button class="tab_btn active" data-tab="tab1">訪問地一覧</button>
  				<button class="tab_btn"  data-tab="tab2">候補地一覧</button>
			</div>
  	
			<!-- ▼ 訪問地 一覧 -->
			<div id="tab1" class="tab_panel active">
				<h2>訪問地一覧</h2>
				
				<div class="">
					<c:if test="${empty visitorList}">
   						<p>訪問地の情報は見つかりませんでした。</p>
  					</c:if>

  					<c:if test="${not empty visitorList}">
						<c:forEach var="visitor" items="${visitorList}">
							<a href="<c:url value='/VisitorServlet?pk=${visitor.visitor_id}'/>">
								<div class="card link-card">
									<h3>${visitor.start_date} の思い出</h3>
									<p>${visitor.prefecture_name}</p>
									<p>${visitor.visitor_place}</p>
								</div>
							</a>	
						</c:forEach>
  					</c:if>
  					
  					<!-- ▼ 訪問地 ページネーション -->
  					<c:if test="${totalPagesVisitor > 1}">
  						<div class="pagination">
  							<c:forEach begin="1" end="${totalPagesVisitor}" var="i">
  								<a href="PlaceSelectServlet?pref=${selectedPrefecture}&page=${i}" 
			   						class="${i == currentPage ? 'active' : ''}">${i}</a>
							</c:forEach>
						</div>
					</c:if>
  				</div>
			</div>
			
			<!-- ▼ 候補地 一覧 -->
			<div id="tab2" class="tab_panel">
				<h2>候補地一覧</h2>
				
				<div class="">
					<c:if test="${empty pickupList}">
   						<p>候補地の情報は見つかりませんでした。</p>
  					</c:if>
					
					<c:if test="${not empty pickupList}">
						<c:forEach var="pickup" items="${pickupList}">
							<a href="<c:url value='/PickupServlet?pk=${pickup.pickup_id}'/>">
								<div class="card link-card" >
									<h3>${pickup.prefecture_name} </h3>
									<p>${pickup.pickup_place}</p>
									<p>${pickup.remarks}</p>
								</div>
							</a>
						</c:forEach>
					</c:if>
					
					<!-- ▼ 候補地 ページネーション -->
					<c:if test="${totalPagesPickup > 1}">
						<div class="pagination">
							<c:forEach begin="1" end="${totalPagesPickup}" var="i">
								<a href="PlaceSelectServlet?pref=${selectedPrefecture}&page=${i}" 
			   						class="${i == currentPage ? 'active' : ''}">${i}</a>
			   				</c:forEach>
						</div>
					</c:if>
				</div>
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
    <img src="<c:url value='/assets/imgs/char/Door.png'/>" alt="ログアウト確認">
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

<!-- タブの切り替え（ここから） -->
<script>
//▼ タブ切り替え処理
	const tabButtons = document.querySelectorAll('.tab_btn');
	const tabContents = document.querySelectorAll('.tab_panel');

 	tabButtons.forEach(button => {
    button.addEventListener('click', () => {
    
    // 全ボタンのアクティブを外す
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

	
// ▼ カードクリックで遷移する処理（ここを追加！）
	document.querySelectorAll('.link-card').forEach(card => {
    	card.addEventListener('click', () => {
      	const url = card.getAttribute('data-href');
      	if (url) {
        	window.location.href = url;
      }
    });
  });
</script>

</body>
</html>