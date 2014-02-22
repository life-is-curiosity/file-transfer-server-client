import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;

public class SimpleFileSenderClient extends JFrame {

	private String file = "";
	private String name = "";

	public SimpleFileSenderClient() {
		super("File Sender Client");
		setSize(500, 100);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		Container c = getContentPane();
		c.setLayout(new FlowLayout());

		JButton receiveButton = new JButton("Receive");
		JButton exitButton = new JButton("Exit");

		final JLabel statusbar = new JLabel(
				"Output of your selection will go here");

		receiveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				FileSenderClient fc = new FileSenderClient();
				try {
					fc.client();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}

		});

		exitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}

		});
		
		c.add(receiveButton);
		c.add(statusbar);
		c.add(exitButton);
		
	}

	public String getFileList(String list) {
		return file = list;
	}

	public static void main(String[] args) throws IOException {

		SimpleFileSenderClient sfc = new SimpleFileSenderClient();
		sfc.setVisible(true);
	}
}
