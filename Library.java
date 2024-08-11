import java.util.HashMap;
import java.util.Map;

public class Library {
    private Map<String, Book> allBooks;

    Library(){
        allBooks = new HashMap<>();        
    }

    public void add_book(Book book){
        allBooks.put(book.getIsbn(), book);
    }

    public int getTotalNumberofAllBooks(){
        return allBooks.size();
    }
}
