/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employeedatamanagement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EmployeeDataManagement extends Application
{

   @Override
   public void start (Stage stage) throws Exception
   {
      Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

      Scene scene = new Scene(root);
      stage.setTitle("Employees Management System");
      stage.setScene(scene);
      stage.show();
   }

   /**
    * add main method
    */
   public static void main (String[] args)
   {
      launch(args);
   }

}
