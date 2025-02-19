package Controller.item;

import Model.Item;

import java.util.List;

public interface itemService {

    boolean addItem();
    boolean updateItem();
    boolean deleteItem();
    Item searchItem(String code);
    List<Item> getAll();
}
