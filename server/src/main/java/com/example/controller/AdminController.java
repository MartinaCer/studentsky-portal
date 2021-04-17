package com.example.controller;

import com.example.dbmodel.Contact;
import com.example.dbmodel.ContactType;
import com.example.dbmodel.Faculty;
import com.example.dbmodel.GlobalLink;
import com.example.dbmodel.Person;
import com.example.dbmodel.Student;
import com.example.dto.FacultyDto;
import com.example.dto.GlobalFacultyLinkDocDto;
import com.example.dto.PersonDetailDto;
import com.example.dto.PersonDto;
import com.example.repository.ContactRepository;
import com.example.repository.DocumentRepository;
import com.example.repository.FacultyRepository;
import com.example.repository.GlobalLinkRepository;
import com.example.repository.LinkRepository;
import com.example.repository.PersonGlobalLinkRepository;
import com.example.repository.PersonRepository;
import com.example.repository.StudentRepository;
import com.example.repository.UserAccountRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("admin")
public class AdminController {

    private final PersonRepository personRepository;
    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;
    private final ContactRepository contactRepository;
    private final DocumentRepository documentRepository;
    private final LinkRepository linkRepository;
    private final UserAccountRepository userAccountRepository;
    private final PersonGlobalLinkRepository personGlobalLinkRepository;
    private final GlobalLinkRepository globalLinkRepository;

    public AdminController(PersonRepository personRepository, StudentRepository studentRepository, FacultyRepository facultyRepository,
            ContactRepository contactRepository, DocumentRepository documentRepository,
            LinkRepository linkRepository, UserAccountRepository userAccountRepository,
            PersonGlobalLinkRepository personGlobalLinkRepository, GlobalLinkRepository globalLinkRepository) {
        this.personRepository = personRepository;
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
        this.contactRepository = contactRepository;
        this.documentRepository = documentRepository;
        this.linkRepository = linkRepository;
        this.userAccountRepository = userAccountRepository;
        this.personGlobalLinkRepository = personGlobalLinkRepository;
        this.globalLinkRepository = globalLinkRepository;
    }

    @GetMapping("/getPersonDetail/{id}")
    public ResponseEntity<?> getPersonDetail(@PathVariable(value = "id") String id) {
        long longId = Long.valueOf(id);
        Person person = personRepository.getOne(longId);
        Map<ContactType, List<String>> contacts = person.getContacts().stream()
                .filter(Contact::isContactOnPerson)
                .collect(Collectors.groupingBy(Contact::getType,
                        Collectors.mapping(Contact::getContactValue, Collectors.toList())));
        return ResponseEntity.ok(new PersonDetailDto(person.getPhotoByteUrl(),
                contacts.get(ContactType.PHONENUMBER), contacts.get(ContactType.EMAIL), contacts.get(ContactType.BANKNUMBER)));
    }

    @GetMapping("/getPeople")
    public ResponseEntity<?> getPeople() {
        return ResponseEntity.ok(personRepository.findAll()
                .stream().map(e -> new PersonDto(e, true)).collect(Collectors.toList()));
    }

    @PostMapping("/createPerson")
    public ResponseEntity<?> createPerson(@RequestBody PersonDto personDto) {
        Person person = new Person();
        setPersonData(person, personDto);
        personRepository.save(person);
        return ResponseEntity.ok(new PersonDto(person, true));
    }

    @PutMapping("/updatePerson")
    public ResponseEntity<?> updatePerson(@RequestBody PersonDto personDto) {
        Person person = personRepository.getOne(personDto.getId());
        setPersonData(person, personDto);
        personRepository.save(person);
        return ResponseEntity.ok(new PersonDto(person, true));
    }

    @DeleteMapping("/deletePerson/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable(value = "id") String id) {
        long longId = Long.valueOf(id);
        List<Student> students = studentRepository.findByPersonId(longId);
        System.out.println(students.get(0).getStudentNumber());
        //studentRepository.deleteAll(students);
        return null;
    }

    @GetMapping("/getFaculties")
    public ResponseEntity<?> getFaculties() {
        return ResponseEntity.ok(facultyRepository.findAll()
                .stream().map(e -> new FacultyDto(e)).collect(Collectors.toList()));
    }

    @PostMapping("/createFaculty")
    public ResponseEntity<?> createFaculty(@RequestBody FacultyDto facultyDto) {
        Faculty faculty = new Faculty();
        setFacultyData(faculty, facultyDto);
        facultyRepository.save(faculty);
        return ResponseEntity.ok(new FacultyDto(faculty));
    }

    @PutMapping("/updateFaculty")
    public ResponseEntity<?> updateFaculty(@RequestBody FacultyDto facultyDto) {
        Faculty faculty = facultyRepository.getOne(facultyDto.getId());
        setFacultyData(faculty, facultyDto);
        facultyRepository.save(faculty);
        return ResponseEntity.ok(new FacultyDto(faculty));
    }

