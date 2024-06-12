package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Employee;
import com.example.repository.EmployeeRepository;

/**
 * 従業員情報を操作するサービス.
 * 
 * @author igamasayuki
 *
 */
@Service
@Transactional
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	/**
	 * 従業員情報を全件取得します.
	 * 
	 * @return 従業員情報一覧
	 */
	public List<Employee> showList(Integer page) {
		Integer offset = 0;
		if(page != null){
			offset = (page - 1) * 10;
		}
		List<Employee> employeeList = employeeRepository.findAll(offset);
		return employeeList;
	}

	/**
	 * 従業員情報を取得します.
	 * 
	 * @param id ID
	 * @return 従業員情報
	 * @throws org.springframework.dao.DataAccessException 検索されない場合は例外が発生します
	 */
	public Employee showDetail(Integer id) {
		Employee employee = employeeRepository.load(id);
		return employee;
	}

	/**
	 * 従業員情報を更新します.
	 * 
	 * @param employee 更新した従業員情報
	 */
	public void update(Employee employee) {
		employeeRepository.update(employee);
	}

	/**
	 * 名前による従業員の曖昧検索をします.
	 *
	 * @param name 検索する名前
	 * @return 検索された従業員情報の一覧
	 */
	public List<Employee> searchEmployees(String name, Integer page){
		Integer offset = 0;
		if(page != null){
			offset = (page - 1) * 10;
		}

		List<Employee> employeeList = employeeRepository.findEmployeeByName(name, offset);
		return employeeList;
	}

	public List<Integer> pageCount(String name){
		int pages = employeeRepository.findCountByName(name) / 10 + 1;

//		if(name == null){
//			pages =  employeeRepository.findAll().size() / 10 + 1;
//		}else{
//			pages = employeeRepository.findCountByName(name) / 10 + 1;
//		}


		List<Integer> pageList = new ArrayList<>();
		for (int i = 1; i <= pages ; i++) {
			pageList.add(i);
		}

		return pageList;
	}
}
