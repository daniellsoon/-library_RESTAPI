package com.library.domain;

import com.library.repository.BookDao;
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
public class TitleTestSuite {

    @Autowired
    private TitleDao titleDao;

    @Autowired
    private BookDao bookDao;

    private static int testCount;

    @BeforeClass
    public static void testCountInit(){
         testCount = 1;
    }

    @Before
    public void testBegin() {
        System.out.println("Start Title Test #" + testCount);
        testCount++;
    }

    @Test
    public void testShouldAddOneTitle() {
        //Given
        Title title = new Title(null, "title_add", "author", 2000);

        //When
        titleDao.save(title);
        long id = title.getId();
        Optional<Title> getGroup = titleDao.findById(id);

        //Then
        assertTrue(getGroup.isPresent());
        assertTrue(titleDao.existsById(id));

        //CleanUp
        titleDao.deleteById(id);
    }

    @Test
    public void testShouldRemoveTitle() {
        //Given
        Title group = new Title(null, "title_remove", "author", 2000);

        //When
        titleDao.save(group);
        titleDao.delete(group);
        List<Title> result = titleDao.findAll();

        //Then
        assertTrue(result.isEmpty());
    }

    @Test
    public void testShouldFindCoupleTitles() {
        //Given
        Title title1 = new Title(null, "title1", "author", 2000);
        Title title2 = new Title(null, "title2", "author", 2000);
        Title title3 = new Title(null, "title3", "author", 2000);

        //When
        titleDao.save(title1);
        titleDao.save(title2);
        titleDao.save(title3);
        List<Title> result = titleDao.findAll();

        //Then
        assertEquals(3, result.size());

        //CleanUp
        for (Title title : result) {

            long id = title.getId();
            titleDao.deleteById(id);
        }
    }

    @Test
    public void testShouldUpdateTitle() {
        //Given
        Title title = new Title(null, "title_to_update", "author", 2000);

        //When
        titleDao.save(title);
        long id = title.getId();
        title.setBookTitle("Updated_Title");
        titleDao.save(title);

        List<Title> result = titleDao.findAll();
        Title updatedName = result.get(0);

        //Then
        assertEquals(id, updatedName.getId(), 0);
        assertEquals("Updated_Title", updatedName.getBookTitle());

        //CleanUp
        titleDao.deleteById(id);
    }

    @Test
    public void testShouldSaveTitleWithBooks() {
        //Given
        Title title = new Title(null, "title_with_products", "author", 2000);
        Book book1 = new Book(null, title);
        Book book2 = new Book(null, title);
        Book book3 = new Book(null, title);

        title.getBooksInTitle().add(book1);
        title.getBooksInTitle().add(book2);
        title.getBooksInTitle().add(book3);


        //When
        titleDao.save(title);
        List<Title> result = titleDao.findAll();
        Title loadedTitle = result.get(0);
        List<Book> booksInTitle = loadedTitle.getBooksInTitle();

        //Then
        assertEquals(3, booksInTitle.size());

        //CleanUp
        for (Book book : booksInTitle) {
            book.setTitle(null);
            long id = book.getId();
            bookDao.save(book);
            bookDao.deleteById(id);
        }
        long id = title.getId();
        titleDao.deleteById(id);
    }

    @Test
    public void testShouldRemoveBooksWithTitle() {
        //Given
        Title title = new Title(null, "remove_title_with_products", "author", 2000);
        Book bookToRemove = new Book(null, title);
        Book book2 = new Book(null, title);
        Book book3 = new Book(null, title);

        title.getBooksInTitle().add(bookToRemove);
        title.getBooksInTitle().add(book2);
        title.getBooksInTitle().add(book3);

        //When
        titleDao.save(title);
        titleDao.delete(title);
        List<Title> result = titleDao.findAll();
        List<Book> booksInTitle = bookDao.findAll();

        //Then
        assertEquals(0, result.size());
        assertEquals(0, booksInTitle.size());
    }
}
