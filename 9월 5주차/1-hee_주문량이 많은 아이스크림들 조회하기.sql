# 두개 테이블을 조인해서 가장 많은 판매량을 갖는 아이스크림 맛 조회
# 두개 테이블에는 맛은 같지만 ID는 다른 데이터가 있고, 이것은 문제에서 필요로하는 그룹 기준이 아님!
# 그러므로, ID로 조인이 아닌 맛을 기준으로 조인해준다.

SELECT 
F.FLAVOR
FROM FIRST_HALF F, JULY J
WHERE F.FLAVOR = J.FLAVOR
GROUP BY F.FLAVOR 
ORDER BY SUM(F.TOTAL_ORDER+J.TOTAL_ORDER) DESC -- 7월 + 상반기의 아이스크림 총 주문량을 더한 값
LIMIT 3; -- 큰 순서대로 상위 3개의 맛
