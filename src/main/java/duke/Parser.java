package duke;

import duke.ui.Ui;

/**
 * Represents a parser that parses inputs into commands.
 */
public class Parser {

    /**
     * Returns command object from parsing an input message.
     *
     * @param inputMessage Message inputted by user.
     * @return Command to be executed.
     */
    public static Command parseCommand(String inputMessage) {
        String[] inputWords = inputMessage.split(Ui.INPUT_DELIMITER);

        String commandType = inputWords[Ui.INPUT_RAW_INDEX_COMMAND];
        String commandDescription = getDescriptionFromInput(inputWords);

        return new Command(commandType, commandDescription);
    }

    private static String getDescriptionFromInput(String[] inputWords) {
        if (inputWords.length > Ui.INPUT_RAW_INDEX_DESC) {
            String description = inputWords[Ui.INPUT_RAW_INDEX_DESC];
            for (int i = 2; i < inputWords.length; i++) {
                description = String.join(Ui.INPUT_DELIMITER, description, inputWords[i]);
            }
            return description;
        } else {
            return "";
        }

    }
}
