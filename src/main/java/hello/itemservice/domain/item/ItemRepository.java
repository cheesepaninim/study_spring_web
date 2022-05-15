package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {

    // 실무에서는 아래 경우에 long 이나 HashMap 사용하면 값이 꼬일 수 있음
    // ConcurrentHashMap 등을 사용 해야함
    
    private static final Map<Long, Item> store = new HashMap<>();
    private static long sequence = 0L;

    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id) {
        return store.get(id);
    }

    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    // 실제로는 ItemParamDto 객체를 만들어서 Id 를 제외한 파라미터를 넣어두는 것이 맞다.
    public void update(Long itemId, Item updateParam) {
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    public void delete(Long itemId) {
        store.remove(itemId);
    }

    public void clearStore() {
        store.clear();
    }

}
