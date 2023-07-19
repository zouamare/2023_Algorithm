# 조건에 맞는 사용자 정보 조회하기
-- 테이블에서 중고 거래 게시물을 3건 이상 등록한 사용자의 사용자 ID, 닉네임, 전체주소, 전화번호를 조회하는 SQL문을 작성
-- 전체 주소는 시, 도로명 주소, 상세 주소가 함께 출력
-- 전화번호의 경우 xxx-xxxx-xxxx 같은 형태로 하이픈 문자열
-- 결과는 회원 ID를 기준으로 내림차순 정렬

SELECT 
UGU.USER_ID, 
UGU.NICKNAME,
CONCAT(UGU.CITY, ' ', UGU.STREET_ADDRESS1, ' ', UGU.STREET_ADDRESS2) as '전체주소',
concat(left(UGU.TLNO, 3), '-', substr(TLNO, 4, 4), '-', right(UGU.TLNO, 4)) as '전화번호'
from USED_GOODS_BOARD UGB 
join USED_GOODS_USER UGU 
on UGB.WRITER_ID = UGU.USER_ID 
GROUP BY UGU.USER_ID  -- 집계를 위해 group by 절 사용
having count(UGU.USER_ID) >= 3 -- 중고 거래 게시물을 3건 이상 등록한 사용자
order by UGU.USER_ID desc; -- 아이디 기준 내림차 순