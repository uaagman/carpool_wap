<%@include file="inc/header.jsp" %>
<title>Home - Carpool</title>
<link href="/resources/css/home.css" rel="stylesheet"/>
</head><body>
<%@include file="inc/nav.jsp" %>

<div class="container">
    <input type="hidden" id="pageCount" value="0"/>
    <input type="hidden" id="postType" value="share"/>
    <div class="row">
        <a class="col-xs-6" id="offerRide" href="javascript:;">Offering a ride</a>
        <a class="col-xs-6 col-sm-6" id="askForRide" href="javascript:;">Asking for a ride</a>
    </div>
    <div class="row">
        <div id="bodyOfHome">
        </div>
        <div class="loading"><img src="/resources/images/loader4.gif"/></div>
    </div>
</div>


<%@include file="inc/footer.jsp" %>
<script src="/resources/js/home.js" rel="script" type="text/javascript"></script>
</body>
</html>