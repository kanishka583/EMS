# EMS
Employee Management System
 
 
 
STS Project (SRI Tech Solutions) 

Requirement Document

**1) Full Profile Microservice   **

Purpose: Employee details storage  

Operations :   
* Register new Employee 
* Get employee by empid 
* Update employee details 
* List all employees 
* Update isCurrentEmp status - for BGV micro service 
* Get All onBench=true and isCurrentEmp=true employee id’s - for Notification micro service 

**2) History Microservice   **

Purpose: Previous company details storage

Operations :   
* Register previous company details ( Call BGV endpoint on successful data entry ) 
* Update status column ( for BGV micro service ) 
* List all previous company details (Optional) 
* Get previous company details by empid (Optional)  * Get Empid’s of status=failed
 
  
**3) Background Verification Microservice ( BGV )   **

Purpose : Verification of Employee experience   

Operations :    
* Validate companies that are present in the history table with Company enum class where we store valid company names , if the companies in the history table are there in the enum class then call update status column endpoint in History micro service to set the status of the verification to either started/completed/ failed for a particular empid and company entry and also in Full Profile Microservice use the endpoint to update isCurrentEmp=true, if background verification status is set to completed.

**4) Notification Microservice    **

Purpose : To notify HR about project assignment or termination and also to notify employee if history micro service is empty. 
 

Use cases : 
 
* Call Full profile micro service 6th endpoint to fetch all empid’s to notify HR on project assignment. 
* Call History micro service 5th endpoint to notify HR about termination 
* Send notification to employees whose entries are empty in History table to remind the employees to fill their history data.
 

**Data Modeling**

**Full profile Microservice :**


Table :

Employee { 

empId varchar primary-key, 

empName varchar, 

empEmail varchar,

empPhnNo long, 

empGender varchar,

empSalary double, 

empdesignation varchar, 

empExperience int, 

empDepartment varchar, 

empDOB Date, 

empSkills varchar, 

empAddress List\<Address>,

isCurrentEmp boolean, 

isOnBench boolean 

} 


Constraints : 
 
==> empid should start with “sts-“ and it should be 4 digit number and increment by 2. 

Ex : sts-1234 , sts-1236 etc  

==> All the fields should be “not null”. 

==> By default “isCurrentEmp” should be false and isOnbench should be true.

==> Department should be enum. 

Department {“IT”,”HR”,”Management”, “Payroll”, “Transport”} 

==> Packages structure : com.sts.<modulename>.employee 

 Example : com.sts.fullprofile.employee 
==> Calculate Designation based on experience.
experience <= 2.0
"Programmer Analyst Trainee"
experience > 2.0 And experience < 4.0
"Programmer Analyst"
experience > 4.0 And experience < 6.0
"Associate"
experience > 6.0 And experience < 10.0
"Senior Associate"
experience > 10.0
"Manager"

==> Address {
addressId int primary key,
empId varchar foreign key,
addressType enum, [“TEMPORARY”, “PERMANENT”,”OFFICE”] zipCode long,
addressLine1 varchar,
addressLine2 varchar,
landmark varchar,
}

==> Except landmark all the fields should be “not null”.


History Micro Service :

Table :

History {
empid string foreign key, 

prevCompanyName string, 

prevCompanyAddress Address, 

prevCompanyStartDate Date, 

prevCompanyEndDate Date, 

prevCompanyHREmail string, 

prevCompanyDesignaXon string, 

prevCompanyEmpid string primary key, 

BGVstatus boolean }

==> set BGVstatus to false iniXally. 

==> All the fields should be “not null”.


Company Enum class { “MICROSOFT”, “AMAZON”,
“WELLS FARGO”, “JPMC”,
“MORGAN STANLEY”, “VIRTUSA”, “TCS”, “INFOSYS”, “WIPRO”, “ACCENTURE”, “CAPGEMINI”}
             
 
