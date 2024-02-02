// Temperatures1.java

// proccessing measurement data


/**********************************************************************

A problem: processing measurement data

Temperature measurements are taken in one and the same place for a couple
of weeks. The measurements are taken regularly � the same number of
measurements each week. At the end of the measurement period the collected
data is to be processed: for each week the least, the greatest and the
average temperature is to be determined. The least, greatest and
average temperature for the whole period is also to be computed.

A solution to the problem

This program reads the temperatures and displays them. Then the least,
greatest and average temperature for each week is computed and stored.
These results are printed on the standard output device. Finally, the
least, greatest and average temperature over the whole measurement
period is decided. These results are also printed on the standard
output device.

Author: Fadil Galjic

**********************************************************************/


import java.util.*; // Scanner, Locale
import static java.lang.System.out;

class Temperatures1
{
	public static void main (String[] args)
	{
 		out.println("TEMPERATURES\n");

		// input tool
		Scanner in = new Scanner(System.in); //Scanner start
		in.useLocale(Locale.US);

        // enter the number of weeks and measurements
		out.print("number of weeks: ");
		int nofWeeks = in.nextInt();
		out.print("number of measurements per week: ");
		int nofMeasurementsPerWeek = in.nextInt();

        // storage space for temperature data, 2d array
        double[][] t = new double[nofWeeks + 1] //Array for amount of weeks with index starting at 1
                                 [nofMeasurementsPerWeek + 1]; //Array for amount of measurements per week

        // read the temperatures
		for (int week = 1; week <= nofWeeks; week++)
		{
			out.println("temperatures - week " + week + ":");
			for (int measurement = 1;
				measurement <= nofMeasurementsPerWeek; measurement++) //Iterating the reading x amount of times
				t[week][measurement] = in.nextDouble(); //Read each of the measurements and assign it to an index in array
		}
		out.println("");

		// show the temperatures
		out.println("the temperatures");
        for (int week = 1; week <= nofWeeks; week++)
        {
			for (int measurement = 1;
			    measurement <= nofMeasurementsPerWeek; measurement++)
			    out.print(t[week][measurement] + " ");
		    out.println("");
		}
		out.println("");

		// the least, greatest and average temperatures - weekly
		double[] minT = new double[nofWeeks + 1]; //Array with min for each week
		double[] maxT = new double[nofWeeks + 1]; //Array with max for each week
		double[] sumT = new double[nofWeeks + 1]; //and so on
		double[] avgT = new double[nofWeeks + 1];
		// add code here
		in.close(); //closes the scanner
		for(int i = 1; i < nofWeeks + 1; i++){ //Iteration through weeks
			double minVal = t[i][1]; //Initiate first element so we have something to compare
			double maxVal = t[i][1]; //same here
			double sum = t[i][1]; //same here
			for(int j = 2; j <= nofMeasurementsPerWeek; j++){ //Iteration through measurements per week
				if(t[i][j] < minVal){ //Check if the value is less than the current min value
					minVal = t[i][j]; // If true then replace it
				}
				else if(t[i][j] > maxVal){ //same here but with max value
					maxVal = t[i][j];
				}
				sum += t[i][j]; //Each value gets added to the sum, not replaced
			}
			maxT[i] = maxVal; //Assign the max value for each iteration of week
			minT[i] = minVal; //Same but with min value
			avgT[i] = sum / nofMeasurementsPerWeek; 
			sumT[i] = sum; //Assign sum of each week to each week
		}

		// show the least, greatest and average temperatures - weekly
		out.println("the least, greatest and average temperatures"
		    + " - weekly");
		for (int week = 1; week <= nofWeeks; week++)
			out.print(minT[week] + " ");
		out.println("");
		for (int week = 1; week <= nofWeeks; week++)
			out.print(maxT[week] + " ");
		out.println("");
		for (int week = 1; week <= nofWeeks; week++)
			out.print(avgT[week] + " ");
		out.println("");
		out.println();
		double minTemp = minT[1];//initiate at first week
		double maxTemp = maxT[1];//initiate at first week
		double sumTemp = sumT[1]; //Sum is 0 at beginning since no temps have been added
	
		
		for (int i = 2; i <= nofWeeks; i++) {
			if (minT[i] < minTemp) {
				minTemp = minT[i];
			}
			if (maxT[i] > maxTemp) {
				maxTemp = maxT[i];
			}
			sumTemp += sumT[i];
		}
		
		double avgTemp = sumTemp / (nofMeasurementsPerWeek * nofWeeks);
		
		// show the least, greatest and average temperature for the whole period
		out.println("the least, greatest and average temperature - whole period");
		out.println(minTemp + "\n" + maxTemp + "\n" + avgTemp);
    }
}
