/**
 * File Description: Contains the undo history model used by DrawingInternalFrame’s `undoStack` public variable.
 */

import java.util.Stack;

public class SizedStack<T> extends Stack<T> {

    private final int maxSize;

    /**
     * Constructor takes in a predefined size
     * @param size
     */
    public SizedStack(int size) {
        super();
        this.maxSize = size;
    }

    /**
     * Add an item to the `undo` stack
     * @param object
     * @return
     */
    @Override
    public Object push(Object object) {
        while (this.size() > maxSize) {
            this.remove(0);
        }
        return super.push((T) object);
    }
}