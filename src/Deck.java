/*
 * File: Deck.java
 * Zachary Muranaka
 * Represents a population you are sampling from without replacement
 */

public class Deck
{
	private long popSize; // The size of the population that is being sampled from
	private long popSuccesses; // The number of successes within the population
	private long sampleSize; // The size of the sample you take from the entire population
	private long desiredSuccesses; // The amount of successes you are looking for from your sample
	private long popFailures; // Calculates the amount of failures throughout the entire population
	private long sampleFailures; // Calculates the number of failures in your sample assuming you get exactly the desired number of successes

	/*
	 * We need double versions of all of the above variables except for sampleSize
	 * These are necessary because there are times when we will need to treat the variables as doubles
	 * For example, in the probability() method we treat the variables as doubles
	 * However, we do not want to allow the user to enter doubles when creating the object
	 */
	private double dpopSize;
	private double dpopSuccesses;
	private double ddesiredSuccesses;
	private double dpopFailures;
	private double dsampleFailures;

	private long combination; // The combination of sampleSize and desiredSuccesses (nCr, where n = sampleSize and r = desiredSuccesses)

	// Calculates the greatest common denominator between two numbers
	private long gcd(final long givenNumber1, final long givenNumber2)
	{
		long greatestCommonDenominator = 1; // Any two numbers' minimum gcd is 1

		for (int i = 1; i <= givenNumber1 && i <= givenNumber2; ++i)
		{
			if (givenNumber1 % i == 0 && givenNumber2 % i == 0) // Checks if i is factor of both integers using modulus division
				greatestCommonDenominator = i;
		}
		return greatestCommonDenominator;
	}

	// Constructor for a Deck object
	public Deck(final long popSize, final long popSuccesses, final long sampleSize, final long desiredSuccesses)
	{
		// Checks if the numbers they entered allow for a valid Deck
		if(popSize >= popSuccesses && popSize >= sampleSize && popSize >= desiredSuccesses && popSuccesses >= desiredSuccesses && sampleSize >= desiredSuccesses && 
			popSize >= 0 && popSuccesses >= 0 && sampleSize >= 0 && desiredSuccesses >= 0)
		{
			try
			{
				this.popSize = popSize;
				this.popSuccesses = popSuccesses;
				this.sampleSize = sampleSize;
				this.desiredSuccesses = desiredSuccesses;

				popFailures = popSize - popSuccesses;
				sampleFailures = sampleSize - desiredSuccesses;

				dpopSize = popSize;
				dpopSuccesses = popSuccesses;
				ddesiredSuccesses = desiredSuccesses;
				dpopFailures = popFailures;
				dsampleFailures = sampleFailures;

				combination = ncr(sampleSize, desiredSuccesses);
			}
			// It is possible to construct a valid Deck that still results in a divide-by-zero error
			catch(ArithmeticException aException)				
			{
				// No further action necessary
			}
		}
	}

	// Constructs a Deck object that's a copy of Deck d
	public Deck(final Deck d)
	{
		popSize = d.popSize;
		popSuccesses = d.popSuccesses;
		sampleSize = d.sampleSize;
		desiredSuccesses = d.desiredSuccesses;

		popFailures = d.popFailures;
		sampleFailures = d.sampleFailures;

		dpopSize = popSize;
		dpopSuccesses = popSuccesses;
		ddesiredSuccesses = desiredSuccesses;
		dpopFailures = popFailures;
		dsampleFailures = sampleFailures;

		combination = ncr(sampleSize, desiredSuccesses);
	}

	// Calculates the hypergeometric probability
	public double probability(double popSize, double popSuccesses, final double desiredSuccesses, double popFailures, final double sampleFailures)
	{
		double w = 1;

		for (int i = 0; i < desiredSuccesses; i++)
		{
			w = (popSuccesses / popSize) * w;
			popSuccesses--;
			popSize--;
		}

		for (int i = 0; i < sampleFailures; i++)
		{
			w = (popFailures / popSize) * w;
			popFailures--;
			popSize--;
		}

		return w;
	}

