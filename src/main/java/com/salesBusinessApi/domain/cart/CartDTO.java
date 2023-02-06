package com.salesBusinessApi.domain.cart;

import com.salesBusinessApi.domain.ordereditem.OrderedItem;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {

    @NotNull
    private int code;

    private List<OrderedItem> listOrderedItems = new List<OrderedItem>() {
        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(Object o) {
            return false;
        }

        @Override
        public Iterator<OrderedItem> iterator() {
            return null;
        }

        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @Override
        public <T> T[] toArray(T[] a) {
            return null;
        }

        @Override
        public boolean add(OrderedItem orderedItem) {
            return false;
        }

        @Override
        public boolean remove(Object o) {
            return false;
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean addAll(Collection<? extends OrderedItem> c) {
            return false;
        }

        @Override
        public boolean addAll(int index, Collection<? extends OrderedItem> c) {
            return false;
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            return false;
        }

        @Override
        public void clear() {

        }

        @Override
        public OrderedItem get(int index) {
            return null;
        }

        @Override
        public OrderedItem set(int index, OrderedItem element) {
            return null;
        }

        @Override
        public void add(int index, OrderedItem element) {

        }

        @Override
        public OrderedItem remove(int index) {
            return null;
        }

        @Override
        public int indexOf(Object o) {
            return 0;
        }

        @Override
        public int lastIndexOf(Object o) {
            return 0;
        }

        @Override
        public ListIterator<OrderedItem> listIterator() {
            return null;
        }

        @Override
        public ListIterator<OrderedItem> listIterator(int index) {
            return null;
        }

        @Override
        public List<OrderedItem> subList(int fromIndex, int toIndex) {
            return null;
        }
    };

    private float totalValue = 0;

    private String description;

    private boolean active = true;

}
