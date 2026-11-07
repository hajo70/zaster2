Generate an OkxFillEntity to store OkxFill data similar to BitgetFutureOrderEntity.
Use billId as primary key. Use Instant and BigDecimal accordingly.

See API-Docs for OkxFill
https://www.okx.com/docs-v5/en/#order-book-trading-trade-get-transaction-details-last-3-months

Samples for OkxFill:
2026-11-07T15:11:53.718+01:00  INFO 6363 --- [           main] de.spricom.zaster2.okx.OkxApiDemo        : #7: OkxFill(instType=FUTURES, instId=ZEC-USD_UM_XPERP-310530, tradeId=6081356, ordId=4005381605299064832, clOrdId=, billId=4010217361792307200, tag=OTversionB, fillPx=1125, fillSz=3, side=buy, posSide=net, execType=M, feeCcy=USDC, fee=-0.00675, ts=1792016190660, fillTime=1792016190659, fillPxVol=, fillPxUsd=, fillMarkVol=, fillMarkPx=1126.07, tradeQuoteQty=null)
2026-11-07T15:11:53.718+01:00  INFO 6363 --- [           main] de.spricom.zaster2.okx.OkxApiDemo        : #8: OkxFill(instType=FUTURES, instId=ZEC-USD_UM_XPERP-310530, tradeId=5975530, ordId=3992396196008955904, clOrdId=, billId=4004722998572224512, tag=OTversionB, fillPx=1132, fillSz=5, side=buy, posSide=net, execType=M, feeCcy=USDC, fee=-0.01132, ts=1791852445876, fillTime=1791852445875, fillPxVol=, fillPxUsd=, fillMarkVol=, fillMarkPx=1132.64, tradeQuoteQty=null)
2026-11-07T15:11:53.718+01:00  INFO 6363 --- [           main] de.spricom.zaster2.okx.OkxApiDemo        : #9: OkxFill(instType=FUTURES, instId=ETH-USD_UM_XPERP-310404, tradeId=5745564, ordId=3992400242874781696, clOrdId=, billId=4004638019456602112, tag=OTversionB, fillPx=2430, fillSz=20, side=buy, posSide=net, execType=M, feeCcy=USDC, fee=-0.00972, ts=1791849913301, fillTime=1791849913299, fillPxVol=, fillPxUsd=, fillMarkVol=, fillMarkPx=2430.43, tradeQuoteQty=null)
2026-11-07T15:11:53.718+01:00  INFO 6363 --- [           main] de.spricom.zaster2.okx.OkxApiDemo        : #10: OkxFill(instType=FUTURES, instId=ZEC-USD_UM_XPERP-310530, tradeId=5935698, ordId=4002909740722819072, clOrdId=O4002909740664674304, billId=4002909741159026688, tag=OTversionB, fillPx=1298.28, fillSz=8, side=sell, posSide=net, execType=T, feeCcy=USDC, fee=-0.0519312, ts=1791798406594, fillTime=1791798406581, fillPxVol=, fillPxUsd=, fillMarkVol=, fillMarkPx=1300.27, tradeQuoteQty=null)
2026-11-07T15:11:53.718+01:00  INFO 6363 --- [           main] de.spricom.zaster2.okx.OkxApiDemo        : #11: OkxFill(instType=FUTURES, instId=ETH-USD_UM_XPERP-310404, tradeId=5465337, ordId=3985070885277798400, clOrdId=, billId=3991483702256078848, tag=OTversionB, fillPx=2530, fillSz=20, side=buy, posSide=net, execType=M, feeCcy=USDC, fee=-0.01012, ts=1791457884099, fillTime=1791457884097, fillPxVol=, fillPxUsd=, fillMarkVol=, fillMarkPx=2530, tradeQuoteQty=null)

Create a correspondig mapper and repository. 
