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
        $.each(data, function (key, val) {
            $.ajax({
                url: "/js/getEmailAndNameFromId/" + val.userId,
                type: "get",
                success: function (data1) {
                    if (data1) {
                        var div = $("<div>").addClass("posts col-md-6").attr("id",val.postId);
                        var title = $("<div>").addClass("title row").append([
                            $("<span>").addClass("postBy col-sm-6").html(data1.fullName + " ( <small>" +data1.email+"</small> )"),
                            $("<span>").addClass("col-sm-6").html(val.datecreated.year + "-" + val.datecreated.month + "-" + val.datecreated.dayOfMonth)
                        ]);
                        var body = $("<div>").addClass("clearfix").html(val.post);
                        var body1 = $("<div>").addClass("row locations");
                        body1.append([
                            $("<div>").addClass("col-md-6 from").append([
                                $("<div>").addClass("title").html("From Location"),
                                $("<div>").html("City : " + val.fromCity),
                                $("<div>").html("State : " + val.fromState),
                                $("<div>").html("Zip : " + val.fromZip)
                            ]),
                            $("<div>").addClass("col-md-6 to").append([
                                $("<div>").addClass("title").html("To Location"),
                                $("<div>").html("City : " + val.toCity),
                                $("<div>").html("State : " + val.toState),
                                $("<div>").html("Zip : " + val.toZip)
                            ])
                        ]);
                        var likeCount = 0;
                        var allComments = $("<div>").addClass("allComments");
                        $.ajax({
                            url:"/js/getLnC/" + val.postId,
                            type:"get",
                            async:false,
                            success:function (d) {
                                likeCount = d.likes;
                                $.each(d.comments,function(k1,v1){
                                    $.ajax({
                                        url: "/js/getEmailAndNameFromId/" + v1.userId,
                                        type: "get",
                                        success: function (d3) {
                                            var ddd = $("<div>").addClass("individualComment").attr("id",v1.commentId);
                                            var title1 = $("<div>").addClass("title row").append([
                                                $("<span>").addClass("postBy col-sm-6").html(d3.fullName + " ( <small>" +d3.email+"</small> )"),
                                                $("<span>").addClass("col-sm-6").html(v1.dateCreated.year + "-" + v1.dateCreated.month + "-" + v1.dateCreated.dayOfMonth)
                                            ]);
                                            var body1 = $("<div>").addClass("clearfix").html(v1.comment);
                                            ddd.append([title1,body1]);
                                            allComments.append(ddd);
                                        }
                                    });
                                });
                            }
                        });

                        var comments = $("<div>").addClass("row commentBox");
                        var commentBox = $("<div>").addClass("col-sm-10").append($("<textarea>").addClass("postComment form-control").attr("placeholder","Write Comment"));
                        var button = $("<div>").addClass("col-sm-2").append([
                            $("<button>").addClass("btn btn-default btn-sm likeComment").html("Like ("+likeCount+")"),
                            $("<br>"),
                            $("<button>").addClass("btn btn-default btn-sm submitComment").html("Post")
                        ]);
                        comments.append([commentBox,button]);
                        div.append([title, body, body1,allComments,comments]);
                        $("#bodyOfHome").append(div);
                        $(".posts:even").css("border-right","3px solid darkblue");
                        $(".submitComment").click(submitComment);
                    }
                }
            });
        });
    }

    function submitComment(evt){
        var self = $(this);
        var postId = self.closest(".posts").attr("id");
        var comment = self.closest(".posts").find(".postComment ").val();
        if(comment === ""){
            self.closest(".posts").find(".postComment ").focus();
            return false;
        } else {
            $.ajax({
                url:"/js/addComment",
                data:{
                    postId : postId,
                    comment : comment
                },
                type:"post",
                success:function(data){
                    if(data){
                        var ddd = $("<div>").addClass("individualComment").attr("id",data.comment.commentId);
                        var title = $("<div>").addClass("title row").append([
                            $("<span>").addClass("postBy col-sm-6").html(data.user.fullName + " ( <small>" +data.user.email+"</small> )"),
                            $("<span>").addClass("col-sm-6").html(data.comment.dateCreated.year + "-" + data.comment.dateCreated.month + "-" + data.comment.dateCreated.dayOfMonth)
                        ]);
                        var body = $("<div>").addClass("clearfix").html(data.comment.comment);
                        ddd.append([title,body]);
                        self.closest(".posts").find(".allComments").prepend(ddd);
                        self.closest(".posts").find(".postComment ").val("");
                    }else{
                        alert("error adding comment");
                    }
                }
            })
        }
    }
});
