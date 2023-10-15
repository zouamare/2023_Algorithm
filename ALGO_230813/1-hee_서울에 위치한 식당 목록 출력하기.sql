-- 서울에 위치한 식당 목록 출력하기
SELECT
R.REST_ID,
R.REST_NAME,
R.FOOD_TYPE,
R.FAVORITES,
R.ADDRESS,
ROUND(AVG(V.REVIEW_SCORE), 2) as SCORE
FROM REST_INFO R
JOIN REST_REVIEW V 
ON R.REST_ID = V.REST_ID
where left(R.ADDRESS, 2) = '서울'
group by R.REST_ID
order by SCORE desc, R.FAVORITES desc;

-- REST_INFO와 REST_REVIEW 테이블에서 
-- 서울에 위치한 식당들의 
-- 식당 ID, 식당 이름, 음식 종류, 즐겨찾기수, 주소, 리뷰 평균 점수를

-- 이때 리뷰 평균점수는 소수점 세 번째 자리에서 반올림 
-- 해주시고 결과는 평균점수를 기준으로 내림차순 정렬해주시고, 
-- 평균점수가 같다면 즐겨찾기수를 기준으로 내림차순 정렬해주세요.