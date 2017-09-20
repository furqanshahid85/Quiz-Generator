import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.Container;

import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JScrollBar;
import javax.swing.JLayeredPane;
import javax.swing.ScrollPaneConstants;

@SuppressWarnings("all")

public class addQuizQuestions extends JFrame implements java.io.Serializable {

	// this is a new comment
	private static JPanel contentPane;
	private static int c=1;
	private static int x=90;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField txtTexxxt;
	public static ArrayList<questionClass> array=new ArrayList<questionClass>();
	public JRadioButton rdbtnMcqs;
	public JRadioButton rdbtnTruefalse;
	public JRadioButton rdbtnBlanks;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					addQuizQuestions frame = new addQuizQuestions();
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
	public addQuizQuestions() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 714, 497);
		contentPane = new JPanel();
		contentPane.setPreferredSize(new Dimension(450, 450));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setPreferredSize(new Dimension(400, 1200));
		scrollPane.setBounds(10, 11, 678, 422);
		contentPane.add(scrollPane);
		
		final JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(300, 1100));
		scrollPane.setViewportView(panel);
		panel.setLayout(null);
		
		JLabel lblClickTheAppropriate = new JLabel("Select the appropriate button to add a question:");
		lblClickTheAppropriate.setBounds(21, 0, 412, 23);
		panel.add(lblClickTheAppropriate);
		lblClickTheAppropriate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		final JRadioButton rdbtnMcqs = new JRadioButton("Mcqs");
		
		ButtonGroup group=new ButtonGroup();
		rdbtnMcqs.setBounds(20, 30, 70, 23);
		panel.add(rdbtnMcqs);
		group.add(rdbtnMcqs);
		
		rdbtnTruefalse = new JRadioButton("True/False");
		rdbtnTruefalse.setBounds(150, 30, 109, 23);
		panel.add(rdbtnTruefalse);
		group.add(rdbtnTruefalse);
		
		rdbtnBlanks = new JRadioButton("Fill in the blanks");
		rdbtnBlanks.setBounds(280, 30, 115, 23);
		panel.add(rdbtnBlanks);
		group.add(rdbtnBlanks);
		
		JButton btnAddQuestion = new JButton("Add Question");
		btnAddQuestion.setBackground(Color.ORANGE);
		btnAddQuestion.setBounds(420, 30, 110, 23);
		panel.add(btnAddQuestion);	
		
	
		
		btnAddQuestion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// checks which button is selected 
				final boolean rdbm=rdbtnMcqs.isSelected();
				final boolean rdbtf=rdbtnTruefalse.isSelected();
				final boolean rdbbl=rdbtnBlanks.isSelected();
				
				if(rdbm) {
					
					createMcqs(panel);
					revalidate();
					repaint();
			
				}else if(rdbtf) {	
				
					createTrueFalse(panel);
					revalidate();
					repaint();
					
				}else if (rdbbl) {
					
					createBlanks(panel);
					revalidate();
					repaint();
				
				}
			}
		});
		
		// this saveQuiz btn saves the entire quiz to an external file
		// quiz.txt which is then read to display the questions to the students
		
		JButton btnSaveQuiz = new JButton("Save Quiz");
		btnSaveQuiz.setBackground(Color.GREEN);
		btnSaveQuiz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dataSerialization(array);
				dispose();
			}
		});
		btnSaveQuiz.setBounds(540, 30, 100, 23);
		panel.add(btnSaveQuiz);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(21, 60, 640, 2);
		panel.add(separator);
	}

