package cracking.the.coding.interview.trials.ObjectOrientedDesign;

import java.util.ArrayList;

enum Suit {
	Club(1), Spade(2), Heart(3), Diamond(4);

	private int value;

	private Suit(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public static Suit getValueOfSuite(int value) {
		switch (value) {
		case 0:
			return Club;
		case 1:
			return Spade;
		case 2:
			return Heart;
		case 4:
			return Diamond;
		default:
			return null;
		}
	}
}

abstract class Card {
	protected Suit type;
	protected int faceValue;

	private boolean available = true;

	public Card(Suit type, int faceValue) {
		this.type = type;
		this.faceValue = faceValue;
	}

	public boolean isAvailable() {
		return available;
	}

	public void markUnAvailable() {
		available = false;
	}

	public void markAvailable() {
		available = true;
	}

	// implement this method for custom card game
	public abstract int value();
}

class Deck<T extends Card> {
	private ArrayList<T> cards;
	private int dealtCardIdx = 0;

	public Deck() {
	}

	public void setDeckOfCards(ArrayList<T> cards) {
		this.cards = cards;
	}

	public void shuffle() {
		for (int i = 0; i < cards.size(); i++) {
			int j = Exercise7_1.randomIntInRange(i, cards.size() - i - 1);
			T card1 = cards.get(i);
			T card2 = cards.get(j);
			cards.set(i, card2);
			cards.set(j, card1);
		}
	}

	public int remainingCards() {
		return cards.size() - dealtCardIdx;
	}

	public ArrayList<T> dealHand(int number) {
		if (remainingCards() < number) {
			return null;
		}

		ArrayList<T> hand = new ArrayList<T>();
		int count = 0;
		while (count < number) {
			T card = dealCard();
			if (card != null) {
				hand.add(card);
				count++;
			}
		}

		return hand;
	}

	public T dealCard() {
		if (remainingCards() == 0) {
			return null;
		}

		T card = cards.get(dealtCardIdx);
		card.markUnAvailable();
		dealtCardIdx++;
		return card;
	}
}

class Hand<T extends Card> {
	protected ArrayList<T> cards = new ArrayList<T>();

	public int score() {
		int score = 0;
		for (T card : cards) {
			score += card.value();
		}
		return score;
	}

	public void addCard(T card) {
		cards.add(card);
	}
}

class BlackJackCard extends Card {
	public BlackJackCard(Suit suit, int value) {
		super(suit, value);
	}

	public int value() {
		if (isAce()) { // Ace
			return 1;
		} else if (faceValue >= 11 && faceValue <= 13) { // Face card
			return 10;
		} else { // Number card
			return faceValue;
		}
	}

	public int minValue() {
		if (isAce()) { // Ace
			return 1;
		} else {
			return value();
		}
	}

	public int maxValue() {
		if (isAce()) { // Ace
			return 11;
		} else {
			return value();
		}
	}

	public boolean isAce() {
		return faceValue == 1;
	}

	public boolean isFaceCard() {
		return faceValue >= 11 && faceValue <= 13;
	}
}

class BlackJackHand extends Hand<BlackJackCard> {
	public BlackJackHand() {

	}

	public int score() {
		ArrayList<Integer> scores = possibleScores();
		int maxUnder = Integer.MIN_VALUE;
		int minOver = Integer.MAX_VALUE;
		for (int score : scores) {
			if (score > 21 && score < minOver) {
				minOver = score;
			} else if (score <= 21 && score > maxUnder) {
				maxUnder = score;
			}
		}
		return maxUnder == Integer.MIN_VALUE ? minOver : maxUnder;
	}

	private ArrayList<Integer> possibleScores() {
		ArrayList<Integer> scores = new ArrayList<Integer>();
		if (cards.size() == 0) {
			return scores;
		}
		for (BlackJackCard card : cards) {
			addCardToScoreList(card, scores);
		}
		return scores;
	}

	private void addCardToScoreList(BlackJackCard card, ArrayList<Integer> scores) {
		if (scores.size() == 0) {
			scores.add(0);
		}
		int length = scores.size();
		for (int i = 0; i < length; i++) {
			int score = scores.get(i);
			scores.set(i, score + card.minValue());
			if (card.minValue() != card.maxValue()) {
				scores.add(score + card.maxValue());
			}
		}
	}

	public boolean busted() {
		return score() > 21;
	}

	public boolean is21() {
		return score() == 21;
	}

	public boolean isBlackJack() {
		if (cards.size() != 2) {
			return false;
		}
		BlackJackCard first = cards.get(0);
		BlackJackCard second = cards.get(1);
		return (first.isAce() && second.isFaceCard()) || (second.isAce() && first.isFaceCard());
	}
}

public class Exercise7_1 {

	public static int randomInt(int n) {
		return (int) (Math.random() * n);
	}

	public static int randomIntInRange(int min, int max) {
		return randomInt(max + 1 - min) + min;
	}
}
