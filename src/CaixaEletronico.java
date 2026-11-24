import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class CaixaEletronico extends JFrame {

	double saldo = 1000; 
	String historico = ""; 

	JLabel lblTitulo, lblSaldo, lblValor;
	JTextField txtValor;
	JButton btnDepositar, btnSacar, btnExtrato;
	JTextArea txtExtrato;

	public CaixaEletronico() {
		
		setTitle("Caixa Eletrônico");
		setSize(420, 520);
		setLayout(null);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		lblTitulo = new JLabel("BANCO ETEC - CAIXA ELETRÔNICO", SwingConstants.CENTER);
		lblTitulo.setBounds(0, 0, 420, 45);
		lblTitulo.setOpaque(true);
		lblTitulo.setBackground(new Color(0, 51, 102));
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 15));
		add(lblTitulo);

		lblSaldo = new JLabel("Saldo: R$ 1000,00", SwingConstants.CENTER);
		lblSaldo.setBounds(20, 60, 380, 40);
		lblSaldo.setFont(new Font("Arial", Font.BOLD, 24));
		add(lblSaldo);

		lblValor = new JLabel("Valor da operação:");
		lblValor.setBounds(30, 115, 360, 20);
		add(lblValor);

		txtValor = new JTextField();
		txtValor.setBounds(30, 138, 360, 30);
		add(txtValor);

		btnDepositar = new JButton("Depositar");
		btnDepositar.setBounds(30, 183, 115, 35);
		add(btnDepositar);

		btnSacar = new JButton("Sacar");
		btnSacar.setBounds(152, 183, 115, 35);
		add(btnSacar);

		btnExtrato = new JButton("Extrato");
		btnExtrato.setBounds(275, 183, 115, 35);
		add(btnExtrato);

		txtExtrato = new JTextArea();
		txtExtrato.setEditable(false);
		JScrollPane scroll = new JScrollPane(txtExtrato);
		scroll.setBounds(30, 235, 360, 230);
		add(scroll);


		btnDepositar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					double valor = Double.parseDouble(txtValor.getText().replace(",", "."));

					if (valor <= 0) {
						JOptionPane.showMessageDialog(null, "O valor deve ser maior que zero!");
					} else {
						saldo = saldo + valor;
						historico = historico + "Depósito de R$ " + String.format("%.2f", valor) + "\n"; 
						lblSaldo.setText("Saldo: R$ " + String.format("%.2f", saldo));
						txtExtrato.setText("Depósito de R$ " + String.format("%.2f", valor) + " realizado.");
						txtValor.setText("");}} 
				
			
			catch (NumberFormatException erro) {
					JOptionPane.showMessageDialog(null, "Digite apenas números!");}}});

		btnSacar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					double valor = Double.parseDouble(txtValor.getText().replace(",", "."));

					if (valor <= 0) { 
						JOptionPane.showMessageDialog(null, "O valor deve ser maior que zero!");
					} else if (valor > saldo) { 
						JOptionPane.showMessageDialog(null, "Saldo insuficiente!");
					} else if (valor > 1000) { 
						JOptionPane.showMessageDialog(null, "O saque máximo por operação é R$ 1000,00!");
					} else if (valor % 10 != 0) {
						JOptionPane.showMessageDialog(null, "O valor deve ser múltiplo de 10 (notas de 100, 50 e 10)!");
					} else {
						
						int reais = (int) valor;
						int notas100 = reais / 100;
						int resto = reais % 100;
						int notas50 = resto / 50;
						resto = resto % 50;
						int notas10 = resto / 10;

						saldo = saldo - valor;

						String notas = "";
						if (notas100 > 0) {
							notas = notas + notas100 + "x R$ 100\n";
						}
						if (notas50 > 0) {
							notas = notas + notas50 + "x R$ 50\n";
						}
						if (notas10 > 0) {
							notas = notas + notas10 + "x R$ 10\n";
						}

						historico = historico + "Saque de R$ " + String.format("%.2f", valor) + "\n" + notas; 
						lblSaldo.setText("Saldo: R$ " + String.format("%.2f", saldo));
						txtExtrato.setText("Saque de R$ " + String.format("%.2f", valor) + "\n" + notas);
						txtValor.setText("");}} 
				
				catch (NumberFormatException erro) {
					JOptionPane.showMessageDialog(null, "Digite apenas números!");}}});

		btnExtrato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (historico.equals("")) {
					txtExtrato.setText("Nenhuma operação realizada.");
				} else {
					txtExtrato.setText(historico);}}});}

	public static void main(String[] args) {
		new CaixaEletronico().setVisible(true);}}