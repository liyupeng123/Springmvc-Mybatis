package org.lyp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.lyp.vo.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/")
public class StudentController {
	@RequestMapping("delete.do")
	public String delete(Student student) {
		MyBatisSessionFactory.getSession().delete("org.lyp.vo.MemberNS.doRemove",student.getSno());
		MyBatisSessionFactory.getSession().commit();
		return "find.do";
	}
	@RequestMapping("add.do")
	public String add(Student student) {
		MyBatisSessionFactory.getSession().insert("org.lyp.vo.MemberNS.doCreate", student);
		MyBatisSessionFactory.getSession().commit();
		MyBatisSessionFactory.close();
		return "find.do";
	}
	@RequestMapping("find.do")
	public String find(HttpServletRequest request) {
		List<Student> list=MyBatisSessionFactory.getSession().selectList("org.lyp.vo.MemberNS.findAll");
		request.setAttribute("list", list);
		MyBatisSessionFactory.close();
		return "student.jsp";
	}
	@RequestMapping("update.do")
	public String update(Student student) {
		MyBatisSessionFactory.getSession().update("org.lyp.vo.MemberNS.doUpdate", student);
		System.out.println(student.getSno());
		MyBatisSessionFactory.getSession().commit();
		
		MyBatisSessionFactory.close();
		return "find.do";
	}
	@RequestMapping("findOne.do")
	public String findone(Student student,HttpServletRequest request) {
		Student s = MyBatisSessionFactory.getSession().selectOne("org.lyp.vo.MemberNS.findOne", student.getSno());
		request.setAttribute("p",s);
		MyBatisSessionFactory.close();
		return "edit.jsp";
	}
}
