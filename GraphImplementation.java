import java.util.List;
import java.util.ArrayList;

public class GraphImplementation implements Graph {
    private int [][] matrix;
    private int vertices;

    public GraphImplementation(int vertices) {
        matrix = new int[vertices][vertices];
        this.vertices = vertices;
    }

    public void addEdge(int src, int tar) throws Exception{
        if (src >= 0 && src < vertices || tar >= 0 && tar <  vertices){
            matrix[src][tar] = 1;
            //matrix[tar][src] = 1;
        }
    }

    public List<Integer> neighbors(int vertex) throws Exception {
        List<Integer> neighbors = new ArrayList<Integer>();

        for (int i = 0; i <  vertices; i++) {
            if (matrix[vertex][i] != 0) {
                neighbors.add(i);
            }
        }
        return neighbors;
    }

    public List<Integer> topologicalSort(){
        List<Integer> list = new ArrayList<Integer>();
        int [] sum = new int[vertices];

        for (int i = 0; i < vertices; i++){
            for (int j = 0; j <  vertices; j++){
                sum[i] += matrix[j][i];
            }
        }

        for (int i = 0; i <  vertices; i++){
            int next = findZero(sum);

            list.add(next);
            sum[next] = -1;

            for (int j = 0; j <  vertices; j++) {
                sum[j] -= matrix[next][j];
            }
        }
        return list;
    }

    //boolean or return index
    int findZero(int [] sum) {
        for (int i = 0; i <  sum.length; i++) {
            if (sum[i] == 0)
                return i;
        }
        return -1;
    }
}
