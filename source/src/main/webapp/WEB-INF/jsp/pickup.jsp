<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- [ 読込 ] jstl を扱えるように --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- [ 短縮 ] CSSフォルダパス --%>
<c:set var="cssPath" value="/assets/css" />
<%-- [ 短縮 ] 画像フォルダパス --%>
<c:set var="imgsPath" value="/assets/imgs" />
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>候補地の詳細｜TABI×TILE</title>
<!-- 全体共通css -->
<link rel="stylesheet" href="<c:url value='${cssPath}/common.css'/>">
<link rel="stylesheet" href="<c:url value='${cssPath}/custom.css'/>">

<style>
	/* 移行ダイアログ */
	#moved-confirmDialog.custom-dialog p {
		/* テキストをダイアログの枠内に納める */
		white-space: normal;
	}
</style>

</head>
<body>

<!-- ヘッダー（ここから） -->
	<header>
	<div class="header">
		<div class="parent">
			<h1>
			 	<a href="<c:url value='/HomeServlet'/>"
			 		><img src="<c:url value='${imgsPath}/TABITILE_logo.png' />"  
						width="150"
		 		/></a>
			</h1>
			
			<!-- ボタン設置 -->
			<div class="menubutton"
				><a href="<c:url value='/GachaServlet'/>"
					><img 
						src="<c:url value='${imgsPath}/icons/gacha.png' />" 
						width="50" 
				></a
				><a href="<c:url value='/QaServlet'/>"
					><img 
						src="<c:url value='${imgsPath}/icons/qa.png' />" 
						width="50" 
				></a
				><a href="<c:url value='/SettingServlet'/>"
					><img 
						src="<c:url value='${imgsPath}/icons/setting.png' />" 
						width="50" 
				></a
				><a href="<c:url value='/LogoutServlet'/>"
					onclick="showConfirmDialog(); return false;"
					><img 
						src="<c:url value='${imgsPath}/icons/logout.png' />" 
						width="50" 
				/></a
			></div>
		</div>
		
		<!-- ニックネーム表示 -->
		<c:if test ="${not empty sessionScope.user_id}">
			<span class="nickname"
				>${sessionScope.user_id.nickname}&nbsp;さん</span>
		</c:if>
	</div>
	<!-- メニューバー表示 -->
	<nav
		><ul
			><li  class="home"
				><a href="<c:url value='/HomeServlet'/>"
				>ホーム</a
			><li class="regist"
				><a href="<c:url value='/VisitorRegistServlet'/>"
				>登録</a
			><li class="search"
				><a href="<c:url value='/VisitorSearchServlet'/>"
				>検索</a
			><li class="list"
				><a href="<c:url value='/VisitorListServlet'/>"
				>一覧</a
		></ul
	></nav>
	
	</header>
<!-- ヘッダー(ここまで) -->

<!-- メイン（ここから） -->
	<main
		><form
	    	method="POST"
	    	action="<c:url value='/PickupServlet' />"
	        ><div
	            ><label
	                ><span
	                    >都道府県</span
	                ><select name="prefecture"
	                    ><option value="" 
	                    	disabled
	                    		>選択してください</option
                    		><c:forEach 
		                    	var="pre" 
		                    	items="${prefList}"
	                    	><option 
	                    		value="${pre.prefecture_id}"
	                    		<c:if test="${pickup.prefecture_id == pre.prefecture_id}"
	                    			>selected</c:if>
                    				>${pre.prefecture_name}</option
                   		></c:forEach
	                ></select
	            ></label
	            ><label
	                ><span
	                    >場所</span
	                ><input type="text" 
	                    name="place"
	                    value="${pickup.pickup_place}"
	            /></label
	        ></div
	        ><div
	            ><label
	                ><span
	                    >備考</span
	                ><textarea name="remarks"
	                    >${pickup.remarks}</textarea
	            ></label
	        ></div
	        ><div
	            ><button type="button"
	                name="DeleteButton"
	                >削除</button
	            ><button type="button"
	                name="UpdateButton"
	                >更新</button
	            ><button type="button"
	            	name="VisitorButton"
	                >訪問</button
	        ></div
	        ><div
	            ><button
					type="button"
	            	>前のリストに戻る</button
	       	></div
	    ></form
    ></main>
<!-- メイン(ここまで) -->

<!-- フッター(ここから) -->
	<div class="footer">
    	<p class="copyright">TABI×TILE　&copy; 2025 Always First.</p>
    	<p>旅のひとコマが、未来を彩るタイルになる</p>
	</div>
<!-- フッター（ここまで） -->


<!-- [ ダイアログ / HTML ] ログアウト (ここから) -->
<div id="overlay" class="overlay" style="display:none;"></div>
<div id="confirmDialog" class="custom-dialog" style="display:none;">
    <p>ログアウトしてもよろしいですか？</p>
    <img src="<c:url value='${imgsPath}/char/Door.png'/>" alt="ログアウト確認">
    <div class="dialog-buttons">
        <button class="btn-no" onclick="handleConfirm(false)">キャンセル</button>
        <button class="btn-yes" onclick="handleConfirm(true)">ログアウト</button>
    </div>
</div>
<!-- [ ダイアログ / HTML ] ログアウト (ここまで) -->

<!-- [ ダイアログ / HTML ] 更新 (ここから) -->
<div id="update-confirmDialog" class="custom-dialog" style="display:none;">
    <p>更新してもよろしいですか？</p>
    <img src="<c:url value='${imgsPath}/char/Question.png'/>" alt="更新確認">
    <div class="dialog-buttons">
        <button class="btn-yes" onclick="handleUpdateConfirm(true)">更新</button>
        <button class="btn-no" onclick="handleUpdateConfirm(false)">キャンセル</button>
    </div>
