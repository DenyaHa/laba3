<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>Database webapp</title>

    <link href="css/index.css" type="text/css" rel="stylesheet">
    <link rel="stylesheet" href="libs/bootstrap-4.4.1/css/bootstrap.min.css">
</head>

<body>
<div class="container main-container">
    <div class="row">
        <div class="col-md-8 d-flex align-items-center justify-content-center">
            <h1 class="text-center my-5">Log In</h1>
        </div>
        <div class="col-md-4 pt-5">
            <div id="login-container">
                <form class="d-table-cell" id="login-form" action="/records" method="post">
                    <div class="form-group px-2">
                        <label class="control-label text-center" for="login">Name:</label>
                        <input type="text" class="form-control" id="login" placeholder="mr. Owe" name="login">
                    </div>
                    <div class="form-group px-2">
                        <label class="control-label text-center" for="password">Password:</label>
                        <input type="password" class="form-control" id="password" placeholder="password"
                               name="password">
                    </div>
                    <div class="form-group">
                        <div class="">
                            <button type="submit" class="btn btn-primary log-in-btn d-block m-auto border"
                                    value="Submit">Submit</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script src="libs/jquery-3.4.1/jquery-3.4.1.min.js"></script>
<script src="libs/bootstrap-4.4.1/js/bootstrap.min.js"></script>
</body>
</html>
