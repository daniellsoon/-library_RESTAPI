package com.library.domain;

import com.library.repository.BorrowDao;
import com.library.repository.ReaderDao;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReaderTestSuite {

    @Autowired
    private ReaderDao readerDao;

    @Autowired
    private BorrowDao borrowDao;

    private static int testCount;

    @BeforeClass
    public static void testCountInit(){
        testCount = 1;
    }

    @Before
    public void testBegin() {
        System.out.println("Start Reader Test #" + testCount);
        testCount++;
    }

    @Test
    public void testShouldSaveReader() {
        //Given
        Reader reader = new Reader(null, "name_to_save", "lastName");

        //When
        readerDao.save(reader);
        long id = reader.getId();
        Optional<Reader> getReader = readerDao.findById(id);

        //Then
        assertTrue(getReader.isPresent());
        assertTrue(readerDao.existsById(id));

        //CleanUp
        readerDao.deleteById(id);
    }

    @Test
    public void testShouldDeleteReader() {
        //Given
        Reader reader = new Reader(null, "name_to_delete", "lastName");

        //When
        readerDao.save(reader);
        readerDao.delete(reader);
        List<Reader> result = readerDao.findAll();

        //Then
        assertEquals(0, result.size());
    }

    @Test
    public void testShouldSaveCoupleUser() {
        //Given
        Reader reader1 = new Reader(null, "name1", "lastName");
        Reader reader2 = new Reader(null, "name2", "lastName");
        Reader reader3 = new Reader(null, "name3", "lastName");

        //When
        readerDao.save(reader1);
        readerDao.save(reader2);
        readerDao.save(reader3);
        List<Reader> result = readerDao.findAll();

        //Then
        assertEquals(3, result.size());

        //CleanUp
        readerDao.deleteAll();
    }

    @Test
    public void testShouldUpdateReaderName() {
        //Given
        Reader reader = new Reader(null, "name", "lastName");
        //When
        readerDao.save(reader);
        reader.setName("updated_name");
        readerDao.save(reader);

        List<Reader> result = readerDao.findAll();
        Reader updatedName = result.get(0);
        //Then
        assertEquals("updated_name", updatedName.getName());

        //CleanUp
        long id = updatedName.getId();
        readerDao.deleteById(id);
    }

    @Test
    public void testShouldAddBorrowToUser() {
        //Given
        Reader reader = new Reader(null, "name", "lastName");
        Borrow borrow1 = new Borrow(null);
        Borrow borrow2 = new Borrow(null);

        borrow1.setReader(reader);
        borrow2.setReader(reader);
        reader.getBorrowedBooks().add(borrow1);
        reader.getBorrowedBooks().add(borrow2);
        //When
        readerDao.save(reader);
        List<Reader> result = readerDao.findAll();
        Reader loadedReader = result.get(0);
        List<Borrow> borrowedBooks = loadedReader.getBorrowedBooks();
        //Then
        assertEquals(2, borrowedBooks.size());

        //CleanUp
        for (Borrow borrow : borrowedBooks) {
            borrow.setReader(null);
            long id = borrow.getId();
            borrowDao.save(borrow);
            borrowDao.deleteById(id);
        }
        long id = reader.getId();
        readerDao.deleteById(id);
    }

    @Test
    public void testShouldDeleteUserWithBorrows() {
        Reader reader = new Reader(null, "name", "lastName");
        Borrow borrow1 = new Borrow(null);
        Borrow borrow2 = new Borrow(null);
        Borrow borrow3 = new Borrow(null);

        borrow1.setReader(reader);
        borrow2.setReader(reader);
        borrow3.setReader(reader);
        reader.getBorrowedBooks().add(borrow1);
        reader.getBorrowedBooks().add(borrow2);
        reader.getBorrowedBooks().add(borrow3);
        //When
        readerDao.save(reader);
        readerDao.delete(reader);
        List<Reader> result = readerDao.findAll();
        List<Borrow> borrowedBooks = borrowDao.findAll();
        //Then
        assertTrue(borrowedBooks.isEmpty());
        assertTrue(result.isEmpty());
    }
}