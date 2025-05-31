package Controler;

import Modelo.Personagem;
import Modelo.Hero;
import Auxiliar.Consts;
import Auxiliar.Desenho;
import auxiliar.Posicao;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tela extends javax.swing.JFrame implements KeyListener {
    
    private static Tela instancia;
    private Fase faseAtual;
    private ControleDeJogo cj = new ControleDeJogo();
    private Graphics g2;
    private int cameraLinha = 0;
    private int cameraColuna = 0;
    private int numeroFase = 1;
    private boolean gameOverShown = false;

    public Tela() {
        Desenho.setCenario(this);
        initComponents();

 /*Cria a janela do tamanho do tabuleiro + insets (bordas) da janela*/
        this.setSize(Consts.RES * Consts.CELL_SIDE + getInsets().left + getInsets().right,
                Consts.RES * Consts.CELL_SIDE + getInsets().top + getInsets().bottom);

        this.addKeyListener(this);
        
        this.numeroFase = 1;
        this.faseAtual = Fase.criarFase1();
        atualizaCamera();   
        Tela.instancia = this;
        
    }
    
    public static Tela getTela() {
        return instancia;
    }
    
    public Fase getFaseAtual() {
        return faseAtual;
    }
    
    private Hero getHeroDaFase() {
        if (faseAtual == null) return null;
        return faseAtual.getHero();
    }

    public int getCameraLinha() {
        return cameraLinha;
    }

    public int getCameraColuna() {
        return cameraColuna;
    }
    
     public void removePersonagem(Personagem p) {
        faseAtual.removePersonagem(p);
    }
     public void addPersonagem(Personagem p) {
        faseAtual.addPersonagem(p);
    }
     public Graphics getGraphicsBuffer() {
        return g2;
    }

    public boolean ehPosicaoValida(Posicao p) {
        return cj.ehPosicaoValida(this.faseAtual.getPersonagens(), p);
    }

    public void paint(Graphics gOld) {
        Graphics g = this.getBufferStrategy().getDrawGraphics();
        /*Criamos um contexto gráfico*/
        g2 = g.create(getInsets().left, getInsets().top, getWidth() - getInsets().right, getHeight() - getInsets().top);
        /**
         * ***********Desenha cenário de fundo*************
         */
        for (int i = 0; i < Consts.RES; i++) {
            for (int j = 0; j < Consts.RES; j++) {
                int mapaLinha = cameraLinha + i;
                int mapaColuna = cameraColuna + j;

                if (mapaLinha < Consts.MUNDO_ALTURA && mapaColuna < Consts.MUNDO_LARGURA) {
                    try {
                        Image newImage = Toolkit.getDefaultToolkit().getImage(
                                new java.io.File(".").getCanonicalPath() + Consts.PATH + "bricks.png");
                        g2.drawImage(newImage,
                                j * Consts.CELL_SIDE, i * Consts.CELL_SIDE,
                                Consts.CELL_SIDE, Consts.CELL_SIDE, null);
                    } catch (IOException ex) {
                        Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        
          // Desenha e processa personagens
        
        if (!this.faseAtual.isEmpty()) {
            this.cj.desenhaTudo(faseAtual.getPersonagens());
            this.cj.processaTudo(faseAtual.getPersonagens());
        }

        g.dispose();
        g2.dispose();
        if (!getBufferStrategy().contentsLost()) {
            getBufferStrategy().show();
        }
    }

    private void atualizaCamera() {
        ArrayList<Personagem> personagens = faseAtual.getPersonagens();
        if (personagens.isEmpty()) return;
        Hero hero = (Hero) personagens.get(0);
        Posicao pos = hero.getPosicao();
        int linha = pos.getLinha();
        int coluna = pos.getColuna();

        cameraLinha = Math.max(0, Math.min(linha - Consts.RES / 2, Consts.MUNDO_ALTURA - Consts.RES));
        cameraColuna = Math.max(0, Math.min(coluna - Consts.RES / 2, Consts.MUNDO_LARGURA - Consts.RES));
    }

    public void go() {
        TimerTask task = new TimerTask() {
            public void run() {
                repaint();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 0, Consts.PERIOD);
    }

    public void keyPressed(KeyEvent e) {
        
        if (faseAtual == null) return;
         
         // 1) Encontra o herói
        ArrayList<Personagem> personagens = faseAtual.getPersonagens();
        
        Hero hero = getHeroDaFase();
        if (hero == null) return;
        
        
        if (e.getKeyCode() == KeyEvent.VK_C) {
            this.faseAtual.clear();
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            hero.moveUp();
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            hero.moveDown();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            hero.moveLeft();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            hero.moveRight();
        }
        this.atualizaCamera();
        this.setTitle("-> Cell: " + (hero.getPosicao().getLinha()) + ", "
                + (hero.getPosicao().getColuna()));

        //repaint(); /*invoca o paint imediatamente, sem aguardar o refresh*/
        
        
        if (faseAtual.checarTrocaDeFase()) {
                numeroFase++;
                System.out.println(">>> TROCANDO PARA FASE " + numeroFase);
                
                switch (numeroFase) {
                    case 2:
                        faseAtual = Fase.criarFase2();
                        break;
                    // case 3: faseAtual = Fase.criarFase3(); break;
                    default: break;
            }
            atualizaCamera();
            repaint();
        
    }
        
    }

 

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("POO2023-1 - Skooter");
        setAlwaysOnTop(true);
        setAutoRequestFocus(false);
        setResizable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 561, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    public void mouseMoved(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }
}
