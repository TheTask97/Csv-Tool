package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JButton;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Gui implements Runnable {

	private JFrame frame;
	Gui window;

	/**
	 * Launch the application.
	 * 
	 * @return
	 */

	@Override
	public void run() {
		try {
			window = new Gui();
			window.frame.setVisible(true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the application.
	 */
	public Gui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Choose the Application to start");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel.setBounds(81, 28, 275, 31);
		frame.getContentPane().add(lblNewLabel);

		JButton btnNewButton = new JButton("CSV Generator");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				CsvGenWin csvwin = new CsvGenWin();

				Thread csvWinThread = new Thread(csvwin);

				csvWinThread.start();
				frame.dispose();

			}
		});
		btnNewButton.setBounds(77, 90, 137, 25);
		frame.getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("CSV Seperator");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				CsvSepWin csvsepwin = new CsvSepWin();

				Thread csvWinThread = new Thread(csvsepwin);

				csvWinThread.start();
				frame.dispose();

			}
		});
		btnNewButton_1.setBounds(219, 90, 137, 25);
		frame.getContentPane().add(btnNewButton_1);
	}

}
