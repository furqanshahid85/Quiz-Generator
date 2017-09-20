import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("all")
public class InstructorPage extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InstructorPage frame = new InstructorPage();
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
	
	// here the main window of instructor page is created
	public InstructorPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 698, 446);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome Instructor");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(30, 28, 192, 14);
		contentPane.add(lblNewLabel);
		
		JButton btnCreateANew = new JButton("Create a new Quiz");
		btnCreateANew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// user is directed to a new window upon clicking the create new quiz btn
				
				CreateQuiz.main(null);
				dispose();
			}
		});
		btnCreateANew.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCreateANew.setBounds(30, 85, 166, 23);
		contentPane.add(btnCreateANew);
		
		JButton btnShowSavedQuizes = new JButton("Show Saved Quizes");
		btnShowSavedQuizes.setBounds(30, 161, 166, 23);
		contentPane.add(btnShowSavedQuizes);
	}
}
