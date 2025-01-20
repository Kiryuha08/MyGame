import java.util.ArrayList;

public class Room {
    public ArrayList<Walls> walls;

    public ArrayList<Enemy> Enemies;
    //public ArrayList<Door> Doors;
    public int[][] BanPoints;

    public Room () {
        this.walls = new ArrayList<Walls>();
        this.BanPoints = new int[800][600]; // Создаём матрицу из всех точек комнаты
    }

    void AddWalls(int x1, int y1, int x2, int y2){
        // добавление стены
        this.walls.add(new Walls(x1,y1,x2,y2));
        this.UpdateBanPoints();
    }

    void UpdateBanPoints(){
        // TODO: расчет недоступных точек (забаненые = 1, доступные = 0)
        for (Walls wall : this.walls){
            for (int i = wall.x1; i < wall.x2 + 1; i++) {
                for (int j = wall.y1; j < wall.y2 + 1; j++){
                        this.BanPoints[i][j] = 1;
                }
            }
        }
        for (int i = 0; i < 800; i++) {
            for (int j = 0; j < 5; j++) {
                this.BanPoints[i][j] = 1;
                this.BanPoints[i][599-j] = 1;
            }
        }
        for (int j = 0; j < 600; j++) {
            for (int i = 0; i < 5; i++) {
                this.BanPoints[i][j] = 1;
                this.BanPoints[799-i][j] = 1;
            }
        }
        // todo: "вырезать" двери
    }

    int pointCheck(int x, int y){
        return (this.BanPoints[x][y] == 1 ? 0 : 1);
    }
}

