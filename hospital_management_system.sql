CREATE DATABASE hospital_management_system;
CREATE TABLE procedureDetails(
    procedure_code INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    cost DECIMAL(10, 2) NOT NULL
);
CREATE TABLE block(
    blockfloor INT,
    blockcode INT,
    PRIMARY KEY (blockfloor, blockcode)
);
CREATE TABLE room(
    room_number INT PRIMARY KEY AUTO_INCREMENT,
    roomtype VARCHAR(50) NOT NULL,
    blockfloor INT,
    blockcode INT,
    unavailable BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (blockfloor, blockcode) REFERENCES block(blockfloor, blockcode)
);
CREATE TABLE physician(
    employee_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    position VARCHAR(100),
    social_security_number BIGINT
);
CREATE TABLE department(
    department_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    head INT,
    FOREIGN KEY (head) REFERENCES physician(employee_id)
);
CREATE TABLE medication(
    medication_code INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    brand VARCHAR(255),
    description TEXT
);
CREATE TABLE affiliated_with(
    physician_id INT,
    department_id INT,
    primary_affiliation BOOLEAN,
    PRIMARY KEY(physician_id, department_id),
    FOREIGN KEY(physician_id) REFERENCES physician(employee_id),
    FOREIGN KEY(department_id) REFERENCES department(department_id)
);
CREATE TABLE patient(
    social_security_number BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    address TEXT,
    phone VARCHAR(15),
    insurance_id INT,
    primary_care_physician INT,
    FOREIGN KEY(primary_care_physician) REFERENCES physician(employee_id)
);
CREATE TABLE stay(
    stay_id INT PRIMARY KEY AUTO_INCREMENT,
    patient_id BIGINT,
    room_id INT,
    start_time DATETIME,
    end_time DATETIME,
    FOREIGN KEY(patient_id) REFERENCES patient(social_security_number),
    FOREIGN KEY(room_id) REFERENCES room(room_number)
);
CREATE TABLE trained_in(
    physician_id INT,
    treatment INT,
    certification_date DATE,
    certification_expires DATE,
    PRIMARY KEY (physician_id, treatment),
    FOREIGN KEY (physician_id) REFERENCES physician(employee_id),
    FOREIGN KEY(treatment) REFERENCES procedureDetails(procedure_code)
);
CREATE TABLE nurse(
    employee_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    position VARCHAR(100),
    registered BOOLEAN,
    social_security_number BIGINT
);
CREATE TABLE undergoes(
    patient_id BIGINT,
    procedure_details INT,
    stay_id INT,
    date_ DATETIME,
    physician_id INT,
    assisting_nurse_id INT,
    PRIMARY KEY(patient_id, procedure_details, stay_id, date_),
    FOREIGN KEY (patient_id) REFERENCES patient(social_security_number),
    FOREIGN KEY(procedure_details) REFERENCES procedureDetails(procedure_code),
    FOREIGN KEY (stay_id) REFERENCES stay(stay_id),
    FOREIGN KEY (physician_id) REFERENCES physician(employee_id),
    FOREIGN KEY(assisting_nurse_id) REFERENCES nurse(employee_id)
);
CREATE TABLE appointment(
    appointment_id INT PRIMARY KEY AUTO_INCREMENT,
    patient_id BIGINT,
    prep_nurse_id INT,
    physician_id INT,
    start_time DATETIME,
    end_time DATETIME,
    examination_room VARCHAR(50),
    FOREIGN KEY(patient_id) REFERENCES patient(social_security_number),
    FOREIGN KEY(prep_nurse_id) REFERENCES nurse(employee_id),
    FOREIGN KEY(physician_id) REFERENCES physician(employee_id)
);
CREATE TABLE prescribes(
    physician_id INT,
    patient_id BIGINT,
    medication_code INT,
    prescription_date DATETIME,
    appointment_id INT,
    dose VARCHAR(255),
    PRIMARY KEY(
        physician_id,
        patient_id,
        medication_code,
        prescription_date
    ),
    FOREIGN KEY(physician_id) REFERENCES physician(employee_id),
    FOREIGN KEY(patient_id) REFERENCES patient(social_security_number),
    FOREIGN KEY(medication_code) REFERENCES medication(medication_code),
    FOREIGN KEY(appointment_id) REFERENCES appointment(appointment_id)
);
CREATE TABLE on_call(
    nurse_id INT,
    blockfloor INT,
    blockcode INT,
    oncall_start DATETIME,
    oncall_end DATETIME,
    PRIMARY KEY(
        nurse_id,
        blockfloor,
        blockcode,
        oncall_start,
        oncall_end
    ),
    FOREIGN KEY(nurse_id) REFERENCES nurse(employee_id),
    FOREIGN KEY(blockfloor, blockcode) REFERENCES block(blockfloor, blockcode)
);
--data insert
INSERT INTO procedureDetails (name, cost)
VALUES ('MRI Scan', 500.00),
    ('CT Scan', 700.00),
    ('X-Ray', 100.00),
    ('Blood Test', 50.00),
    ('Ultrasound', 300.00);
