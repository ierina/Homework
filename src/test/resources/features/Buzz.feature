Feature: Posts on Buzz
@Buzz
  Scenario: Validate that system prevents posting an empty post in Buzz section
    Given user is logged into OrangeHRM with the following credentials
      | username | password |
      | Admin    | admin123 |
    When user navigates to "Buzz" from the left menu
    And user leaves the post box empty
    And user clicks the "Post" button
    Then a validation message appears indicating that the post cannot be empty
