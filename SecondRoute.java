import java.util.*;

public class SecondRoute {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();  // вершины
        int m = sc.nextInt();  // дороги
        int s = sc.nextInt();  // старт
        int t = sc.nextInt();  // финиш

        // Граф: для каждой вершины список пар (куда, стоимость)
        List<int[]>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            graph[u].add(new int[]{v, w});
        }

        int INF = 1_000_000_000;
        int[] dist1 = new int[n]; // Лучший путь
        int[] dist2 = new int[n]; // Второй лучший путь
        
        Arrays.fill(dist1, INF);
        Arrays.fill(dist2, INF);

        // Очередь с приоритетом: сортируем по возрастанию
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        dist1[s] = 0;
        pq.add(new int[]{0, s});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int d = cur[0];   // текущее расстояние
            int v = cur[1];   // текущая вершина

            if (d > dist2[v]) {
                continue;
            }

            for (int[] edge : graph[v]) {
                int to = edge[0];
                int weight = edge[1];
                int newDist = d + weight;

                if (newDist < dist1[to]) {
                    dist2[to] = dist1[to];       
                    dist1[to] = newDist;       
                    pq.add(new int[]{newDist, to});
                    if (dist2[to] != INF) {      // добавляем второй, только если он достижим
                        pq.add(new int[]{dist2[to], to});
                    }
                } 
                // Путь хуже лучшего, но лучше второго
                else if (newDist > dist1[to] && newDist < dist2[to]) {
                    dist2[to] = newDist;
                    pq.add(new int[]{newDist, to});
                } 
                // Путь с такой же стоимостью
                else if (newDist == dist1[to]) {
                    dist2[to] = newDist;
                    pq.add(new int[]{newDist, to});
                }
            }
        }

        int ans1 = dist1[t];
        int ans2 = (dist2[t] == INF) ? -1 : dist2[t];
        
        System.out.println("first=" + ans1 + " second=" + ans2);
    }
}