INSERT INTO block (blockfloor, blockcode)
VALUES (1, 101),
    (2, 102),
    (3, 103),
    (4, 104),
    (5, 105);
INSERT INTO room (roomtype, blockfloor, blockcode, unavailable)
VALUES ('Single', 1, 101, FALSE),
    ('Double', 2, 102, TRUE),
    ('ICU', 3, 103, FALSE),
    ('Private', 4, 104, TRUE),
    ('Suite', 5, 105, FALSE);
INSERT INTO physician (name, position, social_security_number)
VALUES ('Dr. John Smith', 'Cardiologist', 123456789),
    ('Dr. Jane Doe', 'Neurologist', 987654321),
    (
        'Dr. Albert White',
        'Orthopedic Surgeon',
        456789123
    ),
    ('Dr. Emily Green', 'Pediatrician', 789123456),
    ('Dr. Michael Brown', 'Oncologist', 321654987);
INSERT INTO department (name, head)
VALUES ('Cardiology', 1),
    ('Neurology', 2),
    ('Orthopedics', 3),
    ('Pediatrics', 4),
    ('Oncology', 5);
INSERT INTO medication (name, brand, description)
VALUES (
        'Paracetamol',
        'Panadol',
        'Pain relief medication'
    ),
    (
        'Ibuprofen',
        'Advil',
        'Anti-inflammatory medication'
    ),
    (
        'Atorvastatin',
        'Lipitor',
        'Cholesterol-lowering drug'
    ),
    ('Metformin', 'Glucophage', 'Diabetes medication'),
    (
        'Lisinopril',
        'Zestril',
        'Blood pressure control'
    );
INSERT INTO affiliated_with (physician_id, department_id, primary_affiliation)
VALUES (1, 1, TRUE),
    (2, 2, TRUE),
    (3, 3, TRUE),
    (4, 4, TRUE),
    (5, 5, TRUE);
INSERT INTO patient (
        name,
        address,
        phone,
        insurance_id,
        primary_care_physician
    )
VALUES (
        'Alice Johnson',
        '123 Main St',
        '555-1234',
        1001,
        1
    ),
    (
        'Bob Williams',
        '456 Elm St',
        '555-5678',
        1002,
        2
    ),
    (
        'Charlie Brown',
        '789 Oak St',
        '555-9876',
        1003,
        3
    ),
    (
        'David Clark',
        '321 Pine St',
        '555-6543',
        1004,
        4
    ),
    ('Eve Davis', '654 Maple St', '555-4321', 1005, 5);
INSERT INTO stay (patient_id, room_id, start_time, end_time)
VALUES (
        1,
        1,
        '2024-09-01 10:00:00',
        '2024-09-03 12:00:00'
    ),
    (
        2,
        2,
        '2024-09-05 09:00:00',
        '2024-09-06 14:00:00'
    ),
    (
        3,
        3,
        '2024-09-07 08:00:00',
        '2024-09-08 16:00:00'
    ),
    (
        4,
        4,
        '2024-09-10 11:00:00',
        '2024-09-12 15:00:00'
    ),
    (
        5,
        5,
        '2024-09-13 12:00:00',
        '2024-09-14 10:00:00'
    );
