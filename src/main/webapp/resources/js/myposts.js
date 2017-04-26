/**
 * Created by ashok on 4/24/2017.
 */
window.counter = 0;
window.bottomflag=0;
$(function () {
    var loggedUser=$("#loggedUser")[0].innerText;
    if(!loggedUser)
        return;
    $("#offerRideMy").click(function () {
        $(this).css("background-color","#92acf8");
        $("#askForRideMy").css("background-color","#b0dcea");
        loadPosts("share",loggedUser,"Ride Offers");
    });

    $("#askForRideMy").click(function () {
        $(this).css("background-color","#92acf8");
        $("#offerRideMy").css("background-color","#b0dcea");
        loadPosts("pool",loggedUser,"Ride Requests");
    });

    loadPosts("share",loggedUser,"Ride Offers");



    $(document).scroll(function(e){

        if ($(window).scrollTop() >= ($(document).height() - $(window).height())){
            // processing = true; //sets a processing AJAX request flag
            // $.post("url", '<params>', function(data){ //or $.ajax, $.get, $.load etc.
                window.counter=1;

                if(window.bottomflag!==1){
                    window.bottomflag=1;
                 $("#offerRideMy").click();

                }
            //     processing = false; //resets the ajax flag once the callback concludes
            // });
//            alert("At bottom");

        }
    });
});

function loadPosts(type,loggedUser,title) {
    $(".loading").show();
    if(!loggedUser)
        return;
    var heading = $("<h2>").addClass("title").html(title);
    var body = $("#bodyOfHome");
    if(window.bottomflag!==1){
    body.html(heading);
    }
    //var url = "http://localhost:8080/posts/users/" + loggedUser+"/posts/"+type;
    var url = "http://localhost:8080/posts/usertyperange/"+loggedUser+"/postTypeRange/"+type+"/fromrange/"+window.counter+"/torange/25";
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
                            "class": "deletePost",
                            "postID": val.postId
                        })
                            .html("Delete").addClass("deleteBtn")
                    ]);

                divtag.append(
                    [
                        $("<button>", {
                            "class": "showComments",
                            "postID": val.postId
                        })
                            .html("Show comments").addClass("showCommentBtn")
                    ]);
                body.append(divtag);
            })
            $(".deletePost").click(deletePostFunction);
        },
        error:function (xhr,status,exception) {
            var error = $("<h5>").addClass("error").html("Error Loading Posts");
            body.append(error);
        },
        complete:function () {
            $(".loading").hide();
        }
    });

    function deletePostFunction(ev){
        var postID = $(this).attr('postID');
        var divAttach=$(this).closest("div");
       if(!postID)
            return;
        var url = "http://localhost:8080/posts/" + postID;
        $.ajax({
            url: url,
            type:"delete",
            // data:{
            //     "postType":type
            // },
            success:function (data) {
                //alert("Delete successfull");
                location.reload();
            },
            error:function (xhr,status,exception) {
                var error = $("<h5>").addClass("error").html("Error Deleting Posts");
                body.append(error);
            },
            complete:function () {
                $(".loading").hide();
            }
        });
        $("loading").show();
    }
}
