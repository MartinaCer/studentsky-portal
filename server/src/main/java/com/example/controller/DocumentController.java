package com.example.controller;

import com.example.dbmodel.Document;
import com.example.dbmodel.Person;
import com.example.dto.DocumentDto;
import com.example.dto.FileUploadDto;
import com.example.repository.DocumentRepository;
import com.example.repository.PersonRepository;
import com.example.repository.StudentRepository;
import com.example.security.UserDetailsImpl;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Instant;
import java.util.Base64;
import java.util.Base64.Decoder;
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

/**
 *
 * @author Ja
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("document")
public class DocumentController {

    private final DocumentRepository documentRepository;
    private final StudentRepository studentRepository;
    private final PersonRepository personRepository;

    public DocumentController(DocumentRepository documentRepository, StudentRepository studentRepository, PersonRepository personRepository) {
        this.documentRepository = documentRepository;
        this.studentRepository = studentRepository;
        this.personRepository = personRepository;
    }

    public DocumentRepository getDocumentRepository() {
        return documentRepository;
    }

    public StudentRepository getStudentRepository() {
        return studentRepository;
    }

    public PersonRepository getPersonRepository() {
        return personRepository;
    }

    @GetMapping("/")
    public ResponseEntity<?> getPersonDocuments() {
        Person person = studentRepository.findById(((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getStudentId())
                .get().getPerson();
        return ResponseEntity.ok(person.getDocuments().stream()
                .map(e -> new DocumentDto(e)).collect(Collectors.toList()));
    }

    @PostMapping("/")
    public ResponseEntity<?> uploadDocument(@RequestBody FileUploadDto file) throws IOException {
        Person person = studentRepository.findById(((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getStudentId())
                .get().getPerson();
        Decoder decoder = Base64.getDecoder();
        Document documentDb = new Document();
        documentDb.setName(file.getFilename());
        documentDb.setDataFile(decoder.decode(file.getData().split(",")[1]));
        documentDb.setModified(Instant.now());
        documentDb.setPerson(person);
        documentRepository.save(documentDb);
        person.getDocuments().add(documentDb);
        personRepository.save(person);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<?> downloadDocument(@PathVariable(value = "id") String id) throws IOException {
        Document doc = documentRepository.getOne(Long.valueOf(id));
        String fileSeparator = System.getProperty("file.separator");
        File file = new File(System.getProperty("user.home") + fileSeparator + "Downloads" + fileSeparator + doc.getName());
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(doc.getDataFile());
            fos.close();
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteLink(@PathVariable(value = "id") String id) {
        Person person = studentRepository.findById(((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getStudentId())
                .get().getPerson();
        Document doc = documentRepository.getOne(Long.valueOf(id));
        person.getDocuments().remove(doc);
        personRepository.save(person);
        documentRepository.delete(doc);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/")
    public ResponseEntity<?> updateDocument(@RequestBody DocumentDto documentDto) {
        Document documentDb = documentRepository.getOne(documentDto.getId());
        documentDb.setDescription(documentDto.getDescription());
        documentDb.setModified(Instant.now());
        documentRepository.save(documentDb);
        return ResponseEntity.ok(new DocumentDto(documentDb));
    }
}
