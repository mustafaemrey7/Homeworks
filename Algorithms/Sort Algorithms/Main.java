import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.IntStream;

import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;

class Main {
	
    public static void main(String args[]) throws IOException {
    	
    	 // X axis data
        int[] inputAxis = {512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 251282};

        // Create sample data for linear runtime
        double[][] yAxis = new double[3][10]; 
    	int[] dataArray=readData(args[0],251282);
    	
    	
    	
    	
    
    	System.out.println("-----Random Selection Sort-----");
     	test(inputAxis, dataArray, 0);
     	System.out.println("-----Random Quick Sort-----");
     	test(inputAxis, dataArray, 1);
     	System.out.println("-----Random Bucket Sort-----");
     	test(inputAxis, dataArray, 2);
    	Alg2.quickSort(dataArray, 0, dataArray.length-1);//sorting for sort sorted data
    	System.out.println("-----Sorted Selection Sort-----");
     	test(inputAxis, dataArray, 0);
     	System.out.println("-----Sorted Quick Sort-----");
     	test(inputAxis, dataArray, 1);
     	System.out.println("-----Sorted Bucket Sort-----");
     	test(inputAxis, dataArray, 2);
     	int[] reverseArr = IntStream.rangeClosed(1, dataArray.length).map(i -> dataArray[dataArray.length-i]).toArray(); //reverse for reversed sorted data
     	System.out.println("-----Reversed Sorted Selection Sort-----");
     	test(inputAxis, reverseArr, 0);
     	System.out.println("-----Reversed Sorted Quick Sort-----");
     	test(inputAxis, reverseArr, 1);
     	System.out.println("-----Rversed Sorted Bucket Sort-----");
     	test(inputAxis, reverseArr, 2);
     	
     	
     /*	for (int j = 0; j < inputAxis.length; j++) {
    		int[] arr = Arrays.copyOf(reverseArr, inputAxis[j]);
        	long start = System.currentTimeMillis();
    		Alg2.quickSort(arr,0,inputAxis[j]-1);
    		long finish = System.currentTimeMillis();
    		long timeElapsed = finish - start;
    		yAxis[1][j]=timeElapsed;
    			
    		
		}
    	for (int j = 0; j < inputAxis.length; j++) {
    		int[] arr = Arrays.copyOf(reverseArr, inputAxis[j]);
        	long start = System.currentTimeMillis();
    		Alg3.bucketSort(arr,inputAxis[j]);
    		long finish = System.currentTimeMillis();
    		long timeElapsed = finish - start;
    		yAxis[2][j]=timeElapsed;
    			
    		
		}
    	for (int j = 0; j < inputAxis.length; j++) {
    		int[] arr = Arrays.copyOf(reverseArr, inputAxis[j]);
        	long start = System.currentTimeMillis();
    		Alg1.sort(arr,inputAxis[j]);
    		long finish = System.currentTimeMillis();
    		long timeElapsed = finish - start;
    		yAxis[0][j]=timeElapsed;
    			
    		
		}

    	
    	for (int i = 0; i < inputAxis.length; i++) {
    		int[] arr = Arrays.copyOf(dataArray, inputAxis[i]);
    		int randomNumber=(int)(Math.random()*(10000));
			long total=0;
			for (int j = 0; j < 1000; j++) {
				long start = System.nanoTime();
				linearSearch(arr, randomNumber);
				long finish = System.nanoTime();
				long timeElapsed = finish - start;
				total=total+timeElapsed;
			}
    		yAxis[0][i]=total/1000;
    		System.out.print("uzunluk "+inputAxis[i]+"   ");
    		System.out.println(total/1000);
		}
    	
    	
    	//Alg2.quickSort(dataArray, 0, dataArray.length-1); Sorting data for sorted searchs
    	for (int i = 0; i < inputAxis.length; i++) {
    		int[] arr = Arrays.copyOf(dataArray, inputAxis[i]);
    		int randomNumber=(int)(Math.random()*(10000));
			long total=0;
			for (int j = 0; j < 1000; j++) {
				long start = System.nanoTime();
				linearSearch(arr, randomNumber);
				long finish = System.nanoTime();
				long timeElapsed = finish - start;
				total=total+timeElapsed;
			}
    		yAxis[1][i]=total/1000;
    		System.out.print("uzunluk "+inputAxis[i]+"   ");
    		System.out.println(total/1000);
		}
    	for (int i = 0; i < inputAxis.length; i++) {
    		int[] arr = Arrays.copyOf(dataArray, inputAxis[i]);
    		int randomNumber=(int)(Math.random()*10000);
			long total=0;
			for (int j = 0; j < 1000; j++) {
				long start = System.nanoTime();
				binarySearch(arr, randomNumber);
				long finish = System.nanoTime();
				long timeElapsed = finish - start;
				total=total+timeElapsed;
			}
    		yAxis[2][i]=total/1000;
    		System.out.print("uzunluk "+inputAxis[i]+"   ");
    		System.out.println(total/1000);
		}
    	*/
        // Save the char as .png and show it
        //showAndSaveChart("reverse sorted", inputAxis, yAxis);

    
    
    }  
    public static int[] readData(String fileName,int size) {
    	int i = 0;
    	int[] arr=new int[size];
    	try {
            File file = new File("C:\\Users\\musta\\Downloads\\TrafficFlowDataset.csv");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            String[] tempArr;
            while((line = br.readLine()) != null&&size>i) {
            	if (i==0) {	
				}
               else {
               tempArr = line.split(",");            
               arr[i-1]=Integer.parseInt(tempArr[6]);
				}
            	i=i+1;
            }
            br.close();
            } catch(IOException ioe) {
               ioe.printStackTrace();
            }
    	return arr;
    }
   
