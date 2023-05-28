package guru.qa.niffler.test.exampleJunit;

import guru.qa.niffler.jupiter.extension.exampleJunitExtension.CallbacksExtension;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

@Disabled
@ExtendWith(CallbacksExtension.class)
public class SecondJUnitTest extends BaseTest {

  @AfterAll
  static void afterAll() {
    System.out.println("  #### @AfterAll");
  }

  @BeforeAll
  static void beforeAll() {
    System.out.println("  #### @beforeAll");
  }

  @BeforeEach
  void beforeEach() {
    System.out.println("      #### @BeforeEach");
  }

  @AfterEach
  void afterEach() {
    System.out.println("      #### @AfterEach");
  }

  @Test
  void firstTest() {
    System.out.println("             #### @Test firstTest()");
  }

  @Test
  void secondTest() {
    System.out.println("              #### @Test secondTest()");
  }
}