package slogo;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Feel free to completely change this code or delete it entirely. 
 */
class MainTest extends DukeApplicationTest {
    // how close do real valued numbers need to be to count as the same
    static final double TOLERANCE = 0.0005;
    static final int WIDTH = 200;
    static final int HEIGHT = 200;
    static final int NEW_HEIGHT = 300;

    private final Group root = new Group();
    private final Rectangle rectangle = new Rectangle(WIDTH, HEIGHT);

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(root, 200, 200);
        stage.setScene(scene);
        stage.show();

        root.getChildren().add(rectangle);
    }

    /**
     * Test a method from Main.
     */
    @Test
    void testVersionIsReady () {
	      Main m = new Main();
	      // different ways to test double results
        assertEquals(1, Math.round(m.getVersion() * 1000));
        assertTrue(m.getVersion() < 1);
        assertEquals(0.001, m.getVersion(), TOLERANCE);
    }

    @Test
    void testJavaFxTestsRun() {
        javafxRun(() -> rectangle.setHeight(NEW_HEIGHT));
        assertEquals(rectangle.getHeight(), NEW_HEIGHT);
    }
}
