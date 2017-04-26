/**
 * Created by ashok on 4/24/2017.
 */

$(function () {
    $("#offerRide").click(function () {
        $(this).css("background-color", "#92acf8");
        $("#askForRide").css("background-color", "#b0dcea");
        $("#postType").val("share");
        $("#pageCount").val("0");
        loadPosts("share", "Ride Offers");
    });

    $("#askForRide").click(function () {
        $(this).attr("active","1").css("background-color", "#92acf8");
        $("#offerRide").css("background-color", "#b0dcea");
        $("#postType").val("pool");
        $("#pageCount").val("0");
        loadPosts("pool", "Ride Requests");
    });

    loadPosts("share", "Ride Offers");

    function loadPosts(type, title) {
        $(".loading").show();
        var heading = $("<h2>").addClass("title").html(title);
        var body = $("#bodyOfHome");
        body.html(heading);
        var url = "/js/postType/" + type + "/0/25";
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
                        var div = $("<div>").addClass("posts col-md-6").attr("id", val.postId);
                        var title = $("<div>").addClass("title row").append([
                            $("<span>").addClass("postBy col-sm-6").html(data1.fullName + " ( <small>" + data1.email + "</small> )"),
                            $("<span>").addClass("col-sm-6").html(val.datecreated.year + "-" + val.datecreated.month + "-" + val.datecreated.dayOfMonth + " "+ val.datecreated.hour+":"+val.datecreated.minute)
                        ]);
                        var body = $("<div>").addClass("clearfix").html(val.post);
                        var body1 = $("<div>").addClass("row locations");
                        body1.append([
                            $("<div>").addClass("col-md-5 from").append([
                                $("<div>").addClass("title").html("From Location"),
                                $("<div>").html("City : " + val.fromCity),
                                $("<div>").html("State : " + val.fromState),
                                $("<div>").html("Zip : " + val.fromZip)
                            ]),
                            $("<div>").addClass("col-md-5 to").append([
                                $("<div>").addClass("title").html("To Location"),
                                $("<div>").html("City : " + val.toCity),
                                $("<div>").html("State : " + val.toState),
                                $("<div>").html("Zip : " + val.toZip)
                            ]),
                            $("<div>").addClass("col-md-2").append([
                                $("<button>").addClass("btn btn-xs btn-default").html("Weather").click({from: val.fromZip, to: val.toZip},showWeatherMap)
                            ])
                        ]);
                        var likeCount = 0;
                        var allComments = $("<div>").addClass("allComments");
                        $.ajax({
                            url: "/js/getLnC/" + val.postId,
                            type: "get",
                            async: false,
                            success: function (d) {
                                likeCount = d.likes;
                                $.each(d.comments, function (k1, v1) {
                                    $.ajax({
                                        url: "/js/getEmailAndNameFromId/" + v1.userId,
                                        type: "get",
                                        success: function (d3) {
                                            var ddd = $("<div>").addClass("individualComment").attr("id", v1.commentId);
                                            var title1 = $("<div>").addClass("title row").append([
                                                $("<span>").addClass("postBy col-sm-6").html(d3.fullName + " ( <small>" + d3.email + "</small> )"),
                                                $("<span>").addClass("col-sm-6").html(v1.dateCreated.year + "-" + v1.dateCreated.month + "-" + v1.dateCreated.dayOfMonth)
                                            ]);
                                            var body1 = $("<div>").addClass("clearfix c1").html(v1.comment);
                                            ddd.append([title1, body1]);
                                            allComments.append(ddd);
                                        }
                                    });
                                });
                            }
                        });

                        var comments = $("<div>").addClass("row commentBox");
                        var commentBox = $("<div>").addClass("col-sm-10").append($("<textarea>").addClass("postComment form-control").attr("placeholder", "Write Comment"));
                        var button = $("<div>").addClass("col-sm-2").append([
                            $("<button>").addClass("btn btn-default btn-sm likeComment").html("Like (" + likeCount + ")").click(saveLike1),
                            $("<br>"),
                            $("<button>").addClass("btn btn-default btn-sm submitComment").html("Post").click(submitComment1)
                        ]);
                        comments.append([commentBox, button]);
                        div.append([title, body, body1, allComments, comments]);
                        $("#bodyOfHome").append(div);
                        $(".posts:even").css("border-right", "3px solid darkblue");
                        /*$(".submitComment").on("click", submitComment1);
                        $(".likeComment").on("click", saveLike1);*/
                    }
                }
            });
        });
    }

    function submitComment1(evt) {
        evt.preventDefault();
        var self = $(this);
        self.attr("disabled", true);
        var postId = self.closest(".posts").attr("id");
        var comment = self.closest(".posts").find(".postComment ").val();
        if (comment === "") {
            self.closest(".posts").find(".postComment ").focus();
            self.attr("disabled", false);
            return false;
        } else {
            $.ajax({
                url: "/js/addComment",
                async: false,
                data: {
                    postId: postId,
                    comment: comment
                },
                type: "post",
                success: function (data) {
                    if (data) {
                        var ddd = $("<div>").addClass("individualComment").attr("id", data.comment.commentId);
                        var title = $("<div>").addClass("title row").append([
                            $("<span>").addClass("postBy col-sm-6").html(data.user.fullName + " ( <small>" + data.user.email + "</small> )"),
                            $("<span>").addClass("col-sm-6").html(data.comment.dateCreated.year + "-" + data.comment.dateCreated.month + "-" + data.comment.dateCreated.dayOfMonth)
                        ]);
                        var body = $("<div>").addClass("clearfix c1").html(data.comment.comment);
                        ddd.append([title, body]);
                        self.closest(".posts").find(".allComments").prepend(ddd);
                        self.closest(".posts").find(".postComment ").val("");
                    } else {
                        alert("error adding comment");
                    }
                },
                complete: function () {
                    self.attr("disabled", false);
                }
            })
        }
    }

    function saveLike1(evt) {
        var self = $(this);
        evt.preventDefault();
        self.attr("disabled", true);
        var postId = self.closest(".posts").attr("id");
        $.post("/js/addLike", {postId: postId}).done(function (data) {
            if (data) {
                if (data.errorCode) {
                    alert("You have already liked");
                } else {
                    self.html("Like (" + data.likeCount + ")");
                }
            } else {
                alert("error adding comment");
            }
        }).always(function () {
            self.attr("disabled", false);
        });
    }



    // infinite scroll
    function element_in_scroll(elem)
    {
        var docViewTop = $(window).scrollTop();
        var docViewBottom = docViewTop + $(window).height();

        var elemTop = $(elem).offset().top;
        var elemBottom = elemTop + $(elem).height();

        return ((elemBottom <= docViewBottom) && (elemTop >= docViewTop));
    }

    $(document).scroll(function(e){
        if (element_in_scroll(".posts:last")) {
            $(window).off('scroll');
            var pageCount = parseInt($("#pageCount").val()) + parseInt(1);
            $("#pageCount").val(pageCount);
            var postType = $("#postType").val();
            $.ajax({
                type: "get",
                url: "/js/postType/" + postType + "/"+pageCount+"/25",
                success: function (data) {
                    getUserAndFillData(data);
                    $(window).on('scroll');
                }
            });
        }
    });

    function showWeatherMap(event) {
        var fromZip = event.data.from;
        var toZip = event.data.to;
    }

});
