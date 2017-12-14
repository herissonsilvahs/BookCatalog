package fjndev.com.company.dao.book;

import fjndev.com.company.book.Book;
import fjndev.com.company.database.Database;

import java.util.ArrayList;
import java.util.List;

public class BookDaoJDBC implements BookDao
{
    private Database database;

    public BookDaoJDBC()
    {
        database = new Database("org.sqlite.JDBC", "jdbc:sqlite:db/book_catalog.db");
    }

    private boolean execute(String query)
    {
        boolean success = false;
        if(database.connect())
        {
            if (database.executeQuery(query)>0)
            {
                success = true;
            }
            database.close();
        }
        return success;
    }

    private List<Book> executaConsulta(String sql)
    {
        List<Book> books = new ArrayList<>();
        if(database.connect())
        {
            try
            {
                database.setResultSet(sql);
                while (database.getResultSet().next())
                {
                    Integer id = database.getResultSet().getInt("id");
                    String title = database.getResultSet().getString("title");
                    Integer year = database.getResultSet().getInt("year");
                    String description = database.getResultSet().getString("description");
                    String author = database.getResultSet().getString("author");
                    String genre = database.getResultSet().getString("genre");
                    books.add(new Book(id, title, year, description, author, genre));
                }
            }catch(Exception e)
            {
                e.printStackTrace();
            }finally
            {
                database.close();
            }
        }
        return books;
    }

    @Override
    public boolean insert(Book book)
    {
        return execute("insert into books (title, year, description, author, genre) values ('"+book.getTitle()+"',"+
                book.getYear()+",'"+book.getDescription()+"','"+book.getAuthor()+"','"+book.getGenre()+"')");
    }

    @Override
    public boolean update(Book book)
    {
        return execute("update books set title='"+book.getTitle()+
                "',year='"+book.getYear()+"',description='"+book.getDescription()+"',author='"+book.getAuthor()+"',genre='"+
                book.getGenre()+"' where id="+book.getId());
    }

    @Override
    public boolean delete(Book book)
    {
        return execute("delete from books where id="+book.getId());
    }

    @Override
    public List<Book> all()
    {
        return executaConsulta("select * from books");
    }

    @Override
    public List<Book> search(String field, String value)
    {
        return executaConsulta("select * from books where "+field+" like '%"+value+"%'");
    }

    @Override
    public Book getByID(int id)
    {
        List<Book> books = executaConsulta("select * from books where id="+id);
        if (books.size()>0) {
            return books.get(0);
        }
        return null;
    }
}
