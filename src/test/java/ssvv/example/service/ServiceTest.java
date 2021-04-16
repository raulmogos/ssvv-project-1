package ssvv.example.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ssvv.example.domain.Nota;
import ssvv.example.domain.Student;
import ssvv.example.domain.Tema;
import ssvv.example.repository.NotaXMLRepo;
import ssvv.example.repository.StudentFileRepository;
import ssvv.example.repository.StudentXMLRepo;
import ssvv.example.repository.TemaXMLRepo;
import ssvv.example.validation.NotaValidator;
import ssvv.example.validation.StudentValidator;
import ssvv.example.validation.TemaValidator;
import ssvv.example.validation.ValidationException;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class ServiceTest {

    StudentValidator studentValidator;
    StudentXMLRepo studentFileRepository;

    TemaValidator temaValidator;
    TemaXMLRepo temaFileRepository;

    NotaValidator notaValidator;
    NotaXMLRepo notaFileRepository;

    Service service;


    @Before
    public void setUp() throws Exception {
        studentValidator = new StudentValidator();
        studentFileRepository = new StudentXMLRepo("fisiere/StudentiTest.xml");

        temaValidator = new TemaValidator();
        temaFileRepository = new TemaXMLRepo("fisiere/TemeTest.xml");

        notaValidator = new NotaValidator(studentFileRepository, temaFileRepository);
        notaFileRepository = new NotaXMLRepo("fisiere/NoteTest.xml");

        service = new Service(
                studentFileRepository, studentValidator,
                temaFileRepository ,temaValidator,
                notaFileRepository, notaValidator
        );
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
    public void addStudentInvalidName() { // TODO: rename
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
    public void addStudentInvalidEmail() { // TODO: rename
        Student student = new Student("4", "Raul Mogos", 935, "");
        try {
            studentValidator.validate(student);
            assertTrue(false);
            studentFileRepository.save(student);
        } catch (ValidationException e) {
            assertTrue(true);
        }
    }
    
    
    // BVA - boundary value analysis
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


    // White Box testing
    // assignment
    @Test
    public void addAssignmentValid() {
        Tema tema = new Tema("1", "descriere", 3, 5);
        service.addTema(tema);
        assertTrue(true);
    }

    @Test
    public void addAssignmentInvalidId() {
        Tema tema = new Tema("", "descriere", 3, 5);
        try {
            service.addTema(tema);
            fail();
        } catch (ValidationException e) {
            assertTrue(true);
        }
    }

    ////////////////////////////////////////////////////////////////////
    // lab 3 home
    ////////////////////////////////////////////////////////////////////

    @Test
    public void addAssignmentPath1() {
        Tema tema = new Tema("","desc",4,6);
        try {
            service.addTema(tema);
            fail();
        } catch (ValidationException e) {
            assertTrue(true);
        }
    }

    @Test
    public void addAssignmentPath2() {
        Tema tema = new Tema("2342621","",4,6);
        try {
            service.addTema(tema);
            fail();
        } catch (ValidationException e) {
            assertTrue(true);
        }
    }

    @Test
    public void addAssignmentPath3() {
        Tema tema = new Tema("169634", "desc", -2, 10);
        try {
            service.addTema(tema);
            fail();
        } catch (ValidationException e) {
            assertTrue(true);
        }
    }

    @Test
    public void addAssignmentPath3Extra() {
        Tema tema = new Tema("234234", "desc", 17, 10);
        try {
            service.addTema(tema);
            fail();
        } catch (ValidationException e) {
            assertTrue(true);
        }
    }

    @Test
    public void addAssignmentPath4() {
        Tema tema = new Tema("12312","desc", 10,-2);
        try {
            service.addTema(tema);
            fail();
        } catch (ValidationException e) {
            assertTrue(true);
        }
    }

    @Test
    public void addAssignmentPath4Extra() {
        Tema tema = new Tema("13423","desc", 10,17);
        try {
            service.addTema(tema);
            fail();
        } catch (ValidationException e) {
            assertTrue(true);
        }
    }

    @Test
    public void addAssignmentPath5() {
        Tema tema = new Tema("1","desc",2,10);
        try {
            service.addTema(tema);
            assertTrue(true);
        } catch (ValidationException e) {
            fail();
        }
    }

    @Test
    public void addAssignmentPath6() {
        service.deleteTema("2377423");
        Tema tema = new Tema("2377423","desc",2,10);
        try {
            Tema ret = service.addTema(tema);
            assertNull(ret);
        } catch (ValidationException e) {
            fail();
        }
        try {
            Tema ret = service.addTema(tema);
            assertNotNull(ret);
        } catch (ValidationException e) {
            fail();
        }
    }


    // lab4

    @Test
    public void integrationStudent() {
        Student student = new Student("222", "Raul Mogos", 935, "raul@raul.com");
        studentValidator.validate(student);
        assertTrue(true);
        studentFileRepository.save(student);
        assertTrue(true);
    }

    @Test
    public void integrationAssignment() {
        Tema tema = new Tema("111", "descriere", 6, 3);
        service.addTema(tema);
        assertTrue(true);
    }

    @Test
    public void integrationGrade() {
        Nota nota = new Nota("111", "222", "111", 5, LocalDate.of(2018, 10, 15));
        service.addNota(nota, "Nice work!");
        assertTrue(true);
    }

    @Test
    public void integrationAll() {
        Student student = new Student("222", "Raul Mogos", 935, "raul@raul.com");
        service.addStudent(student);
        Tema tema = new Tema("111", "descriere", 6, 3);
        service.addTema(tema);
        Nota nota = new Nota("111", "222", "111", 5, LocalDate.of(2018, 10, 15));
        service.addNota(nota, "Nice work!");
        assertTrue(true);
    }

    // lab 4 - homework
    // Incremental

    @Test
    public void addStudentIncremental() {
        Student student = new Student("222", "Raul Mogos", 935, "raul@raul.com");
        studentValidator.validate(student);
        assertTrue(true);
        studentFileRepository.save(student);
        assertTrue(true);
    }

    @Test
    public void addStudentAddAssignmentIncremental() {
        Student student = new Student("222", "Raul Mogos", 935, "raul@raul.com");
        studentValidator.validate(student);
        studentFileRepository.save(student);
        Tema tema = new Tema("111", "descriere", 6, 3);
        service.addTema(tema);
        assertTrue(true);
    }

    @Test
    public void incrementalAll() {
        Student student = new Student("222", "Raul Mogos", 935, "raul@raul.com");
        service.addStudent(student);
        Tema tema = new Tema("111", "descriere", 6, 3);
        service.addTema(tema);
        Nota nota = new Nota("111", "222", "111", 5, LocalDate.of(2018, 10, 15));
        service.addNota(nota, "Nice work!");
        assertTrue(true);
    }
}