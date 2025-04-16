package demo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.*;
import javax.swing.undo.*;
import javax.swing.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.print.PrinterException;
import java.util.logging.*;
import javax.swing.UIManager;
import java.util.*;
import java.text.SimpleDateFormat;


public class NotepadApp extends JFrame implements ActionListener
{
	//Declare components used
	JFrame ab;
	JLabel l1;
	JMenuBar menubar=new JMenuBar();
	UndoManager um = new UndoManager();
	
	//Main MenuBar elements 
	JMenu file=new JMenu("File");
	JMenu edit=new JMenu("Edit");
	JMenu format=new JMenu("Format");
	JMenu font= new JMenu("Font");
	JMenu fontstyle= new JMenu("Font Style");
	JMenu fontsize= new JMenu("Font Size");
	JMenu fontcolor= new JMenu("Font Color");
	JMenu view=new JMenu("View");
	JMenu zoom= new JMenu("Zoom");
	JMenu help=new JMenu("Help");
	
	//Menu items of New menu
	JMenuItem newFile=new JMenuItem("New");
	JMenuItem openFile=new JMenuItem("Open...");
	JMenuItem saveFile=new JMenuItem("Save");
	JMenuItem printFile=new JMenuItem("Print");
	JMenuItem exitFile=new JMenuItem("Exit");
	
	//Menu items of Edit menu
	JMenuItem undoEdit=new JMenuItem("Undo");
	JMenuItem redoEdit=new JMenuItem("Redo");
	JMenuItem cutEdit=new JMenuItem("Cut");
	JMenuItem copyEdit=new JMenuItem("Copy");
	JMenuItem pasteEdit=new JMenuItem("Paste");
	JMenuItem deleteEdit=new JMenuItem("Delete");
	JMenuItem selectAllEdit=new JMenuItem("Select All");
	JMenuItem timeDateEdit=new JMenuItem("Time/Date");
	
	//Menu items of Format menu
	JMenuItem WrapFormat=new JMenuItem("Word Wrap: Off");
	JMenuItem FontArial=new JMenuItem("Arial");
	JMenuItem FontCSMS=new JMenuItem("Comic Sans Ms");
	JMenuItem FontTNR=new JMenuItem("Times New Roman");
	JMenuItem Fontserif=new JMenuItem("Serif");
	JMenuItem FontSize8=new JMenuItem("8");
	JMenuItem FontSize12=new JMenuItem("12");
	JMenuItem FontSize16=new JMenuItem("16");
	JMenuItem FontSize24=new JMenuItem("24");
	JMenuItem FontSize28=new JMenuItem("28");
	JMenuItem Background= new JMenuItem("Background color");
	JMenuItem Foreground= new JMenuItem("Foreground color");
    
	//Menu items of View menu and Sub menu zoom 
	JMenuItem inZoom=new JMenuItem("Zoom In");
	JMenuItem outZoom=new JMenuItem("Zoom Out");
	JMenuItem defaultZoom=new JMenuItem("Restore Default Zoom");
	
	//Menu items of Help menu
	JMenuItem aboutHelp=new JMenuItem("About Notepad");
	
	//create text area
	JTextArea textArea=new JTextArea();

	//Constructor
	NotepadApp()
	{
		// Design the main frame of application
		setTitle("Untitled-Java TextEditor Application");
		setBounds(100,100,800,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Dell\\Desktop\\SEM 6\\javaall\\JavaMicro\\logo.jfif"); 
		setIconImage(icon);
		setVisible(true);

		
	     //add the menu items to file menu 
		//add action listener to menuitems
		//add shortcut keys
		file.add(newFile);
		newFile.addActionListener(this);
		newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,KeyEvent.CTRL_DOWN_MASK));
		
