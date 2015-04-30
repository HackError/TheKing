var $main = {};
var $map = {};
//карта
$map.map = {}
//слой с картой
$map.div = $('div#map');
//слой с картой местности
$map.divUPD = $('div#map div#mapUPD');

$main.init = function(e) {
    $map.map = e.data.map;
    $map.mapWidth = e.data.mapWidth;
    $map.buidMap();
}

$main.auth = function(data) {
    console.log(data)
}



/*построение карты*/
$map.buidMap = function(){
    console.log("creating map");
    $map.divUPD.css( { 'width': $map.mapWidth * 32 } );
    for ( var $prop in $map.map ) {
        var $sq = $map.map[$prop];
        var $newSQ = $('<div/>').addClass('sq '+$sq.style_class).attr('node', $prop);
        $map.divUPD.append($newSQ);
    }
    $map.divUPD.append( $('<div/>').addClass('clr') );
    console.log("creating map completed");
    $map.div.kinetic();
}