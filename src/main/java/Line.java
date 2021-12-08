import java.awt.geom.Point2D;

class Line {
    private Point2D p1, p2;

    Line(Point2D p1, Point2D p2){
        this.p1 = p1;
        this.p2 = p2;
    }

    Point2D getIntersectionPoint(Line otherLine) throws NoIntersectionError{
        var x1 = p1.getX();
        var y1 = p1.getY();
        var x2 = p2.getX();
        var y2 = p2.getY();

        var x3 = otherLine.p1.getX();
        var y3 = otherLine.p1.getY();
        var x4 = otherLine.p2.getX();
        var y4 = otherLine.p2.getY();

        var denominator = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);

        if(Math.abs(denominator) < 0.0001){
            throw new NoIntersectionError();
        }

        var intersectionX = ((x1 * y2 - y1 * x2) * (x3 - x4) - (x1 - x2) * (x3 * y4 - y3 * x4)) / denominator;
        var intersectionY = ((x1 * y2 - y1 * x2) * (y3 - y4) - (y1 - y2) * (x3 * y4 - y3 * x4)) / denominator;

        return new Point2D.Double(intersectionX, intersectionY);
    }

    double getIntersectionAngle(Line otherLine) throws NoIntersectionError{
        var origin = new Point2D.Double(0, 0);
        var gradient1 = new Point2D.Double(p2.getX() - p1.getX(), p2.getY() - p1.getY());
        var gradient2 = new Point2D.Double(otherLine.p2.getX() - otherLine.p1.getX(), otherLine.p2.getY() - otherLine.p1.getY());
        var gradient1Length = gradient1.distance(origin);
        var gradient2Length = gradient2.distance(origin);

        var gradient1Normalized = new Point2D.Double(gradient1.getX() / gradient1Length, gradient1.getY() / gradient1Length);
        var gradient2Normalized = new Point2D.Double(gradient2.getX() / gradient2Length, gradient2.getY() / gradient2Length);

        var scalarProduct = gradient1Normalized.getX() * gradient2Normalized.getX() + gradient1Normalized.getY() * gradient2Normalized.getY();

        if(Math.abs(scalarProduct) > 0.9999){
            throw new NoIntersectionError();
        }

        var angle = Math.acos(scalarProduct) * (360 / (2 * Math.PI));
        angle = Math.round(angle * 100.0) / 100.0;
        return angle;
    }

    static class NoIntersectionError extends Exception {
        NoIntersectionError() {
            super("The lines are equal or parallel");
        }
    }
}
