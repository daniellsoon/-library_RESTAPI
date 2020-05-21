package com.library.domain;

import com.library.repository.BookDao;
import com.library.repository.BorrowDao;
import com.library.repository.TitleDao;
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
public class BookTestSuite {

    @Autowired
    private BookDao bookDao;

    @Autowired
    private TitleDao titleDao;

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
    public void testShouldAddBook() {
        //Given
        Title title = new Title(null, "Sample", "Sample", 1111);
        Book book = new Book(null, title);
        title.getBooksInTitle().add(book);

        //When
        bookDao.save(book);
        long id = book.getId();
        Optional<Book> getBook = bookDao.findById(id);

        //Then
        assertTrue(getBook.isPresent());
        assertTrue(bookDao.existsById(id));

        //CleanUp
        titleDao.delete(title);
    }

    @Test
    public void testShouldRemoveOneBookFromTitle() {
        //Given
        Title title = new Title(null, "title_with_one_products_to_remove", "author", 2000);
        Book book1 = new Book(null, title);
        Book book2 = new Book(null, title);
        Book book3 = new Book(null, title);

        title.getBooksInTitle().add(book1);
        title.getBooksInTitle().add(book2);
        title.getBooksInTitle().add(book3);

        //When
        titleDao.save(title);
        book1.setTitle(null);
        bookDao.save(book1);
        bookDao.delete(book1);

        List<Title> result = titleDao.findAll();
        List<Book> booksInTitle = bookDao.findAll();


        //Then
        assertEquals(1, result.size());
        assertEquals(2, booksInTitle.size());

        //CleanUp
        for (Book book : booksInTitle) {
            book.setTitle(null);
            bookDao.save(book);
            bookDao.delete(book);
        }
        titleDao.delete(title);
    }

    @Test
    public void testShouldUpdateStatus() {
        //Given
        Title title = new Title(null, "Sample", "Sample", 1111);
        Book book = new Book(null, title);
        title.getBooksInTitle().add(book);

        //When
        bookDao.save(book);
        book.setStatus(StatusAllowed.LOST);
        bookDao.save(book);
        List<Book> books = bookDao.findAll();
        Book bookLoaded = books.get(0);

        //Then
        assertEquals(StatusAllowed.LOST, bookLoaded.getStatus());

        //CleanUp
        titleDao.delete(title);
    }

    @Test
    public void testShouldRemoveBooksWithBorrows() {
        //Given
        Title title = new Title(null, "Sample", "Sample", 1111);
        Book book = new Book(null, title);
        Borrow borrow = new Borrow(null);

        title.getBooksInTitle().add(book);
        borrow.setBook(book);
        book.setBorrow(borrow);

        //When
        bookDao.save(book);
        book.setTitle(null);
        bookDao.save(book);
        bookDao.delete(book);
        List<Book> books = bookDao.findAll();
        List<Borrow> borrows = borrowDao.findAll();

        //Then
        assertTrue(books.isEmpty());
        assertTrue(borrows.isEmpty());

        //CleanUp;
        long id = title.getId();
        titleDao.deleteById(id);
    }
}