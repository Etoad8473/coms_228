package tests;

import edu.iastate.cs228.hw4.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class SplayTreeTest {
    private static final int from = 0;
    private static final int to = 100;

    private SplayTree<Integer> testTree;
    private Random random;

    @Before
    public void init() {
        random = new Random(1);
        testTree = new SplayTree<>();
        List<Integer> list = new ArrayList<>();
        for (int i = from; i <= to; i++) {
            list.add(i);
        }
        Collections.shuffle(list, random);
    }

    @Test
    public void defaultConstructorTest() {
        SplayTree<Integer> splayTree = new SplayTree<>();
        Assert.assertEquals(0, splayTree.size());
        Assert.assertNull(splayTree.getRoot());
    }

    @Test
    public void rootConstructorTest() {
        int rootData = 2;
        SplayTree<Integer> splayTree = new SplayTree<>(rootData);
        Assert.assertEquals(1, splayTree.size());
        Assert.assertEquals(Integer.valueOf(rootData), splayTree.getRoot());
    }

    @Test
    public void copyConstructorTest() {
        SplayTree<Integer> splayTree = new SplayTree<>();
        List<Integer> list = new ArrayList<>();
        for (int i = from; i <= to; i++) {
            list.add(i);
        }
        Collections.shuffle(list, random);

        splayTree.addAll(list);
        SplayTree<Integer> splayTree2 = new SplayTree<>(splayTree);

        for (int i : list) {
            Assert.assertEquals(Integer.valueOf(i), splayTree2.findElement(i));
            Assert.assertEquals(Integer.valueOf(i), splayTree2.getRoot());
        }

        Assert.assertNull(splayTree2.findElement(to + 1));
        Assert.assertEquals(Integer.valueOf(to), splayTree2.getRoot());


        Assert.assertNull(splayTree2.findElement(from - 1));
        Assert.assertEquals(Integer.valueOf(from), splayTree2.getRoot());
    }

    @Test
    public void addBSTTest() {
        SplayTree<Integer> splayTree = new SplayTree<>();
        List<Integer> list = new ArrayList<>();
        for (int i = from; i <= to; i++) {
            list.add(i);
        }
        Collections.shuffle(list, random);

        int count = 0;
        for (int i : list) {
            splayTree.addBST(i);
            count++;
            Assert.assertEquals(count, splayTree.size());
            Assert.assertEquals(list.get(0), splayTree.getRoot());
        }
    }

    @Test
    public void addTest() {
        SplayTree<Integer> splayTree = new SplayTree<>();
        List<Integer> list = new ArrayList<>();
        for (int i = from; i <= to; i++) {
            list.add(i);
        }
        Collections.shuffle(list, random);

        int count = 0;
        for (int i : list) {
            splayTree.add(i);
            count++;
            Assert.assertEquals(count, splayTree.size());
            Assert.assertEquals(Integer.valueOf(i), splayTree.getRoot());
        }
    }

    @Test
    public void clearTest() {
        testTree.clear();
        Assert.assertEquals(0, testTree.size());
        Assert.assertNull(testTree.getRoot());
    }

    @Test
    public void containsTest() {
        SplayTree<Integer> splayTree = new SplayTree<>();
        List<Integer> list = new ArrayList<>();
        for (int i = from; i <= to; i++) {
            list.add(i);
        }
        Collections.shuffle(list, random);

        splayTree.addAll(list);

        for (int i : list) {
            Assert.assertTrue(splayTree.contains(i));
            Assert.assertEquals(Integer.valueOf(i), splayTree.getRoot());
        }

        Assert.assertFalse(splayTree.contains(to + 1));
        Assert.assertEquals(Integer.valueOf(to), splayTree.getRoot());


        Assert.assertFalse(splayTree.contains(from - 1));
        Assert.assertEquals(Integer.valueOf(from), splayTree.getRoot());
    }

    @Test
    public void findElementTest() {
        SplayTree<Integer> splayTree = new SplayTree<>();
        List<Integer> list = new ArrayList<>();
        for (int i = from; i <= to; i++) {
            list.add(i);
        }
        Collections.shuffle(list, random);

        splayTree.addAll(list);

        for (int i : list) {
            Assert.assertEquals(Integer.valueOf(i), splayTree.findElement(i));
            Assert.assertEquals(Integer.valueOf(i), splayTree.getRoot());
        }

        Assert.assertNull(splayTree.findElement(to + 1));
        Assert.assertEquals(Integer.valueOf(to), splayTree.getRoot());


        Assert.assertNull(splayTree.findElement(from - 1));
        Assert.assertEquals(Integer.valueOf(from), splayTree.getRoot());
    }

    @Test
    public void splayTest() {
        SplayTree<Integer> splayTree = new SplayTree<>();
        List<Integer> list = new ArrayList<>();
        for (int i = from; i <= to; i++) {
            list.add(i);
        }
        Collections.shuffle(list, random);

        splayTree.addAll(list);

        int size = splayTree.size();
        for (int i : list) {
            splayTree.splay(i);
            Assert.assertEquals(size, splayTree.size());
            Assert.assertEquals(Integer.valueOf(i), splayTree.getRoot());
        }

        splayTree.splay(to + 1);
        Assert.assertEquals(Integer.valueOf(to), splayTree.getRoot());


        splayTree.splay(from - 1);
        Assert.assertEquals(Integer.valueOf(from), splayTree.getRoot());
    }

    @Test
    public void toStringTest() {
        SplayTree<Integer> splayTree = new SplayTree<>();
        Assert.assertEquals("null\n", splayTree.toString());

        splayTree.addBST(5);
        splayTree.addBST(10);
        Assert.assertEquals("5\n    null\n    10" , splayTree.toString());

        splayTree.addBST(15);
        Assert.assertEquals("5\n" +
                "    null\n" +
                "    10\n" +
                "        null\n" +
                "        15\n" , splayTree.toString());

        splayTree.addBST(0);
        Assert.assertEquals("5\n" +
                "    0\n" +
                "    10\n" +
                "        null\n" +
                "        15\n" 
               , splayTree.toString());
    }

    @Test
    public void removeTest() {
        SplayTree<Integer> splayTree = new SplayTree<>();
        List<Integer> list = new ArrayList<>();
        for (int i = from; i <= to; i++) {
            list.add(i);
        }
        Collections.shuffle(list, random);
        splayTree.addAll(list);

        int size = splayTree.size();
        for (int i = from; i <= to; i += 2) {
            Assert.assertTrue(splayTree.remove(i));
            size--;
            Assert.assertEquals(size, splayTree.size());
        }
        Assert.assertFalse(splayTree.remove(from - 1));
        Assert.assertEquals(size, splayTree.size());
        Assert.assertFalse(splayTree.remove(to + 1));
        Assert.assertEquals(size, splayTree.size());

        Iterator<Integer> treeIter = splayTree.iterator();
        int curr = from + 1;
        while (treeIter.hasNext()) {
            Assert.assertEquals(Integer.valueOf(curr), treeIter.next());
            curr += 2;
        }

    }

    @Test
    public void iteratorTest() {
        SplayTree<Integer> splayTree = new SplayTree<>();
        List<Integer> list = new ArrayList<>();
        List<Integer> straight = new ArrayList<>();
        for (int i = from; i <= to; i++) {
            list.add(i);
            straight.add(i);
        }
        Collections.shuffle(list, random);
        splayTree.addAll(list);

        Iterator<Integer> straightIter = straight.iterator();
        Iterator<Integer> treeIter = splayTree.iterator();
        while (straightIter.hasNext()) {
            Assert.assertTrue(treeIter.hasNext());
            int straightNext = straightIter.next();
            int treeNext = treeIter.next();
            Assert.assertEquals(straightNext, treeNext);
        }
        Assert.assertFalse(treeIter.hasNext());
    }

    @Test
    public void iteratorRemoveTest() {
        SplayTree<Integer> splayTree = new SplayTree<>();
        List<Integer> list = new ArrayList<>();
        for (int i = from; i <= to; i++) {
            list.add(i);
        }
        Collections.shuffle(list, random);
        splayTree.addAll(list);

        boolean found = true;
        int size = splayTree.size();
        while (found) {
            Iterator<Integer> treeIter = splayTree.iterator();
            found = false;
            while (treeIter.hasNext()) {
                int data = treeIter.next();
                if (data % 2 == 0) {
                    treeIter.remove();
                    found = true;
                    size--;
                    break;
                }
            }
            Assert.assertEquals(size, splayTree.size());
        }

        Iterator<Integer> treeIter = splayTree.iterator();
        int curr = from + 1;
        while (treeIter.hasNext()) {
            int data = treeIter.next();
            Assert.assertEquals(curr, data);
            curr += 2;
        }
    }
}
