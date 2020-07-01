import Commands.*;
import Events.HelloEvent;
import Events.PM;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jdautilities.command.CommandClientBuilder;

public class App extends ListenerAdapter{

    public static void main(String args[]) throws Exception{

       // JDA jda = new JDABuilder("NzI3MTk4OTM0MTUzNDI5MDQy.XvtuTQ.2IIRHA_4-anL27gl_8A2zSgr-2Q").build();
        JDA jda = new JDABuilder(AccountType.BOT).setToken("Your token").build();
        //Register our events
        jda.addEventListener(new HelloEvent());
        jda.addEventListener(new PM());
        jda.addEventListener(new Calculate());
        jda.addEventListener(new InviteCommand());
        jda.addEventListener(new UserCommand());


        CommandClientBuilder builder = new CommandClientBuilder();

        builder.setOwnerId("Your token");
        //command prefix
        builder.setPrefix("!");
        //Add our command(this is now where we register the commands)
        builder.addCommand(new ServerInfo());
        //Bot helps u out. its nice
        builder.setHelpWord("help");
        builder.addCommand(new Image());
        builder.addCommand(new UserCommand());
        //build the command client
        CommandClient client = builder.build();
        //We no longer need to register each command class here, we just register the command client
        jda.addEventListener(client);
    }

}