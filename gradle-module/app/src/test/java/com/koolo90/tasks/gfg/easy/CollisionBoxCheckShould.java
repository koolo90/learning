package com.koolo90.tasks.gfg.easy;

import com.koolo90.framework.test.assertions.Assertions;
import org.junit.jupiter.api.Test;

/**
 * https://www.geeksforgeeks.org/dsa/find-two-rectangles-overlap/
 */
public class CollisionBoxCheckShould {
    @Test
    void usingCoordsOnly() {
        Assertions.assertTrue(doOverlap(0,10,10,0,5,5,15,0));
    }

    private boolean doOverlap(int tl1x,
                              int tl1y,
                              int br1x,
                              int br1y,
                              int tl2x,
                              int tl2y,
                              int br2x,
                              int br2y) {
        if (tl1x > br2x || tl2x > br1x) return false;
        if (br1y > tl2y || br2y > tl1y) return false;
        return true;
    }

    @Test
    void usingPoints() {
        Point l1 = new Point(0, 10);
        Point r1 = new Point(10, 0);
        Point l2 = new Point(5, 5);
        Point r2 = new Point(15, 0);

        Assertions.assertTrue(doOverlap(l1, r1, l2, r2));
    }

    @Test
    void usingBox() {
        Box boxA = new Box(new Point(0, 10), new Point(10, 0));
        Box boxB = new Box(new Point(5, 5), new Point(15, 0));

        Assertions.assertTrue(doOverlap(boxA, boxB));
    }

    private record Point(int x, int y) {}
    private record Box(Point tl, Point br) {}

    boolean doOverlap(Box boxA, Box boxB) {
        return doOverlap(boxA.tl, boxA.br, boxB.tl, boxB.br);
    }

    boolean doOverlap(Point l1, Point r1, Point l2, Point r2) {
        if (l1.x > r2.x || l2.x > r1.x) return false;
        if (r1.y > l2.y || r2.y > l1.y) return false;
        return true;
    }
}
