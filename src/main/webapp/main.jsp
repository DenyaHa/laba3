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
    <a class="btn btn-danger mt-4 mb-2 mx-auto d-block" href="/logout">Logout</a>
    <form id="search-form" class="" action="">
        <div class="input-group mb-5">
            <input type="text" class="form-control" placeholder="Search" id="search-input">
            <div class="input-group-append">
                <button class="btn btn-success" type="submit">Go</button>
            </div>
        </div>
    </form>
    <div id="result" class="border p-1 mb-5 mt-5">
    </div>

    <form id="add-form" class="form-inline company-form" action="">
        <label for="cname-a" class="mr-sm-2">Company:</label>
        <input value="C" type="text" class="form-control mb-2 mr-sm-2" placeholder="Enter company name" id="cname-a">
        <label for="mail-a" class="mr-sm-2">Email:</label>
        <input value="M@m.m" type="email" class="form-control mb-2 mr-sm-2" placeholder="Enter email" id="mail-a">
        <label for="url-a" class="mr-sm-2">Url:</label>
        <input value="U" type="text" class="form-control mb-2 mr-sm-2" placeholder="Enter url" id="url-a">
        <button type="submit" id="add-btn" class="btn btn-danger mb-2">Add</button>
    </form>
    <button type="submit" id="clear-btn" class="btn btn-danger mt-4 mb-2 mx-auto d-block">Clear</button>
</div>

<script src="libs/jquery-3.4.1/jquery-3.4.1.min.js"></script>
<script src="libs/bootstrap-4.4.1/js/bootstrap.min.js"></script>
<script src="js/script.js"></script>
</body>

</html>