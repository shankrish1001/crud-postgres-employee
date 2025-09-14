CREATE TABLE public.employees (
    employee_id int8 DEFAULT nextval('employee_employee_id_seq'::regclass) NOT NULL,
    employee_name varchar(255) NOT NULL,
    employee_email varchar(255) NOT NULL,
    employee_dept varchar(255) NOT NULL,
    employee_age int4 NULL,
    log_date date DEFAULT CURRENT_DATE NULL,
    CONSTRAINT employee_pkey PRIMARY KEY (employee_id)
);


-- Enter into DB
docker exec -it test-postgres-db psql -U postgres -d employee_db

-- Check if function exists and has correct parameter type
SELECT proname, proargtypes FROM pg_proc WHERE proname = 'get_employee_by_email';

-- PostgreSQL stored procedure (or function)
CREATE OR REPLACE FUNCTION get_employee_by_email(e_email varchar(255))
RETURNS TABLE(employee_id int8, employee_name varchar(255), employee_email varchar(255), employee_dept varchar(255), employee_age int4) AS $$
BEGIN
RETURN QUERY
SELECT employees.employee_id, employees.employee_name, employees.employee_email, employees.employee_dept, employees.employee_age FROM employees WHERE employees.employee_email = e_email;
END;
$$ LANGUAGE plpgsql;

SELECT * FROM get_employee_by_email('shan@gmail.com');


POST:
http://localhost:8080/api/employees
{
    "employeeName":"Das2",
    "employeeEmail":"das2@gmail",
    "employeeDept":"CSE",
    "employeeAge":20
}



