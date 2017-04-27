<%@include file="inc/header.jsp"%>
<title>Posts - Carpool</title>
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

<%@include file="inc/footer.jsp"%>
<script src="/resources/js/script.js"></script>
<script src="/resources/js/myposts.js" rel="script" type="text/javascript"></script>
</body>
</html>