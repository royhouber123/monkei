import java.util.Arrays;

public class Main {

    public static void main(String [] args)
    {
        DataStructure d1 = new DataStructure();
        Point p1 = new Point(1,2);
        Point p2 = new Point(8,3);
        Point p3 = new Point(4,1);
        Point p4 = new Point(7,5);
        Point p5 = new Point(3,6);
        d1.addPoint(p1);
        d1.addPoint(p2);
        d1.addPoint(p3);
        d1.addPoint(p4);
        d1.addPoint(p5);
        Container pointerX = d1.getAxis(true).getHead();
        Container pointerY = d1.getAxis(false).getHead();
        System.out.print("x list : ");
        while(pointerX != null)
        {
            System.out.print(pointerX.getData().toString() + "  ");
            pointerX = pointerX.getNextContainer();
        }
        System.out.println();
        System.out.print("y list : ");
        while(pointerY != null)
        {
            System.out.print(pointerY.getData().toString() + "  ");
            pointerY = pointerY.getNextContainer();
        }
        System.out.println();
        Point[] test = d1.getPointsInRangeRegAxis(3,7,true);
        System.out.print("points in range 3 - 7 in x axis : ");
        for(int i = 0 ; i < test.length ; i++)
            System.out.print(test[i] + "  ");
        System.out.println();
        System.out.print("points in range 3 - 4 in y axis : ");
        test = d1.getPointsInRangeRegAxis(3,4,false);
        for(int i = 0 ; i < test.length ; i++)
            System.out.print(test[i] + "  ");
        System.out.println();
        System.out.print("point in opp x axis range 2-4 : ");
        test = d1.getPointsInRangeOppAxis(2,4,true);
        for(int i = 0 ; i < test.length ; i++)
            System.out.print(test[i] + "  ");
        System.out.println();
        System.out.print("point in opp y axis range 2-5 : ");
        test = d1.getPointsInRangeOppAxis(2,5,false);
        for(int i = 0 ; i < test.length ; i++)
            System.out.print(test[i] + "  ");
        System.out.println();
        System.out.println("structure density : " + d1.getDensity());
        System.out.println("narrowing y list range to 4 - 5 : ");
        d1.narrowRange(4,5,false);
        pointerX = d1.getAxis(true).getHead();
        pointerY = d1.getAxis(false).getHead();
        System.out.print("new x list : ");
        while(pointerX != null)
        {
            System.out.print(pointerX.getData().toString() + "  ");
            pointerX = pointerX.getNextContainer();
        }
        System.out.println();
        System.out.print("new y list : ");
        while(pointerY != null)
        {
            System.out.print(pointerY.getData().toString() + "  ");
            pointerY = pointerY.getNextContainer();
        }
        System.out.println();
        boolean largest = d1.getLargestAxis();
        if(largest)
            System.out.println("the largest axis is X");
        else
            System.out.println("the largest axis is X");
        System.out.println("getting median of x axis : " + d1.getMedian(true).getData().toString());
        System.out.println("getting median of y axis : " + d1.getMedian(false).getData().toString());
        Point newP1 = new Point(1,2);
        Point newP2 = new Point(5,3);
        Point newP3 = new Point(8,10);
        Point newP4 = new Point(6,11);
        Point newP5 = new Point(89,0);
        Point newP6 = new Point(90,1);
        Point newP7 = new Point(93,2);
        Point newP8 = new Point(44,23);
        d1.addPoint(newP1);
        d1.addPoint(newP2);
        d1.addPoint(newP3);
        d1.addPoint(newP4);
        d1.addPoint(newP5);
        d1.addPoint(newP6);
        d1.addPoint(newP7);
        d1.addPoint(newP8);
        pointerX = d1.getAxis(true).getHead();
        pointerY = d1.getAxis(false).getHead();
        System.out.println("adding points to the stucture :");
        System.out.print("new x list : ");
        while(pointerX != null)
        {
            System.out.print(pointerX.getData().toString() + "  ");
            pointerX = pointerX.getNextContainer();
        }
        System.out.println();
        System.out.print("new y list : ");
        while(pointerY != null)
        {
            System.out.print(pointerY.getData().toString() + "  ");
            pointerY = pointerY.getNextContainer();
        }
        System.out.println();
        Point[] res = d1.nearestPairInStrip(d1.getMedian(true),200,true);
        for(int i = 0 ; i < res.length ; i++)
            System.out.println(res[i].toString());

    }
}
