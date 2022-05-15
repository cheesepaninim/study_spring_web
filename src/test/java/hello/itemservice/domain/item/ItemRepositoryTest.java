package hello.itemservice.domain.item;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }



    @Test
    void save() {
        // given
        Item item = new Item("item-A", 10000, 10);

        // when
        Item savedItem = itemRepository.save(item);

        // then
        Item findItem = itemRepository.findById(item.getId());
        assertThat(findItem).isEqualTo(savedItem);
        // Assertions.assertThat(findItem).isEqualTo(savedItem);
        // => Assertions 드래그하고 Alt + enter
        // => Add on-demand static import.. 하면 위와 같이 줄여 쓸 수 있다.
    }

    @Test
    void findAll() {
        // given
        Item item1 = new Item("item-1", 10000, 30);
        Item item2 = new Item("item-2", 500000, 3);
        Item item3 = new Item("item-3", 100, 100);

        itemRepository.save(item1);
        itemRepository.save(item2);
        Item savedItem3 = itemRepository.save(item1);
        
        // when
        List<Item> result = itemRepository.findAll();

        // then
        assertThat(result.size()).isEqualTo(3);
        assertThat(result).contains(item1, item2, savedItem3);
//        assertThat(result).contains(item1, item2, item3);
    }

    @Test
    void update() {
        // given
        Item item = new Item("item-A", 10000, 10);
        Item savedItem = itemRepository.save(item);
        Long itemId = savedItem.getId();

        // when
        Item updatePram = new Item("item-B", 20000, 5);
        itemRepository.update(itemId, updatePram);

        // then
        Item findItem = itemRepository.findById(itemId);
        assertThat(findItem).isEqualTo(savedItem);
        assertThat(findItem.getPrice() * findItem.getQuantity()).isEqualTo(savedItem.getPrice() * savedItem.getQuantity());
    }

    @Test
    void delete() {
        // given
        Item item = new Item("item-A", 10000, 10);
        Item savedItem = itemRepository.save(item);
        Long itemId = savedItem.getId();

        // when
        itemRepository.delete(itemId);

        // then
        List<Item> list = itemRepository.findAll();
        assertThat(list).doesNotContain(savedItem);
//        assertThat(list).contains(savedItem);
    }

}