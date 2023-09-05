import java.util.ArrayList;
import java.util.List;

interface Transformable {
    void move(double dx, double dy);
    void rotate(double angle);
}

class Point implements Transformable {
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void move(double dx, double dy) {
        x += dx;
        y += dy;
    }

    public void rotate(double angleInDegrees) {
        double radians = Math.toRadians(angleInDegrees);
        double newX = x * Math.cos(radians) - y * Math.sin(radians);
        double newY = x * Math.sin(radians) + y * Math.cos(radians);
        x = newX;
        y = newY;
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}

class Line implements Transformable {
    Point startPoint;
    Point endPoint;

    public Line(Point startPoint, Point endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public void move(double dx, double dy) {
        startPoint.move(dx, dy);
        endPoint.move(dx, dy);
    }

    public void rotate(double angle) {
        startPoint.rotate(angle);
        endPoint.rotate(angle);
    }
}

class Circle implements Transformable {
    private Point center;
    private double radius;

    public Circle(Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    public void move(double dx, double dy) {
        center.move(dx, dy);
    }

    public void rotate(double angle) {
        center.rotate(angle);
    }

    public String toString() {
        return "Center: " + center + ", Radius: " + radius;
    }
}

class Aggregation {
    private List<Transformable> elements;

    public Aggregation() {
        elements = new ArrayList<>();
    }

    public void addElement(Transformable element) {
        elements.add(element);
    }

    public void move(double dx, double dy) {
        for (Transformable element : elements) {
            element.move(dx, dy);
        }
    }

    public void rotate(double angle) {
        for (Transformable element : elements) {
            element.rotate(angle);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Point pointA = new Point(1, 2);
        Point pointB = new Point(3, 4);
        Line line = new Line(pointA, pointB);

        Point circleCenter = new Point(5, 5);
        Circle circle = new Circle(circleCenter, 3);

        Aggregation aggregation = new Aggregation();
        aggregation.addElement(pointA);
        aggregation.addElement(pointB);
        aggregation.addElement(line);
        aggregation.addElement(circle);

        System.out.println("Before moving and rotating:");
        System.out.println(pointA);
        System.out.println(pointB);
        System.out.println("Line: " + line.startPoint + " to " + line.endPoint);
        System.out.println("Circle center: " + circleCenter);

        aggregation.move(2, 2);
        aggregation.rotate(45);

        System.out.println("After moving and rotating:");
        System.out.println(pointA);
        System.out.println(pointB);
        System.out.println("Line: " + line.startPoint + " to " + line.endPoint);
        System.out.println("Circle center: " + circleCenter);
    }
}
