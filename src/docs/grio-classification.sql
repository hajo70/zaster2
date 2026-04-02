-- classify by category
select gc.category, year(p.bookingDate), sum(p.amount)
from giro_classification gc
left outer join postbank_giro_classification pc on gc.id = pc.classification_id
left outer join postbank_giro p on p.id = pc.postbank_giro_id
group by gc.category, year(p.bookingDate)
order by gc.category, year(p.bookingDate)