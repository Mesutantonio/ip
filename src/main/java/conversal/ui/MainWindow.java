package conversal.ui;

import conversal.Conversal;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

public class MainWindowController {
    @FXML private ScrollPane scrollPane;
    @FXML private VBox dialogContainer;
    @FXML private TextField userInput;

    private Conversal conversal;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image botImage  = new Image(this.getClass().getResourceAsStream("/images/DaConversal.png"));

    public void setConversal(Conversal conversal) {
        this.conversal = conversal;
        dialogContainer.heightProperty().addListener((obs, oldV, newV) -> scrollPane.setVvalue(1.0));
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input == null || input.isBlank()) return;

        dialogContainer.getChildren().add(DialogBox.userDialog(input, userImage));

        String response = conversal.getResponse(input);
        dialogContainer.getChildren().add(DialogBox.conversalDialog(response, botImage));

        if ("bye".equalsIgnoreCase(input.trim())) {
            userInput.setDisable(true);
        }
        userInput.clear();
    }

    public void showConversal(String message) {
        dialogContainer.getChildren().add(DialogBox.conversalDialog(message, botImage));
    }
}
