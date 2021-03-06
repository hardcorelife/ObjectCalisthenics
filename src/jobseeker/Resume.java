package jobseeker;

public class Resume
{

  public static final Resume invalid = new Resume();
  private ResumeName         name;

  public Resume()
  {}

  public Resume(String name)
  {
    this.name = new ResumeName(name);
  }

  public Resume(ResumeName name)
  {
    this.name = name;
  }

  public boolean hasName(ResumeName name) 
  {
    return this.name.equals(name);
  }
};
