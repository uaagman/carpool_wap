<%@include file="inc/header.jsp"%>
<title>404 - Carpool</title>
<link rel="stylesheet" type="text/css" href="/resources/css/error.css"/>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div class="error-template">
                <h1>Oops!</h1>
                <h2>404 Not Found</h2>
                <div>
                    Sorry! The requested page not found!
                </div><hr/>
                <div>
                    <a href="<c:url value="/home"/> " class="btn btn-primary">Take Me Home</a>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="inc/footer.jsp" %>
</body>
</html>