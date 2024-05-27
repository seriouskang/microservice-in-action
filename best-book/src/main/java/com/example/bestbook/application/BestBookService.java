package com.example.bestbook.application;

import com.example.bestbook.domain.model.BestBook;
import com.example.bestbook.domain.model.vo.Item;
import com.example.bestbook.infrastructure.BestBookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class BestBookService {
    private final BestBookRepository bestBookRepository;

    public List<BestBook> findAll() {
        return bestBookRepository.findAll();
    }

    public Optional<BestBook> findById(String id) {
        return bestBookRepository.findById(id);
    }

    public void increaseBestBookCount(Item item) {
        BestBook found = bestBookRepository.findByItem(item);

        if(found != null) {
            found.increaseBestBookCount();
        } else {
            log.info("register best book: {}", item);
            found = BestBook.registerBestBook(item);
        }
        save(found);
    }

    public BestBook save(BestBook bestBook) {
        return bestBookRepository.save(bestBook);
    }
}
