package com.library.domain;

import com.library.repository.BookDao;
import com.library.repository.TitleDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TitleTest {

    @Autowired
    private TitleDao titleDao;

    @Autowired
    private BookDao bookDao;

    @Test
    public void testShouldAddOneTitle() {
        //Given
        Title title = new Title("title_add","author", 2000);

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
        Title group = new Title("title_remove","author", 2000);

        //When
        titleDao.save(group);
        titleDao.delete(group);
        List<Title> result = new ArrayList<>();
        Iterable<Title> getTitles = titleDao.findAll();
        getTitles.forEach(result :: add);

        //Then
        assertTrue(result.isEmpty());
    }

    @Test
    public void testShouldFindCoupleTitles() {
        //Given
        Title title1 = new Title("title1","author", 2000);
        Title title2 = new Title("title2","author", 2000);
        Title title3 = new Title("title3","author", 2000);

        //When
        titleDao.save(title1);
        titleDao.save(title2);
        titleDao.save(title3);
        List<Title> result = new ArrayList<>();
        Iterable<Title> getTitles = titleDao.findAll();
        getTitles.forEach(result :: add);

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
        Title title = new Title("title_to_update","author", 2000);

        //When
        titleDao.save(title);
        long id = title.getId();
        title.setTitle("Updated_Title");
        titleDao.save(title);

        List<Title> result = new ArrayList<>();
        Iterable<Title> getTitles = titleDao.findAll();
        getTitles.forEach(result::add);
        Title updatedName = result.get(0);

        //Then
        assertEquals(id, updatedName.getId(), 0);
        assertEquals("Updated_Title", updatedName.getTitle());

        //CleanUp
        titleDao.deleteById(id);
    }

    @Test
    public void testShouldSaveTitleWithBooks() {
        //Given
        Title title = new Title("title_with_products","author", 2000);
        Book book1 = new Book(title);
        Book book2 = new Book(title);
        Book book3 = new Book(title);
        

        title.getBooksInTitle().add(book1);
        title.getBooksInTitle().add(book2);
        title.getBooksInTitle().add(book3);


        //When
        titleDao.save(title);
        List<Title> result = new ArrayList<>();
        Iterable<Title> getGroups = titleDao.findAll();
        getGroups.forEach(result::add);
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

   /* @Test
    public void testShouldNotRemoveProductWithGroupRelation() {
        //Given
        Product product1 = new Product("prod1", "desc1");
        Product product2 = new Product("prod2", "desc2");
        Product product3 = new Product("prod3", "desc3");
        Group group = new Group("Test_Group_with_Products_Nothing_Change");

        group.getProducts().add(product1);
        group.getProducts().add(product2);
        group.getProducts().add(product3);
        product1.setGroup(group);
        product2.setGroup(group);
        product3.setGroup(group);

        //When
        groupRepository.save(group);
        long id = product1.getId();
        productRepository.deleteById(id);

        List<Group> result = new ArrayList<>();
        Iterable<Group> getGroups = groupRepository.findAll();
        getGroups.forEach(result::add);

        List<Product> productsInGroup = new ArrayList<>();
        Iterable<Product> getProducts = productRepository.findAll();
        getProducts.forEach(productsInGroup::add);

        //Then
        assertEquals(1, result.size());
        assertEquals(3, productsInGroup.size());

        //CleanUp
        for (Product product : productsInGroup) {
            product.setGroup(null);
            id = product.getId();
            productRepository.save(product);
            productRepository.deleteById(id);
        }
        id = group.getId();
        groupRepository.deleteById(id);
    }

    @Test
    public void testShouldRemoveOneProduct() {
        //Given
        Product productToRemove = new Product("To_remove", "To_Remove_desc");
        Product product1 = new Product("prod1", "desc1");
        Product product2 = new Product("prod2", "desc2");
        Group group = new Group("Test_Group_with_Product_Deleted");

        group.getProducts().add(product1);
        group.getProducts().add(product2);
        group.getProducts().add(productToRemove);
        product1.setGroup(group);
        product2.setGroup(group);
        productToRemove.setGroup(group);

        //When
        groupRepository.save(group);
        productToRemove.setGroup(null);
        productRepository.save(productToRemove);
        productRepository.delete(productToRemove);
        long id = product1.getId();
        productRepository.deleteById(id);

        List<Group> result = new ArrayList<>();
        Iterable<Group> getGroups = groupRepository.findAll();
        getGroups.forEach(result::add);

        List<Product> productsInGroup = new ArrayList<>();
        Iterable<Product> getProducts = productRepository.findAll();
        getProducts.forEach(productsInGroup::add);

        //Then
        assertEquals(1, result.size());
        assertEquals(2, productsInGroup.size());

        //CleanUp
        for (Product product : productsInGroup) {
            product.setGroup(null);
            id = product.getId();
            productRepository.save(product);
            productRepository.deleteById(id);
        }
        id = group.getId();
        groupRepository.deleteById(id);
    }
*/
}