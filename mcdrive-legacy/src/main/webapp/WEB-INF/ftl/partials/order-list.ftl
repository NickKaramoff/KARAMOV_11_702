<#list orders as order>
    <div class="list-group-item flex-column align-items-start order">
        <div class="d-flex w-100 justify-content-between mb-3">
            <h3 class="display-4 m-0">
                <small>#</small>${order.id}
            </h3>
            <p class="lead text-muted">${order.time}</p>
        </div>
        <ul class="list-group list-group-flush mb-4">
            <#list order.foodpieces as piece>
                <li class="list-group-item d-flex justify-content-between align-items-center">
                    <div class="foodpieceorder">
                        <p class="lead mb-0">${piece.name}</p>
                    </div>
                    <div class="btn-group-toggle" data-toggle="buttons">
                        <label class="btn btn-outline-success <#if piece.ready>active</#if>">
                            <input type="checkbox" autocomplete="off" <#if piece.ready>checked</#if>>
                            Готово
                        </label>
                    </div>
                </li>
            </#list>
        </ul>
        <div class="btn-group btn-group-toggle d-flex justify-content-end"
             data-toggle="buttons" role="group">
            <label class="btn btn-lg btn-outline-success disabled">
                <input type="checkbox" autocomplete="off"> Готов
            </label>
            <label class="btn btn-lg btn-outline-success disabled">
                <input type="checkbox" autocomplete="off"> Выдан
            </label>
        </div>
    </div>
</#list>