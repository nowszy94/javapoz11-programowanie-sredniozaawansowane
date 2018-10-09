package com.sda.library.domain;

import com.sda.library.domain.model.Book;
import com.sda.library.domain.model.Borrow;
import com.sda.library.domain.model.BorrowStatus;
import com.sda.library.domain.port.BorrowRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;

public class BorrowServiceTest {

    private BorrowService borrowService;
    private BooksService booksService;
    private BorrowRepository borrowRepository;

    @Before
    public void init() {
        this.booksService = Mockito.mock(BooksService.class);
        this.borrowRepository = Mockito.mock(BorrowRepository.class);
        this.borrowService = new BorrowService(booksService, borrowRepository);
    }

    @Test
    public void borrowShouldBorrowBook() {
        //given
        Mockito.when(booksService.findById(Mockito.anyString())).thenReturn(
                Optional.of(
                        Book.builder().id("book-id").title("Dziady III").author("Adam Mickiewicz").year(2000).language("Polish").pages(250).build()
                )
        );
        String id = "book-id";
        String username = "test-user";

        //when
        Borrow borrow = borrowService.borrow(id, username);

        //then
        Assert.assertTrue(borrow.getBook() != null);
        Assert.assertEquals(username, borrow.getUser());
        Assert.assertEquals(BorrowStatus.BORROWED, borrow.getBorrowStatus());
        Mockito.verify(borrowRepository, Mockito.times(1)).save(Mockito.any());
    }

}
