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

<style>
     body {
      font-family: sans-serif;
      margin: 0;
      padding: 0;
    }

    .tab_wrap {
      max-width: 600px;
      margin: 0 auto;
      padding: 10px;
    }

    .tab_area {
       display: flex;
      flex-direction: row;
      margin-bottom: 0;
      border-bottom: 2px solid #ccc;
    }

    .tab_btn {
      flex: 1;
      padding: 14px;
      font-size: 16px;
      background: #f0f0f0;
      border: none;
      border-right: 1px solid #ccc;
      cursor: pointer;
      text-align: center;
    }

    .tab_btn:last-child {
      border-right: none;
    }

    .tab_btn.active {
      background-color: white;
      font-weight: bold;
      border-bottom: 2px solid #007BFF;
      color: #007BFF;
    }

    .tab_panel {
      display: none;
      padding: 20px;
      background-color: #fff;
      border: 1px solid #ccc;
      border-top: none;
    }

    .tab_panel.active {
      display: block;
    }

   .card {
      border: 1px solid #ccc;
      padding: 10px;
      margin-bottom: 10px;
      border-radius: 5px;
      background-color: #f9f9f9;
      }
  </style>
  
<link rel="stylesheet" href="<c:url value='assets/css/common.css'/>" >
<link rel="stylesheet" href="<c:url value='assets/css/custom.css'/>">

<style>
     body {
      font-family: sans-serif;
      margin: 0;
      padding: 0;
    }

    .tab_wrap {
      max-width: 600px;
      margin: 0 auto;
      padding: 10px;
    }

    .tab_area {
       display: flex;
      flex-direction: row;
      margin-bottom: 0;
      border-bottom: 2px solid #ccc;
    }

    .tab_btn {
      flex: 1;
      padding: 14px;
      font-size: 16px;
      background: #f0f0f0;
      border: none;
      border-right: 1px solid #ccc;
      cursor: pointer;
      text-align: center;
    }

    .tab_btn:last-child {
      border-right: none;
    }

    .tab_btn.active {
      background-color: white;
      font-weight: bold;
      border-bottom: 2px solid #007BFF;
      color: #007BFF;
    }

    .tab_panel {
      display: none;
      padding: 20px;
      background-color: #fff;
      border: 1px solid #ccc;
      border-top: none;
    }

    .tab_panel.active {
      display: block;
    }

   .card {
      border: 1px solid #ccc;
      padding: 10px;
      margin-bottom: 10px;
      border-radius: 5px;
      background-color: #f9f9f9;
      }
  </style>
  
<link rel="stylesheet" href="<c:url value='assets/css/common.css'/>" >
<link rel="stylesheet" href="<c:url value='assets/css/custom.css'/>">
<link rel="stylesheet" href="<c:url value='assets/css/list.css'/>" >
</head>
<body>

<!-- ヘッダー（ここから） -->
	<header>
		<h1>
		 <a href="<c:url value='/HomeServlet'/>"><img src="<c:url value='/assets/imgs/TABITILE_logo.png' />" width="250"></a>
		</h1>
	
	<nav>
		<ul>
			<li><a href="<c:url value='/HomeServlet'/>">ホーム</a>
			<li><a href="<c:url value='/VisitorRegistServlet'/>">登録</a>
			<li><a href="<c:url value='/VisitorSearchServlet'/>">検索</a>
			<li><a href="<c:url value='/VisitorListServlet'/>">一覧</a>
		</ul>
	</nav>
	
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
		<div class="tab_wrap">
    		<!--タブメニュー-->
  			<div class="tab_area">
   				 <button class="tab_btn active" data-tab="tab1">訪問地一覧</button>
  				<button class="tab_btn"  data-tab="tab2">候補地一覧</button>
			</div>
   				<button class="tab_btn active" data-tab="tab1">訪問地一覧</button>
    			<button class="tab_btn" 		data-tab="tab2">候補地一覧</button> 
  			</div>
   				<button class="tab_btn active" data-tab="tab1">訪問地一覧</button>
    			<button class="tab_btn" 		data-tab="tab2">候補地一覧</button> 
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
							<div class="card link-card" data-href="visitor.jsp?visitor_id=${visitor.visitor_id}">
								<h3>${visitor.start_date} の思い出</h3>
								<p>${visitor.prefecture_name}</p>
								<p>${visitor.visitor_place}</p>
							</div>
						</c:forEach>
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
							<div class="card link-card" data-href="pickup.jsp?pickup_id=${pickup.pickup_id}">
								<p>${pickup.prefecture_name} </p>
								<p>${pickup.pickup_place}</p>
								<p>${pickup.remarks}</p>
							</div>
						</c:forEach>
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

    // 全タブパネルの表示切り替え target=タブパネル
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