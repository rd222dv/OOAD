package BlackJack.model.rules;

public class RulesFactory {
	
	public IWinStrategy GetWinRule() {
		return new BasicWinStrategy();
	}

	public IHitStrategy GetHitRule() {
		return new BasicHitStrategy();
	}

	public INewGameStrategy GetNewGameRule() {
		return new AmericanNewGameStrategy();
	}

	public IHitStrategy GetSoft17Rule() { // Soft17 added
		return new Soft17HitStrategy();
	}

}