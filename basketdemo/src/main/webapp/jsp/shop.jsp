<%--suppress JSUnresolvedVariable --%>
<%--suppress JSUnusedLocalSymbols --%>
<%--suppress JSUnusedLocalSymbols --%>
<%--suppress JSUnresolvedVariable --%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>

<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Магазинчик</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
          crossorigin="anonymous">
    <style>
        .close {
            margin-left: 10px;
        }
    </style>
</head>

<body onload="initialize()">
<div class="jumbotron">
    <div class="container">
        <h1 class="display-3">Магазинчик</h1>
    </div>
</div>
<div class="container">
    <div class="row justify-content-between">
        <div class="col-6">
            <h2 class="mb-4">Товары (лучшие в своём роде)</h2>
            <%--<hr>--%>
            <div class="list-group" id="items-cards">

            </div>
        </div>
        <div class="col-4">
            <h2 class="mb-4">Корзина</h2>
            <%--<hr>--%>
            <div class="mb-3о" id="cart-items">
            </div>
            <div class="d-flex w-100 justify-content-between align-items-center border-top border-secondary pt-1">
                <p class="h5">Итого</p>
                <span class="h5 font-weight-light" id="final-price">0 ₽</span>
            </div>
        </div>
    </div>
</div>
</body>
<script
        src="https://code.jquery.com/jquery-3.3.1.min.js"
        integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
        crossorigin="anonymous"></script>
<script>
    function initialize() {
        getItems();
        getBasket();
    }

    function getItems() {
        $.ajax({
            type: 'GET',
            url: '/items'
        }).done(function (data) {
            let listHtml = '';
            for (let i = 0; i < data.length; i++) {
                let itemHtml = '';
                itemHtml += '<div class="list-group-item">\n';
                itemHtml += '    <div class="d-flex w-100 justify-content-between align-items-center">\n';
                itemHtml += '        <h4 class="mb-1">';
                itemHtml +=             data[i].name;
                itemHtml +=         '</h4>\n';
                itemHtml += '        <div>\n';
                itemHtml += '           <span class="h2 font-weight-light mr-3">';
                itemHtml +=                 data[i].price;
                itemHtml +=             ' ₽</span>\n';
                itemHtml += '            <button onclick="addToBasket(this)" type="button" class="btn btn-outline-secondary float-right" data-id="';
                itemHtml += data[i].id;
                itemHtml += '">\n';
                itemHtml += '                В корзину\n';
                itemHtml += '            </button>\n';
                itemHtml += '        </div>\n';
                itemHtml += '    </div>\n';
                itemHtml += '</div>\n';

                listHtml += itemHtml;
            }
            $('#items-cards').html(listHtml);
        }).fail(function () {
            $('#items-cards').html("ЕГГОГ");
        })
    }

    function addToBasket(button) {
        let item_id = $(button).data("id");

        $.post('/basket', {itemid: item_id}, function () {
            getBasket();
        });
    }

    function removeFromBasket(button) {
        let item_id = $(button).data("id");

        $.ajax({
            type: 'DELETE',
            url: '/basket?itemid='+item_id
        }).done(getBasket);
    }

    function getBasket() {
        $.ajax({
            type: 'GET',
            url: '/basket'
        }).done(function (data) {
            let listHtml = '';
            let finalPrice = 0.0;

            for (let i = 0; i < data.length; i++) {
                let itemHtml = '';
                itemHtml += '<div class="d-flex w-100 justify-content-between align-items-center">\n';
                itemHtml += '    <p class="h5">';
                itemHtml +=          data[i].name;
                itemHtml += '    </p>\n';
                itemHtml += '    <div>\n';
                itemHtml += '    <span class="h5 font-weight-light">';
                itemHtml +=          data[i].amount;
                itemHtml +=        ' &times; ';
                itemHtml +=          data[i].price;
                itemHtml +=        ' ₽</span>\n';
                itemHtml += '    <button type="button" data-id="'+data[i].id+'" onclick="removeFromBasket(this)" class="close" aria-label="Close">\n';
                itemHtml += '        <span aria-hidden="true">&times;</span>\n';
                itemHtml += '    </button>\n';
                itemHtml += '    </div>\n';
                itemHtml += '</div>\n';

                listHtml += itemHtml;

                finalPrice += parseFloat(data[i].price) * parseInt(data[i].amount);
            }
            $('#cart-items').html(listHtml);
            $('#final-price').html(finalPrice + ' ₽');
        }).fail(function () {
            $('#items-cards').html("ЕГГОГ");
        })
    }
</script>
</html>
