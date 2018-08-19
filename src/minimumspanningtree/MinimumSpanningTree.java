package minimumspanningtree;

/**
 *
 * @author Ahmed El Torky
 */
public class MinimumSpanningTree {

    private int dim;
    private int inf;
    private int[][] weight;

    public MinimumSpanningTree(int dimension, int infinityValue, int[][] weight) {
        this.dim = dimension;
        this.inf = infinityValue;
        this.weight = weight;
    }

    public void printArray(int arr[][]) {
        for (int r = 0; r < dim; r++) {
            for (int c = 0; c < dim; c++) {
                System.out.print(arr[r][c] + "\t");
            }
            System.out.println();
        }
    }

    public void minimumSpanningTree() {
        int totalWeight = 0, group = 0;
        int groupArr[] = new int[dim];
        int minSpan[][] = new int[dim][dim];
        int rmin = -1, cmin = -1, min;
        for (int i = 0; i < dim; i++) {
            groupArr[i] = -1;
        }
        for (int r = 0; r < dim; r++) {
            for (int c = 0; c < dim; c++) {
                if (r == c) {
                    minSpan[r][c] = 0;
                } else {
                    minSpan[r][c] = inf;
                }
            }
        }

        while (true) {
            min = inf;
            for (int r = 0; r < dim; r++) {
                for (int c = 0; c < r; c++) {
                    if (min > weight[r][c]) {
                        min = weight[r][c];
                        rmin = r;
                        cmin = c;
                    }
                }
            }
            if (min == inf) {
                break;
            }
            if (groupArr[rmin] != groupArr[cmin] || groupArr[rmin] == -1 || groupArr[cmin] == -1) {
                minSpan[rmin][cmin] = minSpan[cmin][rmin] = min; //min or weight[rmin][cmin]
                totalWeight += min;
                if (groupArr[rmin] == -1 && groupArr[cmin] != -1) {
                    groupArr[rmin] = groupArr[cmin];
                } else if (groupArr[rmin] != -1 && groupArr[cmin] == -1) {
                    groupArr[cmin] = groupArr[rmin];
                } else if (groupArr[rmin] == -1 && groupArr[cmin] == -1) {
                    group++;
                    groupArr[rmin] = groupArr[cmin] = group;
                } else {
                    int temp = groupArr[rmin];
                    for (int i = 0; i < dim; i++) {
                        if (groupArr[i] == temp) {
                            groupArr[i] = groupArr[cmin];
                        }
                    }
                }
            }
            weight[rmin][cmin] = inf;
        }
    }
}
