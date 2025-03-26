import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Room {
    public ArrayList<Walls> walls;

    public ArrayList<Enemy> Enemies;

    //public ArrayList<Door> Doors;
    public int[][] RoomPoints;

    public Room () {
        this.walls = new ArrayList<Walls>();
        this.Enemies = new ArrayList<Enemy>();
        this.RoomPoints = new int[800][600]; // Создаём матрицу из всех точек комнаты

        // walls - 1
        // hero - 2
        // vizor - 3
        // enemy - 4
    }

    void AddWalls(int x1, int y1, int x2, int y2){
        // добавление стены
        this.walls.add(new Walls(x1,y1,x2,y2));
        this.UpdateBanPoints();
    }

    void AddEnemy(int x, int y) throws IOException {
        this.Enemies.add(new Enemy(x,y, this));
    }

    void RefreshPoints(){
        for (int[] row: this.RoomPoints)
            Arrays.fill(row, 0);
    }
    void UpdateBanPoints(){
        // расчет недоступных точек (забаненые = 1, доступные = 0)
        for (Walls wall : this.walls){
            for (int i = wall.x1; i < wall.x2 + 1; i++) {
                for (int j = wall.y1; j < wall.y2 + 1; j++){
                        this.RoomPoints[i][j] = 1;
                }
            }
        }
        for (int i = 0; i < 800; i++) {
            for (int j = 0; j < 5; j++) {
                this.RoomPoints[i][j] = 1;
                this.RoomPoints[i][599-j] = 1;
            }
        }
        for (int j = 0; j < 600; j++) {
            for (int i = 0; i < 5; i++) {
                this.RoomPoints[i][j] = 1;
                this.RoomPoints[799-i][j] = 1;
            }
        }
        // todo: "вырезать" двери
    }

    int pointCheck(int x, int y){
        if (x >= 0 && x < 800 && y >=0 && y < 600)
            return (this.RoomPoints[x][y] == 1 ? 0 : 1);
        else
            return 1;
    }
}

