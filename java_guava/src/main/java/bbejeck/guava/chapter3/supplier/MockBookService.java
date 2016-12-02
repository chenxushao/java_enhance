package bbejeck.guava.chapter3.supplier;

import java.util.List;

import bbejeck.guava.common.model.Book;
import bbejeck.guava.common.service.BookService;

import com.google.common.collect.Lists;

/**
 * User: Bill Bejeck Date: 4/5/13 Time: 1:32 PM
 */
class MockBookService implements BookService {

	@Override
	public List<Book> findBooksByAuthor(String author) {
		return Lists.newArrayList(new Book.Builder().author(author).build());
	}

	@Override
	public Book findBookByIsbn(String isbn) {
		return new Book.Builder().isbn(isbn).build();
	}

	@Override
	public List<Book> get() {
		return Lists.newArrayList(new Book.Builder().build());
	}
}
