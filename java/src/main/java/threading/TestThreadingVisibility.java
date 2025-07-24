package threading;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class TestThreadingVisibility {
    private static int testValue = 0;
    private static volatile int actualSyncedValue = 0;

    private static volatile boolean stopAlteringValues = false;

    private static volatile boolean stopProgram = false;

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            int value = 0;
            int numberOfFullIntPassthroughs = 1;
            while (numberOfFullIntPassthroughs-- > 0) {
                while (value < Integer.MAX_VALUE) {
                    if (!stopAlteringValues) {
                        value++;
                        actualSyncedValue = value;
                        testValue = value;
                    }
                }
                value = 0;
            }
            stopProgram = true;
        }, "thread1");
        thread1.start();

        int numberOfDetectedProblems = 0;
        record Tuple(Integer val1, Integer val2) {}

        Map<Integer, List<Tuple>> diffItems = new HashMap<>();
        while (!stopProgram) {
            stopAlteringValues = true;
            int readUnsyncedValueFirst = testValue;
            int diff = actualSyncedValue - readUnsyncedValueFirst;
            if (diff > 1) {
                numberOfDetectedProblems++;

                List<Tuple> diffTuples = diffItems.get(diff);
                if (diffTuples == null) {
                    diffTuples = new LinkedList<>();
                    diffItems.put(diff, diffTuples);
                }
                diffTuples.add(new Tuple(actualSyncedValue, readUnsyncedValueFirst));
            }
            stopAlteringValues = false;
        }
        for (Integer diff : diffItems.keySet().stream().sorted(Comparator.naturalOrder()).toList()) {
            System.out.println(diff + " : " + diffItems.get(diff).size() + " items");
        }
        System.out.println("Number of detected problems: " + numberOfDetectedProblems);
    }
}

