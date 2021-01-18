# ERS (Expense Reimbursement System )

The Expense Reimbursement System (ERS) will manage the process of reimbursing employees for expenses incurred while on company time. All employees in the company can login and submit requests for reimbursement and view their past tickets and pending requests. Finance managers can log in and view all reimbursement requests and past history for all employees in the company. Finance managers are authorized to approve and deny requests for expense reimbursement.

Reimbursement Types
Employees must select the type of reimbursement as: LODGING, TRAVEL, FOOD, or OTHER.





## Technologies Used

-Frontend : Html, CSS, Javascript
-Backend : Java, Maven, Servlet, JDBC, Tomcat
-Database : PostgreSQL



## Features
-Employee can log into the system.View all submitted request in the past.Create new reimbursment request.
-Finance Manager can log in into the system. View All submitted request.
-finance Manager can filter request based on status :Approved, Pending or Denied.
-Finance Manager can Approve or reject the request

## Getting Started

-Clone the project into your desired folder using below command in gitbash.
```gitbash
git clone https://github.com/2011Canada/project-1-raviraj845.git
```
- Create Postgres database using the script provided in script.sql file.
- Enter the data into the table. Script to enter the data in table is also provided in script.sql file.


## Usage

-if you enter the username and password for employee, it will open the Employee Screen.
-on the employee screen , it will show All past request.It also has a button to create new request.
-click on create new request button, it will open a form to submit a new request. fill out the form and click on submit to submit the request.
-Entering username and password for manager will open a manager's screen.
-it will show All the requests submitted by employees. Manager can filter the request based on a status from the selecter.
-Manager click on a a approve or deny the request by pressing the button and it will update the status accordingly.



## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[MIT](https://choosealicense.com/licenses/mit/)
