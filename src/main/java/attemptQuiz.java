import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

public class attemptQuiz extends JFrame {

	private static JPanel contentPane;
	private JLabel qField;
	private static int c=1;
	private static int x=90;
	public static  ArrayList<questionClass> outputArr=new ArrayList<questionClass>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					attemptQuiz frame = new attemptQuiz();
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
	public attemptQuiz() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 747, 489);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setPreferredSize(new Dimension(400, 1200));
		scrollPane.setBounds(10, 11, 678, 422);
		contentPane.add(scrollPane);
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(300, 1100));
		scrollPane.setViewportView(panel);
		panel.setLayout(null);
		
		dataDeserialization(panel);
		}
		
		//this method call below deserializes the quizCredentials.txt file
		//and returns an arraylist object. this object is then
		// stored in a new arraylist obj (outputArr) that is traversed upto
		// it's size using a for loop and the stored questions are displayed
		
		public static void dataDeserialization(JPanel panel) {
			
			try {
				//deserailization of quizCredentials to get the quiz tiltle
				
				FileInputStream f1=new FileInputStream("quizCredentials.txt");
				ObjectInputStream oin1=new ObjectInputStream(f1);
				quizCredentials obj1=(quizCredentials)oin1.readObject();
				oin1.close();
				f1.close();
				
				FileInputStream f=new FileInputStream("quiz.txt");
				ObjectInputStream oin=new ObjectInputStream(f);
				
				outputArr=(ArrayList<questionClass>)oin.readObject();
		
				oin.close();
				f.close();
				
				/* outputArr has the quiz contents, a for loops traverses the outputArr object
				 * displying the stored contents in their respective formats i.e. (mcqs,true/false,blanks)
				 */
				 /**/
				
				for (int i=0;i<outputArr.size();i++) {
		
					if(outputArr.get(i).qtype==1) { 
				
					JLabel mlbl=new JLabel("Q"+c+". ");
					mlbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
					mlbl.setText(outputArr.get(i).question);
					mlbl.setBounds(20,c*x , 500, 20);
					panel.add(mlbl);
					
					ButtonGroup groupOpt= new ButtonGroup();
					JRadioButton rdbtnoptA = new JRadioButton("A. ");
					JRadioButton rdbtnoptB = new JRadioButton("B. ");
					JRadioButton rdbtnoptC = new JRadioButton("C. ");
					JRadioButton rdbtnoptD = new JRadioButton("D. ");
					
					groupOpt.add(rdbtnoptA);
					rdbtnoptA.setBounds(40, 20+c*x, 40, 23);
					panel.add(rdbtnoptA);
					
					JLabel optFieldA = new JLabel();
					optFieldA.setBounds(80,22+c*x , 90, 20);
					optFieldA.setText(outputArr.get(i).opA);
					panel.add(optFieldA);
					
					groupOpt.add(rdbtnoptB);
					rdbtnoptB.setBounds(40, 40+c*x, 40, 23);
					panel.add(rdbtnoptB);
					
					JLabel optFieldB = new JLabel ("");
					optFieldB.setBounds(80,42+c*x , 90, 20);
					optFieldB.setText(outputArr.get(i).opB);
					panel.add(optFieldB);
					
					groupOpt.add(rdbtnoptC);
					rdbtnoptC.setBounds(180, 20+c*x, 40, 23);
					panel.add(rdbtnoptC);
					
					JLabel optFieldC = new JLabel ("");
					optFieldC.setBounds(220,22+c*x , 90, 20);
					optFieldC.setText(outputArr.get(i).opC);
					panel.add(optFieldC);
				
					groupOpt.add(rdbtnoptD);
					rdbtnoptD.setBounds(180, 40+c*x, 40, 23);
					panel.add(rdbtnoptD);
		
					JLabel optFieldD = new JLabel ("");
					optFieldD.setBounds(220,42+c*x , 90, 20);
					optFieldD.setText(outputArr.get(i).opD);
					panel.add(optFieldD);
					c++;
					}else if(outputArr.get(i).qtype==2) {
						
						
						JLabel tflbl=new JLabel("Q"+c+". ");
						tflbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
						tflbl.setText(outputArr.get(i).question);
						
						tflbl.setBounds(20,c*x , 500, 20);
						panel.add(tflbl);
						ButtonGroup groupOpt= new ButtonGroup();
						JRadioButton rdbtnoptA = new JRadioButton("A. ");
						JRadioButton rdbtnoptB = new JRadioButton("B. ");
						groupOpt.add(rdbtnoptA);
						rdbtnoptA.setBounds(40, 20+c*x, 40, 23);
						panel.add(rdbtnoptA);
						
						JLabel optFieldA = new JLabel();
						optFieldA.setBounds(80,22+c*x , 90, 20);
						optFieldA.setText(outputArr.get(i).opA);
						panel.add(optFieldA);
						
						groupOpt.add(rdbtnoptB);
						rdbtnoptB.setBounds(40, 40+c*x, 40, 23);
						panel.add(rdbtnoptB);
						
						JLabel optFieldB = new JLabel ("");
						optFieldB.setBounds(80,42+c*x , 90, 20);
						optFieldB.setText(outputArr.get(i).opB);
						panel.add(optFieldB);
						
						c++;
					}else if(outputArr.get(i).qtype==3){
						JLabel blbl=new JLabel("Q"+c+". ");
						blbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
						
						blbl.setText(outputArr.get(i).question);
						
						blbl.setBounds(20,c*x , 500, 20);
						panel.add(blbl);
						JTextField blankAns= new JTextField();
						blankAns.setBounds(80,42+c*x , 90, 20);
						panel.add(blankAns);
						c++;
					}
				}

			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}