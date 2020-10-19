Feature: User will visit the edit profile page.

  User will update/add user information in that page.

  Background:
    Given  User navigate to the moodle site and then navigate to the login page
    *  User given "student" as username and "moodle" as password and login
    * User navigate to the profile page


  Scenario: Update Description of the user
    Given  User Navigate to the edit profile page
    And Scroll down and find the description
    When  User remove the description and add "I'm Barbara" and submit updates
    Then  User will navigate back to the profile page
    And  Updated description will appear in the profile

  Scenario: Update user interest
    Given User Navigate to the edit profile page
    And Scroll down and click interest
    When Add "Farming" as interest, remove "music" from interest and submit updates
    Then User will navigate back to the profile page
    And Updated interest will appear in the profile


  Scenario: Update country of the user
    Given  User Navigate to the edit profile page
    And Scroll down and find the country
    When  User update the country as india and submit updates
    Then  User will navigate back to the profile page
    And  Updated country will appear in the profile


