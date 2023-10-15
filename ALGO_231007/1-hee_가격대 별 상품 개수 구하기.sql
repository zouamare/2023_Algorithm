# 가격대 별 상품 개수 구하기
##  만원 단위의 가격대 별로 상품 개수를
SELECT 
(PRICE - ( PRICE % 10000)) AS PRICE_GROUP, # 이부분 수식.. ㅜ_ㅜ
COUNT(*) AS PRODUCTS
FROM PRODUCT
GROUP BY 1
ORDER BY 1 ASC;




