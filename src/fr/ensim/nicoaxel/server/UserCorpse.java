package fr.ensim.nicoaxel.server;

import fr.ensim.nicoaxel.client.Corpse;

import java.util.ArrayList;

public class UserCorpse {
    ArrayList<Corpse> corpses = new ArrayList<>();
    String user = "";

    public UserCorpse(String user){
        this.user = user;
    }

    public void clear(){
        corpses.clear();
    }

    public void setCorpses(ArrayList<Corpse> corpses){
        this.corpses = corpses;
    }

    public void addAnimal(Corpse a){
        corpses.add(a);
    }
}
