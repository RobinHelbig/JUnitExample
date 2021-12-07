import org.junit.jupiter.api.Test;

import java.awt.geom.Point2D;

import static org.junit.jupiter.api.Assertions.*;

class LineTest {
    private Line line1 = new Line(new Point2D.Double(0,0), new Point2D.Double(5,5));
    private Line line2 = new Line(new Point2D.Double(0,0), new Point2D.Double(1,1));
    private Line line3 = new Line(new Point2D.Double(0,5), new Point2D.Double(5,0));

    @Test
    void getIntersectionPoint() throws Line.NoIntersectionError {
        assertThrows(
                Line.NoIntersectionError.class,
                () -> line1.getIntersectionPoint(line2),
                "excepted line1.getIntersectionPoint(line2) to throw but it didn't"
        );

        assertEquals(new Point2D.Double(2.5,2.5), line1.getIntersectionPoint(line3));
    }

    @Test
    void getIntersectionAngle() throws Line.NoIntersectionError{
        assertThrows(
                Line.NoIntersectionError.class,
                () -> line1.getIntersectionAngle(line2),
                "excepted line1.getIntersectionAngle(line2) to throw but it didn't"
        );

        assertEquals(90, line1.getIntersectionAngle(line3));
    }
}