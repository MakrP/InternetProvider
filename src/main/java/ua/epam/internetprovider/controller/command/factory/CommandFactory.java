package ua.epam.internetprovider.controller.command.factory;

import ua.epam.internetprovider.controller.command.Command;
import ua.epam.internetprovider.controller.command.UnknownCommand;
import ua.epam.internetprovider.controller.command.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

public class CommandFactory {
    private static String COMMANDS_PACKAGE = "ua.epam.internetprovider.controller.command.";
    public static Command getCommand(HttpServletRequest req) throws CommandException {
        try{
            return (Command) getCommandClass(req).newInstance();
        } catch (Exception e) {
            throw new CommandException("Error create command object");
        }
    }

    private static Class getCommandClass(HttpServletRequest req) {
        String commandClassName = COMMANDS_PACKAGE + req.getParameter("command") + "Command";
        Class<?> resultCommand = null;
        try{
            resultCommand = Class.forName(commandClassName);
        } catch (ClassNotFoundException e) {
            resultCommand = UnknownCommand.class;
        }
        return resultCommand;
    }
}
