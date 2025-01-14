package x2018all;
public class FrogSimulation
{
  /** Distance, in inches, from the starting position to the goal. */
  private int goalDistance;

  /** Maximum number of hops allowed to reach the goal. */
  private int maxHops;

  /** Constructs a FrogSimulation where dist is the distance, in inches, from the starting
   *  position to the goal, and numHops is the maximum number of hops allowed to reach the goal.
   *  Precondition: dist > 0; numHops > 0
   */
  public FrogSimulation(int dist, int numHops)
  {
    goalDistance = dist;
    maxHops = numHops;
  }

  /** Returns an integer representing the distance, in inches, to be moved when the frog hops.
   */
  private int hopDistance()
  {
    int hop = testHops[nextHopIndex];
    nextHopIndex++;
    if (nextHopIndex >= testHops.length)
      nextHopIndex = 0;
    return hop;
  }

  /********************** Part (a) *************************/

  /** Simulates a frog attempting to reach the goal as described in part (a).
   *  Returns true if the frog successfully reached or passed the goal during the simulation;
   *  false otherwise.
   */
  public boolean simulate()
  {
    int pos = 0;
    for (int hops = 0; hops < maxHops; hops++) // Or:
                                               // for (int hops = 1; hops <= maxHops; hops++)
    {
      pos += hopDistance();
      if (pos >= goalDistance)
        return true;
      else if (pos < 0)
        return false;
    }
    return false;
  }

  /********************** Part (b) *************************/

  /** Runs num simulations and returns the proportion of simulations in which the frog
   *  successfully reached or passed the goal.
   *  Precondition: num > 0
   */
  public double runSimulations(int num)
  {
    int count = 0;
    for (int n = 0; n < num; n++)
      if (simulate())
        count++;
    return (double)count/num;
  }

  /********************** Test *************************/

  private int[] testHops;
  private int nextHopIndex;

  public static void main(String[] args)
  {
    int[][] hopSequences =
    {
      {5, 7, -2, 8, 6},
      {6, 7, 6, 6},
      {6, -6, 31},
      {4, 2, -8},
      {5, 4, 2, 4, 3}
    };

    FrogSimulation sim = new FrogSimulation(24, 5);

    for (int i = 0; i < hopSequences.length; i++)
    {
      sim.testHops = hopSequences[i];
      sim.nextHopIndex = 0;
      System.out.println("Simulation # " + i+1 + ": " + sim.simulate());
    }

    sim.testHops = hopSequences[0];
    sim.nextHopIndex = 0;
    System.out.println(sim.runSimulations(10));
  }
}