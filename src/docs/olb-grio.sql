-- latest 100 transactions
select * from olb_giro
         order by bookingDate desc
limit 100;

-- imported files
select f.filename, count(*) as count from olb_giro g
         join file_import_history f on g.fileImportHistory_id = f.id
group by f.filename

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

