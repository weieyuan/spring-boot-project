require.config({
	baseUrl: BASE_URL + "js",
	paths: {
		jquery: 'lib/jquery/jquery-2.1.4',
		bootstrap: 'lib/bootstrap-3.3.7/js/bootstrap.min',
		less: 'lib/less-v2.7.2-1/less.min'
	},
	shim: {
		'bootstrap': {
			deps: ['jquery']
		}
	}
});

require(["widget/utils", "app/store", "bootstrap", "less"], function(Utils, Store) {
	var strHtml = '<form class="form-horizontal" onSubmit="return false;">\
		<div class="form-group">\
            <label for="inputEmail" class="col-sm-2 control-label">邮箱</label>\
            <div class="col-sm-10">\
                <input type="email" class="form-control" id="inputEmail" placeholder="邮箱">\
            </div>\
        </div>\
        <div class="form-group">\
            <label for="inputUserName" class="col-sm-2 control-label">用户名</label>\
            <div class="col-sm-10">\
                <input type="text" class="form-control" id="inputUserName" placeholder="用户名">\
            </div>\
        </div>\
        <div class="form-group">\
            <label for="inputPassword" class="col-sm-2 control-label">密码</label>\
            <div class="col-sm-10">\
                <input type="password" class="form-control" id="inputPassword" placeholder="密码">\
            </div>\
        </div>\
        <div class="form-group">\
            <label for="inputPassword1" class="col-sm-2 control-label">确认密码</label>\
            <div class="col-sm-10">\
                <input type="password" class="form-control" id="inputPassword1" placeholder="确认密码">\
            </div>\
        </div>\
        <div class="form-group">\
            <div class="col-sm-offset-2 col-sm-10">\
                <button id="registry" class="btn btn-default">注册</button>\
            </div>\
        </div>\
        <div id="info" class="alert alert-warning" role="alert" style="display: none;"></div>\
    </form>';

	$("#container").append(strHtml);

	$("#registry").click(function() {
		var email = $("#inputEmail").val();
		// var rE = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		var rE = new RegExp("^[A-Za-z0-9._%'-]+@[A-Za-z0-9.-]+\\.[a-zA-Z]{2,4}$");
		if (!rE.test(email)) {
			validateFail("邮箱地址不正确");
			return;
		}
		var userName = $("#inputUserName").val();
		if (!userName.length) {
			validateFail("用户名不能为空");
			return;
		}
		if (userName.length > 10) {
			validateFail("用户名长度不能超过10");
			return;
		}
		var passWord = $("#inputPassword").val();
		var passWord1 = $("#inputPassword1").val();
		if (passWord.length < 8) {
			validateFail("密码的长度不能小于8");
			return;
		}
		if (passWord.length > 20) {
			validateFail("密码的长度不能大于20");
			return;
		}
		if (passWord !== passWord1) {
			validateFail("两次输入的密码不相等");
			return;
		}

		Store.registry("registry", {
			email: email,
			userName: userName,
			passWord: passWord
		}, function(data) {
			if (data.ok) {
				registrySuccess(data.res);
			} else {
				validateFail(data.res);
			}

		}, function() {

		});

	});

	var validateFail = function(strMsg) {
		$("#info").css("display", "block");
		$("#info").text(strMsg);
	};

	var registrySuccess = function(res){
		window.location.href = AJAX_ROOT_URL + res;
	};

});