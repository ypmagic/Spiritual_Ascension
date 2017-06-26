import java.util.Arrays;

/**
 * Created by young on 6/25/17.
 */

public class SpiritualAscension {

    public enum Direction {
        RIGHT, LEFT, UP, DOWN
    }

    int n;
    int tracker;
    int i;
    int j;
    int[][] spiritual;
    Direction direction;

    public SpiritualAscension(int n) {
        this.spiritual = new int[n][n];
        for (int i = 0; i < this.n; i++) {
            Arrays.fill(this.spiritual[i], 0);
        }
        this.n = n;
        this.tracker = 1;
        this.i = 0;
        this.j = 0;
        this.direction = Direction.RIGHT;
        fillArray();
    }

    private void checkWall() {
        boolean right = i == 0 && j == this.n-1;
        boolean down = i == this.n-1 && j == this.n-1;
        boolean left = i == this.n-1 && j == 0;
        boolean up = i == 0 && j == 0;
        if (this.direction == Direction.RIGHT && right) {
            this.direction = Direction.DOWN;
        } else if (this.direction == Direction.DOWN && down) {
            this.direction = Direction.LEFT;
        } else if (this.direction == Direction.LEFT && left) {
            this.direction = Direction.UP;
        } else if (this.direction == Direction.UP && up) {
            this.direction = Direction.RIGHT;
        }
    }

    private void checkFilled() {
        if (this.direction == Direction.RIGHT && j < this.n-1) {
            if (this.spiritual[i][j+1] != 0) {
                this.direction = Direction.DOWN;
            }
        } else if (this.direction == Direction.DOWN && i < this.n-1) {
            if (this.spiritual[i+1][j] != 0) {
                this.direction = Direction.LEFT;
            }
        } else if (this.direction == Direction.LEFT && j > 0) {
            if (this.spiritual[i][j-1] != 0) {
                this.direction = Direction.UP;
            }
        } else if (this.direction == Direction.UP && i > 0) {
            if (this.spiritual[i-1][j] != 0) {
                this.direction = Direction.RIGHT;
            }
        }
    }

    private void determineAdvance() {
        checkWall();
        checkFilled();
        if (this.direction == Direction.RIGHT) {
            this.j++;
        } else if (this.direction == Direction.DOWN) {
            this.i++;
        } else if (this.direction == Direction.LEFT) {
            this.j--;
        } else if (this.direction == Direction.UP) {
            this.i--;
        }
    }

    private void fillArray() {
        while (this.tracker != this.n*this.n+1) {
            this.spiritual[this.i][this.j] = this.tracker;
            tracker++;
            determineAdvance();
        }
    }

    private int[][] getSpiritual() {
        return this.spiritual;
    }

    public static void main(String[] args) {
        SpiritualAscension a = new SpiritualAscension(5);
        int[][] g = a.getSpiritual();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.printf("%4d", g[i][j]);
            }
            System.out.println();
        }
    }
}
