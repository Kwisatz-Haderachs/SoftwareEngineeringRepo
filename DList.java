/**
 * Implements a generic doubly linked list where the type of each element is E.
 */
public class DList<E> {
    /**
     * For a nonempty DList, mHead is a reference to the first Node in the DList. For an empty
     * DList, mHead will be null.
     */
    private Node<E> mHead;

    /**
     * The number of Nodes containing data in this DList. For an empty DList mSize is 0.
     */
    private int mSize;

    /**
     * For a nonempty DList mTail is a reference to the last Node in the DList. For an empty DList,
     * mTail will be null.
     */
    private Node<E> mTail;

    /**
     * Creates an empty DList. For an empty DList, mHead = null, mTail = null, and mSize = 0.
     */
    public DList() {
        setHead(null);
        setTail(null);
        setSize(0);
    }

    /**
     * Creates a new DList with one element containing pData.
     */
    public DList(E pData) {
        Node<E> newNode = new Node<E>(pData);

        setHead(newNode);

        setTail(newNode);

        setSize(1);
    }

    public void add(int pIndex, E pData) throws IndexOutOfBoundsException {
        if (pIndex < 0 || pIndex > getSize()) throw new IndexOutOfBoundsException();

        if (pIndex == getSize()) {
            Node<E> newNode = new Node<E>(pData, getTail(), null);

            if (isEmpty()) setHead(newNode);
            else getTail().setNext(newNode);

            setTail(newNode);
        }

        else {
            Node<E> node = getNodeAt(pIndex);

            Node<E> newNode = new Node<E>(pData, node.getPrev(), node);

            if (pIndex != 0) node.getPrev().setNext(newNode);

            node.setPrev(newNode);

            if (pIndex == 0) setHead(newNode);
        }

        setSize(getSize() + 1);
    }

    public void append(E pData) {
        add(getSize(), pData);
    }

    /**
     * Removes all of the elements from the DList. After this operation the DList will be empty.
     */
    public void clear() {
        while (!isEmpty()) { remove(0); }
    }

    public DList<E> clone() {
        DList<E> cloneList = new DList<E>();
        Node<E> traverse = getHead();
        while (traverse != null) {
            cloneList.append(traverse.getData());
            traverse = traverse.getNext();
        }
        return cloneList;
    }

    /**
     * Returns the element at index pIndex.
     *
     * Thows IndexOutOfBoundsException if pIndex < 0 or pIndex >= mSize.
     */
    public E get(int pIndex) throws IndexOutOfBoundsException {
        return getNodeAt(pIndex).getData();
    }

    /**
     * Accessor method for the mHead field.
     */
    protected Node<E> getHead() {
        return mHead;
    }

    /**
     * Returns a reference to the Node at index pIndex.
     *
     * Thows IndexOutOfBoundsException if pIndex < 0 or pIndex >= getSize()
     */
    protected Node<E> getNodeAt(int pIndex) throws IndexOutOfBoundsException {
        if (pIndex < 0 || pIndex >= getSize()) throw new IndexOutOfBoundsException();

        if (pIndex == 0) return getHead();
        else if (pIndex == getSize() - 1) return getTail();

        Node<E> node = getHead().getNext();
        for (int index = 1; index < pIndex; ++index) node = node.getNext();
        return node;
    }

    /**
     * Accessor method for the mSize field.
     */
    public int getSize() {
        return mSize;
    }

    /**
     * Accessor method for the mTail field.
     */
    protected Node<E> getTail() {
        return mTail;
    }

    /**
     * Returns true if this DList is empty.
     */
    public boolean isEmpty() {
        return getSize() == 0;
    }

    /**
     * Prepends a new Node storing pData to this DList.
     */
    public void prepend(E pData) {
        add(0, pData);
    }

