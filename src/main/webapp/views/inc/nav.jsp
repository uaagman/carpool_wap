<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed"
                    data-toggle="collapse" data-target="#navbar" aria-expanded="false"
                    aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span> <span
                    class="icon-bar"></span> <span class="icon-bar"></span> <span
                    class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">carpool</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">

            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="<c:url value="/"/> ">Home</a></li>
                <li><a href="<c:url value="/posts/myposts"/>">My Posts</a></li>
                <li><a href="<c:url value="/weather"/>">WeatherService</a></li>
                <c:if test="${LoggedUser ne null}">

                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${loggedUser}<span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><p>${LoggedUser}</p></li>
                            <li><a href="<c:url value="/profile"/>">Profile</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="<c:url value="/logout"/>">Logout</a></li>
                        </ul>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>