package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

	private static Controller instance;

	public static Controller getInstance(){
		if(Controller.instance == null){
			synchronized (Controller.class){
				if(Controller.getInstance()==null){
					Controller.instance = new Controller();
				}
			}
		}
		return Controller.instance;
	}

	private FileChooser fileChooser;

	@FXML
	private Label labelFile;

	@FXML
	private Label labelMe;

	@FXML
	private Label labelFullConvert;

	@FXML
	private Label labelActiveDirecotryConvert;

	@FXML
	private TextField textName;

	@FXML
	private TextField textDelimiter;

	@FXML
	private Button buttonUploadFile;

	// reference to main application
	private Main mainApp;

	public Controller(){

	}

	@FXML
	private void copy2clipboardFull(){
		final Clipboard clipboard = Clipboard.getSystemClipboard();
		ClipboardContent content = new ClipboardContent();
		content.putString(labelFullConvert.getText());
		clipboard.setContent(content);
	}

	@FXML
	private void copy2clipboardAD(){
		final Clipboard clipboard = Clipboard.getSystemClipboard();
		ClipboardContent content = new ClipboardContent();
		content.putString(labelActiveDirecotryConvert.getText());
		clipboard.setContent(content);
	}

	@FXML
	private void changeLabels() {
		Process proc = new Process();
		String text = textName.getText();
		String[] pieces = text.split(" ");

		if (pieces[0].length() > 0) {
			proc.setNames(pieces[0]);
			try {
				proc.exec(0);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (pieces.length > 1) {
				if (pieces[1].length() > 0) {
					proc.setSurNames(pieces[1]);
					try {
						proc.exec(1);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			//		labelActiveDirecotryConvert.setText(proc.getNewSurNames());
			labelFullConvert.setText(proc.getNameAndSurname());
			labelActiveDirecotryConvert.setText(proc.getForAD());
		}else{
			labelFullConvert.setText("");
			labelActiveDirecotryConvert.setText("");
		}

	}

	@FXML
	private void uploadFile(){
		Process proc = new Process();
		fileChooser = new FileChooser();
		configureFileChooser(fileChooser);
	//	File file = fileChooser.showOpenDialog(null);
		File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage().getScene().getWindow());
		if(file != null){
			labelFile.setText(file.getName());
			openFile(file);
			try {
				proc = new Process(file, this);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void configureFileChooser(FileChooser fileChooser){
		fileChooser.setTitle("Select your file to convert");
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF", "*.*");
		FileChooser.ExtensionFilter extFilter1 = new FileChooser.ExtensionFilter("TXT", "*.txt");
		FileChooser.ExtensionFilter extFilter2 = new FileChooser.ExtensionFilter("CSV", "*.csv");
	//	fileChooser.getExtensionFilters().add(extFilter);
		fileChooser.getExtensionFilters().add(extFilter1);
		fileChooser.getExtensionFilters().add(extFilter2);
	}

	private void openFile(File file) {

	}

	public void setMainApp(Main mainApp){
		this.mainApp = mainApp;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	//	Singleton.getInstance().setTxtField1(textDelimiter);
	}

	public TextField getTextDelimiter() {
		return textDelimiter;
	}
}
