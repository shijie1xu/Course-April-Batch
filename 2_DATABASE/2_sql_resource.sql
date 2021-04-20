
################################# SCHEMA #################################

DROP SCHEMA IF EXISTS course;
CREATE SCHEMA IF NOT EXISTS course;

USE course;


################################# EMPLOYEE TABLE #################################
DROP TABLE IF EXISTS employee;
CREATE TABLE employee(  
  empid		INT,  
  name		VARCHAR(10),  
  job		VARCHAR(9),  
  manager	INT,  
  hiredate	DATE,  
  salary	DECIMAL(7,2),
  deptid	INT,  
  CONSTRAINT pk_emp PRIMARY KEY (empid) 
);


################################# DEPARTMENT TABLE #################################
DROP TABLE IF EXISTS department;
CREATE TABLE department(  
  deptid	INT,  
  name		VARCHAR(14),  
  loc		VARCHAR(13),  
  CONSTRAINT pk_dept PRIMARY KEY (deptid)  
);

################################# SALARY REPORT TABLE #################################
#Job, Department, salmin, salmax, salavg, saltotal, empcount, updatetime###############
DROP TABLE IF EXISTS salary_report;
CREATE TABLE salary_report(  
  job		VARCHAR(9),
  department VARCHAR(14), 
  salmin	DECIMAL(7,2),
  salmax	DECIMAL(7,2),
  salavg	DECIMAL(7,2),
  saltotal	DECIMAL(8,2),
  empcount	INT,
  updatetime Date,
  CONSTRAINT pk_salary_report PRIMARY KEY (job, department) 
);


INSERT INTO department
VALUES
(10, 'ACCOUNTING', 'NEW YORK'),
(20, 'RESEARCH', 'DALLAS'),
(30, 'SALES', 'CHICAGO'),
(40, 'OPERATIONS', 'BOSTON'),
(50, 'OUTSOURCE', 'LONDON');

SELECT * FROM department;

INSERT INTO employee
VALUES
(7839, 'KING', 'PRESIDENT', null, STR_TO_DATE('17-11-1981','%d-%m-%Y'), 5000, 10),
(7698, 'BLAKE', 'MANAGER', 7839, STR_TO_DATE('1-5-1981','%d-%m-%Y'), 2850, 30),
(7782, 'CLARK', 'MANAGER', 7839, STR_TO_DATE('9-6-1981','%d-%m-%Y'), 2450, 10),
(7566, 'JONES', 'MANAGER', 7839, STR_TO_DATE('2-4-1981','%d-%m-%Y'), 2975, 20),
(7788, 'SCOTT', 'ANALYST', 7566, STR_TO_DATE('13-7-1987','%d-%m-%Y'), 3000, 20),
(7902, 'FORD', 'ANALYST', 7566, STR_TO_DATE('3-12-1981','%d-%m-%Y'), 3000, 20),
(7369, 'SMITH', 'CLERK', 7902, STR_TO_DATE('17-12-1980','%d-%m-%Y'), 800, 20),
(7989, 'BOND', 'MANAGER', 7839, STR_TO_DATE('1-5-1981','%d-%m-%Y'), 2850, 90),
(7499, 'ALLEN', 'SALESMAN', 7698, STR_TO_DATE('20-2-1981','%d-%m-%Y'), 1600, 30),
(7521, 'WARD', 'SALESMAN', 7698, STR_TO_DATE('22-2-1981','%d-%m-%Y'), 1250, 30),
(7654, 'MARTIN', 'SALESMAN', 7698, STR_TO_DATE('28-9-1981','%d-%m-%Y'), 1250, 30),
(7844, 'TURNER', 'SALESMAN', 7698, STR_TO_DATE('8-9-1981','%d-%m-%Y'), 1500, 30),
(7876, 'ADAMS', 'CLERK', 7788, STR_TO_DATE('13-7-1987', '%d-%m-%Y'), 1100, 20),
(7900, 'JAMES', 'CLERK', 7698, STR_TO_DATE('3-12-1981','%d-%m-%Y'), 950, 30),
(7430, 'WAYNE', 'CLERK', 7698, STR_TO_DATE('3-12-1981','%d-%m-%Y'), 950, 70),
(7934, 'MILLER', 'CLERK', 7782, STR_TO_DATE('23-1-1982','%d-%m-%Y'), 1300, 10);

SELECT * FROM employee order by empid;


################################# VIEW #################################
DROP VIEW IF EXISTS employee_location_view;
CREATE VIEW employee_location_view
AS
SELECT e.empid, e.name, d.loc
FROM employee e
JOIN department d
on e.deptid = d.deptid;

########### MATERIALIZED VIEW  - NOT SUPPORTED IN MYSQL ################
## SYNTAX EXAMPLE ##
# CREATE MATERIALIZED VIEW employee_location_mv
# REFRESH ON DEMAND 
# START WITH sysdate NEXT sysdate + 1
# AS
# SELECT name, salary
# FROM employee;
########################################################################




################################# STORED PROCEDURE #################################
# Requirement: 
# 1) remove corresponding records from salary_report
# 2) generate new records to salary_report
####################################################################################
USE course;
DROP PROCEDURE IF EXISTS spGenerateReport;

DELIMITER $$
CREATE PROCEDURE spGenerateReport(
    IN job_in VARCHAR(9)
)
BEGIN
	TRUNCATE TABLE salary_report;
    
	INSERT INTO salary_report
    SELECT e.job, d.name, MIN(e.salary), MAX(e.salary),
    AVG(e.salary), SUM(e.salary), COUNT(*), CURDATE()
    FROM employee e
	JOIN department d
    ON e.deptid = d.deptid
    AND UPPER(e.job) = UPPER(job_in)
    GROUP BY e.job, d.name;
END $$
DELIMITER ;

CALL spGenerateReport('CLERK');
SELECT * FROM salary_report;
