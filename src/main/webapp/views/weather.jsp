<%@include file="inc/header.jsp" %>
<title>Weather - Carpool</title>
<link href="/resources/css/weather.css" rel="stylesheet"/>
</head><body>
<%@include file="inc/nav.jsp" %>

<div class="container">
    <div class="row">
        <h3 id="weatherHeader" class="col-sm-8">Weather of 5 days / 3 hours display</h3>
        <div class="col-sm-4">
            <div class="input-group">
                <input type="text" id="inputZip" class="form-control" placeholder="Search by Zip code">
                <span class="input-group-btn">
                    <button class="btn btn-secondary" id="searchZip" type="button">Go!</button>
                </span>
            </div>
        </div>
    </div>
    <div class="dataBody clearfix"></div>
</div>


<%@include file="inc/footer.jsp" %>
<script src="/resources/js/weather.js" rel="script" type="text/javascript"></script>
<%--<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?client=​6c974d3d4c6fd517225aff5aec044a85"></script>--%>
</body>
</html>



