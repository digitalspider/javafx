package au.com.jcloud;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class FXMLDocumentController {

	@FXML // This declares a label that is in the FXML document
	private Label message;

	@FXML
	private TableView<Person> tableview;
	@FXML
	private TableColumn<Person, String> firstNameColumn;
	@FXML
	private TableColumn<Person, String> lastNameColumn;

	public FXMLDocumentController() {
		super();
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	public void initialize() {
		if (message != null) {
			message.setText("javafx");
		}
		// Initialize the person table with the two columns.
//		firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().getFirstName());
//		lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().getLastName());
	}

	@FXML // This code runs when a button is clicked
	private void handleButtonAction(ActionEvent e) {
		System.out.println("You clicked me: " + e.toString());
		message.setText("You clicked me: " + e.toString());
	}
}