select * from okx_bill_details
order by billId asc
limit 100;

select * from okx_bill_details
         where subType not in ('173', '174')
order by billId asc
limit 100;

select sum(posBalChg)
from okx_bill_details
where subType in ('173', '174');

select * from okx_bill_details
where subType in ('173', '174')
order by billId asc
limit 100;
