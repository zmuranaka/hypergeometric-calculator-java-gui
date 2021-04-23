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
    double exactChance;
    double orGreaterInclusiveChance;
    double orLessInclusiveChance;

    // Constructor for the Deck class
    public Deck(final long popSize, final long popSuccesses, final long sampleSize, final long desiredSuccesses)
    {
        // Checks if the numbers they entered allow for a valid Deck
        if (popSize >= popSuccesses && popSize >= sampleSize && popSize >= desiredSuccesses
            && popSuccesses >= desiredSuccesses && sampleSize >= desiredSuccesses
            && popSize >= 0 && popSuccesses >= 0 && sampleSize >= 0 && desiredSuccesses >= 0)
        {
            try
            {
                this.popSize = popSize;
                this.popSuccesses = popSuccesses;
                this.sampleSize = sampleSize;
                this.desiredSuccesses = desiredSuccesses;
                this.popFailures = popSize - popSuccesses;

                this.exactChance = probability(this.desiredSuccesses); // Chance of getting exactly desired successes
                this.orGreaterInclusiveChance = orGreater(exactChance); // Chance of getting desired successes or greater
                this.orLessInclusiveChance = orLess(exactChance); // Chance of getting desired successes or less
            }
            // It is possible to construct a valid Deck that still results in a divide-by-zero error
            catch(final ArithmeticException aException)
            {
                // No further action necessary
            }
        }
    }

    // Calculates the greatest common denominator between two numbers
    private final long gcd(final long givenNumber1, final long givenNumber2)
    {
        long greatestCommonDenominator = 1; // The minimum GCD of any two numbers is 1

        for (int i = 1; i <= givenNumber1 && i <= givenNumber2; ++i)
        {
            if (givenNumber1 % i == 0 && givenNumber2 % i == 0) // Checks if i is factor of both integers using modulus division
                greatestCommonDenominator = i;
        }

        return greatestCommonDenominator;
    }

    /*
     * Calculates a combination of sampleSize and desiredSuccesses
     * The equation for nCr is (n!) / ((r!) * (n - r)!)
     * This can be simplified to n * (n - 1) * (n - 2) * ... * (n - r + 1) / r!
     * This method calculates nCr using this simplified equation
     * n = sampleSize and r = desiredSuccesses
     */
    private final long ncr(long n, long r)
    {
        /*
         * The combination of n and r is equal to the combination of n and (n - r)
         * For example, 17 combination 13 is the same as 17 combination 4
         * Therefore, we can substitute (n - r) for r if we have a larger r value
         * Attempting to prevent overflow, if (n - r) is less than r we use it instead
         */
        if (n - r < r) r = n - r;
      
        // Tries to calculate the combination of n and r
        try
        {
            long top = 1; // top holds the value of n * (n - 1) * (n - 2) ...
            long bottom = 1; // bottom holds the value of r * (r - 1) * (r - 2) ...
          
            if (r != 0)
            {
                /*
                 * Because this only loops until r = 0, we only calculate the first r numbers of the factorial
                 * This is equivalent to the n * (n - 1) * (n - 2) * ... * (n - r + 1)
                 * In other words, it is a reduced factorial of n from n to n - r + 1
                 * This is how we calculate nCr with the simplified equation
                 * The top is n * (n - 1) * (n - 2) * ... * (n - r + 1) and the bottom is r!
                 */
                while (r != 0)
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
            else top = 1; // n combination 0, where n is any number, is always equal to 1

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
            return 1;
        }      
    }

    // Calculates the hypergeometric probability
    private double probability(long currentDesiredSuccesses)
    {
        double popSize = this.popSize;
        double popSuccesses = this.popSuccesses;
        double popFailures = this.popFailures;
        long sampleFailures = this.sampleSize - currentDesiredSuccesses;

        double prob = 1;
        long combination = ncr(this.sampleSize, currentDesiredSuccesses);

        // Calculate the probability from the successes
        while (currentDesiredSuccesses > 0)
        {
            prob *= (popSuccesses / popSize);
            popSuccesses--;
            popSize--;
            currentDesiredSuccesses--;
        }

        // Calculate the probability from the failures
        while (sampleFailures > 0)
        {
            prob *= (popFailures / popSize);
            popFailures--;
            popSize--;
            sampleFailures--;
        }

        return prob * combination;
    }

    // Calculates the probability of n or greater successes
    private double orGreater(double exactChance)
    {
        long tempDesiredSuccesses = this.desiredSuccesses;

        while (tempDesiredSuccesses < this.sampleSize)
        {
            tempDesiredSuccesses++;
            exactChance += probability(tempDesiredSuccesses);
        }
        return exactChance;
    }

    // Calculates the probability of n or less successes
    private double orLess(double exactChance)
    {
        long tempDesiredSuccesses = this.desiredSuccesses;

        while (tempDesiredSuccesses > 0)
        {
            tempDesiredSuccesses--;
            exactChance += probability(tempDesiredSuccesses);
        }
        return exactChance;
    }

    // Getters
    public double getExactChance() { return this.exactChance; }
    public double getOrGreaterInclusiveChance() { return this.orGreaterInclusiveChance; }
    public double getOrLessInclusiveChance() { return this.orLessInclusiveChance; }
}
