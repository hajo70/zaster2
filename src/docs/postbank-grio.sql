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
