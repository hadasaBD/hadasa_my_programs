SELECT CUSTOMERS.COMPANY_NAME, STEP3.TOTAL_PAID FROM ORDERS INNER JOIN
(SELECT ORDER_ID,TOTAL_PAID FROM
(SELECT ORDER_ID, SUM(UNIT_PRICE * QUANTITY * (1-DISCOUNT)) AS TOTAL_PAID
FROM ORDER_DETAILS
GROUP BY ORDER_ID) STEP2
WHERE STEP2.TOTAL_PAID = 
(SELECT MAX(TOTAL_PAID) AS TOTAL_PAID FROM
(SELECT ORDER_ID, SUM(UNIT_PRICE * QUANTITY * (1-DISCOUNT)) AS TOTAL_PAID
FROM ORDER_DETAILS
GROUP BY ORDER_ID))) STEP3 ON ORDERS.ORDER_ID = STEP3.ORDER_ID
INNER JOIN CUSTOMERS ON ORDERS.CUSTOMER_ID=CUSTOMERS.CUSTOMER_ID;