
import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

public class ToyQueue {
    private int[] toyWeights;

    private final Queue<String> toyQueue = new PriorityQueue<>(10, (s1, s2) -> {
        int weight1 = toyWeights[Integer.parseInt(s1) - 1];
        int weight2 = toyWeights[Integer.parseInt(s2) - 1];
        return Integer.compare(weight2, weight1);
    });

    public ToyQueue(String toyId, String toyName, String toyWeight) {
        toyId.split(" ");
        toyName.split(" ");
        String[] weightArr = toyWeight.split(" ");
        toyWeights = new int[weightArr.length];
        for (int i = 0; i < weightArr.length; i++) {
            toyWeights[i] = Integer.parseInt(weightArr[i]);
        }
    }

    public void put(String toy) {
        toyQueue.offer(toy);
    }

    public String get() {
        Random random = new Random();
        int num = random.nextInt(10) + 1;
        if (num <= 2) {
            return toyQueue.poll();
        } else if (num <= 4) {
            return "2";
        } else {
            return "3";
        }
    }

    public void writeToFile(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            for (int i = 0; i < 10; i++) {
                writer.write(get() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ToyQueue toyQueue = new ToyQueue("1 2 3", "конструктор робот кукла", "2 2 6");
        toyQueue.put("1");
        toyQueue.put("2");
        toyQueue.put("3");
        toyQueue.writeToFile("output.txt");
    }
}

