package test;

import java.util.Arrays;

import javax.naming.InitialContext;

public class TestStaticClass {

	/**
	 * @param args
	 */
	public static Test[] array;
	public static void main(String[] args) {
		array = new Test[3];
		init();
		int[] one={1,5,11};
		int[] onecopy = new int[one.length];
		for (int i = 0; i < onecopy.length; i++) {
			onecopy[i] = one[i];
		}
		System.out.println("one"+one+"   "+"onecopy"+onecopy);
		
		
	int[]	onecopySecond = one;
	System.out.println("one"+one+"   "+"onecopySecond"+onecopySecond);
	
	int[] onecopyThird = new int[3];
	onecopyThird = Arrays.copyOf(one, one.length);
	System.out.println("one"+one+"   "+"onecopyThird"+onecopyThird);
	
	
	int[] onecopyForth = new int[3];
	System.arraycopy(one, 0, onecopyForth, 0, one.length);
	System.out.println("one"+one+"   "+"onecopyForth"+onecopyForth);
	
		for (int i = 0; i < array.length; i++) {
			System.out.println("length"+array[i].length + "width" + array[i].width);
		}
		
		int[][] a ={{1,2,3},{},{}};
		int[][] b = new int[3][3];
		System.arraycopy(a, 0, b, 0, 3);
	//	Arrays.copyOf(original, newLength)
	
		b[0][1] = 10;
		System.out.println("a[0][1]="+a[0][1]);
		System.out.println(a[0]);
		System.out.println(b[0]);

	}
	public static void init(){
		for (int i = 0; i < array.length; i++) {
			Test tmpTest = new Test();
			tmpTest.length = i;
			tmpTest.width = i;
			array[i] = tmpTest;
		}
	}
	
	public static class Test{
		int length;
		int width;
		
	}

}
