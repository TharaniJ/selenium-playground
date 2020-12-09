Feature: User will register to the ecommerce portal with user details.

  Once user registered in to the site user name should should be available in the right top corner.
  and user will be in my account page.


  Scenario: Register user with user detail
    Given  User navigate to the ecommerce site
    And  User Enter the email address and click create account
    When  User Enter user detail and click submit account
    Then  User should be navigate to the my account page


