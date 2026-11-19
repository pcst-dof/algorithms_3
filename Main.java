import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();  // количество городов (вершин)
        int m = sc.nextInt();  // количество дорог (ребер)
        int B = sc.nextInt();  // бюджет

        // Каждая дорога - массив [u, v, вес]
        int[][] edges = new int[m][3];
        for (int i = 0; i < m; i++) {
            edges[i][0] = sc.nextInt(); // u
            edges[i][1] = sc.nextInt(); // v
            edges[i][2] = sc.nextInt(); // вес
        }

        // Сортировка дорог по цене
        Arrays.sort(edges, (a, b) -> a[2] - b[2]);

        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        int cost = 0;   // общая стоимость
        int used = 0;   // сколько дорог мы использовали

        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];
            int w = e[2];

            int pu = find(parent, u);
            int pv = find(parent, v);

            if (pu != pv) {
                parent[pu] = pv;   // объединяем две группы
                cost += w;         // добавляем цену
                used++;            // считаем дорогу

                if (used == n - 1) break;
            }
        }

        // Сравниваем с бюджетом
        if (cost <= B) {
            System.out.println("YES " + cost);
        } else {
            System.out.println("NO " + cost);
        }
    }

    static int find(int[] parent, int x) {
        while (parent[x] != x) {
            x = parent[x];
        }
        return x;
    }
}