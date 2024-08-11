import java.util.List;
import java.util.Scanner;

public class gui {
    
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            Library lib = new Library();

            int option;

            while(true){
                System.out.println("\n1. Add a Book");
                System.out.println("2. Borrow Book");
                System.out.println("3. Return Book");
                System.out.println("4. View Available Book");
                System.out.println("5. Exit");
                System.out.println("\nEnter ur Option");
                option = sc.nextInt();

                String isbn;
                switch (option) {
                    case 1 :
                        System.out.println("Enter ISBN : ");
                        isbn = sc.next();
                        System.out.print("Enter Title : ");
                        String title = sc.next();
                        System.out.println("Enter author : ");
                        String author = sc.next();
                        System.out.println("Enter publish Year");
                        int py = sc.nextInt();
                        System.out.println("Enter Total Number of Copies : ");
                        int copi = sc.nextInt();
                        try{
                            lib.add_book(new Book(isbn, title, author, py, copi));
                        }catch (Exception e) {
                            System.out.println("Error : " + e.getMessage());
                        }
                        
                        break;
                    case 2 : 
                        System.out.println("Enter ISBN to Borrow this book : ");
                        isbn = sc.next();
                        try{
                            if(lib.borrow_book(isbn)){
                                System.out.println("Book Borrowed Succesfully");
                            }else{
                                System.out.println("Book is not Available to Borrow");
                            }
                        }catch  (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                        case 3 :
                        System.out.println("Enter ISBN to Return this book : ");
                        isbn = sc.next();
                        try{
                            if(lib.return_book(isbn)){
                                System.out.println("Book Return Succesfully");
                            }else{
                                System.out.println("Book is not Borrowed From Us");
                            }
                        }catch  (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                    break;
                    case 4 :
                        List<Book> avaBooks = lib.getAvailBooks();
                        if(avaBooks.isEmpty()){
                            System.out.println("No Book is Availble Right Now");
                        }
                        for(Book b : avaBooks){
                            System.out.println("ISBN : " + b.getIsbn() + " Title : " + b.getTitle() + " Total Copies : " + b.getTotalCopies() + " Avail Copies : " + b.getAvailCopies());
                        }
                        break;
                    default:
                        System.out.println("Select Proper Option");
                        break;
                }
                
            }
        } catch (Exception e) {
            System.out.println("Any Error Occured : " + e.getMessage());
        }
    }
}
