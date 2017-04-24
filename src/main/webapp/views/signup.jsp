<%@include file="inc/header.jsp" %>
<title>Login - Carpool</title>
<link href="/resources/css/login.css" rel="stylesheet" type="text/css"/>
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <form method="post" action="/signup" class="signup">

                <div class="form-group">
                    <label for="fullName">Full Name</label>
                    <input type="text" name="fullName" id="fullName" required="required" class="form-control"/>
                </div>
                <div class="form-group">
                    <label for="gender">Genger</label>
                    <select name="gender" id="gender" required="required" class="form-control">
                        <option value="" disabled="disabled" selected="selected">--Select One--</option>
                        <option value="male">Male</option>
                        <option value="female">Female</option>
                        <option value="others">Others</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="state">State</label>
                    <input type="text" name="state" id="state" required="required" class="form-control"/>
                </div>
                <div class="form-group">
                    <label for="city">City</label>
                    <input type="text" name="city" id="city" required="required" class="form-control"/>
                </div>
                <div class="form-group">
                    <label for="street">Stret</label>
                    <input type="text" name="street" id="street" required="required" class="form-control"/>
                </div>
                <div class="form-group">
                    <label for="zipCode">Zip code</label>
                    <input type="text" name="zipCode" id="zipCode" required="required" class="form-control"/>
                </div>
                <div class="form-group">
                    <label for="birthYear">Birth Year</label>
                    <input type="number" name="birthYear" id="birthYear" required="required" class="form-control"/>
                </div>
                <hr/>
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" name="email" id="email" required="required" class="form-control"/>
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" name="password" id="password" required="required" class="form-control"/>
                </div>
                <div class="form-group">
                    <label for="rePassword">Reenter Password</label>
                    <input type="password" name="rePasword" id="rePassword" required="required" class="form-control"/>
                </div>
                <hr/>
                <a class="btn btn-default" href="<c:url value="/login"/>">Back</a>
                <button class="btn btn-primary pull-right" type="submit">Signup</button>
            </form>

        </div>
    </div>
</div>

<script src="resources/js/login.js"></script>
<%@include file="inc/footer.jsp" %>