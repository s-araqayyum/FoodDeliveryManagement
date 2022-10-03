import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UnsuccessfulAttempt extends JFrame {

	private JPanel contentPane;
	public UnsuccessfulAttempt() {
		setTitle("Error");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 453, 190);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Would you like to try again or  ");
		lblNewLabel_1.setBounds(34, 62, 229, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("Retry");
		lblNewLabel.setIcon(new ImageIcon("istockphoto-1183050921-612x612-removebg-preview.png"));
		lblNewLabel.setBounds(44, 0, 502, 306);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1_1 = new JLabel("create a new account?");
		lblNewLabel_1_1.setBounds(34, 76, 210, 14);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_2 = new JLabel("Invalid Input!");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(34, 44, 103, 14);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Retry");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				FoodOnWheelsGUI fow = new FoodOnWheelsGUI();
				fow.setVisible(true);
				
			}
		});
		btnNewButton.setBounds(27, 117, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Create Account");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFrame f = new JFrame();
			    JOptionPane.showMessageDialog(f,"Glad to have you! Proceed to console to continue");  
				
				FoodOnWheels fow = new FoodOnWheels();
				fow.RegisterAccount();
			}
		});
		btnNewButton_1.setBounds(126, 117, 118, 23);
		contentPane.add(btnNewButton_1);
	}
}