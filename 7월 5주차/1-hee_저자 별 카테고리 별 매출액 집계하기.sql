-- 2022년 1월의 도서 판매 데이터를 기준
--  저자 별, 카테고리 별 매출액(TOTAL_SALES = 판매량 * 판매가) 을 구하여
-- group by 저자, 
-- 저자 ID(AUTHOR_ID), 
-- 저자명(AUTHOR_NAME), 
-- 카테고리(CATEGORY), 
-- 매출액(SALES) 리스트를 출력하는 SQL문을 작성
-- 결과는 저자 ID를 오름차순으로, 저자 ID가 같다면 카테고리를 내림차순

SELECT
B.AUTHOR_ID,
A.AUTHOR_NAME,
B.CATEGORY,
SUM(B.PRICE * BS.SALES) as TOTAL_SALES
FROM BOOK B
JOIN AUTHOR A
ON B.AUTHOR_ID = A.AUTHOR_ID
JOIN BOOK_SALES BS
ON BS.book_id = B.BOOK_ID
where MONTH(BS.SALES_DATE) = 1 and YEAR(BS.SALES_DATE) = 2022
group by B.AUTHOR_ID, B.CATEGORY
order by B.AUTHOR_ID asc, B.CATEGORY desc;