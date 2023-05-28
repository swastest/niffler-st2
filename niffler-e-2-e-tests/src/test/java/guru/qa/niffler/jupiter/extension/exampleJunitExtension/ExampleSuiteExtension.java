package guru.qa.niffler.jupiter.extension.exampleJunitExtension;

public class ExampleSuiteExtension implements SuiteExtension {

  @Override
  public void beforeSuite() {
    System.out.println("BEFORE SUITE!");
  }

  @Override
  public void afterSuite() {
    System.out.println("AFTER SUITE!");
  }
}
