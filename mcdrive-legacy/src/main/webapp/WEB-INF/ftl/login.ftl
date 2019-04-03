<#include "common/head.ftl">

<body>
<div class="container pt-2 mb-3">
    <nav class="navbar navbar-expand-md navbar-dark bg-dark rounded">
        <span class="navbar-brand mx-1 text-light">McDrive</span>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#main-menu-navbar"
                aria-controls="main-menu-navbar" aria-expanded="false" aria-label="Меню">
            <span class="navbar-toggler-icon"></span>
        </button>
    </nav>
</div>

<div class="container">
    <div class="row">
        <div class="col p-3">
            <h3 class="mb-3">Вход</h3>
                <form method="POST">
                    <div class="row">
                        <div class="col-lg-5 col-md-6">
                            <div class="form-group">
                                <label for="email">E-Mail</label>
                                <div class="input-group">
                                    <input type="email" class="form-control" id="email"
                                           name="email"
                                           placeholder="" required>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-5 col-md-6">
                            <div class="form-group">
                                <label for="password">Пароль</label>
                                <input type="password" class="form-control" id="password"
                                       name="password" placeholder="" required>
                            </div>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-success">Войти</button>
                </form>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
        crossorigin="anonymous"></script>
</body>

</html>