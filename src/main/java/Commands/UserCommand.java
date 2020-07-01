package Commands;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;

import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class UserCommand extends Command {

    public UserCommand(){
        super.name = "user-info";
        super.help = "Get some information about a user";
        super.aliases = new String[]{"userinfo"};
        super.category = new Category("Members");
        super.cooldown = 10;
        super.arguments = "[name]";
    }

    @Override
    protected void execute(CommandEvent event) {
        if (event.getArgs().isEmpty()){
            event.reply("Provide a name! Like this: $user-info [name]");
        }else{
            //Gets the member object of the user so we can use easil
            Member name;
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd"); //Time formatter
            try{
                name = event.getMessage().getMentionedMembers().get(0);
                EmbedBuilder eb = new EmbedBuilder()
                        .setColor(Color.magenta)
                        .setThumbnail("http://pixelartmaker.com/art/1f41a07add48569.png")
                        .setAuthor("Information on " + name.getUser().getName(), "http://www.google.com", name.getUser().getAvatarUrl())
                        .setDescription(name.getUser().getName() + " joined on " + name.getTimeJoined().format(fmt) + " :clock: ")
                        .addField("Status:", name.getOnlineStatus().toString(), true)
                        .addField("Roles:", getRolesAsString(name.getRoles()), true)
                        .addField("Nickname: ", name.getNickname() == null ? "No Nickname" : name.getNickname(), true);
                event.reply(eb.build());
                event.reply(event.getAuthor().getAsMention() + " there you go");
            }catch (IndexOutOfBoundsException ex){
                System.out.println("Exception Occured");
                event.reply("You need to provide the name as a mention.");
            }
        }
    }
    //Get roles for the user
    private String getRolesAsString(List rolesList){
        String roles;
        if (!rolesList.isEmpty()){
            Role tempRole = (Role) rolesList.get(0);
            roles = tempRole.getName();
            for (int i = 1; i < rolesList.size(); i++){
                tempRole = (Role) rolesList.get(i);
                roles = roles + ", " + tempRole.getName();
            }
        }else{
            roles = "No Roles";
        }
        return roles;
    }
}