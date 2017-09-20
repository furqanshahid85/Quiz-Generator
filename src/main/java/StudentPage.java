import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//main class below

public class StudentPage extends JFrame implements java.io.Serializable {

	private static JPanel contentPane;

	/**
	 * Launch the application.
	 */
	
	// main function below
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentPage frame = new StudentPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//method to create the main window StudentPage
	
	public StudentPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 693, 408);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//JLabel are used to display text on the window
		
		JLabel lblQuizTitle = new JLabel("Quiz Title:");
		lblQuizTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblQuizTitle.setBounds(10, 23, 68, 21);
		contentPane.add(lblQuizTitle);
		
		JLabel lblQuizDescription = new JLabel("Quiz Description:");
		lblQuizDescription.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblQuizDescription.setBounds(10, 63, 103, 21);
		contentPane.add(lblQuizDescription);
		
		JLabel lblTotalMarks = new JLabel("Total Marks:");
		lblTotalMarks.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTotalMarks.setBounds(10, 101, 87, 21);
		contentPane.add(lblTotalMarks);
		
		JLabel lblTotalTime = new JLabel("Total Time:");
		lblTotalTime.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTotalTime.setBounds(10, 145, 68, 21);
		contentPane.add(lblTotalTime);
		
		//this method call below deserializes the quizCredentials.txt file
		//and returns a questionClass object. this object is then
		//used to display the stored contents i.e. details of the quiz
		
		dataDeserialization();
		
		//Attempt quiz btn allows the students to attempt the quiz 
		//it closes the current window after making a call to the
		// attemptQuiz JFrame
		
		JButton btnAttemptQuiz = new JButton("Attempt Quiz");
		btnAttemptQuiz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				attemptQuiz.main(null);
				dispose();
			}
		});
		btnAttemptQuiz.setBounds(236, 283, 103, 23);
		contentPane.add(btnAttemptQuiz);
		
	}

	//this method call below deserializes the quizCredentials.txt file
	//and returns a questionClass object. this object is then
	//used to display the stored contents i.e. details of the quiz
	
	public static void dataDeserialization() {
		
		try {
			FileInputStream f=new FileInputStream("quizCredentials.txt");
			ObjectInputStream oin=new ObjectInputStream(f);
			quizCredentials obj=(quizCredentials)oin.readObject();
			oin.close();
			f.close();
			
			//JLabels are used to display the questions and their options
			
			JLabel lblNewLabel = new JLabel("New label");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblNewLabel.setText(obj.quizTitle);
			lblNewLabel.setBounds(137, 28, 188, 16);
			contentPane.add(lblNewLabel);
			
			JLabel label = new JLabel("New label");
			label.setFont(new Font("Tahoma", Font.PLAIN, 14));
			label.setBounds(137, 63, 422, 21);
			label.setText(obj.quizDescription);
			contentPane.add(label);
			
			JLabel label_1 = new JLabel("New label");
			label_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
			label_1.setBounds(137, 104, 188, 14);
			label_1.setText(obj.quizTMarks);
			contentPane.add(label_1);
			
			JLabel label_2 = new JLabel("New label");
			label_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
			label_2.setBounds(137, 148, 188, 14);
			label_2.setText(obj.quizTTime);
			contentPane.add(label_2);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
