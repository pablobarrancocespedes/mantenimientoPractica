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
        LinkedNode<T> current = first;
        while (current != null) {
            if (current.getItem().equals(value)) {
                if (current == first) {
                    deleteFirst();
                    return;
                } else if (current == last) {
                    deleteLast();
                    return;
                } else {
                    current.getPrevious().setNext(current.getNext());
                    current.getNext().setPrevious(current.getPrevious());
                    size--;
                    return;
                }
            }
            current = current.getNext();
        }
    }

    // Method within DoubleLinkedList class
@Override
public void sort(Comparator<? super T> comparator) {
    if (first != last) { // If the list has more than one element
        first = mergeSort(first, comparator);
        // Reset last to the last node
        LinkedNode<T> current = first;
        while (current.getNext() != null) {
            current = current.getNext();
        }
        last = current;
    }
}

// Recursive mergeSort method
private LinkedNode<T> mergeSort(LinkedNode<T> head, Comparator<? super T> comparator) {
    if (head == null || head.getNext() == null) {
        return head;
    }
    LinkedNode<T> middle = getMiddle(head);
    LinkedNode<T> nextOfMiddle = middle.getNext();
    middle.setNext(null);

    LinkedNode<T> left = mergeSort(head, comparator);
    LinkedNode<T> right = mergeSort(nextOfMiddle, comparator);

    return merge(left, right, comparator);
}

// Method to get the middle of the list
private LinkedNode<T> getMiddle(LinkedNode<T> head) {
    if (head == null) return null;
    LinkedNode<T> slow = head, fast = head.getNext();
    while (fast != null && fast.getNext() != null) {
        slow = slow.getNext();
        fast = fast.getNext().getNext();
    }
    return slow;
}

// Method to merge two sorted lists
private LinkedNode<T> merge(LinkedNode<T> a, LinkedNode<T> b, Comparator<? super T> comparator) {
    LinkedNode<T> dummyHead = new LinkedNode<>(null, null, null);
    LinkedNode<T> current = dummyHead;
    while (a != null && b != null) {
        if (comparator.compare(a.getItem(), b.getItem()) <= 0) {
            current.setNext(a);
            a.setPrevious(current);
            a = a.getNext();
        } else {
            current.setNext(b);
            b.setPrevious(current);
            b = b.getNext();
        }
        current = current.getNext();
    }
    current.setNext((a == null) ? b : a);
    if (a == null) {
        if (b != null) b.setPrevious(current);
    } else {
        a.setPrevious(current);
    }
    return dummyHead.getNext();
}

}
