# WorkflowAssistant

This project helps the user stay mindfull while working. It uses an AI model that communicates with the user via email. The AI will be able to ask the user for details about topics that the user are working on. Then be able to update the topics and set a new time for checking back with the user.

## The plan

This is a CLI application but for the AI model to use with the goal of supporting the users work efforts. The AI will be able to ask the user for details about topics that the user are working on. Then be able to update the topics and set a new time for checking back with the user via email.

## Classes

### Super class

- `Topic`: This abstract super class represents the identifiing foundations that all sub classes will need.

### Sub classes

- `Task`: This class represents a task with a deadline.
- `RecurringTask`: A task without a deadline.
- `Problem`: Details about a problem the user wants to work on.

## Interaction

The user will be able to interact by email. Getting reminders and questions about what you are working on. 
