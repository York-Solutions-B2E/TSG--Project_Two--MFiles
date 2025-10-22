
-- Simple SELECT (get ALL necessary columns)
/*
SELECT
    c.claim_number, c.service_start_date, c.service_end_date, 
    p.name, c.status, c.total_member_responsibility
FROM
    claim AS c
INNER JOIN
    provider AS p ON c.provider_id = p.id;
*/

-- Simple SELECT (filtered by Status of Processed)
/*
SELECT
    c.claim_number, c.service_start_date, c.service_end_date, 
    p.name, c.status, c.total_member_responsibility, c.received_date
FROM
    claim AS c
INNER JOIN
    provider AS p ON c.provider_id = p.id
WHERE
    c.status = 'PROCESSED';
*/

-- Simple SELECT (filtered by Status of Processed; Sorted)
-- PROJECT DEFAULT: Filtered by Processed (status) & Sorted by Received Date
/*
SELECT
    c.claim_number, c.service_start_date, c.service_end_date, 
    p.name, c.status, c.total_member_responsibility
FROM
    claim AS c
INNER JOIN
    provider AS p ON c.provider_id = p.id
WHERE
    c.status = 'PROCESSED'
ORDER BY
    c.received_date DESC;
*/

-- SELECT that uses an array (of sorts) for Status
/*
SELECT
    c.claim_number, c.service_start_date, c.service_end_date, 
    p.name, c.status, c.total_member_responsibility
FROM
    claim AS c
INNER JOIN
    provider AS p ON c.provider_id = p.id
WHERE
    c.status IN ('PROCESSED', 'PAID') AND
    name = 'River Clinic'
ORDER BY
    c.received_date DESC;
*/


/*
    status_arr CHARACTER VARYING[] DEFAULT ['PROCESSED'],
    start_date DATE DEFAULT NULL,
    end_date DATE DEFAULT NULL,

    claim_num CHARACTER VARYING DEFAULT NULL
*/

/*
CREATE OR REPLACE FUNCTION get_claims (
    provider CHARACTER VARYING DEFAULT NULL
) RETURNS TABLE(
    claim_number CHARACTER VARYING, service_start_date DATE,
    service_end_date DATE, name CHARACTER VARYING,
    status CHARACTER VARYING, total_member_responsibility NUMERIC
    ) AS $$
BEGIN
    RETURN QUERY
    SELECT
        c.claim_number, c.service_start_date, c.service_end_date, 
        p.name, c.status, c.total_member_responsibility
    FROM
        claim AS c
    INNER JOIN
        provider AS p ON c.provider_id = p.id
    WHERE
        name = provider OR IS NOT NULL
    ORDER BY
        c.received_date DESC;
END;
$$ LANGUAGE plpgsql;

SELECT * FROM get_claims(NULL);
*/



/*
SELECT
    c.claim_number, c.service_start_date, c.service_end_date, 
    p.name, c.status, c.total_member_responsibility
FROM
    claim AS c
INNER JOIN
    provider AS p ON c.provider_id = p.id
WHERE
    status IN ('PROCESSED') AND
    c.service_start_date >= COALESCE(NULL, c.service_start_date) AND
    c.service_end_date <= COALESCE(NULL, CURRENT_DATE) AND
    p.name ILIKE COALESCE(NULL, p.name) AND
    c.claim_number = COALESCE(NULL, c.claim_number)
ORDER BY
    c.received_date DESC;
*/

SELECT
    c.claim_number, c.service_start_date, c.service_end_date, 
    p.name, c.status, c.total_member_responsibility
FROM
    claim AS c
INNER JOIN
    provider AS p ON c.provider_id = p.id
WHERE
    member_id = 'd393b06d-972b-4d12-b95b-de1a94173e83' AND
    status IN ('PROCESSED') AND
    c.service_start_date >= COALESCE('2025-08-22', c.service_start_date) AND
    c.service_end_date <= COALESCE(NULL, CURRENT_DATE) AND
    p.name ILIKE COALESCE(NULL, p.name) AND
    c.claim_number = COALESCE(NULL, c.claim_number)
ORDER BY
    c.received_date DESC;






-- '%river%'
/*


c.claim_number = COALESCE(NULL, c.claim_number)
*/

-- status (IN not EQUALS)       status = COALESCE(NULL, status)
-- service_start_date
-- service_end_date
-- name ('provider' contains NOT exact)
-- claim_number

/*
How to handle the following scenarios:
- IF 'status' is not provided, then default to `('PROCESSED')`
- IF 

NOTE: Evaluating a WHERE to itself produces all results:
- EX: status IN (status) OR
- EX: status = COALESCE(NULL, status)
*/




/*
```
+--------------------------------------------------------------------------------+
| Claims                         [ John Smith ]                        (Sign out)|
+--------------------------------------------------------------------------------+
| Filters: [Status v] [ Date Range ▼ ] [Provider ____] [Claim # ____] (Search)  |
+--------------------------------------------------------------------------------+
| #C-10421 | 08/29–08/29 | River Clinic      | Processed | $45.00 | [View ▸]    |
| #C-10405 | 08/20–08/20 | City Imaging Ctr  | Denied    | $0.00  | [View ▸]    |
| #C-10398 | 08/09–08/09 | Prime Hospital    | Paid      | $120.00| [View ▸]    |
| ...                                                                            |
+--------------------------------------------------------------------------------+
| Page 1 of 5     [10 v] per page         ◂ Prev     1  2  3  ...   Next ▸      |
+--------------------------------------------------------------------------------+
```
*/