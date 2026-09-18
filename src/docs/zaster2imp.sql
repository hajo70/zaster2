insert into bitget_future_order
select * from zaster2imp.bitget_future_order
where orderId not in (select orderId from bitget_future_order);

insert into bitget_future_position
select * from zaster2imp.bitget_future_position
where positionId not in (select positionId from bitget_future_position);
