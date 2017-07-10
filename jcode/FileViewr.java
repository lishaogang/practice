import java.awt.Container;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class FileViewr extends JFrame implements ActionListener{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	JTextArea input;
	JMenuItem open;						  //
	JMenuItem save;						  //
	JMenuItem exit;						  //
	JMenuItem help;						  //

	public FileViewr(){
		Container container=getContentPane();
		input=new JTextArea(12,40);
		input.setFont(new Font("宋体",Font.PLAIN,16));
		JScrollPane scrollPane=new JScrollPane(input);  //
		container.add(scrollPane);                 //
		JMenuBar mb=new JMenuBar();					//
		JMenu m1=new JMenu("File");
		JMenu m2=new JMenu("Edit");
		mb.add(m1);
		mb.add(m2);
		open=new JMenuItem("Open");
		m1.add(open);
		save=new JMenuItem("Save As");
		m1.add(save);
		exit=new JMenuItem("Exit");
		m2.add(exit);
		help=new JMenuItem("Help");
		m2.add(help);
		open.addActionListener(this);
		save.addActionListener(this);
		exit.addActionListener(this);
		help.addActionListener(this);
		setJMenuBar(mb);
		setSize(500,600);						//
		setLocation(450,100);					//
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public static void main(String[] args) {			//
		FileViewr fViewr=new FileViewr();
		fViewr.setTitle("文件浏览器");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==open){							//
			try{
				JFileChooser chooser=new JFileChooser();
				int returnVal=chooser.showOpenDialog(this);
				if(returnVal ==JFileChooser.APPROVE_OPTION){
					File f=chooser.getSelectedFile();		//
					int size=(int)f.length();				//
					FileReader file=new FileReader(f);
					char buf[]=new char[size];
					file.read(buf);						//
					input.setText(new String(buf));
					file.close();
				}
			}catch(IOException e1){

			}
		}
		if(e.getSource()==save){//
			try {
				JFileChooser chooser=new JFileChooser();
				int returnVal=chooser.showSaveDialog(this);
				if(returnVal==JFileChooser.APPROVE_OPTION){
					File f=chooser.getSelectedFile();
					FileWriter file=new FileWriter(f);
					file.write(input.getText());		//
					file.close();
				}
			} catch (Exception e1) {

			}
		}
		if(e.getSource()==exit){			//
			try {
				addWindowListener(new closeWin());
				if(e.getActionCommand().equals("Exit")){
					dispose();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		if(e.getSource()==help){				//
			try {
					File f=new File(File.separator+"workspace"+File.separator+"JavaAplication"+File.separator+"src"+File.separator+"tet");
					FileReader file=new FileReader(f);
					int size=(int)f.length();
					char buf[]=new char[size];
					file.read(buf);
					//System.out.println(buf);
					input.setText(new String(buf));
					file.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
	class closeWin extends WindowAdapter{
		public void windowClosing(WindowEvent e) {
			Window w=e.getWindow();
			w.dispose();
		}
	}
}
