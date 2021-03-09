package com.cognizant.ormlearn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.model.Department;
import com.cognizant.ormlearn.model.Employee;
import com.cognizant.ormlearn.model.Skill;
import com.cognizant.ormlearn.service.CountryService;
import com.cognizant.ormlearn.service.DepartmentService;
import com.cognizant.ormlearn.service.EmployeeService;
import com.cognizant.ormlearn.service.SkillService;
import com.cognizant.ormlearn.service.exception.CountryNotFoundException;

import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;

@SpringBootApplication
public class OrmLearnApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);
//	private static CountryService countryService;
	private static EmployeeService employeeService;
	private static DepartmentService depertmentService;
	private static SkillService skillService;
	
	public static void main(String[] args) throws CountryNotFoundException, ParseException {
		ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);
//		LOGGER.info("inside main");
//		countryService = context.getBean(CountryService.class);
		employeeService = context.getBean(EmployeeService.class);
		depertmentService = context.getBean(DepartmentService.class);
		skillService = context.getBean(SkillService.class);
//		Country country = context.getBean(Country.class);
//		testGetAllCountries();
//		getAllCountriesTest();
//		testAddCountry(country);
//		testUpdateCountry();
//		testDeleteCountry();
//		testSearchByPlaceHolder();
//		testNameStartWith();
//		testSortCountry();
		testGetEmployee();
		testAddEmployee();
		testUpdateEmployee();
		testGetDepartment();
		testAddSkillToEmployee();
		
	}
	
//	private static void testGetAllCountries()
//	{
//		LOGGER.info("Start");
//		List<Country> countries = countryService.getAllCountries();
//		LOGGER.debug("countries={}", countries);
//		LOGGER.info("End");
//	}
	
//	private static void getAllCountriesTest() throws CountryNotFoundException
//	{
//		LOGGER.info("Start");
//		Country country = countryService.findCountryByCode("IN");
//		LOGGER.debug("country={}", country);
//		LOGGER.info("End");
//		
//	}
	
//	private static void testAddCountry(Country country)
//	{
//		LOGGER.info("Start");
//		country.setCode("SL");
//		country.setName("Shit Lund Cunt");
//		countryService.addCountry(country);
//		LOGGER.info("End");
//	}
	
//	private static void testUpdateCountry()
//	{
//		LOGGER.info("Start");
//		countryService.updateCountry("US", "United States");
//		LOGGER.info("End");
//	}
	
//	private static void testDeleteCountry()
//	{
//		LOGGER.info("Start");
//		countryService.deleteCountry("SL");
//		LOGGER.info("End");
//	}
	
//	private static void testSearchByPlaceHolder() 
	//{
//	
//	LOGGER.info("Start");
//	List<Country> countries = countryService.searchByPlaceHolder("di");
//	LOGGER.debug("countries={}", countries);
//	LOGGER.info("End");
//}

//private static void testNameStartWith() {
//	LOGGER.info("Start");
//	List<Country> countries = countryService.searchByStartingLetter("I");
//	LOGGER.debug("countries={}", countries);
//	LOGGER.info("End");
//}

//private static void testSortCountry() {
//	LOGGER.info("Start");
//	List<Country> countries = countryService.searchByStartingLetter("Ind");
//	LOGGER.debug("countries={}", countries);
//	LOGGER.info("end");
//}
	
	private static void testGetEmployee()
	{
		LOGGER.info("Start");
		Employee employee = employeeService.get(1);
		LOGGER.debug("Employee={}", employee);
		LOGGER.debug("Department={}", employee.getDepartment());
		LOGGER.debug("Skills={}", employee.getSkills());
		LOGGER.info("end");
	}

	
	private static void testAddEmployee() throws ParseException
	{
		LOGGER.info("Start");
		Employee employee = new Employee("Jack", 25000.00, true, parseDate("1999-01-19"));
		Department department = depertmentService.get(1);
		employee.setDepartment(department);
		employeeService.save(employee);
		LOGGER.info("End");
	}
	private static Date parseDate(String string) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
	     
	      Date date = formatter.parse(string);
	      return date;
	}
	
	private static void testUpdateEmployee()
	{
		LOGGER.info("start");
		int employeeId = 1;
		Employee employee = employeeService.get(employeeId);
		employee.setDepartment(new Department("Trainer"));
		employeeService.save(employee);
		LOGGER.debug("Employee={}", employee);
		LOGGER.debug("Department={}", employee.getDepartment());
		LOGGER.info("end");
	}


	
	
	private static void testGetDepartment()
	{
		LOGGER.info("Start");
		int departmentId = 4;
		Department department = depertmentService.get(departmentId);
		LOGGER.debug("Department={}", department);
		LOGGER.debug("Employee={}", department.getEmployees());
		LOGGER.info("End");
	}
	
	private static void testAddSkillToEmployee()
	{
		LOGGER.info("Start");
		int employeeId = 1;
		Employee employee = employeeService.get(employeeId);
		Set<Skill> skills = new HashSet<>();
		Skill skill = new Skill("JAVA");
		Skill skill2 = new Skill("MySQL");
		skills.add(skill);
		skills.add(skill2);
		skillService.save(skill);
		skillService.save(skill2);
		employee.setSkills(skills);
		employeeService.save(employee);
		LOGGER.info("End");
	}
	
	private static void testGetSkills()
	{
		LOGGER.info("start");
		int employeeId = 1;
		Employee employee = employeeService.get(employeeId);
		LOGGER.debug("Skills={}", employee.getSkills());
		LOGGER.info("end");
	}
	public static void testGetAllPermanentEmployees() 
	{
		LOGGER.info("Start");
		List<Employee> employees = employeeService.getAllPermanentEmployees();
		LOGGER.debug("Permanent Employees:{}", employees);
		employees.forEach(e -> LOGGER.debug("Skills:{}", e.getSkills()));
		LOGGER.info("End");
	}
	public static void testGetAverageSalary()
	{
		LOGGER.info("Start");
		int deptId = 4;
		double avgSalary = employeeService.getAverageSalary(deptId);
		LOGGER.debug("Average Salary", avgSalary);
		LOGGER.info("End");
	}
	public static void testGetAllEmployees()
	{
		LOGGER.info("Start");
		List<Employee> employees = employeeService.getAllEmployeesNative();
		LOGGER.debug("Employees:{}", employees);
		LOGGER.info("End");
	}


}
