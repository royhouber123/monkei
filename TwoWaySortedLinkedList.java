public class TwoWaySortedLinkedList<T>
{
    private Container head;
    private Container tail;
    private int size;

    public Container getTail() {
        return tail;
    }

    public Container getHead() {
        return head;
    }

    public TwoWaySortedLinkedList()
    {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }
    public void addPoint(Container add, boolean axis)
    {
        boolean added = false;
        if(head == null)
        {
            this.head = add;
            this.tail = add;
            added = true;
        }
        else
        {
            if(axis)
            {
                Container index = this.head;
                while(!added && index != null && add.getData().getX() > index.getData().getX())
                {
                    if(index.getNextContainer() == null)
                    {
                        this.tail.setNextContainer(add);
                        this.tail.getNextContainer().setPrevContainer(this.tail);
                        this.tail = this.tail.getNextContainer();
                        added = true;
                    }
                    index = index.getNextContainer();
                }
                if(!added && index.getPrevContainer() == null)
                {
                    this.head.setPrevContainer(add);
                    this.head.getPrevContainer().setNextContainer(this.head);
                    this.head = this.head.getPrevContainer();
                }
                else if(!added)
                {
                    index.getPrevContainer().setNextContainer(add);
                    add.setNextContainer(index);
                    add.setPrevContainer(index.getPrevContainer());
                    index.setPrevContainer(add);
                }
            }
            else
            {
                Container index = this.head;
                while(!added && index != null && add.getData().getY() > index.getData().getY())
                {
                    if(index.getNextContainer() == null)
                    {
                        this.tail.setNextContainer(add);
                        this.tail.getNextContainer().setPrevContainer(this.tail);
                        this.tail = this.tail.getNextContainer();
                        added = true;
                    }
                    index = index.getNextContainer();
                }
                if(!added && index.getPrevContainer() == null)
                {
                    this.head.setPrevContainer(add);
                    this.head.getPrevContainer().setNextContainer(this.head);
                    this.head = this.head.getPrevContainer();
                }
                else if(!added)
                {
                    index.getPrevContainer().setNextContainer(add);
                    add.setNextContainer(index);
                    add.setPrevContainer(index.getPrevContainer());
                    index.setPrevContainer(add);
                }
            }
        }
        this.size++;
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
        this.size--;
    }

    public int getPointsCountInRange(int min, int max, boolean axis)
    {
        Container index = this.head;
        int count = 0;
        if(axis)
        {
            while(index.getData().getX() < min)
                index = index.getNextContainer();
            while(index != null && index.getData().getX() <= max)
            {
                index = index.getNextContainer();
                count++;
            }
        }
        else
        {
            while(index.getData().getY() < min)
                index = index.getNextContainer();
            while(index != null && index.getData().getY() <= max)
            {
                index = index.getNextContainer();
                count++;
            }
        }
        return count;
    }

    public int getSize()
    {
        return this.size;
    }
}
