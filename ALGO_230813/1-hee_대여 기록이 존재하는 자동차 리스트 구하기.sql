-- 대여 기록이 존재하는 자동차 리스트 구하기
SELECT
CR.CAR_ID as CAR_ID
FROM CAR_RENTAL_COMPANY_CAR CR
JOIN CAR_RENTAL_COMPANY_RENTAL_HISTORY CH
ON CR.CAR_ID = CH.CAR_ID
where CR.CAR_TYPE = '세단' and MONTH(CH.START_DATE) = 10 
group by CAR_ID
order by CR.CAR_ID desc;


-- 자동차 종류가 '세단'인 자동차들 중 
-- 10월에 대여를 시작한 기록이 있는 자동차 ID 리스트를 
-- 출력하는 SQL문을 작성
-- 자동차 ID 리스트는 중복이 없어야 하며, 자동차 ID를 기준으로 내림차순