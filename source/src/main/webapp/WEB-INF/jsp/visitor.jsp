<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- [ 読込 ] jstl を扱えるように --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- [ 短縮 ] コンテキストパス --%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%-- [ 短縮 ] アセッツパス --%>
<c:set var="assetsPath" value="${contextPath}/assets" />
<!DOCTYPE html>
<html lang="ja">
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

    <main
        ><div
            ><label
                ><span
                    >開始日</span
                ><input type="date"
                    name="start_date"
                    value="${startDate}"
            /></label
            ><label
                ><span
                    >終了日</span
                ><input type="date"
                    name="end_date"
                    value="${endDate}"
            /></label
            ><label
                ><span
                    >タイトル</span
                ><input type="text"
                    name="title"
                    value="${title}"
            /></label
        ></div
        ><div
            ><label
                ><span
                    >都道府県</span
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
                    >同行者</span
                ><input type="text"
                    name="componion"
                    value="${componion}"
            /></label
            ><label
                ><span
                    >感情</span
                ><select name="emotion"
                    ><option value="${emotionId}"
                        >${emotion}</option
                ></select
            ></label
        ></div
        ><div
            ><label
                ><span
                    >感想</span
                ><textarea name="thought"
                    >${thought}</textarea
            ></label
        ></div
        ><div
            ><img src="${assetsPath}/imgs/${fileName}" 
                alt="写真1"
                width="300"
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