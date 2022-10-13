import java.util.Scanner;

public class Dijkstra {
    static int V = 9; // 图中边的数量

    public static class MGraph {
        int[] vexs = new int[V]; // 存储图中顶点数据
        int[][] arcs = new int[V][V]; // 二维数组，记录顶点之间的关系
        int vexnum, arcnum; // 记录图的顶点数和弧（边）数
    }

    public static int LocateVex(MGraph G, int V) {
        int i = 0;
        // 遍历一维数组，找到变量v
        for (; i < G.vexnum; i++) {
            if (G.vexs[i] == V) {
                break;
            }
        }
        // 如果找不到，输出提示语句，返回-1
        if (i > G.vexnum) {
            System.out.println("顶点输入有误");
            return -1;
        }
        return i;
    }

    // 构造无向有权图
    public static void CreatDG(MGraph G) {
        Scanner scn = new Scanner(System.in);
        System.out.print("输入图的顶点数和边数：");
        G.vexnum = scn.nextInt();
        G.arcnum = scn.nextInt();
        System.out.print("输入各个顶点：");
        for (int i = 0; i < G.vexnum; i++) {
            G.vexs[i] = scn.nextInt();
        }
        for (int i = 0; i < G.vexnum; i++) {
            for (int j = 0; j < G.vexnum; j++) {
                G.arcs[i][j] = 65535;
            }
        }
        System.out.println("输入各个边的数据:");
        for (int i = 0; i < G.arcnum; i++) {
            int v1 = scn.nextInt();
            int v2 = scn.nextInt();
            int w = scn.nextInt();

            int n = LocateVex(G, v1);
            int m = LocateVex(G, v2);
            if (m == -1 || n == -1) {
                return;
            }
            G.arcs[n][m] = w;
            G.arcs[m][n] = w;
        }
    }

    // 迪杰斯特拉算法，v0表示有向网中起始点所在数组中的下标
    public static void Dijkstra_minTree(MGraph G, int v0, int[] p, int[] D) {
        int[] tab = new int[V]; // 为各个顶点配置一个标记值，用于确认该顶点是否已经找到最短路径
        // 对各数组进行初始化
        for (int v = 0; v < G.vexnum; v++) {
            tab[v] = 0;
            D[v] = G.arcs[v0][v];
            p[v] = 0;
        }
        // 由于以v0位下标的顶点为起始点，所以不用再判断
        D[v0] = 0;
        tab[v0] = 1;
        int k = 0;
        for (int i = 0; i < G.vexnum; i++) {
            int min = 65535;
            // 选择到各顶点权值最小的顶点，即为本次能确定最短路径的顶点
            for (int w = 0; w < G.vexnum; w++) {
                if (tab[w] != 1) {
                    if (D[w] < min) {
                        k = w;
                        min = D[w];
                    }
                }
            }
            // 设置该顶点的标志位为1，避免下次重复判断
            tab[k] = 1;
            // 对v0到各顶点的权值进行更新
            for (int w = 0; w < G.vexnum; w++) {
                if (tab[w] != 1 && (min + G.arcs[k][w] < D[w])) {
                    D[w] = min + G.arcs[k][w];
                    p[w] = k;// 记录各个最短路径上存在的顶点
                }
            }
        }
    }

    public static void main(String[] args) {
        MGraph G = new MGraph();
        CreatDG(G);
        int[] P = new int[V]; // 记录顶点 0 到各个顶点的最短的路径
        int[] D = new int[V]; // 记录顶点 0 到各个顶点的总权值
        Dijkstra_minTree(G, 0, P, D);
        System.out.println("最短路径为：");
        for (int i = 1; i < G.vexnum; i++) {
            System.out.print(i + " - " + 0 + " 的最短路径中的顶点有：");
            System.out.print(i + "-");
            int j = i;
            // 由于每一段最短路径上都记录着经过的顶点，所以采用嵌套的方式输出即可得到各个最短路径上的所有顶点
            while (P[j] != 0) {
                System.out.print(P[j] + "-");
                j = P[j];
            }
            System.out.println("0");
        }
        System.out.println("源点到各顶点的最短路径长度为:");
        for (int i = 1; i < G.vexnum; i++) {
            System.out.println(G.vexs[0] + " - " + G.vexs[i] + " : " + D[i]);
        }
    }
}