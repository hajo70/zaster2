select count(*) from bitget_tax_future_record;

select * from bitget_tax_future_record order by ts desc;

select * from bitget_tax_future_record
where symbol = 'BTCUSDT' and ts > '2026-04-01'
  and (futureTaxType = 'buy_deal' or futureTaxType = 'sell_deal')
order by ts desc;

select sum(amount), sum(fee), sum(amount) + sum(fee) as 'profit', -100 * sum(fee) / sum(amount) as '% fee'
from bitget_tax_future_record
where symbol = 'BTCUSDT' and ts > '2026-04-01'
order by ts desc;