    @DeleteMapping("/deleteFaculty/{id}")
    public ResponseEntity<?> deleteFaculty(@PathVariable(value = "id") String id) {
        return null;
    }

    @GetMapping("/getUniversityLinks")
    public ResponseEntity<?> getUniversityLinks() {
        return ResponseEntity.ok(globalLinkRepository.findAll()
                .stream().filter(e -> e.getFaculty() == null).map(e -> new GlobalFacultyLinkDocDto(e)).collect(Collectors.toList()));
    }

    @PostMapping("/createUniversityLink")
    public ResponseEntity<?> createUniversityLink(@RequestBody GlobalFacultyLinkDocDto dto) {
        GlobalLink globalLink = new GlobalLink();
        setGlobalLinkData(globalLink, dto);
        globalLinkRepository.save(globalLink);
        return ResponseEntity.ok(new GlobalFacultyLinkDocDto(globalLink));
    }

    @PutMapping("/updateUniversityLink")
    public ResponseEntity<?> updateUniversityLink(@RequestBody GlobalFacultyLinkDocDto dto) {
        GlobalLink globalLink = globalLinkRepository.getOne(dto.getId());
        setGlobalLinkData(globalLink, dto);
        globalLinkRepository.save(globalLink);
        return ResponseEntity.ok(new GlobalFacultyLinkDocDto(globalLink));
    }

    @DeleteMapping("/deleteUniversityLink/{id}")
    public ResponseEntity<?> deleteUniversityLink(@PathVariable(value = "id") String id) {
        return null;
    }

    @GetMapping("/getFacultyLinks")
    public ResponseEntity<?> getFacultyLinks() {
        return ResponseEntity.ok(globalLinkRepository.findAll()
                .stream().filter(e -> e.getFaculty() != null).map(e -> new GlobalFacultyLinkDocDto(e)).collect(Collectors.toList()));
    }

    @PostMapping("/createFacultyLink")
    public ResponseEntity<?> createFacultyLink(@RequestBody GlobalFacultyLinkDocDto dto) {
        GlobalLink globalLink = new GlobalLink();
        setGlobalLinkData(globalLink, dto);
        globalLinkRepository.save(globalLink);
        return ResponseEntity.ok(new GlobalFacultyLinkDocDto(globalLink));
    }

    @PutMapping("/updateFacultyLink")
    public ResponseEntity<?> updateFacultyLink(@RequestBody GlobalFacultyLinkDocDto dto) {
        GlobalLink globalLink = globalLinkRepository.getOne(dto.getId());
        setGlobalLinkData(globalLink, dto);
        globalLinkRepository.save(globalLink);
        return ResponseEntity.ok(new GlobalFacultyLinkDocDto(globalLink));
    }

    @DeleteMapping("/deleteFacultyLink/{id}")
    public ResponseEntity<?> deleteFacultyLink(@PathVariable(value = "id") String id) {
        return null;
    }

    private void setPersonData(Person person, PersonDto dto) {
        person.setFirstname(dto.getFirstname());
        person.setLastname(dto.getLastname());
        person.setBirthNumber(dto.getBirthNumber());
        person.setStreet(dto.getStreet());
        person.setHouseNumber(dto.getHouseNumber());
        person.setTown(dto.getTown());
        person.setZipcode(dto.getZipcode());
        person.setISICnumber(dto.getISICnumber());
        person.setIDcardNumber(dto.getIDcardNumber());
        person.setDLnumber(dto.getDLnumber());
        int yearFromBN = Integer.valueOf(dto.getBirthNumber().substring(0, 1));
        int monthFromBN = Integer.valueOf(dto.getBirthNumber().substring(2, 3));
        String dayFromBN = dto.getBirthNumber().substring(4, 5);
        person.setBirthdate(LocalDate.of(
                yearFromBN < 80
                        ? 2000 + yearFromBN
                        : 1900 + yearFromBN,
                monthFromBN > 12
                        ? monthFromBN - 50
                        : monthFromBN,
                dayFromBN.startsWith("0")
                ? Integer.valueOf(dayFromBN.charAt(1))
                : Integer.valueOf(dayFromBN)));
    }

    private void setFacultyData(Faculty faculty, FacultyDto facultyDto) {
        faculty.setShortName(facultyDto.getShortName());
        faculty.setLongName(facultyDto.getLongName());
        faculty.setWebsite(facultyDto.getWebsite());
    }

    private void setGlobalLinkData(GlobalLink gl, GlobalFacultyLinkDocDto dto) {
        gl.setLink(dto.getLink());
        gl.setDescription(dto.getDescription());
    }
}
