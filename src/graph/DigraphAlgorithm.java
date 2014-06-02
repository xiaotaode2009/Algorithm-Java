package graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

public class DigraphAlgorithm {

	/**
	 * @param args
	 */
	public static int[][] digraphEdge;

	public static void main(String[] args) {
		initDigrap();

	}

	public static void initDigrap() {
		File file = new File(
				"/home/xiao/workspace2/Algorithm/src/graph/Digraph");
		try {
			RandomAccessFile accessFile = new RandomAccessFile(file, "r");
			String str = accessFile.readLine();
			int vertexCount = Integer.parseInt(str);
			digraphEdge = new int[vertexCount][vertexCount];
			String tmp = null;
			while ((tmp = accessFile.readLine()) != null) {
				String[] graph = tmp.split(" ");
				int row = Integer.parseInt(graph[0]);
				int line = Integer.parseInt(graph[1]);
				int length = Integer.parseInt(graph[2]);
				digraphEdge[row][line] = length;
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
		public static void dijkstraPath(int[][] edges){
			
			
			
		}

}
