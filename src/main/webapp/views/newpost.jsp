<%@include file="inc/header.jsp" %>
<title>Login - Carpool</title>
<link href="/resources/css/login.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<%@include file="inc/nav.jsp" %>
<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <form method="post" action="/posts/newpost" class="signup">

                <div class="form-group">
                    <label for="postType">Postype</label>
                    <select name="postType" id="postType" required="required" class="form-control">
                        <option value="" disabled="disabled" selected="selected">--Select One--</option>
                        <option value="pool">Car Pool</option>
                        <option value="share">Car Share</option>
                    </select>

                </div>

                <div class="form-group">
                    <label for="dueDate">Date</label>
                    <input type="number" name="dueDate" id="dueDate" required="required" class="form-control"/>
                </div>
                <hr>

                <div id="fromAddress">
                    <div class="form-group">
                        <label for="fromState">State</label>
                        <input type="text" name="fromState" id="fromState" required="required" class="form-control"/>
                    </div>
                    <div class="form-group">
                        <label for="fromCity">City</label>
                        <input type="text" name="fromCity" id="fromCity" required="required" class="form-control"/>
                    </div>

                    <div class="form-group">
                        <label for="fromZip">Zip</label>
                        <input type="text" name="fromZip" id="fromZip" required="required" class="form-control"/>
                    </div>
                </div>
                <hr>
                <div id="toAddress">
                    <div class="form-group">
                        <label for="toState">State</label>
                        <input type="text" name="toState" id="toState" required="required" class="form-control"/>
                    </div>
                    <div class="form-group">
                        <label for="toCity">City</label>
                        <input type="text" name="toCity" id="toCity" required="required" class="form-control"/>
                    </div>

                    <div class="form-group">
                        <label for="toZip">Zip</label>
                        <input type="text" name="toZip" id="toZip" required="required" class="form-control"/>
                    </div>
                </div>
                <div class="form-group">
                    <textarea name="post" id="post" required="required" class="form-control" rows="10" cols="10"
                              placeholder="Please enter your post here">
                    </textarea>
                </div>

                <hr/>
                <a class="btn btn-default" href="<c:url value="/posts"/>">Back</a>
                <button class="btn btn-primary pull-right" type="submit">Post it</button>
            </form>

        </div>
    </div>
</div>

<script src="resources/js/login.js"></script>
<%@include file="inc/footer.jsp" %>