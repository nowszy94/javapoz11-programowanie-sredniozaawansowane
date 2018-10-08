package com.sda.library.domain;

import com.sda.library.domain.exceptions.InvalidPagesValueException;
import com.sda.library.domain.model.Book;
import com.sda.library.domain.port.BooksRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

public class BooksServiceTest {

    private BooksRepository booksRepository;
    private BooksService booksService;

    @Before
    public void before() {
        this.booksRepository = Mockito.mock(BooksRepository.class);
        Mockito.when(booksRepository.findAll()).thenReturn(
                Arrays.asList(
                        Book.builder().title("Dziady III").author("Adam Mickiewicz").year(2000).language("Polish").pages(250).build(),
                        Book.builder().title("Dziady IV").author("Adam Mickiewicz").year(1999).language("Polish").pages(190).build(),
                        Book.builder().title("W pustyni i w puszczy").author("Henryk Sienkiewicz").year(2000).language("Polish").pages(150).build())
        );
        this.booksService = new BooksService(booksRepository);
    }

    @Test
    public void findByTitleShouldReturnEmptyListForNullTitle() {
        //given
        String title = null;

        //when
        List<Book> books = booksService.findByTitle(title);

        //then
        Assert.assertEquals(0, books.size());
    }

    @Test
    public void findByTitleShouldReturnEmptyListForEmptyTitle() {
        //given
        String title = "";

        //when
        List<Book> books = booksService.findByTitle(title);

        //then
        Assert.assertEquals(0, books.size());
    }

    @Test
    public void findByTitleShouldReturnItemsForExistingTitle() {
        //given
        String title = "Dziady";

        //when
        List<Book> books = booksService.findByTitle(title);

        //then
        Assert.assertEquals(2, books.size());
        books.forEach(book -> Assert.assertTrue(book.getTitle().contains(title)));
    }

    @Test
    public void findByTitleShouldReturnEmptyListForNonExistingTitle() {
        //given
        String title = "non-existing";

        //when
        List<Book> books = booksService.findByTitle(title);

        //then
        Assert.assertEquals(0, books.size());
    }

    @Test
    public void findByAuthorShouldReturnEmptyListForNullAuthor() {
        //given
        String author = null;

        //when
        List<Book> books = booksService.findByAuthor(author);

        //then
        Assert.assertEquals(0, books.size());
    }

    @Test
    public void findByAuthorShouldReturnEmptyListForEmptyAuthor() {
        //given
        String author = "";

        //when
        List<Book> books = booksService.findByAuthor(author);

        //then
        Assert.assertEquals(0, books.size());
    }

    @Test
    public void findByAuthorShouldReturnItemsForExistingAuthor() {
        //given
        String author = "Adam Mickiewicz";

        //when
        List<Book> books = booksService.findByAuthor(author);

        //then
        Assert.assertEquals(2, books.size());
        books.forEach(book -> Assert.assertTrue(book.getAuthor().equals(author)));
    }

    @Test
    public void findByAuthorShouldReturnItemsForExistingAuthorLastName() {
        //given
        String authorLastName = "Mickiewicz";
        String expectedAuthorName = "Adam Mickiewicz";

        //when
        List<Book> books = booksService.findByAuthor(authorLastName);

        //then
        Assert.assertEquals(2, books.size());
        books.forEach(book -> Assert.assertTrue(book.getAuthor().equals(expectedAuthorName)));
    }

    @Test
    public void findByAuthorShouldReturnEmptyListForNonExistingAuthor() {
        //given
        String author = "non-existing";

        //when
        List<Book> books = booksService.findByAuthor(author);

        //then
        Assert.assertEquals(0, books.size());
    }

    @Test
    public void findByDateShouldReturnEmptyListForNullDate() {
        //given
        Integer date = null;

        //when
        List<Book> books = booksService.findByDate(date);

        //then
        Assert.assertEquals(0, books.size());
    }

    @Test
    public void findByDateShouldReturnItemsForExistingDate() {
        //given
        Integer date = 2000;

        //when
        List<Book> books = booksService.findByDate(date);

        //then
        Assert.assertEquals(2, books.size());
        books.forEach(book -> Assert.assertEquals(book.getYear(), date));
    }

    @Test
    public void findByDateShouldReturnEmptyListForNonExistingDate() {
        //given
        Integer date = 0;

        //when
        List<Book> books = booksService.findByDate(date);

        //then
        Assert.assertEquals(0, books.size());
    }

    @Test
    public void findByLanguageShouldReturnEmptyListForNullLanguage() {
        //given
        String language = null;

        //when
        List<Book> books = booksService.findByLanguage(language);

        //then
        Assert.assertEquals(0, books.size());
    }
    @Test
    public void findByLanguageShouldReturnEmptyListForEmptyLanguage() {
        //given
        String language = "";

        //when
        List<Book> books = booksService.findByLanguage(language);

        //then
        Assert.assertEquals(0, books.size());
    }
    @Test
    public void findByLanguageShouldReturnItemsForExistingLanguage() {
        //given
        String language = "Polish";

        //when
        List<Book> books = booksService.findByLanguage(language);

        //then
        Assert.assertEquals(3, books.size());
        books.forEach(book -> Assert.assertTrue(book.getLanguage().contains(language)));
    }
    @Test
    public void findByLanguageShouldReturnEmptyListForNonExistingLanguage() {
        //given
        String language = "non-existing";

        //when
        List<Book> books = booksService.findByLanguage(language);

        //then
        Assert.assertEquals(0, books.size());
    }

    @Test
    public void findByPagesRangeShouldReturnBooksForValidPagesRange() throws InvalidPagesValueException {
        //given
        Integer from = 100;
        Integer to = 200;

        //when
        List<Book> books = booksService.findByPagesRange(from, to);

        //then
        Assert.assertEquals(2, books.size());
        books.forEach(e -> Assert.assertTrue(e.getPages() >= from && e.getPages() <= to));
    }

    @Test
    public void findByPagesRangeShouldReturnBooksWhenFromIsEqualToTo() throws InvalidPagesValueException {
        //given
        Integer from = 150;
        Integer to = from;

        //when
        List<Book> books = booksService.findByPagesRange(from, to);

        //then
        Assert.assertEquals(1, books.size());
        Assert.assertEquals(from, books.get(0).getPages());
    }

    @Test(expected = InvalidPagesValueException.class)
    public void findByPagesRangeShouldThrowExceptionWhenFromIsNegative() throws InvalidPagesValueException {
        //given
        Integer from = -100;
        Integer to = 100;

        //when
        booksService.findByPagesRange(from, to);
    }

    @Test(expected = InvalidPagesValueException.class)
    public void findByPagesRangeShouldThrowExceptionWhenToIsNegative() throws InvalidPagesValueException {
        //given
        Integer from = 100;
        Integer to = -100;

        //when
        booksService.findByPagesRange(from, to);
    }

    @Test(expected = InvalidPagesValueException.class)
    public void findByPagesRangeShouldThrowExceptionWhenFromAndToAreBothNegative() throws InvalidPagesValueException {
        //given
        Integer from = -100;
        Integer to = -50;

        //then
        booksService.findByPagesRange(from, to);
    }

    @Test(expected = InvalidPagesValueException.class)
    public void findByPagesRangeShouldThrowExceptionWhenFromIsBiggerThanTo() throws InvalidPagesValueException {
        //given
        Integer from = 150;
        Integer to = 50;

        //when
        booksService.findByPagesRange(from, to);
    }
}
