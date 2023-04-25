public class TwoWayLinkedList<T>
{
    private Container head;
    private Container tail;

    public Container getTail() {
        return tail;
    }

    public Container getHead() {
        return head;
    }

    public TwoWayLinkedList()
    {
        this.head = null;
        this.tail = null;
    }
    public void addPoint(Container add)
    {
        if(head == null)
        {
            this.head = add;
            this.tail = add;
        }
        else
        {
            this.tail.setNextContainer(add);
            this.tail.getNextContainer().setPrevContainer(tail);
            this.tail = this.tail.getNextContainer();
        }
    }
    public void deletePoint(Container delete)
    {
        if(delete.getPrevContainer() == null)
        {
            this.head = delete.getNextContainer();
            delete.getNextContainer().setPrevContainer(null);
            delete.setNextContainer(null);
        }
        else if(delete.getNextContainer() == null)
        {
            this.tail = delete.getPrevContainer();
            this.tail.setNextContainer(null);
            delete.setPrevContainer(null);
        }
        else
        {
            delete.getPrevContainer().setNextContainer(delete.getNextContainer());
            delete.getNextContainer().setPrevContainer(delete.getPrevContainer());
        }
    }
}
