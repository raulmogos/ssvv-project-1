package ssvv.example.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ssvv.example.domain.Student;
import ssvv.example.repository.StudentFileRepository;
import ssvv.example.validation.StudentValidator;
import ssvv.example.validation.ValidationException;

import static org.junit.Assert.*;

public class ServiceTest {

    StudentValidator studentValidator;
    StudentFileRepository studentFileRepository;

    @Before
    public void setUp() throws Exception {
        studentValidator = new StudentValidator();
        studentFileRepository = new StudentFileRepository("fisiere/Studenti.txt");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addStudentValid() {
        Student student = new Student("1", "Raul Mogos", 935, "raul@raul.com");
        studentValidator.validate(student);
        studentFileRepository.save(student);
    }

    @Test
    public void addStudentInvalidGroup() {
        Student student = new Student("2", "Raul Mogos", -935, "raul@raul.com");
        try {
            studentValidator.validate(student);
            assertTrue(false);
            studentFileRepository.save(student);
        } catch (ValidationException e) {
            assertTrue(true);
        }
    }

    @Test
    public void addStudentInvalidId() {
        Student student = new Student("", "Raul Mogos", 935, "raul@raul.com");
        try {
            studentValidator.validate(student);
            assertTrue(false);
            studentFileRepository.save(student);
        } catch (ValidationException e) {
            assertTrue(true);
            assert "Id incorect!" == e.getMessage();
        }
    }
}