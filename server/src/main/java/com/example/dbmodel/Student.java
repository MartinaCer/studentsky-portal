package com.example.dbmodel;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "student")
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    @NotNull
    private long id;
    @NaturalId
    @Column(unique = true)
    @NotNull
    @Size(min = 6, max = 6)
    private String studentNumber;
    @NotNull
    private boolean activeStudent = true;
    @ManyToOne(cascade = CascadeType.ALL)
    private Faculty faculty;
    @ManyToOne(cascade = CascadeType.ALL)
    private Person person;

    public Student() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public boolean isActiveStudent() {
        return activeStudent;
    }

    public void setActiveStudent(boolean activeStudent) {
        this.activeStudent = activeStudent;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

}
