package Controler;

import Modelo.Cano;
import Modelo.Hero;
import Modelo.Personagem;
import Modelo.ZigueZague;
import java.util.ArrayList;


/**
 *
 * @author hugo-zanni
 */
public class Fase {
    
    private final ArrayList<Personagem> personagens;
    
    private final Hero hero;
    
    private final int pontoSaidaLinha;
    private final int pontoSaidaColuna;

    public Fase (Hero hero, int pontoSaidaLinha, int pontoSaidaColuna){
        this.personagens = new ArrayList<>();
        this.hero = hero;
        this.pontoSaidaLinha = pontoSaidaLinha;
        this.pontoSaidaColuna = pontoSaidaColuna;

        // Adiciona o Hero como primeiro elemento da lista
        this.personagens.add(hero);
    }
    
    public void addPersonagem(Personagem p) {
        personagens.add(p);
    }

    public void removePersonagem(Personagem p) {
        personagens.remove(p);
    }

    public ArrayList<Personagem> getPersonagens() {
        return personagens;
    }
    
    public boolean isEmpty(){
        return personagens.isEmpty();
    }
    

    public void clear() {
        personagens.clear();
    }
    
    public Hero getHero() {
        return hero;
    }
    
    public boolean checarTrocaDeFase() {
        int linhaAtual = hero.getPosicao().getLinha();
        int colunaAtual = hero.getPosicao().getColuna();
        return linhaAtual == pontoSaidaLinha && colunaAtual == pontoSaidaColuna;
    }
    
    public static Fase criarFase1() {
        
        Hero hero = new Hero("Robbo.png");
        hero.getPosicao().setPosicao(1, 1);
        
        Fase fase1 = new Fase(hero, 27, 14);    //A saida seria nas coordenadas(10 , 5)
                                                
        Hero c = new Hero("Yoshi.png");     //Posição do Yoshi (Posiçao de saida)
        c.getPosicao().setPosicao(27, 14);
        fase1.addPersonagem(c);
        
        
        for (int i = 0; i < 16; i++) {
            Cano cano = new Cano("Cano.png");
            cano.getPosicao().setPosicao(0 , i);
            fase1.addPersonagem(cano);
        }
        
        for (int i = 0; i < 16; i++) {
            Cano cano = new Cano("Cano.png");
            cano.getPosicao().setPosicao(28 , i);
            fase1.addPersonagem(cano);
        }
        
        for (int i = 0; i < 29; i++) {
            Cano cano = new Cano("Cano.png");
            cano.getPosicao().setPosicao(i , 0);
            fase1.addPersonagem(cano);
        }
        
        for (int i = 0; i < 29; i++) {
            Cano cano = new Cano("Cano.png");
            cano.getPosicao().setPosicao(i , 15);
            fase1.addPersonagem(cano);
        }
        
        
        
        
        return fase1;
    }
    
    public static Fase criarFase2() {
        
        Hero hero = new Hero("Robbo.png");
        hero.getPosicao().setPosicao(27, 1);
        
        Fase fase2 = new Fase(hero, 1, 14);
        
        Hero c = new Hero("Toad.png");     //Posição do Toad (Posiçao de saida)
        c.getPosicao().setPosicao(1, 14);
        fase2.addPersonagem(c);
        
        // Paredes do mapa
        for (int i = 0; i < 16; i++) {
            Cano cano = new Cano("Cano.png");
            cano.getPosicao().setPosicao(0 , i);
            fase2.addPersonagem(cano);
        }
        
        for (int i = 0; i < 16; i++) {
            Cano cano = new Cano("Cano.png");
            cano.getPosicao().setPosicao(28 , i);
            fase2.addPersonagem(cano);
        }
        
        for (int i = 0; i < 29; i++) {
            Cano cano = new Cano("Cano.png");
            cano.getPosicao().setPosicao(i , 0);
            fase2.addPersonagem(cano);
        }
        
        for (int i = 0; i < 29; i++) {
            Cano cano = new Cano("Cano.png");
            cano.getPosicao().setPosicao(i , 15);
            fase2.addPersonagem(cano);
        }
        
        //Termino das paredes
        
        
        // Começo do labirinto
        
        
        int[][] paredes = {
            {26,2},{26,3},{26,4},{24,4},{25,4},{24,5},
            {24,6},{24,7},{26,6},{26,7},{26,6},{27,8},
            {24,9},{25,9},{23,7},{22,7}
        };

        for (int[] pos : paredes) {
            Cano cano = new Cano("Cano.png");
            cano.getPosicao().setPosicao(pos[0], pos[1]);
            fase2.addPersonagem(cano);
        }
        
        
        return fase2;
    }
    
}
