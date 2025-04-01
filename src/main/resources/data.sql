INSERT INTO item (title, created, adjusted, task) SELECT 'testing', '2024-02-22T12:16:00.000000',null,'testing stuff out'
WHERE NOT EXISTS (SELECT 'testing' FROM item WHERE title = 'testing')