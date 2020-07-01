package Events;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class HelloEvent extends ListenerAdapter {

//    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
//        String messageSent = event.getMessage().getContentRaw(); //Get's the event's message as a raw string and stores it into a string
//        if (messageSent.equalsIgnoreCase("hello")){ //Checks to see what the message is
//            event.getChannel().sendMessage("hello").queue(); //Sends a message to the channel that the bot received the message from. Queue must be used to queue bot actions
//        }
//    }

    //a more advanced example
    public void onGuildMessageReceived(GuildMessageReceivedEvent e){
        String[] args = e.getMessage().getContentRaw().split(" ");
        String name = e.getMember().getUser().getName(); //Get the name of the user who sent the message
        if(args[0].equalsIgnoreCase("hi")){
            if(!e.getMember().getUser().isBot()){ //Checks to see if the user who triggered the event is a bot or not. This prevents an endless loop of the message hi being sent.
                e.getChannel().sendMessage("hi " + name).queue(); //Say hi plus their name
            }
        }
        if(args[0].equalsIgnoreCase("hello")){
            if(!e.getMember().getUser().isBot()){ //Checks to see if the user who triggered the event is a bot or not. This prevents an endless loop of the message hi being sent.
                e.getChannel().sendMessage("hello " + name).queue(); //Say hello plus their name
            }
        }
    }
}