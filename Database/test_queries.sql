/**
* SECTION:
* Handle Claims List Filtration
*
* NOTE:
* - Defaults: Status of "Processed"; Service End Date of `CURRENT_DATE`
* - COALESCE returns the first NON-NULL value and is used as a switch
* - ILIKE allows for case-insensitive text
* - The `p.name` text should be surrounded w/ '%' to enable partial-text search
* - A call to the column itself is basically saying to return all data
* - The example screen that this used is included for reference purposes
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