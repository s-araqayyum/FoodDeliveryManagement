import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JButton;

public class FoodOnWheelsGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField textField_1;

	public FoodOnWheelsGUI() {
		setTitle("Restaurant Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 477, 379);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("eating-healthy-food-pana-3101 (1).png"));
		lblNewLabel.setBounds(-187, 0, 605, 524);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(291, 170, 120, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Enter Username");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(291, 145, 120, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Enter Password");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_1.setBounds(291, 201, 120, 14);
		contentPane.add(lblNewLabel_1_1);
		
		textField_1 = new JPasswordField();
		textField_1.setColumns(10);
		textField_1.setBounds(291, 226, 120, 20);
		contentPane.add(textField_1);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setBounds(358, 301, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Create Account");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame f = new JFrame();
				JOptionPane.showMessageDialog(f,"Glad to have you onboard! Go to the console to continue");  
				
				FoodOnWheels fow = new FoodOnWheels();
				fow.RegisterAccount();
			}
		});
		btnNewButton_1.setBounds(218, 301, 120, 23);
		contentPane.add(btnNewButton_1);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
						try {
							
							String loginString = textField.getText();
							String password = textField_1.getText();
							
							int login = Integer.parseInt(loginString);
							
							FoodOnWheels fow = new FoodOnWheels();
							fow.Login(login, password);
							
						} catch (Exception exc) {
							exc.printStackTrace();
						};
	}
});
	}	
}