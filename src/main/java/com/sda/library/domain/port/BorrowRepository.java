package com.sda.library.domain.port;

import com.sda.library.domain.model.Borrow;

public interface BorrowRepository {
    void save(Borrow borrow);
}
