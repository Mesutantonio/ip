package conversal;

import conversal.storage.Storage;
import conversal.task.TaskList;
import conversal.ui.GuiUi;
import conversal.ui.MainWindowController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainApp extends Application {
    private Conversal conversal;

    @Override
    public void start(Stage stage) throws Exception {
        GuiUi ui = new GuiUi();
        Storage storage = new Storage("./data/tasks.txt");
        TaskList tasks = new TaskList(storage.load());
        conversal = new Conversal(ui, storage, tasks);

        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/view/MainWindow.fxml"));
        AnchorPane root = loader.load();

        MainWindowController controller = loader.getController();
        controller.setConversal(conversal);

        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Conversal");
        stage.setScene(scene);
        stage.show();

        controller.showConversal("Hello! I'm Conversal. What can I do for you?");
    }
}
