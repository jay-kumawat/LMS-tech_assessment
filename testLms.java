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

}