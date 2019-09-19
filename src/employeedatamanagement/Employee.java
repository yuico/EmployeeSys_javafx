package employeedatamanagement;
public class Employee
{
   private String ID;
   private String firstName;
   private String lastName;
   private Department department;
   private String title;
   private String startDate;
   private double salary;
   private String marrital;
   private String certificate;

   public Employee ()
   {
   }

   public Employee (String ID, String firstName, String lastName, Department department, String title,
                    String startDate, double salary, String marrital, String certificate)
   {
      this.ID = ID;
      this.firstName = firstName;
      this.lastName = lastName;
      this.department = department;
      this.title = title;
      this.startDate = startDate;
      this.salary = salary;
      this.marrital = marrital;
      this.certificate = certificate;
   }

   public String getID ()
   {
      return ID;
   }

   public void setID (String ID)
   {
      this.ID = ID;
   }

   public String getFirstName ()
   {
      return firstName;
   }

   public void setFirstName (String firstName)
   {
      this.firstName = firstName;
   }

   public String getLastName ()
   {
      return lastName;
   }

   public void setLastName (String lastName)
   {
      this.lastName = lastName;
   }

   public Department getDepartment ()
   {
      return department;
   }

   public void setDepartment (Department department)
   {
      this.department = department;
   }

   public String getTitle ()
   {
      return title;
   }

   public void setTitle (String title)
   {
      this.title = title;
   }

   public String getStartDate ()
   {
      return startDate;
   }

   public void setStartDate (String startDate)
   {
      this.startDate = startDate;
   }

   public double getSalary ()
   {
      return salary;
   }

   public void setSalary (double salary)
   {
      this.salary = salary;
   }

   public String getMarrital ()
   {
      return marrital;
   }

   public void setMarrital (String marrital)
   {
      this.marrital = marrital;
   }

   public String getCertificate ()
   {
      return certificate;
   }

   public void setCertificate (String certificate)
   {
      this.certificate = certificate;
   }

   public String toFileString ()
   {
      return String.format("%s,%s,%s,%s,%s,%s,%.2f,%s,%s", ID, firstName, lastName,
                           department.name(), title, startDate, salary, marrital, certificate);


   }

   /**
    *
    * @return a string of the first name and last name
    */
   @Override
   public String toString ()
   {
      return String.format("%s ,%s (%s)", lastName, firstName, ID);
   }

}
