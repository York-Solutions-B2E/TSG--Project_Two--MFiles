/*
IMPORTANT NOTE:
This script can be ran either directly through the PostgreSQL shell
... (i.e., PL/pgSQL) OR by using the 'data.sql' file in the Spring
... project. That said, a minor modification MUST be made at the end
... of the script:

- IF using PL/pgSQL, then use `$$ LANGUAGE plpgsql;`
- IF using Spring, then use `$$ LANGUAGE plpgsql;;`
*/

-- Initiate an Anonymous Function
DO

-- Pass SQL via String-Literal
$$

-- Initialize necessary variables
DECLARE
tsg_user_id UUID := gen_random_uuid();
tsg_member_id UUID := gen_random_uuid();
tsg_plan_id UUID := gen_random_uuid();
tsg_enrollment_id UUID := gen_random_uuid();

tsg_accumulator_one_id UUID := gen_random_uuid();
tsg_accumulator_two_id UUID := gen_random_uuid();

tsg_provider_id_01 UUID := gen_random_uuid();
tsg_provider_id_02 UUID := gen_random_uuid();
tsg_provider_id_03 UUID := gen_random_uuid();

tsg_claim_id_01 UUID := gen_random_uuid();
tsg_claim_id_02 UUID := gen_random_uuid();
tsg_claim_id_03 UUID := gen_random_uuid();
tsg_claim_id_04 UUID := gen_random_uuid();
tsg_claim_id_05 UUID := gen_random_uuid();
tsg_claim_id_06 UUID := gen_random_uuid();
tsg_claim_id_07 UUID := gen_random_uuid();
tsg_claim_id_08 UUID := gen_random_uuid();
tsg_claim_id_09 UUID := gen_random_uuid();
tsg_claim_id_10 UUID := gen_random_uuid();
tsg_claim_id_11 UUID := gen_random_uuid();
tsg_claim_id_12 UUID := gen_random_uuid();

tsg_claim_line_id_01 UUID := gen_random_uuid();
tsg_claim_line_id_02 UUID := gen_random_uuid();
tsg_claim_line_id_03 UUID := gen_random_uuid();
tsg_claim_line_id_04 UUID := gen_random_uuid();
tsg_claim_line_id_05 UUID := gen_random_uuid();
tsg_claim_line_id_06 UUID := gen_random_uuid();
tsg_claim_line_id_07 UUID := gen_random_uuid();
tsg_claim_line_id_08 UUID := gen_random_uuid();
tsg_claim_line_id_09 UUID := gen_random_uuid();
tsg_claim_line_id_10 UUID := gen_random_uuid();
tsg_claim_line_id_11 UUID := gen_random_uuid();
tsg_claim_line_id_12 UUID := gen_random_uuid();
tsg_claim_line_id_13 UUID := gen_random_uuid();
tsg_claim_line_id_14 UUID := gen_random_uuid();
tsg_claim_line_id_15 UUID := gen_random_uuid();
tsg_claim_line_id_16 UUID := gen_random_uuid();
tsg_claim_line_id_17 UUID := gen_random_uuid();
tsg_claim_line_id_18 UUID := gen_random_uuid();
tsg_claim_line_id_19 UUID := gen_random_uuid();
tsg_claim_line_id_20 UUID := gen_random_uuid();
tsg_claim_line_id_21 UUID := gen_random_uuid();
tsg_claim_line_id_22 UUID := gen_random_uuid();
tsg_claim_line_id_23 UUID := gen_random_uuid();
tsg_claim_line_id_24 UUID := gen_random_uuid();

