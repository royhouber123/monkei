
public class DataStructure implements DT {

	private TwoWayLinkedList<Container> xAxis;
	private TwoWayLinkedList<Container> yAxis;

	//////////////// DON'T DELETE THIS CONSTRUCTOR ////////////////
	public DataStructure()
	{
		xAxis = new TwoWayLinkedList<Container>();
		yAxis = new TwoWayLinkedList<Container>();
	}
	public DataStructure(TwoWayLinkedList<Container> oneAxis , boolean axis)
	{
		if (axis){
			this.xAxis = oneAxis;
			this.yAxis = new TwoWayLinkedList<>();
		}
		else {
			this.yAxis = oneAxis;
			this.xAxis = new TwoWayLinkedList<>();
		}
	}
	public DataStructure(TwoWayLinkedList<Container> xAxis, TwoWayLinkedList<Container> yAxis)
	{
		this.xAxis = xAxis;
		this.yAxis = yAxis;
	}

	@Override
	public void addPoint(Point point)
	{
		xAxis.addPoint(new Container(point), true);
		yAxis.addPoint(new Container(point), false);
	}

	@Override
	public Point[] getPointsInRangeRegAxis(int min, int max, Boolean axis) {
		Point [] result = new Point[xAxis.getPointsCountInRange(min,max,axis)];
		if(axis)
		{
			Container index = xAxis.getHead();
			int i = 0;
			while(index.getData().getX() < min)
				index = index.getNextContainer();
			while(index.getData().getX() <= max)
			{
				result[i] = index.getData();
				i++;
			}
		}
		else
		{
			Container index = yAxis.getHead();
			int i = 0;
			while(index.getData().getY() < min)
				index = index.getNextContainer();
			while(index.getData().getY() <= max)
			{
				result[i] = index.getData();
				i++;
			}
		}
		return result;
	}

	@Override
	public Point[] getPointsInRangeOppAxis(int min, int max, Boolean axis)
	{
		return getPointsInRangeRegAxis(min,max,!axis);
	}

	@Override
	public double getDensity() {
		return (xAxis.getSize()/((xAxis.getTail().getData().getX()-xAxis.getHead().getData().getX())*(yAxis.getTail().getData().getY()-yAxis.getHead().getData().getY())));
	}

	@Override
	public void narrowRange(int min, int max, Boolean axis) {
		if(axis)
		{
			Container first = xAxis.getHead();
			Container last = xAxis.getTail();
			while (first.getData().getX() < min)
			{
				yAxis.deletePoint(first.getCopy());
				first = first.getNextContainer();
				xAxis.deletePoint(first.getPrevContainer());
			}
			while (last.getData().getX() > max)
			{
				yAxis.deletePoint(last.getCopy());
				last = last.getPrevContainer();
				xAxis.deletePoint(last.getNextContainer());
			}
		}
		else
		{
			Container first = yAxis.getHead();
			Container last = yAxis.getTail();
			while (first.getData().getY() < min)
			{
				xAxis.deletePoint(first.getCopy());
				first = first.getNextContainer();
				yAxis.deletePoint(first.getPrevContainer());
			}
			while (last.getData().getY() > max)
			{
				xAxis.deletePoint(last.getCopy());
				last = last.getPrevContainer();
				yAxis.deletePoint(last.getNextContainer());
			}
		}
	}

	@Override
	public Boolean getLargestAxis() {
		return (xAxis.getTail().getData().getX()-xAxis.getHead().getData().getX() > yAxis.getTail().getData().getY()-yAxis.getHead().getData().getY());
	}

	@Override
	public Container getMedian(Boolean axis) {
		int count = 0;
		int listSize = xAxis.getSize();
		Container index;
		if(axis)
		{
			index = xAxis.getHead();
			while(count < listSize/2)
				index = index.getNextContainer();
		}
		else
		{
			index = yAxis.getHead();
			while(count < listSize/2)
				index = index.getNextContainer();
		}
		return index;
	}

	@Override
	public Point[] nearestPairInStrip(Container container, double width, Boolean axis) {
		Point[] nearestPair = new Point[2];
		boolean headIsTooSmall = true, tailIsTooBig = true;
		if (axis) {
			Container head = xAxis.getHead();
			Container tail = xAxis.getTail();
			while (headIsTooSmall & tailIsTooBig) {
				headIsTooSmall = container.getData().getX() - head.getData().getX() > width/2;
				tailIsTooBig = tail.getData().getX() - container.getData().getX() > width/2;
				if (headIsTooSmall)
					head = head.getNextContainer();
				if (tailIsTooBig)
					tail = tail.getPrevContainer();
			}
			if (!headIsTooSmall){
				if (head.getNextContainer().getData().getX() - container.getData().getX() < width/2) {
					nearestPair[0] = head.getData();
					nearestPair[1] = head.getNextContainer().getData();
				}
			}
			else if (!tailIsTooBig){
				if (container.getData().getX() - tail.getPrevContainer().getData().getX() < width/2) {
					nearestPair[0] = tail.getData();
					nearestPair[1] = tail.getPrevContainer().getData();
				}
			}
		}
		else {
			Container head = yAxis.getHead();
			Container tail = yAxis.getTail();
			while (headIsTooSmall & tailIsTooBig ) {
				headIsTooSmall = container.getData().getY() - head.getData().getY() > width/2;
				tailIsTooBig = tail.getData().getY() - container.getData().getY() > width/2;
				if (headIsTooSmall)
					head = head.getNextContainer();
				if (tailIsTooBig)
					tail = tail.getPrevContainer();
			}
			if (!headIsTooSmall){
				if (head.getNextContainer().getData().getY() - container.getData().getY() < width/2) {
					nearestPair[0] = head.getData();
					nearestPair[1] = head.getNextContainer().getData();
				}
			}
			else if (!tailIsTooBig){
				if (container.getData().getY() - tail.getPrevContainer().getData().getY() < width/2) {
					nearestPair[0] = tail.getData();
					nearestPair[1] = tail.getPrevContainer().getData();
				}
			}
		}
		return nearestPair;
	}

	public TwoWayLinkedList<Container> getListInRange(Container first, Container last, boolean axis)
	{
		TwoWayLinkedList<Container> result = new TwoWayLinkedList<>();
		while(first != last)
		{
			result.addPoint(first,axis);
			first = first.getNextContainer();
		}
		result.addPoint(last,axis);
		return result;
	}

	@Override
	public Point[] nearestPair() {
		// TODO Auto-generated method stub
		return null;
	}

	
	//TODO: add members, methods, etc.
	
}

