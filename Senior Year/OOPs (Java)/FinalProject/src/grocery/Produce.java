package grocery;
/**
 * 
 * @author David
 *
 *<h1> Produce </h1>
 *<p> This class contains all the object with their children classes that are to be sold by the grocery</p>
 *
 */
public class Produce {
	Double qty; // allows for fractional quantity, e.g. 2.2 lbs

	public Produce(Double q) {
		qty = q;
	}

	public Produce() {
		qty = (double) 0.0;
	} // default constructor

	public String produceType() {
		return "Produce";
	}

	public String produceClassification() {
		return "Produce";
	}

	public String priceType() {
		return "";
	}

	public double price() {
		return 0.0;
	}

	public Double getQty() {
		return qty;
	} // encapsulation

	public void setQty(Double q) {
		qty = q;
	}
}

class Flowers extends Produce {
	public Flowers(Double q) {
		super(q);
	}

	public Flowers() {
	}// default constructor

	public String produceType() {
		return "Flowers";
	}

	public String produceClassification() {
		return "Flowers";
	}
}

class Vegetable extends Produce {
	public Vegetable(Double q) {
		super(q);
	}

	public Vegetable() {
	}// default constructor

	public String produceType() {
		return "Vegetable";
	}

	public String produceClassification() {
		return "Vegetable";
	}
}

class Fruit extends Produce {
	public Fruit(Double q) {
		super(q);
	}

	public Fruit() {
	}// default constructor

	public String produceType() {
		return "Fruit";
	}

	public String produceClassification() {
		return "Fruit";
	}
}

class Banana extends Fruit {
	public Banana(double q) {
		super(q);
	}

	public Banana() {
	}

	public String produceType() {
		return "Banana";
	}

	public String priceType() {
		return "Dozen";
	}

	public double price() {
		return .79;
	}
}

class Mango extends Fruit {
	public Mango(double q) {
		super(q);
	}

	public Mango() {
	}

	public String produceType() {
		return "Mango";
	}

	public String priceType() {
		return "Unit";
	}

	public double price() {
		return 1.49;
	}

}

class Strawberry extends Fruit {
	public Strawberry(double q) {
		super(q);
	}

	public Strawberry() {
	}

	public String produceType() {
		return "Strawberry";
	}

	public String priceType() {
		return "Lbs";
	}

	public double price() {
		return 2.50;
	}
}

class Orange extends Fruit {
	public Orange(double q) {
		super(q);
	}

	public Orange() {
	}

	public String produceType() {
		return "Orange";
	}

	public String priceType() {
		return "Unit";
	}

	public double price() {
		return .39;
	}
}

class Apple extends Fruit {
	public Apple(double q) {
		super(q);
	}

	public Apple() {
	}

	public String produceType() {
		return "Apple";
	}

	public String priceType() {
		return "Lbs";
	}

	public double price() {
		return 2.33;
	}
}

class Eggplant extends Vegetable {
	public Eggplant(double q) {
		super(q);
	}

	public Eggplant() {
	}

	public String produceType() {
		return "Eggplant";
	}

	public String priceType() {
		return "Unit";
	}

	public double price() {
		return .74;
	}
}

class Radish extends Vegetable {
	public Radish(double q) {
		super(q);
	}

	public Radish() {
	}

	public String produceType() {
		return "Radish";
	}

	public String priceType() {
		return "Dozen";
	}

	public double price() {
		return 1.14;
	}
}

class Cabbage extends Vegetable {
	public Cabbage(double q) {
		super(q);
	}

	public Cabbage() {
	}

	public String produceType() {
		return "Cabbage";
	}

	public String priceType() {
		return "Unit";
	}

	public double price() {
		return 1.48;
	}
}

class Lettuce extends Vegetable {
	public Lettuce(double q) {
		super(q);
	}

	public Lettuce() {
	}

	public String produceType() {
		return "Lettuce";
	}

	public String priceType() {
		return "Unit";
	}

	public double price() {
		return .82;
	}
}

class Carrot extends Vegetable {
	public Carrot(double q) {
		super(q);
	}

	public Carrot() {
	}

	public String produceType() {
		return "Carrot";
	}

	public String priceType() {
		return "Dozen";
	}

	public double price() {
		return .98;
	}
}

class Rose extends Flowers {
	public Rose(double q) {
		super(q);
	}

	public Rose() {
	}

	public String produceType() {
		return "Rose";
	}

	public String priceType() {
		return "Dozen";
	}

	public double price() {
		return 19.99;
	}
}

class Carnation extends Flowers {
	public Carnation(double q) {
		super(q);
	}

	public Carnation() {
	}

	public String produceType() {
		return "Carnation";
	}

	public String priceType() {
		return "Dozen";
	}

	public double price() {
		return 5.28;
	}
}

class Tulip extends Flowers {
	public Tulip(double q) {
		super(q);
	}

	public Tulip() {
	}

	public String produceType() {
		return "Tulip";
	}

	public String priceType() {
		return "Unit";
	}

	public double price() {
		return 1.50;
	}
}

class Lily extends Flowers {
	public Lily(double q) {
		super(q);
	}

	public Lily() {
	}

	public String produceType() {
		return "Lily";
	}

	public String priceType() {
		return "Unit";
	}

	public double price() {
		return 3.00;
	}
}

class Orchid extends Flowers {
	public Orchid(double q) {
		super(q);
	}

	public Orchid() {
	}

	public String produceType() {
		return "Orchid";
	}

	public String priceType() {
		return "Unit";
	}

	public double price() {
		return 13.99;
	}
}
