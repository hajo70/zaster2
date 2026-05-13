-- select oldest orders
select *
from bitget_future_order
order by createdAt
limit 100;

-- select oldest positions
select *
from bitget_future_position
order by createdAt
limit 100;

-- counting
select count(*) from bitget_future_order;
select count(*) from bitget_future_position;

-- api order details
select updatedAt, symbol, side, enterPointSource, size, posAvg, priceAvg, totalProfits, fee, presetStopLossPrice, price
from bitget_future_order
where enterPointSource = 'api'
order by createdAt desc
limit 100;

-- other oder details
select updatedAt, symbol, side, tradeSide, enterPointSource, size, posAvg, priceAvg, totalProfits, fee, presetStopLossPrice, price
from bitget_future_order
where enterPointSource != 'api'
order by createdAt desc
limit 100;

-- position details
select createdAt, updatedAt, symbol, holdSide, openAvgPrice, closeAvgPrice, netProfit
from bitget_future_position
order by createdAt desc
limit 100;

-- stats
select symbol, count(*), sum(fee), sum(totalProfits + fee)
from bitget_future_order
where createdAt > now() - interval 30 day
group by symbol;

select symbol, date(updatedAt), count(*), sum(fee), sum(totalProfits + fee)
from bitget_future_order
where createdAt > now() - interval 7 day
group by symbol, date(updatedAt)
order by symbol, date(updatedAt) desc;

-- adhoc
select *
from bitget_future_order
where createdAt > now() - interval 7 day
and symbol = 'DOGEUSDT';

select *
from bitget_future_position
where createdAt > now() - interval 7 day
  and symbol = 'DOGEUSDT';
