package com.sda.library.domain;

import com.sda.library.domain.model.Book;
import com.sda.library.domain.model.Borrow;
import com.sda.library.domain.model.BorrowStatus;
import com.sda.library.domain.port.BorrowRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
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

    @Test
    public void findByUserAndStatusShouldReturnBorrowsForExistingUserAndBorrowedStatus() {
        //given
        Mockito.when(borrowRepository.findAll()).thenReturn(
                Arrays.asList(
                        Borrow.builder()
                                .user("test-user")
                                .borrowStatus(BorrowStatus.BORROWED)
                                .book(Book.builder().id("id-1").title("Dzieci z Bulerbyn").build())
                                .build(),
                        Borrow.builder()
                                .user("test-user")
                                .borrowStatus(BorrowStatus.BORROWED)
                                .book(Book.builder().id("id-2").title("Dziady IV").build())
                                .build(),
                        Borrow.builder()
                                .user("admin-user")
                                .borrowStatus(BorrowStatus.BORROWED)
                                .book(Book.builder().id("id-3").title("Folwark zwierzecy").build())
                                .build(),
                        Borrow.builder()
                                .user("test-user")
                                .borrowStatus(BorrowStatus.RETURNED)
                                .book(Book.builder().id("id-4").title("W pustyni i w puszczy").build())
                                .build()
                        )
        );

        String username = "test-user";
        BorrowStatus status = BorrowStatus.BORROWED;

        //when
        List<Borrow> borrows = borrowService.findByUserAndStatus(username, status);

        //then
        Assert.assertEquals(2, borrows.size());
        borrows.forEach(e ->
                Assert.assertTrue(username.equals(e.getUser()) && status.equals(e.getBorrowStatus()))
        );
    }
}
