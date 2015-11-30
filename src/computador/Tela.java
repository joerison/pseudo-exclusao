package computador;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.CardLayout;

import javax.swing.ImageIcon;
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
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JMenuItem;

import java.awt.Font;
import java.awt.Color;

import javax.swing.SwingConstants;

public class Tela {

	private JFrame frame;
	private JTextField arquivoNome;

	static Computador dell = new Computador();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela window = new Tela();
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
	public Tela() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Projeto PI I - Pseudo Exclusão (2015)");
		frame.setBounds(100, 100, 517, 606);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		ImageIcon img = new ImageIcon(Tela.class.getResource("/imagens/icon.png"));
		frame.setIconImage(img.getImage());

		JTabbedPane janelaPrincipal = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(janelaPrincipal, "name_3764417533209");

		JPanel criarArquivo = new JPanel();
		janelaPrincipal.addTab("Criar arquivo", null, criarArquivo, null);
		janelaPrincipal.setEnabledAt(0, true);
		criarArquivo.setLayout(null);

		JScrollPane painelArquivoConteudo = new JScrollPane();
		painelArquivoConteudo.setBounds(138, 159, 278, 91);
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
		btnLimpar.setBounds(292, 303, 117, 25);
		criarArquivo.add(btnLimpar);

		JPanel listarArquivos = new JPanel();
		janelaPrincipal.addTab("Listar arquivos", null, listarArquivos, null);
		janelaPrincipal.setEnabledAt(1, true);
		listarArquivos.setLayout(null);

		JScrollPane painelListagemArquivos = new JScrollPane();
		painelListagemArquivos.setBounds(12, 79, 225, 224);
		listarArquivos.add(painelListagemArquivos);

		JList<String> listaArquivosHd = new JList<String>();

		// list.removeAll();
		painelListagemArquivos.setViewportView(listaArquivosHd);

		JPanel recuperar = new JPanel();
		janelaPrincipal.addTab("Recuperar", null, recuperar, null);
		janelaPrincipal.setEnabledAt(2, true);
		recuperar.setLayout(null);

		JScrollPane painelArquivosRecuperar = new JScrollPane();
		painelArquivosRecuperar.setBounds(93, 91, 310, 286);
		recuperar.add(painelArquivosRecuperar);

		JList<String> listaArquivosRecuperar = new JList<String>(dell.exibirParaRecuperar());
		painelArquivosRecuperar.setViewportView(listaArquivosRecuperar);

