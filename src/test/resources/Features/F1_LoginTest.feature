Feature: As a user, I want to test the login page so that the user can login properly
   Scenario: User should be able to login with valid credentials
     Given User launches the application
     And All login fields should be displayed
     When User enters valid username and password
     And Click the login button
     Then User should be able to see the title "Web Orders"


   Scenario Outline: User should not be able to login with invalid credentials
     Given User launches the application
     And All login fields should be displayed
     When User enter invalid "<username>" and "<password>"
     And Click the login button
     Then User should be able to see the alert "Invalid Login or Password."

     Examples:
       | username | password |
       | sai      | pass     |
       | tester   | testing  |
       | user     | pass     |
