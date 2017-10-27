package BlackJack.model.rules;

import BlackJack.model.Dealer;
import BlackJack.model.Player;

public interface IWinStrategy {
	public boolean IsDealerWinner(Player a_player, Dealer a_dealer);
}