		JButton btnRecuperar = new JButton("Recuperar");
		btnRecuperar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dell.exibirParaRecuperar().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Não há arquivos para recuperar.", "Erro",
							JOptionPane.ERROR_MESSAGE);
				} else {
					if (listaArquivosRecuperar.getSelectedIndex() == -1) {
						JOptionPane.showMessageDialog(null, "Selecione um arquivo", "Erro", JOptionPane.ERROR_MESSAGE);
					} else {
						int a = JOptionPane.showConfirmDialog(null, "Confirma a recuperação do arquivo?");
						if (a == 0) {
							try {
								dell.recuperarArquivo(listaArquivosRecuperar.getSelectedIndex());
								listaArquivosRecuperar.setModel(dell.exibirParaRecuperar());
								listaArquivosHd.setModel(dell.mostrarConteudoDoDisco());
								JOptionPane.showMessageDialog(null, "Arquivo recuperado com sucesso.", "Notificação",
										JOptionPane.INFORMATION_MESSAGE);
							} catch (Exception e1) {
							}
						}
					}
				}
			}
		});
		btnRecuperar.setBounds(188, 400, 117, 25);
		recuperar.add(btnRecuperar);

		JLabel lblArquivosLocalizados = new JLabel("::Arquivos localizados");
		lblArquivosLocalizados.setForeground(Color.DARK_GRAY);
		lblArquivosLocalizados.setFont(new Font("Dialog", Font.BOLD, 16));
		lblArquivosLocalizados.setBounds(12, 42, 211, 25);
		recuperar.add(lblArquivosLocalizados);

		JScrollPane painelDetalhesArquivo = new JScrollPane();
		painelDetalhesArquivo.setBounds(265, 79, 202, 224);
		listarArquivos.add(painelDetalhesArquivo);

		JTextPane detalhesArquivo = new JTextPane();
		painelDetalhesArquivo.setViewportView(detalhesArquivo);
		detalhesArquivo.setEditable(false);

		JScrollPane painelNotificacoes = new JScrollPane();
		painelNotificacoes.setBounds(267, 356, 200, 140);
		listarArquivos.add(painelNotificacoes);

		JTextPane notificacoes = new JTextPane();
		painelNotificacoes.setViewportView(notificacoes);
		notificacoes.setEditable(false);

		JLabel lblNotificaes = new JLabel("Notificações:");
		lblNotificaes.setBounds(267, 332, 123, 15);
		listarArquivos.add(lblNotificaes);

		JButton btnAbrir = new JButton("Abrir");
		btnAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dell.mostrarConteudoDoDisco().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Não há arquivos para exibir.", "Erro",
							JOptionPane.ERROR_MESSAGE);
				} else {
					try {
						detalhesArquivo.setText(dell.exibirDetalhesArquivo(listaArquivosHd.getSelectedIndex()));
						notificacoes.setText("");
					} catch (NullPointerException erro) {
						JOptionPane.showMessageDialog(null, "Selecione um arquivo", "Erro", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnAbrir.setBounds(12, 332, 83, 25);
		listarArquivos.add(btnAbrir);

		JButton btnShred = new JButton("Remover");
		btnShred.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (dell.mostrarConteudoDoDisco().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Não há arquivos para remover.", "Erro",
							JOptionPane.ERROR_MESSAGE);
				} else {

					if (listaArquivosHd.getSelectedIndex() == -1) {
						JOptionPane.showMessageDialog(null, "Selecione um arquivo", "Erro", JOptionPane.ERROR_MESSAGE);
					} else {
						Object[] options = { "Método Normal", "Método SHRED" };
						int n = JOptionPane.showOptionDialog(frame, "Como você deseja remover o arquivo?",
								"Selecione um método de remoção", JOptionPane.YES_NO_CANCEL_OPTION,
								JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

						if (n == 0) {
							int arquivoParaExclusao = listaArquivosHd.getSelectedIndex();
							try {
								String resultadoDaExclusao = dell.excluirArquivo(MetodoExclusao.NATIVA,
										arquivoParaExclusao);
								/*
								 * removeAll + setModel funcionam em conjunto
								 * com a finalidade de atualizar o conteudo da
								 * lista
								 */
								listaArquivosHd.removeAll();
								listaArquivosHd.setModel(dell.mostrarConteudoDoDisco());
								listaArquivosRecuperar.setModel(dell.exibirParaRecuperar());
								notificacoes.setText(resultadoDaExclusao);
							} catch (Exception e1) {
							}

						} else if (n == 1) {
							int arquivoParaExclusao = listaArquivosHd.getSelectedIndex();
							try {
								String resultadoDaExclusao = dell.excluirArquivo(MetodoExclusao.SHRED,
										arquivoParaExclusao);
								/*
								 * removeAll + setModel funcionam em conjunto
								 * com a finalidade de atualizar o conteudo da
								 * lista
								 */
								listaArquivosHd.removeAll();
								listaArquivosHd.setModel(dell.mostrarConteudoDoDisco());
								listaArquivosRecuperar.setModel(dell.exibirParaRecuperar());
								notificacoes.setText(resultadoDaExclusao);
							} catch (Exception e1) {
							}
						}
					}
					detalhesArquivo.setText("");
				}
			}
		});
		btnShred.setBounds(142, 332, 95, 25);
		listarArquivos.add(btnShred);

		JLabel lblListarArquivos = new JLabel("::Listar arquivos");
		lblListarArquivos.setForeground(Color.DARK_GRAY);
		lblListarArquivos.setFont(new Font("Dialog", Font.BOLD, 16));
		lblListarArquivos.setBounds(12, 42, 183, 25);
		listarArquivos.add(lblListarArquivos);

		JLabel lblDetalhesDoArquivo = new JLabel("Detalhes do arquivo");
		lblDetalhesDoArquivo.setBounds(267, 52, 169, 15);
		listarArquivos.add(lblDetalhesDoArquivo);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (arquivoNome.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o nome do arquivo", "Erro", JOptionPane.ERROR_MESSAGE);
				} else if (arquivoConteudo.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o conteúdo do arquivo", "Erro",
							JOptionPane.ERROR_MESSAGE);

				} else {
					dell.criarArquivo(arquivoNome.getText(), arquivoConteudo.getText());
					listaArquivosHd.setModel(dell.mostrarConteudoDoDisco());
					arquivoNome.setText("");
					arquivoConteudo.setText("");
					JOptionPane.showMessageDialog(null, "Arquivo salvo com sucesso.", "Confirmação",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnSalvar.setBounds(138, 303, 117, 25);
		criarArquivo.add(btnSalvar);

		JLabel lblCriarArquivo = new JLabel("::Criar arquivo");
		lblCriarArquivo.setForeground(Color.DARK_GRAY);
		lblCriarArquivo.setFont(new Font("Dialog", Font.BOLD, 16));
		lblCriarArquivo.setBounds(12, 42, 183, 25);
		criarArquivo.add(lblCriarArquivo);

		JPanel sobre = new JPanel();
		sobre.setOpaque(false);
		sobre.setRequestFocusEnabled(false);
		sobre.setVerifyInputWhenFocusTarget(false);
		janelaPrincipal.addTab("Sobre", null, sobre, null);
		sobre.setLayout(null);

		JLabel lblOlaIssoEh = new JLabel("PSEUDO-EXCLUSÃO:");
		lblOlaIssoEh.setHorizontalAlignment(SwingConstants.CENTER);
		lblOlaIssoEh.setBounds(12, 68, 488, 15);
		sobre.add(lblOlaIssoEh);

		JLabel lblAbordagemDaFalsa = new JLabel("ABORDAGEM DA FALSA PERCEPÇÃO DE REMOÇÃO DE ARQUIVO NO");
		lblAbordagemDaFalsa.setHorizontalAlignment(SwingConstants.CENTER);
		lblAbordagemDaFalsa.setBounds(12, 87, 488, 15);
		sobre.add(lblAbordagemDaFalsa);

		JLabel label = new JLabel("ÂMBITO COMPUTACIONAL");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(12, 105, 488, 15);
		sobre.add(label);

		JLabel lblJoerisonSilva = new JLabel("Joerison SIlva");
		lblJoerisonSilva.setHorizontalAlignment(SwingConstants.CENTER);
		lblJoerisonSilva.setBounds(12, 141, 488, 15);
		sobre.add(lblJoerisonSilva);

		JLabel lblJoerisongmailcom = new JLabel("joerison@gmail.com");
		lblJoerisongmailcom.setHorizontalAlignment(SwingConstants.CENTER);
		lblJoerisongmailcom.setBounds(12, 164, 488, 15);
		sobre.add(lblJoerisongmailcom);

		JLabel lblRamonRezende = new JLabel("Ramon Rezende");
		lblRamonRezende.setHorizontalAlignment(SwingConstants.CENTER);
		lblRamonRezende.setBounds(12, 191, 488, 15);
		sobre.add(lblRamonRezende);

		JLabel lblRamonderezendegmailcom = new JLabel("ramonderezende@gmail.com");
		lblRamonderezendegmailcom.setHorizontalAlignment(SwingConstants.CENTER);
		lblRamonderezendegmailcom.setBounds(12, 211, 488, 15);
		sobre.add(lblRamonderezendegmailcom);

		JLabel lblProfGuilhermeVelozo = new JLabel("Prof. Guilherme Velozo N. Oliveira");
		lblProfGuilhermeVelozo.setHorizontalAlignment(SwingConstants.CENTER);
		lblProfGuilhermeVelozo.setBounds(12, 259, 488, 15);
		sobre.add(lblProfGuilhermeVelozo);

		JLabel lblGuilhermeupisbr = new JLabel("guilherme03058@upis.br");
		lblGuilhermeupisbr.setHorizontalAlignment(SwingConstants.CENTER);
		lblGuilhermeupisbr.setBounds(12, 282, 488, 15);
		sobre.add(lblGuilhermeupisbr);

		JLabel lblUnioPioneiraDe = new JLabel("UNIÃO PIONEIRA DE INTEGRAÇÃO SOCIAL");
		lblUnioPioneiraDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblUnioPioneiraDe.setBounds(12, 31, 488, 15);
		sobre.add(lblUnioPioneiraDe);

		JLabel lblUpis = new JLabel("UPIS");
		lblUpis.setHorizontalAlignment(SwingConstants.CENTER);
		lblUpis.setBounds(12, 12, 488, 15);
		sobre.add(lblUpis);

		JLabel lblProjetoIntegrado = new JLabel("PROJETO INTEGRADO I");
		lblProjetoIntegrado.setHorizontalAlignment(SwingConstants.CENTER);
		lblProjetoIntegrado.setBounds(12, 46, 488, 15);
		sobre.add(lblProjetoIntegrado);

		JLabel lblBrasliaDf = new JLabel("Brasília - DF, 2015");
		lblBrasliaDf.setHorizontalAlignment(SwingConstants.CENTER);
		lblBrasliaDf.setBounds(12, 425, 488, 15);
		sobre.add(lblBrasliaDf);
		
		JLabel lblJorgeSobue = new JLabel("Jorge Sobue");
		lblJorgeSobue.setHorizontalAlignment(SwingConstants.CENTER);
		lblJorgeSobue.setBounds(12, 360, 195, 15);
		sobre.add(lblJorgeSobue);
		
		JLabel lblJorgesobuegmailcom = new JLabel("jorgesobue@gmail.com");
		lblJorgesobuegmailcom.setHorizontalAlignment(SwingConstants.CENTER);
		lblJorgesobuegmailcom.setBounds(12, 383, 200, 15);
		sobre.add(lblJorgesobuegmailcom);
		
		JLabel lblContatoclebermitchellcombr = new JLabel("contato@clebermitchell.com.br");
		lblContatoclebermitchellcombr.setHorizontalAlignment(SwingConstants.CENTER);
		lblContatoclebermitchellcombr.setBounds(252, 383, 235, 15);
		sobre.add(lblContatoclebermitchellcombr);
		
		JLabel lblCleberMitchell = new JLabel("Cleber Mitchell");
		lblCleberMitchell.setHorizontalAlignment(SwingConstants.CENTER);
		lblCleberMitchell.setBounds(275, 360, 184, 15);
		sobre.add(lblCleberMitchell);
		
		JLabel lblAgradecimentos = new JLabel("Agradecimentos:");
		lblAgradecimentos.setHorizontalAlignment(SwingConstants.CENTER);
		lblAgradecimentos.setBounds(12, 329, 488, 15);
		sobre.add(lblAgradecimentos);

		JPanel manual = new JPanel();
		manual.setLayout(null);
		manual.setVerifyInputWhenFocusTarget(false);
		manual.setRequestFocusEnabled(false);
		manual.setOpaque(false);
		// janelaPrincipal.addTab("Manual", null, manual, null);

		JLabel lblRemovaUm = new JLabel("3) Remova um arquivo usando o método normal.");
		lblRemovaUm.setHorizontalAlignment(SwingConstants.LEFT);
		lblRemovaUm.setBounds(12, 66, 488, 15);
		manual.add(lblRemovaUm);

		JLabel lblRecupereOs = new JLabel("5) Recupere os arquivos");
		lblRecupereOs.setHorizontalAlignment(SwingConstants.LEFT);
		lblRecupereOs.setBounds(12, 117, 488, 15);
		manual.add(lblRecupereOs);

		JLabel lblAnaliseOs = new JLabel("6) Analise os arquivos recuperados.");
		lblAnaliseOs.setHorizontalAlignment(SwingConstants.LEFT);
		lblAnaliseOs.setBounds(12, 139, 488, 15);
		manual.add(lblAnaliseOs);

		JLabel lblUnioPioneiraDe_1 = new JLabel("1) Crie um arquivo.");
		lblUnioPioneiraDe_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblUnioPioneiraDe_1.setBounds(12, 12, 488, 15);
		manual.add(lblUnioPioneiraDe_1);

		JLabel lblExibaOs = new JLabel("2) Exiba os arquivos.");
		lblExibaOs.setHorizontalAlignment(SwingConstants.LEFT);
		lblExibaOs.setBounds(12, 39, 488, 15);
		manual.add(lblExibaOs);

		JLabel lblRemovaUm_1 = new JLabel("4) Remova um arquivo usando o método SHRED.");
		lblRemovaUm_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblRemovaUm_1.setBounds(12, 90, 488, 15);
		manual.add(lblRemovaUm_1);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnIncio = new JMenu("Início");
		menuBar.add(mnIncio);

		JMenuItem mntmNewMenuItem = new JMenuItem("Desligar");
		mnIncio.add(mntmNewMenuItem);
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		JMenu mnAjuda = new JMenu("Ajuda");
		menuBar.add(mnAjuda);

		JMenuItem mntmManual = new JMenuItem("Manual");
		mntmManual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame = new JFrame("Manual");
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.getContentPane().add(manual);
				frame.pack();
				frame.setVisible(true);
				frame.setBounds(100, 100, 517, 200);
			}
		});
		mnAjuda.add(mntmManual);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Sobre");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame = new JFrame("Sobre");
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.getContentPane().add(sobre);
				frame.pack();
				frame.setVisible(true);
				frame.setBounds(100, 100, 517, 470);
			}
		});
		mnAjuda.add(mntmNewMenuItem_1);

	}
}