    public static void showAndSaveChart(String title, int[] xAxis, double[][] yAxis) throws IOException {
        // Create Chart
        XYChart chart = new XYChartBuilder().width(800).height(600).title(title)
                .yAxisTitle("Time in Milliseconds").xAxisTitle("Input Size").build();

        // Convert x axis to double[]
        double[] doubleX = Arrays.stream(xAxis).asDoubleStream().toArray();

        // Customize Chart
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);

        // Add a plot for a sorting algorithm
        chart.addSeries("Lineer Search(Random Data)", doubleX, yAxis[0]);
        chart.addSeries("Lineer Search(Sorted Data)", doubleX, yAxis[1]);
        chart.addSeries("Binary Search(Sorted Data)", doubleX, yAxis[2]);
        // Save the chart as PNG
        BitmapEncoder.saveBitmap(chart, title + ".png", BitmapEncoder.BitmapFormat.PNG);
        // Show the chart
        new SwingWrapper(chart).displayChart();
    }  
    public static int linearSearch(int[] arr,int x) {
    	
    	for (int i = 0; i < arr.length-1; i++) {
			if (arr[i]==x) {
				return x;	
			}
		}
    	return -1;
    }
    public static int binarySearch(int[] arr,int x) {
    	int low=0;
    	int up=arr.length-1;
    	while((up-low)>1) {	
    		int mid=(up+low)/2;
    		if (arr[mid]<x) {
				low=mid+1;
			}
    		else {
				up=mid;	
			}
    	}
    	if(arr[low]==x) {
    		return low;   		
    	}
    	else if (arr[up]==x) {
    		return up;
		}
    	return -1;   	
    }
    public static void test(int[] inputAxis,int[] dataArray,int sortType) {
    	for (int i = 0; i < inputAxis.length; i++) {
			int len=inputAxis[i];
    		float total=0;
    	
			for (int j = 0; j < 10; j++) {
    		int[] arr = Arrays.copyOf(dataArray, len);
    		long startTime = System.nanoTime();
    		if(sortType==0) {
    			Alg1.sort(arr, len);
    		}
    		else if (sortType==1) {
				Alg2.quickSort(arr, 0, arr.length-1);
			}
    		else if(sortType==2) {
				Alg3.bucketSort(arr, len);
			}      
            long endTime = System.nanoTime();
            long duration = (endTime - startTime);
            total+=duration;
		}
    	long average = (long) (total/10);
    	
    	System.out.print("Data Size "+len+" ");
        System.out.printf("Average = %.2f",average/1000000.0);
        System.out.println();
        
		}
	}
}