	/*
	 * Calculates a combination of sampleSize and desiredSuccesses
	 * The equation for nCr is (n!) / ((r!) * (n - r)!)
	 * This can be simplified to n * (n - 1) * (n - 2) * ... * (n - r + 1) / r!
	 * This method calculates nCr using this simplified equation
	 * n = sampleSize and r = desiredSuccesses
	 */
	public long ncr(long n, long r)
	{
		/*
		 * The combination of n and r is equal to the combination of n and (n - r)
		 * For example, 17 combination 13 is the same as 17 combination 4
		 * Therefore, we can substitute (n - r) for r if we have a larger r value
		 * Attempting to prevent overflow, if (n - r) is less than r we use it instead
		 */
		if(n - r < r)
	  		r = n - r;
	  
		// Tries to calculate the combination of n and r
		try
		{
			long top = 1; // top holds the value of n * (n - 1) * (n - 2) ...
			long bottom = 1; // bottom holds the value of r * (r - 1) * (r - 2) ...
		  
			if(r != 0)
			{
		  		/*
				 * Because this only loops until r = 0, we only calculate the first r numbers of the factorial
				 * This is equivalent to the n * (n - 1) * (n - 2) * ... * (n - r + 1)
				 * In other words, it is a reduced factorial of n from n to n - r + 1
				 * This is how we calculate nCr with the simplified equation
				 * The top is n * (n - 1) * (n - 2) * ... * (n - r + 1) and the bottom is r!
				 */
				while(r > 0)
				{
			  		top *= n;
					bottom *= r;

					long greatestCommonDenominator = gcd(top, bottom);

					// Divides the top and bottom of the fraction by their gcd to help prevent overflow
					top /= greatestCommonDenominator;
					bottom /= greatestCommonDenominator;

					n--;
					r--;
				}
			}
			else // n combination 0, where n is any number, is always equal to 1
		  		top = 1;

			/*
			 * A factorial always simplifies to a whole number
			 * Therefore, we can just return the top, because using the gcd division, bottom should simplify to 1
			 * In the case where r = 0, we simply store 1 in top because n combination 0 is always 1
			 */
			return top;
		}
		// There was a divide-by-zero error
		catch(ArithmeticException aException)
		{
			return 1; // Returns a default value of 1
		}
	}

	// Calculates the probability of n or greater successes
	public double orGreater(double exactChance)
	{
		for (long i = desiredSuccesses; i < sampleSize; i++)
		{
			ddesiredSuccesses++;
			desiredSuccesses++;
			combination = ncr(sampleSize, desiredSuccesses);
			dsampleFailures = sampleSize - desiredSuccesses;
			double iChance = probability(dpopSize, dpopSuccesses, ddesiredSuccesses, dpopFailures, dsampleFailures);
			iChance = iChance * combination;
			exactChance += iChance;
		}

		return exactChance;
	}

	// Calculates the probability of n or less successes
	public double orLess(double exactChance)
	{
		for (long i = desiredSuccesses; i > 0; i--)
		{
			ddesiredSuccesses--;
			desiredSuccesses--;
			combination = ncr(sampleSize, desiredSuccesses);
			dsampleFailures = sampleSize - desiredSuccesses;
			double iChance = probability(dpopSize, dpopSuccesses, ddesiredSuccesses, dpopFailures, dsampleFailures);
			iChance = iChance * combination;
			exactChance += iChance;
		}
      
		return exactChance;
	}

	// The following six methods are accessor methods for double versions of variables
   public double getdpopSize(){ return dpopSize; }

   public double getdpopSuccesses(){ return dpopSuccesses; }

   public double getddesiredSuccesses(){ return ddesiredSuccesses; }

   public double getdpopFailures(){ return dpopFailures; }

   public double getdsampleFailures(){ return dsampleFailures; }

   public long getcombination(){ return combination; }

}
