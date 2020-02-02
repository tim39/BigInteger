package math;

/**
 * This class encapsulates a BigInteger, i.e. a positive or negative integer
 * with any number of digits, which overcomes the computer storage length
 * limitation of an integer.
 * 
 */
public class BigInteger {

	/**
	 * True if this is a negative integer
	 */
	boolean negative;

	/**
	 * Number of digits in this integer
	 */
	int numDigits;

	/**
	 * Reference to the first node of this integer's linked list representation
	 * NOTE: The linked list stores the Least Significant Digit in the FIRST
	 * node. For instance, the integer 235 would be stored as: 5 --> 3 --> 2
	 */
	DigitNode front;

	/**
	 * Initializes this integer to a positive number with zero digits, in other
	 * words this is the 0 (zero) valued integer.
	 */
	public BigInteger() {
		negative = false;
		numDigits = 0;
		front = null;
	}

	/**
	 * Parses an input integer string into a corresponding BigInteger instance.
	 * A correctly formatted integer would have an optional sign as the first
	 * character (no sign means positive), and at least one digit character
	 * (including zero). Examples of correct format, with corresponding values
	 * Format Value +0 0 -0 0 +123 123 1023 1023 0012 12 0 0 -123 -123 -001 -1
	 * +000 0
	 * 
	 * 
	 * @param integer
	 *            Integer string that is to be parsed
	 * @return BigInteger instance that stores the input integer ` * @throws
	 *         IllegalArgumentException If input is incorrectly formatted
	 */
	public static BigInteger parse(String integer) throws IllegalArgumentException {
		// Incorrect format: contains letter, other punctuation, is not number,
		// +,-, trailing or leading space

		// if (format invalid) {throw exception} See if anything is not a number
		// boolean containsLetter = false;
		// for (int i = 0; i<integer.length();i++){
		// if (Character.isLetter(integer.charAt(i))){ containsLetter = true;
		// break;}
		// }
		// if (containsLetter) {throw new IllegalArgumentException("Incorrect
		// format");}
		BigInteger a = new BigInteger();
		integer = integer.trim(); // Works perfectly, only deletes leading and
									// trailing spaces
		if (integer.charAt(0) == '+') {
			a.negative = false;
			integer = integer.substring(1);
		}
		if (integer.charAt(0) == '-') {
			a.negative = true;
			integer = integer.substring(1);
		}
		if (integer.length() > 1) {
			int z = 0;
			while (integer.charAt(z) == '0' && z < integer.length() - 1) {
				z++;
			}
			integer = integer.substring(z);
			// This doesn't work for 000, needs to produce answer 0 --> fixed
			// this
		}
		if (integer.equals("0")&&a.negative) {a.negative=false;}
		DigitNode temp = null;
		for (int i = 0; i < integer.length(); i++) {
			a.numDigits++;
			a.front = new DigitNode(Integer.parseInt(integer.charAt(i) + ""), temp);
			temp = a.front;
		}
		// Add in exception
		System.out.println(a.negative);
		System.out.println(a.numDigits);
		return a;
	}

