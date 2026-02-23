package vn.edu.hcmut.cse.adsoftweng.lab.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import vn.edu.hcmut.cse.adsoftweng.lab.entity.Student;
import vn.edu.hcmut.cse.adsoftweng.lab.repository.StudentRepository;


@Service
public class StudentService {
    @Autowired
    private StudentRepository repository;

    public List<Student> getAll() {
        return repository.findAll();
    }

    public Student getById(String id) {
        return repository.findById(id).orElse(null);
    }

    public List<Student> searchByName(String name) {
        return repository.findByNameContaining(name);
    }

    public void save_student(Student student){
        repository.save(student);
    }

    public void del_student(Student student){
        repository.delete(student);
    }
    
}