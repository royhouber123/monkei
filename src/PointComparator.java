import java.util.Arrays;
import java.util.Comparator;

public class PointComparator implements Comparator<Point> {

    private final Point[] points;

    public PointComparator(Point[] points) {
        this.points = points;
    }

    public int compare(Point a, Point b) {
        for (int i = 0; i < points.length; i++) {
            if (points[i] == a) continue;
            if (points[i] == b) continue;
            double distanceA = Math.sqrt(Math.pow(a.getX() - points[i].getX(), 2) + Math.pow(a.getY() - points[i].getY(), 2));
            double distanceB = Math.sqrt(Math.pow(b.getX() - points[i].getX(), 2) + Math.pow(b.getY() - points[i].getY(), 2));
            if (distanceA != distanceB) {
                return Double.compare(distanceA, distanceB);
            }
        }
        return 0;
    }
}

