<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<%@page import="java.text.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
          integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
    <title>Document</title>
</head>


<style>
    .search{
        display: flex;
        justify-content: space-between;
        align-items: center;
        width: 40%;
        height: 50px;
        background-color: #fff;
        border-radius: 5px;
        box-shadow: 0px 0px 10px 0px rgba(0,0,0,0.1);
        margin: 10px 10px 10px 0px;
        left: 30%;
        position: absolute;
        top: 0%;
    }

    .search input{
        height: 100%;
        width: 100%;
        border: none;
        outline: none;
        margin-left: 10px;
        font-size: 15px;
        border-radius: 10px;
    }

    .search button{
        height: 100%;
        width: 100px;
        border: none;
        outline: none;
        background-color: aqua;
        border-radius: 5px;
        cursor: pointer;
    }
</style>

<body>

<section class="wrapper">
    <div class="container-fostrap">
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">
                    <img th:src="@{/images/logo.png}" src="../static/images/logo.png" width="auto" height="40" class="d-inline-block align-top" alt=""/>
                </a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav mr-auto">
                        <!-- ... (your existing code) ... -->
                    </ul>
                    <ul class="navbar-nav ml-auto">
                        <!-- Move SortBy dropdown to the right -->
                        <li class="nav-item dropdown order-1 order-lg-0">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                SortBy
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <a class="dropdown-item" href="/user/products?sort=price&order=desc">Price (Desc)</a>
                                <a class="dropdown-item" href="/user/products?sort=name">Name</a>
                                <div class="dropdown-divider"></div>
                            </div>
                        </li>
                        <!-- End of SortBy dropdown -->
                        <li class="nav-item active">
                            <a class="nav-link" th:href="@{/}" href="/carts">CART</a>
                        </li>
                        <li class="nav-item active">
                            <a class="nav-link" href="profileDisplay" >Profile</a>
                        </li>
                        <li class="nav-item active">
                            <a class="nav-link" sec:authorize="isAuthenticated()" href="/">Logout</a>
                        </li>
                        <!-- Update the href attribute to match the correct URL -->
                        <li class="nav-item active">
                            <a class="nav-link" href="/about">About Us</a>
                        </li>

                    </ul>
                </div>
            </div>


            <form action="/search" method="get" onsubmit="return validateSearch()">
                <div class="search">
                    <input type="text" name="category" id="searchInput" placeholder="Search" />
                    <button type="submit">Search</button>
                </div>
            </form>


        </nav>

        <div class="row">
            <c:forEach var="product" items="${products}">
                <div class="col-md-3">
                    <div class="card mb-4">
                        <img class="card-img-top" src="${product.image}" alt="Product 1">
                        <div class="card-body">
                            <b><h4 class="card-title">${product.name}</h4></b>
                            <h5 class="card-text">Category: ${product.category.name}</h5>
                            <h5 class="card-text">Price: ${product.price}</h5>
                            <p class="card-text">Description: ${product.description}</p>
                            <a href="#" class="btn btn-primary">Add to Cart</a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</section>

<!-- Include Bootstrap and jQuery scripts explicitly -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>

<script>

    function validateSearch() {
        var searchInput = document.getElementById('searchInput').value.trim();

        if (searchInput === '') {
            alert('Please enter a search term');
            return false;
        }
        return true;
    }

    // Function to reload the page with the selected sorting option
    // Function to reload the page with the selected sorting option
    function sortBy(value) {
        var currentUrl = window.location.href;
        var separator = currentUrl.indexOf('?') !== -1 ? '&' : '?';
        var newUrl = currentUrl + separator + 'sort=' + value;
        console.log(newUrl); // Log the new URL to the console
        window.location.href = newUrl;
    }


    // Attach an event listener to the dropdown items
    document.addEventListener('DOMContentLoaded', function () {
        const dropdownItems = document.querySelectorAll('.dropdown-item');
        dropdownItems.forEach(item => {
            item.addEventListener('click', function () {
                const sortByValue = item.innerText.toLowerCase(); // Assuming the value is lowercase
                sortBy(sortByValue);
            });
        });
    });
</script>

</body>
</html>
