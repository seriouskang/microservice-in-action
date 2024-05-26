package com.example.book.framework.httpadapter;

import com.example.book.application.port.in.AddBookUsecase;
import com.example.book.application.port.in.InquiryUsecase;
import com.example.book.framework.httpadapter.dto.BookInfoDTO;
import com.example.book.framework.httpadapter.dto.BookOutputDTO;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
@Api(tags = {"book controller"})
public class BookController {
    private final AddBookUsecase addBookUsecase;
    private final InquiryUsecase inquiryUsecase;

    @PostMapping
    public ResponseEntity<BookOutputDTO> createBook(@RequestBody BookInfoDTO bookInfoDTO) {
        BookOutputDTO added = addBookUsecase.addBook(bookInfoDTO);
        return new ResponseEntity<>(added, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookOutputDTO> book(@PathVariable Long id) {
        BookOutputDTO found = inquiryUsecase.bookInfo(id);
        return found != null ? ResponseEntity.ok(found) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
