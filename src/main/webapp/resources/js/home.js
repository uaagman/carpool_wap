/**
 * Created by ashok on 4/24/2017.
 */

$(function () {
    $("#offerRide").click(function () {
        $(this).css("background-color", "#92acf8");
        $("#askForRide").css("background-color", "#b0dcea");
        loadPosts("share", "Ride Offers");
    });

    $("#askForRide").click(function () {
        $(this).css("background-color", "#92acf8");
        $("#offerRide").css("background-color", "#b0dcea");
        loadPosts("pool", "Ride Requests");
    });

    loadPosts("share", "Ride Offers");

    function loadPosts(type, title) {
        $(".loading").show();
        var heading = $("<h2>").addClass("title").html(title);
        var body = $("#bodyOfHome");
        body.html(heading);
        var url = "/posts/postType/" + type;
        $.ajax({
            url: url,
            type: "get",
            success: function (data) {
                getUserAndFillData(data);
            },
            error: function (xhr, status, exception) {
                var error = $("<h5>").addClass("error").html("Error Loading Posts");
                body.append(error);
            },
            complete: function () {
                $(".loading").hide();
            }
        });
    }

    function getUserAndFillData(data) {
        console.log(data);
        $.each(data, function (key, val) {
            $.ajax({
                url: "/js/getEmailAndNameFromId/" + val.userId,
                type: "get",
                success: function (data1) {
                    console.log(data1);
                    if (data1) {
                        var div = $("<div>").addClass("posts col-md-6");
                        var title = $("<div>").addClass("title").append([
                            $("<span>").addClass("postBy pull-left").html(data1.fullName + " (" + $("<small>").html(val.email) + " )"),
                            $("<span>").addClass("pull-right").html(val.datecreated.year + "-" + val.datecreated.month + "-" + val.datecreated.dayOfMonth)
                        ]);
                        var body = $("<div>").addClass("clearfix").html(val.post);
                        var body1 = $("<div>").addClass("row");
                        body1.append([
                            $("<div>").addClass("col-md-6").append([
                                $("<h5>").html("From Location"),
                                $("<div>").html("City : " + val.fromCity),
                                $("<div>").html("State : " + val.fromState),
                                $("<div>").html("Zip : " + val.fromZip)
                            ]),
                            $("<div>").addClass("col-md-6").append([
                                $("<h5>").html("To Location"),
                                $("<div>").html("City : " + val.toCity),
                                $("<div>").html("State : " + val.toState),
                                $("<div>").html("Zip : " + val.toZip)
                            ])
                        ]);
                        div.append([title, body, body1]);
                        $("#bodyOfHome").append(div);
                    }
                }
            });
        });
    }
});
