import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;

public class SimpleFileSenderServer extends JFrame {

	private String file = "";
	private String fileType = "";

	public SimpleFileSenderServer() {
		super("File Sender Server");
		setSize(500, 100);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		Container c = getContentPane();
		c.setLayout(new FlowLayout());

		JButton openButton = new JButton("Open");
		JButton saveButton = new JButton("Save");
		JButton dirButton = new JButton("Pick Dir");
		JButton sendButton = new JButton("Send");
		JButton receiveButton = new JButton("Receive");
		JButton exitButton = new JButton("Exit");

		final JLabel statusbar = new JLabel(
				"Output of your selection will go here");

		sendButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				FileSenderServer fs = new FileSenderServer();
				try {
					fs.serverSide(file, fileType);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		});
		receiveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				/*
				 * FileSenderServer fs = new FileSenderServer(); try {
				 * fs.serverSide(file); } catch (IOException e) {
				 * e.printStackTrace(); }
				 */
			}

		});

		exitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}

		});

		// Create a file chooser that opens up as an Open dialog
		openButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent ae) {

				JFileChooser chooser = new JFileChooser();
				chooser.setMultiSelectionEnabled(true);
				int option = chooser.showOpenDialog(SimpleFileSenderServer.this);
				if (option == JFileChooser.APPROVE_OPTION) {
					File[] sf = chooser.getSelectedFiles();
					String filelist = "nothing";
					if (sf.length > 0)
						filelist = sf[0].getName();
					for (int i = 1; i < sf.length; i++) {
						filelist += ", " + sf[i].getName();
					}
					statusbar.setText("You chose " + filelist);
					file = chooser.getSelectedFile().getPath();
					fileType = chooser.getTypeDescription(sf[0]);
				} else {
					statusbar.setText("You canceled.");
				}
				chooser = null;
			}
		});

		// Create a file chooser that opens up as a Save dialog
		saveButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent ae) {

				JFileChooser chooser = new JFileChooser();
				int option = chooser.showSaveDialog(SimpleFileSenderServer.this);
				if (option == JFileChooser.APPROVE_OPTION) {
					statusbar.setText("You saved "
							+ ((chooser.getSelectedFile() != null) ? chooser
									.getSelectedFile().getName() : "nothing"));
				} else {
					statusbar.setText("You canceled.");
				}
			}
		});

		// Create a file chooser that allows you to pick a directory
		// rather than a file
		dirButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent ae) {
				JFileChooser chooser = new JFileChooser();
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int option = chooser.showOpenDialog(SimpleFileSenderServer.this);
				if (option == JFileChooser.APPROVE_OPTION) {
					statusbar.setText("You opened "
							+ ((chooser.getSelectedFile() != null) ? chooser
									.getSelectedFile().getName() : "nothing"));
				} else {
					statusbar.setText("You canceled.");
				}
			}
		});
		c.add(openButton);
		c.add(saveButton);
		c.add(dirButton);
		c.add(sendButton);
		c.add(exitButton);
		c.add(receiveButton);
		c.add(statusbar);
	}

	public String getFileList(String list) {
		return file = list;
	}

	public static void main(String[] args) throws IOException {
		
		SimpleFileSenderServer sfc = new SimpleFileSenderServer();
		sfc.setVisible(true);
	}
}
