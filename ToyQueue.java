import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Random;

class Toy {
    private int id;
    private String name;
    private int weight;

    public Toy(int id, String name, int weight) {
        this.id = id;
        this.name = name;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }
}

public class ToyQueue {
    public static void main(String[] args) {
        Toy toy1 = new Toy(1, "конструктор", 2);
        Toy toy2 = new Toy(2, "робот", 2);
        Toy toy3 = new Toy(3, "кукла", 6);

        PriorityQueue<Toy> queue = new PriorityQueue<>((t1, t2) -> t2.getWeight() - t1.getWeight());
        
        queue.offer(toy1);
        queue.offer(toy2);
        queue.offer(toy3);
       
        

        Random random = new Random();
        try {
            FileWriter writer = new FileWriter("output.txt");
            for (int i = 0; i < 10; i++) {
                int randomNumber = random.nextInt(100);
                Toy toy = get(queue, randomNumber);
                writer.write(toy.getId() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    

    public static Toy get(PriorityQueue<Toy> queue, int randomNumber) {
        int totalWeight = queue.stream().mapToInt(Toy::getWeight).sum();
        int cumulativeWeight = 0;
        for (Toy toy : queue) {
            cumulativeWeight += toy.getWeight();
            if (randomNumber <= (cumulativeWeight * 100 / totalWeight)) {
                return toy;
            }
        }
        return null;
    }
}

