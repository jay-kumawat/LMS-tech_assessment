import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.*;

public class testLms {

    Library lib;
    Book b1;
    Book b2;

    @Before
    public void setUp(){
        lib = new Library();
        //format Book Const : isbn, title, author, publishyear, totalCopeies
        //isbn can be 10 or 13 digits so str will bttr
        b1 = new Book("9356294496", "Mahagatha", "Satyarth Nayak", 2022, 2);
        b2 = new Book("978-8122905229", "HISTORY OF INDIAN PHILOSOPHY", "DR. R.N.SHARMA", 2019, 3);
    
        // adding books into the library 
        lib.add_book(b1);
        lib.add_book(b2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIfInvalidFieldsInBook(){
        b1 = new Book("","The Hidden Hindu","Akshat Gupta", 2022, 2);
        b2 = new Book("978-0241301913", "","DK", 2021, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTotalCopiesInBookObj(){
        Book book = new Book("978-0241301913", "The Mythology","DK", 2021, 0);

    }
    @Test
    public void testBookObject() {
        assertEquals("978-8122905229", b2.getIsbn());
        assertEquals("HISTORY OF INDIAN PHILOSOPHY", b2.getTitle());
        assertEquals(3, b2.getTotalCopies());
    }

    @Test
    public void test_add_copies_ofBook(){
        int additional_copies = 3;
        b1.add_copies(additional_copies);
        assertEquals(5, b1.getTotalCopies());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_add_copies_IfNegativeNumber(){
        int additional_copies = -2;
        b1.add_copies(additional_copies);
        // assertEquals(2, b1.getTotalCopies());
    }

    @Test
    public void test_borrow_book(){
        b1.borrow_book();
        assertEquals(1, b1.getBorrowedCopies());

        //Borrowing 1 more book
        b1.borrow_book();
        assertEquals(2, b1.getBorrowedCopies());

        //b1 has total 2 copies what if i borrow it once again should return false
        // changing the functionality of method accorifing below testcase
        assertFalse(b1.borrow_book());
    }

    @Test
    public void test_return_book(){
        //Intially no Copies were borrowed so should return false
        assertFalse(b1.return_book());
        
        //borrowing one book so can return it
        b1.borrow_book();
        b1.return_book();
        assertEquals(0, b1.getBorrowedCopies());
    }

    @Test
    public void test_getAvailCopies(){
        b2.borrow_book();
        b2.borrow_book();
        assertEquals(1, b2.getAvailCopies());

        b2.return_book();
        assertEquals(2, b2.getAvailCopies());
    }

    //Book is complete
    //Now adding thhe library class

    @Test
    public void testBookAdded_toLibrary(){
        assertEquals(2, lib.getTotalNumberofAllBooks());
    }

    //Now if Same Isbn book is enterd number of copies of that perticuler book should get inncreased

    @Test
    public void testIncreaseCopies_OfParticulerBook(){
        lib.add_book(new Book("9356294496", "Mahagatha", "Satyarth Nayak", 2022, 3));
        assertEquals(5, b1.getTotalCopies());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTo_addBookWith_InvalidCopies(){
        lib.add_book(new Book("9356294496", "Mahagatha", "Satyarth Nayak", 2022, 0));
    }
    
    @Test
    public void testBorrowBookFromLib(){
        assertTrue(lib.borrow_book("9356294496"));
        assertEquals(1,b1.getAvailCopies());
    }

    @Test
    public void testBorrowAllBooks(){
        assertTrue(lib.borrow_book("9356294496"));
        assertTrue(lib.borrow_book("9356294496"));
        assertFalse(lib.borrow_book("9356294496")); //No more copies availble thats why return false
        assertEquals(0, b1.getAvailCopies());
    }


    //if requesting non existing book from the library
    @Test(expected = IllegalArgumentException.class)
    public void testBorrowBook_IsbnIsNotAvailble(){
        lib.borrow_book("1124523456");
    }

    @Test
    public void testReturnBook_AfterBorrowing(){
        lib.borrow_book("9356294496");
        assertEquals(1, b1.getAvailCopies());
        assertTrue(lib.return_book("9356294496"));
        assertEquals(2, b1.getAvailCopies());
    }

    @Test
    public void testReturnBook_thatisNotBorrowed(){
        //this will return false because book is not borrowed
        assertFalse(lib.return_book("9356294496")); 
    }

    @Test(expected = IllegalArgumentException.class)
    public void testReturnBook_notExistinLibrary(){
        lib.return_book("1234567890");
    }

    @Test
    public void testgetAvailBooks(){
        List<Book> availableBooks =  lib.getAvailBooks();
        assertTrue(availableBooks.contains(b1));
        assertTrue(availableBooks.contains(b2));
    }

    @Test
    public void testAvailableBooks_IfSomeoneBorrows(){
        
        lib.borrow_book("9356294496");
        lib.borrow_book("9356294496");
        assertFalse(lib.borrow_book("9356294496")); //No more copies are availble
        
        assertEquals(0, b1.getAvailCopies());
        
        List<Book> avaBooks = lib.getAvailBooks();
        //only one book will be availble 
        assertEquals(1, avaBooks.size());
        
    }

    @Test
    public void testAvailableBooks_IfNoBookIsAvailable(){
        lib.borrow_book("9356294496");
        lib.borrow_book("9356294496");

        lib.borrow_book("978-8122905229");
        lib.borrow_book("978-8122905229");
        lib.borrow_book("978-8122905229");

        assertEquals(0, b1.getAvailCopies());
        assertEquals(0, b2.getAvailCopies());

        //no books are availble in the library now
        List<Book> avaBooks = lib.getAvailBooks();
        assertTrue(avaBooks.isEmpty());
    }

    @Test
    public void testBorrowingAndReturningBook_MultipleTimes(){
        assertTrue(lib.borrow_book("978-8122905229"));
        assertTrue(lib.borrow_book("978-8122905229"));

        assertEquals(1, b2.getAvailCopies());
        
        assertTrue(lib.return_book("978-8122905229"));
        assertEquals(2, b2.getAvailCopies());
        
        assertTrue(lib.return_book("978-8122905229"));
        assertEquals(3, b2.getAvailCopies());

    }
}