		file.add(openFile);
		openFile.addActionListener(this);
		openFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,KeyEvent.CTRL_DOWN_MASK));
		
		file.add(saveFile);
		saveFile.addActionListener(this);
		saveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,KeyEvent.CTRL_DOWN_MASK));
		
		file.add(printFile);
		printFile.addActionListener(this);
		printFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,KeyEvent.CTRL_DOWN_MASK));
		
		file.add(exitFile);
		exitFile.addActionListener(this);
		
		 //add the menu items to edit menu 
		//add action listener to menu items
		//add shortcut keys
		edit.add(undoEdit);
		undoEdit.addActionListener(this);
		undoEdit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,KeyEvent.CTRL_DOWN_MASK));
		
		edit.add(redoEdit);
		redoEdit.addActionListener(this);
		undoEdit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,KeyEvent.CTRL_DOWN_MASK));
		
		edit.add(cutEdit);
		cutEdit.addActionListener(this);
		cutEdit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,KeyEvent.CTRL_DOWN_MASK));
		
		edit.add(copyEdit);
		copyEdit.addActionListener(this);
		copyEdit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,KeyEvent.CTRL_DOWN_MASK));
		
		edit.add(pasteEdit);
		pasteEdit.addActionListener(this);
		pasteEdit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,KeyEvent.CTRL_DOWN_MASK));
		
		edit.add(deleteEdit);
		deleteEdit.addActionListener(this);
		
		edit.add(selectAllEdit);
		selectAllEdit.addActionListener(this);
		selectAllEdit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,KeyEvent.CTRL_DOWN_MASK));
		
		edit.add(timeDateEdit);
		timeDateEdit.addActionListener(this);
		
		 //add the menu items to format menu 
		//add action listener to menu items
		//add shortcut keys
		format.add(WrapFormat);
		WrapFormat.addActionListener(this);
		
		//add sub menu font to format menu
		format.add(font);
		
		 //add the menu items to font sub menu 
		font.add(fontstyle);
		font.add(fontsize);
		font.add(fontcolor);
		
		//add the menu items to font style menu 
		//add action listener to menu items
		fontstyle.add(FontArial);
		FontArial.addActionListener(this);
		fontstyle.add(FontCSMS);
		FontCSMS.addActionListener(this);
		fontstyle.add(FontTNR);
		FontTNR.addActionListener(this);
		fontstyle.add(Fontserif);
		Fontserif.addActionListener(this);
		
		//add the menu items to font size menu 
		//add action listener to menu items
		fontsize.add(FontSize8);
		FontSize8.addActionListener(this);
		fontsize.add(FontSize12);
		FontSize12.addActionListener(this);
		fontsize.add(FontSize16);
		FontSize16.addActionListener(this);
		fontsize.add(FontSize24);
		FontSize24.addActionListener(this);
		fontsize.add(FontSize28);
		FontSize28.addActionListener(this);
		
		//add the menu items to font color menu 
		//add action listener to menu items
		fontcolor.add(Background);
		Background.addActionListener(this);
		fontcolor.add(Foreground);
		Foreground.addActionListener(this);
		
		//add sub menu zoom to view  menu
		view.add(zoom);
		
		//add the menu items to zoom sub menu 
		//add action listener to menu items
		zoom.add(inZoom);
		inZoom.addActionListener(this);
		zoom.add(outZoom);
		outZoom.addActionListener(this);
		zoom.add(defaultZoom);
		defaultZoom.addActionListener(this);
		
		//add the menu items to help menu 
		//add action listener to menu items
		help.add(aboutHelp);
		aboutHelp.addActionListener(this);
		
		// add menu to menu bar
		menubar.add(file);
		menubar.add(edit);
		menubar.add(format);
		menubar.add(view);
		menubar.add(help);
		
		setJMenuBar(menubar);
		
		//Add scrollbar to text area and set policy
		JScrollPane scrollpane = new JScrollPane(textArea);
		add(scrollpane);
		scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollpane.setBorder(BorderFactory.createEmptyBorder());
		//set initial font properties
		textArea.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		//set undo property to text area
		textArea.getDocument().addUndoableEditListener(
				new UndoableEditListener() {
					public void undoableEditHappened(UndoableEditEvent e) {
						um.addEdit(e.getEdit());
					}
				});
		
		//sub frame of application to display about section in help menu
		ab = new JFrame("About Notepad");
		ab.setSize(500,500);
		ab.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    l1 = new JLabel();
	    l1.setIcon(new ImageIcon("C:\\Users\\Dell\\Desktop\\SEM 6\\javaall\\JavaMicro\\ss.png"));
	    l1.setBounds(50,30,100,100);
	    ab.add(l1);
	}
	
	//Method invoked by listener after action is performed by the source
	public void actionPerformed(ActionEvent e)
	{
		//File menu items action implementation details
		if(e.getActionCommand().equalsIgnoreCase("New"))
		{
			textArea.setText(null);
			setTitle("Untitled-Java TextEditor Application");
		}
		else if(e.getActionCommand().equalsIgnoreCase("Open..."))
		{
			JFileChooser fileChooser=new JFileChooser();
			FileNameExtensionFilter textFilter=new FileNameExtensionFilter("Text Documents(*.txt)","txt");
			fileChooser.setAcceptAllFileFilterUsed(false);
			fileChooser.addChoosableFileFilter(textFilter);
			int action = fileChooser.showOpenDialog(null);
			if(action!=JFileChooser.APPROVE_OPTION)
			{
				return;
			}
			else
			{
				try
				{
					BufferedReader reader = new BufferedReader(new FileReader(fileChooser.getSelectedFile()));
					textArea.read(reader,null);
					String fileNme = fileChooser.getSelectedFile().toString();
					setTitle(fileNme);
				}
				catch(IOException ex)
				{
					ex.printStackTrace();
				}
			}
		}
		else if(e.getActionCommand().equalsIgnoreCase("Save"))
		{
			JFileChooser fileChooser=new JFileChooser();
			FileNameExtensionFilter textFilter=new FileNameExtensionFilter("Text Documents(*.txt)","txt");
			fileChooser.setAcceptAllFileFilterUsed(false);
			fileChooser.addChoosableFileFilter(textFilter);
			int action = fileChooser.showSaveDialog(null);
			if(action!=JFileChooser.APPROVE_OPTION)
			{
				return;
			}
			else
			{
				String fileName=fileChooser.getSelectedFile().getAbsolutePath().toString();
				if(!fileName.contains(".txt"))
					fileName=fileName+"txt";
				try
				{
					BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
					textArea.write(writer);
				}
				catch(IOException ex)
				{
					ex.printStackTrace();
				}
			}
		}
		
		else if(e.getActionCommand().equalsIgnoreCase("Print"))
		{
			try
			{
				textArea.print();
				
			}
			catch(PrinterException ex)
			{
				Logger.getLogger(NotepadApp.class.getName()).log(Level.SEVERE,null,ex);
			}
		}
		else if(e.getActionCommand().equalsIgnoreCase("exit"))
		{
			System.exit(0);
		}
		
		//Edit menu items action implementation details
		else if(e.getActionCommand().equalsIgnoreCase("Undo"))
		{
			um.undo();
		}
		else if(e.getActionCommand().equalsIgnoreCase("Redo"))
		{
			
			um.redo();
		}
		else if(e.getActionCommand().equalsIgnoreCase("Cut"))
		{
			textArea.cut();
		}
		else if(e.getActionCommand().equalsIgnoreCase("Copy"))
		{
			textArea.copy();
		}
		else if(e.getActionCommand().equalsIgnoreCase("Paste"))
		{
			textArea.paste();
		}
		else if(e.getActionCommand().equalsIgnoreCase("Delete"))
		{
			
			String s = textArea.getSelectedText();
			String s1 = s.replace(s,"");
			textArea.setText(s1);
		}
		else if(e.getActionCommand().equalsIgnoreCase("select All"))
		{
			textArea.selectAll();
		}
		else if(e.getActionCommand().equalsIgnoreCase("Time/Date"))
		{
			SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date date = new Date();
			String t1 = textArea.getText();
			String t2 = formatter.format(date);
			String t3 = t1+t2;
			textArea.setText(t3);
		}
		
		//format menu items action implementation details
		else if(e.getSource()==WrapFormat)
		{  
			if(e.getActionCommand().equalsIgnoreCase("Word Wrap: On")) 
			{
				textArea.setLineWrap(true);
				textArea.setWrapStyleWord(true); 
				WrapFormat.setText("Word Wrap: On");
			}
			if(e.getActionCommand().equalsIgnoreCase("Word Wrap: Off")) 
			{
				textArea.setLineWrap(false);
				textArea.setWrapStyleWord(false); 
				WrapFormat.setText("Word Wrap: On");
			}
		}
		else if(e.getActionCommand().equalsIgnoreCase("Arial"))
		{
			Font gf = textArea.getFont();
			int size = gf.getSize();
			Font fs = new Font("Arial",Font.PLAIN,size);
			textArea.setFont(fs);
		}
		else if(e.getActionCommand().equalsIgnoreCase("Comic Sans Ms"))
		{
			Font gf = textArea.getFont();
			int size = gf.getSize();
			Font fs = new Font("Comic Sans Ms",Font.PLAIN,size);
			textArea.setFont(fs);
		}
		else if(e.getActionCommand().equalsIgnoreCase("Times New Roman"))
		{
			Font gf = textArea.getFont();
			int size = gf.getSize();
			Font fs = new Font("Times New Roman",Font.PLAIN,size);
			textArea.setFont(fs);
		}
		else if(e.getActionCommand().equalsIgnoreCase("Serif"))
		{
			Font gf = textArea.getFont();
			int size = gf.getSize();
			Font fs = new Font("Serif",Font.PLAIN,size);
			textArea.setFont(fs);
		}
		else if(e.getActionCommand().equalsIgnoreCase("8"))
		{
			Font gf = textArea.getFont();
			String family = gf.getFamily();
			Font fs = new Font(family,Font.PLAIN,8);
			textArea.setFont(fs);
		}
		else if(e.getActionCommand().equalsIgnoreCase("12"))
		{
			Font gf = textArea.getFont();
			String family = gf.getFamily();
			Font fs = new Font(family,Font.PLAIN,12);
			textArea.setFont(fs);
		}
		else if(e.getActionCommand().equalsIgnoreCase("16"))
		{
			Font gf = textArea.getFont();
			String family = gf.getFamily();
			Font fs = new Font(family,Font.PLAIN,16);
			textArea.setFont(fs);
		}
		else if(e.getActionCommand().equalsIgnoreCase("24"))
		{
			Font gf = textArea.getFont();
			String family = gf.getFamily();
			Font fs = new Font(family,Font.PLAIN,24);
			textArea.setFont(fs);
		}
		else if(e.getActionCommand().equalsIgnoreCase("28"))
		{
			Font gf = textArea.getFont();
			String family = gf.getFamily();
			Font fs = new Font(family,Font.PLAIN,28);
			textArea.setFont(fs);
		}
		else if(e.getActionCommand().equalsIgnoreCase("Background color"))
		{
			JColorChooser color = new JColorChooser();
			Color newcolor = color.showDialog(textArea, "Select Background Colore",Color.white);
			textArea.setBackground(newcolor);
		}
		else if(e.getActionCommand().equalsIgnoreCase("Foreground color"))
		{
			JColorChooser color = new JColorChooser();
			Color newcolor = color.showDialog(textArea, "Select Background Colore",Color.black);
			textArea.setForeground(newcolor);
		}
		
		//View menu items action implementation details
		else if(e.getActionCommand().equalsIgnoreCase("Zoom In"))
		{
			Font gf = textArea.getFont();
			int size = gf.getSize();
			String family = gf.getFamily();
			int newSize = size+10;
			Font fs = new Font(family,Font.PLAIN,newSize);
			textArea.setFont(fs);
			
		}
		else if(e.getActionCommand().equalsIgnoreCase("Zoom Out"))
		{
			Font gf = textArea.getFont();
			int size = gf.getSize();
			String family = gf.getFamily();
			int newSize = size-10;
			Font fs = new Font(family,Font.PLAIN,newSize);
			textArea.setFont(fs);
		}
		else if(e.getActionCommand().equalsIgnoreCase("Restore Default Zoom"))
		{
			Font gf = textArea.getFont();
			String family = gf.getFamily();
			Font fs = new Font(family,Font.PLAIN,20);
			textArea.setFont(fs);
		}
		
		//Help menu items action implementation details
		else if(e.getActionCommand().equalsIgnoreCase("About Notepad"))
		{
			ab.setVisible(true);
		}
	}
	
	public static void main(String[] args) throws Exception
	{
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new NotepadApp();
	}

}
