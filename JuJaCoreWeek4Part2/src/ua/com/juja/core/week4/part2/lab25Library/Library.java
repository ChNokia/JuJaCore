package ua.com.juja.core.week4.part2.lab25Library;

class Issue {
    private String name;
    private int countPages;

    public Issue(String name, int countPages) {
        this.name = name;
        this.countPages = countPages;
    }

    public String toPrint() {
        return "Issue{" +
                "name=" + name +
                ",countPages=" + countPages +
                "}";
    }

    public int getCountPages() {
        return countPages;
    }

    public String getName(){
        return name;
    }

}

class Journal extends Issue {
    private String yearJournal;
    private String numberJournal;

    public Journal(String nameJournal, int countPages, String yearJournal, String numberJournal) {
        super(nameJournal, countPages);
        this.yearJournal = yearJournal;
        this.numberJournal = numberJournal;
    }

    @Override
    public String toPrint() {
        return "Journal{" +
                "name=" + getName() +
                ",countPages=" + getCountPages() +
                ",year=" + yearJournal +
                ",number=" + numberJournal +
                "}";
    }
}

class Book extends Issue {

    private String authorBook;

    public Book(String name, int countPages, String authorBook) {
        super(name, countPages);
        this.authorBook = authorBook;
    }

    @Override
    public String toPrint() {
        return "Book{" +
                "name=" + getName() +
                ",countPages=" + getCountPages() +
                ",author=" + authorBook +
                "}";
    }
}
public class Library {

    public String getIssueWithCountPagesMoreN(Issue[] catalog, int barrierCountPages) {
        String result = "";

        for ( int i = 0; i < catalog.length; i++ ) {
            if ( catalog[i].getCountPages() > barrierCountPages ) {
                result += catalog[i].toPrint();
            }
        }

        return result;
    }

}
