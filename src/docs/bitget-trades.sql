-- select oldest orders
select *
from bitget_future_order
order by createdAt
limit 100
