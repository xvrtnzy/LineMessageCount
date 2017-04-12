import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
//import javax.swing.JProgressBar;

public class testView2 extends JFrame {

	private JPanel contentPane;
	private JTextArea textarea;
	//private JProgressBar progressbar;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	}

	/**
	 * Create the frame.
	 */
	public testView2() {
		setTitle("\u8A08\u7B97\u7D50\u679C");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 563, 414);
		contentPane = new JPanel();
		contentPane.setBorder ( new TitledBorder ( new EtchedBorder (), "統計結果" ) );
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		textarea = new JTextArea();
		textarea.setEditable(false);
		
		JScrollPane scrollPane = new JScrollPane(textarea);
		contentPane.add(scrollPane, BorderLayout.CENTER);		
		/*
		progressbar = new JProgressBar();
		progressbar.setStringPainted(true);
		progressbar.setBorderPainted(true);
		progressbar.setIndeterminate(true);
		contentPane.add(progressbar, BorderLayout.SOUTH);*/
			
		setVisible(true);
	}
	
	public JTextArea getTextArea(){
		return this.textarea;
	}
	
	//public JProgressBar getProgressBar(){
	//	return progressbar;
	//}
}
