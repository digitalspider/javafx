package au.com.jcloud;

import java.net.URL;
import java.util.Arrays;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Some tutorials:
 * http://code.makery.ch/library/javafx-8-tutorial/
 * http://www.javafxtutorials.com/
 * https://www.tutorialspoint.com/javafx/index.htm
 * http://docs.oracle.com/javafx/2/get_started/jfxpub-get_started.htm
 *
 * @author davidv
 */
public class App extends Application {

	private ObservableList<Person> personData = FXCollections.observableArrayList();

	public static void main(String[] args) {
		System.out.println("Hello World!");
		launch(args);
	}

	private void initPersonData() {
		personData.add(new Person("david", "vittor", "davidv@jcloud.com.au"));
		personData.add(new Person("anand", "parajuli", "anandp@jcloud.com.au"));
		personData.add(new Person("sarah", "tabassum", "saraht@jcloud.com.au"));
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Hello World!");

		URL url = getClass().getResource("/ui/page.fxml");
		System.out.println("url=" + url);
		Parent pane = FXMLLoader.load(url);

		for (Node child : pane.getChildrenUnmodifiable()) {
			System.out.println(child);
		}

//		GridPane pane = getGridPane();
//		addGridComponents(pane);

//		StackPane pane = new StackPane();
//		FlowPane pane = new FlowPane();
//		addStackComponents(pane);

		Scene scene = new Scene(pane, 650, 430);

		final Label message = (Label) scene.lookup("#message");
		message.setText("greetings!");
		Button button = (Button) scene.lookup("#button");
		EventHandler<? super MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				System.out.println("event=" + event);
				message.setText("clicked");
				if (!getPersonData().isEmpty()) {
					getPersonData().remove(0);
				}
				else {
					initPersonData();
				}
			}
		};
		button.setOnMouseClicked(eventHandler);
		TableView<?> tableView = (TableView<?>) scene.lookup("#tableview");
		System.out.println("Table=" + tableView);

		TableColumn firstNameColumn = tableView.getColumns().get(0);
		firstNameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));
		TableColumn lastNameColumn = tableView.getColumns().get(1);
		lastNameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("lastName"));
		TableColumn emailColumn = tableView.getColumns().get(2);
		emailColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("email"));

		initPersonData();
		tableView.setItems((ObservableList) getPersonData());

		TreeView<String> treeView = (TreeView<String>) scene.lookup("#treeview");
		System.out.println("Tree=" + treeView);

		List<String> data = Arrays.asList(new String[] { "book1", "book2", "book3" });
		List<String> book1ldata = Arrays.asList(new String[] { "book1.1", "book1.2", "book1.3" });
		TreeItem<String> root = new TreeItem<String>("Root Node");
		root.setExpanded(true);
		for (String itemString : data) {
			TreeItem child = new TreeItem<String>(itemString);
			root.getChildren().add(child);
			// Add children for book1
			if ("book1".equals(itemString)) {
				for (String book1Item : book1ldata) {
					child.getChildren().add(new TreeItem<String>(book1Item));
				}
			}
		}
		treeView.setRoot(root);

		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void addStackComponents(Pane pane) {
		Button btn = createButton("Say 'Hello World'");
		pane.getChildren().add(btn);
		pane.getChildren().add(createButton("Say 'Hello World2'"));
		pane.getChildren().add(createButton("Say 'Hello World3'"));
	}

	private GridPane getGridPane() {
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setPadding(new Insets(25, 25, 25, 25));
		return pane;
	}

	private void addGridComponents(GridPane grid) {
		Text scenetitle = new Text("Welcome");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(scenetitle, 0, 0, 2, 1);

		Label userName = new Label("User Name:");
		grid.add(userName, 0, 1);

		TextField userTextField = new TextField();
		grid.add(userTextField, 1, 1);

		Label pw = new Label("Password:");
		grid.add(pw, 0, 2);

		PasswordField pwBox = new PasswordField();
		grid.add(pwBox, 1, 2);

		final Text actiontarget = new Text();
		grid.add(actiontarget, 1, 6);

		Button btn = new Button("Sign in");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(btn);
		grid.add(hbBtn, 1, 4);
		btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				actiontarget.setFill(Color.FIREBRICK);
				actiontarget.setText("Sign in button pressed");
			}
		});
	}

	private Button createButton(String label) {
		Button btn = new Button();
		btn.setText(label);
		btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				System.out.println("Hello World!");
			}
		});
		return btn;
	}

	public ObservableList<Person> getPersonData() {
		return personData;
	}

	public void setPersonData(ObservableList<Person> personData) {
		this.personData = personData;
	}
}
