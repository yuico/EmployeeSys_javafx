package employeedatamanagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmployeeList extends ArrayList<Employee> {

    private static EmployeeList single_instance = null;

    //the default constructor
    private EmployeeList() {

    }

    private EmployeeList(File file) throws FileNotFoundException {
        loadFromFile(file);
    }

    //static method to create instance of Singleton class 
    public static EmployeeList getInstance(File file) throws FileNotFoundException {
        if (single_instance == null) {
            single_instance = new EmployeeList(file);
        }
        return single_instance;
    }

    public Employee find(String ID) {

        int temp = -1;
        boolean temp2 = false;

        for (Employee e : this) {
            if (e.getID() == ID) {
                temp = this.indexOf(e);
                temp2 = true;
            }
        }

        if (temp2) {
            return this.get(temp);
        } else {
            return null;
        }
    }

    public void loadFromFile(File file) throws FileNotFoundException {
        try {
            if (file.exists()) {
                Scanner scanner = new Scanner(file);
                scanner.useDelimiter("\r\n|[\r\n\u2028\u2029\u0085]");
                while (scanner.hasNextLine()) {
                    String s = scanner.nextLine();
                    String[] fields = s.split(",");
                    Boolean tf = isValidDouble(fields[6]);
                    if (tf) {
                        this.add(
                                new Employee(
                                        fields[0],
                                        fields[1],
                                        fields[2],
                                        Department.getDepartmentName(fields[3]),
                                        fields[4],
                                        fields[5],
                                        Double.parseDouble(fields[6]),
                                        fields[7],
                                        fields[8])
                        );
                    }
                }
                scanner.close();
            }
        } catch (IOException ex) {
            System.out.println("Error: file not found" + ex.getMessage());
        }

    }

    private boolean isValidDouble(String string) {

        boolean numeric = true;
        try {
            Double num = Double.parseDouble(string);
        } catch (NumberFormatException e) {
            numeric = false;
        }
        return numeric;
    }

    public void writeToFile(File file) throws FileNotFoundException {
        try {
            if (file.exists()) {
                PrintWriter fileOut = new PrintWriter(file);
                if (fileOut != null && this != null) {
                    for (Employee e : this) {
                        fileOut.println(e.toFileString());
                    }
                }
                fileOut.close();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public EmployeeList findEmployeeByFirstName(String name) {
        EmployeeList temp = new EmployeeList();

        for (Employee e : this) {
            Pattern p = Pattern.compile(name.trim().toLowerCase());
            Matcher m = p.matcher(e.getFirstName().trim().toLowerCase());
            if (m.find()) {
                temp.add(e);
            }
        }

        return temp;
    }

    public EmployeeList findEmployeeByID(String ID) {
        EmployeeList temp = new EmployeeList();

        for (Employee e : this) {
            Pattern p = Pattern.compile(ID.trim().toLowerCase());
            Matcher m = p.matcher(e.getID().trim().toLowerCase());
            if (m.find()) {
                temp.add(e);
            }
        }

        return temp;

    }

    public String[] getEmployeeNameList() {
        String[] EmployeeNameList = new String[this.size()];

        for (int i = 0; i < this.size(); i++) {
            EmployeeNameList[i] = this.get(i).getFirstName() + this.get(i).getLastName();
        }
        return EmployeeNameList;
    }

}
