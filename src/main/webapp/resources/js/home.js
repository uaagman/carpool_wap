/**
 * Created by ashok on 4/24/2017.
 */

$(function () {
    $("#offerRide").click(function () {
        $(this).css("background-color","#92acf8");
        $("#askForRide").css("background-color","#b0dcea");
        loadPosts("share","Ride Offers");
    });

    $("#askForRide").click(function () {
        $(this).css("background-color","#92acf8");
        $("#offerRide").css("background-color","#b0dcea");
        loadPosts("pool","Ride Requests");
    });

    loadPosts("share","Ride Offers");
});

function loadPosts(type,title) {
    $(".loading").show();
    var heading = $("<h2>").addClass("title").html(title);
    var body = $("#bodyOfHome");
    body.html(heading);
    var url = "http://localhost:8080/posts/postType/" + type;
    $.ajax({
        url: url,
        type:"get",
        // data:{
        //     "postType":type
        // },
        success:function (data) {
            $.each(data,function (key,val) {
                // var div = $("<div>").addClass("posts").html("");
                var divtag = $("<div>")
                    .css({
                        'border': '2px solid black',
                        'margin-bottom': "5px",
                        'overflow': 'auto'

                    });

                divtag.append(
                    [
                        $('<h3>')
                            .html(val.post),
                        $('<span>').html(
                            val.body
                        ),
                        $('<br>'),
                        $("<button>", {
                            "class": "showComments",
                            "postID": val.postId
                        })
                            .html("Show comments").addClass("showCommentBtn")
                    ]);
                body.append(divtag);
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
