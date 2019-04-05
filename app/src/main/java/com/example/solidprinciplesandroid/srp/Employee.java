package com.example.solidprinciplesandroid.srp;

public class Employee {
	double calculatePay() {
		//business logic for calculating payment
		return 0;
	}
	
	Employee save(Employee emp) {
		//business logic for storing employee information
		return emp;
	}
}
/*
 * So this call has 2 responsibility, So this doesn't follow the SRP
 * The solution is there should be two classes one for each responsibility
 */
class EmployeePay{
	double calculatePay() {
		//business logic for calculating payment
		return 0;
	}
}
class EmployeeRepo{
	Employee save(Employee emp) {
		//business logic for storing employee information
		return emp;
	}
}
