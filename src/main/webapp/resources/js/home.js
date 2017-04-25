/**
 * Created by ashok on 4/24/2017.
 */

$(function () {
    $("#offerRide").click(function () {
        $(this).css("background-color","#92acf8");
        $("#askForRide").css("background-color","#b0dcea");
        loadPosts("offers","Ride Offers");
    });

    $("#askForRide").click(function () {
        $(this).css("background-color","#92acf8");
        $("#offerRide").css("background-color","#b0dcea");
        loadPosts("rides","Ride Requests");
    });

    loadPosts("offers","Ride Offers");
});

function loadPosts(type,title) {
    $(".loading").show();
    var heading = $("<h2>").addClass("title").html(title);
    var body = $("#bodyOfHome");
    body.html(heading);
    $.ajax({
        url: "",
        type:"post",
        data:{
            rideType:type
        },
        success:function (data) {
            $.each(data,function (key,value) {
                var div = $("<div>").addClass("posts").html("");
                body.append(elm);
            })
        },
        error:function (xhr,status,exception) {
            var error = $("<h5>").addClass("error").html("Error Loading Posts");
            body.append(error);
        },
        complete:function () {
            $(".loading").hide();
        }
    });

}
