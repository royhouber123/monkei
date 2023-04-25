
//Don't change the class name
public class Container{
	private Point data;//Don't delete or change this field;
	private Container nextContainer;
	private Container prevContainer;
	private Container copy;
	public Container(Point p)
	{
		this.data = p;
		this.nextContainer = null;
		this.prevContainer = null;
		this.copy = null;
	}
	public void setCopy(Container copy)
	{
		this.copy = copy;
	}
	public Container getCopy()
	{
		return this.copy;
	}
	public void setPoint(Point point) {
		this.data = point;
	}
	public void setNextContainer(Container nextContainer) {
		this.nextContainer = nextContainer;
	}
	public void setPrevContainer(Container prevContainer) {
		this.prevContainer = prevContainer;
	}
	public Point getPoint() {
		return data;
	}
	public Container getNextContainer() {
		return this.nextContainer;
	}
	public Container getPrevContainer() {return this.prevContainer;}
	
	//Don't delete or change this function
	public Point getData()
	{
		return data;
	}
}
