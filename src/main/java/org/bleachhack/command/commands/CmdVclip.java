package main.java.org.bleachhack.command.commands;

import org.bleachhack.command.CommandCategory;
import org.bleachhack.command.Command

public class CmdVclip extends Command {

    public CmdVclip() {
        super("vclip",
                "Teleport through blocs on the y-axis",
                "vclip <amount>",
                CommandCategory.MISC);
    }

    @Override
    public void onCommand(String alias, String[] args) throws Exception {

    }
}
