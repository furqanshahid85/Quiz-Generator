
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JRadioButton;

@SuppressWarnings("all")
//a
// main class is created here
public class Login extends JFrame {

	public String Tname="instructor";
	public String Sname="student";
	public String password="asdf123";	
	public boolean roleInstructor;
	public boolean roleStudent;
	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	//main func is created here
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	// here the main login window is created
	
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// here the a label is created
		JLabel lblQuizGenerator = new JLabel("Quiz Generator");
		lblQuizGenerator.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblQuizGenerator.setBounds(152, 11, 128, 14);
		contentPane.add(lblQuizGenerator);
		
		// here the username label is created
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsername.setBounds(10, 64, 68, 14);
		contentPane.add(lblUsername);
		
		// here the password label is created
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setBounds(10, 109, 68, 14);
		contentPane.add(lblPassword);
		
		// here the username field is created
		
		usernameField = new JTextField();
		usernameField.setBounds(88, 63, 236, 20);
		contentPane.add(usernameField);
		usernameField.setColumns(10);

		// here the password field is created
		
		passwordField = new JPasswordField();
		passwordField.setBounds(88, 108, 236, 20);
		contentPane.add(passwordField);
		
		//radio btn for selecting the user role i.e. student/instructor

		final JRadioButton rdbtnInstructor = new JRadioButton("Instructor");
		rdbtnInstructor.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnInstructor.setBounds(88, 158, 109, 23);
		contentPane.add(rdbtnInstructor);
		
		final JRadioButton rdbtnStudent = new JRadioButton("Student");
		rdbtnStudent.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnStudent.setBounds(215, 158, 109, 23);
		contentPane.add(rdbtnStudent);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnInstructor);
		group.add(rdbtnStudent);
		
		// here the login btn is created
		//when pressed the user is logged in, below is the event listener of login btn
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			// Here we get the username and password and check
			// whether it's correct
			
				String username=usernameField.getText();
				String password=passwordField.getText();
				
				// here we check which role is selected
				
				roleInstructor=rdbtnInstructor.isSelected();
				roleStudent=rdbtnStudent.isSelected();
				
				
			if(username.equals(Tname)&& password.equals(password) && roleInstructor) {
			
				//on successfull login the user is taken to another page
				
				InstructorPage.main(null);
				dispose();
				
			}else if(username.equals(Sname)&& password.equals(password) && roleStudent) { 
					
				StudentPage.main(null);
				dispose();
				
			}else {
				
				//if login is unsuccessful an error window is shown
			
				JOptionPane.showMessageDialog(null, "login error, incorrect credentials","Login Error",JOptionPane.ERROR_MESSAGE);
				passwordField.setText(null);
			}
			}});
		
		btnLogin.setBounds(152, 214, 96, 23);
		contentPane.add(btnLogin);
		
	}
}
