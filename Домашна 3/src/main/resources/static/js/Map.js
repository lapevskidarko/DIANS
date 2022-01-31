function mapView() {
    var element = document.getElementById('osm-map');
    var map = L.map(element);
    filterStations(map);
    L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
        attribution: '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
    }).addTo(map);

    var target = L.latLng('41.608635','21.745275');
    map.setView(target,9);

}

function mapUpdate() {
    document.getElementById('osm-map').remove();
    $('#box').append(
        $('<div>').prop({
            id: 'osm-map',
        })
    );
    var element = document.getElementById('osm-map');
    element.className = 'leaflet-container';
    element.style.position = 'absolute';
    element.style.height = '80%';
    element.style.width = '95%';
    element.style.marginTop = '1%';
    element.style.marginLeft = '2.5%';
    var map = L.map(element);
    filterStations(map);
    L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
        attribution: '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
    }).addTo(map);
    var target = L.latLng('41.608635','21.745275');
    map.setView(target,9);
}

function filterStations(map){
    var filter = document.getElementById('filter');
    var ourRequest = new XMLHttpRequest();
    if(filter.value.length === 0)
        // ourRequest.open('GET','https://charging-stations-mk.herokuapp.com/api/stations')
        ourRequest.open('GET','/api/stations')
    else
        // ourRequest.open('GET','https://charging-stations-mk.herokuapp.com/api/stations?filter='+filter.value)
        ourRequest.open('GET','/api/stations?filter='+filter.value)
    ourRequest.onload = function (){
        var ourData = JSON.parse(ourRequest.responseText);
        var marker;
        for(let i = 0; i<ourData.length; i++){
            marker = L.marker(ourData[i].latLong)
            map.addLayer(marker);
            marker.bindPopup('<p>Name of station: ' + ourData[i].name + ' </p>' +
                '<p>Operator: ' + ourData[i].operatorName + '</p>'
                + '<p>Type: ' + ourData[i].type + '</p>'
            );
        }

    };
    ourRequest.send();
}

function getCity(lat,lng) {
    var ourRequest = new XMLHttpRequest();
    ourRequest.open('GET', 'https://api.openweathermap.org/geo/1.0/reverse?lat='+lat+'&lon='+lng+'&limit=2&appid=fb06a46815d7f7c87c719dcf9aa0fa71')
    ourRequest.onload = function () {
        var ourData = JSON.parse(ourRequest.responseText);
        $('#city').val(ourData[0]['name']);
    };
    ourRequest.send();
}

function showLatLng(){
    var element = document.getElementById('osm-map');
    var map = L.map(element);

    L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
        attribution: '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
    }).addTo(map);

    var target = L.latLng('41.608635','21.745275');
    map.setView(target,9);

    var marker;
    map.once('click', function (e){
        marker = L.marker(e.latlng, {
            draggable: true
        });
        getCity(e.latlng.lat,e.latlng.lng);
        $('#cord').val(e.latlng.lat + ", "+ e.latlng.lng);
        marker.on('drag',function (e){
            $('#cord').val(e.latlng.lat + ", "+ e.latlng.lng);
        });
        marker.on('dragend', function () {
            var parts = $('#cord').val().split(', ');
            getCity(parts[0],parts[1]);
        });
        map.addLayer(marker);

    });

}