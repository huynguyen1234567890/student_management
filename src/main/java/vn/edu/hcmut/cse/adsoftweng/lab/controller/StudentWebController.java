package vn.edu.hcmut.cse.adsoftweng.lab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; // Luu y: su dung @Controller, KHONG dung,→ @RestController
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vn.edu.hcmut.cse.adsoftweng.lab.service.StudentService;
import vn.edu.hcmut.cse.adsoftweng.lab.entity.Student;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@Controller
@RequestMapping("/students")
public class StudentWebController {
    @Autowired
    private StudentService service;
    // Route: GET http://localhost:8080/students
    // @GetMapping
    // public String getAllStudents(Model model) {
    // // 1. Lay du lieu tu Service
    //     List<Student> students = service.getAll();
    //     // 2. Dong goi du lieu vao "Model" de chuyen sang View
    //     // Key "dsSinhVien" se duoc su dung ben file HTML
    //     model.addAttribute("dsSinhVien", students);
    //     // 3. Tra ve ten cua View (khong can duoi .html)
    //     // Spring Boot se tu dong tim file tai: src/main/resources/templates/students.html
    //     return "students";
    // }
    @GetMapping
    public String getAllStudents(@RequestParam(required = false) String keyword, Model model) {
        List<Student> students;
        if (keyword != null && !keyword.isEmpty()) {
        // Can viet them ham searchByName trong Service/Repository
            students = service.searchByName(keyword);
        } else {
            students = service.getAll();
        }
        model.addAttribute("dsSinhVien", students);
        return "students";
    }

    @GetMapping("/new")
    public String getMethodName(Model model) {
        model.addAttribute("huy", new Student());
        return "add_student";
    }
    
    @PostMapping
    public String saveStudent(@ModelAttribute("huy") Student student) {

        service.save_student(student); // lưu database

        return "redirect:/students";
    }

    @GetMapping("/{id}")
    public String getDetail(@PathVariable String id,Model model) {
        Student student =service.getById(id);
        model.addAttribute("student", student);
        return "student_detail";
    }

    @GetMapping("/{id}/update")
    public String getUpdateForm(@PathVariable String id,Model model) {
        Student student =service.getById(id);
        model.addAttribute("student", student);
        return "update_student";
    }
    
    @PutMapping("/{id}")
    public String putStudent(@ModelAttribute("student") Student student) {
        service.save_student(student);
        return "student_detail" ;
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@ModelAttribute("student") Student student){
        service.del_student(student);
        return "redirect:/students";
    }
    
}
