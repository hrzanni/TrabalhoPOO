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

    /**
     * Remove um personagem da fase.
     */
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
        hero.getPosicao().setPosicao(2, 1);
        
        Fase fase1 = new Fase(hero, 10, 5);    //A saida seria nas coordenadas(10 , 5)

        Hero c = new Hero("blackTile.png");
        c.getPosicao().setPosicao(10, 5);
        fase1.addPersonagem(c);
        
        
        for (int i = 0; i < 16; i++) {
            Cano cano = new Cano("Cano.png");
            cano.getPosicao().setPosicao(0 , i);
            fase1.addPersonagem(cano);
        }
        
        for (int i = 0; i < 16; i++) {
            Cano cano = new Cano("Cano.png");
            cano.getPosicao().setPosicao(29 , i);
            fase1.addPersonagem(cano);
        }
        
        return fase1;
    }
    
    public static Fase criarFase2() {
        
        Hero hero = new Hero("blackTile.png");
        hero.getPosicao().setPosicao(2, 1);
        
        Fase fase2 = new Fase(hero, 2, 2);

        ZigueZague zz = new ZigueZague("Robbo.png");
        zz.getPosicao().setPosicao(5, 5);
        fase2.addPersonagem(zz);
        
        return fase2;
    }
    
}
