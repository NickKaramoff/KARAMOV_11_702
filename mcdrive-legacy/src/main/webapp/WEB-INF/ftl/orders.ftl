<#include "common/head.ftl">

<body>
<#switch user.role>
    <#case "operator">
        <#include "header/operator.ftl">
        <#break>
    <#case "cook">
        <#include "header/cook.ftl">
        <#break>
    <#case "admin">
        <#include "header/admin.ftl">
        <#break>
</#switch>
<div class="container">
    <div class="row">
        <!-- ORDERS LIST -->
        <div class="col-md-8 col-lg-9 mb-3">
            <a href="" type="button" class="btn btn-outline-secondary btn-lg btn-block mb-3">Новый заказ</a>
            <div class="list-group" id="orders-list">
                <#include "partials/order-list.ftl">
            </div>
        </div>
        <!-- END ORDERS LIST -->

        <!-- SIDEBAR -->
        <div class="col-md-4 col-lg-3">
            <#include "common/sidebar.ftl">
        </div>
        <!-- END SIDEBAR -->
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
        integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
        integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
        crossorigin="anonymous"></script>
</body>

</html>