package com.avdhoot.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.avdhoot.user.entity.User;
import com.avdhoot.user.repository.UserRepository;

import VO.Department;
import VO.ResponseTemplateVO;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userrepository;
	
	@Autowired
	private RestTemplate resttemplate;
	
	public User saveUser(User user) {
		return userrepository.save(user);
	}

	public ResponseTemplateVO getUserWithDepartment(Long userId) {
		ResponseTemplateVO vo = new ResponseTemplateVO();
		User user = userrepository.findByUserId(userId);
		Department department = resttemplate.getForObject(
				"http://localhost:9004/departments/"+ user.getDepartmentId(), Department.class);
		vo.setUser(user);
		vo.setDepartment(department);
		return vo;
	}
}
