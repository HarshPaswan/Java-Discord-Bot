package Commands;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class InviteCommand extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        int timeString = 3600; //seconds in an hour
        String[] message = e.getMessage().getContentRaw().split(" "); //splits message received into an array holding each word in each index
        if (message.length == 1 && message[0].equalsIgnoreCase("!invite")){  //if they type the command alone, tell them how to use it
            e.getChannel().sendMessage("To use !invite do: !invite create").queue(); //tells user how to use the !invite command
        }else if(message[0].equalsIgnoreCase("!invite") && message[1].equalsIgnoreCase("create")) {
            e.getChannel().sendMessage("Hey " + e.getAuthor().getName() + "! You want to invite someone? Cool!").queue();
            e.getChannel().sendMessage("Give them this link: " + e.getChannel().createInvite().setMaxAge(timeString).complete().getUrl()).queue(); //create an invite, set length to 1 hour, return url as string
            e.getChannel().sendMessage("The invite expires in: " + timeString + " seconds.").queue(); //Tells them how long the invite will exist for
        }

    }
}
