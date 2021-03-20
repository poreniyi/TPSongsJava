/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3tes2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Niyi Adekunle
 */
public class SongsAndSources {
  public  Map <Integer,String>TouhouSongs =new HashMap<>();// holds touhou songs made by circles format: id,name
    Map <Integer,String>  SourceSongs=new HashMap<>(); // holds original source songs from touhou games the ones made by ZUN format: id,name
    Map <String,String> WordTable=new  HashMap<>(); // will be the world table wihtout numbers
     List<Integer> Count=new ArrayList<>(); // for do word and word table
     List<String> Songs=new ArrayList<>();// for the world table
     
     String file_name= "d:\\\\documents\\\\textfiles\\\\Touhou Music Sources.txt";

    /*
     name:importTxt
     imports the text file for use in the song match and sourcesong match methods
     status:works
     */
    public void importTxt(List<String> test) throws IOException{
         readfiles file= new readfiles(file_name);
        String arry2[]=file.OpenFile();
        for(String val:arry2){
            test.add(val);
        }
    }
     /*
     Method Name:songMatch
     takes in an array that holds the text from the txt document and
     gets the touhou songs the ones made by circles from the txt document removes any uneccasry characters and adds it to the Touhou Songs Map
     Status:work in progess
     */
        public void songMatch() throws IOException{//
           List<String> Temps=new ArrayList<>();
           List<String> Temps2=new ArrayList<>();
           List<String> test=new ArrayList<>();
        importTxt(test);
       for(int i=4;i<test.size();i++){
      String text=test.get(i);
      Pattern p= Pattern.compile("^[0-9]{0,3}[)]");
       Matcher m= p.matcher(text);
       boolean matches=m.find();
       if (matches){
           Temps.add(test.get(i));
                    }
       removeNumbers(Temps,Temps2); 
       }
         move(Temps2);
     move2(Temps);
      
                                                 }
          /* Method name :move
       this part gets rd of the ) character in the line  and then trims the rest of the string and then adds it to hashmap Touhou songs
        Status:works
       */
     public void move(List<String> Temps2){
         StringBuilder sb;
       int counter=0;
       String result="";
       for(String vals: Temps2){
           sb=new StringBuilder(vals);
           sb.deleteCharAt(0);
           result=sb.toString().trim();
           TouhouSongs.put(counter,result);
           counter++;
       }
         
     }
     /*
     Name:move2
     gets rid of numbers and then the ) character and trims the rest of the string and then adds it into list Songs
     Status:works
     */
     public void move2(List<String>List1){
         List<String> List2=new ArrayList<>();
         Pattern p2=Pattern.compile("[^0-9]{3}.*");
           for(String val: List1){
           String song=val;
           song=song.trim();
           Matcher m2=p2.matcher(song);
           if(m2.find()){
                    List2.add(m2.group());
           }
       }
          StringBuilder sb;
       int counter=0;
       String result="";
       for(String vals: List2){
           sb=new StringBuilder(vals);
           sb.deleteCharAt(0);
           result=sb.toString().trim();
           Songs.add(counter,result);
           counter++;
       }    
     }
 
        /*
        method Name:sourceMatch
        gets the source songs the ones made by zun from the txt document and adds it to the SourceSongs hashmap
        Status:Works
        */
       
     public void sourceMatch( ) throws IOException{   
         List<String> Temp=new ArrayList<>();
         List<String> test=new ArrayList<>();
         importTxt(test);
          for(int i=4;i<test.size();i++){
      String text=test.get(i);
      Pattern p= Pattern.compile("^\\D");     
       Matcher m= p.matcher(text);     
       boolean matches=m.find();       
     if (matches){
           Temp.add(test.get(i));
                    }
                                  }
         moveLines(Temp);
             int counter=0;
             for(String SourceSong: Temp){
                 SourceSongs.put(counter, SourceSong);
                         counter++;
             }
         
                                          }
     /*
     Method name:WordTable gets the number of songs per line 
     status:works
     */
     public void WordTable() throws IOException{
        List<String> Temp=new ArrayList<>();
        List<String>txt=new ArrayList<>();
        importTxt(txt);
          for(int i=4;i<txt.size();i++){
      String text=txt.get(i);
      Pattern p2=Pattern.compile("^[0-9]{0,3}[)]");// this is for the touhou songs
      Pattern p= Pattern.compile("^\\D");     // this is for thesource songs
       Matcher m= p.matcher(text);// for the source songs  
       Matcher m2= p2.matcher(text);// for the touhou songs
       boolean matches=m.find(); // for the source songs
       boolean matches2=m2.find();// for the touhou songs
     if (matches){
            Temp.add(text);
                    }
     if(matches2){
         Temp.add(text);
     }
       moveLines(Temp);
          }
          int counter=0;
          for(int i=1;i<Temp.size();i++){
               String text=Temp.get(i);
               Pattern p= Pattern.compile("^[0-9]{0,3}[)]");     // this is for touhou songs
       Matcher m= p.matcher(text);// for the source songs   by ZUN
       boolean matches=m.find(); // for the source songs by ZUN
       if(matches){
           
           //System.out.println(text);
           counter++;
       }
       if(!matches){
            Count.add(counter);
           counter=0;          
       }
       //for the final value
       if(i==Temp.size()-1){
          //System.out.println("This is the end");
           Count.add(counter);
       }
                                   }
     }
    
