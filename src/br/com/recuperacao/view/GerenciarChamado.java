package br.com.recuperacao.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import br.com.recuperacao.dao.CRUDApapaconha;
import br.com.recuperacao.domain.Apapaconha;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GerenciarChamado extends JFrame {

	private JPanel contentPane;
	private Long id = null;
	private JTable table;
	private JTextPane txtDescricao;
	private JTextPane txtObservacao;
	private JTextField txtNomePessoa;
	private JTextField txtNomeFuncionario;
	private JTextField txtDepartamento;
	private JTextField txtStatusChamado;
	private JFormattedTextField txtDataAbertura;
	private JFormattedTextField txtDataResolucao;
	private JButton btnCadastrar;
	private JButton btnAtualizar;
	private JButton btnListar;
	private JButton btnApagar;
	
	private MaskFormatter da;
	private MaskFormatter dr;
	
	CRUDApapaconha cc = new CRUDApapaconha();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GerenciarChamado frame = new GerenciarChamado();
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
	public GerenciarChamado() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(GerenciarChamado.class.getResource("/br/com/recuperacao/view/mobile-3-icon.png")));
		try {
		setTitle("Gerenciamendo de Chamado");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(50, 205, 50));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		carregarTabela();
		
		btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Apapaconha cadChamado = new Apapaconha();
				if(txtNomePessoa.getText().trim().equals("")
						||txtDepartamento.getText().trim().equals("")
						||txtDescricao.getText().trim().equals("")
						||txtDataAbertura.getText().trim().equals("")
						||txtDataResolucao.getText().trim().equals("")
						||txtStatusChamado.getText().trim().equals("")
						||txtObservacao.getText().trim().equals("")
						||txtNomeFuncionario.getText().trim().equals("")
						) {
					JOptionPane.showMessageDialog(null, "Você precisa preencher todos os campos!","ERRO",JOptionPane.ERROR_MESSAGE);
				}
				else {
					cadChamado.setNomePessoa(txtNomePessoa.getName());
					cadChamado.setDepartamento(txtDepartamento.getName());
					cadChamado.setDescricao(txtDescricao.getName());
					cadChamado.setDataAbertura(Date.valueOf(txtDataAbertura.getText()));
					cadChamado.setDataResolucao(Date.valueOf(txtDataResolucao.getText()));
					cadChamado.setStatusChamado(txtStatusChamado.getText());
					cadChamado.setObservacao(txtObservacao.getText());
					cadChamado.setNomeFuncionario(txtNomeFuncionario.getText());
					cadChamado.setIdChamado(id);
					
					JOptionPane.showMessageDialog(null, cc.atualizar(cadChamado).getNomePessoa()+" Foi atualizado!");
					carregarTabela();
					limparCampos();
				}
				
			}
		});
		btnAtualizar.setForeground(new Color(0, 0, 0));
		btnAtualizar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnAtualizar.setBackground(new Color(255, 255, 255));
		btnAtualizar.setBounds(140, 224, 120, 23);
		contentPane.add(btnAtualizar);
		
		btnListar = new JButton("Listar");
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnListar.setForeground(new Color(0, 0, 0));
		btnListar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnListar.setBackground(new Color(255, 255, 255));
		btnListar.setBounds(270, 224, 120, 23);
		contentPane.add(btnListar);
		
		btnApagar = new JButton("Apagar");
		btnApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(id.equals(null)) {
					JOptionPane.showMessageDialog(null, "Selecione um chamado para apagar");
				}
				else {
					if(JOptionPane.showConfirmDialog(null, "Você realmente deseja apagar este chamado?","ATENÇÃO",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==0) {
						
						Apapaconha ar = new Apapaconha();
						ar.setIdChamado(id);
						JOptionPane.showMessageDialog(null, cc.apagar(ar));
						carregarTabela();
						limparCampos();
					}
				}
			}
		});
		btnApagar.setForeground(new Color(0, 0, 0));
		btnApagar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnApagar.setBackground(new Color(255, 255, 255));
		btnApagar.setBounds(398, 224, 120, 23);
		contentPane.add(btnApagar);

		txtNomePessoa = new JTextField();
		txtNomePessoa.setBounds(10, 25, 260, 20);
		contentPane.add(txtNomePessoa);
		txtNomePessoa.setColumns(10);
		
		txtNomeFuncionario = new JTextField();
		txtNomeFuncionario.setColumns(10);
		txtNomeFuncionario.setBounds(300, 25, 260, 20);
		contentPane.add(txtNomeFuncionario);
		
		JLabel lblNomePessoa = new JLabel("Nome da Pessoa:");
		lblNomePessoa.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNomePessoa.setForeground(new Color(0, 0, 0));
		lblNomePessoa.setBounds(10, 11, 120, 14);
		contentPane.add(lblNomePessoa);
		
		JLabel lblNomeFuncionario = new JLabel("Nome do Funcionário:");
		lblNomeFuncionario.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNomeFuncionario.setForeground(new Color(0, 0, 0));
		lblNomeFuncionario.setBounds(300, 11, 154, 14);
		contentPane.add(lblNomeFuncionario);
		
		JEditorPane txtDescricao = new JEditorPane();
		txtDescricao.setBounds(300, 108, 260, 105);
		contentPane.add(txtDescricao);
		
		JLabel lblDescricao = new JLabel("Descrição:");
		lblDescricao.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDescricao.setForeground(new Color(0, 0, 0));
		lblDescricao.setBounds(300, 93, 120, 14);
		contentPane.add(lblDescricao);
		
		JEditorPane txtObservacao = new JEditorPane();
		txtObservacao.setBounds(10, 149, 260, 64);
		contentPane.add(txtObservacao);
		
		JLabel lblObservacao = new JLabel("Observações:");
		lblObservacao.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblObservacao.setForeground(new Color(0, 0, 0));
		lblObservacao.setBounds(10, 134, 200, 14);
		contentPane.add(lblObservacao);
		
		txtDepartamento = new JTextField();
		txtDepartamento.setColumns(10);
		txtDepartamento.setBounds(10, 65, 260, 20);
		contentPane.add(txtDepartamento);
		
		JLabel lblDepartamento = new JLabel("Departamento:");
		lblDepartamento.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDepartamento.setForeground(new Color(0, 0, 0));
		lblDepartamento.setBounds(10, 52, 120, 14);
		contentPane.add(lblDepartamento);
		
		dr = new MaskFormatter("####/##/##");
		dr.setPlaceholderCharacter('_');
		
		txtDataResolucao = new JFormattedTextField(dr);
		txtDataResolucao.setColumns(10);
		txtDataResolucao.setBounds(440, 65, 120, 20);
		contentPane.add(txtDataResolucao);
		
		da = new MaskFormatter("####/##/##");
		da.setPlaceholderCharacter('_');
		
		txtDataAbertura = new JFormattedTextField(da);
		txtDataAbertura.setColumns(10);
		txtDataAbertura.setBounds(300, 65, 120, 20);
		contentPane.add(txtDataAbertura);
		
		JLabel lblDataAbertura = new JLabel("Data de Abertura:");
		lblDataAbertura.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDataAbertura.setForeground(new Color(0, 0, 0));
		lblDataAbertura.setBounds(300, 52, 120, 14);
		contentPane.add(lblDataAbertura);
		
		JLabel lblDataResolucao = new JLabel("Data de Resolução:");
		lblDataResolucao.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDataResolucao.setForeground(new Color(0, 0, 0));
		lblDataResolucao.setBounds(440, 52, 120, 14);
		contentPane.add(lblDataResolucao);
		
		txtStatusChamado = new JTextField();
		txtStatusChamado.setBounds(10, 108, 260, 20);
		contentPane.add(txtStatusChamado);
		txtStatusChamado.setColumns(10);
		
		JLabel lblStatusChamado = new JLabel("Status do Chamado:");
		lblStatusChamado.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblStatusChamado.setBackground(new Color(0, 0, 0));
		lblStatusChamado.setForeground(new Color(0, 0, 0));
		lblStatusChamado.setBounds(10, 93, 120, 14);
		contentPane.add(lblStatusChamado);
		
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(633, 0, 241, 236);
		contentPane.add(lblNewLabel);
		
		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Apapaconha cadChamado = new Apapaconha();
				if(txtNomePessoa.getText().trim().equals("")
						||txtDepartamento.getText().trim().equals("")
						||txtDescricao.getText().trim().equals("")
						||txtDataAbertura.getText().trim().equals("")
						||txtDataResolucao.getText().trim().equals("")
						||txtStatusChamado.getText().trim().equals("")
						||txtObservacao.getText().trim().equals("")
						||txtNomeFuncionario.getText().trim().equals("")
						) {
					JOptionPane.showMessageDialog(null, "Você precisa preencher todos os campos!","ERRO",JOptionPane.ERROR_MESSAGE);
				}
				else {
					cadChamado.setNomePessoa(txtNomePessoa.getName());
					cadChamado.setDepartamento(txtDepartamento.getName());
					cadChamado.setDescricao(txtDescricao.getName());
					cadChamado.setDataAbertura(Date.valueOf(txtDataAbertura.getText()));
					cadChamado.setDataResolucao(Date.valueOf(txtDataResolucao.getText()));
					cadChamado.setStatusChamado(txtStatusChamado.getText());
					cadChamado.setObservacao(txtObservacao.getText());
					cadChamado.setNomeFuncionario(txtNomeFuncionario.getText());
					
					JOptionPane.showMessageDialog(null, cc.gravar(cadChamado));
					carregarTabela();
					limparCampos();
				}
			}
		});
		btnCadastrar.setForeground(new Color(0, 0, 0));
		btnCadastrar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnCadastrar.setBackground(new Color(255, 255, 255));
		btnCadastrar.setBounds(10, 224, 120, 23);
		contentPane.add(btnCadastrar);
		
		JButton btnAbrirGrafico = new JButton("Abrir Grafico");
		btnAbrirGrafico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAbrirGrafico.setBounds(528, 226, 110, 23);
		contentPane.add(btnAbrirGrafico);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void carregarTabela() {
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 258, 864, 192);
		contentPane.add(scrollPane);
		
		String[] cabecalho = {
				"ID Chamado",
				"Nome da Pessoa",
				"Departamento",
				"Descrição da Chamada",
				"Data de Abertura",
				"Data de Resolução",
				"Status do Chamado",
				"Observação",
				"Nome do Funcionário",
				
		};
		
		
		Object[][] dados = new Object[cc.listar().size()][9];
		
		for(int i = 0; i < dados.length; i++) {
			dados[i][0] = cc.listar().get(i).getIdChamado();
			dados[i][1] = cc.listar().get(i).getNomePessoa();
			dados[i][2] = cc.listar().get(i).getDepartamento();
			dados[i][3] = cc.listar().get(i).getDescricao();
			dados[i][4] = cc.listar().get(i).getDataAbertura();
			dados[i][5] = cc.listar().get(i).getDataResolucao();
			dados[i][6] = cc.listar().get(i).getStatusChamado();
			dados[i][7] = cc.listar().get(i).getObservacao();
			dados[i][8] = cc.listar().get(i).getNomeFuncionario();
		}
		
		DefaultTableModel model = new DefaultTableModel(dados, cabecalho);
		
		JTable table = new JTable(model);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtNomePessoa.setText(String.valueOf(table.getValueAt(table.getSelectedRow(),1)));
				txtDepartamento.setText(String.valueOf(table.getValueAt(table.getSelectedRow(),2)));
				txtDescricao.setText(String.valueOf(table.getValueAt(table.getSelectedRow(),3)));
				txtDataAbertura.setText(String.valueOf(table.getValueAt(table.getSelectedRow(),4)));
				txtDataResolucao.setText(String.valueOf(table.getValueAt(table.getSelectedRow(),5)));
				txtStatusChamado.setText(String.valueOf(table.getValueAt(table.getSelectedRow(),6)));
				txtObservacao.setText(String.valueOf(table.getValueAt(table.getSelectedRow(),7)));
				txtNomeFuncionario.setText(String.valueOf(table.getValueAt(table.getSelectedRow(),8)));
				id = Long.parseLong(String.valueOf(table.getValueAt(table.getSelectedRow(),0)));
			}
		});
		scrollPane.setViewportView(table);
	}
	private void limparCampos() {
		
		txtNomePessoa.setText("");
		txtDepartamento.setText("");
		txtDescricao.setText("");
		txtDataAbertura.setText("");
		txtDataResolucao.setText("");
		txtStatusChamado.setText("");
		txtObservacao.setText("");
		txtNomeFuncionario.setText("");
	}
}
