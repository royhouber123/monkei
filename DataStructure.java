
public class DataStructure implements DT {

	private TwoWayLinkedList<Container> xAxis;
	private TwoWayLinkedList<Container> yAxis;

	//////////////// DON'T DELETE THIS CONSTRUCTOR ////////////////
	public DataStructure()
	{
		xAxis = new TwoWayLinkedList<Container>();
		yAxis = new TwoWayLinkedList<Container>();
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
	public Point[] nearestPairInStrip(Container container, double width,
			Boolean axis)
	{
		Point [] nearestPair = new Point [2];
		Container med = getMedian(axis);
		return nearestPairInStrip(container,width,axis,nearestPair,med);
	}

	public Point[] nearestPairInStrip(Container container, double width,
									  Boolean axis, Point[] nearestPair, Container med)
	{
		if(med.getData().getX()-container.getData().getX() > width/2)
	}

	public TwoWayLinkedList<Container> getListInRange(Container first, Container last, boolean axis)
	{
		TwoWayLinkedList<Container> result = new TwoWayLinkedList<Container>();
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

