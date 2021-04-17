package com.example.controller;

import com.example.dbmodel.Contact;
import com.example.dbmodel.Person;
import com.example.dto.ContactDto;
import com.example.repository.ContactRepository;
import com.example.repository.PersonRepository;
import com.example.repository.StudentRepository;
import com.example.security.UserDetailsImpl;
import java.util.List;
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
@RequestMapping("contact")
public class ContactController {

    private final ContactRepository contactRepository;
    private final StudentRepository studentRepository;
    private final PersonRepository personRepository;

    public ContactController(ContactRepository contactRepository, StudentRepository studentRepository, PersonRepository personRepository) {
        this.contactRepository = contactRepository;
        this.studentRepository = studentRepository;
        this.personRepository = personRepository;
    }

    public ContactRepository getContactRepository() {
        return contactRepository;
    }

    public StudentRepository getStudentRepository() {
        return studentRepository;
    }

    public PersonRepository getPersonRepository() {
        return personRepository;
    }

    @GetMapping("/")
    public ResponseEntity<?> getContacts() {
        Person person = studentRepository.findById(((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getStudentId())
                .get().getPerson();
        return ResponseEntity.ok(person.getContacts().stream()
                .map(e -> new ContactDto(e)).collect(Collectors.toList()));
    }

    @PutMapping("/")
    public ResponseEntity<?> updateContacts(@RequestBody List<ContactDto> contactDto) {
        Person person = studentRepository.findById(((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getStudentId())
                .get().getPerson();
        List<ContactDto> extracted = person.getContacts().stream().filter(e -> e.isContactOnPerson() == contactDto.get(0).isPersonContact())
                .map(e -> new ContactDto(e)).collect(Collectors.toList());
        List<ContactDto> toChangeList = contactDto.stream().filter(e -> !extracted.contains(e)).collect(Collectors.toList());
        if (!toChangeList.isEmpty()) {
            ContactDto toChange = toChangeList.get(0);
            Contact contact = contactRepository.getOne(toChange.getId());
            contact.setDescription(toChange.getDescription());
            contact.setContactValue(toChange.getContactValue());
            contactRepository.save(contact);
            return ResponseEntity.ok(new ContactDto(contact));
        }
        return ResponseEntity.ok(null);
    }

    @PostMapping("/")
    public ResponseEntity<?> createContact(@RequestBody ContactDto contactDto) {
        Person person = studentRepository.findById(((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getStudentId())
                .get().getPerson();
        Contact contact = new Contact();
        contact.setContactValue(contactDto.getContactValue());
        contact.setDescription(contactDto.getDescription());
        contact.setType(contactDto.getType());
        contact.setContactOnPerson(contactDto.isPersonContact());
        contact.setPerson(person);
        contactRepository.save(contact);
        person.getContacts().add(contact);
        personRepository.save(person);
        return ResponseEntity.ok(new ContactDto(contact));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteContact(@PathVariable(value = "id") String id) {
        Person person = studentRepository.findById(((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getStudentId())
                .get().getPerson();
        Long longId = Long.valueOf(id);
        Contact contact = contactRepository.getOne(longId);
        person.getContacts().remove(contact);
        personRepository.save(person);
        contactRepository.delete(contact);
        return ResponseEntity.ok(new ContactDto(contact));
    }
}
