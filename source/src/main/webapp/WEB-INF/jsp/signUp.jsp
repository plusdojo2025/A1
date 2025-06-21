<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- [ 読込 ] jstl を扱えるように --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>｜TABI×TILE</title>

	<!-- 全体共通css -->
	<link rel="stylesheet" href="<c:url value='assets/css/common.css'/>">
	<link rel="stylesheet" href="<c:url value='assets/css/custom.css'/>">
	<!-- Q&A画面用css -->
	<link rel="stylesheet" href="<c:url value='assets/css/signup.css'/>">
	
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

    <main>
        <form method="POST" action="<c:url value='/SignUpServlet'/>" id="signup_form">
            <table>
                <p>
                    <label>ID<br>
                    <p>※IDは変更できません</p>   
                    <input type="text" id="user_id" name="user_id">
                    </label>
                </p>
                <p>
                    <label>パスワードを設定してください<br>
                    <p>
                        ※パスワードは8文字以上で、<br>
                        大文字、小文字、数字を1文字以上使用してください。<br>
                        パスワードを変更するには、<br>
                        現在のパスワードの入力が必要になります。<br>
                    </p>
                    <input type="password" id="password" name="password">
                    <span id="buttonEye" class="fa fa-eye-slash" onclick="pushHideButton()"></span>
                    </label>
                </p>
                <p>
                    <label>住んでいる地域を選択してください<br>
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
                    </label>
                </p>
                <p>
                    <label>ニックネームを設定してください</label><br>
                    <input type="text" id="nickname" name="nickname">
                </p>
                <p colspan="2">
                    <input type="submit" id="register" name="submit" value="登録">
                    <span id="error_message"></span>
                </p>
            </table>
        </form>
        <!-- ダイアログHTML(ここから) -->
		<div id="overlay" class="overlay" style="display:none;"></div>
		<div id="confirmDialog" class="custom-dialog" style="display:none;">
		    <p>ログアウトしてもよろしいですか？</p>
		    <img src="<c:url value='/assets/images/logo3.png' />" alt="ログアウト確認">
		    <div class="dialog-buttons">
		        <button class="btn-no" onclick="handleConfirm(false)">キャンセル</button>
		        <button class="btn-yes" onclick="handleConfirm(true)">ログアウト</button>
		    </div>
		</div>
		<!-- ダイアログHTML(ここまで) -->
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
    }   
    
    <!-- ダイアログJS(ここから) -->
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
    <!-- ダイアログJS(ここまで) -->
    </script>
    </main>

	<footer class="footer">
		<p class="copyright">TABI×TILE　&copy; 2025 Always First.</p>
		<p>旅のひとコマが、未来を彩るタイルになる</p>
	</footer>
</body>
</html>