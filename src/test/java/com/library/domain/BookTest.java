package com.library.domain;

import com.library.repository.BookDao;
import com.library.repository.BorrowDao;
import com.library.repository.TitleDao;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookTest {

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

}