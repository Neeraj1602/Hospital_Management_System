CREATE DATABASE hospital;
USE hospital;

CREATE TABLE patients (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    age INT,
    gender VARCHAR(10),
    diagnosis VARCHAR(255)
);

CREATE TABLE doctors (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    specialization VARCHAR(100)
);

CREATE TABLE appointments (
    id INT PRIMARY KEY AUTO_INCREMENT,
    patient_id INT,
    doctor_id INT,
    appointment_date DATE,
    FOREIGN KEY (patient_id) REFERENCES patients(id),
    FOREIGN KEY (doctor_id) REFERENCES doctors(id)
);

INSERT INTO DOCTORS(name, specialization) VALUES ("Dr.Manish Yadav", "Physician");
INSERT INTO DOCTORS(name, specialization) VALUES ("Dr.Neeraj Gupta", "NeuroSurgeon");
INSERT INTO DOCTORS(name, specialization) VALUES ("Dr.Manisha Agrawal", "gynecologist");
INSERT INTO DOCTORS(name, specialization) VALUES ("Dr.Monu Pratap Singh", "Cardiologist");
INSERT INTO DOCTORS(name, specialization) VALUES ("Dr.Himanshu verma", "Orthopedist");
INSERT INTO DOCTORS(name, specialization) VALUES ("Dr.Piryanshu Shukla", "General surgery");