<%@include file="inc/header.jsp" %>
<title>Maps - Carpool</title>
<link href="/resources/css/map.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<c:if test="${loggedUser ne null}">
    <%@include file="inc/nav.jsp" %>
</c:if>
<div class="container-fluid">
        <div id="map">

        </div>
</div>

<%@include file="inc/footer.jsp" %>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCg4hATJz-Rl6ZzaTs3bnSVbnXE_MZolzg"></script>
<script src="/resources/js/map.js" rel="script" type="text/javascript"></script>
<script>
    $(function () {
        initMap('52557','map');
    });
</script>
</body>
</html>