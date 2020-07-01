package Events;

import net.dv8tion.jda.api.events.channel.category.CategoryCreateEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class PM extends ListenerAdapter {

    //You can pretty much find an event for everything. Here's another example.
    public void onCategoryCreate(CategoryCreateEvent e){
        e.getGuild().getDefaultChannel().sendMessage("wot").queue();
    }

}