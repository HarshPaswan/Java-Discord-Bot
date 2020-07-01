package Commands;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class Image extends Command {

    public Image(){
        this.name = "image";
        this.arguments = "[width] [height] [image-url] [degrees to rotate(optional)]"; //arguments shown in $helpme
        this.cooldown = 10;
        this.help = "Manipulates images. Provide an image link and you can resize and or rotate it.";
    }
    //https://github.com/coobird/thumbnailator <---- the api I used
    @Override
    protected void execute(CommandEvent e) {
        if (e.getArgs().equalsIgnoreCase("")){
            e.reply("You did not provide any arguments, " + e.getAuthor().getAsMention());
            e.reply("To use the Image Manipulator, type the command like this(without brackets]: $image [width] [height] [image-url] [degrees to rotate(optional)]");
        }else{
            try{
                String[] args = e.getArgs().split(" "); //Stores all of the arguments sent with the command
                int width = Integer.parseInt(args[0]);
                int height = Integer.parseInt(args[1]);
                URL imageURL = new URL(args[2]); //Stores the image link into a URL object
                int rotateAmount = 0;
                if (args.length == 4){ //Checks to see if they provided the optional rotation amount
                    rotateAmount = Integer.parseInt(args[3]);
                }
                OutputStream os = new ByteArrayOutputStream(); //Makes a new outputstream to be used to send the new image once it has been manipulated on the next line of code
                Thumbnails.of(imageURL).forceSize(width,height).rotate(rotateAmount).outputFormat("png").toOutputStream(os); //takes the image, does things to it, sends to our output stream
                byte[] imageInByte = ((ByteArrayOutputStream) os).toByteArray(); //Converts the ByteArrayOutputStream to an actual Byte Array so we can send a file using discord java api
                e.getChannel().sendFile(imageInByte,"newfile.png").queue(); //Sends image in chat
                //Image sent
                e.reply(e.getAuthor().getAsMention() + ", here is your new image!!! It has been resized to: " + width + "x" + height + " and rotated to " + rotateAmount + "°");
            }catch (Exception ex){
                System.out.println("Some exception happened. oops");
            }

        }

    }
}
