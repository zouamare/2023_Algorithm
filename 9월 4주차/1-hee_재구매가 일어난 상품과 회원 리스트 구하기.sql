-- SELF JOIN(?) 을 통한 풀이!
-- WHERE 절로 조인을 해주는데, 
-- GROUP BY 절에 사용할 것과 같은 기준으로 조인해줌 
-- (= 문제에 조건에 따르면 USER_ID & PRODUCT_ID를 PK로 하는 조합을 구해야 하므로)
-- GROUBY 절에 1번 컬럼과 2번 컬럼으로 묶고
-- 1번(회원 ID) 오름차순 정렬 2번(상품 ID) 내림차순 정렬

SELECT O1.USER_ID, O2.PRODUCT_ID
FROM ONLINE_SALE O1, ONLINE_SALE O2
WHERE O1.user_id = O2.user_id 
AND O1.product_id = O2.product_id
GROUP BY 1, 2
HAVING COUNT(*) >= 2 -- 재구매한 회원인지 판단하는 여부이고, 이 조건절에서 2개 이상 집계된 경우에는 중복구매 이므로!
ORDER BY 1 ASC, 2 DESC;