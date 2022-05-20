package com.quodemaven;

import org.junit.Assert;
import org.junit.Test;

/**
* Unit Test for Simple App
*/

public class AppTest {
   @Test
   public void testApp() {
      App appObject = new App();
      Assert.assertEquals(appObject.reverseString("Test!"), "!tseT");
   }
}
