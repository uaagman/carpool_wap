<%@include file="inc/header.jsp"%>
<title>Login - Carpool</title>
<link href="/resources/css/login.css" rel="stylesheet" type="text/css"/>
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <form method="post" action="/login" class="login">
                <div class="titleH">Login</div>
                <div class="error">${errorMsg}</div>
                <div class="form-group">
                    <label for="username">Username</label>
                    <input type="text" name="username" id="username" required="required" class="form-control"/>
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" name="password" id="password" required="required" class="form-control"/>
                </div>
                <div class="checkbox">
                    <label for="remember"><input type="checkbox" name="remember" id="remember"/>Remember me</label>
                </div>
                <button class="btn btn-primary pull-right" type="submit">Login</button>
                <div class="clearfix">
                    <hr/>
                    New To Carpool? <a href="/signup">Sign Up</a> Now
                </div>
            </form>

        </div>
    </div>
</div>

<%@include file="inc/footer.jsp" %>
<script src="resources/js/login.js" rel="script" type="text/javascript"></script>
</body>
</html>