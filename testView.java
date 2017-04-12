import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class testView extends JFrame {

	private JPanel contentPane;
	private JTextField text_path;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					testController controller = new testController();
					testView frame = new testView(controller);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public testView(testController controller) {

		String laf = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
		try { 
		   UIManager.setLookAndFeel(laf);   
		}  
		catch(Exception e) { 
		  e.printStackTrace();
		} 
		
		setTitle("Line\u8A0A\u606F\u8A08\u7B97");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);  //將視窗置於螢幕正中央
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		class Myclosehandler extends WindowAdapter{
			private Component parentcomponent;
			public Myclosehandler(Component c){
				this.parentcomponent = c;
			}
			public void windowClosing(WindowEvent e){
				int op = JOptionPane.showConfirmDialog(parentcomponent, new String("要離開了嗎?"), "確認訊息", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if(op == JOptionPane.YES_OPTION){
					System.exit(0);
				}
			}
		}
		addWindowListener(new Myclosehandler(this));
		setBounds(100, 100, 450, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane_path = new JScrollPane();
		scrollPane_path.setBounds(23, 38, 290, 32);
		contentPane.add(scrollPane_path);
		
		text_path = new JTextField();
		scrollPane_path.setViewportView(text_path);
		text_path.setEditable(false);
		text_path.setBackground(Color.WHITE);
		text_path.setColumns(10);
		
		JButton btn_select = new JButton("\u9078\u64C7\u6A94\u6848");
		btn_select.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfile = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("純文字文件", "txt");
				jfile.setFileFilter(filter);
				int returnValue = jfile.showOpenDialog(null);
				if(returnValue == JFileChooser.APPROVE_OPTION ){
					File getfile = jfile.getSelectedFile();	
					text_path.setText(getfile.getPath());
					controller.acceptFile(getfile);
				}
			}
		});
		btn_select.setBounds(323, 37, 87, 32);
		contentPane.add(btn_select);
		
		JButton btn_start = new JButton("\u958B\u59CB\u8A08\u7B97");
		class Starteventhandler implements ActionListener{
			private Component parentcomponent;
			public Starteventhandler(Component c){
				this.parentcomponent = c;
			}
			public void actionPerformed(ActionEvent e) {
				if(controller.isgetFile()){
					controller.count();
				} else {
					JOptionPane.showMessageDialog(parentcomponent,"請先選擇要計算的檔案!!","尚未選黨", JOptionPane.WARNING_MESSAGE);
				}
			}
		}
		btn_start.addActionListener(new Starteventhandler(this));
		btn_start.setBounds(174, 100, 90, 32);
		contentPane.add(btn_start);
		
		
	}
}
