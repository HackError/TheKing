var $map = {'width':$('#map').attr('mapWidth'),'height':$('#map').attr('mapHeight')} ;
var $userID = 0;
var $to = ''; //timer

$.get('/client_updater.php?get_user_id',function(data){
            $userID=data;
    });
    
var $owners = [];
/*сбор всех игроков*/
var $i = 0;
$('#map .st_p').each(function(){
	$owners[$i] = {'id':$(this).attr('owner')};
	$i++;
});

/*сдвигаем пользовательски див в другой угол*/
$('#user_data').css({'left':$.cookie('left'),'top':$.cookie('top')});
$('#user_data').draggable({ opacity: '0.4',cancel: "#form_user_p_work, input, label, button", containment: "#map", cursor: "move", snap: "#map", stop: function() { $.cookie('left', $('#user_data').css('left')); $.cookie('top', $('#user_data').css('top')); } });
//$('#user_data').draggable();

$('#eventDiv').css({'left':$.cookie('eleft'),'top':$.cookie('etop')});
$('#eventDiv').draggable({ opacity: '0.4',cancel: "input, label, button", containment: "#map", cursor: "move", snap: "#map", stop: function() { $.cookie('eleft', $('#eventDiv').css('left')); $.cookie('etop', $('#eventDiv').css('top')); } });
//$('#eventDiv').draggable();



$(window).load(function () {
	
	$('#map').kinetic();
	
	$('#logo').css({"left": ($(window).width() - $('#logo').outerWidth()) / 2 + $(window).scrollLeft() + "px",'display':'none'});
	
	
	/*BIND set_user_p_work*/
	$('form#form_user_p_work input').on('keyup',function(){
		set_user_p_work();
	});
	
	setInterval(function() { update() }, 10000);
	bounds();
        
        $('#map').animate({
        	scrollTop: $("div[owner="+$userID+"]").offset().top+1,
                scrollLeft: $("div[owner="+$userID+"]").offset().top+1,
	}, 400);   
        
        
    events();
	
	glowMap();
	
	
});

/*ПОДСВЕТКА КВАДРАТА КАРТЫ ПРИ НАВЕДЕНИИ*/
function glowMap() {
	var $div = '<div class="glowSQ"></div>';
	
	$('.sq').on('mouseenter',function(){
		$(this).prepend($div);
	})
	.on('mouseleave',function(){
		$('.glowSQ').remove();
	})
	.on('click',function(){
		show_ground_sq_window(this);
	});
}
/*ЗАПРОС ИНФОРМАЦИИ О КЛЕТКЕ*/
function show_ground_sq_window($sq) {
	
	$.ajax({
		type: "POST",
		dataType: "html",
		url: '/client_updater.php?get_ground_info',
		data: {node:$($sq).attr("node")},
		success: function(data) {

			$('#show_ground').remove();
			
			console.log(data);
			
			$($sq).prepend('<div id="show_ground" class="arrow_box"></div>').find('#show_ground').html(data);
			
			var $sg_box = $('#show_ground');
			var $box = {};		
			
			$sg_box.css({width:250});
			
			$box.height = $sg_box.outerHeight();
			$box.width = $sg_box.outerWidth();
			$box.margin = {left:0, top:(($box.height+10)*-1)};
			$box.offset = $sg_box.offset();
					
			if (($box.offset.top - $box.height) < 0 && ($box.offset.left + $box.width) > $(window).width())
			{
				$box.margin.left -= $box.width-32;
				$box.margin.top = 42;
				$sg_box.removeClass('arrow_box').addClass('arrow_box_bright');
			}
			else if (($box.offset.left + $box.width) > $(window).width()) {
				$box.margin.left -= $box.width-32;
				$sg_box.removeClass('arrow_box').addClass('arrow_box_right');		
			}
			else if (($box.offset.top - $box.height) < 0) {
				$box.margin.top = 42;
				$sg_box.removeClass('arrow_box').addClass('arrow_box_bleft');		
			}
			
			
			//$sg_box.css({'margin-top':$box.margin.top});
			$sg_box.css({'margin-left':$box.margin.left, 'margin-top':$box.margin.top});		
			
			$sg_box.on('mouseleave',function(){
				$sg_box.remove();
			});
		}
	});
	
	
	
	
	
	
	
}

