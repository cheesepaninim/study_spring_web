package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

// @RequiredArgsConstructor 가 있으면 이 아래 부분 생략 가능
/*
// @Autowired // Spring 에서 생성자가 하나만 있으면 생략 가능
//    public BasicItemController(ItemRepository itemRepository) {
//        this.itemRepository = itemRepository;
//    }
*/

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        Item item1 = new Item("item-A", 10000, 30);
        Item item2 = new Item("item-B", 500000, 3);
        Item item3 = new Item("item-C", 100, 100);

        itemRepository.save(item1);
        itemRepository.save(item2);
        itemRepository.save(item3);
    }

    /**
     * [ 상품 목록 ]
     *
     * @param model
     * @return
     */
    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);

        return "basic/items";
    }

    /**
     * [ 상품 상세 ]
     * @param itemId
     * @param model
     * @return
     */
    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);

        return "basic/item";
    }


}
