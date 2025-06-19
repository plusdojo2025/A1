<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- [ 読込 ] jstl を扱えるように --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- [ 短縮 ] 画像フォルダパス --%>
<c:set var="imgsPath" value="assets/imgs" />
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>候補地の詳細｜TABI×TILE</title>
<link >
</head>
<body>

<!-- ヘッダー（ここから） -->
	<header>
		<h1>
		 	<a href="<c:url value='/HomeServlet'/>"
		 		><img src="<c:url value='/${imgsPath}/TABITILE_logo.png' />"  
	 		/></a>
		</h1>
	
	<nav
		><ul
			><li
				><a href="<c:url value='/HomeServlet'/>"
				>ホーム</a
			><li
				><a href="<c:url value='/VisitorRegistServlet'/>"
				>登録</a
			><li
				><a href="<c:url value='/VisitorSearchServlet'/>"
				>検索</a
			><li
				><a href="<c:url value='/VisitorListServlet'/>"
				>一覧</a
		></ul
	></nav>

	<!-- ボタン設置 -->
	<div class=""
		><a href="<c:url value='/GachaServlet'/>"
			>ガチャ</a
		><a href="<c:url value='/QaServlet'/>"
			>QA</a
		><a href="<c:url value='/SettingServlet'/>"
			>設定</a
		><a href="<c:url value='/LogoutServlet'/>"
			>ログアウト</a
	></div>

	</header>
<!-- ヘッダー(ここまで) -->

<!-- メイン（ここから） -->
    <main
        ><div
            ><label
                ><span
                    >※ 都道府県</span
                ><select name="prefecture"
                    ><option value="${prefectureId}"
                    	>${prefecture}</option
                ></select
            ></label
            ><label
                ><span
                    >場所</span
                ><input type="text" 
                    name="place"
                    value="${place}"
            /></label
        ></div
        ><div
            ><label
                ><span
                    >備考</span
                ><textarea name="remarks"
                    >${remarks}</textarea
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
            	>前に戻る</button
       	></div
    ></main>
<!-- メイン(ここまで) -->

<!-- フッター(ここから) -->
	<div class="footer">
    	<p class="copyright">TABI×TILE　&copy; 2025 Always First.</p>
    	<p>旅のひとコマが、未来を彩るタイルになる</p>
	</div>
<!-- フッター（ここまで） -->

</body>
</html>