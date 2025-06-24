<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="cssPath" value="/assets/css" />
<c:set var="imgsPath" value="/assets/imgs" />

<link rel="stylesheet" href="<c:url value='${cssPath}/badge.css'/>">

<!-- バッジ画像差し替えスクリプト -->
<script>

   document.addEventListener("DOMContentLoaded", function () {
       let imgElem;
	  <c:set var="count" value="1" />
	  <c:forEach var="e" items="${sessionScope.badgeList}" varStatus="status">
	  
    	<c:forEach var="i" begin="1" end="9">
    	/* alert(${count}) */
		      <c:if test="${e.badge_id == count}">
		        imgElem = document.getElementById("${count}");
		        
		        
		         if (imgElem) { 
		          imgElem.src = "<c:url value='${imgsPath}/badges/${e.badge_image}' />";
		          
		         } 
		      </c:if>
	     	 <c:set var="count" value="${count + 1}" />
	      </c:forEach>
 	      <c:set var="count" value="1" /> 
       <c:if test="${e.badge_id != status.index + 1}">
     		 ${status.index}
   	 </c:if> 
    </c:forEach>
  }); 
/*   let imgElem;
  document.addEventListener("DOMContentLoaded", function () {
	  
	  <c:forEach var="e" items="${sessionScope.badgeList}" varStatus="status">
	  
    	
	        imgElem = document.getElementById("${status.index+1}");  
	        
	      	imgElem.src = "<c:url value='/assets/imgs/badges/${e.badge_image}' />";      

	  </c:forEach>

  }); */
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
 </div>