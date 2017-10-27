package BlackJack.controller;

import BlackJack.view.IView;
import BlackJack.view.IView.PlayerState;
import BlackJack.model.Game;
import BlackJack.model.Observer;

public class PlayGame implements Observer {
	Game game;
	IView view;

	public PlayGame(Game a_game, IView a_view) {
		this.game = a_game;
		this.view = a_view;
		this.game.AddSubscriber(this);
	}

	public boolean Play() {
		view.DisplayWelcomeMessage();

		view.DisplayDealerHand(game.GetDealerHand(), game.GetDealerScore());
		view.DisplayPlayerHand(game.GetPlayerHand(), game.GetPlayerScore());

		if (game.IsGameOver()) {
			view.DisplayGameOver(game.IsDealerWinner());
		}

		PlayerState input = view.GetInput();

		switch (input) {
		case Play:
			game.NewGame();
			break;

		case Stand:
			game.Stand();
			break;

		case Hit:
			game.Hit();
			break;

		case Quit:
			return false;

		}
		return true;
	}

	@Override
	public void DealNewCard() {

		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (game.IsGameOver()) {
			view.DisplayGameOver(game.IsDealerWinner());
		}
		view.InsertRow();
		view.DisplayDealerHand(game.GetDealerHand(), game.GetDealerScore());
		view.DisplayPlayerHand(game.GetPlayerHand(), game.GetPlayerScore());

	}
}