	/**
	 * Adds an integer to this integer, and returns the result in a NEW
	 * BigInteger object. DOES NOT MODIFY this integer. NOTE that either or both
	 * of the integers involved could be negative. (Which means this method can
	 * effectively subtract as well.)
	 * 
	 * @param other
	 *            Other integer to be added to this integer
	 * @return Result integer
	 */
	public BigInteger add(BigInteger other) {
		// carry, add to link list, --, +-
		// front = null;
		// If one linked list runs out first, farm the rest from the longer
		// number
		// take into account one being negative
		// need to say if biginteger is negative
		// NUMBER OF DIGITS IS PART OF THE OBJECT BEING RETURNED
		DigitNode ptr = other.front;
		DigitNode pts = this.front;
		BigInteger b = new BigInteger();
		// DigitNode bFront = null;
		// b.front = bFront;

		// Normal addition seems to work finem

		int carry = 0;
		int digitSum = 0;
		DigitNode temp = null;
		b.front = temp;
		boolean hasRun = false;
		if ((other.negative == false && this.negative == false) || (other.negative == true && this.negative == true)) {
			if (other.negative == true && this.negative == true) {
				b.negative = true;
			}
			hasRun = true;
			int ptrS = 0, ptsS = 0;

			while (ptr != null || pts != null) {
				if (ptr != null) {
					ptrS = ptr.digit;
				} else {
					ptrS = 0;
				}
				if (pts != null) {
					ptsS = pts.digit;
				} else {
					ptsS = 0;
				}
				digitSum = (ptrS + ptsS + carry);
				if (digitSum > 9) {
					carry = 1;
					digitSum -= 10;
				} else {
					carry = 0;
				}
				if (temp == null) {
					temp = new DigitNode(digitSum, null);
					b.front = temp;
				} else {
					temp.next = new DigitNode(digitSum, null);
					temp = temp.next;
				}
				if (ptr != null)
					ptr = ptr.next;
				if (pts != null)
					pts = pts.next;
				
			}
			// if (ptr != null) {
			// /*
			// * if (carry == 1) { digitSum = ptr.digit + 1; if (digitSum > 9)
			// * { carry = 1; } else { carry = 0; } temp.next = new
			// * DigitNode(digitSum, null); } else { temp.next = new
			// * DigitNode(ptr.digit, null); } temp = temp.next; ptr =
			// * ptr.next;
			// */
			// digitSum = ptr.digit + carry;
			// if (digitSum > 9) {
			// carry = 1;
			// //digitSum-=10;
			// } else {
			// carry = 0;
			// }
			// temp.next = new DigitNode(digitSum, null);
			// temp = temp.next;
			// ptr = ptr.next;
			// }
			// if (pts != null) {
			// /*
			// * if (carry == 1) { temp.next = new DigitNode(pts.digit, null);
			// * } else { temp.next = new DigitNode(pts.digit, null); } temp =
			// * temp.next; pts = pts.next;
			// */
			// /*if (carry == 1) {
			// digitSum = pts.digit + 1;
			// if (digitSum > 9) {
			// carry = 1;
			// } else {
			// carry = 0;
			// }
			// temp.next = new DigitNode(digitSum, null);
			// } else {
			// temp.next = new DigitNode(pts.digit, null);
			// }
			// temp = temp.next;
			// pts = pts.next;
			// }*/
			// digitSum = pts.digit + carry;
			// if (digitSum > 9) {
			// carry = 1;
			// //digitSum-=10;
			// } else {
			// carry = 0;
			// }
			// temp.next = new DigitNode(digitSum, null);
			// temp = temp.next;
			// pts = pts.next;}

		}
		if (carry == 1) {
			temp.next = new DigitNode(1, null);
			carry = 0;
		}
		// might need .prev
		// skip over this if other ran, can use boolean
		// make it so its always ptr-pts
		// big - small or positive - negative
		// Could do big-small and then use > to determine negative boolean

		// get test case 2-100 to work

		// could use other.numdigits or this.numdigits
		int borrow = 0;
		if (!((hasRun == true) == true)) {
			if ((other.negative == true || this.negative == true)) {

				// big - small

				// if (other.negative) // {this-other}
				// {
				// pts = other.front;
				// ptr = this.front;
				// }
				// if (this.negative) // {other-this}
				// {
				// ptr = other.front;
				// pts = this.front;
				// }
				if (other.numDigits > this.numDigits) {
					ptr = other.front;
					pts = this.front;
					if (other.negative) {
						b.negative = true;
					}
				} else if (this.numDigits > other.numDigits) {
					pts = other.front;
					ptr = this.front;
					if (this.negative) {
						b.negative = true;
					}

					// need to get below to work, determines which
					// number is bigger

				} else if (this.numDigits == other.numDigits) {
					DigitNode abc = other.front;
					DigitNode xyz = this.front;
					boolean abcG = true;
					while (abc != null) {
						if (abc.digit > xyz.digit) {
							abcG = true;
							ptr = other.front;
							pts = this.front;
							if (other.negative) {
								b.negative = true;
							}

						} else if (xyz.digit > abc.digit) {
							abcG = false;
							pts = other.front;
							ptr = this.front;
							if (this.negative) {
								b.negative = true;
							}
						}
						abc = abc.next;
						xyz = xyz.next;
					}
				}
				while (ptr != null || pts != null) {
					// For example, if 2-100, can do 100-2 and negative=true
					int ptrS = 0, ptsS = 0;
					if (ptr != null) {
						ptrS = ptr.digit;
					} else {
						ptrS = 0;
					}
					if (pts != null) {
						ptsS = pts.digit;
					} else {
						ptsS = 0;
					}
					int digitDiff = (ptrS - ptsS - borrow);
					if (digitDiff < 0) {
						borrow = 1;
						digitDiff += 10;
					} else {
						borrow = 0;
					}
					if (temp == null) {
						temp = new DigitNode(digitDiff, null);
						b.front = temp;
					} else {
						temp.next = new DigitNode(digitDiff, null);
						temp = temp.next;
					}
					if (ptr != null)
						ptr = ptr.next;
					if (pts != null)
						pts = pts.next;
					// doesnt' yet take into account one list being shorter
				}

			}

		}
		String bString = b.toString();
		b = parse(bString);
	
		// b.parse(integer) possible op strat
		// System.out.println(b.toString());
		System.out.println("negative="+b.negative);
		 System.out.println(b.numDigits); //guessing this automatically works. check when I have time
		return b;
	}

