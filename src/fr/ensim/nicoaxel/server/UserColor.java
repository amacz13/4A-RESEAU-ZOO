package fr.ensim.nicoaxel.server;

import java.util.ArrayList;

public class UserColor {

    static ArrayList<String> colors =  new ArrayList<String>() {{add("RED");add("BLUE");add("BLACK");add("GREEN");add("MAGENTA");
        add("ORANGE");add("WHITE");add("CYAN");}};
    static ArrayList<Boolean> free =  new ArrayList<Boolean>() {{add(true);add(true);add(true);add(true);add(true);
        add(true);add(true);add(true);}};

    public static String getFreeColor(){
        for(int i=0 ; i<colors.size() ; i++){
            if(free.get(i)){
                free.set(i, false);
                return colors.get(i);
            }
        }
        return "None";
    }

    public static void liberate(String str){
        for(int i=0 ; i<colors.size() ; i++){
            if(colors.get(i).equals(str)){
                free.set(i, true);
            }
        }
    }
}
