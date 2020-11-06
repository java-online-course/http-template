import com.epam.izh.rd.online.entity.Pokemon;
import com.epam.izh.rd.online.service.FightingClubService;
import com.epam.izh.rd.online.service.PokemonLoaderService;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class FightingClubServiceTest {
    public FightingClubService fightingClubService = new FightingClubService(new PokemonLoaderService());
    @Test
    public void doBattleTest() {
        Pokemon p1 = new Pokemon();
        p1.setPokemonId(25);
        p1.setHp((short) 35);
        p1.setDefense((short) 40);
        p1.setAttack((short) 55);

        Pokemon p2 = new Pokemon();
        p2.setPokemonId(79);
        p2.setHp((short) 90);
        p2.setDefense((short) 65);
        p2.setAttack((short) 65);
        assertEquals(fightingClubService.doBattle(p1,p2),p2);
    }
}
