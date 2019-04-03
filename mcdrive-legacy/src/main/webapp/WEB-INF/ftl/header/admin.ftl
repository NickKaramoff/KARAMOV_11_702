<div class="container pt-2 mb-3">
    <nav class="navbar navbar-expand-md navbar-dark bg-dark rounded">
        <span class="navbar-brand mx-1 text-light">McDrive <sup style="opacity:0.5;">Admin</sup></span>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#main-menu-navbar"
                aria-controls="main-menu-navbar" aria-expanded="false" aria-label="Меню">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse ml-3" id="main-menu-navbar">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item <#if tabNumber=0>active</#if>">
                    <a class="nav-link" href="/orders">Смена</a>
                </li>
                <li class="nav-item <#if tabNumber=1>active</#if>">
                    <a class="nav-link" href="/foodpieces">Блюда</a>
                </li>
                <li class="nav-item <#if tabNumber=2>active</#if>">
                    <a class="nav-link" href="/ingredients">Ингредиенты</a>
                </li>
                <li class="nav-item <#if tabNumber=3>active</#if>">
                    <a class="nav-link" href="/employees">Работники</a>
                </li>
                <li class="nav-item <#if tabNumber=4>active</#if>">
                    <a class="nav-link" href="/report">Отчёт</a>
                </li>
            </ul>
        </div>
    </nav>
</div>