package com.library.domain;

import com.library.repository.BookDao;
import com.library.repository.BorrowDao;
import com.library.repository.ReaderDao;
import com.library.repository.TitleDao;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BorrowTestSuite {

    private static int testCount;

    @Autowired
    private BookDao bookDao;

    @Autowired
    private ReaderDao readerDao;

    @Autowired
    private BorrowDao borrowDao;

    @Autowired
    private TitleDao titleDao;

    @BeforeClass
    public static void testCountInit() {
        testCount = 1;
    }

    @Before
    public void testBegin() {
        System.out.println("Start Reader Test #" + testCount);
        testCount++;
    }

    @Test
    public void testShouldSaveBorrow() {
        //Given
        Borrow borrow = new Borrow(null);

        //When
        borrowDao.save(borrow);
        long id = borrow.getId();
        Optional<Borrow> getBorrow = borrowDao.findById(id);

        //Then
        assertTrue(getBorrow.isPresent());
        assertTrue(borrowDao.existsById(id));

        //CleanUp
        borrowDao.deleteById(id);
    }

    @Test
    public void testShouldDeleteBorrow() {
        //Given
        Borrow borrow = new Borrow(null);

        //When
        borrowDao.save(borrow);
        borrowDao.delete(borrow);
        List<Borrow> result = borrowDao.findAll();

        //Then
        assertEquals(0, result.size());
    }

    @Test
    public void testShouldUpdateReturnDate() {
        //Given
        Borrow borrow = new Borrow(null);

        //When
        borrowDao.save(borrow);
        borrow.setReturnDate(LocalDate.of(2020, 1, 20));
        borrowDao.save(borrow);
        List<Borrow> result = borrowDao.findAll();
        Borrow loadedBorrow = result.get(0);

        //Then
        assertEquals(LocalDate.of(2020, 1, 20), loadedBorrow.getReturnDate());

        //CleanUp
        long id = borrow.getId();
        borrowDao.deleteById(id);
    }

    @Test
    public void testShouldRemoveOnlyBorrowWithoutReader() {
        //Given
        Borrow borrow = new Borrow(null);
        Reader reader = new Reader(null, "name", "lastname");

        borrow.setReader(reader);
        reader.getBorrowedBooks().add(borrow);

        //When
        borrowDao.save(borrow);
        borrowDao.delete(borrow);
        borrow.setReader(null);
        borrowDao.save(borrow);
        borrowDao.delete(borrow);

        List<Reader> readers = readerDao.findAll();
        List<Borrow> borrows = borrowDao.findAll();

        //Then
        assertEquals(0, borrows.size());
        assertEquals(1, readers.size());

        //CleanUp
        long id = reader.getId();
        readerDao.deleteById(id);
    }

    @Test
    public void testShouldRemoveOnlyBorrowWithoutBook() {
        //Given
        Borrow borrow = new Borrow(null);
        Title title = new Title(null, "title", "author", 2000);
        Book book = new Book(null, title );

        title.getBooksInTitle().add(book);
        borrow.setBook(book);
        book.setBorrow(borrow);

        //When
        borrowDao.save(borrow);
        borrowDao.delete(borrow);
        borrow.setBook(null);
        borrowDao.save(borrow);
        borrowDao.delete(borrow);

        List<Book> books = bookDao.findAll();
        List<Borrow> borrows = borrowDao.findAll();

        //Then
        assertEquals(0, borrows.size());
        assertEquals(1, books.size());

        //CleanUp
        titleDao.delete(title);
    }

    @Test
    public void testShouldFindBorrowByBookId() {
        //Given
        Borrow borrow1 = new Borrow(null);
        Borrow borrow2 = new Borrow(null);
        Title title = new Title(null, "title", "author", 2000);
        Book book1 = new Book(null, title);
        Book book2 = new Book(null, title);

        title.getBooksInTitle().add(book1);
        title.getBooksInTitle().add(book1);
        borrow1.setBook(book1);
        borrow2.setBook(book2);
        book1.setBorrow(borrow1);
        book2.setBorrow(borrow2);

        //When
        titleDao.save(title);
        long bookId = book1.getId();
        Borrow loadedBorrow = borrowDao.findByBookId(bookId);

        //Then
        assertEquals(borrow1.getId(), loadedBorrow.getId());

        //CleanUp
        titleDao.delete(title);
    }
}