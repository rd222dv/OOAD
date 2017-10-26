package BlackJack.controller;

import BlackJack.view.IView;
import BlackJack.view.Observer;
import BlackJack.view.IView.PlayerState;
import BlackJack.model.Game;

public class PlayGame extends Observer {
	private Game a_game;
	private IView a_view;

	public PlayGame(Game a_game, IView a_view) {
		this.a_game = a_game;
		this.a_view = a_view;
	}

	public boolean Play(Game a_game, IView a_view) {
		a_view.DisplayWelcomeMessage();

		a_view.DisplayDealerHand(a_game.GetDealerHand(), a_game.GetDealerScore());
		a_view.DisplayPlayerHand(a_game.GetPlayerHand(), a_game.GetPlayerScore());

		if (a_game.IsGameOver()) {
			a_view.DisplayGameOver(a_game.IsDealerWinner());
		}

		PlayerState input = a_view.GetInput();

		switch (input) {
		case Play:
			a_game.NewGame();
			break;

		case Stand:
			a_game.Stand();
			break;

		case Hit:
			a_game.Hit();
			break;

		case Quit:
			return false;

		}
		return true;
	}

	public void DealNewCard() {

		a_view.DisplayDealerHand(a_game.GetDealerHand(), a_game.GetDealerScore());
		a_view.DisplayPlayerHand(a_game.GetPlayerHand(), a_game.GetPlayerScore());

		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