public static void dataSerialization(ArrayList<questionClass> Array) {

		try {
			//deserailization of quizCredentials to get the quiz tiltle
			//System.out.println(quizTitle);
			FileInputStream f1=new FileInputStream("quizCredentials.txt");
			ObjectInputStream oin=new ObjectInputStream(f1);
			quizCredentials obj1=(quizCredentials)oin.readObject();
			oin.close();
			f1.close();
			
			//serialization of the entered data
			
			FileOutputStream f=new FileOutputStream("quiz.txt",true);
			ObjectOutputStream obf=new ObjectOutputStream(f);
			
			// the quiz details are added into an arraylist of type questionClass
			//the arraylist is then serialized to the external file
			
			obf.writeObject(Array);
			obf.close();
			f.close();
		}catch(Exception error) {
			error.printStackTrace();
		}
	}
	
	// this method creates the mcqs dynamically
	
	public static void createMcqs(JPanel panel) {
		
		// we create label to show the question index no. and other text
		// text fields to take input for questions,mcqs and expected answers

		final JTextField qField = new JTextField("enter your question here");
		qField.setFont(new Font("Tahoma", Font.ITALIC, 11));
		qField.setBounds(50,c*x , 400, 20);
		panel.add(qField);	 
		
		JLabel mlbl=new JLabel("Q"+c+". ");
		
		mlbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		mlbl.setBounds(20,c*x , 236, 20);
		panel.add(mlbl);
		
		JLabel explbl=new JLabel("exected answer: ");
		explbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		explbl.setBounds(20,60+c*x , 236, 20);
		panel.add(explbl);
		 
		
		final JTextField expField = new JTextField("write expected ans here");
		expField.setFont(new Font("Tahoma", Font.ITALIC, 11));
		expField.setBounds(130,62+c*x , 250, 20);
		panel.add(expField);
		
		ButtonGroup groupOpt= new ButtonGroup();
		JRadioButton rdbtnoptA = new JRadioButton("A. ");
		JRadioButton rdbtnoptB = new JRadioButton("B. ");
		JRadioButton rdbtnoptC = new JRadioButton("C. ");
		JRadioButton rdbtnoptD = new JRadioButton("D. ");
		groupOpt.add(rdbtnoptA);
		rdbtnoptA.setBounds(40, 20+c*x, 40, 23);
		panel.add(rdbtnoptA);
		
		final JTextField optFieldA = new JTextField();
		optFieldA.setBounds(80,22+c*x , 90, 20);
		panel.add(optFieldA);
		 
		
		groupOpt.add(rdbtnoptB);
		rdbtnoptB.setBounds(40, 40+c*x, 40, 23);
		panel.add(rdbtnoptB);
		
		final JTextField optFieldB = new JTextField("");
		optFieldB.setBounds(80,42+c*x , 90, 20);
		panel.add(optFieldB);
		
		groupOpt.add(rdbtnoptC);
		rdbtnoptC.setBounds(180, 20+c*x, 40, 23);
		panel.add(rdbtnoptC);
		
		final JTextField optFieldC = new JTextField("");
		optFieldC.setBounds(220,22+c*x , 90, 20);
		panel.add(optFieldC);
		
		groupOpt.add(rdbtnoptD);
		rdbtnoptD.setBounds(180, 40+c*x, 40, 23);
		panel.add(rdbtnoptD);
		
		final JTextField optFieldD = new JTextField("");
		optFieldD.setBounds(220,42+c*x , 90, 20);
		panel.add(optFieldD);
		
		//save btn below saves the input recieved to an object of
		//questionClass. this object is added to an arraylist
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(500, 40+c*x, 137, 23);
		panel.add(btnSave);
		
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				questionClass obj=new questionClass();
				obj.qtype=1;
				obj.question=qField.getText();
				obj.opA=optFieldA.getText();
				obj.opB=optFieldB.getText();
				obj.opC=optFieldC.getText();
				obj.opD=optFieldD.getText();
				obj.expAns=expField.getText();
					array.add(obj);
			}
		});
		
		c++;	
	}
	
	//this method creates the true false questions
	
	public static void createTrueFalse(JPanel panel) {
		
		//JTextfields take the question inputs
		//JLabels are used to display text
				
		final JTextField qField = new JTextField("enter your question here");
		qField.setFont(new Font("Tahoma", Font.ITALIC, 11));
		qField.setBounds(50,c*x , 400, 20);
		panel.add(qField);

		JLabel mlbl=new JLabel("Q"+c+". ");
		mlbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		mlbl.setBounds(20,c*x , 236, 20);
		panel.add(mlbl);
		//.add(mlbl);
		
		JLabel explbl=new JLabel("exected answer: ");
		explbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		explbl.setBounds(20,60+c*x , 236, 20);
		panel.add(explbl);
		 
		
		final JTextField expField = new JTextField("write the expected ans here");
		expField.setFont(new Font("Tahoma", Font.ITALIC, 11));
		expField.setBounds(130,62+c*x , 250, 20);
		panel.add(expField);
		//.add(expField);
		
		ButtonGroup groupOpt= new ButtonGroup();
		JRadioButton rdbtnoptA = new JRadioButton("A. ");
		JRadioButton rdbtnoptB = new JRadioButton("B. ");
		
		groupOpt.add(rdbtnoptA);
		rdbtnoptA.setBounds(40, 20+c*x, 40, 23);
		panel.add(rdbtnoptA);
		
		final JTextField optFieldA = new JTextField();
		optFieldA.setBounds(80,22+c*x , 90, 20);
		panel.add(optFieldA);
		
		groupOpt.add(rdbtnoptB);
		rdbtnoptB.setBounds(40, 40+c*x, 40, 23);
		panel.add(rdbtnoptB);
		
		final JTextField optFieldB = new JTextField();
		optFieldB.setBounds(80,42+c*x , 90, 20);
		panel.add(optFieldB);
		
		//save btn below saves the entered question, option, expected answer
		//the contents are added to an arraylist as mentioned before
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(500, 35+c*x, 137, 23);
		panel.add(btnSave);
		
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				questionClass obj=new questionClass();
				obj.qtype=2;
				obj.question=qField.getText();
				obj.opA=optFieldA.getText();
				obj.opB=optFieldB.getText();
				obj.expAns=expField.getText();
					array.add(obj);
			}
		});
		c++;
	}
	
	//this method creates the fill in the blanks questions
	
	public static void createBlanks(JPanel panel) {
		
		//JTextfields take the question inputs
		//JLabels are used to display text
		
		JLabel mlbl=new JLabel("Q"+c+". ");
		mlbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		mlbl.setBounds(20,c*x , 236, 20);
		panel.add(mlbl);
		
		final JTextField qField = new JTextField("enter your question here");
		qField.setFont(new Font("Tahoma", Font.ITALIC, 11));
		qField.setBounds(50,c*x , 400, 20);
		panel.add(qField);
		 
		
		JLabel explbl=new JLabel("exected answer: ");
		explbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		explbl.setBounds(20,22+c*x , 236, 20);
		panel.add(explbl);
		 
		
		final JTextField expField = new JTextField("write the expected ans here");
		expField.setFont(new Font("Tahoma", Font.ITALIC, 11));
		expField.setBounds(130,22+c*x , 250, 20);
		panel.add(expField);
		
		//save btn below saves the entered question, option, expected answer
		//the contents are added to an arraylist as mentioned before	
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(500, 20+c*x, 137, 23);
		panel.add(btnSave);
		
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				questionClass obj=new questionClass();
				obj.qtype=3;
				obj.question=qField.getText();
				obj.expAns=expField.getText();
					array.add(obj);
			}
		});
		c++;
	}
}

// the class below has the attributes for all type of question
// a new object of this created everytime we make a new question

@SuppressWarnings("all")
class questionClass implements java.io.Serializable{
	public int qtype;
	public String question=null;
	public String opA=null;
	public String opB=null;
	public String opC=null;
	public String opD=null;
	public String expAns=null;
	
	public void setValues(String que,String opa,String opb, String opc,String opd,String eans){

		question=que;
		opA=opa;
		opB=opb;
		opC=opc;
		opD=opd;
		expAns=eans;	
	}
}