INSERT INTO trained_in (
        physician_id,
        treatment,
        certification_date,
        certification_expires
    )
VALUES (1, 1, '2020-01-01', '2025-01-01'),
    (2, 2, '2020-02-01', '2025-02-01'),
    (3, 3, '2020-03-01', '2025-03-01'),
    (4, 4, '2020-04-01', '2025-04-01'),
    (5, 5, '2020-05-01', '2025-05-01');
INSERT INTO nurse (
        name,
        position,
        registered,
        social_security_number
    )
VALUES (
        'Nurse Emma Johnson',
        'Head Nurse',
        TRUE,
        987321654
    ),
    (
        'Nurse Jack Wilson',
        'Staff Nurse',
        TRUE,
        123789456
    ),
    (
        'Nurse Olivia Lee',
        'Registered Nurse',
        TRUE,
        456123789
    ),
    (
        'Nurse Liam Harris',
        'Registered Nurse',
        FALSE,
        789456123
    ),
    (
        'Nurse Sophia Clark',
        'Staff Nurse',
        TRUE,
        321987654
    );
INSERT INTO undergoes (
        patient_id,
        procedure_details,
        stay_id,
        date_,
        physician_id,
        assisting_nurse_id
    )
VALUES (1, 1, 1, '2024-09-01 11:00:00', 1, 1),
    (2, 2, 2, '2024-09-05 10:00:00', 2, 2),
    (3, 3, 3, '2024-09-07 09:00:00', 3, 3),
    (4, 4, 4, '2024-09-10 12:00:00', 4, 4),
    (5, 5, 5, '2024-09-13 13:00:00', 5, 5);
INSERT INTO appointment (
        patient_id,
        prep_nurse_id,
        physician_id,
        start_time,
        end_time,
        examination_room
    )
VALUES (
        1,
        1,
        1,
        '2024-09-02 10:00:00',
        '2024-09-02 10:30:00',
        'Room A'
    ),
    (
        2,
        2,
        2,
        '2024-09-06 11:00:00',
        '2024-09-06 11:30:00',
        'Room B'
    ),
    (
        3,
        3,
        3,
        '2024-09-08 12:00:00',
        '2024-09-08 12:30:00',
        'Room C'
    ),
    (
        4,
        4,
        4,
        '2024-09-12 09:00:00',
        '2024-09-12 09:30:00',
        'Room D'
    ),
    (
        5,
        5,
        5,
        '2024-09-14 10:00:00',
        '2024-09-14 10:30:00',
        'Room E'
    );
INSERT INTO prescribes (
        physician_id,
        patient_id,
        medication_code,
        prescription_date,
        appointment_id,
        dose
    )
VALUES (1, 1, 1, '2024-09-02 10:00:00', 1, '500mg'),
    (2, 2, 2, '2024-09-06 11:00:00', 2, '200mg'),
    (3, 3, 3, '2024-09-08 12:00:00', 3, '10mg'),
    (4, 4, 4, '2024-09-12 09:00:00', 4, '500mg'),
    (5, 5, 5, '2024-09-14 10:00:00', 5, '50mg');
INSERT INTO on_call (
        nurse_id,
        blockfloor,
        blockcode,
        oncall_start,
        oncall_end
    )
VALUES (
        1,
        1,
        101,
        '2024-09-01 08:00:00',
        '2024-09-01 16:00:00'
    ),
    (
        2,
        2,
        102,
        '2024-09-05 08:00:00',
        '2024-09-05 16:00:00'
    ),
    (
        3,
        3,
        103,
        '2024-09-07 08:00:00',
        '2024-09-07 16:00:00'
    ),
    (
        4,
        4,
        104,
        '2024-09-10 08:00:00',
        '2024-09-10 16:00:00'
    ),
    (
        5,
        5,
        105,
        '2024-09-13 08:00:00',
        '2024-09-13 16:00:00'
    );