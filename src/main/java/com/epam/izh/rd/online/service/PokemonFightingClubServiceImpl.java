package com.epam.izh.rd.online.service;


import com.epam.izh.rd.online.entity.Pokemon;
import com.epam.izh.rd.online.view.ShowImage;

public class PokemonFightingClubServiceImpl implements PokemonFightingClubService {
    private ShowImage showImage;
    @Override
    public Pokemon doBattle(Pokemon p1, Pokemon p2) {
        System.out.println("История боя: ");
        while (p2.getHp() >= 1 && p1.getHp()>=1){
            //Первый нападает покемон с наибольшим ID
            if (p1.getPokemonId()> p2.getPokemonId()) {
                doDamage(p1, p2);
                if (p2.getHp() > 0) {
                    doDamage(p2, p1);
                }
            }else {
                doDamage(p2, p1);
                if (p1.getHp() > 0) {
                    doDamage(p1, p2);
                }
            }
        }

        if (p1.getHp() <= 0) {
            return p2;
        } else {
            return p1;
        }
    }

    @Override
    public void showWinner(Pokemon winner) {
        System.out.println("Pokemon "+winner.getPokemonName()+" won.");
        showImage = new ShowImage();
    }

    @Override
    public void doDamage(Pokemon from, Pokemon to) {
        short damage =  (short) (from.getAttack()-(from.getAttack()* to.getDefense()/100));
        to.setHp((short) (to.getHp() - damage));
        System.out.println("Нападает покемон "+from.getPokemonName()+ " c атакой "+ from.getAttack());
        System.out.println("Покемон "+to.getPokemonName() +" имеет защиту "+to.getDefense()+ "%" );
        System.out.println("Hp покемона "+to.getPokemonName() + ": "+to.getHp());
    }
}
