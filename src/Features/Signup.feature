Feature: User should register his/her details to to enter the Myntra site.


  Scenario: SignUp with mobile no
    Given User in the SignUp Page of "https://www.myntra.com/"
    And User shouldn't have  registered to the site with given mobile no
    And User signup using <mobileNo>
    When Clicked on continue Button
    Then I should be in the home page/Display error message saying invalid no

  Scenario Outline: Login using different types of credentials
    Examples:
      |MobileNo   | Message               |
      |0774204238 | welcome to Myntra     |
      |Invalid No | Mobile No Invalid     |