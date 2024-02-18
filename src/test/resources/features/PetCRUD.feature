@Regression
Feature: Pet end point validations


  Scenario: Add a new pet with valid and invalid cases.
    When user initiates rest service to add new pet
    Then verify response is successful and returns valid pet data
    When user adds same pet again
    Then verify response is successful and returns valid pet data
    When user adds pet with multiple tags
    Then verify response is successful and returns valid pet data
    When user adds new pet without mandatory fields
    Then verify response throws error for not providing mandatory fields
    When user adds pet with invalid id
    Then verify response throws error for adding pet with invalid id

  Scenario: Get a pet with valid and invalid cases
    When user performs search of a valid pet
    Then verify response is successful and returns valid pet data
    When user performs search of non existing pet
    Then verify response throws error for non existing pet
    When user performs search of pet with invalid id
    Then verify response throws error for pet with invalid id


  Scenario: Update a pet with valid and invalid cases
    When user performs update of an existing pet
    Then verify response is successful
    When user performs update of non existing pet
    Then verify response throws error for non existing pet
    When user performs update with invalid id
    Then verify response throws error for pet with invalid id
    When user performs update with invalid request method
    Then verify response throws error with invalid request


  Scenario: Update  pet form with valid and invalid cases
    When user performs update of form for an existing pet
    Then verify response is successful
    When user performs update of form for non existing pet
    Then verify response throws error for non existing pet
    When user performs update of form invalid request method
    Then verify response throws error with invalid request

  Scenario: Delete a pet with valid and invalid cases
    When user performs delete of an existing pet
    Then verify response is successful
    When user performs delete of non existing pet
    Then verify response throws error for non existing pet
    When user performs delete with invalid id
    Then verify response throws error for pet with invalid id

  Scenario Outline: Get pets with status cases
    When user performs search of pets with different "<status>"
    Then verify response is successful with same "<status>" pets

    Examples:
      |status|
      | available|
      | pending|
      | sold|