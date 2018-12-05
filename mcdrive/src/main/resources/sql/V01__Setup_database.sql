CREATE TABLE "user" (
  --  Пользователь
  id          BIGSERIAL PRIMARY KEY,
  first_name  VARCHAR(100),
  last_name   VARCHAR(100),
  role        VARCHAR(8)
);

CREATE TABLE cookie (
  --  Аутентификация
  id          BIGSERIAL PRIMARY KEY,
  value       CHAR(36),
  last_visit  TIMESTAMP,
  user_id     BIGINT
);

CREATE TABLE foodpiece (
  --   Блюда
  id        BIGSERIAL PRIMARY KEY,
  name      VARCHAR(50),
  cost      REAL,
  available BOOLEAN
);

CREATE TABLE ingredient (
  --   Ингредиенты
  id        BIGSERIAL PRIMARY KEY,
  name      VARCHAR(50),
  available BOOLEAN
);

CREATE TABLE ingredient_foodpiece (
  --   В каких блюдах какие ингредиенты
  ingredient_id BIGINT,
  foodpiece_id  BIGINT,
  amount        SMALLINT
);

CREATE TABLE modification (
  --   Модификации (доп. котлета, сыр, etc.)
  id            BIGSERIAL PRIMARY KEY,
  name          VARCHAR(50),
  ingredient_id BIGINT,
  extra_cost    REAL
);

CREATE TABLE modification_foodpiece (
  --   Какие модификации применимы к каким блюдам
  foodpiece_id    BIGINT,
  modification_id BIGINT
);

CREATE TABLE "order" (
  --   Заказ
  id     BIGSERIAL PRIMARY KEY,
  sum    REAL,
  "time" TIMESTAMP,
  paid   BOOLEAN,
  ready  BOOLEAN
);

CREATE TABLE foodpiece_order (
  --   Какая еда в каком заказе
  id           BIGSERIAL PRIMARY KEY,
  foodpiece_id BIGINT,
  order_id     BIGINT,
  ready        BOOLEAN
);

CREATE TABLE modification_foodpiece_order (
  --   Какие модификацие были применены к каким блюдам в неком заказе
  modification_id    BIGINT,
  foodpiece_order_id BIGINT,
  amount             SMALLINT,
  extra_cost         REAL
);

ALTER TABLE cookie
  ADD FOREIGN KEY (user_id)
REFERENCES "user" (id);

ALTER TABLE foodpiece_order
  ADD FOREIGN KEY (foodpiece_id)
REFERENCES foodpiece (id);

ALTER TABLE foodpiece_order
  ADD FOREIGN KEY (order_id)
REFERENCES "order" (id);

ALTER TABLE ingredient_foodpiece
  ADD FOREIGN KEY (ingredient_id)
REFERENCES ingredient (id);

ALTER TABLE ingredient_foodpiece
  ADD FOREIGN KEY (foodpiece_id)
REFERENCES foodpiece (id);

ALTER TABLE modification_foodpiece_order
  ADD FOREIGN KEY (modification_id)
REFERENCES modification (id);

ALTER TABLE modification_foodpiece_order
  ADD FOREIGN KEY (foodpiece_order_id)
REFERENCES foodpiece_order (id);

ALTER TABLE modification
  ADD FOREIGN KEY (ingredient_id)
REFERENCES ingredient (id);

ALTER TABLE modification_foodpiece
  ADD FOREIGN KEY (foodpiece_id)
REFERENCES foodpiece (id);

ALTER TABLE modification_foodpiece
  ADD FOREIGN KEY (modification_id)
REFERENCES modification (id);