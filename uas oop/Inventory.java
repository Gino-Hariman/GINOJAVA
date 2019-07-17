package UTS2;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class Inventory {
    // Item, Qty
    private List<InventoryItem> items;
    private int sum;

    public Inventory() {
        this.items = new ArrayList<InventoryItem>();
    }

    public List<InventoryItem> getItems() { return this.items; }

    // mengembalikan banyak total item
    public int getItemTotalCount() {
        this.sum = 0;
        for (InventoryItem i: items) {
            this.sum += i.getQty();
        }
        return this.sum;
    }

    public boolean contains(Item item) {
        for (InventoryItem ii: items) {
            if (ii.getItem().getName().equals(item.getName())) {
                return true;
            }
        }
        return false;
    }

    public int search(Item item) {
        for (InventoryItem ii: items) {
            if (ii.getItem().getName().equals(item.getName())) {
                return items.indexOf(ii);
            }
        }
        return -1;
    }

    // mengembalikan item tertentu, kembalikan -1 jika tidak ditemukan
    public int getItemCount(Item item) {
        if (contains(item)) return items.get(search(item)).getQty();
        return -1;
    }

    // mengurangi jumlah qty item tertentu, kembalikan jumlah qty yang berhasil diambil
    // jika qty > banyak item di inventory, kembalikan banyak item di inventory
    // jika tidak temukan throw NoSuchElementException
    // jika qty <= 0 throw IllegalArgumentException
    public int takeItem(Item item, int qty) {
        if (!contains(item)) {
            throw new NoSuchElementException();
        }
        if (qty <= 0) throw new IllegalArgumentException();

        int count = getItemCount(item);
        if (qty < count) {
            items.get(search(item)).setQty(count-qty);
            return qty;
        } else if (qty > count) {
            items.get(search(item)).setQty(0);
            return count;
        }
        return -1;
        
    }

    // menambahkan item tertentu ke inventory sebanyak inventory
    // jika qty <= 0 throw IllegalArgumentException
    public void putItem(Item item, int qty) {
        if (qty <= 0) throw new IllegalArgumentException();
        items.add(new InventoryItem(item, qty));
    }

    // kembalikan list items yang diurutkan berdasarkan harga item (descending)
    public List<InventoryItem> getOrderByPrice() {
        this.items.sort(new Comparator<InventoryItem>() {
            public int compare(InventoryItem item1, InventoryItem item2) {
                return (int)( ((item1.getItem().getPrice()) - (item2.getItem().getPrice()) )* -1);
            }
        });
        return this.items;
    }
    // kembalikan list items yang diurutukan berdasarkan berat item (descending)
    public List<InventoryItem> getOrderByWeight() {
        this.items.sort(new Comparator<InventoryItem>() {
            public int compare(InventoryItem item1, InventoryItem item2) {
                return (int) ( ((item1.getItem().getWeight())*100- (item2.getItem().getWeight()*100)) )* -1;
            }
        });
        return this.items;
    }
    // kembalikan list items yang diurutkan berdasarkan qty item (descending)
    public List<InventoryItem> getOrderByQty() {
        this.items.sort(new Comparator<InventoryItem>() {
            public int compare(InventoryItem item1, InventoryItem item2) {
                return (int)(item1.getQty()- item2.getQty()) * -1;
            }
        });
        return this.items;
    }
}
