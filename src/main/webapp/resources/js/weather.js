/**
 * Created by ashok on 4/24/2017.
 */
$(function () {
    var pos;
    $.ajax({
        url: "/getZipOfLoggedUser",
        type: "get",
        success: function (data) {
            if (data) {
                getWeatherByZip(data);
            } else {
                getLocation();
            }

        },
        error: function (xhr, status, exception) {
            getLocation();
        }
    });

    function getWeatherByZip(zip) {
        $.ajax({
            url: "http://api.openweathermap.org/data/2.5/forecast",
            data: {
                zip: zip + ",us",
                appid: "6c974d3d4c6fd517225aff5aec044a85"
            },
            type: "get",
            dataType: "json",
            success: function (data) {
                $("#weatherHeader small").remove();
                $("#weatherByLocation").remove();
                $("#weatherHeader").append($("<small>").html(" By Zip: " + zip));
                $("#weatherHeader").after($("<button>").addClass("btn btn-default pull-right").attr("id", "weatherByLocation").html("Show Weather by current Location"));
                parseWeatherData(data);
                $("#weatherByLocation").click(getLocation);
            },
            error: function (xhr, status, exception) {
                getLocation();
            }
        });
    }

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
        $(".dataBody").html("");
        $.each(a, function (key, value) {
            var d1 = $("<div>").addClass("weatherTab row").html($("<h4>").addClass("col-xs-12").html(key));
            $.each(value, function (k, v) {
                console.log(v);
                var date = new Date(v.dt * 1000);
                var d = $("<div>").addClass("col-md-4 col-sm-6 col-xs-12 weatherCols");
                d.append($("<div>").html("Time: " + date.getUTCHours() + ":" + date.getUTCMinutes()));
                d.append($("<div>").html("Temp: " + v.main.temp+" &#8490;"));
                d.append($("<div>").html(v.weather[0].main + " (" + v.weather[0].description + ")"));
                d.append($("<div>").html("Wind: " + v.wind.speed + " (" + v.wind.deg + "&deg;)"));
                if (v.rain) {
                    d.append($("<div>").html("Rain: " + v.rain["3h"]));
                } else {
                    d.append($("<div>").html("Rain: -"));
                }
                d1.append(d);
            });
            $(".dataBody").append(d1);
        });
    }

    function getLocation() {
        $("#weatherByLocation").remove();
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
                    $("#weatherHeader small").remove();
                    $("#weatherHeader").append($("<small>").html(" By Lat: " + pos.coords.latitude + " Long: " + pos.coords.longitude));
                    parseWeatherData(data);
                },
                error: function (xhr, status, exception) {
                    console.log(exception);
                }
            });
        } else {
            $(".dataBody").html("Cannot get location");
        }
    }

    $("#searchZip").click(function () {
        getWeatherByZip($("#inputZip").val());
    })
});