/**
 * Created by ashok on 4/24/2017.
 */


$(function () {
    setInterval(function () {
        $.get("/js/getLatestPosts").done(function (data) {
            $.each(data, function (key, value) {
                var div = $("<div>").addClass("notification").append([
                    $("<div>").html(value.post),
                    $("<div>").html("Type: " + value.postType),
                    $("<div>").html("From: " + value.fromState + "(" + value.fromCity + ")"),
                    $("<div>").html("To: " + value.toState + "(" + value.toCity + ")")
                ]).click({postType:value.postType},showTheNotification);
                $("body").append(div);
            });
        });
    }, 10000);

    function showTheNotification(event) {
        var postType = event.data.postType;
        $(this).remove();
        if(postType == "pool"){
            $("#askForRide").click();
        }else{
            $("#offerRide").click();
        }
    }
});


/*
$(function () {
    var currentTime = Date.now();
    setInterval(function () {
        $.get("/js/getLatestPosts/"+currentTime).done(function (data) {
            $.each(data, function (key, value) {
                var div = $("<div>").addClass("notification").append([
                    $("<div>").html(value.post),
                    $("<div>").html("Type: " + value.postType),
                    $("<div>").html("From: " + value.fromState + "(" + value.fromCity + ")"),
                    $("<div>").html("To: " + value.toState + "(" + value.toCity + ")")
                ]).click({postType:value.postType},showTheNotification);
                $("body").append(div);
            });
            currentTime = Date.now();
        });
    }, 10000);

    function showTheNotification(event) {
        var postType = event.data.postType;
        if(postType == "pool"){
            $("#askForRide").click();
        }else{
            $("#offerRide").click();
        }
        $(this).remove();
    }
});*/
