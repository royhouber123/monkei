import java.util.Arrays;

public class DataStructure implements DT {

	private TwoWaySortedLinkedList<Container> xAxis;
	private TwoWaySortedLinkedList<Container> yAxis;
	public TwoWaySortedLinkedList<Container> getAxis(boolean b)
	{
		if(b)
			return xAxis;
		return yAxis;
	}

	//////////////// DON'T DELETE THIS CONSTRUCTOR ////////////////
	public DataStructure()
	{
		xAxis = new TwoWaySortedLinkedList<Container>();
		yAxis = new TwoWaySortedLinkedList<Container>();
	}

	@Override
	public void addPoint(Point point)
	{
		Container copy = new Container(point);
		Container copy2 = new Container(point);
		xAxis.addPoint(copy, true);
		yAxis.addPoint(copy2, false);
		copy.setCopy(copy2);
		copy2.setCopy(copy);
	}

	@Override
	public Point[] getPointsInRangeRegAxis(int min, int max, Boolean axis) {
		Point [] result;
		if(axis)
		{
			result = new Point[xAxis.getPointsCountInRange(min,max,axis)];
			Container index = xAxis.getHead();
			int i = 0;
			while(index.getData().getX() < min)
				index = index.getNextContainer();
			while(index.getData().getX() <= max)
			{
				result[i] = index.getData();
				index = index.getNextContainer();
				i++;
			}
		}
		else
		{
			result = new Point[yAxis.getPointsCountInRange(min,max,axis)];
			Container index = yAxis.getHead();
			int i = 0;
			while(index.getData().getY() < min)
				index = index.getNextContainer();
			while(index.getData().getY() <= max)
			{
				result[i] = index.getData();
				index = index.getNextContainer();
				i++;
			}
		}
		return result;
	}

	@Override
	public Point[] getPointsInRangeOppAxis(int min, int max, Boolean axis)
	{
		Point [] result = new Point[getAxis(axis).getPointsCountInRange(min,max,axis)];
		Container index = this.getAxis(!axis).getHead();
		int j = 0;
		while(index != null )
		{
			if(axis)
			{
				if(index.getData().getX() >= min && index.getData().getX() <= max)
				{
					result[j] = index.getData();
					j++;
				}
				index = index.getNextContainer();
			}
			else
			{
				if(index.getData().getY() >= min && index.getData().getY() <= max)
				{
					result[j] = index.getData();
					j++;
				}
				index = index.getNextContainer();
			}
		}
		return result;
	}

	@Override
	public double getDensity()
	{
		return ((double)xAxis.getSize()/((xAxis.getTail().getData().getX()-xAxis.getHead().getData().getX())*(yAxis.getTail().getData().getY()-yAxis.getHead().getData().getY())));
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
			{
				count++;
				index = index.getNextContainer();
			}
		}
		else
		{
			index = yAxis.getHead();
			while(count < listSize/2)
			{
				count++;
				index = index.getNextContainer();
			}
		}
		return index;
	}

	@Override
	public Point[] nearestPairInStrip(Container container, double width, Boolean axis)
	{
		Container right = getMedian(axis).getNextContainer();
		Container left = getMedian(axis).getPrevContainer();
		Point [] pointsInRange = new Point[countPointsInRange(container,width,axis)];
		Point [] nearestPoints = new Point[pointsInRange.length];
		int i = 1;
		pointsInRange[0] = getMedian(axis).getData();
		if(pointsInRange.length >= 2)
		{
			if(axis)
			{
				while (right != null && right.getData().getX() - getMedian(axis).getData().getX() <= width/2)
				{
					pointsInRange[i] = new Point(right.getData());
					i++;
					right= right.getNextContainer();
				}
				while (left != null && getMedian(axis).getData().getX() - left.getData().getX() <= width/2)
				{
					pointsInRange[i] = new Point(left.getData());
					i++;
					left = left.getPrevContainer();
				}
			}
			else
			{
				while (right != null && right.getData().getY() - getMedian(axis).getData().getY() <= width/2)
				{
					pointsInRange[i] = new Point(right.getData());
					i++;
					right = right.getNextContainer();
				}
				while (left != null && getMedian(axis).getData().getY() - left.getData().getY() <= width/2)
				{
					pointsInRange[i] = new Point(left.getData());
					i++;
					left = left.getPrevContainer();
				}
			}
			Arrays.sort(pointsInRange, new PointComparator(pointsInRange));
			for(int j = 0 ; j < pointsInRange.length ; j++)
				nearestPoints[j] = pointsInRange[j];
		}
		return nearestPoints;

	}

	public int countPointsInRange(Container container, double width, boolean axis)
	{
		Container right = getMedian(axis).getNextContainer();
		Container left = getMedian(axis).getPrevContainer();
		int count = 1;
		if(axis)
		{
			while (right != null && right.getData().getX() - getMedian(axis).getData().getX() <= width/2)
			{
				count++;
				right = right.getNextContainer();
			}
			while (left != null && getMedian(axis).getData().getX() - left.getData().getX() <= width/2)
			{
				count++;
				left = left.getPrevContainer();
			}
		}
		else
		{
			while (right != null && right.getData().getY() - getMedian(axis).getData().getY() <= width/2)
			{
				count++;
				right = right.getNextContainer();
			}
			while (left != null && getMedian(axis).getData().getY() - left.getData().getY() <= width/2)
			{
				count++;
				left = left.getPrevContainer();
			}
		}
		return count;
	}

	public int distance(Point p1, Point p2)
	{
		return (Math.abs(p1.getX()-p2.getX())+Math.abs(p1.getY()-p2.getY()));
	}

	@Override
	public Point[] nearestPair() {
		return nearestPairInStrip(getMedian(true),xAxis.getTail().getData().getX()-xAxis.getHead().getData().getX(),true);
	}


	
}

