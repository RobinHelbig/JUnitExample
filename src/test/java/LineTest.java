import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.awt.geom.Point2D;

import static org.junit.jupiter.api.Assertions.*;

class LineTest {
    static TestCase[] testData(){
        Line line1 = new Line(new Point2D.Double(0,0), new Point2D.Double(5,5));
        Line line2 = new Line(new Point2D.Double(0,0), new Point2D.Double(1,1));
        Line line3 = new Line(new Point2D.Double(0,5), new Point2D.Double(5,0));
        Line line4 = new Line(new Point2D.Double(1,1), new Point2D.Double(1,2));
        Line line5 = new Line(new Point2D.Double(3,4), new Point2D.Double(2,5));

        return new TestCase[]{
                new TestCase(line1, line2, null,null),
                new TestCase(line1, line3, new Point2D.Double(2.5,2.5), 90.0),
                new TestCase(line4, line5, new Point2D.Double(1.0,6.0), 45.0),
        };
    }

    @ParameterizedTest
    @MethodSource(value =  "testData")
    void getIntersectionPoint(TestCase testCase) throws Line.NoIntersectionError {
        if(testCase.intersectionPoint == null){
            assertThrows(
                    Line.NoIntersectionError.class,
                    () -> testCase.line1.getIntersectionPoint(testCase.line2),
                    "excepted line1.getIntersectionPoint(line2) to throw but it didn't"
            );
        } else {
            assertEquals(testCase.intersectionPoint,  testCase.line1.getIntersectionPoint(testCase.line2));
        }
    }

    @ParameterizedTest
    @MethodSource(value =  "testData")
    void getIntersectionAngle(TestCase testCase) throws Line.NoIntersectionError{
        if(testCase.intersectionAngle == null){
            assertThrows(
                    Line.NoIntersectionError.class,
                    () -> testCase.line1.getIntersectionAngle(testCase.line2),
                    "excepted line1.getIntersectionAngle(line2) to throw but it didn't"
            );
        } else {
            assertEquals(testCase.intersectionAngle, testCase.line1.getIntersectionAngle(testCase.line2));
        }
    }

    static class TestCase{
        Line line1, line2;
        Point2D intersectionPoint;
        Double intersectionAngle;

        TestCase(Line line1, Line line2, Point2D intersectionPoint, Double intersectionAngle){
            this.line1 = line1;
            this.line2 = line2;
            this.intersectionPoint = intersectionPoint;
            this.intersectionAngle = intersectionAngle;
        }
    }
}