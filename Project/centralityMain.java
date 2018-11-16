import java.util.Arrays;
/**
 * @author Varun Jain 21963986
 * @author Joshua Goh 21978677
 */
public class centralityMain 
{
	public static void main(String[] args)
	{
		Centrality a = new Centrality(); 
		a.filename(System.getProperty("user.dir") + "/" + args[0]);
		
		long beginTime1 = System.currentTimeMillis();
		int[][] degreeStorage = a.degreecentrality();
		int[][] sortedDegreeStorage = a.degree_sort(degreeStorage);
		long endTime1 = System.currentTimeMillis();
		long time1 = endTime1 - beginTime1;
		
		System.out.println("Degree Centrality");
		for(int i = 0; i < 5; i++)
		{
			System.out.printf("	Number %d Node: %d  --> Degree Centrality: %d\n", i+1, (int) sortedDegreeStorage[i][1], (int) sortedDegreeStorage[i][0]);
		}
		System.out.println("run time: "+time1+" ms");
		
		System.out.printf("\n");
		long beginTime2 = System.currentTimeMillis();
		double[][] closenessStorage = a.closeness();
		double[][] sortedClosenessStorage = a.closeness_sort(closenessStorage);
		long endTime2 = System.currentTimeMillis();
		long time2 = endTime2 - beginTime2;
		
		System.out.println("Closeness Centrality");
		for(int i = 0; i < 5; i++)
		{
			System.out.printf("	Number %d Node: %d  --> Closeness Centrality: %.15f\n", i+1,  (int) sortedClosenessStorage[i][1],  sortedClosenessStorage[i][0]);
		}
		System.out.println("run time: "+time2+" ms");
		System.out.printf("\n");
		long beginTime3 = System.currentTimeMillis();
		float[][] betweenessStorage = a.Betweeness_Centrality();
		float[][] sortedBetweenessStorage = a.betweeness_sort(betweenessStorage);
		long endTime3 = System.currentTimeMillis();
		long time3 = endTime3 - beginTime3;
		
		System.out.println("Betweeness Centrality");
		for(int i = 0; i < 5; i++)
		{
			System.out.printf("	Number %d Node: %d --> Betweeness Centrality: %f\n", i+1,   (int) sortedBetweenessStorage[i][1],  sortedBetweenessStorage[i][0]);
		}
		System.out.println("run time: "+time3+" ms");
		System.out.printf("\n");
		long beginTime4 = System.currentTimeMillis();
		double[][] katzStorage = a.KatZ_Centrality();
		double[][] sortedkatzStorage = a.closeness_sort(katzStorage);
		long endTime4 = System.currentTimeMillis();
		long time4 = endTime4 - beginTime4;
		
		System.out.println("Katz Centrality");
		for(int i = 0; i < 5; i++)
		{
			System.out.printf("	Number %d Node: %d  --> Katz Centrality: %.01f\n", i+1,  (int) sortedkatzStorage[i][1],    sortedkatzStorage[i][0]);
		}
		System.out.println("run time: "+time4
				+" ms");
		
	}
	

}

