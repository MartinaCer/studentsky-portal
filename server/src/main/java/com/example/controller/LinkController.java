package com.example.controller;

import com.example.dbmodel.Link;
import com.example.dbmodel.Person;
import com.example.dbmodel.PersonGlobalLink;
import com.example.dbmodel.Student;
import com.example.dto.GlobalFacultyLinkDocDto;
import com.example.dto.LinkDto;
import com.example.dto.PersonGlobalLinkDto;
import com.example.repository.GlobalLinkRepository;
import com.example.repository.LinkRepository;
import com.example.repository.PersonGlobalLinkRepository;
import com.example.repository.PersonRepository;
import com.example.repository.StudentRepository;
import com.example.security.UserDetailsImpl;
import java.time.Instant;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("link")
public class LinkController {

    private final LinkRepository linkRepository;
    private final GlobalLinkRepository globalLinkRepository;
    private final PersonGlobalLinkRepository personGlobalLinkRepository;
    private final StudentRepository studentRepository;
    private final PersonRepository personRepository;

    public LinkController(LinkRepository linkRepository, GlobalLinkRepository globalLinkRepository,
            PersonGlobalLinkRepository personGlobalLinkRepository,
            StudentRepository studentRepository, PersonRepository personRepository) {
        this.linkRepository = linkRepository;
        this.globalLinkRepository = globalLinkRepository;
        this.personGlobalLinkRepository = personGlobalLinkRepository;
        this.studentRepository = studentRepository;
        this.personRepository = personRepository;
    }

    public LinkRepository getLinkRepository() {
        return linkRepository;
    }

    public GlobalLinkRepository getGlobalLinkRepository() {
        return globalLinkRepository;
    }

    public PersonGlobalLinkRepository getPersonGlobalLinkRepository() {
        return personGlobalLinkRepository;
    }

    public StudentRepository getStudentRepository() {
        return studentRepository;
    }

    public PersonRepository getPersonRepository() {
        return personRepository;
    }

    @GetMapping("/")
    public ResponseEntity<?> getPersonLinks() {
        Person person = studentRepository.findById(((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getStudentId())
                .get().getPerson();
        return ResponseEntity.ok(person.getLinks().stream()
                .map(e -> new LinkDto(e)).collect(Collectors.toList()));
    }

    @PutMapping("/")
    public ResponseEntity<?> updateLink(@RequestBody LinkDto linkDto) {
        Link linkDB = linkRepository.getOne(linkDto.getId());
        linkDB.setLink(linkDto.getLink());
        linkDB.setDescription(linkDto.getDescription());
        linkDB.setModified(Instant.now());
        linkRepository.save(linkDB);
        return ResponseEntity.ok(new LinkDto(linkDB));
    }

    @PostMapping("/")
    public ResponseEntity<?> createLink(@RequestBody LinkDto linkDto) {
        Person person = studentRepository.findById(((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getStudentId())
                .get().getPerson();
        Link linkDb = new Link();
        linkDb.setLink(linkDto.getLink());
        linkDb.setDescription(linkDto.getDescription());
        linkDb.setModified(Instant.now());
        linkDb.setPerson(person);
        linkRepository.save(linkDb);
        person.getLinks().add(linkDb);
        personRepository.save(person);
        return ResponseEntity.ok(new LinkDto(linkDb));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteLink(@PathVariable(value = "id") String id) {
        Person person = studentRepository.findById(((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getStudentId())
                .get().getPerson();
        Long longId = Long.valueOf(id);
        Link link = linkRepository.getOne(longId);
        person.getLinks().remove(link);
        personRepository.save(person);
        linkRepository.delete(link);
        return ResponseEntity.ok(new LinkDto(link));
    }

    @GetMapping("/globalLinks")
    public ResponseEntity<?> getGlobalLinks() {
        return ResponseEntity.ok(globalLinkRepository.findAll().stream().filter(l -> !l.isFileLink() && l.getFaculty() == null)
                .map(l -> new GlobalFacultyLinkDocDto(l)).collect(Collectors.toList()));
    }

    @GetMapping("/globalFacultyLinks")
    public ResponseEntity<?> getGlobalFacultyLinks() {
        Student student = studentRepository.findById(((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getStudentId()).get();
        return ResponseEntity.ok(globalLinkRepository.findAll().stream().filter(l -> !l.isFileLink() && l.getFaculty() == student.getFaculty())
                .map(l -> new GlobalFacultyLinkDocDto(l)).collect(Collectors.toList()));
    }

    @GetMapping("/globalDocumentLinks")
    public ResponseEntity<?> getGlobalDocumentLinks() {
        Student student = studentRepository.findById(((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getStudentId()).get();
        return ResponseEntity.ok(globalLinkRepository.findAll().stream()
                .filter(l -> l.isFileLink() && (l.getFaculty() == null || l.getFaculty() == student.getFaculty()))
                .map(l -> new GlobalFacultyLinkDocDto(l)).collect(Collectors.toList()));
    }

    @GetMapping("/globalPersonLinks")
    public ResponseEntity<?> getGlobalPersonLinks() {
        Person person = studentRepository.findById(((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getStudentId())
                .get().getPerson();
        return ResponseEntity.ok(person.getPersonGlobalLinks().stream()
                .map(l -> new PersonGlobalLinkDto(l)).collect(Collectors.toList()));
    }

    @PostMapping("/createUpdateGlobalPersonLink")
    public ResponseEntity<?> createUpdateGlobalPersonLink(@RequestBody PersonGlobalLinkDto linkDto) {
        Person person = studentRepository.findById(((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getStudentId())
                .get().getPerson();
        PersonGlobalLink linkDB = linkDto.getId() == null
                ? new PersonGlobalLink() : personGlobalLinkRepository.getOne(linkDto.getId());
        linkDB.setLink(linkDto.getLink());
        linkDB.setDescription(linkDto.getDescription());
        linkDB.setType(linkDto.getType());
        if (linkDto.getId() == null) {
            linkDB.setPerson(person);
        }
        personGlobalLinkRepository.save(linkDB);
        if (linkDto.getId() == null) {
            person.getPersonGlobalLinks().add(linkDB);
            personRepository.save(person);
        }
        return ResponseEntity.ok(new PersonGlobalLinkDto(linkDB));
    }

    @DeleteMapping("/deleteGlobalPersonLink/{id}")
    public ResponseEntity<?> deleteGlobalPersonLink(@PathVariable(value = "id") String id) {
        Person person = studentRepository.findById(((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getStudentId())
                .get().getPerson();
        Long longId = Long.valueOf(id);
        PersonGlobalLink link = personGlobalLinkRepository.getOne(longId);
        person.getPersonGlobalLinks().remove(link);
        personRepository.save(person);
        personGlobalLinkRepository.delete(link);
        return ResponseEntity.ok(new PersonGlobalLinkDto(link));
    }
}
