CREATE TABLE mcdrive_foodpiece (
  --   Блюда
  id        SERIAL PRIMARY KEY,
  name      VARCHAR(50),
  cost      REAL,
  available BOOLEAN
);

CREATE TABLE mcdrive_ingredient (
  --   Ингредиенты
  id        SERIAL PRIMARY KEY,
  name      VARCHAR(50),
  available BOOLEAN
);

CREATE TABLE mcdrive_ingredient_foodpiece (
  --   В каких блюдах какие ингредиенты
  ingredient_id INTEGER,
  foodpiece_id  INTEGER,
  amount        INTEGER
);

CREATE TABLE mcdrive_modification (
  --   Модификации (доп. котлета, сыр, etc.)
  id            SERIAL PRIMARY KEY,
  name          VARCHAR(50),
  ingredient_id INTEGER,
  extra_cost    REAL
);

CREATE TABLE mcdrive_modification_foodpiece (
  --   Какие модификации применимы к каким блюдам
  foodpiece_id    INTEGER,
  modification_id INTEGER
);

CREATE TABLE mcdrive_order (
  --   Заказ
  id     SERIAL PRIMARY KEY,
  sum    REAL,
  "time" TIMESTAMP,
  paid   BOOLEAN,
  ready  BOOLEAN
);

CREATE TABLE mcdrive_foodpiece_order (
  --   Какая еда в каком заказе
  id           SERIAL PRIMARY KEY,
  foodpiece_id INTEGER,
  order_id     INTEGER,
  ready        BOOLEAN
);

CREATE TABLE mcdrive_modification_foodpiece_order (
  --   Какие модификацие были применены к каким блюдам в неком заказе
  modification_id    INTEGER,
  foodpiece_order_id INTEGER,
  amount             INTEGER,
  extra_cost         REAL
);

ALTER TABLE mcdrive_foodpiece_order
  ADD FOREIGN KEY (foodpiece_id)
REFERENCES mcdrive_foodpiece (id);

ALTER TABLE mcdrive_foodpiece_order
  ADD FOREIGN KEY (order_id)
REFERENCES mcdrive_order (id);

ALTER TABLE mcdrive_ingredient_foodpiece
  ADD FOREIGN KEY (ingredient_id)
REFERENCES mcdrive_ingredient (id);

ALTER TABLE mcdrive_ingredient_foodpiece
  ADD FOREIGN KEY (foodpiece_id)
REFERENCES mcdrive_foodpiece (id);

ALTER TABLE mcdrive_modification_foodpiece_order
  ADD FOREIGN KEY (modification_id)
REFERENCES mcdrive_modification (id);

ALTER TABLE mcdrive_modification_foodpiece_order
  ADD FOREIGN KEY (foodpiece_order_id)
REFERENCES mcdrive_foodpiece_order (id);

ALTER TABLE mcdrive_modification
  ADD FOREIGN KEY (ingredient_id)
REFERENCES mcdrive_ingredient (id);

ALTER TABLE mcdrive_modification_foodpiece
  ADD FOREIGN KEY (foodpiece_id)
REFERENCES mcdrive_foodpiece (id);

ALTER TABLE mcdrive_modification_foodpiece
  ADD FOREIGN KEY (modification_id)
REFERENCES mcdrive_modification (id);