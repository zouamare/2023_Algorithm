-- 완료된 중고 거래의 총금액이 70만 원 이상인 사람
-- 회원 ID, 닉네임, 총거래금액을 조회하는 SQL문을 작성
-- 결과는 총거래금액을 기준으로 오름차순 정렬

SELECT
U.USER_ID, U.NICKNAME, SUM(B.PRICE) as TOTAL_SALES -- 회원 ID, 닉네임, 총거래금액
FROM USED_GOODS_BOARD B
JOIN USED_GOODS_USER U
ON B.WRITER_ID = U.USER_ID and B.STATUS = 'DONE' -- 완료된
GROUP BY U.USER_ID, B.STATUS
HAVING TOTAL_SALES >= 700000 -- 완료된 중고 거래의 총금액이 70만 원 이상
ORDER BY 3; -- 결과는 총거래금액을 기준으로 오름차순 정렬

