package fjndev.com.company.book;

public class Book
{
    private int id;

    private String title;
    private String author;
    private Integer year;
    private String genre;
    private String description;

    public Book(final Integer id,
                final String title,
                final Integer year,
                final String description,
                final String author,
                final String genre)
    {
        this.id = id;
        this.description = description;
        this.year = year;
        this.title = title;
        this.genre = genre;
        this.author = author;
    }

    public Book(final String title,
                final Integer year,
                final String description,
                final String author,
                final String genre)
    {
        this.description = description;
        this.year = year;
        this.title = title;
        this.genre = genre;
        this.author = author;
    }

    public String getTitle()
    {
        return title;
    }

    public String getAuthor()
    {
        return author;
    }

    public Integer getYear()
    {
        return year;
    }

    public String getGenre()
    {
        return genre;
    }

    public String getDescription()
    {
        return description;
    }

    public int getId()
    {
        return id;
    }

    @Override
    public String toString()
    {
        return title;
    }
}
