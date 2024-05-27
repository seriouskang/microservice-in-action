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

    public boolean delete(String id) {
        Optional<BestBook> found = bestBookRepository.findById(id);

        if(found.isPresent()) {
            bestBookRepository.delete(found.get());
            return true;
        }
        return false;
    }

    public BestBook update(String id, BestBook bestBook) {
        Optional<BestBook> found = bestBookRepository.findById(id);

        if(found.isPresent()) {
            BestBook foundBestBook = found.get();
            foundBestBook.setItem(bestBook.getItem());
            foundBestBook.setRentCount(bestBook.getRentCount());

            return bestBookRepository.save(foundBestBook);
        }

        return null;
    }

    public BestBook save(BestBook bestBook) {
        return bestBookRepository.save(bestBook);
    }
}
