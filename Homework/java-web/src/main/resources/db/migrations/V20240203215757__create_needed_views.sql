CREATE VIEW MonthlyEncodingStats AS
SELECT
    DATE_TRUNC('month', time) AS month,
    algorithm AS codec,
    'success' AS status,
    COUNT(*) AS count
FROM
    History
GROUP BY
    month, codec, status
ORDER BY
    month DESC;

CREATE VIEW DailyEncodingStats AS
SELECT
    DATE_TRUNC('day', time) AS day,
    algorithm AS codec,
    'success' AS status,
    COUNT(*) AS count
FROM
    History
GROUP BY
    day, codec, status
ORDER BY
    day DESC;

CREATE VIEW FailedEncodings AS
SELECT
    time,
    algorithm AS codec,
    'failed' AS status
FROM
    History
WHERE
    algorithm = 'cipher holder'
ORDER BY
    time DESC;
