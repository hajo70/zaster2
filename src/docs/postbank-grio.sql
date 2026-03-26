select count(distinct fileImportHistory_id) from postbank_giro;
select count(*) from postbank_giro;

select * from postbank_giro
         order by bookingDate desc
limit 100;

select
    bookingDate,
    amount,
    partnerName,
    paymentReference,
    transactionType,
    mandateReference,
    deviatingRecipient,
    iban
    from postbank_giro
order by bookingDate desc
limit 1000;

select
    bookingDate,
    amount,
    partnerName,
    paymentReference,
    transactionType,
    mandateReference,
    deviatingRecipient,
    iban
from postbank_giro
where amount > 0
order by bookingDate desc
limit 1000;


-- duplicates
select * from postbank_giro
where id in (select pg1.id
             from postbank_giro pg1,
                  postbank_giro pg2
             where pg1.id != pg2.id
               and pg1.bookingDate = pg2.bookingDate
               and pg1.amount = pg2.amount)
order by bookingDate, amount

