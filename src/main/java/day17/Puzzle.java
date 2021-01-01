package day17;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Puzzle {
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

        AtomicInteger count = new AtomicInteger();
        Set<Set<Container>> cacheMap = new LinkedHashSet<>();
        getPossibleOutcomes(totalQty, containers, count, cacheMap, new LinkedHashSet<>());
        System.out.println(count.get());

        Map<Integer, Integer> pathCount = cacheMap.stream().collect(Collectors.toMap(
                data -> data.size(), data -> 0));
        for (Set<Container> container : cacheMap) {
            pathCount.compute(container.size(), (k, v) -> v == null ? 1 : v + 1);
        }

        System.out.println(pathCount);
    }

    static void getPossibleOutcomes(int qtyLeft, List<Container> containers, AtomicInteger count, Set<Set<Container>> cache, Set<Container> path) {
        if (qtyLeft == 0) {
            if (!cache.contains(path)) {
//                System.out.println(path);
                cache.add(path);
                count.incrementAndGet();
            }

            return;
        }

        if (qtyLeft < 0) {
            return;
        }

        if (containers.size() == 0) {
            return;
        }

        for (int i = 0; i < containers.size(); i++) {
            Container container = containers.get(i);
            int remainder = qtyLeft - container.qty;
            List<Container> newContainers = new ArrayList<>(containers);
            newContainers.remove(container);
            Set<Container> newPath = new LinkedHashSet<>(path);
            newPath.add(container);
            getPossibleOutcomes(remainder, newContainers, count, cache, newPath);
        }
    }
}
