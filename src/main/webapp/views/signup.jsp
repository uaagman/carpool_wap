<%@include file="inc/header.jsp" %>
<%--@elvariable id="user" type="com.carpool.domain.User"--%>
<%--@elvariable id="error" type="com.carpool.exception.ErrorMessage"--%>
<title>Signup - Carpool</title>
<link href="/resources/css/login.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<c:if test="${loggedUser ne null}">
    <%@include file="inc/nav.jsp" %>
</c:if>
<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <form method="post" action="/signup" class="signup">
                <div class="titleH">
                    <c:choose>
                        <c:when test="${loggedUser ne null}">
                            Update Profile
                        </c:when>
                        <c:otherwise>
                            Sign Up
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="error">${errorMsg}</div>
                <div class="error">
                    <c:forEach items="${errors}" var="error">
                        ${error.message}<br/>
                       <%-- <ce:showMessage color="red" text="${error.message}"/>--%>
                    </c:forEach>
                </div>
                <div class="form-group">
                    <label for="fullName">Full Name</label>
                    <input type="text" name="fullName" id="fullName" value="${user.fullName}" required="required"
                           class="form-control"/>
                    <input type="hidden" name="userId" id="userId" value="${user.userId}"/>
                </div>
                <div class="form-group">
                    <label for="gender">Gender</label>
                    <select name="gender" id="gender" required="required" class="form-control">
                        <option value="" disabled="disabled" selected="selected">--Select One--</option>
                        <option value="male"
                                <c:if test="${user.gender eq 'male'}">selected</c:if> >Male
                        </option>
                        <option value="female"
                                <c:if test="${user.gender eq 'female'}">selected</c:if> >Female
                        </option>
                        <option value="others"
                                <c:if test="${user.gender eq 'others'}">selected</c:if> >Others
                        </option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="state">State</label>
                    <input type="text" name="state" id="state" value="${user.state}" required="required"
                           class="form-control"/>
                </div>
                <div class="form-group">
                    <label for="city">City</label>
                    <input type="text" name="city" id="city" value="${user.city}" required="required"
                           class="form-control"/>
                </div>
                <div class="form-group">
                    <label for="street">Stret</label>
                    <input type="text" name="street" id="street" value="${user.street}" required="required"
                           class="form-control"/>
                </div>
                <div class="form-group">
                    <label for="zipCode">Zip code</label>
                    <input type="number" name="zipCode" id="zipCode" value="${user.zipCode}" required="required"
                           class="form-control"/>
                </div>
                <div class="form-group">
                    <label for="birthYear">Birth Year</label>
                    <input type="number" name="birthYear" id="birthYear" value="${user.birthYear}" required="required"
                           class="form-control"/>
                </div>
                <hr/>
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" name="email" id="email" value="${user.email}" required="required"
                           class="form-control"/>
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" name="password" id="password" required="required" class="form-control"/>
                </div>
                <div class="form-group">
                    <label for="rePassword">Reenter Password</label>
                    <input type="password" name="rePassword" id="rePassword" required="required" class="form-control"/>
                </div>
                <hr/>
                <a class="btn btn-default" href="<c:url value="/login"/>">Back</a>
                <button class="btn btn-primary pull-right" type="submit"><c:choose>
                    <c:when test="${loggedUser ne null}">
                        Update Profile
                    </c:when>
                    <c:otherwise>
                        Sign Up
                    </c:otherwise>
                </c:choose></button>
            </form>

        </div>
    </div>
</div>

<%@include file="inc/footer.jsp" %>
<script src="resources/js/login.js" rel="script" type="text/javascript"></script>
</body>
</html>