<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- [ 読込 ] jstl を扱えるように --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<link href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" rel="stylesheet">

<title>新規登録｜TABI×TILE</title>

	<!-- 全体共通css -->
	<link rel="stylesheet" href="<c:url value='assets/css/common.css'/>">
	<link rel="stylesheet" href="<c:url value='assets/css/custom.css'/>">
	
	<!-- 新規登録画面用css -->
	<link rel="stylesheet" href="<c:url value='assets/css/signup.css'/>">
	
</head>
<body>

<!-- ヘッダー（ここから） -->
	<header>
		<div class="header">
			<a href="<c:url value='/HomeServlet'/>"><img src="<c:url value='/assets/imgs/TABITILE_logo.png' />" width="250"></a>
		</div>
	</header>
<!-- ヘッダー(ここまで) -->

    <main class=main>
    <h1>新規登録</h1>
        <form method="POST" action="<c:url value='/SignUpServlet'/>" id="signup_form">
        <!-- ID入力欄 -->
            <p>ID</p>
                <div class=rule>
                	※IDは変更できません。
                </div>   
		        <input type="text" id="user_id" class="nickname" name="user_id">
		<!-- パスワード入力欄 -->
            <p>パスワード</p>
             <div class=rule>
                 ※パスワードは8文字以上で、<br>
                 大文字、小文字、数字を1文字以上使用してください。<br>
                 パスワードを変更するには、<br>
                 現在のパスワードの入力が必要になります。<br>
             </div>
             <div class="pass">
	            <p class="pass2">
                   <input type="password" id="password" class="pass3" name="password">
                   <span id="buttonEye" class="fa fa-eye-slash" onclick="pushHideButton()"></span>
               	</p>
             </div>
        <!-- 都道府県選択欄 -->
          	<p>都道府県</p>
	             <div class=rule>
	                 あなたの住んでいる都道府県を選択してください。<br>
	             </div>
	                <select name="prefecture_id">
	                    <option value="" selected>選択してください</option>
	                    <option value="1">北海道</option>
	                    <option value="2">青森県</option> 
	                    <option value="3">岩手県</option>
	                    <option value="4">宮城県</option>
	                    <option value="5">秋田県</option>
	                    <option value="6">山形県</option> 
	                    <option value="7">福島県</option>
	                    <option value="8">茨城県</option>
	                    <option value="9">栃木県</option>
	                    <option value="10">群馬県</option> 
	                    <option value="11">埼玉県</option>
	                    <option value="12">千葉県</option>
	                    <option value="13">東京都</option>
	                    <option value="14">神奈川県</option> 
	                    <option value="15">新潟県</option>
	                    <option value="16">富山県</option>
	                    <option value="17">石川県</option>
	                    <option value="18">福井県</option> 
	                    <option value="19">山梨県</option>
	                    <option value="20">長野県</option>
	                    <option value="21">岐阜県</option>
	                    <option value="22">静岡県</option> 
	                    <option value="23">愛知県</option>
	                    <option value="24">三重県</option>
	                    <option value="25">滋賀県</option>
	                    <option value="26">京都府</option> 
	                    <option value="27">大阪府</option>
	                    <option value="28">兵庫県</option>
	                    <option value="29">奈良県</option>
	                    <option value="30">和歌山県</option> 
	                    <option value="31">鳥取県</option>
	                    <option value="32">島根県</option> 
	                    <option value="33">岡山県</option>
	                    <option value="34">広島県</option>
	                    <option value="35">山口県</option>
	                    <option value="36">徳島県</option> 
	                    <option value="37">香川県</option>
	                    <option value="38">愛媛県</option>
	                    <option value="39">高知県</option>
	                    <option value="40">福岡県</option> 
	                    <option value="41">佐賀県</option>
	                    <option value="42">長崎県</option>
	                    <option value="43">熊本県</option>
	                    <option value="44">大分県</option> 
	                    <option value="45">宮崎県</option>
	                    <option value="46">鹿児島県</option>
	                    <option value="47">沖縄県</option>     
	                </select>
	   <!-- ニックネーム入力欄 -->
               <p>ニックネーム</p>
	               	<p class=rule>
	                    アプリ内で使用する名前を入力してください。<br>
	                </p>
	           <input type="text" id="nickname" class="nickname" name="nickname">
       <!-- 登録ボタン -->       
            <p colspan="2">
                <p class=button><input type="submit" id="register" class="push-button" name="submit" value="登録"></p>
                <span id="error_message" style="color:red;"></span>
            </p>
		<!-- ログイン画面に戻るボタン -->
		    <div class="return-buttons">
		 		<a href="<c:url value='/LoginServlet' />">ログイン画面に戻る</a>
		    </div>
        </form>
    </main>

<!-- フッター(ここから) -->
	<div class="footer">
		<p class="copyright">TABI×TILE　&copy; 2025 Always First.</p>
		<p>旅のひとコマが、未来を彩るタイルになる</p>
	</div>
<!-- フッター（ここまで） -->
	
<script>
    /*パスワードを隠すための処理*/
    function pushHideButton() {
    var txtPass = document.getElementById("password");
    var btnEye = document.getElementById("buttonEye");
    if (txtPass.type === "text") {
        txtPass.type = "password";
        btnEye.className = "fa fa-eye-slash";
    } else {
        txtPass.type = "text";
        btnEye.className = "fa fa-eye";
        
        }
    }
    
    /*HTML要素をオブジェクトとして取得する*/
    let formObj = document.getElementById('signup_form');
    let errorMessageObj = document.getElementById('error_message');

    /* [実行]ボタンをクリックしたときの処理 */
    formObj.onsubmit = function() {
	    /* 各入力項目を必須入力項目とします */
	    if (!formObj.user_id.value) {
	        errorMessageObj.textContent = '※ユーザーIDを入力してください！';
	        return false;
	        }else if(!formObj.password.value){
	        errorMessageObj.textContent = '※パスワードを入力してください！';
	        return false;
	        }else if(!formObj.prefecture_id.value){
	        errorMessageObj.textContent = '※都道府県を選択してください！';
	        return false;            
	        }else if(!formObj.nickname.value){
	        errorMessageObj.textContent = '※ニックネームを入力してください！';
	        return false;                   
	        }
	    
	    if (formObj.password.value.length < 8) {
	        event.preventDefault(); // フォーム送信を停止
	        errorMessageObj.textContent = 'パスワードは8文字以上で入力してください。';
	      } else {
	        errorMessageObj.textContent = ''; // エラーをクリア
	      }
	    
	    const password = formObj.password.value;
	    const regex = /^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)[A-Za-z\d]*$/;

	    if (regex.test(password)) {
	    	errorMessageObj.textContent = ''; // エラーをクリア
	    } else {
	    	errorMessageObj.textContent = 'パスワードは大文字、小文字、数字を1文字以上含めてください';
	    }
    
    }   
    </script>
</body>
</html>