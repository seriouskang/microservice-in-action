package com.example.book.application.port.in;

import com.example.book.framework.httpadapter.dto.BookOutputDTO;

public interface InquiryUsecase {
    BookOutputDTO bookInfo(long id);
}
