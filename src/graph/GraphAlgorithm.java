package graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import org.omg.CORBA.PUBLIC_MEMBER;

public class GraphAlgorithm {
	/**
	 * @param args
	 */
	public static int[][] graphEdge;
	public static Edge[] primEdges;
	public static Edge[] kruskalEdges;
	
	public static void main(String[] args) {
		initNoDirectionGraph();
		primEdges = new Edge[graphEdge.length-1];
		kruskalEdges = new Edge[graphEdge.length-1];
		PrimTree(graphEdge);
		KruskalTree(graphEdge);
		printArray(graphEdge);
		printPrimEdge(primEdges);
		System.out.println("----------------------");
		printPrimEdge(kruskalEdges);
		//int[][] testCycle = {{0,1,1},{1,0,0},{1,0,0},};
		//int[][] testCycle = {{0,1,1},{1,0,1},{1,1,0},};
		
		
	}
//图初始化
	public static void initNoDirectionGraph() {
		File file = new File(
				"/home/xiao/workspace2/Algorithm/src/graph/algorithm");
		try {
			RandomAccessFile accessFile = new RandomAccessFile(file, "r");
			String vertex = accessFile.readLine();
			int count = Integer.parseInt(vertex);
			graphEdge = new int[count][count];		
			String tmpString;
			while ((tmpString = accessFile.readLine() )!= null ){
			int start = tmpString.charAt(0)-'0';
			int end = tmpString.charAt(2)-'0';
			int length = tmpString.charAt(4)-'0';
			graphEdge[start][end] = length;
			graphEdge[end][start] = length;
			}
			accessFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
   //Prim算法
	public static void PrimTree(int[][] edge){
		boolean[] flag = new boolean[edge.length];
		flag[0] = true;
		int addittion = 1;
		int[] addedVertex = new int[flag.length];
		addedVertex[0] = 0;
		for (int i = 0; i < flag.length-1; i++) {
			Edge addEdge = new Edge();
			int addedVertextmp = -1;
			int shortEdge = 10000000;
			for (int j = 0; j <= i; j++) {
				for (int k = 0; k < addedVertex.length; k++) {
					if(edge[addedVertex[j]][k]!=0 && edge[addedVertex[j]][k]< shortEdge && flag[k]!=true)
						{
						addedVertextmp = k;
						addEdge.end = k;
						addEdge.start = addedVertex[j];
						addEdge.length = edge[addedVertex[j]][k];
						shortEdge = edge[addedVertex[j]][k];
						}
				}
			}
			addedVertex[i+1] = addedVertextmp;
			flag[addedVertextmp] = true;
			primEdges[i] = addEdge;
		}
	}
	
	//Kruskal最小生成树
	public static void KruskalTree(int[][] edge){
		int[][] added = new int[edge.length][edge.length];
		boolean[] addedflag = new boolean[edge.length];
		int[][] edgeCopy = new int[edge.length][edge.length];
		for (int i = 0; i < edgeCopy.length; i++) {
			for (int j = 0; j < edgeCopy.length; j++) {
				edgeCopy[i][j] = edge[i][j];
			}
		}
		for (int i = 0; i < added.length-1; i++) {//总共只要加入顶点数-1条边
			int start=0;
			int end=0;
			int minum = 1000000;
			Edge edgeKruskal = new Edge();
			for (int j = 0; j < edge.length; j++) {
				for (int j2 = 0; j2 < edge.length; j2++) {
					if(edgeCopy[j][j2]>0 && edgeCopy[j][j2] < minum && edgeCopy[j][j2]!=88888){minum =edgeCopy[j][j2] ;start = j;end = j2;}
				}
			}
			added[start][end] = edgeCopy[start][end];
			added[end][start] = edgeCopy[end][start];
			if(isCycle(added)){//存在环路
				i--;
				added[start][end] = 0;
				added[end][start] = 0;
				edgeCopy[start][end] = 1000000;
				edgeCopy[end][start] = 1000000;
			}
			else{
				edgeCopy[start][end] = 99999;
				edgeCopy[end][start] = 99999;
				addedflag[start] = true;
				addedflag[end] = true;
				added[start][end] = edge[start][end];
				added[end][start] = edge[end][start];
			    edgeKruskal.start = start;
			    edgeKruskal.end = end;
			    edgeKruskal.length = edge[start][end];
			    kruskalEdges[i] = edgeKruskal;
			   				
			}	
		}
		
		
	}
	//判断是否存在环
	public static boolean isCycle(int[][] edge2){
		int[][] edge = new int[edge2.length][edge2.length];
		for (int i = 0; i < edge.length; i++) {
			for (int j = 0; j < edge.length; j++) {
				edge[i][j] = edge2[i][j];
			}
		}
		boolean flag = false;
		boolean[] delete = new boolean[edge.length];
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < edge.length; j++) {
				int count = 0;
				int row = 0;
				for (int k = 0; k < edge[j].length; k++) {
					if(edge[j][k]>0) {count++; row = k;}
				}
				if(count == 0){delete[j] = true;}
				if(count == 1){delete[j] = true;edge[j][row] = 0;edge[row][j] = 0;}
			}
		}
		
		
		for (int i = 0; i < delete.length; i++) {
			if(delete[i] == false) flag = true;
		}
		
		return flag;//返回true代表存在环
	}
	
	
	public static void printArray(int[][] array){
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				System.out.print(array[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	
	public static void printPrimEdge(Edge[] edges){
		for (int i = 0; i < edges.length; i++) {
			System.out.println("边"+"V"+edges[i].start+"---"+"边"+"V"+edges[i].end+"  "+"长度为"+edges[i].length);
		}
	}
	
	public static class Edge{
		int start;
		int end;
		int length;
	}

}
