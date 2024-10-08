public class Book {

    private String isbn;
    private String title;
    private String author;
    private int pubYear;
    private int totCopies;
    private int borrowedCopies;

    public Book(String isbn, String title, String author, int publishyear, int totalCopies) {
        if(isbn == null || title == null || isbn.isEmpty() || title.isEmpty()){
            throw new IllegalArgumentException("Isbn and title cant be null");
        }
        if(totalCopies < 1){
            throw new IllegalArgumentException("Total Copies cant be 0 oe less");
        }
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.pubYear = publishyear;
        this.totCopies = totalCopies;
        this.borrowedCopies = 0;
    }

    public void add_copies(int numberOfCopies){
        if(numberOfCopies > 0){
            totCopies = totCopies + numberOfCopies;
        }else{
            throw new IllegalArgumentException("numberOfCopies should be > 0");
        }
    }
    
    public boolean borrow_book(){
        if(borrowedCopies == totCopies){
            return false;
        }
        borrowedCopies++;
        return true;
    }

    public boolean return_book(){
        if(borrowedCopies == 0){
            return false;
        }
        borrowedCopies--;
        return true;

    }
    
    public int getAvailCopies(){
        return totCopies - borrowedCopies;
    }


    public String getIsbn() {
        return isbn;
    }
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public int getPubYear() {
        return pubYear;
    }
    public int getTotalCopies() {
        return totCopies;
    }
    public int getBorrowedCopies() {
        return borrowedCopies;
    }
}
