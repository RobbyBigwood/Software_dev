import org.junit.internal.builders.JUnit3Builder;
import org.junit.jupiter.api.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests of the Circle class
 * To be run with JUnit 5: https://junit.org/junit5/
 * JUnit 5 User Guide: https://junit.org/junit5/docs/current/user-guide/
 *
*/

@DisplayName(" Circle 🔴 🔴 ♥ 🔴 🔴 Tests ")
class CircleTest {
    Circle origin;
    Circle axesCircle;
    CircleTest() {
    }

    /**
     * test cases:
     * uhhh find like 2 more
     * test uhm... test uhhh, the thig
     *
     */

    @Test
    @DisplayName("Constructor Tests")
    void testConstructors() {
        Circle defaultCircle = new Circle();
        assertEquals(true,defaultCircle.getX()==0,"default x value");
        assertEquals(true,defaultCircle.getY()==0, "default y value");
        assertEquals(true,defaultCircle.getRadius()==1,"default radius");
        assertEquals(false,defaultCircle.getRadius()==2, "uhhhh not default radius?");

    } // end testConstructor()

    @Test
    @DisplayName("Test Bound")
	void testBound() {
        Circle origin = new Circle();
        Circle boundCircle = new Circle(5, 0,1);
        assertEquals(0,origin.angleBetween(boundCircle),"angleBetween is not reverting to 0");

    }

    @BeforeEach
    void setUpTests() {
        origin = new Circle();
        axesCircle = new Circle(0,5,1);
    }
    @ParameterizedTest(name = "Test with Parameter {0}")
    @ValueSource(ints = {0,1,2,3})
    @DisplayName("Test 90 degrees (test if 0, any positive y is 90)")
    void testAxes(int param) {
        axesCircle.setY(param);
        assertEquals(90,origin.angleBetween(axesCircle),"Angle is not calculated correctly");
    }
    @ParameterizedTest(name = "coordinates for testing x axis")
    @CsvSource({
            "1, 0",
            "-1, 0",
            "5,0",
            "0,5",
    })
    @DisplayName("Test multiple x axes")
    void test180(int param0, int param1) {
        axesCircle.setX(param1);
        axesCircle.setY(0);
        assertEquals(180,origin.angleBetween(axesCircle),"Either setters are off or angle is not being changed");
    }
    @Test
    @DisplayName("Test 270 degrees")
    void test270(){
        axesCircle.setX(0);
        axesCircle.setY(-5);
        assertEquals(270,origin.angleBetween(axesCircle),"Angle is not being changed to correct value");
    }

    @Test
    @DisplayName("Test same location")
    void testSameLocation() {
        assertEquals(0,origin.angleBetween(origin),"There is an angle between circles in the same position");
    }
} // end class CircleTests
