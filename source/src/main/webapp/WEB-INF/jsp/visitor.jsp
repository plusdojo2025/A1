
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- [ 読込 ] jstl を扱えるように --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- [ 短縮 ] CSSフォルダパス --%>
<c:set var="cssPath" value="/assets/css" />
<%-- [ 短縮 ] 画像フォルダパス --%>
<c:set var="imgsPath" value="/assets/imgs" />
<%-- [ 短縮 ] ユーザーフォルダパス --%>
<c:set var="mediaPath" value="/media/${sessionScope.user_id.user_id}" />
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>訪問地の詳細｜TABI×TILE</title>
<!-- 全体共通css -->
<link rel="stylesheet" href="<c:url value='${cssPath}/common.css'/>">
<link rel="stylesheet" href="<c:url value='${cssPath}/custom.css'/>">
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
	    	action="<c:url value='/VisitorServlet' />"
	    	enctype="multipart/form-data"
	        ><div
	            ><label
	                ><span
	                    >開始日</span
	                ><input type="date"
	                    name="start_date"
	                    value="${visitor.start_date}"
	            /></label
	            ><label
	                ><span
	                    >終了日</span
	                ><input type="date"
	                    name="end_date"
	                    value="${visitor.end_date}"
	            /></label
	            ><label
	                ><span
	                    >タイトル</span
	                ><input type="text"
	                    name="title"
	                    value="${visitor.title}"
	            /></label
	        ></div
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
	                    		<c:if test="${visitor.prefecture_id == pre.prefecture_id}"
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
	                    value="${visitor.visitor_place}"
	            /></label
	        ></div
	        ><div
	            ><label
	                ><span
	                    >同行者</span
	                ><input type="text"
	                    name="componion"
	                    value="${visitor.componion}"
	            /></label
	            ><label
	                ><span
	                    >感情</span
	                ><select name="emotion"
	                    ><option value="" 
		                    	disabled
		                    	<c:if test="${visitor.emotion_id == -1}"
		                    		>selected</c:if> 
	                    		>選択してください</option
	                    	><c:forEach 
		                    	var="emo" 
		                    	items="${emoList}"
	                    	><option 
	                    		value="${emo.emotion_id}"
	                    		<c:if test="${visitor.emotion_id == emo.emotion_id}"
	                    			>selected</c:if>
                    				>${emo.emoji}</option
                   		></c:forEach
	                ></select
	            ></label
	        ></div
	        ><div
	            ><label
	                ><span
	                    >感想</span
	                ><textarea name="thought"
	                    >${visitor.thought}</textarea
	            ></label
	        ></div
	        ><div class="photos"
	            ><img <%-- [ 取得 ] メディアパス --%>  
	            	src="<c:url value='${mediaPath}/${visitor.photo1}'/>" 
	                alt="写真1"
	                width="300"
	        	/><input type="hidden"
					name="delReq1" 
	        		value="しない"
	        	/><button type="button"
	        		<c:if test='${empty visitor.photo1}'
	        			>disabled="disabled"</c:if>
	        		>削除</button
				><input type="file" 
	        		accept="image/*"
	        		name="photo1"
	        /></div
	        ><div class="photos"
	            ><img <%-- [ 取得 ] メディアパス --%>  
	            	src="<c:url value='${mediaPath}/${visitor.photo2}'/>" 
	                alt="写真2"
	                width="300"
	        	/><input type="hidden"
					name="delReq2" 
	        		value="しない"
	        	/><button type="button"
	        		<c:if test='${empty visitor.photo2}'
	        			>disabled="disabled"</c:if>
	        		>削除</button
				><input type="file" 
	        		accept="image/*"
	        		name="photo2"
	        /></div
	        ><div class="photos"
	            ><img <%-- [ 取得 ] メディアパス --%>  
	            	src="<c:url value='${mediaPath}/${visitor.photo3}'/>" 
	                alt="写真3"
	                width="300"
	        	/><input type="hidden"
					name="delReq3" 
	        		value="しない"
	        	/><button type="button"
	        		<c:if test='${empty visitor.photo3}'
	        			>disabled="disabled"</c:if>
	        		>削除</button
				><input type="file" 
	        		accept="image/*"
	        		name="photo3"
	        /></div
	        ><div class="photos"
	            ><img <%-- [ 取得 ] メディアパス --%>  
	            	src="<c:url value='${mediaPath}/${visitor.photo4}'/>" 
	                alt="写真4"
	                width="300"
	        	/><input type="hidden"
					name="delReq4" 
	        		value="しない"
	        	/><button type="button"
	        		<c:if test='${empty visitor.photo4}'
	        			>disabled="disabled"</c:if>
	        		>削除</button
				><input type="file" 
	        		accept="image/*"
	        		name="photo4"
	        /></div
	        ><div class="photos"
	            ><img <%-- [ 取得 ] メディアパス --%>  
	            	src="<c:url value='${mediaPath}/${visitor.photo5}'/>" 
	                alt="写真5"
	                width="300"
	        	/><input type="hidden"
					name="delReq5" 
	        		value="しない"
	        	/><button type="button"
	        		<c:if test='${empty visitor.photo5}'
	        			>disabled="disabled"</c:if>
	        		>削除</button
				><input type="file" 
	        		accept="image/*"
	        		name="photo5"
	        /></div
	        ><div
	            ><button type="button"
	                name="DeleteButton"
	                >削除</button
	            ><button type="button"
	                name="UpdateButton"
	                >更新</button
	        ></div
	        ><div
	            ><button
					type="button"
	            	>前のリストに戻る</button
	       	></div
	    ></form>
	</main>
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

	// [ 要素 ] 写真の表示と入力
	const photosObjs = document.querySelectorAll('.photos');
	
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

	// 訪問地レコードから取得した画像のパス（フルバージョン）
	const imgs = [];

	// ファイルの削除をするかどうか
	// プレビュー表示を消すかどうか
	for (let i = 0; i < photosObjs.length; ++i){
		// [ 短縮 ] 画像関連の要素を括る div
		const photos = photosObjs[i];
		// [ デバッグ ] div を取得できているか
		console.log(photos);
		
		// [ 要素 ] ファイル入力値を保持
		const pFInput   = photos.querySelector('input[type="file"]');
		// [ 要素 ] ファイル削除をするかの値を保持
		const pHidInput = photos.querySelector('input[type="hidden"]');
		// [ 要素 ] ファイルを削除する/しない ボタン
		const pBtn 		= photos.querySelector('button[type="button"]');
		// [ 要素 ] 初期値の画像 か 新たに保存する画像 の プレビュー表示
		const img 		= photos.querySelector('img');
		// [ 追加 ] レコードから取得した画像フルパス
		imgs.push(img.src);

		// 新規なら削除要請ボタンの活性化の制御が必要
		// console.log('filePath: ' + imgs[i] + ' / ' + typeof(imgs[i]));
		// console.table(imgs[i].split("/"));
		// [ 取得 ] ファイルパスの末尾（ファイル名）
		const fileName = imgs[i].split("/").slice(-1)[0];
		// [ デバッグ ] ファイル名 を取得できているか
		console.log('fileName: ' + fileName);

		// ファイルを削除する/しない ボタン
		// クリック時
		pBtn.onclick = function (e) {
			// バッククォートでの$参照は衝突するため使用しない...
			console.log(
				pHidInput.name + ': ' + pHidInput.value
				+ img.src);

			// 表示内容の切り替え
			if (pBtn.textContent === "削除"){
				// 削除の要請を送る

				// 現在表示されている画像は
				// 保存されているファイルではない為
				if (fileName === ""){
					// 非活性化
					pBtn.disabled = true
				} else {
					// ボタンにも反映させる
					pHidInput.value = "削除"; // 訳: 削除要請をする
					pBtn.textContent = "リセット"; // 訳: 削除要請を取り消すか？
				}
				
				// 入力されたファイルを削除
				pFInput.value = '';
				// プレビューを削除	
				img.src = '';
			} else {
				// このままで更新

				pHidInput.value = "しない"; // 訳: 削除要請をしない
				// ボタンにも反映させる
				pBtn.textContent = "削除"; // 訳: 削除するか？
				// 初期値の画像パスに戻す
				img.src = imgs[i];
			}

		}

		// ファイル入力値を保持
		// 入力値が変わった時
		pFInput.onchange = function(e) {
			// バッククォートでの$参照は衝突するため使用しない...
			console.log(pFInput.name + ': ' + pFInput.value
				+ img.src);

			// アップロードしたファイルを取得
			const newImg = e.target.files[0];
			// [ デバッグ ] ファイルデータ を取得できているか
			console.log('newImg: ' + newImg);
			
			// ファイル無い場合は...
			// 送信するデータが無い為、元々の保存されたデータに戻す。
			if (newImg === undefined){

				// 保存されているファイルではない為
				if (fileName === ""){
					// 非活性化
					pBtn.disabled = true
				}

				// 元々の状態に戻す
				img.src = imgs[i];
			} else {
				// 活性化させて、プレビュー画像の状態からも戻せるようにする
				pBtn.disabled = false;

				// blob 形式オブジェクトの一時的なURL（これを参照することで画像を表示することが可能）
				const blobUrl = window.URL.createObjectURL(newImg);
				
				// プレビュー表示
				img.src = blobUrl;
				console.log('img[src]: ' + img.src);
			}

			// このままで更新

			// 表示内容の切り替え
			pHidInput.value = "しない"; // 訳: 削除要請をしない
			// ボタンにも反映させる
			pBtn.textContent = "削除"; // 訳: 削除するか？
		}
	}
	
</script>
<!-- JS（ここまで） -->


</body>
</html>
