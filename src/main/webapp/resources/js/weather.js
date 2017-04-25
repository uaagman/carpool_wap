
$(function () {
    var pos;
    $.ajax({
        url: "http://api.openweathermap.org/data/2.5/forecast",
        data: {
            zip: "525579,us",
            appid: "6c974d3d4c6fd517225aff5aec044a85"
        },
        type: "get",
        dataType: "json",
        success: function (data) {
            parseWeatherData(data);
        },
        error: function (xhr, status, exception) {
            getLocation();
        }
    });

    function parseWeatherData(data) {
        var a = {};
        $.each(data.list, function (key, value) {
            var date = new Date(value.dt * 1000);
            a[date.getUTCFullYear() + "-" + (date.getUTCMonth() + 1) + "-" + date.getUTCDate()] = {};
        });
        $.each(data.list, function (key, value) {
            var date = new Date(value.dt * 1000);
            a[date.getUTCFullYear() + "-" + (date.getUTCMonth() + 1) + "-" + date.getUTCDate()][date.getTime() / 1000] = value;
        });

        $.each(a, function (key, value) {
            $.each(value, function (k, v) {
                // console.log(v);
            });
        });
        console.log(a);
    }

    function getLocation() {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(getPos);
        } else {
            pos = null;
        }
    }

    function getPos(position) {
        pos = position;
        secondAjaxCall();
    }

    function secondAjaxCall() {
        if (pos) {
            $.ajax({
                url: "http://api.openweathermap.org/data/2.5/forecast",
                data: {
                    lat: pos.coords.latitude,
                    lon: pos.coords.longitude,
                    appid: "6c974d3d4c6fd517225aff5aec044a85"
                },
                type: "get",
                dataType: "json",
                success: function (data) {
                    parseWeatherData(data);
                },
                error: function (xhr, status, exception) {
                    console.log(exception);
                }
            });
        }else{
            $(".dataBody").html("Cannot get location");
        }
    }
});