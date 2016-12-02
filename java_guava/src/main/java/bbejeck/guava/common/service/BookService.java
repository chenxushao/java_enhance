package bbejeck.guava.common.service;

import java.util.List;

import bbejeck.guava.common.model.Book;

/**
 * User: Bill Bejeck Date: 4/5/13 Time: 1:29 PM
 */
public interface BookService {

	List<Book> findBooksByAuthor(String author);

	Book findBookByIsbn(String isbn);

	List<Book> get();

}
