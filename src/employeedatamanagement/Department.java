package employeedatamanagement;
import java.util.HashMap;
public enum Department
{
   SALES_MARKETING(1, "Sale Marketing"),
   HR_ADMIN(2, "HR Admin"),
   TECHNICAL_ENGINEERING(3, "Technical Engineering"),
   PROJECT_MANAGEMENT(4, "Project Management");

   private int id;
   private String name;
   private static HashMap<String, Department> lookUpByName = null;


   private Department (int id, String name)
   {
      this.id = id;
      this.name = name;

   }

   private static void initNameLookUp ()
   {
      lookUpByName = new HashMap<String, Department>();
      for (Department d : Department.values()) {
         lookUpByName.put(d.name, d);
      }
   }

   public static Department getDepartmentByFullName (String name)
   {
      if (lookUpByName == null) {
         initNameLookUp();
      }
      Department d = lookUpByName.get(name);
      if (d == null) {
         throw new IllegalArgumentException("Invalid Department name.");
      }
      return d;
   }

   public static Department getDepartmentName (String name)
   {

      return Department.valueOf(name);
   }

   public int getId ()
   {
      return id;
   }

   public String getName ()
   {
      return name;
   }

}
