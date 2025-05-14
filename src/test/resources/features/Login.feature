Feature: Login functionality
  @Login
  Scenario: Validate that the username field on the Login page is case-sensitive
    Given a user account exists with username "Admin" and password "admin123"
    When user opens the login page
    And user enters username "admin"
    And user enters password "admin123"
    And user clicks the login button
    Then an error message appears indicating "Invalid credentials" above the login form