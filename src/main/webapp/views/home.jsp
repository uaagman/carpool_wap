<%@include file="inc/header.jsp" %>
<title>Home - Carpool</title>
<link href="/resources/css/home.css" rel="stylesheet"/>
</head><body>
<%@include file="inc/nav.jsp" %>
<msg:showMessage text="test" color="red"/>
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


<%@include file="inc/footer.jsp" %>
<script src="/resources/js/script.js"></script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCg4hATJz-Rl6ZzaTs3bnSVbnXE_MZolzg"></script>
<script src="/resources/js/map.js" rel="script" type="text/javascript"></script>
<script src="/resources/js/home.js" rel="script" type="text/javascript"></script>
</body>
</html>