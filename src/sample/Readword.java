package sample;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by shenqianqian on 2017/11/1.
 */
class Readword{
    String URLString;
    int id;
    public  Readword(){
        URLString="";
        id=0;
    }
    public Readword(String URLString,int id){
        this.URLString=URLString;
        this.id=id;
    }
    public String getWords() {
        ArrayList<String> myword=new ArrayList<>();
        try {
            java.net.URL url = new java.net.URL(URLString);
            int count = id;
            try (Scanner input = new Scanner(url.openStream())) {
                while (input.hasNext()) {
                    String word = input.next();
                    myword.add(word);
                    //  System.out.println("The first words is " +word.toString() );
                    if (word.trim().length() > 0)
                        count += 1;
                }
            }
            // "The first number of words is " + myword.get(id));
            //  System.out.println("The number of words is " + count);
        }
        catch (java.net.MalformedURLException ex) {
            System.out.println("Invalid URL");
        }
        catch (java.io.IOException ex) {
            System.out.println("IO Errors: no such file");
        }
        return myword.get(id);
    }
    public int getId() {
        return id;
    }
}