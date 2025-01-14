package x2009all;

public class BatteryCharger
{
  /** rateTable has 24 entries representing the charging costs for hours 0 through 23. */
  private int[] rateTable;

  public BatteryCharger()
  {
    int[] table = {50, 60, 160, 60, 80, 100, 100, 120, 150, 150, 150, 200,
                 40, 240, 220, 220, 200, 200, 180, 180, 140, 100, 80, 60};
    rateTable = table;
  }

  // Part (a)

  /** Determines the total cost to charge the battery starting at the beginning of startHour.
   *  @param startHour the hour at which the charge period begins
   *         Precondition: 0 = startHour = 23
   *  @param chargeTime the number of hours the battery needs to be charged
   *         Precondition: chargeTime > 0
   *  @return the total cost to charge the battery
   */
  private int getChargingCost(int startHour, int chargeTime)
  {
    int cost = 0;

    for (int hour = 0; hour < chargeTime; hour++)
      cost += rateTable[(startHour + hour) % 24];

    return cost;
  }

  // *************************************************

  // Part (b)

  /** Determines start time to charge the battery at the lowest cost for the given charge time.
   *  @param chargeTime the number of hours the battery needs to be charged
   *         Precondition: chargeTime > 0
   *  @return an optimal start time, with 0 = returned value = 23
   */
  public int getChargeStartTime(int chargeTime)
  {
    int bestStartHour = 0;
    int minCost = getChargingCost(0, chargeTime);

    for (int hour = 1; hour < 24; hour++)
    {
      int cost = getChargingCost(hour, chargeTime);
      if (cost < minCost)
      {
        bestStartHour = hour;
        minCost = cost;
      }
    }

    return bestStartHour;
  }

  // *************************************************

  public static void main(String[] args)
  {
    BatteryCharger charger = new BatteryCharger();

    System.out.println("Cost:");
    System.out.println(charger.getChargingCost(12, 1));
    System.out.println(charger.getChargingCost(0, 2));
    System.out.println(charger.getChargingCost(22, 7));
    System.out.println(charger.getChargingCost(22, 30));
    System.out.println();

    System.out.println("Best starting time:");
    System.out.println(charger.getChargeStartTime(1));
    System.out.println(charger.getChargeStartTime(2));
    System.out.println(charger.getChargeStartTime(7));
    System.out.println(charger.getChargeStartTime(30));
    System.out.println();
  }
}
