<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="cssPath" value="/assets/css" />
<c:set var="imgsPath" value="/assets/imgs" />

<link rel="stylesheet" href="<c:url value='${cssPath}/badge.css'/>">

<c:if test="${empty sessionScope.badgeList}">
  <p style="color:red;">バッジリストが空です！Servletでセットされていない可能性があります。</p>
</c:if>

<c:forEach var="k" items="${sessionScope.badgeList}">
  <p>DEBUG: ${k.badge_id} - ${k.badge_name} - ${k.badgeAcquiredDate}</p>
</c:forEach>

<!-- バッジ画像差し替えスクリプト -->
<script>
  document.addEventListener("DOMContentLoaded", function () {
    <c:forEach var="b" items="${sessionScope.badgeList}">
      const img = document.getElementById("${b.badge_id}");
      if (img && "${b.badgeAcquiredDate}" !== "null") {
        img.src = "<c:url value='${imgsPath}/badges/${b.badge_image}'/>";
        const date = document.getElementById("date${b.badge_id}");
        if (date) {
          date.textContent = "${fn:substring(b.badgeAcquiredDate, 0, 10)}";
        }
      }
    </c:forEach>
  });
</script>

<!-- 初期状態 empty.png で3×3 -->
<div class="badge-grid">
	<div class="badge-cell">
  		<img id="1" src="<c:url value='${imgsPath}/badges/empty.png'/>" alt="未獲得">
  		<p id="date1"></p>
	</div>
	<div class="badge-cell">
  		<img id="2" src="<c:url value='${imgsPath}/badges/empty.png'/>" alt="未獲得">
  		<p id="date2"></p>
	</div>
	<div class="badge-cell">
		<img id="3" src="<c:url value='${imgsPath}/badges/empty.png'/>" alt="未獲得">
 		<p id="date3"></p>
	</div>
	<div class="badge-cell">
		<img id="4" src="<c:url value='${imgsPath}/badges/empty.png'/>" alt="未獲得">
	<p id="date4"></p>
	</div>
	<div class="badge-cell">
		<img id="5" src="<c:url value='${imgsPath}/badges/empty.png'/>" alt="未獲得">
 		<p id="date5"></p>
 	</div>
	<div class="badge-cell">
 		<img id="6" src="<c:url value='${imgsPath}/badges/empty.png'/>" alt="未獲得">
		<p id="date6"></p>
	</div>
	<div class="badge-cell">
 		<img id="7" src="<c:url value='${imgsPath}/badges/empty.png'/>" alt="未獲得">
 		<p id="date7"></p>
	</div>
	<div class="badge-cell">
		<img id="8" src="<c:url value='${imgsPath}/badges/empty.png'/>" alt="未獲得">
		<p id="date8"></p>
	</div>
	<div class="badge-cell">
		<img id="9" src="<c:url value='${imgsPath}/badges/empty.png'/>" alt="未獲得">
		<p id="date9"></p>
	</div>
 </div>
 
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
 </div> --%>
 
 
 <h2>デバッグ出力：バッジ一覧</h2>
<ul>
<c:forEach var="b" items="${sessionScope.badgeList}">
  <li>${b.badge_name}：${b.badgeAcquiredDate}</li>
</c:forEach>
</ul>

 <%-- <div class="badge-grid">
  <c:forEach var="b" items="${sessionScope.badgeList}">
    <div class="badge-cell">
      <img 
        src="<c:url value='/assets/imgs/badges/${empty b.badgeAcquiredDate ? "empty.png" : b.badge_image}' />" 
        alt="バッジ画像" 
        width="80" height="80"
      />
      <p>
        <c:if test="${not empty b.badgeAcquiredDate}">
          <fmt:formatDate value="${b.badgeAcquiredDate}" pattern="yyyy/MM/dd"/>
        </c:if>
      </p>
    </div>
  </c:forEach>
</div> --%>


<script>
  document.addEventListener("DOMContentLoaded", function () {
    <c:forEach var="b" items="${sessionScope.badgeList}">
      const img = document.getElementById("${b.badge_id}");
      if (img) {
        img.src = "<c:url value='${imgsPath}/badges/${b.badge_image}'/>";
      }

      const date = document.getElementById("date${b.badge_id}");
      if (date) {
        date.textContent = "${fn:substring(b.badgeAcquiredDate, 0, 10)}";
      }
    </c:forEach>
  });
</script>



<p>badgeListの確認：</p>
<c:out value="${sessionScope.badgeList}" />