    /**
     * Removes the element at index pIndex from this DList. Shifts any succeeding elements to
     * the left (i.e., subtracts one from their indices). Returns the element that was removed from
     * the list.
     */
    public E remove(int pIndex) throws IndexOutOfBoundsException {
        Node<E> node = getNodeAt(pIndex);

        // Are we removing the only element in a list with one element?
        if (getSize() == 1) {
            setHead(null);
            setTail(null);
        }

        else if (pIndex == 0) {
            node.getNext().setPrev(null);

            setHead(node.getNext());
        }

        else if (pIndex == getSize() - 1) {
            node.getPrev().setNext(null);

            setTail(node.getPrev());
        }

        else {
            node.getPrev().setNext(node.getNext());
            node.getNext().setPrev(node.getPrev());
        }

        setSize(getSize() - 1);

        return node.getData();
    }

    /**
     * Replaces the element at the specified position in this list with the specified element.
     * Returns the element that was previously stored at pIndex.
     */
    public E set(int pIndex, E pData) throws IndexOutOfBoundsException {
        Node<E> node = getNodeAt(pIndex);
        E original = node.getData();
        node.setData(pData);
        return original;
    }

    /**
     * Mutator method for the mHead field.
     */
    protected void setHead(Node<E> pHead) {
        mHead = pHead;
    }

    /**
     * Mutator method for the mSize field.
     */
    protected void setSize(int pSize) {
        mSize = pSize;
    }

    /**
     * Mutator method for the mTail field.
     */
    protected void setTail(Node<E> pTail) {
        mTail = pTail;
    }

    /**
     * Returns a string representation of this DList where we define string representation be the
     * string representation of each of the Nodes.
     */
    @Override
    public String toString() {
        String string = "";
        int i;
        for (i = 0; i < getSize() - 1; ++i) {
            string += get(i) + " ";
        }
        if (!isEmpty()) {
            string += get(i);
        }
        return string;
    }

    protected static class Node<E> {
        /**
         * The data stored in this Node.
         */
        E mData;

        /**
         * A reference to the succeeding Node in the DList.
         */
        Node<E> mNext;

        /**
         * A reference to the preceding Node in the DList.
         */
        Node<E> mPrev;

        /**
         * Creates a new Node storing no data and with mNext and mPrev set to null.
         */
        public Node() {
            this(null);
        }

        /**
         * Creates a new Node storing pData as the data and with mNext and mPrev set to null.
         */
        public Node(E pData) {
            setData(pData);
            setNext(null);
            setPrev(null);
        }

        /**
         * Creates a new Node storing pData as the data, mPrev initialized to pPrev, and mNext
         * initialized to pNext.
         */
        public Node(E pData, Node<E> pPrev, Node<E> pNext) {
            setData(pData);
            setPrev(pPrev);
            setNext(pNext);
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object pNode) {
            Node<E> node = (Node<E>)pNode;
            // Check condition 1.
            if (node == null) return false;

            // Check condition 2.
            if (this == node) return true;

            // Check condition 3.
            if (this.getClass() != node.getClass()) return false;

            // Check condition 4.
            if (getData() == node.getData() && getNext() == node.getNext() &&
            getPrev() == node.getPrev()) return true;

            // Condition 5.
            return false;
        }

        /**
         * See: https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html#hashCode()
         * See: https://www.javaworld.com/article/2073618/java-s-system-identityhashcode.html
         */
        @Override
        public int hashCode() {
            return System.identityHashCode(this);
        }

        /**
         * Accessor method for the mData instance variable.
         */
        public E getData() {
            return mData;
        }

       /**
         * Accessor method for the mNext instance variable.
         */
        public Node<E> getNext() {
            return mNext;
        }

       /**
         * Accessor method for the mPrev instance variable.
         */
        public Node<E> getPrev() {
            return mPrev;
        }

        /**
         * Mutator method for the mData instance variable.
         */
        public void setData(E pData) {
            mData = pData;
        }

        /**
         * Mutator method for the mNext instance variable.
         */
        public void setNext(Node<E> pNext) {
            mNext = pNext;
        }

        /**
         * Mutator method for the mPrev instance variable.
         */
        public void setPrev(Node<E> pPrev) {
            mPrev = pPrev;
        }

        /**
         * Returns a string representation of this Node where we define the string representation
         * to be the string representation of the data stored in this Node.
         */
        @Override
        public String toString() {
            return "" + getData();
        }
    }
}
