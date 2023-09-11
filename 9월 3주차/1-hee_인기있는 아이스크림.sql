# 맛을 기준으로 그룹해주고,
# 총 주문량에대한 정렬 옵션을 준다.

SELECT
FLAVOR -- 상반기에 판매된 아이스크림의 맛 
FROM FIRST_HALF
GROUP BY FLAVOR
ORDER BY SUM(TOTAL_ORDER) DESC, SHIPMENT_ID	;  -- 1) 총주문량을 기준 내림차순  2) 출하 번호를 기준 오름차순



