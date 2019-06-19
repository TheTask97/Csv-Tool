package main;

import java.awt.Frame;

import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {

		try {
			Thread guiThread = new Thread(new Gui());
			guiThread.start();
			guiThread.join();
		} catch (Exception e) {

			JOptionPane.showMessageDialog(((new Frame())), "Programm coudnt start.", "Insane error",
					JOptionPane.ERROR_MESSAGE);

		}

	} 

}
