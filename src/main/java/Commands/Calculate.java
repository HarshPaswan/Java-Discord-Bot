package Commands;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Calculate extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {

        String[] message = e.getMessage().getContentRaw().split(" ");

        if(message.length == 1 && message[0].equalsIgnoreCase("!calculate")){
            e.getChannel().sendMessage("To use this command type(without brackets): !calculate [add/sub/mult/div] [first-num] [second-num]").queue();
        }else if(message[0].equalsIgnoreCase("!calculate") && message[1].equalsIgnoreCase("add")){
            int num1 = Integer.parseInt(message[2]);
            int num2 = Integer.parseInt(message[3]);
            e.getChannel().sendMessage("The Result is: " + (num1 + num2)).queue();
        }else if(message[0].equalsIgnoreCase("!calculate") && message[1].equalsIgnoreCase("sub")){
            int num1 = Integer.parseInt(message[2]);
            int num2 = Integer.parseInt(message[3]);
            e.getChannel().sendMessage("The Result is: " + (num1 - num2)).queue();
        }else if(message[0].equalsIgnoreCase("!calculate") && message[1].equalsIgnoreCase("mult")){
            int num1 = Integer.parseInt(message[2]);
            int num2 = Integer.parseInt(message[3]);
            e.getChannel().sendMessage("The Result is: " + (num1 * num2)).queue();
        }
        else if(message[0].equalsIgnoreCase("!calculate") && message[1].equalsIgnoreCase("div")){
            int num1 = Integer.parseInt(message[2]);
            int num2 = Integer.parseInt(message[3]);
            e.getChannel().sendMessage("The Result is: " + (num1 / num2)).queue();
        }
    }
}