</div>
<!-- [ ダイアログ / HTML ] 更新 (ここまで) -->

<!-- [ ダイアログ / HTML ] 削除 (ここから) -->
<div id="delete-confirmDialog" class="custom-dialog" style="display:none;">
    <p>削除してもよろしいですか？</p>
    <img src="<c:url value='${imgsPath}/char/Question.png'/>" alt="更新確認">
    <div class="dialog-buttons">
        <button class="btn-no" onclick="handleDeleteConfirm(true)">削除</button>
        <button class="btn-yes" onclick="handleDeleteConfirm(false)">キャンセル</button>
    </div>
</div>
<!-- [ ダイアログ / HTML ] 削除 (ここまで) -->

<!-- [ ダイアログ / HTML ] 移行 (ここから) -->
<div id="moved-confirmDialog" class="custom-dialog" style="display:none;">
    <p>訪問登録に現在の候補内容を写します。</p>
    <p>ただし、「移行する」と候補の登録は削除されます。</p>
    <p>移行してもよろしいでしょうか？</p>
    <img src="<c:url value='${imgsPath}/char/Question.png'/>" alt="移行確認">
    <div class="dialog-buttons">
        <button class="btn-yes" onclick="handleMovedConfirm(true)">移行する</button>
        <button class="btn-no" onclick="handleMovedConfirm(false)">キャンセル</button>
    </div>
</div>
<!-- [ ダイアログ / HTML ] 移行 (ここまで) -->


<!-- [ ダイアログ / JS ] 共通処理 (ここから) -->
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
<!-- [ ダイアログ / JS ] 共通処理 (ここまで) -->


<!-- JS（ここから） -->
<script type="text/javascript">
	// [ 要素 ] form
	const formObj = document.querySelector('form');
	// [ 要素 ] 確認ダイアログ表示時の背景
	const overlayObj = document.getElementById('overlay');
	// [ 要素 ] 更新ダイアログ
	const updateConfirmDialogObj = document.getElementById('update-confirmDialog');
	// [ 要素 ] 削除ダイアログ
	const deleteConfirmDialogObj =  document.getElementById('delete-confirmDialog');
	// [ 要素 ] 移行ダイアログ
	const movedConfirmDialogObj =  document.getElementById('moved-confirmDialog');

	// [ 表示 ] 更新の確認ダイアログ
	function showUpdateConfirmDialog() {
		overlayObj.style.display = 'block';
		updateConfirmDialogObj.style.display = 'block';
		console.log("更新ダイアログ: 表示");
		
	}

	// [ 受付 ] 更新確認のハンドル
	function handleUpdateConfirm(isConfirmed) {
		overlayObj.style.display = 'none';
		updateConfirmDialogObj.style.display = 'none';
		console.log("更新ダイアログ: 非表示");
		console.log(`入力値: ${isConfirmed}`);
		
		// 更新しないなら
		if (isConfirmed === false){
			// 何もしない
			return;
		}

		// 送信を実行する
		customSubmit("UpdateButton");
	}
	
	// [ 表示 ] 削除の確認ダイアログ
	function showDeleteConfirmDialog() {
		overlayObj.style.display = 'block';
		deleteConfirmDialogObj.style.display = 'block';
		console.log("削除ダイアログ: 表示");
	}

	// [ 受付 ] 削除確認のハンドル
	function handleDeleteConfirm(isConfirmed) {
		overlayObj.style.display = 'none';
		deleteConfirmDialogObj.style.display = 'none';
		console.log("削除ダイアログ: 非表示");
		console.log(`入力値: ${isConfirmed}`);
		
		// 削除しないなら
		if (isConfirmed === false){
			// 何もしない
			return;
		}
		
		// 送信を実行する
		customSubmit("DeleteButton");
	}

	
	// [ 表示 ] 移行の確認ダイアログ
	function showMovedConfirmDialog() {
		overlayObj.style.display = 'block';
		movedConfirmDialogObj.style.display = 'block';
		console.log("移行ダイアログ: 表示");
		
	}

	// [ 受付 ] 移行確認のハンドル
	function handleMovedConfirm(isConfirmed) {
		overlayObj.style.display = 'none';
		movedConfirmDialogObj.style.display = 'none';
		console.log("移行ダイアログ: 非表示");
		console.log(`入力値: ${isConfirmed}`);
		
		// 移行しないなら
		if (isConfirmed === false){
			// 何もしない
			return;
		}

		// 送信を実行する
		customSubmit("MovedButton");
	}

		
	// 実行する内容の入力
	function customSubmit(execution) {
		formObj.onformdata = function(e) {
			// イベントオブジェクトから形式データを取得します。
			const fd = e.formData;
			fd.set("execution", execution);
		}
		
		// 送信を実行する
		formObj.submit();
	}

		// 更新ボタン
	formObj.UpdateButton.onclick = function () {
		// 更新する前に...
		// 確認ダイアログ を表示する
		showUpdateConfirmDialog();
	};
	
	// 削除ボタン
	formObj.DeleteButton.onclick = function () {
		// 削除する前に...
		// 確認ダイアログ を表示する
		showDeleteConfirmDialog();
	};

	// 移行ボタン
	formObj.VisitorButton.onclick = function () {
		// 移行する前に...
		// 確認ダイアログ を表示する
		showMovedConfirmDialog();
	};


</script>
<!-- JS（ここまで） -->



</body>
</html>