	/**
	 * Returns the BigInteger obtained by multiplying the given BigInteger with
	 * this BigInteger - DOES NOT MODIFY this BigInteger
	 * 
	 * @param other
	 *            BigInteger to be multiplied
	 * @return A new BigInteger which is the product of this BigInteger and
	 *         other.
	 */
	public BigInteger multiply(BigInteger other) {
		
		//middle school vs iterative
		//iterative easier, works for lower test cases
		//middle school harder, but works for all.
		
		DigitNode par = other.front; //Switching seemed to do nothing
		DigitNode pas = this.front;
		BigInteger c = new BigInteger();
		c.numDigits = 0;
		c.front = null; // for now
		DigitNode pro = new DigitNode(0, null);
		c.front = pro;
		// if ((other.negative == false && this.negative == false) ||
		// (other.negative == true && this.negative == true)) {
		// c.negative = false;
		// }

		// int digitCount = 0; // par is number getting added, pas is number of
		// times
		/*
		 * while (par != null && pas != null) { int for (int n = 0; n <
		 * pas.digit; n++) { digitCount += 0; } }
		 */

		// recursive multiplication
//		int Tens = 1;
//		BigInteger Subtract = new BigInteger();
//		DigitNode sub = new DigitNode(-1, null);
//		Subtract.front = sub;
//		BigInteger Empty = new BigInteger();
//		DigitNode emp = new DigitNode(0, null);
		// while (pas != null) {
		//
		// int pasDig = (pas.digit * Tens); // this exceeds available -->
		// store this in big integer
		// while (pasDig > 0) {
		// c = c.add(other);
		// pasDig--;
		// } // does other or c go first
		// pas = pas.next;
		// Tens = Tens * 10;
		// }
		// BigInteger pasDig = new BigInteger();
		// DigitNode tmp = null;
		// while (pas != null) {
		// if (tmp==null){
		// tmp = new DigitNode(pas.digit,null);
		// //tmp.digit=pas.digit;
		// pasDig.front = tmp;
		// } else {
		// tmp.next = new DigitNode(pas.digit, null);
		// tmp = tmp.next;
		// pas = pas.next;}
		// }
		// while (!(pasDig.numDigits==1 && pasDig.front.digit==0 )) {
		// c = c.add(other);
		// pasDig.add(Subtract);
		// } // does other or c go first

		// could replace with below implementation

		// 234 times 1249=
		// 234 times 9 + 234 times 10 times 4 + 234 times 100 times 2 + 234
		// times 1000 times 1
		// 234 times 40 = 234 times 4, throw a digit on the end(easier because
		// its in reverse order)
		// Compute partial products and add
		// How to keep track of how many zeros to add

		// have an int for numzeros (or something similar)
		
		int numZx = 0;
		int numZy = 0;
		int numZ = 0;
		int a = 0;
		
		while (pas != null) { //iterates through this 
			par = other.front;
			numZx=0;
			
			while (par != null) { //iterates other
				BigInteger New = new BigInteger();
				int f = numZx+numZy; //Number of zeroes in partial product
				DigitNode fk = null;
				System.out.println("F="+f); // prints num zeroes during a particular pass
				while (f > 0) {
					if (fk == null) {
						fk = new DigitNode(0, null); System.out.println("Added the first zero");
						New.front = fk;
						f--;
					} else {
						fk.next = new DigitNode(0, null);
						fk = fk.next; System.out.println("Added another zero");
						f--;
					}
					 //numZ--;
				}
				int k = (pas.digit * par.digit);// + a;
				if (k > 9) {
					a = k / 10;
					k = k - (a * 10);
					if (fk == null) {
						fk = new DigitNode(k, null); System.out.println("Added the k node as front");
						New.front = fk;
						fk.next = new DigitNode(a, null); System.out.println("Added a node as second");
						fk = fk.next;
					} else { 
						//a = 0;
						// DigitNode q = new DigitNode(a,null);
						fk.next = new DigitNode(k, null); // fk.next = new DigitNode(k, new DigitNode(a, null));
						fk = fk.next;
						fk.next = new DigitNode(a, null);
						fk = fk.next;
					}
				}
				else { a=0; //when pas * par <= 9
					if (fk == null) {
						fk = new DigitNode(k, null);
						New.front = fk;
					} else {
						fk.next = new DigitNode(k, null);
						fk = fk.next;
					}
				}
				System.out.println("Before addition:"+c);
				System.out.println("New:"+New);
				c = c.add(New);
				System.out.println("After addition:"+c);
				par = par.next;
				 numZx++; //x if (par!=null)
			}
			//c = c.add(New);
			pas = pas.next;
			numZy++; //y if (pas!=null)
		}

		//Compute it all into 1.
//		pas = this.front;
//		BigInteger New = new BigInteger(); //When to initialize?
//		while (pas != null) {
//			//BigInteger New = new BigInteger();
//			par = other.front;
//			while (par != null) {
//				int f = numZ;
//				DigitNode fk = null;
//				while (f > 0) {
//					if (fk == null) {
//						fk = new DigitNode(0, null);
//						New.front = fk;
//						f--;
//					} else {
//						fk.next = new DigitNode(0, null);
//						fk = fk.next;
//					}
//					f--; //numZ--;
//				}
//				int k = (pas.digit * par.digit) + a;
//				if (k > 9) {
//					a = k / 10;
//					k = k - (a * 10);
//					if (fk == null) {
//						fk = new DigitNode(k, null);
//						New.front = fk;
//						//fk.next = new DigitNode(a, null);
//						//fk = fk.next;
//					} else { 
//						a = 0;
//						// DigitNode q = new DigitNode(a,null);
//						fk.next = new DigitNode(k, null);
//						fk = fk.next;
//						//fk.next = new DigitNode(a, null);
//						//fk = fk.next;
//					}
//				} else {
//					if (fk == null) {
//						fk = new DigitNode(k, null);
//						New.front = fk;
//					} else {
//						fk.next = new DigitNode(k, null);
//						fk = fk.next;
//					}
//				}
//				par = par.next;
//				if (par!=null) numZ++; //x
//			}
//			//c = c.add(New);
//			pas = pas.next;
//			if (pas!=null)numZ++; //y
//		}
//		c = New;
		// Below checks if result should be negative, modifies .negative
		// accordingly
		while (true) {
			if (other.negative == true && this.negative == true)
				break;
			if (other.negative == true || this.negative == true) {
				c.negative = true;
				break;
			}
			break;
		}

		// System.out.println(c.toString());
		// System.out.println(c.negative);
		// System.out.println(other.toString()); // other is the second int
		// inputted, this is first
		System.out.println("#digC: "+c.numDigits);
		System.out.println("c.negative: "+c.negative);
		return c;

		// This method depends on add method
		// 123 times 32
		// =123 times 2 + 123 times 30
		// could be 123 + 123 + 123+123...+123
		// replace all plus signs with .add

		// for multiply, the operation is the same regardless of positive or
		// negative
		// if both positive or both negative, result positive, otherwise
		// negative
		// just have to figure out the operation, have to be able to carry other
		// values besides 1
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		if (front == null) {
			return "0";
		}

		String retval = front.digit + "";
		for (DigitNode curr = front.next; curr != null; curr = curr.next) {
			retval = curr.digit + retval;
		}

		if (negative) {
			retval = '-' + retval;
		}

		return retval;
	}

}