USE course;

-- 1 
SELECT DISTINCT  job FROM employee;

-- 2
SELECT name, salary, deptid FROM employee WHERE job = 'clerk' ORDER BY deptid, salary DESC; 

-- 3
SELECT	name, hiredate FROM employee WHERE job = 'manager';

-- 4
SELECT * FROM employee WHERE job IN ('clerk','analyst') ORDER BY job, name DESC;

-- 5
SELECT * FROM employee WHERE salary BETWEEN 2200 AND 4500;

-- 6
SELECT * FROM employee WHERE job NOT IN ('manager' , 'president') ORDER BY salary;

-- 7
SELECT * FROM employee WHERE salary > ( SELECT salary FROM employee WHERE name = 'blake');

-- 8
SELECT * FROM employee WHERE job = ( SELECT job FROM employee WHERE name = 'allen');

-- 8 alternative
SELECT * FROM employee a
JOIN employee b
ON a.job = b.job
AND b.name = 'allen';

-- 9
SELECT * FROM employee WHERE salary IN ( SELECT salary FROM employee WHERE name IN ('ford', 'smith'));

-- 10
SELECT a.*, b.name AS manager_name, b. hiredate AS manager_hiredate 
FROM employee a, employee b 
WHERE a.manager = b.empid 
AND a.hiredate < b.hiredate;

-- 11
SELECT name, MAX(salary)
FROM course.employee
GROUP BY deptid;

-- 12
SELECT e.name, IFNULL(d.name, "NA") AS department FROM employee e LEFT JOIN department d ON e.deptid = d.deptid;

-- 13
SELECT d.deptid, d.name, loc FROM employee e RIGHT JOIN department d ON e.deptid = d.deptid WHERE empid IS NULL;

SELECT *
FROM department 
WHERE deptid NOT IN (SELECT deptid FROM employee);

-- 14
SELECT salary FROM employee ORDER BY salary DESC LIMIT 1 OFFSET 1;

-- 14 alternative
SELECT MAX(salary)
FROM employee
WHERE salary NOT IN (SELECT MAX(salary) FROM employee);

-- 15
SELECT * FROM employee ORDER BY salary DESC LIMIT 1 OFFSET 2;

-- 16
SELECT job, SUM(salary) AS total_salary FROM employee GROUP BY job;

-- 17
SELECT d.deptid, d.name, IFNULL(AVG(salary), 0) AS average_salary FROM employee e RIGHT JOIN department d ON e.deptid = d.deptid GROUP BY d.deptid;

-- 18
SELECT d.deptid, d.name, AVG(salary) AS average_salary FROM employee e JOIN department d ON e.deptid = d.deptid GROUP BY d.deptid 
HAVING AVG(salary) >= 1000;

-- 19
SELECT COUNT(*), deptid, job FROM employee GROUP BY deptid, job ORDER BY deptid;

-- 20
SELECT d.name, d.loc, COUNT(*) AS count
FROM department d
JOIN employee e
ON e.deptid = d.deptid
GROUP BY d.deptid
HAVING COUNT(*)>=2;

-- 21
SELECT d.name, d.loc, count(*)
FROM employee e 
JOIN department d
ON e.deptid = d.deptid
WHERE e.job = 'CLERK' 
HAVING count(*)>2;

-- 22
SELECT d.name, COUNT(*) as count
FROM employee e
INNER JOIN department d on e.deptid = d.deptid
GROUP BY d.name
ORDER BY COUNT(*) DESC
LIMIT 1;

-- 23
SELECT * FROM employee WHERE UPPER(name) LIKE '%A%';

-- 24
SELECT * FROM employee WHERE dayofmonth(hiredate) < 15;

-- 25
-- 18

-- 26
SELECT CONCAT(name, '(', LOWER(job), ')') AS employee_job FROM employee;

-- NOT ACCETPTABLE IN mysql
-- SELECT NAME || "(" || LOWER(JOB) || ")"
-- FROM EMPLOYEE;

-- 27
SELECT name, MIN(salary) AS min_salary FROM employee GROUP BY deptid ORDER BY MIN(salary) DESC;

-- 28
SELECT e.name, MIN(hiredate) AS min_salary FROM employee e JOIN department d ON e.deptid = d.deptid GROUP BY d.deptid ORDER BY MIN(hiredate);

-- 29
SELECT * FROM employee WHERE year(hiredate) = 1981;

-- 30
SELECT d.name,
IFNULL(SUM(CASE WHEN e.hiredate <= "1981-05-01" THEN 1 END), 0) AS senior_count,
IFNULL(SUM(CASE WHEN e.hiredate > "1981-05-01" THEN 1 END), 0) AS junior_count
FROM employee e
JOIN department d
ON e.deptid = d.deptid
GROUP BY d.deptid;

USE course;
DELIMITER $$
DROP PROCEDURE IF EXISTS spTotalSalary;
CREATE PROCEDURE spTotalSalary(
IN depname VARCHAR(14),
OUT sum INT
)
BEGIN
SELECT SUM(e.salary) INTO sum
FROM employee e
JOIN department d
on e.deptid = d.deptid
WHERE depname = d.name;
END$$
DELIMITER ;

call spTotalSalary('ACCOUNTING',@sum);
SELECT @sum as sum;





