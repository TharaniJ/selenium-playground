Feature: User will login to the moodle site with username and password.

  Once user logged in to the site user name should should be available in the right top corner.


 Scenario: Login with correct credentials
   Given  User navigate to the moodle site
   *  User navigate to the login page
   *  User given "student" as username and "moodle" as password
   When  User clicks the login button
   Then  User should be navigate to the home moodle page
   *  Username should appear in the right top corner of the home page

