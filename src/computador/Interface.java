package computador;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.CardLayout;

import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JScrollPane;
import javax.swing.JList;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import javax.swing.JTextPane;
import javax.swing.JTextArea;

public class Interface {

	private JFrame frame;
	private JTextField arquivoNome;

	static Computador dell = new Computador();
	private JMenu mnDesligar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface window = new Interface();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Interface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 761, 606);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));

		JTabbedPane janelaPrincipal = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(janelaPrincipal, "name_3764417533209");

		JPanel criarArquivo = new JPanel();
		janelaPrincipal.addTab("Criar arquivo", null, criarArquivo, null);
		criarArquivo.setLayout(null);
		
		JScrollPane painelArquivoConteudo = new JScrollPane();
		painelArquivoConteudo.setBounds(138, 159, 278, 182);
		criarArquivo.add(painelArquivoConteudo);
		
		JTextArea arquivoConteudo = new JTextArea();
		painelArquivoConteudo.setViewportView(arquivoConteudo);


		arquivoNome = new JTextField();
		arquivoNome.setBounds(138, 128, 210, 19);
		criarArquivo.add(arquivoNome);
		arquivoNome.setColumns(10);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(53, 130, 70, 15);
		criarArquivo.add(lblNome);

		JLabel lblContedo = new JLabel("Conteúdo:");
		lblContedo.setBounds(53, 157, 80, 15);
		criarArquivo.add(lblContedo);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				arquivoNome.setText("");
				arquivoConteudo.setText("");
			}
		});
		btnLimpar.setBounds(320, 367, 117, 25);
		criarArquivo.add(btnLimpar);

		JPanel listarArquivos = new JPanel();
		janelaPrincipal.addTab("Listar Arquivos", null, listarArquivos, null);
		listarArquivos.setLayout(null);

		JScrollPane painelListagemArquivos = new JScrollPane();
		painelListagemArquivos.setBounds(30, 79, 348, 236);
		listarArquivos.add(painelListagemArquivos);

		JList<String> listaArquivosHd = new JList<String>();

		// list.removeAll();
		painelListagemArquivos.setViewportView(listaArquivosHd);

		JPanel panel = new JPanel();
		janelaPrincipal.addTab("Recuperar", null, panel, null);
		panel.setLayout(null);

		JScrollPane painelArquivosRecuperar = new JScrollPane();
		painelArquivosRecuperar.setBounds(63, 46, 317, 286);
		panel.add(painelArquivosRecuperar);

		JList<String> listaArquivosRecuperar = new JList<String>(
				dell.exibirParaRecuperar());
		painelArquivosRecuperar.setViewportView(listaArquivosRecuperar);

		JButton btnRecuperar = new JButton("Recuperar");
		btnRecuperar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dell.exibirParaRecuperar().isEmpty()) {
					JOptionPane.showMessageDialog(null,
							"Não há arquivos para recuperar.", "Erro",
							JOptionPane.ERROR_MESSAGE);
				} else {
					if (listaArquivosRecuperar.getSelectedIndex() == -1) {
						JOptionPane.showMessageDialog(null,
								"Selecione um arquivo", "Erro",
								JOptionPane.ERROR_MESSAGE);
					} else {
						int a = JOptionPane.showConfirmDialog(null,
								"Confirma a recuperação do arquivo?");
						if (a == 0) {
							try {
								dell.recuperarArquivo(listaArquivosRecuperar
										.getSelectedIndex());
							} catch (Exception e1) {
							}
							listaArquivosRecuperar.setModel(dell
									.exibirParaRecuperar());
							listaArquivosHd.setModel(dell
									.mostrarConteudoDoDisco());
						}
					}
				}
			}
		});
		btnRecuperar.setBounds(49, 386, 117, 25);
		panel.add(btnRecuperar);

		JTextPane notificacoes = new JTextPane();
		notificacoes.setBounds(413, 374, 225, 111);
		listarArquivos.add(notificacoes);

		JLabel lblNotificaes = new JLabel("Notificações:");
		lblNotificaes.setBounds(413, 349, 123, 15);
		listarArquivos.add(lblNotificaes);

		JTextPane detalhesArquivo = new JTextPane();
		detalhesArquivo.setEditable(false);
		detalhesArquivo.setBounds(407, 79, 231, 236);
		listarArquivos.add(detalhesArquivo);

		JButton btnAbrir = new JButton("Abrir");
		btnAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dell.mostrarConteudoDoDisco().isEmpty()) {
					JOptionPane.showMessageDialog(null,
							"Não há arquivos para exibir.", "Erro",
							JOptionPane.ERROR_MESSAGE);
				} else {
					try {
						detalhesArquivo.setText(dell
								.exibirDetalhesArquivo(listaArquivosHd
										.getSelectedIndex()));
						notificacoes.setText("");
					} catch (NullPointerException erro) {
						JOptionPane.showMessageDialog(null,
								"Selecione um arquivo", "Erro",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnAbrir.setBounds(107, 344, 83, 25);
		listarArquivos.add(btnAbrir);

		JButton btnShred = new JButton("Remover");
		btnShred.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (dell.mostrarConteudoDoDisco().isEmpty()) {
					JOptionPane.showMessageDialog(null,
							"Não há arquivos para remover.", "Erro",
							JOptionPane.ERROR_MESSAGE);
				} else {

					if (listaArquivosHd.getSelectedIndex() == -1) {
						JOptionPane.showMessageDialog(null,
								"Selecione um arquivo", "Erro",
								JOptionPane.ERROR_MESSAGE);
					} else {
						Object[] options = { "Método Normal", "Método SHRED" };
						int n = JOptionPane.showOptionDialog(frame,
								"Como você deseja remover o arquivo?",
								"Selecione um método de remoção",
								JOptionPane.YES_NO_CANCEL_OPTION,
								JOptionPane.QUESTION_MESSAGE, null, options,
								options[0]);

						if (n == 0) {
							int arquivoParaExclusao = listaArquivosHd
									.getSelectedIndex();
							try {
								String resultadoDaExclusao = dell
										.excluirArquivo(MetodoExclusao.NATIVA,
												arquivoParaExclusao);
								/*
								 * removeAll + setModel funcionam em conjunto
								 * com a finalidade de atualizar o conteudo da
								 * lista
								 */
								listaArquivosHd.removeAll();
								listaArquivosHd.setModel(dell
										.mostrarConteudoDoDisco());
								listaArquivosRecuperar.setModel(dell
										.exibirParaRecuperar());
								notificacoes.setText(resultadoDaExclusao);
							} catch (Exception e1) {
							}

						} else if (n == 1) {
							int arquivoParaExclusao = listaArquivosHd
									.getSelectedIndex();
							try {
								String resultadoDaExclusao = dell
										.excluirArquivo(MetodoExclusao.SHRED,
												arquivoParaExclusao);
								/*
								 * removeAll + setModel funcionam em conjunto
								 * com a finalidade de atualizar o conteudo da
								 * lista
								 */
								listaArquivosHd.removeAll();
								listaArquivosHd.setModel(dell
										.mostrarConteudoDoDisco());
								listaArquivosRecuperar.setModel(dell
										.exibirParaRecuperar());
								notificacoes.setText(resultadoDaExclusao);
							} catch (Exception e1) {
							}
						}
					}
					detalhesArquivo.setText("");
				}
			}
		});
		btnShred.setBounds(202, 344, 95, 25);
		listarArquivos.add(btnShred);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (arquivoNome.getText().equals("")) {
					JOptionPane.showMessageDialog(null,
							"Você não informou o nome do arquivo", "Erro",
							JOptionPane.ERROR_MESSAGE);
				} else if (arquivoConteudo.getText().equals("")) {
					JOptionPane.showMessageDialog(null,
							"Você não informou o conteúdo do arquivo", "Erro",
							JOptionPane.ERROR_MESSAGE);

				} else {
					dell.criarArquivo(arquivoNome.getText(),
							arquivoConteudo.getText());
					listaArquivosHd.setModel(dell.mostrarConteudoDoDisco());
					arquivoNome.setText("");
					arquivoConteudo.setText("");
					JOptionPane.showMessageDialog(null,
							"Arquivo salvo com sucesso.", "Confirmação",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnSalvar.setBounds(165, 367, 117, 25);
		criarArquivo.add(btnSalvar);

		JLabel lblOlaa = DefaultComponentFactory.getInstance().createTitle(
				"Trabalho de PI I - Pseudo Exclusão");
		lblOlaa.setBounds(189, 12, 267, 35);
		criarArquivo.add(lblOlaa);

		JLabel lblCriarArquivo = DefaultComponentFactory.getInstance()
				.createTitle("CRIAR ARQUIVO");
		lblCriarArquivo.setBounds(53, 80, 140, 19);
		criarArquivo.add(lblCriarArquivo);
		
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnIncio = new JMenu("Início");
		menuBar.add(mnIncio);

		mnDesligar = new JMenu("Desligar");
		mnDesligar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("teste");
			}
		});
		menuBar.add(mnDesligar);

		JMenu mnAjuda = new JMenu("Ajuda");
		menuBar.add(mnAjuda);

	}
	public JMenu getMnDesligar() {
		return mnDesligar;
	}
}
