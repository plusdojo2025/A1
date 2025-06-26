<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="cssPath" value="/assets/css" />
<c:set var="imgsPath" value="/assets/imgs" />

<link rel="stylesheet" href="<c:url value='${cssPath}/badge.css'/>">

<!-- テスト -->
<%-- <c:if test="${empty sessionScope.badgeList}">
	<p style="color:red;">バッジリストが空です！Servletでセットされていない可能性があります。</p>
</c:if> --%>

<!-- 取得日確認テスト -->
<%-- <c:forEach var="k" items="${sessionScope.badgeList}">
	<p>DEBUG: ${k.badge_id} - ${k.badge_name} - ${k.badgeAcquiredDate}</p>
</c:forEach> --%>
 
 <!-- バッジグリッド（3×3）を表示 -->
<div class="badge-grid">
	<c:forEach var="b" items="${sessionScope.badgeList}" varStatus="status">
		<div class="badge-cell">
			<!-- 画像を表示：獲得していない場合は empty.png -->
			<img
				src="<c:url value='/assets/imgs/badges/${empty b.badgeAcquiredDate ? "empty.png" : b.badge_image}'/>"
				alt="${b.badge_name}" width="80" height="80" />
			
			<!-- nullでなければ日付も表示 -->
			<p>
				<c:if test="${not empty b.badgeAcquiredDate}">
					<!-- yyyy-mm-dd形式にする -->
					${fn:substring(b.badgeAcquiredDate, 0, 10)}
				</c:if>
			</p>
		</div>
	</c:forEach>
</div>

<!-- 獲得バッジ一覧（日付＋称号名）をテキストで一覧表示 -->
 <div class="item-badge">
	 <p class="get-badge">獲得バッジ</p>
	 <c:forEach var="k" items="${sessionScope.badgeList}">
	 	<c:if test="${not empty k.badgeAcquiredDate}">
	 		<div class="date-badge">
	 			<!-- 日付が文字列なので0番目から10文字取ってくる -->
	 			${fn:substring(k.badgeAcquiredDate,0,10)}
	 			　<span>${k.badge_name}</span>地方制覇
	 		</div>
	 	</c:if>
	 </c:forEach>
 </div>
 