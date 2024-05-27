package com.example.bestbook.infrastructure;

import com.example.bestbook.domain.model.BestBook;
import com.example.bestbook.domain.model.vo.Item;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BestBookRepository extends MongoRepository<BestBook, String> {
    BestBook findByItem(Item item);
}
