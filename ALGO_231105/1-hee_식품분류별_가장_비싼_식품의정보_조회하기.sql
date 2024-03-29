SELECT
A.CATEGORY, 
A.PRICE,
A.PRODUCT_NAME
FROM FOOD_PRODUCT A, 
-- 최대 값을 구하기 위한 다중행 인라인 뷰
(SELECT 
    CATEGORY, 
    MAX(PRICE) AS PRICE 
    FROM FOOD_PRODUCT 
    GROUP BY 1
) B 
WHERE A.PRICE =  B.PRICE -- 최대 값을 구하고 그 값을 기준으로 EQUI 조인을 해줌
GROUP BY 1 -- 카테고리 별로 그룹핑 한번 더 해주는데,
HAVING CATEGORY IN ('과자', '국', '김치', '식용유') -- 문제에서 주어진 그룹핑 조건절을 여기서 해줘야함
-- 인라인 뷰에서 그룹핑을 해줄 경우에는 메인 쿼리를 수행하면서 제외해아할 카테고리도 같이 그룹핑을 하는
-- 합집합 연산이 발생할 수 있으므로 여기서 필터링을 걸어주어야 함
ORDER BY 2 DESC;

/*
풀이
- 인라인 뷰를 통해서 그룹별 최대 값을 구하고,
- 메인 쿼리에서는 카테고리별 최대 가격을 갖는 상품의 카테고리, 정보, 특히 이름을 구하는 식으로 구현함
- 메인 쿼리 하나에서 집계함수 (MAX)를 사용하면 안되는 이유는
- 데이터베이스의 쿼리 실행 계획에 따르면, 메인 쿼리에서 조회할 칼럼 별로 데이터를 모두 조회한 후에
- 그룹핑을 하기 때문에, 집계를 하는 과정에서 최대값에 해당하는 튜플을 집계해주는 것이 아니기 때문임
- 즉 집계함수를 안한 상태로 조회하면 각각의 엔티티별로 튜플을 이루어 릴레이션에 기록되어 있을텐데
- 집계함수를 하는 순간 정렬된 튜플의 순서는 깨지고, 집계함수에 의해 기존의 데이터와 다른 새로운 튜플이 생성되어 버림
- 따라서, '최대값을 가지는 행의 정보'를 출력하고자하는 현재 문제의 요구사항에 따라
- 적절한 조인 조건을 주어서 최대 값을 가질 때의 카테고리별 튜플을 조회하는 것이 풀이의 핵심
*/

-- 문제 조건
-- PRODUCT_ID, PRODUCT_NAME, PRODUCT_CD, CATEGORY, PRICE
-- 식품분류별로 가격이 제일 비싼 식품의 분류, 가격, 이름을 조회하는 SQL문을 작성
-- 이때 식품분류가 '과자', '국', '김치', '식용유'인 경우만 출력
-- 결과는 식품 가격을 기준으로 내림차순 정렬