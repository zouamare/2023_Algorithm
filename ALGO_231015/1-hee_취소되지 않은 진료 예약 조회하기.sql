-- 테이블에서 2022년 4월 13일 취소되지 않은 ← 4월 13일 기준이라고 해야되지 않나...
-- 이러면 4월 13일에 취소되지 않은 모든 기록에 대해서 조회하라는 건줄 이해함...
-- 진료예약번호, 환자이름, 환자번호, 진료과코드, 의사이름, 진료예약일시
-- 진료 예약목록 테이블이 환자 테이블과 의사 테이블로부터 참조 관계에 있음
-- 따라서, 세 개의 릴레이션을 조인할 때 중심이 될 릴레이션을 진료 예약목록으로 하기로 함!
-- 근데 JOIN을 쓰면 사실 순서는 크게 중요한지 모르겠음..?

SELECT 
A.APNT_NO, P.PT_NAME, P.PT_NO, 
D.MCDP_CD, D.DR_NAME, A.APNT_YMD
FROM APPOINTMENT A
JOIN PATIENT P
ON A.PT_NO=P.PT_NO -- 예약목록과 환자 릴레이션은 환자 번호로 서로 관계가 있음 (환자번호 = 참조키)
JOIN DOCTOR D
ON A.MDDR_ID=D.DR_ID -- 예약목록과 의사 릴레이션은 의사 ID로 서로 관계가 있음 (의사ID = 참조키)
WHERE DATE(A.APNT_YMD) = DATE('2022-04-13') -- 이날만...T_T
AND A.APNT_CNCL_YN='N'
AND A.MCDP_CD = 'CS' 
ORDER BY 6 ASC;

