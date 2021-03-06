
import java.text.SimpleDateFormat;
import java.util.Calendar;

import TheLadders.ReportFormatType;
import TheLadders.TheLadders;

import job.Job;
import job.JobFactory;
import job.JobName;
import job.JobType;
import jobseeker.HumanName;
import jobseeker.Jobseeker;
import jobseeker.JobseekerFactory;
import jobseeker.NoSuchResumeException;
import jobseeker.ResumeName;
import jobseeker.ResumeRequiredException;
import employer.AlreadyExistsException;
import employer.Employer;
import employer.EmployerFactory;
import employer.EmployerName;



public class ObjectCalisthenics
{
  public final static void main(String[] args) throws AlreadyExistsException, NoSuchResumeException
  {

    Employer theMasonry = EmployerFactory.employerFrom(new EmployerName("Perfect Cuboid Masonry"));
    Employer numbersRUs = EmployerFactory.employerFrom(new EmployerName("Numbers-R-Us"));

    Job masonryCEOJob = JobFactory.jobFrom(theMasonry, JobType.ATS, new JobName("CEO"));
    Job masonryGroundskeeperJob1 = JobFactory.jobFrom(theMasonry, JobType.ATS, new JobName("Groundskeeper"));
    Job masonryGroundskeeperJob2 = JobFactory.jobFrom(theMasonry, JobType.ATS, new JobName("Groundskeeper"));
    Job masonryGroundskeeperJob3 = JobFactory.jobFrom(theMasonry, JobType.ATS, new JobName("Groundskeeper"));
    Job masonrySeniorGeometerJob = JobFactory.jobFrom(theMasonry, JobType.JREQ, new JobName("Senior Geometer"));

    Job numberStoreJob = JobFactory.jobFrom(numbersRUs, JobType.ATS, new JobName("Head of 64-bit Unsigned Integer Sales"));

    theMasonry.postJob(masonryCEOJob);
    theMasonry.postJob(masonryGroundskeeperJob1);
    theMasonry.postJob(masonryGroundskeeperJob2);
    theMasonry.postJob(masonryGroundskeeperJob3);
    theMasonry.postJob(masonrySeniorGeometerJob);

    numbersRUs.postJob(numberStoreJob);

    Jobseeker sophie = JobseekerFactory.jobseekerFrom( new HumanName("Sophie", "Germain") );
    Jobseeker euler = JobseekerFactory.jobseekerFrom( new HumanName("Leonhard", "Euler") );
    Jobseeker me = JobseekerFactory.jobseekerFrom( new HumanName("Charles", "Morris") );

    try
    {
      sophie.applyToJob(numberStoreJob);
    }
    catch (ResumeRequiredException e)
    {}

    try
    {
      System.out.println("Jobseeker applying to ATS CEO without Resume");
      euler.applyToJob(masonryCEOJob);
    }
    catch (ResumeRequiredException innerEx)
    {}

    try
    {
      System.out.println("Jobseeker applying to ATS Groundskeeper without Resume");
      euler.applyToJob(masonryGroundskeeperJob1);
    }
    catch (ResumeRequiredException innerEx)
    {}

    System.out.println("Jobseeker saving job Senior Geometer");
    euler.saveJob(masonrySeniorGeometerJob);
    
    System.out.print("The saved job was: ");
    euler.getListingOfSavedJobs();

    try
    {
      System.out.println("Jobseeker applying to JREQ Senior Geometer without Resume");
      me.applyToJob(masonrySeniorGeometerJob);
    }
    catch (ResumeRequiredException ex)
    {
      System.out.println("Jobseeker recieved a ResumeRequiredException... creating a resume");
      me.createResume("All About Me");
      me.createResume("Relevant Facts");
      try
      {
        System.out.println("Jobseeker applying to JREQ Senior Geometer with Resume");
        me.applyToJobWithResume(masonrySeniorGeometerJob, new ResumeName("Relevant Facts"));
      }
      catch (ResumeRequiredException ex2)
      {
        System.out.println("Exception: Jobseeker applying to JREQ Senior Geometer with Resume [FAILED]: " + ex2);
      }
    }

    String today = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());

    System.out.println("Testing TheLadders.reportForJobApplicationsByDay for Today and CSV:");
    TheLadders.reportForJobApplicationsByDay(today, ReportFormatType.CSV);

    System.out.println("Testing TheLadders.reportForJobApplicationsByDay for Today and HTML:");
    TheLadders.reportForJobApplicationsByDay(today, ReportFormatType.HTML);

    System.out.println("Testing TheLadders.reportForAggregateJobApplicationsSuccessRate for CSV:");
    TheLadders.reportForAggregateJobApplicationsSuccessRate(ReportFormatType.CSV);
  }
}
