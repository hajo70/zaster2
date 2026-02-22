select count(*) from bitget_tax_future_record

select * from bitget_tax_future_record order by ts

select futureTaxType, count(*) from bitget_tax_future_record
group by futureTaxType

select spotTaxType, count(*) from bitget_tax_spot_record
group by spotTaxType

select * from bitget_tax_spot_record where spotTaxType = 'fiat_recharge_in'

select * from bitget_tax_spot_record where spotTaxType like 'Transfer%' order by ts

select * from bitget_tax_spot_record where spotTaxType like 'Exchange%'