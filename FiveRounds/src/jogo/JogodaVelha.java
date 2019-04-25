/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory;
import java.awt.Color;
import static java.awt.Color.blue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.text.ParseException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ActionMap;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import sun.nio.cs.ext.JIS_X_0212_MS5022X;

/**
 *
 * @author Wagdson
 */
public class JogodaVelha extends javax.swing.JFrame {

    boolean jogadorAtivo1;
    boolean jogadorAtivo2;
    String jogador1;
    String jogador2;
    String novoJogo;
    int contEmpate = 0;
    int contResult = 0;
    int rodadas = 1;
    int resultado[][] = new int[2][5];
    int matrizResultado[][] = new int[2][5];
    int vitoriaX = 0;
    int vitoriaO = 0;
    int numerosDeEmpate = 0;

    public JogodaVelha() {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JogodaVelha.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(JogodaVelha.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(JogodaVelha.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(JogodaVelha.class.getName()).log(Level.SEVERE, null, ex);
        }

        initComponents();
        URL caminhoIcone = getClass().getResource("/imagens/ICONE.png");
        Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(caminhoIcone);
        this.setIconImage(iconeTitulo);
        desabilitarJogo();
        //txtJogador1.setDocument(new Limite_digitos(10));
        txtJogador22.setDocument(new Limite_digitos(10));
        txtJogador1.setDocument(new Limite_digitos(10));
        menuReiniciar.setEnabled(false);

        tblMatriz.setBackground(new Color(0, 0, 0, 0));
        ((DefaultTableCellRenderer) tblMatriz.getDefaultRenderer(Object.class)).setBackground(new Color(0, 0, 0, 0));
        tblMatriz.setGridColor(Color.WHITE);
        tblMatriz.setForeground(Color.WHITE);
        tblMatriz.setOpaque(false);
        ((DefaultTableCellRenderer) tblMatriz.getDefaultRenderer(Object.class)).setOpaque(false);
        tblMatriz.setShowGrid(true);

        tblMatriz.setEnabled(false);
        criarProcesso();
        MenuMatrizes.setEnabled(false);
        menuConversao.setEnabled(false);

    }

    private void txtJogador2KeyTyped(java.awt.event.KeyEvent evt) {
        char C = evt.getKeyChar();

        if (Character.isDigit(C)) {
            getToolkit().beep();
            evt.consume();
            txtJogador22.setCursor(null);
        } else if ((int) evt.getKeyChar() > 32 && (int) evt.getKeyChar() <= 47
                || (int) evt.getKeyChar() >= 58 && (int) evt.getKeyChar() <= 64
                || (int) evt.getKeyChar() >= 91 && (int) evt.getKeyChar() <= 96
                || (int) evt.getKeyChar() >= 123 && (int) evt.getKeyChar() <= 225
                || (int) evt.getKeyChar() >= 230 && (int) evt.getKeyChar() <= 255) {
            getToolkit().beep();
            evt.consume();
            txtJogador22.setCursor(null);

        }

    }

    public void criarProcesso() {
        JProgressBar jProgressBar = new JProgressBar();
        //barraProcessoX.setBounds(40, 130, 70, 14);
        barraProcessoX.setMinimum(0);
        barraProcessoX.setMaximum(100);
        barraProcessoO.setMaximum(0);
        barraProcessoO.setMaximum(100);

    }

    public void personalizarProcesso() {
        if (vitoriaX > vitoriaO) {
            barraProcessoX.setForeground(Color.GREEN);
            barraProcessoO.setForeground(Color.RED);
        } else if (vitoriaX < vitoriaO) {
            barraProcessoX.setForeground(Color.RED);
            barraProcessoO.setForeground(Color.GREEN);
        } else if (vitoriaX == vitoriaO) {
            barraProcessoX.setForeground(Color.BLUE);
            barraProcessoO.setForeground(Color.BLUE);

        }
    }

    public void setarBarraProcessso() {

        if (vitoriaX == 0) {
            barraProcessoX.setValue(0);
        } else if (vitoriaX == 1) {

            barraProcessoX.setValue(20);
        } else if (vitoriaX == 2) {
            barraProcessoX.setValue(40);
        } else if (vitoriaX == 3) {
            barraProcessoX.setValue(60);
        } else if (vitoriaX == 4) {
            barraProcessoX.setValue(80);
        } else if (vitoriaX == 5) {
            barraProcessoX.setValue(100);
        }

        if (vitoriaO == 1) {
            barraProcessoO.setValue(20);
        } else if (vitoriaO == 2) {
            barraProcessoO.setValue(40);
        } else if (vitoriaO == 3) {
            barraProcessoO.setValue(60);
        } else if (vitoriaO == 4) {
            barraProcessoO.setValue(80);
        } else if (vitoriaO == 5) {
            barraProcessoO.setValue(100);
        }

    }

    public void jogadorAtivo() {
        if (jogadorAtivo1 == true) {
            jogadorAtivo1 = false;

            jogadorAtivo2 = true;
            lblVez.setText(jogador2 + " SUA VEZ!");
        } else {
            jogadorAtivo1 = true;
            jogadorAtivo2 = false;
            lblVez.setText(jogador1 + " SUA VEZ!");
        }
    }

    public void mostrarMatriz() {
        int linha = 1;
        int lin = 0;
        DefaultTableModel Model = (DefaultTableModel) tblMatriz.getModel();
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        centralizado.setHorizontalAlignment(lin);
        for (int i = 0; i < resultado.length; i++) {
            for (int j = 0; j < 5; j++) {
                tblMatriz.getColumnModel().getColumn(j).setCellRenderer(centralizado);
                tblMatriz.setValueAt(resultado[i][j], linha, j);
            }
            linha++;
        }
    }

    public void incluirNaMatriz() {
        boolean limitador = true;
        if (contResult > 0 && contResult < 5) {
            for (int i = contResult - 1; i >= 0; i--) {
                while (limitador) {
                    if (resultado[0][i] != 0) {
                        resultado[0][contResult] = (resultado[0][i]);
                        limitador = false;
                        i = -1;
                    } else {
                        limitador = false;
                    }

                }

            }

            limitador = true;
            for (int i = contResult - 1; i >= 0; i--) {

                while (limitador) {
                    if (resultado[1][i] != 0) {
                        resultado[1][contResult] = (resultado[1][i]);
                        limitador = false;
                        i = -1;

                    } else {
                        limitador = false;
                    }
                }

            }
        }
    }

    public void limparMatriz() {
        for (int i = 0; i < resultado.length; i++) {
            for (int j = 0; j < resultado[i].length; j++) {
                resultado[i][j] = 0;
                mostrarMatriz();
            }

        }

    }

    public void vencedor() {

        contEmpate++;
        // Vitoria pela linha 1
        if (btn1.getText().equals("X") && btn2.getText().equals("X") && btn3.getText().equals("X")) {
            btn1.setBackground(new Color(255, 0, 0));
            btn2.setBackground(new Color(255, 0, 0));
            btn3.setBackground(new Color(255, 0, 0));
            mostrarMatriz();
            incluirNaMatriz();
            vitoriaX++;
            if (rodadas < 5) {
                JOptionPane.showMessageDialog(null, "     " + jogador1 + " GANHOU! \n QUE CONTINUE A BATALHA!");

            } else {
                if (vitoriaX > vitoriaO) {
                    JOptionPane.showMessageDialog(null, "     " + jogador1 + ": NOVO VENCEDOR FIVE ROUNDS!");

                } else if (vitoriaX < vitoriaO) {
                    JOptionPane.showMessageDialog(null, "     " + jogador2 + ": NOVO VENCEDOR FIVE ROUNDS!");

                } else if (vitoriaX == vitoriaO) {
                    JOptionPane.showMessageDialog(null, "    EMPATE");
                }

            }

            resultado[0][contResult] += 5;

            lblResultadoX.setText(String.valueOf(resultado[0][contResult]));
            lblResultadoO.setText(String.valueOf(resultado[1][contResult]));

            contResult++;
            novoJogo();
            contEmpate = 0;

        } else if (btn1.getText().equals("O") && btn2.getText().equals("O") && btn3.getText().equals("O")) {
            btn1.setBackground(new Color(255, 0, 0));
            btn2.setBackground(new Color(255, 0, 0));
            btn3.setBackground(new Color(255, 0, 0));
            mostrarMatriz();
            incluirNaMatriz();
            vitoriaO++;
            if (rodadas < 5) {
                JOptionPane.showMessageDialog(null, "     " + jogador2 + " GANHOU! \n QUE CONTINUE A BATALHA!");

            } else {
                if (vitoriaX > vitoriaO) {
                    JOptionPane.showMessageDialog(null, "     " + jogador1 + ": NOVO VENCEDOR FIVE ROUNDS!");

                } else if (vitoriaX < vitoriaO) {
                    JOptionPane.showMessageDialog(null, "     " + jogador2 + ": NOVO VENCEDOR FIVE ROUNDS!");

                } else if (vitoriaX == vitoriaO) {
                    JOptionPane.showMessageDialog(null, "    EMPATE");
                }
            }
            resultado[1][contResult] += 5;
            lblResultadoX.setText(String.valueOf(resultado[0][contResult]));
            lblResultadoO.setText(String.valueOf(resultado[1][contResult]));
            contResult++;

            novoJogo();
            contEmpate = 0;

            // Vitoria pela linha 2
        } else if (btn4.getText().equals("X") && btn5.getText().equals("X") && btn6.getText().equals("X")) {
            btn4.setBackground(new Color(255, 0, 0));
            btn5.setBackground(new Color(255, 0, 0));
            btn6.setBackground(new Color(255, 0, 0));
            mostrarMatriz();
            incluirNaMatriz();
            vitoriaX++;
            if (rodadas < 5) {
                JOptionPane.showMessageDialog(null, "     " + jogador1 + " GANHOU! \n QUE CONTINUE A BATALHA!");

            } else {
                if (vitoriaX > vitoriaO) {
                    JOptionPane.showMessageDialog(null, "     " + jogador1 + ": NOVO VENCEDOR FIVE ROUNDS!");

                } else if (vitoriaX < vitoriaO) {
                    JOptionPane.showMessageDialog(null, "     " + jogador2 + ": NOVO VENCEDOR FIVE ROUNDS!");

                } else if (vitoriaX == vitoriaO) {
                    JOptionPane.showMessageDialog(null, "    EMPATE");
                }
            }
            resultado[0][contResult] += 5;
            lblResultadoX.setText(String.valueOf(resultado[0][contResult]));
            lblResultadoO.setText(String.valueOf(resultado[1][contResult]));
            contResult++;

            novoJogo();
            contEmpate = 0;

        } else if (btn4.getText().equals("O") && btn5.getText().equals("O") && btn6.getText().equals("O")) {
            btn4.setBackground(new Color(255, 0, 0));
            btn5.setBackground(new Color(255, 0, 0));
            btn6.setBackground(new Color(255, 0, 0));
            mostrarMatriz();
            incluirNaMatriz();
            vitoriaO++;
            if (rodadas < 5) {
                JOptionPane.showMessageDialog(null, "     " + jogador2 + " GANHOU! \n QUE CONTINUE A BATALHA!");

            } else {
                if (vitoriaX > vitoriaO) {
                    JOptionPane.showMessageDialog(null, "     " + jogador1 + ": NOVO VENCEDOR FIVE ROUNDS!");

                } else if (vitoriaX < vitoriaO) {
                    JOptionPane.showMessageDialog(null, "     " + jogador2 + ": NOVO VENCEDOR FIVE ROUNDS!");

                } else if (vitoriaX == vitoriaO) {
                    JOptionPane.showMessageDialog(null, "    EMPATE");
                }

            }

            resultado[1][contResult] += 5;
            lblResultadoX.setText(String.valueOf(resultado[0][contResult]));
            lblResultadoO.setText(String.valueOf(resultado[1][contResult]));
            contResult++;

            novoJogo();
            contEmpate = 0;

            // Vitoria pela linha 3
        } else if (btn7.getText().equals("X") && btn8.getText().equals("X") && btn9.getText().equals("X")) {
            btn7.setBackground(new Color(255, 0, 0));
            btn8.setBackground(new Color(255, 0, 0));
            btn9.setBackground(new Color(255, 0, 0));
            mostrarMatriz();
            incluirNaMatriz();
            vitoriaX++;
            if (rodadas < 5) {
                JOptionPane.showMessageDialog(null, "     " + jogador1 + " GANHOU! \n QUE CONTINUE A BATALHA!");

            } else {
                if (vitoriaX > vitoriaO) {
                    JOptionPane.showMessageDialog(null, "     " + jogador1 + ": NOVO VENCEDOR FIVE ROUNDS!");

                } else if (vitoriaX < vitoriaO) {
                    JOptionPane.showMessageDialog(null, "     " + jogador2 + ": NOVO VENCEDOR FIVE ROUNDS!");

                } else if (vitoriaX == vitoriaO) {
                    JOptionPane.showMessageDialog(null, "    EMPATE");
                }

            }
            resultado[0][contResult] += 5;
            lblResultadoX.setText(String.valueOf(resultado[0][contResult]));
            lblResultadoO.setText(String.valueOf(resultado[1][contResult]));
            contResult++;

            novoJogo();
            contEmpate = 0;

        } else if (btn7.getText().equals("O") && btn8.getText().equals("O") && btn9.getText().equals("O")) {
            btn7.setBackground(new Color(255, 0, 0));
            btn8.setBackground(new Color(255, 0, 0));
            btn9.setBackground(new Color(255, 0, 0));
            mostrarMatriz();
            incluirNaMatriz();
            vitoriaO++;
            if (rodadas < 5) {
                JOptionPane.showMessageDialog(null, "     " + jogador2 + " GANHOU! \n QUE CONTINUE A BATALHA!");

            } else {
                if (vitoriaX > vitoriaO) {
                    JOptionPane.showMessageDialog(null, "     " + jogador1 + ": NOVO VENCEDOR FIVE ROUNDS!");

                } else if (vitoriaX < vitoriaO) {
                    JOptionPane.showMessageDialog(null, "     " + jogador2 + ": NOVO VENCEDOR FIVE ROUNDS!");

                } else if (vitoriaX == vitoriaO) {
                    JOptionPane.showMessageDialog(null, "    EMPATE");
                }

            }
            resultado[1][contResult] += 5;
            lblResultadoX.setText(String.valueOf(resultado[0][contResult]));
            lblResultadoO.setText(String.valueOf(resultado[1][contResult]));
            contResult++;

            novoJogo();
            contEmpate = 0;

        } //Vitoria Coluna1
        else if (btn1.getText().equals("X") && btn4.getText().equals("X") && btn7.getText().equals("X")) {
            btn1.setBackground(new Color(255, 0, 0));
            btn4.setBackground(new Color(255, 0, 0));
            btn7.setBackground(new Color(255, 0, 0));
            mostrarMatriz();
            incluirNaMatriz();
            vitoriaX++;
            if (rodadas < 5) {
                JOptionPane.showMessageDialog(null, "     " + jogador1 + " GANHOU! \n QUE CONTINUE A BATALHA!");

            } else {
                if (vitoriaX > vitoriaO) {
                    JOptionPane.showMessageDialog(null, "     " + jogador1 + ": NOVO VENCEDOR FIVE ROUNDS!");

                } else if (vitoriaX < vitoriaO) {
                    JOptionPane.showMessageDialog(null, "     " + jogador2 + ": NOVO VENCEDOR FIVE ROUNDS!");

                } else if (vitoriaX == vitoriaO) {
                    JOptionPane.showMessageDialog(null, "    EMPATE");
                }

            }
            resultado[0][contResult] += 5;
            lblResultadoX.setText(String.valueOf(resultado[0][contResult]));
            lblResultadoO.setText(String.valueOf(resultado[1][contResult]));
            contResult++;

            novoJogo();
            contEmpate = 0;

        } else if (btn1.getText().equals("O") && btn4.getText().equals("O") && btn7.getText().equals("O")) {
            btn1.setBackground(new Color(255, 0, 0));
            btn4.setBackground(new Color(255, 0, 0));
            btn7.setBackground(new Color(255, 0, 0));
            mostrarMatriz();
            incluirNaMatriz();
            vitoriaO++;
            if (rodadas < 5) {
                JOptionPane.showMessageDialog(null, "     " + jogador2 + " GANHOU! \n QUE CONTINUE A BATALHA!");

            } else {
                if (vitoriaX > vitoriaO) {
                    JOptionPane.showMessageDialog(null, "     " + jogador1 + ": NOVO VENCEDOR FIVE ROUNDS!");

                } else if (vitoriaX < vitoriaO) {
                    JOptionPane.showMessageDialog(null, "     " + jogador2 + ": NOVO VENCEDOR FIVE ROUNDS!");

                } else if (vitoriaX == vitoriaO) {
                    JOptionPane.showMessageDialog(null, "    EMPATE");
                }

            }
            resultado[1][contResult] += 5;
            lblResultadoX.setText(String.valueOf(resultado[0][contResult]));
            lblResultadoO.setText(String.valueOf(resultado[1][contResult]));
            contResult++;

            novoJogo();
            contEmpate = 0;

        } // Vitória pela Coluna 2
        else if (btn2.getText().equals("X") && btn5.getText().equals("X") && btn8.getText().equals("X")) {
            btn2.setBackground(new Color(255, 0, 0));
            btn5.setBackground(new Color(255, 0, 0));
            btn8.setBackground(new Color(255, 0, 0));
            mostrarMatriz();
            incluirNaMatriz();
            vitoriaX++;
            if (rodadas < 5) {
                JOptionPane.showMessageDialog(null, "     " + jogador1 + " GANHOU! \n QUE CONTINUE A BATALHA!");

            } else {
                if (vitoriaX > vitoriaO) {
                    JOptionPane.showMessageDialog(null, "     " + jogador1 + ": NOVO VENCEDOR FIVE ROUNDS!");

                } else if (vitoriaX < vitoriaO) {
                    JOptionPane.showMessageDialog(null, "     " + jogador2 + ": NOVO VENCEDOR FIVE ROUNDS!");

                } else if (vitoriaX == vitoriaO) {
                    JOptionPane.showMessageDialog(null, "    EMPATE");
                }

            }
            resultado[0][contResult] += 5;
            lblResultadoX.setText(String.valueOf(resultado[0][contResult]));
            lblResultadoO.setText(String.valueOf(resultado[1][contResult]));
            contResult++;

            novoJogo();
            contEmpate = 0;

        } else if (btn2.getText().equals("O") && btn5.getText().equals("O") && btn8.getText().equals("O")) {
            btn2.setBackground(new Color(255, 0, 0));
            btn5.setBackground(new Color(255, 0, 0));
            btn8.setBackground(new Color(255, 0, 0));
            mostrarMatriz();
            incluirNaMatriz();
            vitoriaO++;
            if (rodadas < 5) {
                JOptionPane.showMessageDialog(null, "     " + jogador2 + " GANHOU! \n QUE CONTINUE A BATALHA!");

            } else {
                if (vitoriaX > vitoriaO) {
                    JOptionPane.showMessageDialog(null, "     " + jogador1 + ": NOVO VENCEDOR FIVE ROUNDS!");

                } else if (vitoriaX < vitoriaO) {
                    JOptionPane.showMessageDialog(null, "     " + jogador2 + ": NOVO VENCEDOR FIVE ROUNDS!");

                } else if (vitoriaX == vitoriaO) {
                    JOptionPane.showMessageDialog(null, "    EMPATE");
                }

            }
            resultado[1][contResult] += 5;
            lblResultadoX.setText(String.valueOf(resultado[0][contResult]));
            lblResultadoO.setText(String.valueOf(resultado[1][contResult]));
            contResult++;

            novoJogo();
            contEmpate = 0;

        } // Vitória pela coluna 3
        else if (btn3.getText().equals("X") && btn6.getText().equals("X") && btn9.getText().equals("X")) {
            btn3.setBackground(new Color(255, 0, 0));
            btn6.setBackground(new Color(255, 0, 0));
            btn9.setBackground(new Color(255, 0, 0));
            mostrarMatriz();
            incluirNaMatriz();
            vitoriaX++;
            if (rodadas < 5) {
                JOptionPane.showMessageDialog(null, "     " + jogador1 + " GANHOU! \n QUE CONTINUE A BATALHA!");

            } else {
                if (vitoriaX > vitoriaO) {
                    JOptionPane.showMessageDialog(null, "     " + jogador1 + ": NOVO VENCEDOR FIVE ROUNDS!");

                } else if (vitoriaX < vitoriaO) {
                    JOptionPane.showMessageDialog(null, "     " + jogador2 + ": NOVO VENCEDOR FIVE ROUNDS!");

                } else if (vitoriaX == vitoriaO) {
                    JOptionPane.showMessageDialog(null, "    EMPATE");
                }

            }
            resultado[0][contResult] += 5;
            lblResultadoX.setText(String.valueOf(resultado[0][contResult]));
            lblResultadoO.setText(String.valueOf(resultado[1][contResult]));
            contResult++;

            novoJogo();
            contEmpate = 0;

        } else if (btn3.getText().equals("O") && btn6.getText().equals("O") && btn9.getText().equals("O")) {
            btn3.setBackground(new Color(255, 0, 0));
            btn6.setBackground(new Color(255, 0, 0));
            btn9.setBackground(new Color(255, 0, 0));
            mostrarMatriz();
            incluirNaMatriz();
            vitoriaO++;
            if (rodadas < 5) {
                JOptionPane.showMessageDialog(null, "     " + jogador2 + " GANHOU! \n QUE CONTINUE A BATALHA!");

            } else {
                if (vitoriaX > vitoriaO) {
                    JOptionPane.showMessageDialog(null, "     " + jogador1 + ": NOVO VENCEDOR FIVE ROUNDS!");

                } else if (vitoriaX < vitoriaO) {
                    JOptionPane.showMessageDialog(null, "     " + jogador2 + ": NOVO VENCEDOR FIVE ROUNDS!");

                } else if (vitoriaX == vitoriaO) {
                    JOptionPane.showMessageDialog(null, "    EMPATE");
                }

            }
            resultado[1][contResult] += 5;
            lblResultadoX.setText(String.valueOf(resultado[0][contResult]));
            lblResultadoO.setText(String.valueOf(resultado[1][contResult]));
            contResult++;

            novoJogo();
            contEmpate = 0;

        } //Vitória pela diagonal esquerda
        else if (btn7.getText().equals("X") && btn5.getText().equals("X") && btn3.getText().equals("X")) {
            btn7.setBackground(new Color(255, 0, 0));
            btn5.setBackground(new Color(255, 0, 0));
            btn3.setBackground(new Color(255, 0, 0));
            mostrarMatriz();
            incluirNaMatriz();
            vitoriaX++;
            if (rodadas < 5) {
                JOptionPane.showMessageDialog(null, "     " + jogador1 + " GANHOU! \n QUE CONTINUE A BATALHA!");

            } else {
                if (vitoriaX > vitoriaO) {
                    JOptionPane.showMessageDialog(null, "     " + jogador1 + ": NOVO VENCEDOR FIVE ROUNDS!");

                } else if (vitoriaX < vitoriaO) {
                    JOptionPane.showMessageDialog(null, "     " + jogador2 + ": NOVO VENCEDOR FIVE ROUNDS!");

                } else if (vitoriaX == vitoriaO) {
                    JOptionPane.showMessageDialog(null, "    EMPATE");
                }

            }
            resultado[0][contResult] += 5;
            lblResultadoX.setText(String.valueOf(resultado[0][contResult]));
            lblResultadoO.setText(String.valueOf(resultado[1][contResult]));
            contResult++;

            novoJogo();
            contEmpate = 0;

        } else if (btn7.getText().equals("O") && btn5.getText().equals("O") && btn3.getText().equals("O")) {
            btn7.setBackground(new Color(255, 0, 0));
            btn5.setBackground(new Color(255, 0, 0));
            btn3.setBackground(new Color(255, 0, 0));
            mostrarMatriz();
            incluirNaMatriz();
            vitoriaO++;
            if (rodadas < 5) {
                JOptionPane.showMessageDialog(null, "     " + jogador2 + " GANHOU! \n QUE CONTINUE A BATALHA!");

            } else {
                if (vitoriaX > vitoriaO) {
                    JOptionPane.showMessageDialog(null, "     " + jogador1 + ": NOVO VENCEDOR FIVE ROUNDS!");

                } else if (vitoriaX < vitoriaO) {
                    JOptionPane.showMessageDialog(null, "     " + jogador2 + ": NOVO VENCEDOR FIVE ROUNDS!");

                } else if (vitoriaX == vitoriaO) {
                    JOptionPane.showMessageDialog(null, "    EMPATE");
                }

            }
            resultado[1][contResult] += 5;
            lblResultadoX.setText(String.valueOf(resultado[0][contResult]));
            lblResultadoO.setText(String.valueOf(resultado[1][contResult]));
            contResult++;

            novoJogo();
            contEmpate = 0;;

        } //Vitória pela diagonal direita
        else if (btn9.getText().equals("X") && btn5.getText().equals("X") && btn1.getText().equals("X")) {
            btn9.setBackground(new Color(255, 0, 0));
            btn5.setBackground(new Color(255, 0, 0));
            btn1.setBackground(new Color(255, 0, 0));
            mostrarMatriz();
            incluirNaMatriz();
            vitoriaX++;
            if (rodadas < 5) {
                JOptionPane.showMessageDialog(null, "     " + jogador1 + " GANHOU! \n QUE CONTINUE A BATALHA!");

            } else {
                if (vitoriaX > vitoriaO) {
                    JOptionPane.showMessageDialog(null, "     " + jogador1 + ": NOVO VENCEDOR FIVE ROUNDS!");

                } else if (vitoriaX < vitoriaO) {
                    JOptionPane.showMessageDialog(null, "     " + jogador2 + ": NOVO VENCEDOR FIVE ROUNDS!");

                } else if (vitoriaX == vitoriaO) {
                    JOptionPane.showMessageDialog(null, "    EMPATE!");
                }

                btnIniciar.setEnabled(true);

            }
            resultado[0][contResult] += 5;
            lblResultadoX.setText(String.valueOf(resultado[0][contResult]));
            lblResultadoO.setText(String.valueOf(resultado[1][contResult]));
            contResult++;

            novoJogo();
            contEmpate = 0;

        } else if (btn9.getText().equals("O") && btn5.getText().equals("O") && btn1.getText().equals("O")) {
            btn9.setBackground(new Color(255, 0, 0));
            btn5.setBackground(new Color(255, 0, 0));
            btn1.setBackground(new Color(255, 0, 0));
            mostrarMatriz();
            incluirNaMatriz();
            vitoriaO++;
            if (rodadas < 5) {
                JOptionPane.showMessageDialog(null, "     " + jogador2 + " GANHOU! \n QUE CONTINUE A BATALHA!");

            } else {
                if (vitoriaX > vitoriaO) {
                    JOptionPane.showMessageDialog(null, "     " + jogador1 + ": NOVO VENCEDOR FIVE ROUNDS!");

                } else if (vitoriaX < vitoriaO) {
                    JOptionPane.showMessageDialog(null, "     " + jogador2 + ": NOVO VENCEDOR FIVE ROUNDS!");

                } else if (vitoriaX == vitoriaO) {
                    JOptionPane.showMessageDialog(null, "    EMPATE!");
                }

            }
            resultado[1][contResult] += 5;
            lblResultadoX.setText(String.valueOf(resultado[0][contResult]));
            lblResultadoO.setText(String.valueOf(resultado[1][contResult]));
            contResult++;

            novoJogo();
            contEmpate = 0;

        }
        //Empate
        if (contEmpate == 9) {
            vitoriaX++;
            vitoriaO++;
            mostrarMatriz();
            numerosDeEmpate++;
            incluirNaMatriz();

            if (rodadas < 5) {
                JOptionPane.showMessageDialog(null, "       VELHA!");
            } else {
                if (vitoriaX > vitoriaO) {
                    JOptionPane.showMessageDialog(null, "     " + jogador1 + ": NOVO VENCEDOR FIVE ROUNDS!");

                } else if (vitoriaX < vitoriaO) {
                    JOptionPane.showMessageDialog(null, "     " + jogador2 + ": NOVO VENCEDOR FIVE ROUNDS!");

                } else if (vitoriaX == vitoriaO) {
                    JOptionPane.showMessageDialog(null, "                 EMPATE! \n"
                            + "SEM VENCEDOR FIVE ROUNDS!\"");
                }
            }

            resultado[0][contResult] += 2;
            resultado[1][contResult] += 2;
            lblResultadoX.setText(String.valueOf(resultado[0][contResult]));
            lblResultadoO.setText(String.valueOf(resultado[1][contResult]));

            contResult++;

            novoJogo();
            contEmpate = 0;
            menuNovoJogo.setEnabled(true);

        }

    }

    public void novoJogo() {
        rodadas++;
        mostrarMatriz();
        btn1.setText("");
        btn2.setText("");
        btn3.setText("");
        btn4.setText("");
        btn5.setText("");
        btn6.setText("");
        btn7.setText("");
        btn8.setText("");
        btn9.setText("");
        btn1.setBackground(new Color(0, 255, 255));
        btn2.setBackground(new Color(0, 255, 255));
        btn3.setBackground(new Color(0, 255, 255));
        btn4.setBackground(new Color(0, 255, 255));
        btn5.setBackground(new Color(0, 255, 255));
        btn6.setBackground(new Color(0, 255, 255));
        btn7.setBackground(new Color(0, 255, 255));
        btn8.setBackground(new Color(0, 255, 255));
        btn9.setBackground(new Color(0, 255, 255));
        if (rodadas <= 5) {
            lblRound.setText(String.valueOf(rodadas));
            personalizarProcesso();
            setarBarraProcessso();
        } else if (rodadas > 5) {
            if (vitoriaX > vitoriaO) {
                //  JOptionPane.showMessageDialog(null, jogador1 + "VENCEU. \n"
                //        + "    PARABENS!");
                desabilitarJogo();
                btnIniciar.setEnabled(true);

                //menuReiniciar.setEnabled(false);
                MenuMatrizes.setEnabled(true);
                menuConversao.setEnabled(true);
                txtJogador1.setEditable(true);
                txtJogador22.setEditable(true);
                menuReiniciar.setEnabled(false);
            } else if (vitoriaX < vitoriaO) {

                desabilitarJogo();
                btnIniciar.setEnabled(true);

                //menuReiniciar.setEnabled(false);
                MenuMatrizes.setEnabled(true);
                menuConversao.setEnabled(true);
                txtJogador1.setEditable(true);
                txtJogador22.setEditable(true);
                menuReiniciar.setEnabled(false);
            }
            MenuMatrizes.setEnabled(true);
            menuConversao.setEnabled(true);
            lblVez.setText(" ");
            personalizarProcesso();
            if (vitoriaX > vitoriaO) {
                barraProcessoX.setValue(100);
            } else if (vitoriaO > vitoriaX) {
                barraProcessoO.setValue(100);
            } else if (vitoriaX == vitoriaO) {
                barraProcessoX.setValue(100);
                barraProcessoO.setValue(100);
            }
        }
    }

    public void desabilitarJogo() {
        btn1.setEnabled(false);
        btn2.setEnabled(false);
        btn3.setEnabled(false);
        btn4.setEnabled(false);
        btn5.setEnabled(false);
        btn6.setEnabled(false);
        btn7.setEnabled(false);
        btn8.setEnabled(false);
        btn9.setEnabled(false);

    }

    public void habilitarJogo() {
        btn1.setEnabled(true);
        btn2.setEnabled(true);
        btn3.setEnabled(true);
        btn4.setEnabled(true);
        btn5.setEnabled(true);
        btn6.setEnabled(true);
        btn7.setEnabled(true);
        btn8.setEnabled(true);
        btn9.setEnabled(true);
    }

    public void definir() {

        jogador1 = txtJogador1.getText();
        jogador2 = txtJogador22.getText();

    }

    public void sorteio() {
        int num;
        Random gerador = new Random(System.currentTimeMillis());
        num = gerador.nextInt();

        if (num % 2 == 0) {
            jogadorAtivo1 = true;
            jogadorAtivo2 = false;
            JOptionPane.showMessageDialog(null, jogador1 + " VOCÊ INICIA!");

            lblVez.setText(jogador1 + " SUA VEZ!");
        } else {
            jogadorAtivo2 = true;
            jogadorAtivo1 = false;
            JOptionPane.showMessageDialog(null, jogador2 + " VOCÊ INICIA!");

            lblVez.setText(jogador2 + " SUA VEZ!");
        }
        btnIniciar.setEnabled(false);
        txtJogador1.setEditable(false);
        txtJogador22.setEditable(false);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlPrincipal = new javax.swing.JPanel();
        lblVez = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtJogador1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnIniciar = new javax.swing.JButton();
        txtJogador22 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        barraProcessoO = new javax.swing.JProgressBar();
        jLabel6 = new javax.swing.JLabel();
        lblJogadorX = new javax.swing.JLabel();
        lblJogadorO = new javax.swing.JLabel();
        lblResultadoX = new javax.swing.JLabel();
        lblResultadoO = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblRound = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        barraProcessoX = new javax.swing.JProgressBar();
        jPanel2 = new javax.swing.JPanel();
        tblMatriz = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        pnlJogo = new javax.swing.JPanel();
        btn1 = new javax.swing.JButton();
        btn2 = new javax.swing.JButton();
        btn3 = new javax.swing.JButton();
        btn4 = new javax.swing.JButton();
        btn5 = new javax.swing.JButton();
        btn6 = new javax.swing.JButton();
        btn7 = new javax.swing.JButton();
        btn8 = new javax.swing.JButton();
        btn9 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        imagenfive = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        imagendefunco = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuArquivo = new javax.swing.JMenu();
        menuNovoJogo = new javax.swing.JMenuItem();
        menuReiniciar = new javax.swing.JMenuItem();
        menuSair = new javax.swing.JMenuItem();
        menuCalculos = new javax.swing.JMenu();
        MenuMatrizes = new javax.swing.JMenuItem();
        menuConversao = new javax.swing.JMenuItem();
        menuAjuda = new javax.swing.JMenu();
        menuSobre = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("FIVE ROUNDS");
        setBackground(new java.awt.Color(153, 0, 153));
        setName("Jogo da Velho"); // NOI18N
        setPreferredSize(new java.awt.Dimension(800, 600));
        setResizable(false);
        getContentPane().setLayout(null);

        pnlPrincipal.setBackground(new java.awt.Color(255, 255, 255));
        pnlPrincipal.setForeground(new java.awt.Color(0, 94, 242));
        pnlPrincipal.setPreferredSize(new java.awt.Dimension(800, 700));
        pnlPrincipal.setLayout(null);

        lblVez.setBackground(new java.awt.Color(255, 255, 255));
        lblVez.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        lblVez.setForeground(new java.awt.Color(255, 255, 255));
        lblVez.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pnlPrincipal.add(lblVez);
        lblVez.setBounds(80, 170, 220, 30);

        jPanel4.setBackground(new java.awt.Color(111, 162, 244));
        jPanel4.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 255, 255)));
        jPanel4.setOpaque(false);
        jPanel4.setLayout(null);

        jLabel4.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("JOGADOR O:");
        jPanel4.add(jLabel4);
        jLabel4.setBounds(10, 50, 110, 21);

        txtJogador1.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        txtJogador1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtJogador1ActionPerformed(evt);
            }
        });
        txtJogador1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtJogador1KeyTyped(evt);
            }
        });
        jPanel4.add(txtJogador1);
        txtJogador1.setBounds(130, 10, 189, 30);

        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("JOGADOR X:");
        jPanel4.add(jLabel1);
        jLabel1.setBounds(10, 10, 110, 21);

        btnIniciar.setFont(new java.awt.Font("Arial Black", 0, 11)); // NOI18N
        btnIniciar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/joystick_game_3819 (1).png"))); // NOI18N
        btnIniciar.setOpaque(false);
        btnIniciar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnIniciar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });
        jPanel4.add(btnIniciar);
        btnIniciar.setBounds(130, 90, 80, 30);

        txtJogador22.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        txtJogador22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtJogador22ActionPerformed(evt);
            }
        });
        txtJogador22.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtJogador22KeyTyped(evt);
            }
        });
        jPanel4.add(txtJogador22);
        txtJogador22.setBounds(130, 50, 189, 30);

        pnlPrincipal.add(jPanel4);
        jPanel4.setBounds(10, 20, 330, 130);

        jPanel1.setBackground(new java.awt.Color(111, 162, 244));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 255, 255)));
        jPanel1.setOpaque(false);
        jPanel1.setLayout(null);
        jPanel1.add(barraProcessoO);
        barraProcessoO.setBounds(160, 100, 80, 14);

        jLabel6.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("PONTUAÇÃO");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(10, 30, 110, 21);

        lblJogadorX.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        lblJogadorX.setForeground(new java.awt.Color(255, 255, 255));
        lblJogadorX.setText("JOGADOR ");
        lblJogadorX.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel1.add(lblJogadorX);
        lblJogadorX.setBounds(10, 60, 130, 20);

        lblJogadorO.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        lblJogadorO.setForeground(new java.awt.Color(255, 255, 255));
        lblJogadorO.setText("JOGADOR ");
        lblJogadorO.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel1.add(lblJogadorO);
        lblJogadorO.setBounds(10, 100, 130, 20);

        lblResultadoX.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        lblResultadoX.setForeground(new java.awt.Color(255, 255, 255));
        lblResultadoX.setText("0");
        lblResultadoX.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lblResultadoX.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel1.add(lblResultadoX);
        lblResultadoX.setBounds(160, 40, 20, 20);

        lblResultadoO.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        lblResultadoO.setForeground(new java.awt.Color(255, 255, 255));
        lblResultadoO.setText("0");
        lblResultadoO.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel1.add(lblResultadoO);
        lblResultadoO.setBounds(160, 80, 30, 20);

        jLabel2.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("ROUND:");
        jLabel2.setToolTipText("");
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel1.add(jLabel2);
        jLabel2.setBounds(10, 0, 90, 20);

        lblRound.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblRound.setForeground(new java.awt.Color(255, 255, 255));
        lblRound.setText("0");
        lblRound.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel1.add(lblRound);
        lblRound.setBounds(100, 0, 41, 20);

        jLabel8.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("X:");
        jLabel8.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel1.add(jLabel8);
        jLabel8.setBounds(140, 60, 20, 20);

        jLabel9.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("O:");
        jLabel9.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel1.add(jLabel9);
        jLabel9.setBounds(140, 100, 20, 20);
        jPanel1.add(barraProcessoX);
        barraProcessoX.setBounds(160, 60, 80, 14);

        pnlPrincipal.add(jPanel1);
        jPanel1.setBounds(520, 20, 260, 130);

        jPanel2.setOpaque(false);
        jPanel2.setLayout(null);

        tblMatriz.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        tblMatriz.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "1º Raund", "2º Raund", "3º Raund", "4º Raund", "5º Raund"
            }
        ));
        tblMatriz.setGridColor(new java.awt.Color(255, 255, 255));
        tblMatriz.setMinimumSize(new java.awt.Dimension(75, 80));
        jPanel2.add(tblMatriz);
        tblMatriz.setBounds(20, 50, 400, 60);

        jLabel7.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("X");
        jPanel2.add(jLabel7);
        jLabel7.setBounds(0, 60, 20, 21);

        jLabel5.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("O");
        jPanel2.add(jLabel5);
        jLabel5.setBounds(0, 80, 20, 21);

        jLabel3.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("5ºROUND");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(340, 40, 80, 21);

        jLabel10.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("PLACAR");
        jPanel2.add(jLabel10);
        jLabel10.setBounds(20, 20, 80, 21);

        jLabel11.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("1ºROUND");
        jPanel2.add(jLabel11);
        jLabel11.setBounds(20, 40, 80, 21);

        jLabel12.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("2ºROUND");
        jPanel2.add(jLabel12);
        jLabel12.setBounds(100, 40, 80, 21);

        jLabel13.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("3ºROUND");
        jPanel2.add(jLabel13);
        jLabel13.setBounds(180, 40, 80, 21);

        jLabel14.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("4ºROUND");
        jPanel2.add(jLabel14);
        jLabel14.setBounds(260, 40, 80, 21);

        pnlPrincipal.add(jPanel2);
        jPanel2.setBounds(360, 430, 440, 110);

        pnlJogo.setBackground(new java.awt.Color(45, 77, 242));
        pnlJogo.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 255, 255)));
        pnlJogo.setToolTipText("");
        pnlJogo.setOpaque(false);
        pnlJogo.setLayout(null);

        btn1.setBackground(new java.awt.Color(0, 255, 255));
        btn1.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1ActionPerformed(evt);
            }
        });
        pnlJogo.add(btn1);
        btn1.setBounds(11, 12, 95, 95);

        btn2.setBackground(new java.awt.Color(0, 255, 255));
        btn2.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn2ActionPerformed(evt);
            }
        });
        pnlJogo.add(btn2);
        btn2.setBounds(120, 10, 95, 95);

        btn3.setBackground(new java.awt.Color(0, 255, 255));
        btn3.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        btn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn3ActionPerformed(evt);
            }
        });
        pnlJogo.add(btn3);
        btn3.setBounds(230, 10, 95, 95);

        btn4.setBackground(new java.awt.Color(0, 255, 255));
        btn4.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        btn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn4ActionPerformed(evt);
            }
        });
        pnlJogo.add(btn4);
        btn4.setBounds(10, 120, 95, 95);

        btn5.setBackground(new java.awt.Color(0, 255, 255));
        btn5.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        btn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn5ActionPerformed(evt);
            }
        });
        pnlJogo.add(btn5);
        btn5.setBounds(120, 120, 95, 95);

        btn6.setBackground(new java.awt.Color(0, 255, 255));
        btn6.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        btn6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn6ActionPerformed(evt);
            }
        });
        pnlJogo.add(btn6);
        btn6.setBounds(230, 120, 95, 95);

        btn7.setBackground(new java.awt.Color(0, 255, 255));
        btn7.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        btn7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn7ActionPerformed(evt);
            }
        });
        pnlJogo.add(btn7);
        btn7.setBounds(10, 230, 95, 95);

        btn8.setBackground(new java.awt.Color(0, 255, 255));
        btn8.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        btn8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn8ActionPerformed(evt);
            }
        });
        pnlJogo.add(btn8);
        btn8.setBounds(120, 230, 95, 95);

        btn9.setBackground(new java.awt.Color(0, 255, 255));
        btn9.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        btn9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn9ActionPerformed(evt);
            }
        });
        pnlJogo.add(btn9);
        btn9.setBounds(230, 230, 95, 95);

        pnlPrincipal.add(pnlJogo);
        pnlJogo.setBounds(10, 210, 340, 330);
        pnlJogo.getAccessibleContext().setAccessibleName("JOGO DA VELHA");

        jLabel16.setBackground(new java.awt.Color(255, 0, 0));
        jLabel16.setFont(new java.awt.Font("DialogInput", 3, 48)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 0, 0));
        jLabel16.setText("Five Rounds");
        pnlPrincipal.add(jLabel16);
        jLabel16.setBounds(470, 150, 340, 60);

        imagenfive.setBackground(new java.awt.Color(255, 0, 0));
        imagenfive.setFont(new java.awt.Font("Segoe Print", 1, 24)); // NOI18N
        imagenfive.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/ICONE (1).png"))); // NOI18N
        pnlPrincipal.add(imagenfive);
        imagenfive.setBounds(370, 90, 130, 120);

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/gif.gif"))); // NOI18N
        jLabel15.setText("jLabel15");
        pnlPrincipal.add(jLabel15);
        jLabel15.setBounds(400, 210, 360, 230);

        imagendefunco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/cropped-800-600-913942.jpg"))); // NOI18N
        pnlPrincipal.add(imagendefunco);
        imagendefunco.setBounds(0, 10, 800, 540);

        getContentPane().add(pnlPrincipal);
        pnlPrincipal.setBounds(0, -10, 1230, 850);

        menuArquivo.setText("Arquivo");

        menuNovoJogo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, java.awt.event.InputEvent.SHIFT_MASK));
        menuNovoJogo.setText("Novo Jogo");
        menuNovoJogo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuNovoJogoActionPerformed(evt);
            }
        });
        menuArquivo.add(menuNovoJogo);

        menuReiniciar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.SHIFT_MASK));
        menuReiniciar.setText("Reiniciar");
        menuReiniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuReiniciarActionPerformed(evt);
            }
        });
        menuArquivo.add(menuReiniciar);

        menuSair.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, java.awt.event.InputEvent.SHIFT_MASK));
        menuSair.setText("Sair");
        menuSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSairActionPerformed(evt);
            }
        });
        menuArquivo.add(menuSair);

        jMenuBar1.add(menuArquivo);

        menuCalculos.setText("Cálculos");
        menuCalculos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCalculosActionPerformed(evt);
            }
        });

        MenuMatrizes.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_1, java.awt.event.InputEvent.CTRL_MASK));
        MenuMatrizes.setText("Matrizes");
        MenuMatrizes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuMatrizesActionPerformed(evt);
            }
        });
        menuCalculos.add(MenuMatrizes);

        menuConversao.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_2, java.awt.event.InputEvent.CTRL_MASK));
        menuConversao.setText("Conversões");
        menuConversao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuConversaoActionPerformed(evt);
            }
        });
        menuCalculos.add(menuConversao);

        jMenuBar1.add(menuCalculos);

        menuAjuda.setText("Ajuda");

        menuSobre.setText("Sobre");
        menuSobre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSobreActionPerformed(evt);
            }
        });
        menuAjuda.add(menuSobre);

        jMenuBar1.add(menuAjuda);

        setJMenuBar(jMenuBar1);

        getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn9ActionPerformed
        if (jogadorAtivo1 == true) {
            if (btn9.getText().equals("")) {
                btn9.setText("X");
                jogadorAtivo();
                vencedor();
            }
        } else {
            if (btn9.getText().equals("")) {
                btn9.setText("O");
                jogadorAtivo();
                vencedor();
            }
        }
    }//GEN-LAST:event_btn9ActionPerformed

    private void btn8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn8ActionPerformed
        if (jogadorAtivo1 == true) {
            if (btn8.getText().equals("")) {
                btn8.setText("X");
                jogadorAtivo();
                vencedor();
            }
        } else {
            if (btn8.getText().equals("")) {
                btn8.setText("O");
                jogadorAtivo();
                vencedor();
            }
        }
    }//GEN-LAST:event_btn8ActionPerformed

    private void btn7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn7ActionPerformed
        if (jogadorAtivo1 == true) {
            if (btn7.getText().equals("")) {
                btn7.setText("X");
                jogadorAtivo();
                vencedor();
            }
        } else {
            if (btn7.getText().equals("")) {
                btn7.setText("O");
                jogadorAtivo();
                vencedor();
            }
        }
    }//GEN-LAST:event_btn7ActionPerformed

    private void btn6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn6ActionPerformed
        if (jogadorAtivo1 == true) {
            if (btn6.getText().equals("")) {
                btn6.setText("X");
                jogadorAtivo();
                vencedor();
            }
        } else {
            if (btn6.getText().equals("")) {
                btn6.setText("O");
                jogadorAtivo();
                vencedor();
            }
        }
    }//GEN-LAST:event_btn6ActionPerformed

    private void btn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn5ActionPerformed
        if (jogadorAtivo1 == true) {
            if (btn5.getText().equals("")) {
                btn5.setText("X");
                jogadorAtivo();
                vencedor();
            }
        } else {
            if (btn5.getText().equals("")) {
                btn5.setText("O");
                jogadorAtivo();
                vencedor();
            }
        }
    }//GEN-LAST:event_btn5ActionPerformed

    private void btn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn4ActionPerformed
        if (jogadorAtivo1 == true) {
            if (btn4.getText().equals("")) {
                btn4.setText("X");
                jogadorAtivo();
                vencedor();
            }
        } else {
            if (btn4.getText().equals("")) {
                btn4.setText("O");
                jogadorAtivo();
                vencedor();
            }
        }
    }//GEN-LAST:event_btn4ActionPerformed

    private void btn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn3ActionPerformed
        if (jogadorAtivo1 == true) {
            if (btn3.getText().equals("")) {
                btn3.setText("X");
                jogadorAtivo();
                vencedor();
            }
        } else {
            if (btn3.getText().equals("")) {
                btn3.setText("O");
                jogadorAtivo();
                vencedor();
            }
        }
    }//GEN-LAST:event_btn3ActionPerformed

    private void btn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn2ActionPerformed
        if (jogadorAtivo1 == true) {
            if (btn2.getText().equals("")) {
                btn2.setText("X");
                jogadorAtivo();
                vencedor();
            }
        } else {
            if (btn2.getText().equals("")) {
                btn2.setText("O");
                jogadorAtivo();
                vencedor();
            }
        }
    }//GEN-LAST:event_btn2ActionPerformed

    private void btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1ActionPerformed
        if (jogadorAtivo1 == true) {
            if (btn1.getText().equals("")) {
                btn1.setText("X");
                jogadorAtivo();
                vencedor();
                System.out.println("jogador um true ");
            }
        } else {
            if (btn1.getText().equals("")) {
                btn1.setText("O");
                jogadorAtivo();
                vencedor();
            }
        }
    }//GEN-LAST:event_btn1ActionPerformed

    private void txtJogador22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtJogador22ActionPerformed

    }//GEN-LAST:event_txtJogador22ActionPerformed

    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarActionPerformed

        vitoriaO = 0;
        vitoriaX = 0;
        setarBarraProcessso();
        barraProcessoX.setValue(0);
        barraProcessoO.setValue(0);
        menuReiniciar.setEnabled(true);
        limparMatriz();
        if (txtJogador1.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "O CAMPO \"JOGADOR X\" ESTÁ VAZIO!");

        } else if (txtJogador22.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "O CAMPO \"JOGADOR O\" ESTÁ VAZIO!");
        } else if (txtJogador1.getText().equals(txtJogador22.getText())) {
            JOptionPane.showMessageDialog(null, "OS CAMPOS \"JOGADOR X\"   E    \"JOGADOR O\"\n          NÃO PODEM SER IGUAIS!");
        } else if (!(txtJogador1.getText().equals("") && txtJogador22.getText().equals(""))) {
            rodadas = 1;
            contResult = 0;
            limparMatriz();
            definir();
            sorteio();
            habilitarJogo();
            lblRound.setText(String.valueOf(rodadas));
            lblJogadorX.setText(jogador1);
            lblJogadorO.setText(jogador2);
            lblResultadoX.setText(String.valueOf(resultado[0][contResult]));
            lblResultadoO.setText(String.valueOf(resultado[1][contResult]));
            txtJogador1.setEditable(false);
            txtJogador22.setEditable(false);
            MenuMatrizes.setEnabled(false);
            menuConversao.setEnabled(false);

        }

    }//GEN-LAST:event_btnIniciarActionPerformed

    private void txtJogador1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtJogador1ActionPerformed

    }//GEN-LAST:event_txtJogador1ActionPerformed

    private void MenuMatrizesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuMatrizesActionPerformed

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 5; j++) {
                matrizResultado[i][j] = resultado[i][j];
            }
        }
        Matrizes frame = new Matrizes(matrizResultado);
        frame.setVisible(true);


    }//GEN-LAST:event_MenuMatrizesActionPerformed

    private void menuCalculosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCalculosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuCalculosActionPerformed

    private void menuNovoJogoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuNovoJogoActionPerformed

        novoJogo();

        rodadas = 0;
        vitoriaO = 0;
        vitoriaX = 0;
        contEmpate = 0;
        barraProcessoX.setValue(0);
        barraProcessoO.setValue(0);
        limparMatriz();
        mostrarMatriz();
        contResult = 0;
        txtJogador1.setEditable(true);
        txtJogador22.setEditable(true);
        txtJogador1.setText("");
        txtJogador22.setText("");

        lblRound.setText(String.valueOf(rodadas));
        lblResultadoX.setText(String.valueOf(resultado[0][0]));
        lblResultadoO.setText(String.valueOf(resultado[1][0]));
        desabilitarJogo();
        btnIniciar.setEnabled(true);
        MenuMatrizes.setEnabled(false);
        menuConversao.setEnabled(false);
        lblVez.setText("");


    }//GEN-LAST:event_menuNovoJogoActionPerformed

    private void menuReiniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuReiniciarActionPerformed

        personalizarProcesso();
        contEmpate = 0;

        contResult = 0;
        limparMatriz();
        sorteio();
        habilitarJogo();

        lblJogadorX.setText(jogador1);
        lblJogadorO.setText(jogador2);
        lblResultadoX.setText(String.valueOf(resultado[0][contResult]));
        lblResultadoO.setText(String.valueOf(resultado[1][contResult]));
        novoJogo();
        setarBarraProcessso();
        rodadas = 1;
        lblRound.setText(String.valueOf(rodadas));
        vitoriaO = 0;
        vitoriaX = 0;
        barraProcessoX.setValue(0);
        barraProcessoO.setValue(0);


    }//GEN-LAST:event_menuReiniciarActionPerformed

    private void menuSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSairActionPerformed
        dispose();
    }//GEN-LAST:event_menuSairActionPerformed

    private void menuConversaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuConversaoActionPerformed
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 5; j++) {
                matrizResultado[i][j] = resultado[i][j];
            }
        }
        Conversoes frame = new Conversoes(matrizResultado);
        frame.setVisible(true);
    }//GEN-LAST:event_menuConversaoActionPerformed

    private void txtJogador1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtJogador1KeyTyped
        char C = evt.getKeyChar();

        if (Character.isDigit(C)) {
            getToolkit().beep();
            evt.consume();
            txtJogador1.setCursor(null);
        } else if ((int) evt.getKeyChar() >= 32 && (int) evt.getKeyChar() <= 47
                || (int) evt.getKeyChar() >= 58 && (int) evt.getKeyChar() <= 64
                || (int) evt.getKeyChar() >= 91 && (int) evt.getKeyChar() <= 96
                || (int) evt.getKeyChar() >= 123 && (int) evt.getKeyChar() <= 225
                || (int) evt.getKeyChar() >= 230 && (int) evt.getKeyChar() <= 255) {
            getToolkit().beep();
            evt.consume();
            txtJogador1.setCursor(null);

        }
    }//GEN-LAST:event_txtJogador1KeyTyped

    private void txtJogador22KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtJogador22KeyTyped
        char C = evt.getKeyChar();

        if (Character.isDigit(C)) {
            getToolkit().beep();
            evt.consume();
            txtJogador22.setCursor(null);
        } else if ((int) evt.getKeyChar() >= 32 && (int) evt.getKeyChar() <= 47
                || (int) evt.getKeyChar() >= 58 && (int) evt.getKeyChar() <= 64
                || (int) evt.getKeyChar() >= 91 && (int) evt.getKeyChar() <= 96
                || (int) evt.getKeyChar() >= 123 && (int) evt.getKeyChar() <= 225
                || (int) evt.getKeyChar() >= 230 && (int) evt.getKeyChar() <= 255) {
            getToolkit().beep();
            evt.consume();
            txtJogador22.setCursor(null);

        }
    }//GEN-LAST:event_txtJogador22KeyTyped

    private void menuSobreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSobreActionPerformed
        JOptionPane.showMessageDialog(null, "\n                                                               FIVE ROUNDS \n"
                + "\n\n"
                + " É um jogo da velha criado com intuito de entreter os jogadores  e fazer com que eles tenham interação\n "
                + " com matrizes e bases numéricas.\n"
                + "\n"
                + "REGRAS DO JOGO \n"
                + "\n"
                + "O jogo é formado por dois jogadores, onde um corresponde ao “Jogador X” e o outro ao “Jogador O”.\n"
                + "\n"
                + "Para iniciar o jogo, é preciso informar o nome dos jogadores nos campos “jogador X” \n"
                + " e \"jogador O” no canto superior esquerdo da tela. \n"
                + "\n"
                + "Em seguida, clique no botão logo abaixo dos campos, para que o jogo seja iniciado.\n"
                + " O sistema sorteará automaticamente e dirá quem irá começar. \n"
                + "\n"
                + "Após o jogador marcar a posição que deseja, passará a vez para o próximo jogador. Ganhará\n"
                + "o primeiro que conseguir 3 símbolos em uma linha, podendo ser na vertical, horizontal\n"
                + "ou diagonal. Caso nenhum jogador consiga, dará empate.\n"
                + "\n"
                + "O sistema dirá quem ganhou a rodada, e após cinco rodadas ele informará quem venceu a partida\n"
                + "ou houve empate. Assim a opção “Cálculos, Matrizes” no menu iniciar será ativada,\n"
                + "para que o jogador possa fazer os cálculos  com os resultados do jogo e gerar\n"
                + "matrizes como desejar. \n"
                + "\n"
                + "Também será liberada a opção “Cálculos, Conversões” onde o jogador poderá converter\n"
                + "em outras bases numéricas a soma dos resultados da matriz que foi gerada pelo placar.\n"
                + " \n"
                + "\n");
    }//GEN-LAST:event_menuSobreActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JogodaVelha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JogodaVelha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JogodaVelha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JogodaVelha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JogodaVelha().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem MenuMatrizes;
    private javax.swing.JProgressBar barraProcessoO;
    private javax.swing.JProgressBar barraProcessoX;
    private javax.swing.JButton btn1;
    private javax.swing.JButton btn2;
    private javax.swing.JButton btn3;
    private javax.swing.JButton btn4;
    private javax.swing.JButton btn5;
    private javax.swing.JButton btn6;
    private javax.swing.JButton btn7;
    private javax.swing.JButton btn8;
    private javax.swing.JButton btn9;
    private javax.swing.JButton btnIniciar;
    private javax.swing.JLabel imagendefunco;
    private javax.swing.JLabel imagenfive;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lblJogadorO;
    private javax.swing.JLabel lblJogadorX;
    private javax.swing.JLabel lblResultadoO;
    private javax.swing.JLabel lblResultadoX;
    private javax.swing.JLabel lblRound;
    private javax.swing.JLabel lblVez;
    private javax.swing.JMenu menuAjuda;
    private javax.swing.JMenu menuArquivo;
    private javax.swing.JMenu menuCalculos;
    private javax.swing.JMenuItem menuConversao;
    private javax.swing.JMenuItem menuNovoJogo;
    private javax.swing.JMenuItem menuReiniciar;
    private javax.swing.JMenuItem menuSair;
    private javax.swing.JMenuItem menuSobre;
    private javax.swing.JPanel pnlJogo;
    private javax.swing.JPanel pnlPrincipal;
    private javax.swing.JTable tblMatriz;
    private javax.swing.JTextField txtJogador1;
    private javax.swing.JTextField txtJogador22;
    // End of variables declaration//GEN-END:variables

    private void paint(String g) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void setMaximumSize(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void barraProgresso(Object put) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
