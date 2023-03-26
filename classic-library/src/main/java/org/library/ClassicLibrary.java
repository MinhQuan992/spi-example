package org.library;

import java.util.Map;
import java.util.TreeMap;
import org.library.spi.Book;
import org.library.spi.Library;

public class ClassicLibrary implements Library {
  private static final String CLASSIC_LIBRARY = "CLASSIC";
  private final Map<String, Book> books;

  public ClassicLibrary() {
    books = new TreeMap<>();

    Book nineteenEightyFour = new Book("Nineteen Eighty-Four",
        "George Orwell", "Description");
    Book theLordOfTheRings = new Book("The Lord of the Rings",
        "J. R. R. Tolkien", "Description");

    books.put("Nineteen Eighty-Four", nineteenEightyFour);
    books.put("The Lord of the Rings", theLordOfTheRings);
  }

  @Override
  public String getCategory() {
    return CLASSIC_LIBRARY;
  }

  @Override
  public Book getBook(String name) {
    return books.get(name);
  }
}
