  // A-2, (b)

public class LoudDog extends Dog
{
  public LoudDog(String name)
  {
    super(name);
  }

  public String speak()
  {
    String s = super.speak();
    return s + s;
  }
}