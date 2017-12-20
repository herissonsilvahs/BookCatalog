package fjndev.com.company.dao.book;


import fjndev.com.company.book.Book;

import java.util.List;

public interface BookDao
{
    boolean insert(Book author);

    boolean update(Book author);

    boolean delete(Book author);

    List<Book> all();

    List<Book> search(String field, String value);

    Book getByID(int id);
    Book getLastRegistry();
}
