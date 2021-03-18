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


    // group
    @Test
    public void addStudentValidGroup() {
        Student student = new Student("2", "Raul Mogos", 935, "raul@raul.com");
        studentValidator.validate(student);
        assertTrue(true);
        studentFileRepository.save(student);
        assertTrue(true);
    }

    @Test
    public void addStudentValidGroupBVA0() {
        Student student = new Student("2", "Raul Mogos", 0, "raul@raul.com");
        studentValidator.validate(student);
        assertTrue(true);
        studentFileRepository.save(student);
        assertTrue(true);
    }

    @Test
    public void addStudentValidGroupBVA1() {
        Student student = new Student("2", "Raul Mogos", 1, "raul@raul.com");
        studentValidator.validate(student);
        assertTrue(true);
        studentFileRepository.save(student);
        assertTrue(true);
    }

    @Test
    public void addStudentInvalidGroupBVAMinus1() {
        Student student = new Student("2", "Raul Mogos", -1, "raul@raul.com");
        try {
            studentValidator.validate(student);
            assertTrue(false);
            studentFileRepository.save(student);
        } catch (ValidationException e) {
            assertTrue(true);
        }
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

    // id
    @Test
    public void addStudentValidId() {
        Student student = new Student("1", "Raul Mogos", 935, "raul@raul.com");
        studentValidator.validate(student);
        assertTrue(true);
        studentFileRepository.save(student);
        assertTrue(true);
    }

    @Test
    public void addStudentValidIdBVA() {
        Student student = new Student("0", "Raul Mogos", 935, "raul@raul.com");
        studentValidator.validate(student);
        assertTrue(true);
        studentFileRepository.save(student);
        assertTrue(true);
    }

    @Test
    public void addStudentInvalidIdEmpty() {
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

    @Test
    public void addStudentInvalidIdNotANumber() {
        Student student = new Student("wed", "Raul Mogos", 935, "raul@raul.com");
        try {
            studentValidator.validate(student);
            assertTrue(false);
            studentFileRepository.save(student);
        } catch (ValidationException e) {
            assertTrue(true);
            assert "Id incorect!" == e.getMessage();
        }
    }

    @Test
    public void addStudentInvalidIdNegative() {
        Student student = new Student("-1", "Raul Mogos", 935, "raul@raul.com");
        try {
            studentValidator.validate(student);
            assertTrue(false);
            studentFileRepository.save(student);
        } catch (ValidationException e) {
            assertTrue(true);
            assert "Id incorect!" == e.getMessage();
        }
    }

    // name
    @Test
    public void addStudentValidName() {
        Student student = new Student("3", "Raul Mogos", 935, "raul@raul.com");
        studentValidator.validate(student);
        assertTrue(true);
        studentFileRepository.save(student);
        assertTrue(true);
    }

    @Test
    public void addStudentValidNameBVA() {
        Student student = new Student("3", "a", 935, "raul@raul.com");
        studentValidator.validate(student);
        assertTrue(true);
        studentFileRepository.save(student);
        assertTrue(true);
    }

    @Test
    public void addStudentInvalidName() {
        Student student = new Student("3", "", 935, "raul@raul.com");
        try {
            studentValidator.validate(student);
            assertTrue(false);
            studentFileRepository.save(student);
        } catch (ValidationException e) {
            assertTrue(true);
        }
    }

    // email
    @Test
    public void addStudentValidEmail() {
        Student student = new Student("4", "Raul Mogos", 935, "raul@raul.com");
        studentValidator.validate(student);
        assertTrue(true);
        studentFileRepository.save(student);
        assertTrue(true);
    }

    @Test
    public void addStudentValidEmailBVA() {
        Student student = new Student("1", "Raul Mogos", 935, "a");
        studentValidator.validate(student);
        assertTrue(true);
        studentFileRepository.save(student);
        assertTrue(true);
    }

    @Test
    public void addStudentInvalidEmail() {
        Student student = new Student("4", "Raul Mogos", 935, "");
        try {
            studentValidator.validate(student);
            assertTrue(false);
            studentFileRepository.save(student);
        } catch (ValidationException e) {
            assertTrue(true);
        }
    }
    
    
    // BVA
    @Test
    public void addStudentBVAInvalidEmail() {
        Student student = new Student("4", "Raul Mogos", 935, "");
        try {
            studentValidator.validate(student);
            assertTrue(false);
            studentFileRepository.save(student);
        } catch (ValidationException e) {
            assertTrue(true);
        }
    }
}