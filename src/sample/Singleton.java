package sample;

import javafx.scene.control.TextField;

/**
 * GUI Created by akeske on 07/06/2015.
 */
public class Singleton {

	private static Singleton instance = new Singleton();

	public static Singleton getInstance(){
		return instance;
	}

	public TextField getTxtField1() {
		return txtField1;
	}

	public void setTxtField1(TextField txtField1) {
		this.txtField1 = txtField1;
	}

	private TextField txtField1;
}
