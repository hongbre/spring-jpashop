package jpabook.jpashop.service;

import jpabook.jpashop.domain.exception.NotEnoughStockException;
import jpabook.jpashop.domain.item.Album;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class ItemServiceTest {

    @Autowired
    ItemService itemService;
    @Autowired
    ItemRepository itemRepository;

    @Test
    public void 앨범_재고_추가() {
        //given
        Item item = new Album();
        item.setName("Album");
        item.setStockQuantity(1);
        itemService.saveItem(item);

        //when
        item.addStock(1);

        //then
        assertEquals(2, item.getStockQuantity());
    }

    @Test
    public void 앨범_재고_감소_예외() {
        //given
        Item item = new Book();
        item.setName("Book");
        item.setStockQuantity(1);
        itemService.saveItem(item);

        //when
        //then
        assertThrows(NotEnoughStockException.class, () -> item.removeStock(2));
    }
}