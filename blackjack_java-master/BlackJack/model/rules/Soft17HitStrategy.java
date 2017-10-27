package BlackJack.model.rules;

import BlackJack.model.Card;
import BlackJack.model.Player;

public class Soft17HitStrategy implements IHitStrategy {
	private final int g_hitLimit = 17;

	public boolean DoHit(Player a_dealer) {
		boolean hasAce = false;
		for (Card card : a_dealer.GetHand()) {
			// First pick the hidden card
			card.Show(true);
			if (card.GetValue() == Card.Value.Ace) {
				// Had ace, set hasAce to true
				hasAce = true;
			}
		}
		// The current score is 17 and had an ace, therefore can hit
		if (hasAce && (a_dealer.CalcScore() == g_hitLimit)) {
			return true;
		}
		// Otherwise follow basic hit strategy
		else {
			return a_dealer.CalcScore() < g_hitLimit;
		}
	}

}

