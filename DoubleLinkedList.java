package deque;

import java.util.Comparator;

public class DoubleLinkedList<T> implements DoubleLinkedQueue<T> {

    private LinkedNode<T> first;
    private LinkedNode<T> last;
    private int size;

    public DoubleLinkedList() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    @Override
    public void prepend(T value) {
        LinkedNode<T> newNode = new LinkedNode<>(value, null, first);
        if (isEmpty()) {
            last = newNode;
        } else {
            first.setPrevious(newNode);
        }
        first = newNode;
        size++;
    }

    @Override
    public void append(T value) {
        LinkedNode<T> newNode = new LinkedNode<>(value, last, null);
        if (isEmpty()) {
            first = newNode;
        } else {
            last.setNext(newNode);
        }
        last = newNode;
        size++;
    }

    @Override
    public void deleteFirst() {
        if (isEmpty()) {
            throw new DoubleLinkedQueueException("Deque is empty");
        }
        if (size == 1) {
            first = null;
            last = null;
        } else {
            first = first.getNext();
            first.setPrevious(null);
        }
        size--;
    }

    @Override
    public void deleteLast() {
        if (isEmpty()) {
            throw new DoubleLinkedQueueException("Deque is empty");
        }
        if (size == 1) {
            first = null;
            last = null;
        } else {
            last = last.getPrevious();
            last.setNext(null);
        }
        size--;
    }

    @Override
    public T first() {
        if (isEmpty()) {
            throw new DoubleLinkedQueueException("Deque is empty");
        }
        return first.getItem();
    }

    @Override
    public T last() {
        if (isEmpty()) {
            throw new DoubleLinkedQueueException("Deque is empty");
        }
        return last.getItem();
    }

    @Override
    public int size() {
        return size;
    }

    private boolean isEmpty() {
        return size == 0;
    }


    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new DoubleLinkedQueueException("Index: " + index + ", Size: " + size);
        }
        LinkedNode<T> actual = first;
        for (int i = 0; i < index; i++) {
            actual = actual.getNext();
        }
        return actual.getItem();
    }

    @Override
    public boolean contains(T value) {
        if(value == null){
            throw new DoubleLinkedQueueException("Value es nulo");
        } else {
            LinkedNode<T> actual = first;
            while (actual != null) {
                if (actual.getItem().equals(value)) {
                    return true;
                }
                actual = actual.getNext();
            }
        return false;
        }
        
    }

    @Override
    public void remove(T value) {
        LinkedNode<T> actual = first;
        while (actual != null) {
            if (actual.getItem().equals(value)) {
                if (actual == first) {
                    deleteFirst();
                    return;
                } else if (actual == last) {
                    deleteLast();
                    return;
                } else {
                    actual.getPrevious().setNext(actual.getNext());
                    actual.getNext().setPrevious(actual.getPrevious());
                    size--;
                    return;
                }
            }
            actual = actual.getNext();
        }
    }

   
@Override
public void sort(Comparator<? super T> comparator) {
    if (size > 1) { 
        first = mergeSort(first, comparator);
        LinkedNode<T> actual = first;
        while (actual.getNext() != null) {
            actual = actual.getNext();
        }
        last = actual;
    }
}

private LinkedNode<T> mergeSort(LinkedNode<T> pivote, Comparator<? super T> comparator) {
    if (pivote == null || pivote.getNext() == null) {
        return pivote;
    }
    LinkedNode<T> medio = getMedio(pivote);
    LinkedNode<T> siguiente = medio.getNext();
    medio.setNext(null);

    LinkedNode<T> izq = mergeSort(pivote, comparator);
    LinkedNode<T> der = mergeSort(siguiente, comparator);

    return merge(izq, der, comparator);
}

private LinkedNode<T> getMedio(LinkedNode<T> pivote) {
    if (pivote == null) return null;
    LinkedNode<T> actual = pivote, siguiente = pivote.getNext();
    while (siguiente != null && siguiente.getNext() != null) {
        actual = actual.getNext();
        siguiente = siguiente.getNext().getNext();
    }
    return actual;
}

private LinkedNode<T> merge(LinkedNode<T> a, LinkedNode<T> b, Comparator<? super T> comparator) {
    LinkedNode<T> falsoPivote = new LinkedNode<>(null, null, null);
    LinkedNode<T> actual = falsoPivote;
    while (a != null && b != null) {
        if (comparator.compare(a.getItem(), b.getItem()) <= 0) {
            actual.setNext(a);
            a.setPrevious(actual);
            a = a.getNext();
        } else {
            actual.setNext(b);
            b.setPrevious(actual);
            b = b.getNext();
        }
        actual = actual.getNext();
    }
    actual.setNext((a == null) ? b : a);
    if (a == null) {
        if (b != null) b.setPrevious(actual);
    } else {
        a.setPrevious(actual);
    }
    return falsoPivote.getNext();
}

}
