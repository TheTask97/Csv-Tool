package main;

import java.awt.Font;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.commons.io.FilenameUtils;

public class CsvSepWin implements Runnable {

	private JFrame frame;
	ButtonGroup group = new ButtonGroup();
	JTextArea textArea;
	String dir, filePath, fileName;
	File f = null;
	JTextArea textArea_1;
	private JTextField textField;
	private JTextField textField_1;
	double targetSize;
	CsvSep csvsep;

	/**
	 * Launch the application.
	 */

	public void run() {
		try {
			CsvSepWin window = new CsvSepWin();
			window.frame.setVisible(true);
			window.frame.setTitle("CSV Seperator");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the application.
	 */
	public CsvSepWin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 371);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton btnNewButton = new JButton("Split CSV");
		btnNewButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				try {

					targetSize = Double.parseDouble(textField.getText());

					if (targetSize > 999 || group.getSelection().getActionCommand().equals("gb") && targetSize > 50) {

						JOptionPane.showMessageDialog(((new Frame())), "Your Number is a bit to large...", "WARNING",
								JOptionPane.WARNING_MESSAGE);

					} else {
						System.out.println(group.getSelection().getActionCommand());

						if (f == null || dir == null) {

							JOptionPane.showMessageDialog(((new Frame())), "Your file or directory is wrong.",
									"WARNING", JOptionPane.WARNING_MESSAGE);

						} else {

							if (FilenameUtils.getExtension(f.getName()).equals("csv")) {

								csvsep = new CsvSep(filePath, dir, textField_1.getText(), targetSize,
										group.getSelection().getActionCommand());

								long time = csvsep.doIt();

								JOptionPane.showMessageDialog(((new Frame())),
										"Your Job is done and took " + time / 1000 + " seconds.", "Done",
										JOptionPane.INFORMATION_MESSAGE);

							}else {
								
								JOptionPane.showMessageDialog(((new Frame())),
										"Looks like you didnt selected a CSV-File.", "Wrong File",
										JOptionPane.WARNING_MESSAGE);
								
							}
						}

					}

				} catch (Exception exc) {

					JOptionPane.showMessageDialog(((new Frame())), "Please type a number.", "Error",
							JOptionPane.WARNING_MESSAGE);

				}

			}

		});
		btnNewButton.setBounds(154, 287, 97, 25);
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
					textArea_1.setText(dir);
				} else {
					if (dir == null) {
						textArea_1.setText("Please Select a dir");
					}
				}

			}
		});

		btnChoose.setBounds(283, 108, 98, 25);
		frame.getContentPane().add(btnChoose);

		JLabel lblChooseDirectory = new JLabel("Choose Directory");
		lblChooseDirectory.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblChooseDirectory.setBounds(152, 111, 137, 16);
		frame.getContentPane().add(lblChooseDirectory);

		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setBounds(152, 43, 229, 42);
		frame.getContentPane().add(textArea);

		JLabel lblChooseFile = new JLabel("Choose File");
		lblChooseFile.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblChooseFile.setBounds(152, 14, 137, 16);
		frame.getContentPane().add(lblChooseFile);

		JButton btnChoose_1 = new JButton("Choose");
		btnChoose_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				//
				// disable the "All files" option.
				//
				FileNameExtensionFilter filter = new FileNameExtensionFilter("*.csv", "csv");
				chooser.setFileFilter(filter);
				//
				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					f = chooser.getSelectedFile();
					textArea.setText(f.getName());
					filePath = f.getAbsolutePath();
				} else {
					if (f == null) {
						textArea.setText("Please Select a File");
					}
				}

			}
		});
		btnChoose_1.setBounds(286, 10, 98, 25);
		frame.getContentPane().add(btnChoose_1);

		textArea_1 = new JTextArea();
		textArea_1.setLineWrap(true);
		textArea_1.setEditable(false);
		textArea_1.setBounds(152, 138, 229, 47);
		frame.getContentPane().add(textArea_1);

		JLabel lblSplitSize = new JLabel("Split Size");
		lblSplitSize.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSplitSize.setBounds(12, 198, 70, 25);
		frame.getContentPane().add(lblSplitSize);

		textField = new JTextField();
		textField.setBounds(12, 227, 70, 22);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(152, 227, 127, 22);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);

		JLabel lblFilesToBe = new JLabel("Files to be named");
		lblFilesToBe.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFilesToBe.setBounds(152, 198, 229, 25);
		frame.getContentPane().add(lblFilesToBe);

	}

}
