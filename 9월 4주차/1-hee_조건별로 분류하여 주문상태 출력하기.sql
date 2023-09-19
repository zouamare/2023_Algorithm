-- DATE_FORMAT을 통한 문자열의 변환과
-- CASE WHEN 절을 통한 조건 분기!

SELECT 
ORDER_ID, 
PRODUCT_ID, 
DATE_FORMAT(OUT_DATE, "%Y-%m-%d") AS OUT_DATE, -- 여기가 치사한게, 문제에는 포맷 정하라고 안했는데 결과에서 포맷 요구함 -_-;;;
CASE WHEN (OUT_DATE <= '2022-05-01') THEN '출고완료' -- 출고 여부 칼럼의 값에 대한 조건!, 세 가지 경우가 있음 :)
     WHEN (OUT_DATE > '2022-05-01') THEN '출고대기'
     ELSE '출고미정' END AS '출고여부'
FROM FOOD_ORDER
ORDER BY 1 ASC; -- 주문 ID를 기준으로 오름차순 정렬