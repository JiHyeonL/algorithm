-- 코드를 작성해주세요
SELECT fish.ID, name.FISH_NAME, fish.LENGTH 
FROM FISH_INFO AS fish
JOIN FISH_NAME_INFO AS name ON fish.FISH_TYPE = name.FISH_TYPE
WHERE fish.length = (
    SELECT MAX(subf.LENGTH) FROM FISH_INFO AS subf
    WHERE subf.FISH_TYPE = fish.FISH_TYPE
)
ORDER BY fish.ID;