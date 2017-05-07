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

require(["widget/utils", "app/store", "bootstrap", "less"], function(Utils, Stores) {

    var strHtml = '<form class="form-horizontal" onSubmit="return false;">\
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
            <div class="col-sm-offset-2 col-sm-10">\
                <button id="signIn" type="submit" class="btn btn-default">登录</button>\
                <button id="forgetPassword" type="submit" class="btn btn-default">忘记密码</button>\
                <button id="regigtry" type="submit" class="btn btn-default">注册</button>\
            </div>\
        </div>\
        <div id="info" class="alert alert-warning" role="alert" style="display: none;"></div>\
    </form>';

    $("#container").append(strHtml);

    $("#signIn").click(function() {
        var userName = $("#inputUserName").val();
        var passWord = $("#inputPassword").val();
        Stores.login("login", {
            userName: userName,
            passWord: passWord,
        }, function(data) {
            if (!data.ok) {
                validateFail(data.res);
            } else {
                loginSuccess(data.res);
            }
        }, function() {

        });
    });

    var validateFail = function(strMsg) {
        $("#info").css("display", "block");
        $("#info").text(strMsg);
    };

    var loginSuccess = function(res) {
        window.location.href = AJAX_ROOT_URL + res;
    };

});