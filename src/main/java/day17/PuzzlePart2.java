package day17;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class PuzzlePart2 {
    static class Container {
        int qty;

        public Container(int qty) {
            this.qty = qty;
        }

        @Override
        public String toString() {
            return "Container{" +
                    "qty=" + qty +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        final File inputFile = new File(Class.forName(Thread.currentThread().getStackTrace()[1].getClassName()).getResource("Input.txt").toURI().getPath());
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile)));

        String line;
        List<Container> containers = new LinkedList<>();

        int totalQty = 150;

        while ((line = reader.readLine()) != null) {
            int containerSize = Integer.parseInt(line);
            containers.add(new Container(containerSize));
        }

        System.out.println(containers.size());
        Set<Set<Container>> pathCache = new LinkedHashSet<>();
        AtomicInteger pathCount = new AtomicInteger();

        getPossibleOutcomes(totalQty, containers, pathCache, pathCount, new LinkedHashSet<>());
        System.out.println(pathCache.size());
        System.out.println(pathCount);
        System.out.println(pathCache);
    }

    static void getPossibleOutcomes(int qtyLeft, List<Container> containers, Set<Set<Container>> pathCache, AtomicInteger pathCount, Set<Container> path) {
        if (qtyLeft == 0) {
            if (pathCount.get() == 0 || path.size() < pathCount.get()) {
                pathCount.set(path.size());
                pathCache.clear();
                pathCache.add(path);
            } else if (path.size() == pathCount.get() && !pathCache.contains(path)) {
                pathCache.add(path);
            }

            return;
        }

        if (qtyLeft < 0) {
            return;
        }

        if (containers.size() == 0) {
            return;
        }

        if (pathCount.get() != 0 && path.size() >= pathCount.get()) {
            return;
        }

        for (int i = 0; i < containers.size(); i++) {
            Container container = containers.get(i);
            int remainder = qtyLeft - container.qty;
            List<Container> newContainers = new ArrayList<>(containers);
            newContainers.remove(container);
            Set<Container> newPath = new LinkedHashSet<>(path);
            newPath.add(container);
            getPossibleOutcomes(remainder, newContainers, pathCache, pathCount, newPath);
        }
    }
}
