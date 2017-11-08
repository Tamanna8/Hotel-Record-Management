package GroupTwo.ui;

import GroupTwo.model.AppModel;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * View logic contain with screen encapsulated all UI that user interact with.
 *
 * @author Thapana Berpan
 * @author Francesco Ward
 *
 * @version 8.0 8/23/2016
 */
public class AppView extends Application {

    private Button submitAdd;
    private Button submitCheck;
    private Button displayAll;
    AppModel app = new AppModel();

    /**
     * Creating scene as a layout for the graphic Also launched and created a
     * main screen
     *
     * @param stage The platform for the GUI
     */
    public void start(Stage stage) {
        stage.setTitle("Hotel Manager Sceen");
        Scene scene = new Scene(loadMainScreen(), 400, 400);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Creating VBox that allow user to traveling to different UI windows.
     *
     * @return VBox contain all the four button and text
     */
    public VBox loadMainScreen() {
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);

        // create buttons and set action to load individual screens
        // check button
        Button btnCheckCustomerStatus = new Button("Check Customer Status");
        btnCheckCustomerStatus.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                btnCheckCustomerStatus.getScene().setRoot(goToCheckCustomerStatus());
            }
        });
        // add button
        Button btnAddCustomer = new Button("Add Customer");
        btnAddCustomer.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                btnAddCustomer.getScene().setRoot(goToAddCustomer());
            }
        });
        // remove button
        Button btnRemoveCustomer = new Button("Remove Customer");
        btnRemoveCustomer.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                btnRemoveCustomer.getScene().setRoot(goToRemoveCustomer());
            }
        });
        //update button
        Button btnUpdateCustomer = new Button("Update Customer");
        btnUpdateCustomer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                btnUpdateCustomer.getScene().setRoot(goToUpdateCustomer());
            }
        });

        // Go to other screen
        Text title = new Text("Welcome to Island Hotel\n\n");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        vBox.getChildren().add(title);
        Text intro = new Text("Our Island Hotel always value every customer's "
                + "experiences \n and pleasure to offer the best service in the world.\n\n");
        intro.setFont(Font.font("Arial", 12));
        vBox.getChildren().add(intro);
        Text text2 = new Text("Go to Add a Customer");
        vBox.getChildren().addAll(text2, btnAddCustomer);
        Text text1 = new Text("Go to Check Customer Status");
        vBox.getChildren().addAll(text1, btnCheckCustomerStatus);
        Text text4 = new Text("Go to Update a Customer");
        vBox.getChildren().addAll(text4, btnUpdateCustomer);
        Text text3 = new Text("Go to Remove a Customer");
        vBox.getChildren().addAll(text3, btnRemoveCustomer);

        vBox.setStyle("-fx-background-color:  #D0E6FA;");

        return vBox;
    }

    /**
     * Creating VBox for Checking Customer UI setting.
     *
     * @return VBox contain all the input, output graphic
     */
    public VBox goToCheckCustomerStatus() {
        Label title = new Label("Check Customer Status");
        title.setPadding(new Insets(5.0, 5.0, 20.0, 20.0));
        title.setFont(Font.font("Arial", FontWeight.BOLD, 12.0));
        Label lblInputText = new Label("Name: ");
        Label lblInputRoom = new Label("Room: ");
        TextField inputText = new TextField();
        TextField inputRoom = new TextField();

        inputText.setMaxWidth(500);
        String output = "";

        Label outputText = new Label();
        outputText.setWrapText(true);
        outputText.setMinSize(200, 200);

        submitCheck = new Button("Submit");
        submitCheck.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                try {
                    String input = inputText.getText();
                    int room = Integer.parseInt(inputRoom.getText());
                    if (room > 0) {
                        if (!input.equals("")) {
                            if (app.checkCustomer(input, room)) {
                                outputText.setText(app.displayEquals(input));
                            } else {
                                outputText.setText("Customer " + input + " is NOT in the db");
                            }
                        } else {
                            //get customers from room
                            outputText.setText(app.displayCustomersInArray(app.retrieveCustomersFromRoom(room)));
                        }
                    } else {
                        throw new IllegalArgumentException();
                    }
                } catch (Exception e) {
                    outputText.setText("Please enter a valid room number!");
                }
            }
        });

        displayAll = new Button("Display All");
        displayAll.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                outputText.setText(app.displayAll());
            }
        });

        final Button back = new Button("Back");
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                back.getScene().setRoot(loadMainScreen());
            }
        });

        // Created HBox for Vbox
        HBox titleBox = new HBox(title);
        HBox firstBox = new HBox(lblInputText, inputText);
        HBox secondBox = new HBox(outputText);

        HBox thirdBox = new HBox(submitCheck, displayAll, back);
        HBox sixthBox = new HBox(lblInputRoom, inputRoom);
        StackPane forthBox = new StackPane();
        HBox.setHgrow(forthBox, Priority.ALWAYS);

        titleBox.setAlignment(Pos.CENTER);
        firstBox.setAlignment(Pos.CENTER_LEFT);
        sixthBox.setAlignment(Pos.CENTER_LEFT);
        thirdBox.setAlignment(Pos.CENTER);
        forthBox.setAlignment(Pos.BOTTOM_CENTER);
        firstBox.setPadding(new Insets(5.0, 5.0, 5.0, 15.0));
        secondBox.setPadding(new Insets(5.0, 5.0, 5.0, 5.0));
        sixthBox.setPadding(new Insets(5.0, 5.0, 5.0, 15.0));

        VBox vBox = new VBox(titleBox, firstBox, sixthBox, secondBox, thirdBox,
                forthBox /*fifthBox*/);
        vBox.setAlignment(Pos.CENTER);
        vBox.setStyle("-fx-background-color:  #D0E6FA;");
        return vBox;
    }

    /**
     * Creating VBox for Add Customer UI setting.
     *
     *
     * @return VBox contain all the input, output graphic
     */
    public VBox goToAddCustomer() {
        Label title = new Label("Add Customer");
        title.setPadding(new Insets(5.0, 5.0, 20.0, 20.0));
        title.setFont(Font.font("Arial", FontWeight.BOLD, 12.0));
        Label lblName = new Label("Name: ");
        TextField inputName = new TextField();
        inputName.setEditable(true);
        Label lblRoom = new Label("Room: ");
        TextField inputRoom = new TextField();
        inputRoom.setEditable(true);
        Label lblNights = new Label("Nights: ");
        TextField inputNights = new TextField();
        Label lblCell = new Label("Cell#: ");
        TextField txtCell = new TextField();
        inputRoom.setMaxWidth(500);
        inputNights.setMaxWidth(500);
        inputName.setMaxWidth(500);
        TextField outputText = new TextField("");
        outputText.setDisable(true);
        outputText.setMinSize(300, 200);

        submitAdd = new Button("Submit");
        submitAdd.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                try {
                    String regex = "^[a-zA-Z]+$";
                    String iName = inputName.getText();
                    // validate name input
                    if (iName.matches(regex)) {
                        int iRoom = Integer.parseInt(inputRoom.getText());
                        int nights = Integer.parseInt(inputNights.getText());
                        String cell = txtCell.getText();
                           // valide night input
                        if (iRoom > 0 && nights > 0) {
                            outputText.setText(app.addCustomer(iName, iRoom, nights, cell));
                        } else {
                            throw new IllegalArgumentException();
                        }
                    } else {
                        outputText.setText("Please enter valid letter \n for name");
                    }

                } catch (Exception e) {
                    outputText.setText("Please enter valid numbers \n for room and nights!");
                }
            }
        });
        // Back button
        final Button back = new Button("Back");
        back.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                back.getScene().setRoot(loadMainScreen());
            }
        });
        Text text = new Text("Add a Customer");

        HBox titleBox = new HBox(title);
        HBox firstBox = new HBox(lblName, inputName);
        HBox fifthBox = new HBox(lblRoom, inputRoom);
        HBox secondBox = new HBox(outputText);
        HBox thirdBox = new HBox(submitAdd, back);
        HBox sixthBox = new HBox(lblNights, inputNights);
        HBox eighthBox = new HBox(lblCell, txtCell);
        titleBox.setAlignment(Pos.CENTER);
        firstBox.setAlignment(Pos.CENTER_LEFT);
        firstBox.setPadding(new Insets(0.0, 0.0, 0.0, 15.0));
        secondBox.setAlignment(Pos.BASELINE_LEFT);
        thirdBox.setAlignment(Pos.CENTER);
        fifthBox.setPadding(new Insets(1.0, 1.0, 1.0, 15.0));
        secondBox.setPadding(new Insets(1.0, 1.0, 1.0, 15.0));
        sixthBox.setPadding(new Insets(1.0, 1.0, 1.0, 15.0));
        eighthBox.setAlignment(Pos.CENTER_LEFT);
        eighthBox.setPadding(new Insets(1.0, 1.0, 1.0, 15.0));

        VBox vBox = new VBox(titleBox, firstBox, fifthBox, sixthBox, eighthBox, secondBox,
                thirdBox);
        vBox.setAlignment(Pos.CENTER);
        vBox.setStyle("-fx-background-color:  #D0E6FA;");
        return vBox;
    }

    /**
     * Creating VBox for Removing Customer UI setting.
     *
     * @return VBox contain all the button, input, output graphic
     */
    public VBox goToRemoveCustomer() {
        Label title = new Label("Remove Customer");
        title.setPadding(new Insets(5.0, 5.0, 20.0, 20.0));
        title.setFont(Font.font("Arial", FontWeight.BOLD, 12.0));
        Label lblInputText = new Label("Name: ");
        Label lblInputRoom = new Label("Room: ");
        TextField inputText = new TextField();
        TextField inputRoom = new TextField();
        inputText.setMaxWidth(500);
        inputRoom.setMaxWidth(500);
        TextField outputText = new TextField("");
        outputText.setDisable(true);
        outputText.setMinSize(300, 200);

        // Submit Button
        final Button submit = new Button("Submit");
        submit.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                try {
                    String input = inputText.getText();
                    int room = Integer.parseInt(inputRoom.getText());
                    outputText.setText(app.removeCustomer(input, room));
                } catch (Exception e) {
                    outputText.setText("Please enter a valid room number");
                }
            }
        });
        //Button to remove all the customers
        final Button removeAll = new Button("Remove All");
        removeAll.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                app.removeAll();
                outputText.setText("Hotel is Empty!");
            }
        });

        final Button back = new Button("Back");
        back.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                back.getScene().setRoot(loadMainScreen());
            }
        });
        // Button to delete a cutomer
        Text text = new Text("Delete a Customer");

        HBox titleBox = new HBox(title);
        HBox firstBox = new HBox(lblInputText, inputText);
        HBox secondBox = new HBox(outputText);
        HBox thirdBox = new HBox(submit, removeAll, back);
        HBox fifthBox = new HBox(lblInputRoom, inputRoom);
        titleBox.setAlignment(Pos.CENTER);
        firstBox.setAlignment(Pos.CENTER_LEFT);
        firstBox.setPadding(new Insets(5.0, 5.0, 5.0, 15.0));
        secondBox.setAlignment(Pos.BASELINE_LEFT);
        secondBox.setPadding(new Insets(5.0, 5.0, 5.0, 5.0));
        thirdBox.setAlignment(Pos.CENTER);
        thirdBox.setPadding(new Insets(5.5, 5.0, 5.0, 5.0));
        fifthBox.setPadding(new Insets(5.5, 5.0, 5.0, 15.0));

        VBox vBox = new VBox(titleBox, firstBox, fifthBox,
                secondBox, thirdBox);
        vBox.setAlignment(Pos.CENTER);
        vBox.setStyle("-fx-background-color:  #D0E6FA;");
        return vBox;
    }

    /**
     * Creating VBox for Updating Customer UI setting.
     *
     * @return VBox contain all the button, input, output graphic
     */
    public VBox goToUpdateCustomer() {
        Label title = new Label("Update Customer");
        title.setPadding(new Insets(0.0, 0.0, 0.0, 0.0));
        title.setFont(Font.font("Arial", FontWeight.BOLD, 12.0));
        Label lblInputText = new Label("Input Name: ");
        Label lblInputRoom = new Label("Update Room: ");
        Label lblInputNights = new Label("Update nights: ");
        Label lblUpdateCell = new Label("Update cell: ");
        TextField updateCell = new TextField();
        TextField inputNights = new TextField();
        TextField inputText = new TextField();
        TextField inputRoom = new TextField();

        inputText.setMaxWidth(500);
        // Setting  
        String output = "";

        Label outputText = new Label();
        outputText.setWrapText(true);
        outputText.setMinSize(200, 200);

        // Button to update the information of cutomer
        submitCheck = new Button("Update");
        submitCheck.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                try {
                    String input = inputText.getText();
                    int room = Integer.parseInt(inputRoom.getText());
                    int nights = Integer.parseInt(inputNights.getText());
                    String cell = updateCell.getText();
                    //update room number, nights & cell
                    outputText.setText(app.updateCustomer(input, room, nights, cell));
                } catch (Exception e) {
                    outputText.setText("Please enter valid numbers for room and nights!");
                }
            }
        });
        // Button to display all the information of the customer
        displayAll = new Button("Display All");
        displayAll.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                outputText.setText(app.displayAll());
            }
        });
        // Button to go back to old window
        final Button button = new Button("Back");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                button.getScene().setRoot(loadMainScreen());
            }
        });

        // Created HBox for Vbox
        HBox titleBox = new HBox(title);
        HBox firstBox = new HBox(lblInputText, inputText);
        HBox secondBox = new HBox(outputText);

        HBox thirdBox = new HBox(submitCheck, displayAll, button);
        HBox sixthBox = new HBox(lblInputRoom, inputRoom);
        HBox seventhBox = new HBox(lblInputNights, inputNights);
        HBox eighthBox = new HBox(lblUpdateCell, updateCell);

        titleBox.setAlignment(Pos.CENTER);
        firstBox.setAlignment(Pos.CENTER_LEFT);
        sixthBox.setAlignment(Pos.CENTER_LEFT);
        seventhBox.setAlignment(Pos.CENTER_LEFT);
        thirdBox.setAlignment(Pos.CENTER);
        eighthBox.setAlignment(Pos.CENTER_LEFT);

        titleBox.setPadding(new Insets(0.0, 0.0, 0.0, 0.0));
        firstBox.setPadding(new Insets(10.0, 1.0, 1.0, 15.0));
        sixthBox.setPadding(new Insets(1.0, 1.0, 1.0, 15.0));
        seventhBox.setPadding(new Insets(1.0, 1.0, 1.0, 15.0));
        eighthBox.setPadding(new Insets(1.0, 1.0, 1.0, 15.0));

        VBox vBox = new VBox(titleBox, firstBox, sixthBox, seventhBox, eighthBox, secondBox, thirdBox);
        vBox.setAlignment(Pos.CENTER);
        vBox.setStyle("-fx-background-color:  #D0E6FA;");
        return vBox;
    }

}
