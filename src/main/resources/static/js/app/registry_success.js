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

require([], function(){
	
});
