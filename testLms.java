import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.*;

public class testLms {

    Book b1;
    Book b2;

    @Before
    public void setUp(){
        //format Book Const : isbn, title, author, publishyear, totalCopeies
        //isbn can be 10 or 13 digits so str will bttr
        b1 = new Book("9356294496", "Mahagatha", "Satyarth Nayak", 2022, 2);
        b2 = new Book("978-8122905229", "HISTORY OF INDIAN PHILOSOPHY", "DR. R.N.SHARMA", 2019, 3);
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
}