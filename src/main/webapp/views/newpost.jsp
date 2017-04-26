<%@include file="inc/header.jsp" %>
<title>New Post - Carpool</title>
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
                    <label for="dueDate">Due Date/Time</label>
                                    <div class='input-group date' id='datetimepicker1'>
                                        <input type='text' name="dueDate" id="dueDate" required class="form-control" />
                                        <span class="input-group-addon">
                                        <span class="glyphicon glyphicon-calendar"></span>
                                    </span>
                                    </div>


                </div>



                <fieldset>
                    <legend>From:</legend>
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
                </fieldset>

                <div class="form-group">
                    <textarea name="post" id="post" required="required" rows="10" cols="70"
                              placeholder="Please enter your post here"></textarea>
                </div>

                <a class="btn btn-default" href="<c:url value="/posts"/>">Back</a>
                <button class="btn btn-primary pull-right" type="submit">Post it</button>
            </form>

        </div>
    </div>
</div>

<%@include file="inc/footer.jsp" %>
<script src="resources/js/dateTimePicker.js" rel="script" type="text/javascript"></script>
</body>
</html>