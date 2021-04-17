package com.example.controller;

import com.example.dbmodel.Person;
import com.example.dbmodel.Student;
import com.example.dto.FacultyDto;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.dto.PersonDto;
import com.example.repository.PersonRepository;
import com.example.repository.StudentRepository;
import com.example.security.UserDetailsImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("person")
public class PersonController {

    private final PersonRepository personRepository;
    private final StudentRepository studentRepository;

    public PersonController(PersonRepository personRepository, StudentRepository studentRepository) {
        this.personRepository = personRepository;
        this.studentRepository = studentRepository;
    }

    public PersonRepository getPersonRepository() {
        return personRepository;
    }

    public StudentRepository getStudentRepository() {
        return studentRepository;
    }

    @GetMapping("/")
    public ResponseEntity<?> getPerson() {
        Person person = studentRepository.findById(((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getStudentId())
                .get().getPerson();
        return ResponseEntity.ok(new PersonDto(person, false));
    }

    @PostMapping("/")
    public ResponseEntity<?> saveProfilePhoto(@RequestBody String imageUrl) {
        Person person = studentRepository.findById(((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getStudentId())
                .get().getPerson();
        person.setPhotoByteUrl(imageUrl);
        personRepository.save(person);
        return ResponseEntity.ok(new PersonDto(person, false));
    }

    @DeleteMapping("/")
    public ResponseEntity<?> deleteProfilePhoto() {
        Person person = studentRepository.findById(((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getStudentId())
                .get().getPerson();
        person.setPhotoByteUrl(null);
        personRepository.save(person);
        return ResponseEntity.ok(new PersonDto(person, false));
    }

    @GetMapping("/faculty")
    public ResponseEntity<?> getFaculty() {
        Student student = studentRepository.findById(((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getStudentId()).get();
        return ResponseEntity.ok(new FacultyDto(student.getFaculty()));
    }

}
