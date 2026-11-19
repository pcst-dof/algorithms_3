import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class RobotsOnField {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Размер поля
        int h = sc.nextInt();  
        int w = sc.nextInt();  
        sc.nextLine();  

        // 2. Читаем само поле
        char[][] grid = new char[h][w];
        int startRow = -1; 
        int startCol = -1;

        for (int i = 0; i < h; i++) {
            String line = sc.nextLine();
            for (int j = 0; j < w; j++) {
                grid[i][j] = line.charAt(j);
                if (grid[i][j] == 'S') {  
                    startRow = i;
                    startCol = j;
                }
            }
        }

        // Массив расстояний. (-1 - не были)
        int[][] dist = new int[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                dist[i][j] = -1;
            }
        }

        // Очередь для BFS
        Queue<Integer> queueRow = new LinkedList<>();
        Queue<Integer> queueCol = new LinkedList<>();


        queueRow.add(startRow);
        queueCol.add(startCol);
        dist[startRow][startCol] = 0;   // расстояние от старта до старта = 0


        int reachable = 0;      // сколько клеток достижимо
        int maxDistance = 0;    // максимальное расстояние

        // Направления
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        // Главный цикл BFS
        while (!queueRow.isEmpty()) {
            int r = queueRow.poll();
            int c = queueCol.poll();

            reachable++;
            if (dist[r][c] > maxDistance) {
                maxDistance = dist[r][c];
            }

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d]; 
                int nc = c + dc[d]; 

                // Провека 3-х условий:
                // 1) не вышли за границы
                // 2) клетка не заблокирована
                // 3) ещё не посетили эту клетку
                if (nr >= 0 && nr < h && nc >= 0 && nc < w
                        && grid[nr][nc] != '#'
                        && dist[nr][nc] == -1) {

                    dist[nr][nc] = dist[r][c] + 1;  
                    queueRow.add(nr);
                    queueCol.add(nc);
                }
            }
        }

        System.out.println("reachable=" + reachable + " maxDistance=" + maxDistance);
    }
}



// Для теста на месте
//5 6
//S...#.
//##..#.
//...##.
//.#....
//......
