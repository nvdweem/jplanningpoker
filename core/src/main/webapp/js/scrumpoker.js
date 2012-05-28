var Scrumpoker = function($) {
	var cardsShown = false;
	var lastRequest = false;
	
	function init() {
		$('#yourname').hide();
		$.get('admin', function(r) {if (r.admin == 'true') initAdmin(); else initNormal(); });
		
		// Name
		$.get('changename', function(d){$('#yourname').val(d.name);});
		$(document).on('keyup', '#yourname', changeName);
	}
	
	function initAdmin() {
		$('#content').load('admin.html?');
		$(document).on('click', '.qrcode', function() {
			$('<div title="QR Code"> Scan this code with your mobile to join this poker session!<br/><img src="qrcode" style="width: 100%; height: 100%;" /></div>').dialog();
		})
		.on('click', '.showcards', showCards)
		.on('click', '.hidecards', hideCards)
		.on('click', '.reset', reset)
		;
		
		adminLoop();
	}
	function initNormal() {
		$('#yourname').show();
		$('#content').load('normal.html');
		$(document).on('click', '.selectable', function() {
			$('.selected').removeClass('selected');
			$(this).addClass('selected');
			$.post('poker', {number: $(this).text()});
		})
		.on('click', '.selectable a', function(e) {e.preventDefault();})
		;
		normalLoop();
	}
	function normalLoop() {
		$.get('poker', function(d) {
			$('.selected').removeClass('selected');
			$('.card').filter(function() {return $(this).text().trim() == d.poker;}).addClass('selected');
		});
		setTimeout(normalLoop, 1000);
	}
	
	function adminLoop() {
		if (cardsShown) return;
		showUsers();
		setTimeout(adminLoop, 1000);
	}
	function showCards() {
		if (lastRequest && lastRequest.abort) lastRequest.abort();
		cardsShown = true;
		$.getJSON('unsetchoices');
		$('.card').addClass('shown').removeClass('chosen').removeClass('notchosen');
		$(this).removeClass('showcards').addClass('hidecards').find('span').text('Hide cards').end().find('i').removeClass('icon-eye-open').addClass('icon-eye-close');
	}
	function hideCards() {
		$('.card').removeClass('shown');
		$(this).addClass('showcards').removeClass('hidecards').find('span').text('Show cards').end().find('i').addClass('icon-eye-open').removeClass('icon-eye-close');
		cardsShown = false;
		adminLoop();
	}
	function reset() {
		$.get('reset');
		$('.hidecards').click();
	}
	
	function showUsers() {
		lastRequest = $.getJSON('status', {rnd: new Date()}, function(data) {
			$('.users').html(kite('#user_tmpl')(data));
		});
	}
	
	function changeName() {
		$.post('changename', {name: this.value});
	}
	
	$(init);
	
}(jQuery);
