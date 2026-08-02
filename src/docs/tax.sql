select * from olb_giro
where bookingDate between '2025-01-01' and '2026-01-01'
and transactionText = 'UEBERWSG'

select * from postbank_giro;

select distinct transactionType from postbank_giro;

select * from postbank_giro
where transactionType in ('SEPA Überweisung', 'Überweisung', 'SEPA Echtzeitüberweisung')
and bookingDate between '2025-01-01' and '2026-01-01';

select * from postbank_giro where lower(partnerName) like '%naaf%';

select * from olb_giro where lower(partnerName) like '%naaf%';

select p.bookingDate, gc.name, p.partnerName, p.amount
from giro_classification gc
         join postbank_giro_classification pc on gc.id = pc.classification_id
         join postbank_giro p on p.id = pc.postbank_giro_id
where gc.category = 'SPENDE'
  and p.bookingDate between '2024-12-31' and '2026-01-01'
order by gc.name, p.bookingDate;

select p.bookingDate, gc.name, p.partnerName, p.amount
from giro_classification gc
         join postbank_giro_classification pc on gc.id = pc.classification_id
         join postbank_giro p on p.id = pc.postbank_giro_id
where gc.category = 'VERSICHERUNG'
  and p.bookingDate between '2024-12-31' and '2026-01-01'
order by gc.name, p.bookingDate;

select p.bookingDate, gc.name, p.partnerName, p.amount
from giro_classification gc
         join postbank_giro_classification pc on gc.id = pc.classification_id
         join postbank_giro p on p.id = pc.postbank_giro_id
where gc.category = 'TILO'
  and p.bookingDate between '2024-12-31' and '2026-01-01'
order by gc.name, p.bookingDate;

select * from postbank_giro
where lower(partnerName) like '%bund der vers%'
order by bookingDate;

select * from postbank_giro
where partnerName like 'BdV%'
order by bookingDate;

select * from postbank_giro
where lower(paymentReference) like '%risiko%'
order by bookingDate;
