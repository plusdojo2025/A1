<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" rel="stylesheet">
   <style>
      #textPassword {
        border: none; /* デフォルトの枠線を消す */
      }
      #fieldPassword {
        border-width: thin;
        border-style: solid;
        width: 200px;
      }
    </style>
<title>｜TABI×TILE</title>
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
	<main>
        <form method="POST" action="/webapp/RegistServlet" id="name_form">
            <p>
                <label>ニックネームの変更<br>
                    <input type="text" id="name" name="name" placeholder="ニックネームの表示">
                    <input type="submit" id="register" name="submit" value="変更">
                    <span id="error_message1"></span>
                </label>
            </p>
        </form>
        <form method="POST" action="/webapp/RegistServlet" id="prefecture_form">
            <p>
                <label>都道府県の変更<br>
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
                    <input type="submit" id="register" name="submit" value="変更">
                    <span id="error_message2"></span>
                </label>
            </p>
        </form>
        <form method="POST" action="/webapp/RegistServlet" id="password_form">
            <p>パスワードの変更</p>
                <label>現在のパスワード<br>
                    <input type="password" id="password1" name="password" placeholder="現在のパスワードを入力">
                    <span id="buttonEye1" class="fa fa-eye-slash" onclick="pushHideButton()"></span>
                </label><br>
                <label>新しいパスワード<br>
                        <input type="password" id="password2" name="password" placeholder="新しいパスワードを入力">
                        <span id="buttonEye2" class="fa fa-eye-slash" onclick="pushHideButton()"></span><br>
                        <input type="submit" id="register" name="submit" value="変更">
                        <span id="error_message3"></span>
                </label>
        </form>
    <script>
    function pushHideButton() {
    var txtPass1 = document.getElementById("password1");
    var txtPass2 = document.getElementById("password2");
    var btnEye1 = document.getElementById("buttonEye1");
    var btnEye2 = document.getElementById("buttonEye2");

    if (txtPass1.type === "text") {
        txtPass1.type = "password";
        btnEye1.className = "fa fa-eye-slash";
    } else {
        txtPass1.type = "text";
        btnEye1.className = "fa fa-eye";
        }
    
    if (txtPass2.type === "text") {
        txtPass2.type = "password";
        btnEye2.className = "fa fa-eye-slash";
    } else {
        txtPass2.type = "text";
        btnEye2.className = "fa fa-eye";
        }
    }
        /*HTML要素をオブジェクトとして取得する*/
    let nameformObj = document.getElementById('name_form');
    let errorMessageObj1 = document.getElementById('error_message1');

    /* [実行]ボタンをクリックしたときの処理 */
    nameformObj.onsubmit = function() {
    /* 各入力項目を必須入力項目とします */
    if (!nameformObj.name.value) {
        errorMessageObj1.textContent = '※ニックネームを入力してください！';
        return false;
        }
    }
    
    let prefectureformObj = document.getElementById('prefecture_form');
    let errorMessageObj2 = document.getElementById('error_message2');

    /* [実行]ボタンをクリックしたときの処理 */
    prefectureformObj.onsubmit = function() {
    /* 各入力項目を必須入力項目とします */
    if (!prefectureformObj.prefecture_id.value) {
        errorMessageObj2.textContent = '※都道府県を選択してください！';
        return false;
        }
    }
    
    let passwordformObj = document.getElementById('password_form');
    let errorMessageObj3 = document.getElementById('error_message3');

    /* [実行]ボタンをクリックしたときの処理 */
    passwordformObj.onsubmit = function() {
    /* 各入力項目を必須入力項目とします */
    if (!passwordformObj.password1.value) {
        errorMessageObj3.textContent = '※現在のパスワードを入力してください';
        return false;
        }else if(!passwordformObj.password2.value){
        errorMessageObj3.textContent = '※新しいパスワードを入力してください';
        return false;
        }else if(passwordformObj.password1.value === passwordformObj.password2.value){
        errorMessageObj3.textContent = '※現在のパスワードと同じものが入力されています';
        return false;            
        }
    }

    </script>
	</main>
<!-- メイン(ここまで) -->

<!-- フッター(ここから) -->
	<div class="footer">
    	<p class="copyright">TABI×TILE　&copy; 2025 Always First.</p>
    	<p>旅のひとコマが、未来を彩るタイルになる</p>
	</div>
<!-- フッター（ここまで） -->
</body>
</html>