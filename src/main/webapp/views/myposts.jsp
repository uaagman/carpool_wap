<%@include file="inc/header.jsp"%>
<title>Posts - Carpool</title>
<link href="/resources/css/home.css" rel="stylesheet"/>
<link href="/resources/css/myposts.css" rel="stylesheet"/>
</head><body>
<%@include file="inc/nav.jsp"%>
<div class="container">

    <div class="row">
        <h1>My Posts</h1>
    </div>

    <div id="newPost">
        <a href="/newpost">Make new post</a>
    </div>

    <div class="container">
        <input type="hidden" id="pageCount" value="0"/>
        <input type="hidden" id="postType" value="share"/>
        <div class="row">
            <a class="col-xs-6" id="offerRideMy" href="javascript:;">Offering a ride</a>
            <a class="col-xs-6 col-sm-6" id="askForRideMy" href="javascript:;">Asking for a ride</a>
        </div>
        <div class="row">
            <div id="bodyOfHome">
            </div>
            <div class="loading"><img src="/resources/images/loader4.gif"/></div>
        </div>
    </div>
</div>
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Weather Map</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="modalMap col-sm-6 fromMap">
                        <h5>From Location </h5>
                        <div id="fromMap"></div>
                    </div>
                    <div class="modalMap col-sm-6 toMap">
                        <h5>To Location </h5>
                        <div id="toMap"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="inc/footer.jsp"%>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCg4hATJz-Rl6ZzaTs3bnSVbnXE_MZolzg"></script>
<script src="/resources/js/map.js" rel="script" type="text/javascript"></script>
<script src="/resources/js/myposts.js" rel="script" type="text/javascript"></script>
</body>
</html>