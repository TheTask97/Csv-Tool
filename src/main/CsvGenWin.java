package main;

import java.awt.Font;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CsvGenWin implements Runnable {

	private JFrame frame;
	private JTextField textField;
	JTextArea textArea;
	String dir = null;
	double targetSize;
	ButtonGroup group = new ButtonGroup();
	CsvGen csvgen;

	/**
	 * Launch the application.
	 */
	public void run() {

		try {
			CsvGenWin window = new CsvGenWin();
			window.frame.setVisible(true);
			window.frame.setTitle("CSV Generator");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the application.
	 */
	public CsvGenWin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton btnNewButton = new JButton("Generate");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				try {

					targetSize = Double.parseDouble(textField.getText());

					if (targetSize > 999 || group.getSelection().getActionCommand().equals("gb") && targetSize > 50) {

						JOptionPane.showMessageDialog(((new Frame())), "Your Number is a bit to large...", "WARNING",
								JOptionPane.WARNING_MESSAGE);

					} else {

						csvgen = new CsvGen(targetSize, group.getSelection().getActionCommand(), dir);
						
						long time = csvgen.doIt();
						
						JOptionPane.showMessageDialog(((new Frame())), "Your Job is done and took " + time/1000 + " seconds.", "Done",
								JOptionPane.INFORMATION_MESSAGE);

					}

				} catch (Exception exc) {

					JOptionPane.showMessageDialog(((new Frame())), "Please type a number.", "Error",
							JOptionPane.WARNING_MESSAGE);

				}

			}
		});
		btnNewButton.setBounds(152, 198, 97, 25);
		frame.getContentPane().add(btnNewButton);

		JRadioButton rdbtnKilobyte = new JRadioButton("Kilobyte");
		rdbtnKilobyte.setActionCommand("kb");
		group.add(rdbtnKilobyte);
		rdbtnKilobyte.setBounds(8, 78, 127, 25);
		frame.getContentPane().add(rdbtnKilobyte);

		JRadioButton rdbtnMegabyte = new JRadioButton("Megabyte");
		rdbtnMegabyte.setActionCommand("mb");
		group.add(rdbtnMegabyte);
		rdbtnMegabyte.setBounds(8, 108, 127, 25);
		frame.getContentPane().add(rdbtnMegabyte);

		JRadioButton rdbtnGigabyte = new JRadioButton("Gigabyte");
		rdbtnGigabyte.setActionCommand("gb");
		group.add(rdbtnGigabyte);
		rdbtnGigabyte.setBounds(8, 138, 127, 25);
		frame.getContentPane().add(rdbtnGigabyte);

		JRadioButton rdbtnByte = new JRadioButton("Byte");
		rdbtnByte.setActionCommand("byte");
		rdbtnByte.setSelected(true);
		group.add(rdbtnByte);
		rdbtnByte.setBounds(8, 48, 127, 25);
		frame.getContentPane().add(rdbtnByte);

		JLabel lblNewLabel = new JLabel("Choose Unit");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(12, 13, 86, 16);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblEnterFilesize = new JLabel("Enter Filesize");
		lblEnterFilesize.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEnterFilesize.setBounds(152, 14, 97, 16);
		frame.getContentPane().add(lblEnterFilesize);

		textField = new JTextField();
		textField.setBounds(287, 11, 97, 22);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JButton btnChoose = new JButton("Choose");
		btnChoose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				//
				// disable the "All files" option.
				//
				chooser.setAcceptAllFileFilterUsed(false);
				//
				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					dir = chooser.getSelectedFile().getAbsolutePath();
					textArea.setText(dir);
				} else {
					if (dir == null) {
						textArea.setText("Please Select a dir");
					}
				}

			}
		});

		btnChoose.setBounds(286, 48, 98, 25);
		frame.getContentPane().add(btnChoose);

		JLabel lblChooseDirectory = new JLabel("Choose Directory");
		lblChooseDirectory.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblChooseDirectory.setBounds(152, 51, 137, 16);
		frame.getContentPane().add(lblChooseDirectory);

		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setBounds(152, 92, 229, 77);
		frame.getContentPane().add(textArea);
	}
}
