Feature: Assigning leave
  @AssignLeave
  Scenario: Prevent assigning leave for past dates
    Given user is logged into OrangeHRM with the valid credentials
      | username | password |
      | Admin    | admin123 |
    And a new employee is created for the test
    When user navigates to the "Assign Leave" page via Leave > Assign Leave
    And user enters the test employee name in the "Employee Name" field and selects from the dropdown
    And user selects "CAN - Personal" from the "Leave Type" dropdown
    And user select a past date from the date calendar
    And user presses the "Assign" button
    Then the system prevents the leave from being assigned
