import java.util.HashMap;
import java.util.Map;

public class Library {
    private Map<String, Book> allBooks;

    Library(){
        allBooks = new HashMap<>();        
    }

    public void add_book(Book book){
        if(allBooks.containsKey(book.getIsbn())){
            //get Existing Book
            Book existing_book = allBooks.get(book.getIsbn());
            // if(existing_book != null){} ->  book should not be null - need to add validation on book constructor
            existing_book.add_copies(book.getTotalCopies());
        }else{
            allBooks.put(book.getIsbn(), book);
        }
    }

    public boolean borrow_book(String isbn){
        Book book =  allBooks.get(isbn);
        if(book == null){
            throw new IllegalArgumentException("isbn not availble");
        }
        return book.borrow_book();
    }

    public boolean return_book(String isbn){   
        Book book = allBooks.get(isbn);
        if(book == null){
            throw new IllegalArgumentException("isbn not available");
        }
        return book.return_book();
    }
    public int getTotalNumberofAllBooks(){
        return allBooks.size();
    }
}
