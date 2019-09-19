/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employeedatamanagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class FXMLDocumentController implements Initializable
{

   @FXML
   private BorderPane bpMain;
   @FXML
   private VBox topVbox1, topVbox2, vbox1, vbox2;
   private String current = "vbox1";
   @FXML
   private ComboBox<Department> cmbBoxDepartment;
   @FXML
   private ListView<Employee> lstView, lstResult;
   @FXML
   private TextField txtID, txtFirstName, txtLastName, txtTitle, txtStartDate, txtSalary, txtMarrital, txtCertificate, txtSearch;
   @FXML
   private RadioButton rbtID, rbtFirstName;
   @FXML
   private Button btnSave, btnCancel, btnExit, btnAdd, btnEdit, btnSearch, btnDelete;
   @FXML
   private ToggleGroup grpSearchby;

   private int editClicked = 0;

   private File file = new File("employees.csv");
   private EmployeeList employeeList;
   private int selectedEmployeeIndex;
   private int selectedDepartmentIndex;
   ObservableList<Employee> obsEmployeeList = FXCollections.observableArrayList();
   ObservableList<Department> departmentList = FXCollections.observableArrayList();

   @Override
   public void initialize (URL url, ResourceBundle rb)
   {
      //load the combo box with department list
      departmentList.addAll(Department.values());
      //for view,add, and edit department information.
      cmbBoxDepartment.setItems(departmentList);
      cmbBoxDepartment.getSelectionModel().select(0);


      try {
         if (file.exists()) {
            employeeList = EmployeeList.getInstance(file);

         }
      }
      catch (IOException ex) {
         System.out.println("Error: file not found" + ex.getMessage());
      }


      obsEmployeeList.addAll(employeeList);

      lstView.setItems(obsEmployeeList);
      lstView.getSelectionModel().select(0);
      displayRecord(obsEmployeeList.get(0));


      lstView.getSelectionModel().selectedItemProperty().addListener(new InvalidationListener()
      {

         @Override
         public void invalidated (Observable observable)
         {
            selectedEmployeeIndex = lstView.getSelectionModel().getSelectedIndex();
            displayRecord(obsEmployeeList.get(selectedEmployeeIndex));
         }
      });


      cmbBoxDepartment.getSelectionModel().selectedItemProperty().addListener(new InvalidationListener()
      {

         @Override
         public void invalidated (Observable observable)
         {
            selectedDepartmentIndex = cmbBoxDepartment.getSelectionModel().getSelectedIndex();

         }
      });


      btnSave.setDisable(true);
      btnCancel.setDisable(true);

   }

   @FXML  //ActionEvent event
   public void swap ()
   {
      if (current.equals("vbox1")) {
         bpMain.setTop(topVbox2);
         bpMain.setCenter(vbox2);
         current = "vbox2";
         txtSearch.clear();
         lstResult.getItems().clear();

      }
      else {
         bpMain.setTop(topVbox1);
         bpMain.setCenter(vbox1);
         current = "vbox1";

      }
   }

   private void displayRecord (Employee employee)
   {
      //accepts an Employee object then display that Employee object's data in the fields of UI.
      txtID.setEditable(false);
      txtFirstName.setEditable(false);
      txtLastName.setEditable(false);
      //txtDepartment.setEditable(false);
      cmbBoxDepartment.setValue(employeeList.get(selectedEmployeeIndex).getDepartment());
      txtTitle.setEditable(false);
      txtStartDate.setEditable(false);
      txtSalary.setEditable(false);
      txtMarrital.setEditable(false);
      txtCertificate.setEditable(false);

      txtID.setText(String.format("%s", employee.getID()));
      txtFirstName.setText(employee.getFirstName());
      //txtDepartment.setText(employee.getDepartment().getName());
      txtLastName.setText(employee.getLastName());
      txtTitle.setText(employee.getTitle());
      txtStartDate.setText(employee.getStartDate());
      txtSalary.setText(String.format("%.2f", employee.getSalary()));
      txtMarrital.setText(employee.getMarrital());
      txtCertificate.setText(employee.getCertificate());



      //set the color the grey
      txtID.setStyle("-fx-background-color:#f3f3f3");
      txtFirstName.setStyle("-fx-background-color:#f3f3f3");
      txtLastName.setStyle("-fx-background-color:#f3f3f3");
      txtStartDate.setStyle("-fx-background-color:#f3f3f3");
      cmbBoxDepartment.setStyle("-fx-background-color:#f3f3f3");
      txtSalary.setStyle("-fx-background-color:#f3f3f3");
      txtMarrital.setStyle("-fx-background-color:#f3f3f3");
      txtTitle.setStyle("-fx-background-color:#f3f3f3");
      txtCertificate.setStyle("-fx-background-color:#f3f3f3");
   }

   public void edit ()
   {

      editClicked = 1;
      txtID.setEditable(true);
      txtFirstName.setEditable(true);
      cmbBoxDepartment.setEditable(true);
      cmbBoxDepartment.setValue(employeeList.get(selectedEmployeeIndex).getDepartment());
      txtLastName.setEditable(true);
      txtTitle.setEditable(true);
      txtStartDate.setEditable(true);
      txtSalary.setEditable(true);
      txtMarrital.setEditable(true);
      txtCertificate.setEditable(true);
      setTxtFieldBcolorW();
      btnSave.setDisable(false);
      btnCancel.setDisable(false);
      btnAdd.setDisable(true);
      btnDelete.setDisable(true);
      btnEdit.setDisable(true);



   }

   public void setTxtFieldBcolorW ()
   {
      txtID.setStyle("-fx-background-color:white");
      txtFirstName.setStyle("-fx-background-color:white");
      cmbBoxDepartment.setStyle("-fx-background-color:white");
      txtLastName.setStyle("-fx-background-color:white");
      txtLastName.setStyle("-fx-background-color:white");
      txtTitle.setStyle("-fx-background-color:white");
      txtStartDate.setStyle("-fx-background-color:white");
      txtSalary.setStyle("-fx-background-color:white");
      txtMarrital.setStyle("-fx-background-color:white");
      txtCertificate.setStyle("-fx-background-color:white");

   }


   public void add ()
   {

      editClicked = 1;
      txtID.setEditable(true);
      txtFirstName.setEditable(true);
      cmbBoxDepartment.setEditable(true);
      cmbBoxDepartment.setValue(employeeList.get(selectedEmployeeIndex).getDepartment());
      txtLastName.setEditable(true);
      txtTitle.setEditable(true);
      txtStartDate.setEditable(true);
      txtSalary.setEditable(true);
      txtMarrital.setEditable(true);
      txtCertificate.setEditable(true);
      setTxtFieldBcolorW();
      //clear contents in the boxes
      txtID.clear();
      txtFirstName.clear();
      txtLastName.clear();
      txtTitle.clear();
      txtStartDate.clear();
      txtSalary.clear();
      txtMarrital.clear();
      txtCertificate.clear();

      btnSave.setDisable(false);
      btnCancel.setDisable(false);
      btnEdit.setDisable(true);
      btnDelete.setDisable(true);
      btnAdd.setDisable(true);
   }

   public void save () throws FileNotFoundException
   {
      if (editClicked == 1) {
         if (validInput()) {
            Employee e = new Employee(
                    txtID.getText(),
                    txtFirstName.getText(),
                    txtLastName.getText(),
                    departmentList.get(selectedDepartmentIndex), //cmbBoxDepartment.getValue(),
                    txtTitle.getText(),
                    txtStartDate.getText(),
                    Double.parseDouble(txtSalary.getText()),
                    txtMarrital.getText(),
                    txtCertificate.getText()
            );

            employeeList.add(e);
            obsEmployeeList.clear();
            obsEmployeeList.addAll(employeeList);

            lstView.setItems(obsEmployeeList);
            lstView.getSelectionModel().select(0);
            displayRecord(obsEmployeeList.get(0));

            employeeList.writeToFile(file);
            updated();
         }
         else {
            //for  record editting
            if (validInput()) {
               Employee editEmployee = employeeList.get(selectedEmployeeIndex);

               editEmployee.setID(txtID.getText());
               editEmployee.setFirstName(txtFirstName.getText());
               editEmployee.setLastName(txtLastName.getText());
               editEmployee.setDepartment(departmentList.get(selectedDepartmentIndex));
               editEmployee.setTitle(txtTitle.getText());
               editEmployee.setStartDate(txtStartDate.getText());
               editEmployee.setSalary(Double.parseDouble(txtSalary.getText()));
               editEmployee.setMarrital(txtMarrital.getText());
               editEmployee.setCertificate(txtCertificate.getText());


               obsEmployeeList.clear();
               obsEmployeeList.addAll(employeeList);

               lstView.setItems(obsEmployeeList);
               lstView.getSelectionModel().select(0);
               displayRecord(obsEmployeeList.get(0));

               employeeList.writeToFile(file);
            }
            updated();
         }
         editClicked = 0;

      }
      btnSave.setDisable(true);
      btnCancel.setDisable(true);
      btnAdd.setDisable(false);
      btnEdit.setDisable(false);
      btnDelete.setDisable(false);

   }

   public void cancel ()
   {
      editClicked = 0;
      displayRecord(employeeList.get(selectedEmployeeIndex));
      btnSave.setDisable(true);
      btnCancel.setDisable(true);
      btnEdit.setDisable(false);
      btnDelete.setDisable(false);
      btnAdd.setDisable(false);

   }

   public void delete () throws FileNotFoundException
   {
      editClicked = 0;
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle(" Delete Record ");
      alert.setHeaderText(null);
      alert.setContentText("Are you sure you wish to delete the record?");
      Optional<ButtonType> result = alert.showAndWait();
      if (result.get() == ButtonType.OK) {
         employeeList.remove(selectedEmployeeIndex);
         obsEmployeeList.clear();
         obsEmployeeList.addAll(employeeList);

         lstView.setItems(obsEmployeeList);
         lstView.getSelectionModel().select(0);
         displayRecord(obsEmployeeList.get(0));

         employeeList.writeToFile(file);
      }
      btnAdd.setDisable(false);
      btnEdit.setDisable(false);
      btnDelete.setDisable(false);
      btnSave.setDisable(true);
      btnCancel.setDisable(true);

   }

   public void exit () throws FileNotFoundException
   {
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Exit Program");
      alert.setHeaderText(null);
      alert.setContentText("Are you sure you wish to exit?");
      Optional<ButtonType> result = alert.showAndWait();
      if (result.get() == ButtonType.OK) {

         employeeList.writeToFile(file);
         System.exit(0);
      }
   }

   private static void dataEntryError ()
   {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Data Entry Error");
      alert.setHeaderText(null);
      alert.setContentText("Please enter the correct number.");
      Optional<ButtonType> result = alert.showAndWait();
   }

   private static void validDataError ()
   {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Data Entry Error");
      alert.setHeaderText(null);
      alert.setContentText("You must enter a valid numeric value.");
      Optional<ButtonType> result = alert.showAndWait();
   }

   private static void zeroDataError ()
   {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Data Entry Error");
      alert.setHeaderText(null);
      alert.setContentText("You must enter a value greater than 0.");
      Optional<ButtonType> result = alert.showAndWait();
   }

   private static void updated ()
   {
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Data Updated");
      alert.setHeaderText(null);
      alert.setContentText("Your data was updated successfully.");
      Optional<ButtonType> result = alert.showAndWait();
   }

   private boolean isValidDouble (String string)
   {

      boolean numeric = true;
      try {
         Double num = Double.parseDouble(string);
      }
      catch (NumberFormatException e) {
         numeric = false;
      }
      return numeric;
   }

   private boolean validInput ()
   {
      boolean valid = false;
      if (txtID.getText().trim().isEmpty()) {
         dataEntryError();
         txtID.requestFocus();
      }
      if (txtFirstName.getText().trim().isEmpty()) {
         dataEntryError();
         txtFirstName.requestFocus();
      }
      if (txtLastName.getText().trim().isEmpty()) {
         dataEntryError();
         txtLastName.requestFocus();
      }
      else if (!isValidDouble(txtSalary.getText().trim())) {
         validDataError();
         txtSalary.requestFocus();
      }

      valid = true;
      return valid;
   }
   int selectedSearchIndex;
   String selectedID;

   @FXML
   public void doSearch ()
   {
      lstResult.getItems().clear();
      EmployeeList tempList;

      ObservableList<Employee> obsSearchList = FXCollections.observableArrayList();

      if (rbtFirstName.isSelected()) {
         tempList = employeeList.findEmployeeByFirstName(txtSearch.getText().trim());
      }
      else {
         tempList = employeeList.findEmployeeByID(txtSearch.getText().trim());
      }

      obsSearchList.addAll(tempList);

      lstResult.setItems(obsSearchList);

      lstResult.getSelectionModel().select(0);


      lstResult.getSelectionModel().selectedItemProperty().addListener(new InvalidationListener()
      {

         @Override
         public void invalidated (Observable observable)
         {

            selectedSearchIndex = lstResult.getSelectionModel().getSelectedIndex();
            selectedID = lstResult.getSelectionModel().getSelectedItem().getID();
         }
      });
   }

   @FXML
   public void select ()
   {
      int eIndex = employeeList.indexOf(employeeList.find(selectedID));

      swap();

      lstView.getSelectionModel().select(eIndex);
      displayRecord(employeeList.get(eIndex));

   }

}
