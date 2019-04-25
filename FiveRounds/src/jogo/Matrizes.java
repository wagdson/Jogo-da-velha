/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author heber
 */
public class Matrizes extends javax.swing.JFrame {
    JogodaVelha jogodavelha = new JogodaVelha();

    int[][] matriz = new int[2][5];
    int[][] matrizResultado = new int[2][5];
    int[][] aCopia = new int[2][5];
    int[][] bInvertida = new int[2][5];
    int[][] cTransposta = new int[5][2];
    int[][] dAdicao = new int[2][5];
    int[][] eSubtracao = new int[2][5];
    int[][] fMutiplicacao = new int[2][2];
    /**
     * Creates new form atrizes
     */
    public Matrizes(java.awt.Frame parent, boolean modal) {
       // super(parent, modal);
        
        
       initComponents();
       
  

    }

    public Matrizes(int[][] matriz) {
        
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 5; j++) {
                matrizResultado[i][j] = matriz[i][j];
            }
        }
        calcularMatriz();
        initComponents();
             URL caminhoIcone = getClass().getResource("/imagens/ICONE.png");
        Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(caminhoIcone);
        this.setIconImage(iconeTitulo);
        btnLimpar.setEnabled(false);

       btrMatrizResultado.setSelected(true);
       setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
       tblResultados.setEnabled(false);
       
      
     
       
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btrMatrizResultado = new javax.swing.JRadioButton();
        btrMatrizA = new javax.swing.JRadioButton();
        btrMatrizC = new javax.swing.JRadioButton();
        btrMatrizB = new javax.swing.JRadioButton();
        btrMatrizD = new javax.swing.JRadioButton();
        btrMatrizE = new javax.swing.JRadioButton();
        btrMatrizF = new javax.swing.JRadioButton();
        btnImprimir = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        tblResultados = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        fundo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MATRIZES");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setToolTipText("Cálculos com Matrizes");
        jPanel1.setPreferredSize(new java.awt.Dimension(700, 500));
        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(102, 255, 255)));
        jPanel2.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel1.setText("OPÇÃO DE MATRIZES");
        jPanel2.add(jLabel1);
        jLabel1.setBounds(30, 10, 220, 21);

        buttonGroup1.add(btrMatrizResultado);
        btrMatrizResultado.setFont(new java.awt.Font("Arial Black", 1, 11)); // NOI18N
        btrMatrizResultado.setText("MATRIZ RESULTADO");
        btrMatrizResultado.setOpaque(false);
        btrMatrizResultado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btrMatrizResultadoActionPerformed(evt);
            }
        });
        jPanel2.add(btrMatrizResultado);
        btrMatrizResultado.setBounds(30, 30, 180, 25);

        buttonGroup1.add(btrMatrizA);
        btrMatrizA.setFont(new java.awt.Font("Arial Black", 1, 11)); // NOI18N
        btrMatrizA.setText("MATRIZ  A (CÓPIA)");
        btrMatrizA.setOpaque(false);
        btrMatrizA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btrMatrizAActionPerformed(evt);
            }
        });
        jPanel2.add(btrMatrizA);
        btrMatrizA.setBounds(30, 60, 160, 25);

        buttonGroup1.add(btrMatrizC);
        btrMatrizC.setFont(new java.awt.Font("Arial Black", 1, 11)); // NOI18N
        btrMatrizC.setText("MATRIZ C (TRANSPOSTA)");
        btrMatrizC.setOpaque(false);
        jPanel2.add(btrMatrizC);
        btrMatrizC.setBounds(30, 120, 210, 25);

        buttonGroup1.add(btrMatrizB);
        btrMatrizB.setFont(new java.awt.Font("Arial Black", 1, 11)); // NOI18N
        btrMatrizB.setText("MATRIZ B (VALORES INVERSOS)");
        btrMatrizB.setOpaque(false);
        btrMatrizB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btrMatrizBActionPerformed(evt);
            }
        });
        jPanel2.add(btrMatrizB);
        btrMatrizB.setBounds(30, 90, 260, 25);

        buttonGroup1.add(btrMatrizD);
        btrMatrizD.setFont(new java.awt.Font("Arial Black", 1, 11)); // NOI18N
        btrMatrizD.setText("MATRIZ D (ADIÇÃO)");
        btrMatrizD.setOpaque(false);
        btrMatrizD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btrMatrizDActionPerformed(evt);
            }
        });
        jPanel2.add(btrMatrizD);
        btrMatrizD.setBounds(400, 30, 170, 25);

        buttonGroup1.add(btrMatrizE);
        btrMatrizE.setFont(new java.awt.Font("Arial Black", 1, 11)); // NOI18N
        btrMatrizE.setText("MATRIZ E (SUBTRAÇÃO)");
        btrMatrizE.setOpaque(false);
        btrMatrizE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btrMatrizEActionPerformed(evt);
            }
        });
        jPanel2.add(btrMatrizE);
        btrMatrizE.setBounds(400, 60, 197, 25);

        buttonGroup1.add(btrMatrizF);
        btrMatrizF.setFont(new java.awt.Font("Arial Black", 1, 11)); // NOI18N
        btrMatrizF.setText("MATRIZ F (MULTIPLICAÇÃO)");
        btrMatrizF.setOpaque(false);
        jPanel2.add(btrMatrizF);
        btrMatrizF.setBounds(400, 90, 223, 25);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(20, 10, 650, 150);

        btnImprimir.setFont(new java.awt.Font("Arial Black", 0, 11)); // NOI18N
        btnImprimir.setText("IMPRIMIR");
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });
        jPanel1.add(btnImprimir);
        btnImprimir.setBounds(290, 170, 118, 25);

        btnLimpar.setFont(new java.awt.Font("Arial Black", 0, 11)); // NOI18N
        btnLimpar.setText("LIMPAR");
        btnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparActionPerformed(evt);
            }
        });
        jPanel1.add(btnLimpar);
        btnLimpar.setBounds(420, 170, 118, 25);

        jButton4.setFont(new java.awt.Font("Arial Black", 0, 11)); // NOI18N
        jButton4.setText("FECHAR");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4);
        jButton4.setBounds(550, 170, 118, 25);

        jPanel3.setBackground(new java.awt.Color(102, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 255, 255)));
        jPanel3.setOpaque(false);

        tblResultados.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        tblResultados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, "", null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "", "", "", "", ""
            }
        ));
        tblResultados.setGridColor(new java.awt.Color(255, 255, 255));
        tblResultados.setInheritsPopupMenu(true);
        tblResultados.setMinimumSize(new java.awt.Dimension(75, 150));
        tblResultados.setOpaque(false);
        tblResultados.setRowHeight(32);
        tblResultados.setSelectionForeground(new java.awt.Color(0, 0, 0));

        jLabel2.setFont(new java.awt.Font("Arial Black", 1, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("RESULTADO");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(517, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(tblResultados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tblResultados, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(jPanel3);
        jPanel3.setBounds(20, 200, 660, 200);

        fundo.setFont(new java.awt.Font("Arial Black", 0, 11)); // NOI18N
        fundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/cropped-800-600-913942.jpg"))); // NOI18N
        fundo.setOpaque(true);
        fundo.setPreferredSize(new java.awt.Dimension(700, 500));
        jPanel1.add(fundo);
        fundo.setBounds(0, 0, 700, 440);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btrMatrizResultadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btrMatrizResultadoActionPerformed

    }//GEN-LAST:event_btrMatrizResultadoActionPerformed

    private void btrMatrizBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btrMatrizBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btrMatrizBActionPerformed

    private void btrMatrizDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btrMatrizDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btrMatrizDActionPerformed

    private void btrMatrizEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btrMatrizEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btrMatrizEActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        btrMatrizA.setSelected(false);
        //desabilitaButton();
        btnLimpar.setEnabled(true);
        btnImprimir.setEnabled(false);
        DefaultTableModel Model = (DefaultTableModel) tblResultados.getModel();
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();

        String imprimirLinhaUm = "";
        String imprimirLinhaDois = "";

        //-------------------------------------------------------------------------------------------------------------------
        int linha = 1;
        int coluna = 1;
        int lin = 0;
        centralizado.setHorizontalAlignment(lin);

        if (btrMatrizResultado.isSelected()) {
            linha = 1;
            for (int i = 0; i < matrizResultado.length; i++) {
                for (int j = 0; j < matrizResultado[i].length; j++) {
                    tblResultados.getColumnModel().getColumn(j).setCellRenderer(centralizado);
                    tblResultados.setValueAt(matrizResultado[i][j], linha, j);

                }
                linha =3;
            }
        }

        if (btrMatrizA.isSelected()) {
            linha = 1;
            for (int i = 0; i < aCopia.length; i++) {
                for (int j = 0; j < aCopia[i].length; j++) {
                    tblResultados.getColumnModel().getColumn(j).setCellRenderer(centralizado);
                    tblResultados.setValueAt(aCopia[i][j], linha, j);
                }
                linha =3;
            }

        }

        if (btrMatrizB.isSelected()) {
            linha = 1;
            for (int i = 0; i < bInvertida.length; i++) {
                for (int j = 0; j < bInvertida[i].length; j++) {
                    tblResultados.getColumnModel().getColumn(j).setCellRenderer(centralizado);
                    tblResultados.setValueAt(bInvertida[i][j], linha, j);

                }
                linha =3;

            }

        }

        if (btrMatrizC.isSelected()) {
            coluna = 1;
            for (int i = 0; i < cTransposta.length; i++) {
                for (int j = 0; j < 2; j++) {
                    tblResultados.getColumnModel().getColumn(j).setCellRenderer(centralizado);

                    tblResultados.setValueAt(cTransposta[i][j], i, coluna);
                    coluna ++;
                }
                coluna = 1;

            }

        }

        if (btrMatrizD.isSelected()) {
            linha = 1;
            for (int i = 0; i < dAdicao.length; i++) {
                for (int j = 0; j < dAdicao[i].length; j++) {
                    tblResultados.getColumnModel().getColumn(j).setCellRenderer(centralizado);

                    tblResultados.setValueAt(dAdicao[i][j], linha, j);

                }
                linha =3;
            }

        }

        if (btrMatrizE.isSelected()) {
            linha = 1;
            for (int i = 0; i < eSubtracao.length; i++) {
                for (int j = 0; j < eSubtracao[i].length; j++) {
                    tblResultados.getColumnModel().getColumn(j).setCellRenderer(centralizado);
                    tblResultados.setValueAt(eSubtracao[i][j], linha, j);
                }
                linha =3;
            }
        }
        linha = 1;
        coluna = 1;
        if (btrMatrizF.isSelected()) {
            int rows = 3;
            int cols = 3;

            for (int i = 0; i < fMutiplicacao.length; i++) {
                for (int j = 0; j < fMutiplicacao.length; j++) {
                    tblResultados.getColumnModel().getColumn(j).setCellRenderer(centralizado);
                    tblResultados.setValueAt(fMutiplicacao[i][j], linha, coluna);
                    coluna ++;
                }
                coluna = 1;
                linha +=2;
            }
        }
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        habilitaButton();
        btnImprimir.setEnabled(true);
        btnLimpar.setEnabled(false);

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                tblResultados.setValueAt("", i, j);

            }
        }
    }//GEN-LAST:event_btnLimparActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btrMatrizAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btrMatrizAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btrMatrizAActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
      
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            
                
            }
        });
    
    }
    public void calcularMatriz() {
        /**
         *
         *
         * //preencimento da matriz A copia
         *
         *
         */
        for (int i = 0; i < matrizResultado.length; i++) {
            for (int j = 0; j < matrizResultado[i].length; j++) {
                aCopia[i][j] = matrizResultado[i][j];

            }
        }

        /**
         *
         *
         * //preencimento da matriz B Invertida
         *
         *
         */
        int aux = 4;
        for (int i = 0; i < aCopia.length; i++) {
            for (int j = 0; j < aCopia[i].length; j++) {
                bInvertida[i][j] = aCopia[i][aux];
                aux--;

            }
            aux = 4;
        }
        /**
         *
         *
         * //preencimento da matriz C transposta
         *
         *
         */

        for (int i = 0; i < aCopia.length; i++) {
            for (int j = 0; j < aCopia[i].length; j++) {
                cTransposta[j][i] = aCopia[i][j];
            }
        }
        /**
         *
         *
         * //preencimento da matriz D adição
         *
         *
         */
        for (int i = 0; i < aCopia.length; i++) {
            for (int j = 0; j < aCopia[i].length; j++) {
                dAdicao[i][j] = aCopia[i][j] + bInvertida[i][j];

            }

        }

        /**
         *
         *
         * //preencimento da matriz E subtração
         *
         *
         */
        for (int i = 0; i < aCopia.length; i++) {
            for (int j = 0; j < aCopia[i].length; j++) {
                eSubtracao[i][j] = aCopia[i][j] - bInvertida[i][j];

            }

        }

        /**
         *
         *
         * //preencimento da matriz F Multiplicação
         *
         *
         */
        int contador = 0;
        for (int i = 0; i < aCopia.length; i++) {
            for (int j = 0; j < aCopia[i].length; j++) {

                fMutiplicacao[i][i] += aCopia[i][j] * cTransposta[j][i];

            }
        }
        contador = 0;
        for (int i = 1; i >= 0; i--) {
            for (int j = 0; j < aCopia[i].length; j++) {

                fMutiplicacao[contador][i] += aCopia[contador][j] * cTransposta[j][i];
            }
            contador++;
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JRadioButton btrMatrizA;
    private javax.swing.JRadioButton btrMatrizB;
    private javax.swing.JRadioButton btrMatrizC;
    private javax.swing.JRadioButton btrMatrizD;
    private javax.swing.JRadioButton btrMatrizE;
    private javax.swing.JRadioButton btrMatrizF;
    private javax.swing.JRadioButton btrMatrizResultado;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel fundo;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTable tblResultados;
    // End of variables declaration//GEN-END:variables
   public void habilitaButton() {
        btrMatrizResultado.setEnabled(true);
        btrMatrizA.setEnabled(true);
        btrMatrizB.setEnabled(true);
        btrMatrizC.setEnabled(true);
        btrMatrizD.setEnabled(true);
        btrMatrizE.setEnabled(true);
        btrMatrizF.setEnabled(true);
    }
}