tsg_claim_status_event_id_01 UUID := gen_random_uuid();
tsg_claim_status_event_id_02 UUID := gen_random_uuid();
tsg_claim_status_event_id_03 UUID := gen_random_uuid();
tsg_claim_status_event_id_04 UUID := gen_random_uuid();
tsg_claim_status_event_id_05 UUID := gen_random_uuid();
tsg_claim_status_event_id_06 UUID := gen_random_uuid();
tsg_claim_status_event_id_07 UUID := gen_random_uuid();
tsg_claim_status_event_id_08 UUID := gen_random_uuid();
tsg_claim_status_event_id_09 UUID := gen_random_uuid();
tsg_claim_status_event_id_10 UUID := gen_random_uuid();
tsg_claim_status_event_id_11 UUID := gen_random_uuid();
tsg_claim_status_event_id_12 UUID := gen_random_uuid();

-- Create Code-Block for Data Seeding
BEGIN

    -- Code Block: Seed (x01) User and Member
    BEGIN
        INSERT INTO tsguser (id, email, created_at, updated_at) VALUES (
        tsg_user_id,
        'm.kefiles@gmail.com',
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP
        );

        INSERT INTO member (
        id, user_id, first_name, last_name, date_of_birth,
        email, phone, line1, city, state, postal_code
        ) VALUES (
        tsg_member_id, tsg_user_id, 'Mike', 'Files', '1991-01-31',
        'm.kefiles@gmail.com', '207-500-9076', '118 Staples Hill Road',
        'Canton', 'Maine', '04221'
        );
    END;


    -- Code Block: Seed (x01) Plan
    BEGIN
        INSERT INTO plan (id, name, plan_type, network_name, plan_year, deductible, out_out_pocket_max)
        VALUES (
        tsg_plan_id, 'Anthem Clear Choice Bronze X', 'PPO', 'Anthem', 2025,
        8000.00, 10600.00
        );
    END;


    -- Code Block: Seed (x01) Enrollment
    BEGIN
        INSERT INTO enrollment (id, member_id, plan_id, coverage_start)
        VALUES (
        tsg_enrollment_id, tsg_member_id,
        tsg_plan_id, CURRENT_DATE
        );
    END;


    -- Code Block: Seed (x02) Accumulators (i.e., BOTH)
    BEGIN
        INSERT INTO accumulator (
        id, enrollment_id, type, tier, limit_amount, used_amount
        ) VALUES (
        tsg_accumulator_one_id, tsg_enrollment_id, 'DEDUCTIBLE',
        'IN_NETWORK', 7200.00, 200.00
        );

        INSERT INTO accumulator (
        id, enrollment_id, type, tier, limit_amount, used_amount
        ) VALUES (
        tsg_accumulator_two_id, tsg_enrollment_id, 'OOP_MAX',
        'IN_NETWORK', 8000.00, 500.00
        );
    END;


    -- Code Block: Seed (x03) Providers
    BEGIN
        INSERT INTO provider (
        id, name, specialty, phone, city, line1,
        postal_code, state
        ) VALUES (
        tsg_provider_id_01, 'River Clinic', 'Neurology',
        '508-555-0101', 'Boston', '1 Main Street',
        '02347', 'MA'
        );

        INSERT INTO provider (
        id, name, specialty, phone, city, line1,
        postal_code, state
        ) VALUES (
        tsg_provider_id_02, 'City Imaging Ctr', 'Radiology',
        '508-555-1010', 'Worcester', '2 Main Street',
        '02779', 'MA'
        );

        INSERT INTO provider (
        id, name, specialty, phone, city, line1,
        postal_code, state
        ) VALUES (
        tsg_provider_id_03, 'Prime Hospital', 'Dermatology',
        '508-555-2121', 'Springfield', '3 Main Street',
        '02780', 'MA'
        );
    END;


    -- Code Block: Seed (x12) Claims
    BEGIN
        INSERT INTO claim (
        id, claim_number, member_id, provider_id, service_start_date,
        service_end_date, received_date, status, total_billed,
        total_allowed, total_plan_paid, total_member_responsibility, updated_at
        ) VALUES (
        tsg_claim_id_01, '#C-10300', tsg_member_id, tsg_provider_id_01, '2025-08-31',
        '2025-07-22', '2025-08-18', 'SUBMITTED', 385.05, 819.08, 407.69, 1523.11, NOW()
        );

        INSERT INTO claim (
        id, claim_number, member_id, provider_id, service_start_date,
        service_end_date, received_date, status, total_billed,
        total_allowed, total_plan_paid, total_member_responsibility, updated_at
        ) VALUES (
        tsg_claim_id_02, '#C-10301', tsg_member_id, tsg_provider_id_02, '2025-07-15',
        '2025-04-02', '2024-11-24', 'IN_REVIEW', 1186.51, 1260.99, 555.68, 529.26, NOW()
        );

        INSERT INTO claim (
        id, claim_number, member_id, provider_id, service_start_date,
        service_end_date, received_date, status, total_billed,
        total_allowed, total_plan_paid, total_member_responsibility, updated_at
        ) VALUES (
        tsg_claim_id_03, '#C-10302', tsg_member_id, tsg_provider_id_03, '2025-03-22',
        '2025-03-10', '2024-11-18', 'DENIED', 234.27, 1305.94, 92.05, 128.88, NOW()
        );

        INSERT INTO claim (
        id, claim_number, member_id, provider_id, service_start_date,
        service_end_date, received_date, status, total_billed,
        total_allowed, total_plan_paid, total_member_responsibility, updated_at
        ) VALUES (
        tsg_claim_id_04, '#C-10303', tsg_member_id, tsg_provider_id_03, '2025-06-17',
        '2025-07-22', '2025-07-25', 'SUBMITTED', 385.57, 1955.37, 966.66, 179.68, NOW()
        );

        INSERT INTO claim (
        id, claim_number, member_id, provider_id, service_start_date,
        service_end_date, received_date, status, total_billed,
        total_allowed, total_plan_paid, total_member_responsibility, updated_at
        ) VALUES (
        tsg_claim_id_05, '#C-10304', tsg_member_id, tsg_provider_id_02, '2025-04-17',
        '2025-08-15', '2025-01-29', 'PAID', 1265.67, 328.20, 1746.12, 1442.73, NOW()
        );

        INSERT INTO claim (
        id, claim_number, member_id, provider_id, service_start_date,
        service_end_date, received_date, status, total_billed,
        total_allowed, total_plan_paid, total_member_responsibility, updated_at
        ) VALUES (
        tsg_claim_id_06, '#C-10305', tsg_member_id, tsg_provider_id_01, '2025-07-17',
        '2025-08-04', '2025-09-07', 'PAID', 1708.07, 1251.84, 1130.69, 1163.99, NOW()
        );

        INSERT INTO claim (
        id, claim_number, member_id, provider_id, service_start_date,
        service_end_date, received_date, status, total_billed,
        total_allowed, total_plan_paid, total_member_responsibility, updated_at
        ) VALUES (
        tsg_claim_id_07, '#C-10306', tsg_member_id, tsg_provider_id_02, '2025-08-07',
        '2025-02-07', '2024-10-23', 'IN_REVIEW', 937.33, 961.43, 1183.37, 1234.88, NOW()
        );

        INSERT INTO claim (
        id, claim_number, member_id, provider_id, service_start_date,
        service_end_date, received_date, status, total_billed,
        total_allowed, total_plan_paid, total_member_responsibility, updated_at
        ) VALUES (
        tsg_claim_id_08, '#C-10307', tsg_member_id, tsg_provider_id_01, '2024-11-02',
        '2025-05-05', '2025-07-27', 'SUBMITTED', 372.32, 1782.40, 311.50, 1158.99, NOW()
        );

        INSERT INTO claim (
        id, claim_number, member_id, provider_id, service_start_date,
        service_end_date, received_date, status, total_billed,
        total_allowed, total_plan_paid, total_member_responsibility, updated_at
        ) VALUES (
        tsg_claim_id_09, '#C-10308', tsg_member_id, tsg_provider_id_03, '2025-07-03',
        '2025-02-04', '2024-09-25', 'PROCESSED', 1923.28, 1811.66, 567.71, 849.77, NOW()
        );

        INSERT INTO claim (
        id, claim_number, member_id, provider_id, service_start_date,
        service_end_date, received_date, status, total_billed,
        total_allowed, total_plan_paid, total_member_responsibility, updated_at
        ) VALUES (
        tsg_claim_id_10, '#C-10309', tsg_member_id, tsg_provider_id_03, '2024-12-22',
        '2025-01-26', '2024-09-09', 'DENIED', 1576.24, 1850.97, 1494.30, 1838.27, NOW()
        );

        INSERT INTO claim (
        id, claim_number, member_id, provider_id, service_start_date,
        service_end_date, received_date, status, total_billed,
        total_allowed, total_plan_paid, total_member_responsibility, updated_at
        ) VALUES (
        tsg_claim_id_11, '#C-10310', tsg_member_id, tsg_provider_id_01, '2025-08-22',
        '2025-01-21', '2024-10-21', 'PROCESSED', 1935.31, 278.91, 1916.65, 812.09, NOW()
        );

        INSERT INTO claim (
        id, claim_number, member_id, provider_id, service_start_date,
        service_end_date, received_date, status, total_billed,
        total_allowed, total_plan_paid, total_member_responsibility, updated_at
        ) VALUES (
        tsg_claim_id_12, '#C-10311', tsg_member_id, tsg_provider_id_02, '2025-01-29',
        '2025-04-13', '2025-04-11', 'IN_REVIEW', 544.22, 976.95, 1613.82, 709.51, NOW()
        );
    END;


    -- Code Block: Seed (x24) Claims Lines (each Claim will have (x2))
    BEGIN
        INSERT INTO claim_line (
        id, claim_id, line_number, cpt_code, description, billed_amount, allowed_amount,
        deductible_applied, copay_applied, coinsurance_applied, plan_paid, member_responsibility
        ) VALUES (
        tsg_claim_line_id_01, tsg_claim_id_01, 1, '93897', 'Emboli detcj wo iv mbubb njx',
        494.70, 24.05, 210.33, 130.65, 10.31, 403.52, 330.62
        );

        INSERT INTO claim_line (
        id, claim_id, line_number, cpt_code, description, billed_amount, allowed_amount,
        deductible_applied, copay_applied, coinsurance_applied, plan_paid, member_responsibility
        ) VALUES (
        tsg_claim_line_id_02, tsg_claim_id_02, 3, '90688', 'IIV4 vacc splt 0.5 ml im',
        46.31, 23.23, 16.81, 159.08, 206.32, 418.65, 54.61
        );

        INSERT INTO claim_line (
        id, claim_id, line_number, cpt_code, description, billed_amount, allowed_amount,
        deductible_applied, copay_applied, coinsurance_applied, plan_paid, member_responsibility
        ) VALUES (
        tsg_claim_line_id_03, tsg_claim_id_03, 5, '0425U', 'Genom rpd seq alys ea cmprtr',
        130.18, 426.89, 436.06, 83.87, 256.60, 431.94, 445.91
        );

        INSERT INTO claim_line (
        id, claim_id, line_number, cpt_code, description, billed_amount, allowed_amount,
        deductible_applied, copay_applied, coinsurance_applied, plan_paid, member_responsibility
        ) VALUES (
        tsg_claim_line_id_04, tsg_claim_id_04, 7, '73501', 'X-ray exam hip uni 1 view',
        30.58, 146.12, 389.45, 410.36, 416.61, 150.65, 222.50
        );

        INSERT INTO claim_line (
        id, claim_id, line_number, cpt_code, description, billed_amount, allowed_amount,
        deductible_applied, copay_applied, coinsurance_applied, plan_paid, member_responsibility
        ) VALUES (
        tsg_claim_line_id_05, tsg_claim_id_05, 9, 'G2250', 'Remot img sub by pt non e/m',
        424.53, 370.35, 430.26, 37.83, 272.14, 56.47, 298.61
        );

        INSERT INTO claim_line (
        id, claim_id, line_number, cpt_code, description, billed_amount, allowed_amount,
        deductible_applied, copay_applied, coinsurance_applied, plan_paid, member_responsibility
        ) VALUES (
        tsg_claim_line_id_06, tsg_claim_id_06, 11, '98967', 'Ph1 assmt&mgmt nqhp 11-20',
        132.62, 198.03, 130.67, 92.48, 439.10, 464.97, 22.69
        );

        INSERT INTO claim_line (
        id, claim_id, line_number, cpt_code, description, billed_amount, allowed_amount,
        deductible_applied, copay_applied, coinsurance_applied, plan_paid, member_responsibility
        ) VALUES (
        tsg_claim_line_id_07, tsg_claim_id_07, 13, '0042U', 'B brgdrferi antb 12 prtn igg',
        76.49, 278.05, 53.55, 317.51, 484.01, 64.80, 441.14
        );

        INSERT INTO claim_line (
        id, claim_id, line_number, cpt_code, description, billed_amount, allowed_amount,
        deductible_applied, copay_applied, coinsurance_applied, plan_paid, member_responsibility
        ) VALUES (
        tsg_claim_line_id_08, tsg_claim_id_08, 15, 'G0476', 'Hpv combo assay ca screen',
        129.52, 445.96, 146.64, 196.30, 419.89, 169.99, 54.19
        );

        INSERT INTO claim_line (
        id, claim_id, line_number, cpt_code, description, billed_amount, allowed_amount,
        deductible_applied, copay_applied, coinsurance_applied, plan_paid, member_responsibility
        ) VALUES (
        tsg_claim_line_id_09, tsg_claim_id_09, 17, '73223', 'Mri joint upr extr w/o&w/dye',
        89.52, 370.72, 339.32, 218.70, 81.92, 52.28, 359.46
        );

        INSERT INTO claim_line (
        id, claim_id, line_number, cpt_code, description, billed_amount, allowed_amount,
        deductible_applied, copay_applied, coinsurance_applied, plan_paid, member_responsibility
        ) VALUES (
        tsg_claim_line_id_10, tsg_claim_id_10, 19, 'G0451', 'Development test interp & rep',
        54.34, 224.97, 472.06, 44.18, 30.85, 464.64, 431.33
        );

        INSERT INTO claim_line (
        id, claim_id, line_number, cpt_code, description, billed_amount, allowed_amount,
        deductible_applied, copay_applied, coinsurance_applied, plan_paid, member_responsibility
        ) VALUES (
        tsg_claim_line_id_11, tsg_claim_id_11, 21, '92608', 'Ex for speech device rx addl',
        311.93, 412.11, 463.94, 118.46, 153.80, 322.80, 495.63
        );

        INSERT INTO claim_line (
        id, claim_id, line_number, cpt_code, description, billed_amount, allowed_amount,
        deductible_applied, copay_applied, coinsurance_applied, plan_paid, member_responsibility
        ) VALUES (
        tsg_claim_line_id_12, tsg_claim_id_12, 23, '0473U', 'Onc sld tum bld/slv 648 gene',
        288.75, 462.49, 408.69, 69.95, 477.82, 208.22, 106.43
        );

        INSERT INTO claim_line (
        id, claim_id, line_number, cpt_code, description, billed_amount, allowed_amount,
        deductible_applied, copay_applied, coinsurance_applied, plan_paid, member_responsibility
        ) VALUES (
        tsg_claim_line_id_13, tsg_claim_id_01, 2, '73090', 'X-ray exam of forearm',
        379.02, 59.97, 197.93, 318.91, 101.66, 344.92, 19.67
        );

        INSERT INTO claim_line (
        id, claim_id, line_number, cpt_code, description, billed_amount, allowed_amount,
        deductible_applied, copay_applied, coinsurance_applied, plan_paid, member_responsibility
        ) VALUES (
        tsg_claim_line_id_14, tsg_claim_id_02, 4, '0373U', 'Iadna rsp tr nfct 17 8 13&16',
        438.43, 370.50, 195.15, 67.49, 172.01, 245.70, 68.35
        );

        INSERT INTO claim_line (
        id, claim_id, line_number, cpt_code, description, billed_amount, allowed_amount,
        deductible_applied, copay_applied, coinsurance_applied, plan_paid, member_responsibility
        ) VALUES (
        tsg_claim_line_id_15, tsg_claim_id_03, 6, '0153U', 'Onc breast mrna 101 genes',
        287.73, 280.11, 15.53, 193.46, 226.77, 26.11, 12.42
        );

        INSERT INTO claim_line (
        id, claim_id, line_number, cpt_code, description, billed_amount, allowed_amount,
        deductible_applied, copay_applied, coinsurance_applied, plan_paid, member_responsibility
        ) VALUES (
        tsg_claim_line_id_16, tsg_claim_id_04, 8, 'C9791', 'MRI hyperpolarized xenon129',
        109.39, 204.63, 287.94, 372.92, 106.03, 145.15, 83.93
        );

        INSERT INTO claim_line (
        id, claim_id, line_number, cpt_code, description, billed_amount, allowed_amount,
        deductible_applied, copay_applied, coinsurance_applied, plan_paid, member_responsibility
        ) VALUES (
        tsg_claim_line_id_17, tsg_claim_id_05, 10, '70300', 'X-ray exam of teeth',
        47.41, 411.80, 296.92, 382.91, 161.54, 369.95, 49.35
        );

        INSERT INTO claim_line (
        id, claim_id, line_number, cpt_code, description, billed_amount, allowed_amount,
        deductible_applied, copay_applied, coinsurance_applied, plan_paid, member_responsibility
        ) VALUES (
        tsg_claim_line_id_18, tsg_claim_id_06, 12, '77431', 'Radiation therapy management',
        276.97, 390.97, 187.51, 99.11, 447.58, 108.67, 281.61
        );

        INSERT INTO claim_line (
        id, claim_id, line_number, cpt_code, description, billed_amount, allowed_amount,
        deductible_applied, copay_applied, coinsurance_applied, plan_paid, member_responsibility
        ) VALUES (
        tsg_claim_line_id_19, tsg_claim_id_07, 14, '93888', 'Intracranial study',
        157.97, 211.12, 215.66, 372.74, 158.92, 99.79, 405.65
        );

        INSERT INTO claim_line (
        id, claim_id, line_number, cpt_code, description, billed_amount, allowed_amount,
        deductible_applied, copay_applied, coinsurance_applied, plan_paid, member_responsibility
        ) VALUES (
        tsg_claim_line_id_20, tsg_claim_id_08, 16, 'U0002', 'Covid-19 lab test non-cdc',
        81.81, 126.24, 409.60, 410.37, 109.85, 388.69, 21.62
        );

        INSERT INTO claim_line (
        id, claim_id, line_number, cpt_code, description, billed_amount, allowed_amount,
        deductible_applied, copay_applied, coinsurance_applied, plan_paid, member_responsibility
        ) VALUES (
        tsg_claim_line_id_21, tsg_claim_id_09, 18, '71552', 'Mri chest w/o & w/dye',
        339.02, 463.21, 130.16, 201.38, 333.83, 323.90, 303.38
        );

        INSERT INTO claim_line (
        id, claim_id, line_number, cpt_code, description, billed_amount, allowed_amount,
        deductible_applied, copay_applied, coinsurance_applied, plan_paid, member_responsibility
        ) VALUES (
        tsg_claim_line_id_22, tsg_claim_id_10, 20, '73020', 'X-ray exam of shoulder',
        87.41, 295.46, 149.02, 41.25, 113.37, 267.33, 466.69
        );

        INSERT INTO claim_line (
        id, claim_id, line_number, cpt_code, description, billed_amount, allowed_amount,
        deductible_applied, copay_applied, coinsurance_applied, plan_paid, member_responsibility
        ) VALUES (
        tsg_claim_line_id_23, tsg_claim_id_11, 22, '90913', 'Bfb training ea addl 15 min',
        245.97, 357.30, 87.77, 344.36, 411.35, 404.52, 479.14
        );

        INSERT INTO claim_line (
        id, claim_id, line_number, cpt_code, description, billed_amount, allowed_amount,
        deductible_applied, copay_applied, coinsurance_applied, plan_paid, member_responsibility
        ) VALUES (
        tsg_claim_line_id_24, tsg_claim_id_12, 24, 'A9594', 'Gallium ga-68 psma-11 ucla',
        210.23, 354.36, 257.63, 363.79, 37.86, 193.83, 164.91
        );
    END;


    -- Code Block: Seed (x12) Claim Status Events (to match Claims)
    BEGIN
        INSERT INTO claim_status_event (id,claim_id,status,occurred_at,note) VALUES (
        tsg_claim_status_event_id_01, tsg_claim_id_01, 'SUBMITTED', NOW(), 'A great note 01'
        );

        INSERT INTO claim_status_event (id,claim_id,status,occurred_at,note) VALUES (
        tsg_claim_status_event_id_02, tsg_claim_id_02, 'IN_REVIEW', NOW(), 'A great note 02'
        );

        INSERT INTO claim_status_event (id,claim_id,status,occurred_at,note) VALUES (
        tsg_claim_status_event_id_03, tsg_claim_id_03, 'DENIED', NOW(), 'A great note 03'
        );

        INSERT INTO claim_status_event (id,claim_id,status,occurred_at,note) VALUES (
        tsg_claim_status_event_id_04, tsg_claim_id_04, 'SUBMITTED', NOW(), 'A great note 04'
        );

        INSERT INTO claim_status_event (id,claim_id,status,occurred_at,note) VALUES (
        tsg_claim_status_event_id_05, tsg_claim_id_05, 'PAID', NOW(), 'A great note 05'
        );

        INSERT INTO claim_status_event (id,claim_id,status,occurred_at,note) VALUES (
        tsg_claim_status_event_id_06, tsg_claim_id_06, 'PAID', NOW(), 'A great note 06'
        );

        INSERT INTO claim_status_event (id,claim_id,status,occurred_at,note) VALUES (
        tsg_claim_status_event_id_07, tsg_claim_id_07, 'IN_REVIEW', NOW(), 'A great note 07'
        );

        INSERT INTO claim_status_event (id,claim_id,status,occurred_at,note) VALUES (
        tsg_claim_status_event_id_08, tsg_claim_id_08, 'SUBMITTED', NOW(), 'A great note 08'
        );

        INSERT INTO claim_status_event (id,claim_id,status,occurred_at,note) VALUES (
        tsg_claim_status_event_id_09, tsg_claim_id_09, 'PROCESSED', NOW(), 'A great note 09'
        );

        INSERT INTO claim_status_event (id,claim_id,status,occurred_at,note) VALUES (
        tsg_claim_status_event_id_10, tsg_claim_id_10, 'DENIED', NOW(), 'A great note 10'
        );

        INSERT INTO claim_status_event (id,claim_id,status,occurred_at,note) VALUES (
        tsg_claim_status_event_id_11, tsg_claim_id_11, 'PROCESSED', NOW(), 'A great note 11'
        );

        INSERT INTO claim_status_event (id,claim_id,status,occurred_at,note) VALUES (
        tsg_claim_status_event_id_12, tsg_claim_id_12, 'IN_REVIEW', NOW(), 'A great note 12'
        );
    END;
END

-- End the SQL String-Literal (PL/pgSQL version)
-- $$ LANGUAGE plpgsql;

-- End the SQL String-Literal (Spring version)
$$ LANGUAGE plpgsql;;