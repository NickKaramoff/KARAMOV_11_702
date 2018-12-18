-- 1. Cколько уникальных посетителей было на сайте за месяц?
SELECT count(*) as count
FROM
  (SELECT DISTINCT client_id FROM entries) as unique_clients;

-- 2. Сколько страниц просмотрел посетитель в среднем, максимум, минимум?
SELECT min(visits), avg(visits)::NUMERIC(10,3), max(visits)
FROM
  (SELECT count(url) as visits FROM entries GROUP BY client_id) as client_visits;

-- 3. Сколько посетителей сделало заказы?
SELECT count(*)
FROM
  (SELECT DISTINCT client_id FROM entries WHERE url='/order.phtml') as orders;

-- 4. Сколько в среднем заказов оформляется за день?
SELECT avg(orders)::NUMERIC(10,3)
FROM
  (SELECT date_time::DATE, count(url) as orders
   FROM entries
   WHERE url='/order.phtml'
   GROUP BY date_time::DATE) as order_count;

-- 5. Сколько времени прошло с момента входа на сайт до оформления заказа, в среднем, максимум, минимум?
SELECT min(order_t-enter_t), avg(order_t-enter_t), max(order_t-enter_t)
FROM
  (SELECT client_id, min(date_time) as order_t FROM entries WHERE url='/order.phtml' GROUP BY client_id) as order_t_t
  JOIN
  (SELECT client_id, min(date_time) as enter_t FROM entries GROUP BY client_id) as enter_t_t
  ON order_t_t.client_id=enter_t_t.client_id;

-- 6. Сколько в среднем посетителей бывает за час?
SELECT avg(views)::NUMERIC(10,3)
FROM (SELECT date_trunc('hour', date_time), count(url) as views
      FROM entries
      GROUP BY date_trunc('hour', date_time)) as view_count;

-- 7. Какие пользователи совершили больше всего покупок?
SELECT client_id, count(*) as item_count
FROM
  (SELECT client_id, url FROM entries WHERE url LIKE '/addbasket%') as purchases
GROUP BY client_id
ORDER BY item_count DESC;

-- 8. Какие книги покупались чаще остальных?
SELECT split_part(url, '=', 2) as book_id, count(url) as order_count
FROM entries
WHERE url LIKE '/addbasket%'
GROUP BY url
ORDER BY order_count DESC;