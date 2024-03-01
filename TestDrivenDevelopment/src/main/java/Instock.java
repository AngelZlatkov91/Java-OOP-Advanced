import jdk.jshell.spi.ExecutionControl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Instock implements ProductStock {
    private  List<Product> dataSet = new ArrayList<>();

    @Override
    public int getCount() {
       return dataSet.size();
    }

    @Override
    public boolean contains(Product product) {
        return dataSet.contains(product);
    }

    @Override
    public void add(Product product) {
        dataSet.add(product);
    }

    @Override
    public void changeQuantity(String product, int quantity) {
        Product first = this.dataSet.stream().filter(p -> p.label.equals(product))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
         first.setQuantity(quantity);
    }

    @Override
    public Product find(int index) {
        return dataSet.get(index);
    }

    @Override
    public Product findByLabel(String label) {
        return this.dataSet.stream().filter(p -> p.label.equals(label))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

    }

    @Override
    public Iterable<Product> findFirstByAlphabeticalOrder(int count) {
        if (count > this.dataSet.size()) {
            return new ArrayList<>();
        }
       return this.dataSet.stream().limit(count)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public Iterable<Product> findAllInRange(double lo, double hi) {
        return this.dataSet.stream().filter(p -> p.getPrice()> lo && p.getPrice() <= hi )
                .sorted((a,b)-> Double.compare(b.getQuantity(),a.getPrice()))
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public Iterable<Product> findAllByPrice(double price) {
        return this.dataSet.stream().filter(p -> p.getPrice() == price)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public Iterable<Product> findFirstMostExpensiveProducts(int count) {
        List<Product> collect = this.dataSet.stream().sorted((a,b)->Double.compare(b.getPrice(),a.getPrice()))
                .limit(count)
                .collect(Collectors.toUnmodifiableList());
        if (collect.size() < count) {
           throw new IllegalArgumentException();
        }
        return collect;

    }

    @Override
    public Iterable<Product> findAllByQuantity(int quantity) {
        return  this.dataSet.stream()
                .filter(p->p.getQuantity() == quantity)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public Iterator<Product> iterator() {

        return this.dataSet.iterator();
    }
}
