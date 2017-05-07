define(["jquery", "widget/utils", "app/mock"], function($, Utils, Mock){

	var Store = {};
	var mock = false;
	Store.registry = function(url, params, oAfterOkCallback, oAfterPOKCallback){

		if(mock){

		}
		else{
			Utils.postJson(AJAX_ROOT_URL + url, params, oAfterOkCallback, oAfterPOKCallback);
		}

	};
	Store.login = function(url, params, oAfterOkCallback, oAfterPOKCallback){
		if(mock){

		}
		else{
			Utils.postJson(AJAX_ROOT_URL + url, params, oAfterOkCallback, oAfterPOKCallback);
		}
	};

	return Store;

});