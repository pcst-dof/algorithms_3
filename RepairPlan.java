import java.util.*;

public class RepairPlan {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();  // количество работ (вершин)
        int m = sc.nextInt();  // количество зависимостей (рёбер)

        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        // Массив входящих степеней
        int[] inDegree = new int[n];

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();  // работа, которая должна быть сделана первой
            int v = sc.nextInt();  // работа, которая зависит от u
            graph[u].add(v);       // добавляем ребро u
            inDegree[v]++;         // увеличиваем входящую степень v
        }

        // Все работы с нулевой входящей степенью
        Queue<Integer> queue = new LinkedList<>();
        int initialCount = 0;  // счётчик работ, доступных в начале

        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
                initialCount++;
            }
        }

        // Алгоритм Кана: топологическая сортировка
        List<Integer> order = new ArrayList<>();

        while (!queue.isEmpty()) {
            int u = queue.poll();  // берём работу из очереди
            order.add(u);          // добавляем в результат

            for (int v : graph[u]) {
                inDegree[v]--;     // уменьшаем входящую степень v
                if (inDegree[v] == 0) {
                    queue.add(v);  // если все зависимости выполнены, добавляем в очередь
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("order=");
        for (int i = 0; i < order.size(); i++) {
            if (i > 0) sb.append(" ");
            sb.append(order.get(i));
        }
        sb.append(" initial=").append(initialCount);
        System.out.println(sb.toString());
    }
}