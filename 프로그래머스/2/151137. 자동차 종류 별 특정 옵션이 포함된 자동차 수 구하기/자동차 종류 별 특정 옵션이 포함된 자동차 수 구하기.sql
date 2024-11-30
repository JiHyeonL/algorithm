-- 코드를 입력하세요
# select * from CAR_RENTAL_COMPANY_CAR;
SELECT car_type, count(CAR_TYPE) as cars from CAR_RENTAL_COMPANY_CAR
where options like '%가죽시트' or options like '%열선시트%' or options like '%통풍시트%'
group by CAR_TYPE
order by car_type asc;