function events()
{    
	if ($('#eventDiv').html()=='') $('#eventDiv').css({'display':'none'});
    var $form = $('#event');
    var $res = {'gold':'0','wood':'0','grain':'0','money':'0','peasant':'0','soldiers':'0'}
    var $err = '';
    if ($form.length) {
        $('#sendAnswerEvent').unbind();
        
        $('#sendAnswerEvent').on('click',function(){
            /*проверка введённых данных*/
            $.each($res, function($key, $val) {
                $res[$key] = $($key).html();
                var $input = $form.find('input[name='+ $key +']');
                if ($input.length) {
                    if ($input.val() > $res[$key]) $err = "need"+$key;
                }
            });
            
            console.log($res);
            
            if ($err=='')
            {
                $.post('/client_updater.php?set_event_answer',$form.serialize(),function(data){
                    if (data=='') $('#eventDiv').fadeOut();
					
					$('#eventDiv').html(data);
                    
                    update_user_data();
                    events();
                });
            }
            console.log($err);
            return false;
        });
    }    
}

function update()
{        
	map_update();
	update_units_on_map();	
    update_user_data();
    events();
	event_updater();
}

/*обновление окна события при новом событии*/
function event_updater() {
	$.get('/client_updater.php?get_user_event',function(data){
		if (data!="" && $('#eventDiv').css('display') == 'none') {			
			$('#eventDiv').html(data).fadeIn();
			events();
		}		
	});	
}

/*установка распределения крестьян*/
function set_user_p_work() {
	$.post('/client_updater.php?set_user_p_work',$('form#form_user_p_work').serialize());
}

/*обновление пользовательских данных*/
function update_user_data() {
    $.get('/client_updater.php?get_user_data',function(data){
        $('#user_data .updateData').html(data);
    });
}

/*обновление карты*/
function map_update()
{
	$.get('/client_updater.php?print_map',function(data){
		if (data != 'notUpdate')
		{
			$('#mapUPD').html(data);
			bounds();
			glowMap();
		}		
	});
}

/*обновление позиций юнитов на карте*/
function update_units_on_map()
{
	$.getJSON( "/client_updater.php?unit_on_map", function(data) {
		
		for(var $u in data) {
			var $unit = data[$u];
			var $uOm = $('#unit'+$unit.id);
			$uOm.attr({'x':$unit.x, 'y':$unit.y}).delay( 100 ).animate({top:$unit.x*32,left:$unit.y*32});
		}				
	});
}

/*рисуем границы царств*/
function bounds()
{
	$('#map .bounds').remove();
	for(var i=0; i<$owners.length; i++) {
		var $ownerID = $owners[i].id;
		$('#map .sq[owner='+$ownerID+']').append('<div owner="'+$ownerID+'" class="bounds bL bR bT bB"></div>').find('.bounds').each(function(){
			var $sq = $(this).parent();
			var $x = $sq.attr('x')*1;
			var $y = $sq.attr('y')*1;

			var $node = node($x,$y);
			//влево
			if ($('#map .sq[node='+($node-1)+']').attr('owner') == $ownerID) $(this).removeClass('bL');
			//вправо
			if ($('#map .sq[node='+($node+1)+']').attr('owner') == $ownerID) $(this).removeClass('bR');
			//вверх			
			if ($('#map .sq[node='+($node-$map.width*1)+']').attr('owner') == $ownerID) $(this).removeClass('bT');
			//вниз
			if ($('#map .sq[node='+($node+$map.width*1)+']').attr('owner') == $ownerID) $(this).removeClass('bB');
		});
		//console.log($owners);
	}

}

function node($x, $y) {
	return ($y*1 * $map.width + $x*1);
}
	
function coord($i) {
	var $x = $i % $map.width;
	var $y = ($i - $x) / $map.width;
	
	var $coord = {'x':$x,'y':$y};
	
	return $coord;
}

$.fn.alignCenterScreen = function() {
	this.css("position", "absolute");
	this.css("top", ($(window).height() - this.outerHeight()) / 2 + $(window).scrollTop() + "px");
	this.css("left", ($(window).width() - this.outerWidth()) / 2 + $(window).scrollLeft() + "px");
	return this
};