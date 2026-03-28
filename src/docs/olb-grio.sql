select count(distinct fileImportHistory_id) from olb_giro;
select count(*) from olb_giro;

select * from olb_giro
         order by bookingDate desc
limit 100;

select distinct fileImportHistory_id from olb_giro;
truncate olb_giro;
delete from file_import_history where id = 102;

select
    bookingDate,
    amount,
    partnerName,
    paymentReference,
    transactionText,
    customerReference,
    iban
    from olb_giro
order by bookingDate desc
limit 1000;

select
    bookingDate,
    amount,
    partnerName,
    paymentReference,
    transactionText,
    customerReference,
    iban
from olb_giro
where amount > 0
order by bookingDate desc
limit 1000;


-- duplicates
select * from olb_giro
where id in (select pg1.id
             from olb_giro pg1,
                  olb_giro pg2
             where pg1.id != pg2.id
               and pg1.bookingDate = pg2.bookingDate
               and pg1.amount = pg2.amount)
order by bookingDate, amount

