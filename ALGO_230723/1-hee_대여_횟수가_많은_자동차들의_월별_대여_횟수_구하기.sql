# 대여 횟수가 많은 자동차들의 월별 대여 횟수 구하기
-- 2022년 8월부터 2022년 10월까지 총 대여 횟수가 5회 이상인 자동차들에 대해서
-- 해당 기간 동안의 월별 자동차 ID 별 총 대여 횟수(컬럼명: RECORDS) 리스트를 출력
-- 결과는 월을 기준으로 오름차순 정렬하고, 월이 같다면 자동차 ID를 기준으로 내림차순 정렬
-- 특정 월의 총 대여 횟수가 0인 경우에는 결과에서 제외

SELECT 
MONTH(CH1.START_DATE) as MONTH,
CH1.CAR_ID as CAR_ID,
count(CH1.CAR_ID) as RECORDS
from CAR_RENTAL_COMPANY_RENTAL_HISTORY as CH1
where CH1.CAR_ID in 
    (select CH2.CAR_ID from CAR_RENTAL_COMPANY_RENTAL_HISTORY as CH2
        WHERE YEAR(CH2.START_DATE) = '2022' 
        and MONTH(CH2.START_DATE) between 8 and 10
        group by CH2.CAR_ID 
        having count(*) >= 5) -- 총 대여 횟수가 5회 이상인 자동차들
        and YEAR(CH1.START_DATE) = '2022' and MONTH(CH1.START_DATE) between 8 and 10
group by MONTH, CH1.CAR_ID -- order 절에서 카운트를 위해 ID와 월을 기준으로 그룹핑 필요!
having count(*) != 0 -- 특정 월의 총 대여 횟수가 0인 경우에는 결과에서 제외
order by MONTH asc, CAR_ID desc; -- 월을 기준으로 오름차순 정렬하고, 월이 같다면 자동차 ID를 기준으로 내림차순 정렬
