@Regression
Feature: Pet Image scenarios

  Scenario: Update image for a pet
    Given create a new pet
    When user uploads "pic.jpg" with "most popular pet" description
    Then verify response indicates upload image is success
    And  user deletes the pet