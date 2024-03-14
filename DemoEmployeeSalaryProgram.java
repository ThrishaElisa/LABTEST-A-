/**
 * Program Description: construct a Java Program based on the given class diagram related to the employee salary data from an input file
 * Programmer: Thrisha Yusri
 * Date: 14/03/2024
 */

import javax.swing.JOptionPane;
import java.text.DecimalFormat;
import java.io.*;
import java.util.StringTokenizer;

public class DemoEmployeeSalaryProgram{
    public static void main(String[]args) throws IOException,FileNotFoundException
    {
        DecimalFormat dF = new DecimalFormat("0.00");//decimal fromat for double data type
        try
        {
            //read data from input file
            BufferedReader inFile= new BufferedReader(new FileReader("employeeSalaries.txt"));
            // output file
            PrintWriter outFile = new PrintWriter(new FileWriter("employeeData.txt"));

            //Declare the variables
            String inputData = null;
            String empName = "";//employee name
            double empSalary = 0.00;//employee salary
            int empWorkYear = 0;//employee working year
            
            //declare variables for top performing employee
            String topEmpName = "";//top employee name
            double topEmpAnnSalary = 0.00;//top employee salary
            int topEmpWorkYear = 0;//top employee working year
            
            //declare variable for latest employee
            String latestEmpName = "";//latest employee name
            double latestEmpAnnSalary = 0.00;//latest employee salary
            int latestEmpWorkYear = 0;//latest employee working year
            
            //The title header
            outFile.println("====================================================================");
            outFile.println("|                          List of Employee                        |");
            outFile.println("====================================================================");
            
            //while line from the input file in the current line is not null, get the details, calculate the annual salary and store them in an output file
            while((inputData = inFile.readLine()) != null)
            {
                StringTokenizer tokenizer = new StringTokenizer(inputData,"|");
                
                empName = tokenizer.nextToken();
                empSalary = Double.parseDouble(tokenizer.nextToken());
                empWorkYear = Integer.parseInt(tokenizer.nextToken());
                
                //calculate the annual salary for each employee,yearly increment of 5% for each year of service.
                double annSalary = empSalary + (empSalary * 0.05);
                
                //to test for teh negative number
                if(empSalary < 0|| empWorkYear < 0 )
                    throw new IllegalArgumentException();
                
                //find top performing employee
                if(annSalary > topEmpAnnSalary){
                    topEmpName = empName;
                    topEmpAnnSalary = annSalary ;
                    topEmpWorkYear = empWorkYear;
                }
                
                //find the employee with the latest years of service
                if(latestEmpWorkYear == 0 || empWorkYear < latestEmpWorkYear){
                    latestEmpName = empName;
                    latestEmpAnnSalary = annSalary;
                    latestEmpWorkYear = empWorkYear;
                }
                
                //store list of employee
                String empData = empName+"\t\t\t RM "+dF.format(annSalary)+"\t\t\t ("+ empWorkYear+"  years)";
                outFile.println(empData);
            }
        
            //top performing employee detalis
            outFile.println("\n=================== Top Performing Employee Detalis ===================");
            String topEmpData = "Employee Name: "+topEmpName+"\nAnnual Salary: RM "+dF.format(topEmpAnnSalary)+"\nYears of Working: "+topEmpWorkYear+" years";
            outFile.println(topEmpData);
            
            //display top performing employee
            JOptionPane.showMessageDialog(null,"=================== Top Performing Employee Detalis ===================\n"+ topEmpData);
            
            //Latest performing employee detalis
            outFile.println("\n=================== Detalis  of Employee with the least years of service ===================");
            String latestEmpData = "Employee Name: "+latestEmpName+"\nAnnual Salary: RM "+dF.format(latestEmpAnnSalary)+"\nYears of Working: "+latestEmpWorkYear+" years";
            outFile.println(latestEmpData);
            
            //display latest performing employee
            JOptionPane.showMessageDialog(null,"=============== Detalis  of Employee with the least years of service ===============\n"+ latestEmpData);
            
            //close all files
            inFile.close();
            outFile.close();
        }
        catch(FileNotFoundException ex){ //to handle file not found error
            //display error message if the file path not found
            String output="File not found";
            JOptionPane.showMessageDialog(null, output,"Error!", JOptionPane.ERROR_MESSAGE);
            
        } catch(IllegalArgumentException iae){ 
            //display the error message if the data read from the file input is invalid
            String output="Invalid input! Please check your input file.";
            JOptionPane.showMessageDialog(null, output,"Error!", JOptionPane.ERROR_MESSAGE);
        }
    }
}
