package org.library.service;

import java.util.Iterator;
import java.util.Objects;
import java.util.ServiceLoader;
import org.library.exception.BookNotFoundException;
import org.library.spi.Book;
import org.library.spi.Library;

public class LibraryService {
  private final ServiceLoader<Library> loader;
  private static LibraryService instance;

  private LibraryService() {
    loader = ServiceLoader.load(Library.class);
  }

  public static synchronized LibraryService getInstance() {
    if (instance == null) {
      instance = new LibraryService();
    }
    return instance;
  }

  public Book getBook(String name) {
    Book book = null;
    Iterator<Library> libraries = loader.iterator();
    while (book == null && libraries.hasNext()) {
      Library library = libraries.next();
      book = library.getBook(name);
    }
    if (book == null) {
      throw new BookNotFoundException();
    }
    return book;
  }

  public Book getBook(String name, String category) {
    return loader.stream().map(ServiceLoader.Provider::get)
        .filter(library -> library.getCategory().equals(category))
        .map(library -> library.getBook(name)).filter(
            Objects::nonNull).findFirst().orElseThrow(BookNotFoundException::new);
  }
}
