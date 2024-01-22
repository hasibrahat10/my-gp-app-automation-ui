Feature: My Gp App Login

  @signIn
  Scenario: I want to sign in the app
    Then I click on sign in button and navigate to sign in screen
    Then I entered mobile phone number and click continue button
    And I want to check the verification screen text " Enter the verification code \n we’ve sent to 01720381823"