     /*
     Method name:move2 gets rid of the numbers in the line and gets rid of duplicates only unique songs now remain 
     status works:
     */
     public void removeNumbers(List<String> fromThisList,List<String> IntoThisList){
         Pattern p2=Pattern.compile("[^0-9]{3}.*");
           for(String val: fromThisList){
           String song=val;
           song=song.trim();
           Matcher m2=p2.matcher(song);
           if(m2.find()){
               if(!IntoThisList.contains(m2.group())){ 
                    IntoThisList.add(m2.group());
               }
           }
       }
     }
 
     /*
     method name:moveLines
     moves the lines that were split up back on the same line
     */
     public void moveLines(List<String> Values){
          for(int i=0;i<Values.size();i++){
         String line=Values.get(i);
         if(line.charAt(0)=='|'){
             String newLine=Values.get(i-1)+line;
             Values.set(i-1, newLine);
             Values.remove(i);
             i=0;
         }
     }
     }
     /*
     gets name:doword2
     Makes the songlines;
     in the formmat Sourcesong,id+TouhouSong,id
     status:works
     */
     public void doword2(){
         int songcounter=0;
         int skipcounter=0;
         int songid=0;
         int sourceid=0;
         String allsongs="";
         for(int key: SourceSongs.keySet()){
             String SourceSong=SourceSongs.get(key);
             for(int i=0;i<Count.get(skipcounter);i++){
                 if(songcounter!=Songs.size()){
                      String song=Songs.get(songcounter);
                       songid=getsongid(TouhouSongs,song);
                       sourceid=getsongid(SourceSongs,SourceSong);
                // System.out.println("The Source Song is:"+SourceSong+sourceid+" The Touhou song is:"+song+"The song id is:"+songid);
                // System.out.println("The Source Song is:"+SourceSong+SourceSong);
                allsongs+=song+"/";
                 songcounter++;
                 }
             }
          skipcounter++;
                   WordTable.put(SourceSong, allsongs);
          allsongs="";
     }
 
                  //System.out.println("The size is"+WordTable.size());
 for(String SourceSong: WordTable.keySet()){
            // System.out.println(" The SourceSong is:"+SourceSong+" Touhou Song is:"+WordTable.get(SourceSong));
     }
     }
     /*
     name getMap
     returns a map either TouhouSongs or SourceSongs
     */
     public Map<Integer,String> getMap(String name){
        Map<Integer,String> Temp=new HashMap<>();
         switch(name){
             case"S":
                 Temp.putAll(SourceSongs);
                     break;
            case"T":
                Temp.putAll(TouhouSongs);
                break;
         }
         return Temp;
     }
     /*
     name: getsongid
     takes in a map and retrieives the corresponding id from the song name;
     status:works
     */
     public int getsongid(Map<Integer,String> Hash,String name){
         int songid=0;
        // TouhouSongs.put(500, "not good");
        // SourceSongs.put(500, "not good");
         String songname="";
         for(int id:Hash.keySet()){
             songname=Hash.get(id);
             if(name.equals(songname)){
                 songid=id;
                   break;
             }
         }
         return songid;
     }
     /*
     name getSongname
     returns the songname based on the paramater name
     */
      public String getsongName(int songid){
         String songname=TouhouSongs.get(songid);
         return songname;
     }
      /*
      returns the source id based on the given song
      status:work in progress
      */
      public String SongToSource(String tsongname){
          String TheSourceSong="";
          String songsplit[];
          for(String SourceSong:WordTable.keySet()){
              songsplit=WordTable.get(SourceSong).split("/");
              for(String songs: songsplit){
              if(tsongname.equals(songs)){
                 TheSourceSong+=SourceSong;                
              }
          }
          }
         return TheSourceSong;
     }
     
        public void printMaps(String sw){
           Map<Integer,String> Temp=new HashMap<>();
           
            switch(sw){
                case "T":
                    Temp.putAll(TouhouSongs);
                    break;
                case "S":
                    Temp.putAll(SourceSongs);
                    break;
                case "W":
                     for(String id: WordTable.keySet()){
             System.out.println("The Source Song is:"+id+" The song is: "+WordTable.get(id));
         }   
                     System.out.println(WordTable.size());
                    break;
    
            }
         for(int id: Temp.keySet()){
             System.out.println("The song is:"+Temp.get(id)+" The id is:"+id);
         }   
        }
        public void printSongs(){
            for(String song: Songs){
                System.out.println(song);
            }     
        }
        public void printCount(){
            int sourcesong=2;
            int counters=0;
            for(int counter: Count){
                System.out.println(sourcesong+"|: "+counter+" "+SourceSongs.get(counters));
                counters++;
                sourcesong++;
            }
            System.out.println(Count.size());
        }
        public void writeSongs(String writeToThis) throws IOException{
       writefiles data=new writefiles(writeToThis,true);
          Path path=Paths.get(writeToThis);//writeToThis;
          Files.deleteIfExists(path);
          for(String object:Songs){
              data.writeToFile(object);
          }     
        }
         public void writeSongs2(String writeToThis) throws IOException{
       writefiles data=new writefiles(writeToThis,true);
          Path path=Paths.get(writeToThis);//writeToThis;
          Files.deleteIfExists(path);
          List<String>values=new ArrayList<>();
          int counter=0;
          Collections.sort(values,Collator.getInstance());
          for(int id:TouhouSongs.keySet()){
              values.add(TouhouSongs.get(id));
          }     
                Collections.sort(values,Collator.getInstance());
                for(String val:values){
                    data.writeToFile(val+val.hashCode()+"this counter"+counter);
                    counter++;
                }
//          for(int id:TouhouSongs.keySet()){
//              data.writeToFile(counter+TouhouSongs.get(id));
//              counter++;
//          }     
        }
     

}
