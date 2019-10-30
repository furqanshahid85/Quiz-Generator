import java.awt.BorderLayout;
import java.awt.EventQueue;

import java.nio.file.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.awt.event.ActionEvent;




@SuppressWarnings("all")

public class CreateQuiz extends JFrame implements java.io.Serializable {

	private JPanel contentPane;
	public static JTextField titleField;
	private JLabel lblDescription;
	private JButton btnAddQuestion;
	public JLabel label;
	private JLabel lblTotalMarks;
	public JTextField quizMarksField;
	private JLabel lblTimeAllowed;
	public JTextField quizTimeField;
	public JTextArea descriptionField;
	static int c=1;
	/**
	 * Launch the application.
	 */
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateQuiz frame = new CreateQuiz();
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
	
	//main quiz window is created here
	
	public CreateQuiz() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 717, 468);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//label for quiz title
		
		JLabel lbltitle = new JLabel("Quiz Title");
		lbltitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbltitle.setBounds(10, 24, 110, 14);
		contentPane.add(lbltitle);
		
		//text field for entering quiz title
		titleField = new JTextField();
		titleField.setBounds(130, 20, 239, 26);
		contentPane.add(titleField);
		titleField.setColumns(10);
		
		//label for quiz description
		
		lblDescription = new JLabel("Quiz Description:");
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDescription.setBounds(10, 79, 110, 38);
		contentPane.add(lblDescription);
		
		//text field for entering quiz description
		
		descriptionField = new JTextArea();
		descriptionField.setBounds(129, 75, 440, 52);
		contentPane.add(descriptionField);

		
		label = new JLabel("");
		label.setBounds(10, 173, 46, 14);
		contentPane.add(label);

		//label for quiz total marks
		
		lblTotalMarks = new JLabel("Total Marks: ");
		lblTotalMarks.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTotalMarks.setBounds(10, 195, 110, 14);
		contentPane.add(lblTotalMarks);

		//text field for entering quiz total marks
		
		quizMarksField = new JTextField();
		quizMarksField.setBounds(130, 194, 118, 20);
		contentPane.add(quizMarksField);
		quizMarksField.setColumns(10);

		//label for quiz time allowd for quiz
		
		lblTimeAllowed = new JLabel("Time Allowed: ");
		lblTimeAllowed.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTimeAllowed.setBounds(10, 256, 110, 14);
		contentPane.add(lblTimeAllowed);

		//text field for entering the total time for quiz
		
		quizTimeField = new JTextField();
		quizTimeField.setColumns(10);
		quizTimeField.setBounds(130, 255, 118, 20);
		contentPane.add(quizTimeField);
		
		//below is the btn for creating the quiz
		
		btnAddQuestion = new JButton("Create Quiz");
		btnAddQuestion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//here a new obj of quizCredential class is created and quiz details
				//are stored in the quizCredential.txt files
				
				quizCredentials obj=new quizCredentials();
				obj.quizTitle=titleField.getText();
				obj.quizDescription=descriptionField.getText();
				obj.quizTMarks=quizMarksField.getText();
				obj.quizTTime=quizTimeField.getText();
				
				//call to func that serializes the quiz details to an external file
				
				dataSerialization(obj);
				
				//below a new window for the user is prompted
				
				addQuizQuestions.main(null);
				dispose();// closes the current window
			}
		});
		btnAddQuestion.setBounds(273, 346, 110, 23);
		contentPane.add(btnAddQuestion);
		
	}
	
	// this method serializes the details of the quiz 
	
	public static void dataSerialization(quizCredentials obj) {
		try {
			FileOutputStream f=new FileOutputStream("quizCredentials.txt");
			ObjectOutputStream obf=new ObjectOutputStream(f);
			obf.writeObject(obj);
			obf.close();
			f.close();
		}catch(Exception error) {
			error.printStackTrace();
		}
	}
}

// below is the class that stores the quiz details in the form of an object

class quizCredentials implements java.io.Serializable{
	public String quizTitle;
	public String quizDescription;
	public String quizTMarks;
	public String quizTTime;
	
	public void setValues(String qt,String qd,String qm,String qtt) {
		quizTitle=qt;
		quizDescription=qd;
		quizTMarks=qm;
		quizTTime=qtt;
	}
	
}


